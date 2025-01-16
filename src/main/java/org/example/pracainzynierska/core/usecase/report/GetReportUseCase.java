package org.example.pracainzynierska.core.usecase.report;

import com.example.model.Report;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.raport.ReportRepository;
import org.example.pracainzynierska.mapper.ReportMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetReportUseCase {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public Report getReport(UUID id) {
        return reportMapper.toDto(reportRepository.findById(id).orElseThrow());
    }
}
