package com.example.scooterrental.service;

import com.example.scooterrental.api.BasicResponse;
import org.springframework.http.ResponseEntity;

public interface RentalService {

    ResponseEntity<BasicResponse> rentScooter(Long scooterId, Long accountId);

}
