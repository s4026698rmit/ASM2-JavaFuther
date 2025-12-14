package com.groupX.ems.services;

import com.groupX.ems.models.Event;
import com.groupX.ems.models.enums.EventStatus;
import com.groupX.ems.models.enums.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Override
    public Optional<Event> findById(long id) {
        return getAllPublicEvents().stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public List<Event> findAll() {
        return getAllPublicEvents();
    }

    @Override
    public List<Event> getAllPublicEvents() {
        // Stub data; replace with repository-backed implementation.
        return List.of(
                new Event(1, "Tech Summit", EventType.CONFERENCE, "Tech trends",
                        LocalDate.now().plusDays(7), LocalDate.now().plusDays(8),
                        "Hanoi", EventStatus.SCHEDULED, null),
                new Event(2, "Workshop UX", EventType.WORKSHOP, "UX best practices",
                        LocalDate.now().plusDays(14), LocalDate.now().plusDays(14),
                        "HCMC", EventStatus.SCHEDULED, null),
                new Event(3, "AI Expo", EventType.EXHIBITION, "AI Expo",
                        LocalDate.now().plusDays(21), LocalDate.now().plusDays(22),
                        "Danang", EventStatus.SCHEDULED, null)
        );
    }

    @Override
    public List<Event> findPublicEvents(String eventType,
                                        LocalDate startDate,
                                        LocalDate endDate,
                                        String location,
                                        boolean onlyAvailable) {
        try {
            return getAllPublicEvents().stream()
                    .filter(e -> filterType(e, eventType))
                    .filter(e -> filterDate(e, startDate, endDate))
                    .filter(e -> filterLocation(e, location))
                    .filter(e -> filterAvailability(e, onlyAvailable))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.error("Failed to filter events", ex);
            return List.of();
        }
    }

    @Override
    public Event save(Event event) {
        // Stub: return input
        return event;
    }

    @Override
    public void delete(long id) {
        // Stub no-op
    }

    private boolean filterType(Event e, String eventType) {
        if (eventType == null || eventType.isBlank() || "All types".equalsIgnoreCase(eventType)) {
            return true;
        }
        return e.getType() != null && e.getType().name().equalsIgnoreCase(eventType);
    }

    private boolean filterDate(Event e, LocalDate start, LocalDate end) {
        if (start == null && end == null) return true;
        LocalDate s = e.getStartDate();
        LocalDate f = e.getEndDate();
        if (s == null || f == null) return false;
        boolean afterStart = start == null || !f.isBefore(start);
        boolean beforeEnd = end == null || !s.isAfter(end);
        return afterStart && beforeEnd;
    }

    private boolean filterLocation(Event e, String location) {
        if (location == null || location.isBlank()) return true;
        return e.getLocation() != null && e.getLocation().toLowerCase().contains(location.toLowerCase());
    }

    private boolean filterAvailability(Event e, boolean onlyAvailable) {
        if (!onlyAvailable) return true;
        EventStatus status = e.getStatus();
        return status == EventStatus.SCHEDULED || status == EventStatus.ONGOING;
    }
}

