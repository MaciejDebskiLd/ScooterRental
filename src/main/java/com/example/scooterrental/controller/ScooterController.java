package com.example.scooterrental.controller;

import com.example.scooterrental.api.request.AddScooterRequest;
import com.example.scooterrental.api.response.AddScooterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("scooter")
public class ScooterController {

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<AddScooterResponse> addScooter(
            @RequestBody AddScooterRequest request
    ){
        return null;
    }
}

