package com.example.scooterrental.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ownerEmail;
    private String ownerUserName;
    private Integer ownerAge;
    private BigDecimal balance;
    private LocalDateTime createDate;
    @OneToOne (mappedBy = "userAccount")
    private Scooter scooter;

    public UserAccount() {
    }

    public UserAccount(Long id, String ownerEmail, String ownerUserName, Integer ownerAge, BigDecimal balance, LocalDateTime createDate) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.ownerUserName = ownerUserName;
        this.ownerAge = ownerAge;
        this.balance = balance;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public Integer getOwnerAge() {
        return ownerAge;
    }

    public void setOwnerAge(Integer ownerAge) {
        this.ownerAge = ownerAge;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }
}
