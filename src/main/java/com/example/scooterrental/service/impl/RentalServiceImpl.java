package com.example.scooterrental.service.impl;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.common.MsgSource;
import com.example.scooterrental.repository.ScooterRepository;
import com.example.scooterrental.repository.UserAccountRepository;
import com.example.scooterrental.service.AbstractCommonService;
import com.example.scooterrental.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceImpl extends AbstractCommonService implements RentalService {

    private UserAccountRepository userAccountRepository;
    private ScooterRepository scooterRepository;

    public RentalServiceImpl(MsgSource msgSource, UserAccountRepository userAccountRepository, ScooterRepository scooterRepository) {
        super(msgSource);
        this.userAccountRepository = userAccountRepository;
        this.scooterRepository = scooterRepository;
    }

    @Override
    public ResponseEntity<BasicResponse> rentScooter(Long scooterId, Long accountId){
        return null;
    }
}
