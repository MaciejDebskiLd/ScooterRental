package com.example.scooterrental.api.response;

import com.example.scooterrental.api.BasicResponse;

import java.math.BigDecimal;

public class DisplayBalanceResponse extends BasicResponse {
    public DisplayBalanceResponse() {
    }

    public DisplayBalanceResponse(String responseMsg, BigDecimal balance) {
        super(responseMsg+ " " +balance);
    }
}
