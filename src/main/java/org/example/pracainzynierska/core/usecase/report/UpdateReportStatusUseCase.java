package org.example.pracainzynierska.core.usecase.report;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.example.pracainzynierska.core.entities.report.ReportEntity;
import org.example.pracainzynierska.core.entities.report.ReportRepository;
import org.example.pracainzynierska.core.entities.report.ReportStatus;
import org.example.pracainzynierska.core.entities.report.ReportType;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.exception.ReportNotFoundException;
import org.example.pracainzynierska.mapper.SteamGameMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateReportStatusUseCase {

    private final ReportRepository reportRepository;
    private final SteamGameRepository steamGameRepository;
    private final SteamGameMapper steamGameMapper;
    private final GameRatingRepository gameRatingRepository;

    @Async
    @CacheEvict(value = "reportsCache", key = "#reportId")
    public void updateReportStatusAsync(String reportId, ReportType reportType) {
        ReportEntity reportEntity = reportRepository.findById(UUID.fromString(reportId))
                .orElseThrow(() -> new ReportNotFoundException("Raport o id " + reportId + " nie znaleziony"));

        if (reportType.equals(ReportType.RATING)) {
            List<GameRatingEntity> ratingsEntities = gameRatingRepository.findByCreatedAtBetween(reportEntity.getStartDate().atStartOfDay(), reportEntity.getEndDate().atStartOfDay());
            reportEntity.setGameRatings(new HashSet<>(ratingsEntities));
        } else if (reportType.equals(ReportType.GAME)) {
            List<SteamGameEntity> fetchedSteamGames = steamGameRepository.findByReleaseDateBetween(reportEntity.getStartDate(), reportEntity.getEndDate());
            reportEntity.setSteamGames(new HashSet<>(fetchedSteamGames));
        }

        reportEntity.setReportStatus(ReportStatus.FINISHED);

        reportRepository.save(reportEntity);
    }
}
