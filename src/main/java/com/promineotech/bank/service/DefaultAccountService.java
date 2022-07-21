package com.promineotech.bank.service;

import java.util.Optional;
import com.promineotech.bank.model.AccountModel;
import com.promineotech.bank.repository.AccountRepository;

public class DefaultAccountService implements AccountService {
  private AccountRepository repository;
  private NotificationSender sender;
  
  public DefaultAccountService(AccountRepository repository,
                               NotificationSender notification) {
    this.repository = repository;
    this.sender = notification;
  }
  
  @Override
  public AccountModel open(String owner, Float initialDeposit) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AccountModel close(String number) {
    // When an account is closed with a balance 
    // greater than 0, then the account close 
    // fails so that no money is lost. 
    Optional<AccountModel> account = repository.get(number);
    if (account.isPresent()) {
      if (account.get().getBalance() > 0) {
        System.out.println("Account cannot be closed with balance "
            + "greater than 0");
      }
      else {
        //repository.save
        return(account.get());
      }
    }
    System.out.println("Account not found.");
    return null;
  }

  @Override
  public AccountModel getBalance(String number) {
    // TODO Auto-generated method stub
    //repository = new AccountCSVRepository();
    return null;
  }

  @Override
  public AccountModel deposit(String number, Float amount) {
    Optional<AccountModel> account = repository.get(number);
    if (account.isPresent()) {
      // When a deposit of more than $10000 is made 
      // then a notification should be sent so that federal 
      // law is being complied with.
      if (amount >= 10000) {
        sender.notify(account.get().getOwner() +
                      " deposited $" + amount + " into account " + 
                      account.get().getNumber());
      }
      
      Float existingBalance = account.get().getBalance();
      account.get().setBalance(existingBalance + amount);
      repository.save(account.get().getNumber(), 
                      account.get().getOwner(),
                      account.get().getBalance());

    }
    System.out.println("Account not found.");
    return null;
  }

  @Override
  public AccountModel withdraw(String number, Float amount) {
    // TODO Auto-generated method stub
    return null;
  }

}
