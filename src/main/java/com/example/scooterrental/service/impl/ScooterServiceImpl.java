package com.example.scooterrental.service.impl;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.api.request.AddScooterRequest;
import com.example.scooterrental.api.response.AddScooterResponse;
import com.example.scooterrental.common.MsgSource;
import com.example.scooterrental.exception.CommonBadRequestException;
import com.example.scooterrental.exception.CommonConflictException;
import com.example.scooterrental.model.Scooter;
import com.example.scooterrental.model.ScooterDock;
import com.example.scooterrental.repository.ScooterDockRepository;
import com.example.scooterrental.repository.ScooterRepository;
import com.example.scooterrental.service.AbstractCommonService;
import com.example.scooterrental.service.ScooterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.scooterrental.common.ValidationUtils.*;

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
    @Transactional
    public ResponseEntity<AddScooterResponse> addScooter(AddScooterRequest request) {
        validateAddScooterRequest(request);
        ScooterDock scooterDock = extractDockFromRepository(request.getScooterDockId());
        checkIsAvailablePlaceInDock(scooterDock);
        Scooter addedScooter = addScooterToDataSource(request, scooterDock);
        return ResponseEntity.ok(new AddScooterResponse(msgSource.OK003, addedScooter.getId()));
    }

    private void validateAddScooterRequest(AddScooterRequest request) {
        if (isNullOrEmpty(request.getModelName())
                || isNull(request.getRentalPrice())
                || isNull(request.getMaxSpeed())
                || isNull(request.getScooterDockId())) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
        if (isIncorrectMaxSpeed(request.getMaxSpeed())){
            throw new CommonBadRequestException(msgSource.ERR007);
        }
    }

    private ScooterDock extractDockFromRepository(Long scooterDockId){
        Optional<ScooterDock> optionalScooterDock = scooterDockRepository.findById(scooterDockId);
        if(!optionalScooterDock.isPresent()){
            throw new CommonConflictException(msgSource.ERR008);
        }
        return optionalScooterDock.get();
    }

    private void checkIsAvailablePlaceInDock(ScooterDock scooterDock){
        if(scooterDock.getAvailablePlace()<= scooterDock.getScooters().size()){
            throw new CommonConflictException(msgSource.ERR009);
        }
    }

    private Scooter addScooterToDataSource(AddScooterRequest request, ScooterDock scooterDock){
        Scooter scooter = new Scooter();
        scooter.setModelName(request.getModelName());
        scooter.setMaxSpeed(request.getMaxSpeed());
        scooter.setRentalPrice(new BigDecimal(request.getRentalPrice()));
        scooter.setScooterDock(scooterDock);

        return scooterRepository.save(scooter);
    }
    @Override
    @Transactional
    public ResponseEntity<BasicResponse> undockScooter(Long scooterId) {
        Scooter scooter = extractScooterFromRepository(scooterId);
        checkScooterIsRented(scooter);
        scooter.setScooterDock(null);
        scooterRepository.save(scooter);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK010));
    }
    private Scooter extractScooterFromRepository(Long scooterId) {
        Optional<Scooter> optionalScooter = scooterRepository.findById(scooterId);
        if (!optionalScooter.isPresent()) {
            throw new CommonConflictException(msgSource.ERR010);
        }
        return optionalScooter.get();
    }

    private void checkScooterIsRented(Scooter scooter) {
        if (scooter.getUserAccount() != null) {
            throw new CommonConflictException(msgSource.ERR015);
        }
    }
}
