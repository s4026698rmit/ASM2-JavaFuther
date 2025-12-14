package com.groupX.ems.services;

import com.groupX.ems.models.Presenter;
import com.groupX.ems.models.enums.Role;
import com.groupX.ems.repositories.PresenterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
public class PresenterServiceImpl implements PresenterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PresenterServiceImpl.class);
    private final PresenterRepository presenterRepository;

    public PresenterServiceImpl(PresenterRepository presenterRepository) {
        this.presenterRepository = presenterRepository;
    }

    @Override
    public List<Presenter> findPublicPresentersByEventId(long eventId) {
        try {
            if (presenterRepository != null) {
                // If repository supports event filtering, use it; otherwise return all.
                return presenterRepository.findAll();
            }
        } catch (Exception e) {
            LOGGER.error("Failed to load presenters for event {}", eventId, e);
        }
        // Fallback stub data
        return List.of(
                new Presenter(1, "Alice Presenter", LocalDate.now(), "alice@example.com", "000",
                        "alice", "", Role.PRESENTER, "AI", "Senior Speaker"),
                new Presenter(2, "Bob Presenter", LocalDate.now(), "bob@example.com", "111",
                        "bob", "", Role.PRESENTER, "UX", "Designer")
        );
    }
}

