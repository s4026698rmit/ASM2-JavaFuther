package com.groupX.ems.view_models;

import com.groupX.ems.models.enums.TicketStatus;
import com.groupX.ems.models.enums.TicketType;

import java.math.BigDecimal;

public class TicketViewModel {
    private long id;
    private String ticketCode;
    private TicketType type;
    private TicketStatus status;
    private BigDecimal price;

    public TicketViewModel() {
    }

    public TicketViewModel(long id, String ticketCode, TicketType type, TicketStatus status, BigDecimal price) {
        this.id = id;
        this.ticketCode = ticketCode;
        this.type = type;
        this.status = status;
        this.price = price;
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

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

