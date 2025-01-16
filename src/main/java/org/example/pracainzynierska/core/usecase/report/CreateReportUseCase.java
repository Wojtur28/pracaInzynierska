package org.example.pracainzynierska.core.usecase.report;

import com.example.model.Report;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.raport.ReportRepository;
import org.example.pracainzynierska.core.entities.raport.ReportStatus;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.mapper.ReportMapper;
import org.example.pracainzynierska.mapper.SteamGameMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateReportUseCase {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final UpdateReportStatusUseCase updateReportStatusUseCase;

    @CacheEvict(value = "reportsCache", allEntries = true)
    public Report createReport(Report report) {

        report.setReportStatus(String.valueOf(ReportStatus.IN_PROGRESS));

        Report savedReport = reportMapper.toDto(reportRepository.save(reportMapper.toEntity(report)));

        updateReportStatusUseCase.updateReportStatusAsync(String.valueOf(savedReport.getId()));

        return savedReport;
    }
}
