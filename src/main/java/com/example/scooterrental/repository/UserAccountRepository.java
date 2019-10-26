package com.example.scooterrental.repository;

import com.example.scooterrental.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    List<UserAccount> findByOwnerEmail(String ownerEmail);
}
