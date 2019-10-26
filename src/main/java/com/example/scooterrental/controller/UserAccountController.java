package com.example.scooterrental.controller;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.api.request.CreateUserAccountRequest;
import com.example.scooterrental.api.response.CreateUserAccountResponse;
import com.example.scooterrental.service.impl.UserAccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-account")
public class UserAccountController {

    public UserAccountServiceImpl userAccountServiceImpl;

    public UserAccountController(UserAccountServiceImpl userAccountServiceImpl) {
        this.userAccountServiceImpl = userAccountServiceImpl;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<CreateUserAccountResponse> createUserAccount(
            @RequestBody CreateUserAccountRequest request
    ){
        return userAccountServiceImpl.createUserAccount(request);
    }

    @PutMapping(value = "/{accountId}/recharge", produces = "application/json")
    public ResponseEntity<BasicResponse> rechargeUserAccount(
            @PathVariable Long accountId,
            @RequestParam String amount
    ){
        return userAccountServiceImpl.rechargeUserAccount(accountId, amount);
    }
}
