package com.example.scooterrental.controller;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rental")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @PutMapping(value = "/{scooterId}/scooter", produces = "application/json")
    public ResponseEntity<BasicResponse> rentScooter(
            @PathVariable Long scooterId,
            @RequestParam Long accountId
    ) {
        return rentalService.rentScooter(scooterId, accountId);
    }

    @PutMapping(value = "/{scooterId}/return", produces = "application/json")
    public ResponseEntity<BasicResponse> returnScooter(@PathVariable Long scooterId, @RequestParam Long scooterDockId) {
        return rentalService.returnScooter(scooterId, scooterDockId);
    }
}
