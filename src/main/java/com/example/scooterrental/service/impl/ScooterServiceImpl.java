package com.example.scooterrental.service.impl;

import com.example.scooterrental.api.request.AddScooterRequest;
import com.example.scooterrental.api.response.AddScooterResponse;
import com.example.scooterrental.common.MsgSource;
import com.example.scooterrental.repository.ScooterDockRepository;
import com.example.scooterrental.repository.ScooterRepository;
import com.example.scooterrental.service.AbstractCommonService;
import com.example.scooterrental.service.ScooterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScooterServiceImpl extends AbstractCommonService implements ScooterService {

    private ScooterRepository scooterRepository;
    private ScooterDockRepository scooterDockRepository;

    public ScooterServiceImpl(MsgSource msgSource, ScooterRepository scooterRepository, ScooterDockRepository scooterDockRepository) {
        super(msgSource);
        this.scooterRepository = scooterRepository;
        this.scooterDockRepository = scooterDockRepository;
    }

    @Override
    public ResponseEntity<AddScooterResponse> addScooter(AddScooterRequest request){
        return null;
    }
}
