package com.example.scooterrental.api.response;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.model.Scooter;

public class DisplayRentedScooterResponse extends BasicResponse {
    public DisplayRentedScooterResponse() {
    }

    public DisplayRentedScooterResponse(String responseMsg, Scooter scooter) {
        super(responseMsg + " " + scooter.toString());
    }

}
