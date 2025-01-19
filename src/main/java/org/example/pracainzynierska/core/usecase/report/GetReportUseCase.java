package org.example.pracainzynierska.core.usecase.report;

import com.example.model.ReportDetails;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.report.ReportRepository;
import org.example.pracainzynierska.exception.ReportNotFoundException;
import org.example.pracainzynierska.mapper.ReportMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetReportUseCase {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    @Cacheable(value = "reportsCache", key = "#id")
    public ReportDetails getReport(UUID id) {
        return reportMapper.toDetailsDto(reportRepository.findById(id).orElseThrow(()
                -> new ReportNotFoundException("Raport o id " + id + " nie znaleziony")));
    }
}
