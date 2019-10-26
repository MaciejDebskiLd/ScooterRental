package com.example.scooterrental.service.impl;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.api.request.CreateUserAccountRequest;
import com.example.scooterrental.api.response.CreateUserAccountResponse;
import com.example.scooterrental.common.MsgSource;
import com.example.scooterrental.repository.UserAccountRepository;
import com.example.scooterrental.service.AbstractCommonService;
import com.example.scooterrental.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl extends AbstractCommonService implements UserAccountService {

    private UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(MsgSource msgSource, UserAccountRepository userAccountRepository) {
        super(msgSource);
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public ResponseEntity<CreateUserAccountResponse> createUserAccount(CreateUserAccountRequest request){
        return null;
    }

    @Override
    public ResponseEntity<BasicResponse> rechargeUserAccount(Long accountId, String amount){
        return null;
    }
}
