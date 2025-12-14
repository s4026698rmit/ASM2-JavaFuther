package com.groupX.ems.view_controllers;

/**
 * @author 13
 */

import com.groupX.ems.MainApp;
import com.groupX.ems.models.Event;
import com.groupX.ems.models.Presenter;
import com.groupX.ems.models.enums.EventStatus;
import com.groupX.ems.models.enums.EventType;
import com.groupX.ems.models.enums.Role;
import com.groupX.ems.services.EventService;
import com.groupX.ems.services.PresenterService;
import com.groupX.ems.utils.LocalStorageUtil;
import com.groupX.ems.view_models.EventViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MainDashboardController {

    @FXML
    private Label currentRoleLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label selectedEventLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private ComboBox<String> eventTypeComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField locationTextField;
    @FXML
    private CheckBox onlyAvailableCheckBox;
    @FXML
    private TableView<EventViewModel> eventsTable;
    @FXML
    private TableColumn<EventViewModel, String> nameColumn;
    @FXML
    private TableColumn<EventViewModel, String> typeColumn;
    @FXML
    private TableColumn<EventViewModel, String> dateColumn;
    @FXML
    private TableColumn<EventViewModel, String> locationColumn;
    @FXML
    private TableColumn<EventViewModel, String> statusColumn;
    @FXML
    private TableColumn<EventViewModel, String> availabilityColumn;
    @FXML
    private ListView<String> presenterListView;

    private final ObservableList<EventViewModel> eventRows = FXCollections.observableArrayList();
    private final EventService eventService;
    private final PresenterService presenterService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MainDashboardController.class);

    public MainDashboardController() {
        this(null, null);
    }

    public MainDashboardController(EventService eventService,
                                   PresenterService presenterService) {
        this.eventService = eventService;
        this.presenterService = presenterService;
    }

    @FXML
    public void initialize() {
        initTableColumns();
        initFilters();
        bindSelection();
        refreshRoleLabel();
        loadEvents();
    }

    private void initTableColumns() {
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        typeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateRange()));
        locationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLocation()));
        statusColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));
        availabilityColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAvailabilityText()));
        eventsTable.setItems(eventRows);
    }

    private void initFilters() {
        eventTypeComboBox.getItems().clear();
        eventTypeComboBox.getItems().add("All types");
        for (EventType type : EventType.values()) {
            eventTypeComboBox.getItems().add(type.name());
        }
        eventTypeComboBox.getSelectionModel().selectFirst();
    }

    private void bindSelection() {
        eventsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedEventLabel.setText(newSel.getName());
                loadPresentersForEvent(newSel.getId());
            } else {
                selectedEventLabel.setText("None");
                presenterListView.getItems().clear();
            }
        });
    }

    private void refreshRoleLabel() {
        Role role = LocalStorageUtil.getCurrentRoleOrAnonymous();
        currentRoleLabel.setText("Role: " + role.name());
    }

    private void loadEvents() {
        try {
            eventRows.clear();
            List<Event> events = fetchEvents();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (Event e : events) {
                String dateRange = e.getStartDate() != null && e.getEndDate() != null
                        ? fmt.format(e.getStartDate()) + " - " + fmt.format(e.getEndDate())
                        : "";
                String availability = availabilityText(e.getStatus());
                eventRows.add(new EventViewModel(
                        e.getId(),
                        defaultString(e.getName()),
                        e.getType() != null ? e.getType().name() : "",
                        dateRange,
                        defaultString(e.getLocation()),
                        e.getStatus() != null ? e.getStatus().name() : "",
                        availability
                ));
            }
            statusLabel.setText("Loaded " + eventRows.size() + " events");
        } catch (Exception ex) {
            LOGGER.error("Failed to load events", ex);
            statusLabel.setText("Error loading events");
        }
    }

    private List<Event> fetchEvents() {
        if (eventService != null) {
            return eventService.getAllPublicEvents();
        }
        return List.of(
                new Event(1, "Tech Summit", EventType.CONFERENCE, "Tech trends", LocalDate.now().plusDays(7),
                        LocalDate.now().plusDays(8), "Hanoi", EventStatus.SCHEDULED, null),
                new Event(2, "Workshop UX", EventType.WORKSHOP, "UX best practices", LocalDate.now().plusDays(14),
                        LocalDate.now().plusDays(14), "HCMC", EventStatus.SCHEDULED, null)
        );
    }

    private void loadPresentersForEvent(long eventId) {
        presenterListView.getItems().clear();
        List<Presenter> presenters = fetchPresenters(eventId);
        List<String> items = presenters.stream()
                .map(p -> p.getFullName() + (p.getExpertiseArea() != null ? " — " + p.getExpertiseArea() : ""))
                .collect(Collectors.toList());
        presenterListView.getItems().addAll(items);
    }

    private List<Presenter> fetchPresenters(long eventId) {
        if (presenterService != null) {
            return presenterService.findPublicPresentersByEventId(eventId);
        }
        return List.of(
                new Presenter(1, "Alice Presenter", LocalDate.now(), "alice@example.com", "000",
                        "alice", "", Role.PRESENTER, "AI", "Senior Speaker"),
                new Presenter(2, "Bob Presenter", LocalDate.now(), "bob@example.com", "111",
                        "bob", "", Role.PRESENTER, "UX", "Designer")
        );
    }

    @FXML
    private void onLoginClicked() {
        MainApp.switchScene("/views/login.fxml");
    }

    @FXML
    private void onRegisterClicked() {
        MainApp.switchScene("/views/register.fxml");
    }

    @FXML
    private void onApplyFilters() {
        try {
            String eventType = eventTypeComboBox.getSelectionModel().getSelectedItem();
            LocalDate start = startDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();
            String location = locationTextField.getText();
            boolean onlyAvailable = onlyAvailableCheckBox.isSelected();

            List<Event> events = eventService != null
                    ? eventService.findPublicEvents(eventType, start, end, location, onlyAvailable)
                    : fetchEvents();
            mapEvents(events);
            statusLabel.setText("Found " + eventRows.size() + " events");
        } catch (Exception ex) {
            LOGGER.error("Failed to apply filters", ex);
            statusLabel.setText("Error applying filters");
        }
    }

    @FXML
    private void onClearFilters() {
        eventTypeComboBox.getSelectionModel().selectFirst();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        locationTextField.clear();
        onlyAvailableCheckBox.setSelected(false);
        loadEvents();
        statusLabel.setText("Filters cleared.");
    }

    private void mapEvents(List<Event> events) {
        eventRows.clear();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Event e : events) {
            String dateRange = e.getStartDate() != null && e.getEndDate() != null
                    ? fmt.format(e.getStartDate()) + " - " + fmt.format(e.getEndDate())
                    : "";
            eventRows.add(new EventViewModel(
                    e.getId(),
                    defaultString(e.getName()),
                    e.getType() != null ? e.getType().name() : "",
                    dateRange,
                    defaultString(e.getLocation()),
                    e.getStatus() != null ? e.getStatus().name() : "",
                    availabilityText(e.getStatus())
            ));
        }
    }

    private String availabilityText(EventStatus status) {
        if (status == null) return "Unknown";
        return switch (status) {
            case SCHEDULED, ONGOING -> "Open";
            case COMPLETED -> "Finished";
            case CANCELLED -> "Cancelled";
        };
    }

    private String defaultString(String value) {
        return value == null ? "" : value;
    }
}
