package org.example.pracainzynierska.web.entrypoint;

import com.example.api.ReportApi;
import com.example.model.Report;
import com.example.model.ReportDetails;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.report.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@AllArgsConstructor
public class ReportController implements ReportApi {

    private final GetReportsUseCase getReportsUseCase;
    private final GetReportUseCase getReportUseCase;
    private final DeleteReportUseCase deleteReportUseCase;
    private final CreateReportUseCase createReportUseCase;
    private final EvictReportsCacheUseCase evictReportsCacheUseCase;

    @Override
    public ResponseEntity<List<Report>> getReports(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(getReportsUseCase.getReports(page, size));
    }

    @Override
    public ResponseEntity<ReportDetails> getReport(@PathVariable UUID reportId) {
        return ResponseEntity.ok(getReportUseCase.getReport(reportId));
    }

    @Override
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        return ResponseEntity.ok(createReportUseCase.createReport(report));
    }

    @Override
    public ResponseEntity<Void> deleteReport(@PathVariable UUID reportId) {
        deleteReportUseCase.deleteReport(reportId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> refreshReportsCache() {
        evictReportsCacheUseCase.evictAllReportsCache();
        return ResponseEntity.noContent().build();
    }
}
