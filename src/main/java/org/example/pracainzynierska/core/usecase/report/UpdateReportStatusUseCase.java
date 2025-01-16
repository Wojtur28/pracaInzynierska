package org.example.pracainzynierska.core.usecase.report;

import com.example.model.SteamGame;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.raport.ReportEntity;
import org.example.pracainzynierska.core.entities.raport.ReportRepository;
import org.example.pracainzynierska.core.entities.raport.ReportStatus;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.exception.ReportNotFoundException;
import org.example.pracainzynierska.mapper.SteamGameMapper;
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

    @Async
    public void updateReportStatusAsync(String reportId) {
        ReportEntity reportEntity = reportRepository.findById(UUID.fromString(reportId))
                .orElseThrow(() -> new ReportNotFoundException("Raport o id " + reportId + " nie znaleziony"));

        List<SteamGame> steamGames = steamGameMapper.toDto(
                steamGameRepository.findByReleaseDateBetween(reportEntity.getStartDate(), reportEntity.getEndDate())
        );
        reportEntity.setSteamGames(new HashSet<>(steamGameMapper.toEntity(steamGames)));

        reportEntity.setReportStatus(ReportStatus.FINISHED);

        reportRepository.save(reportEntity);
    }
}
