package com.example.scooterrental.api.response;

import com.example.scooterrental.api.BasicResponse;

public class AddScooterResponse extends BasicResponse {
    private Long scooterId;

    public AddScooterResponse() {
    }

    public AddScooterResponse(String responseMsg, Long scooterId) {
        super(responseMsg);
        this.scooterId = scooterId;
    }

    public Long getScooterId() {
        return scooterId;
    }

    public void setScooterId(Long scooterId) {
        this.scooterId = scooterId;
    }
}
