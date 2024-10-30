package org.example.pracainzynierska.core.mapper;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class GameIdMapper {

    private Map<UUID, Long> uuidToLongMap = new HashMap<>();
    private Map<Long, UUID> longToUuidMap = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    public long getLongId(UUID uuid) {
        return uuidToLongMap.computeIfAbsent(uuid, k -> {
            long id = idCounter.getAndIncrement();
            longToUuidMap.put(id, k);
            return id;
        });
    }

    public UUID getUuid(long id) {
        return longToUuidMap.get(id);
    }
}
