package com.example.scooterrental.service.impl;

import com.example.scooterrental.api.BasicResponse;
import com.example.scooterrental.api.request.CreateUserAccountRequest;
import com.example.scooterrental.api.response.CreateUserAccountResponse;
import com.example.scooterrental.api.response.DisplayBalanceResponse;
import com.example.scooterrental.api.response.DisplayRentedScooterResponse;
import com.example.scooterrental.common.MsgSource;
import com.example.scooterrental.exception.CommonBadRequestException;
import com.example.scooterrental.exception.CommonConflictException;
import com.example.scooterrental.model.UserAccount;
import com.example.scooterrental.repository.UserAccountRepository;
import com.example.scooterrental.service.AbstractCommonService;
import com.example.scooterrental.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.scooterrental.common.ValidationUtils.*;

@Service
public class UserAccountServiceImpl extends AbstractCommonService implements UserAccountService {

    private UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(MsgSource msgSource, UserAccountRepository userAccountRepository) {
        super(msgSource);
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<CreateUserAccountResponse> createUserAccount(CreateUserAccountRequest request) {
        validateCreateAccountRequest(request);
        checkOwnerEmailAlreadyExist(request.getOwnerEmail());
        UserAccount addedAccount = addUserAccountToDataSource(request);
        return ResponseEntity.ok(new CreateUserAccountResponse(msgSource.OK001, addedAccount.getId()));
    }

    private void validateCreateAccountRequest(CreateUserAccountRequest request) {
        if (isNullOrEmpty(request.getOwnerUsername())
                || isNullOrEmpty(request.getOwnerEmail())
                || isNull(request.getOwnerAge())) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
        if (isIncorrectEmail(request.getOwnerEmail())) {
            throw new CommonBadRequestException(msgSource.ERR002);
        }

        if (isIncorrectAge(request.getOwnerAge())) {
            throw new CommonBadRequestException(msgSource.ERR003);
        }
    }

    private void checkOwnerEmailAlreadyExist(String ownerEmail) {
        List<UserAccount> userAccounts = userAccountRepository.findByOwnerEmail(ownerEmail);
        if (!userAccounts.isEmpty()) {
            throw new CommonConflictException(msgSource.ERR004);
        }
    }

    private UserAccount addUserAccountToDataSource(CreateUserAccountRequest request) {
        UserAccount userAccount = new UserAccount(
                null,
                request.getOwnerEmail(),
                request.getOwnerUsername(),
                request.getOwnerAge(),
                new BigDecimal(0.0),
                LocalDateTime.now()
        );
        return userAccountRepository.save(userAccount);
    }


    @Override
    public ResponseEntity<BasicResponse> rechargeUserAccount(Long accountId, String amount) {
        BigDecimal rechargeAmount = extractAmountToBigDecimal(amount);
        addRechargeAmountToUserAccountBalance(accountId, rechargeAmount);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK002));

    }

    private BigDecimal extractAmountToBigDecimal(String amount) {
        try {
            return new BigDecimal(amount);
        } catch (NumberFormatException nfe) {
            throw new CommonConflictException(msgSource.ERR005);
        }
    }

    private void addRechargeAmountToUserAccountBalance(Long accountId, BigDecimal rechargeAmount) {
        Optional<UserAccount> userAccountData = userAccountRepository.findById(accountId);
        if (!userAccountData.isPresent()) {
            throw new CommonConflictException(msgSource.ERR006);
        }
        UserAccount accountData = userAccountData.get();
        accountData.setBalance(accountData.getBalance().add(rechargeAmount));
        userAccountRepository.save(accountData);
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> updateEmail(Long userId, String userEmail) {
        if (isIncorrectEmail(userEmail)) {
            throw new CommonBadRequestException(msgSource.ERR002);
        }
        UserAccount userAccount = extractUserAccountFromRepository(userId);
        userAccount.setOwnerEmail(userEmail);
        userAccountRepository.save(userAccount);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK009));
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> displayBalance(Long userId) {
        UserAccount userAccount = extractUserAccountFromRepository(userId);

        return ResponseEntity.ok(new DisplayBalanceResponse(msgSource.OK006, userAccount.getBalance()));
    }
    private UserAccount extractUserAccountFromRepository(Long accountId) {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(accountId);
        if (!optionalUserAccount.isPresent()) {
            throw new CommonConflictException(msgSource.ERR006);
        }
        return optionalUserAccount.get();
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> displayRentedScooter(String userEmail) {
        if (isIncorrectEmail(userEmail)) {
            throw new CommonBadRequestException(msgSource.ERR002);
        }
        UserAccount userAccount = extractUserAccountFromRepository(userEmail);
        if(userAccount.getScooter() == null){
            throw new CommonBadRequestException(msgSource.ERR014);
        }
        return ResponseEntity.ok(new DisplayRentedScooterResponse(msgSource.OK007, userAccount.getScooter()));
    }

    private UserAccount extractUserAccountFromRepository(String userEmail) {
        List<UserAccount> userAccounts = userAccountRepository.findByOwnerEmail(userEmail);
        if (userAccounts.isEmpty()) {
            throw new CommonConflictException(msgSource.ERR006);
        }
        return userAccounts.get(0);
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> removeAccount(String userEmail) {
        if (isIncorrectEmail(userEmail)) {
            throw new CommonBadRequestException(msgSource.ERR002);
        }
        UserAccount userAccount = extractUserAccountFromRepository(userEmail);
        checkUserAccountAlreadyHaveAnyRental(userAccount);
        userAccountRepository.delete(userAccount);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK008));
    }

    private void checkUserAccountAlreadyHaveAnyRental(UserAccount userAccount) {
        if (userAccount.getScooter() != null) {
            throw new CommonConflictException(msgSource.ERR015);
        }
    }

}
