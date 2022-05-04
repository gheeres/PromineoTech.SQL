package com.promineotech.bank.model;

import java.util.Date;
import com.promineotech.bank.exception.BankingException;

public class AccountModel {
  private String owner;
  private String number;
  private Float balance;
  private Date lastTransaction;
  
  public AccountModel(String number, String owner) {
    this(number, owner, 0F, null);
  }

  public AccountModel(String number, String owner, 
                      Date lastTransaction) {
    this(number, owner, 0F, lastTransaction);
  }

  public AccountModel(String number, String owner, 
                      Float balance, Date lastTransaction) {
    if (number == null) {
      throw new BankingException("Account number cannot be null");
    }
    this.number = number;
    this.lastTransaction = lastTransaction;
    this.balance = balance;
    setOwner(owner);
  }

  public String getOwner() {
    return owner;
  }

  public AccountModel setOwner(String owner) {
    if (owner == null) {
      throw new BankingException("Owner cannot be null");
    }
    this.owner = owner;
    return(this);
  }

  public String getNumber() {
    return number;
  }

  public Float getBalance() {
    return balance;
  }

  public AccountModel setBalance(Float balance) {
    if (this.balance != balance) {
      this.lastTransaction = new Date();
      this.balance = balance;
    }
    return(this);
  }

  public Date getLastTransaction() {
    return lastTransaction;
  }

  @Override
  public String toString() {
    return "AccountModel [owner=" + getOwner() + 
                       ", number=" + getNumber() + 
                       ", balance=" + getBalance() + 
                       ", lastTransaction=" + getLastTransaction() + 
                        "]";
  }
}
