package com.example.scooterrental.service.impl;

import com.example.scooterrental.common.MsgSource;
import com.example.scooterrental.exception.CommonConflictException;
import com.example.scooterrental.model.Scooter;
import com.example.scooterrental.model.ScooterDock;
import com.example.scooterrental.repository.ScooterDockRepository;
import com.example.scooterrental.service.AbstractCommonService;
import com.example.scooterrental.service.ScooterDockService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ScooterDockerServiceImpl extends AbstractCommonService implements ScooterDockService {

    private ScooterDockRepository scooterDockRepository;

    public ScooterDockerServiceImpl(MsgSource msgSource, ScooterDockRepository scooterDockRepository) {
        super(msgSource);
        this.scooterDockRepository = scooterDockRepository;
    }

    @Override
    public ResponseEntity<Set<Scooter>> getAllDockScooters(Long scooterDockId){
        Optional<ScooterDock> optionalScooterDock = scooterDockRepository.findById(scooterDockId);
        if(!optionalScooterDock.isPresent()){
            throw new CommonConflictException(msgSource.ERR008);
        }
        return ResponseEntity.ok(optionalScooterDock.get().getScooters());
    }
}
