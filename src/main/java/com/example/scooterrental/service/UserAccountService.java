package com.example.scooterrental.service;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.api.request.CreateUserAccountRequest;
import com.example.scooterrental.api.response.CreateUserAccountResponse;
import org.springframework.http.ResponseEntity;

public interface UserAccountService {

    ResponseEntity<CreateUserAccountResponse> createUserAccount(CreateUserAccountRequest request);
    ResponseEntity<BasicResponse> rechargeUserAccount(Long accountId, String amount);
    ResponseEntity<BasicResponse> updateEmail(Long userId, String userEmail);
    ResponseEntity<BasicResponse> removeAccount(String userEmail);
    ResponseEntity<BasicResponse> displayRentedScooter(String userEmail);
    ResponseEntity<BasicResponse> displayBalance(Long userId);
}
