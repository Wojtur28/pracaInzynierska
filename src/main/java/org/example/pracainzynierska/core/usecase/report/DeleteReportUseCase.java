package org.example.pracainzynierska.core.usecase.report;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.raport.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteReportUseCase {

    private final ReportRepository reportRepository;

    public void deleteReport(UUID id) {
        reportRepository.deleteById(id);
    }
}
