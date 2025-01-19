package org.example.pracainzynierska.core.usecase.report;

import com.example.model.Report;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.report.ReportEntity;
import org.example.pracainzynierska.core.entities.report.ReportRepository;
import org.example.pracainzynierska.core.entities.report.ReportStatus;
import org.example.pracainzynierska.mapper.ReportMapper;
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

        ReportEntity entity = reportMapper.toEntity(report);
        ReportEntity savedEntity = reportRepository.save(entity);

        updateReportStatusUseCase.updateReportStatusAsync(String.valueOf(savedEntity.getId()), savedEntity.getReportType());

        return reportMapper.toDto(savedEntity);
    }
}
