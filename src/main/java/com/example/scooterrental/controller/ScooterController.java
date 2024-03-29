package com.example.scooterrental.controller;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.api.request.AddScooterRequest;
import com.example.scooterrental.api.response.AddScooterResponse;
import com.example.scooterrental.service.ScooterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("scooter")
public class ScooterController {

    private ScooterService scooterService;

    public ScooterController(ScooterService scooterService) {
        this.scooterService = scooterService;
    }

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public ResponseEntity<AddScooterResponse> addScooter(
            @RequestBody AddScooterRequest request
    ){
        return scooterService.addScooter(request);
    }

    @PutMapping(value = "/{scooterId}/undock", produces = "application/json")
    public ResponseEntity<BasicResponse> undockScooter(
            @PathVariable Long scooterId
    ) {
        return scooterService.undockScooter(scooterId);
    }
}

