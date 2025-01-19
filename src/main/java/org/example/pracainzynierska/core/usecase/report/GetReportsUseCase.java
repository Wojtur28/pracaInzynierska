package org.example.pracainzynierska.core.usecase.report;

import com.example.model.Report;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.report.ReportRepository;
import org.example.pracainzynierska.mapper.ReportMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetReportsUseCase {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;


    public List<Report> getReports(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return reportMapper.toDto(reportRepository.findAll(pageable).getContent());
    }
}
