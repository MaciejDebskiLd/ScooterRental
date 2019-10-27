package com.example.scooterrental.service.impl;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.common.MsgSource;
import com.example.scooterrental.exception.CommonConflictException;
import com.example.scooterrental.model.Scooter;
import com.example.scooterrental.model.ScooterDock;
import com.example.scooterrental.model.UserAccount;
import com.example.scooterrental.repository.ScooterDockRepository;
import com.example.scooterrental.repository.ScooterRepository;
import com.example.scooterrental.repository.UserAccountRepository;
import com.example.scooterrental.service.AbstractCommonService;
import com.example.scooterrental.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class RentalServiceImpl extends AbstractCommonService implements RentalService {

    private UserAccountRepository userAccountRepository;
    private ScooterRepository scooterRepository;
    private ScooterDockRepository scooterDockRepository;

    public RentalServiceImpl() {
        super();
    }

    public RentalServiceImpl(MsgSource msgSource, UserAccountRepository userAccountRepository, ScooterRepository scooterRepository) {
        super(msgSource);
        this.userAccountRepository = userAccountRepository;
        this.scooterRepository = scooterRepository;
    }

    public RentalServiceImpl(MsgSource msgSource, UserAccountRepository userAccountRepository, ScooterRepository scooterRepository, ScooterDockRepository scooterDockRepository) {
        super(msgSource);
        this.userAccountRepository = userAccountRepository;
        this.scooterRepository = scooterRepository;
        this.scooterDockRepository = scooterDockRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> rentScooter(Long scooterId, Long accountId){
        UserAccount userAccount = extractUserAccountFromRepository(accountId);
        Scooter scooter = extractScooterFromRepository(scooterId);
        checkScooterIsAvailableToRent(scooter);
        checkUserAccountAlreadyHasAnyRental(userAccount);
        checkUserAccountHasEnoughMoney(userAccount, scooter.getRentalPrice());
        finalizeScooterRental(userAccount, scooter);

        return ResponseEntity.ok(BasicResponse.of(msgSource.OK004));
    }

    private UserAccount extractUserAccountFromRepository(Long accountId){
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(accountId);
        if(!optionalUserAccount.isPresent()){
            throw new CommonConflictException(msgSource.ERR006);
        }
        return optionalUserAccount.get();
    }
    private Scooter extractScooterFromRepository(Long scooterId){
        Optional<Scooter> optionalScooter = scooterRepository.findById(scooterId);
        if(!optionalScooter.isPresent()){
            throw new CommonConflictException(msgSource.ERR010);
        }return optionalScooter.get();
    }
    private void checkScooterIsAvailableToRent(Scooter scooter){
        if(scooter.getScooterDock() == null || scooter.getUserAccount() != null){
            throw new CommonConflictException(msgSource.ERR011);
        }
    }

    private void checkUserAccountAlreadyHasAnyRental(UserAccount userAccount){
        if(userAccount.getScooter() != null){
            throw new CommonConflictException(msgSource.ERR012);
        }
    }
    private void checkUserAccountHasEnoughMoney(UserAccount userAccount, BigDecimal rentalPrice){
        if(userAccount.getBalance().compareTo(rentalPrice)<0){
            throw new CommonConflictException(msgSource.ERR013);
        }
    }
    private void finalizeScooterRental (UserAccount userAccount, Scooter scooter){
        userAccount.setBalance(userAccount.getBalance().subtract(scooter.getRentalPrice()));
        scooter.setScooterDock(null);
        scooter.setUserAccount(userAccount);
        scooterRepository.save(scooter);
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> returnScooter(Long scooterId, Long scooterDockId){
        Scooter scooter = extractScooterFromRepository(scooterId);
        checkScooterIsOccupied(scooter);
        ScooterDock scooterDock = extractScooterDockFromRepository(scooterDockId);
        checkForFreeSpaceInDock(scooterDock);
        UserAccount userAccount = extractUserAccountFromRepository(scooter.getUserAccount().getId());
        finalizeScooterReturn(userAccount, scooter, scooterDock);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK005));

    }

    private void checkScooterIsOccupied(Scooter scooter){
        if(scooter.getScooterDock() != null || scooter.getUserAccount() == null){
            throw new CommonConflictException(msgSource.ERR014);
        }
    }

    private ScooterDock extractScooterDockFromRepository(Long scooterDockId){
        Optional<ScooterDock> optionalScooterDock = scooterDockRepository.findById(scooterDockId);
        if (!optionalScooterDock.isPresent()){
            throw new CommonConflictException(msgSource.ERR008);
        } return optionalScooterDock.get();
    }

    private void checkForFreeSpaceInDock(ScooterDock scooterDock){
        if(scooterDock.getScooters().size()>=scooterDock.getAvailablePlace()){
            throw new CommonConflictException(msgSource.ERR009);
        }
    }

    private void finalizeScooterReturn(UserAccount userAccount, Scooter scooter, ScooterDock scooterDock){
        scooter.setUserAccount(null);
        scooter.setScooterDock(scooterDock);
//        userAccount.setScooter(null);
//        userAccountRepository.save(userAccount);
//        scooterDockRepository.save(scooterDock);
        scooterRepository.save(scooter);

    }
}
