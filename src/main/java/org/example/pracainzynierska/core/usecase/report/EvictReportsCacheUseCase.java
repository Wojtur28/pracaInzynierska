package org.example.pracainzynierska.core.usecase.report;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EvictReportsCacheUseCase {

    @CacheEvict(value = "reports", allEntries = true)
    public void evictAllReportsCache() {
    }
}
