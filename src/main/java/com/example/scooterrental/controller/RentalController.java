package com.example.scooterrental.controller;

import com.example.scooterrental.api.BasicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rental")
public class RentalController {

    @PutMapping(value = "/{scooterId}/scooter", produces = "application/json")
    public ResponseEntity<BasicResponse> createUserAccount(
            @PathVariable Long userId,
            @RequestParam Long accountId
    ){
        return null;
    }
}
