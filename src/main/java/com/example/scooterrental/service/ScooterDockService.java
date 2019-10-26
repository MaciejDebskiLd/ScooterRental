package com.example.scooterrental.service;

import com.example.scooterrental.model.Scooter;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ScooterDockService {

    ResponseEntity<Set<Scooter>> getAllDockScooters(Long scooterDockId);
}
