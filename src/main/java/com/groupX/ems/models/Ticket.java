package com.groupX.ems.models;

import com.groupX.ems.models.enums.TicketStatus;
import com.groupX.ems.models.enums.TicketType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ticket {
    private long id;
    private String ticketCode;
    private TicketType type;
    private BigDecimal price;
    private TicketStatus status;
    private long attendeeId;
    private long eventId;
    private long sessionId;
    private String qrCodeData;
    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;

    public Ticket() {
    }

    public Ticket(long id, String ticketCode, TicketType type, BigDecimal price, TicketStatus status,
                  long attendeeId, long eventId, long sessionId, String qrCodeData,
                  LocalDateTime issuedAt, LocalDateTime usedAt) {
        this.id = id;
        this.ticketCode = ticketCode;
        this.type = type;
        this.price = price;
        this.status = status;
        this.attendeeId = attendeeId;
        this.eventId = eventId;
        this.sessionId = sessionId;
        this.qrCodeData = qrCodeData;
        this.issuedAt = issuedAt;
        this.usedAt = usedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public long getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(long attendeeId) {
        this.attendeeId = attendeeId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getQrCodeData() {
        return qrCodeData;
    }

    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(LocalDateTime usedAt) {
        this.usedAt = usedAt;
    }
}

