package com.example.scooterrental.service;

import com.example.scooterrental.api.request.AddScooterRequest;
import com.example.scooterrental.api.response.AddScooterResponse;
import org.springframework.http.ResponseEntity;

public interface ScooterService {

    ResponseEntity<AddScooterResponse> addScooter(AddScooterRequest request);
}
