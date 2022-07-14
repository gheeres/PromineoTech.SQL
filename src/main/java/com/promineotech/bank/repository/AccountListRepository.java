package com.promineotech.bank.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.promineotech.bank.model.AccountModel;

public class AccountListRepository implements AccountRepository {
  private static final List<AccountModel> defaultAccounts = List.of(
    new AccountModel("123456", "bob@bank.com").setBalance(1000F),
    new AccountModel("123456-002", "bob@bank.com").setBalance(301F),
    new AccountModel("123456-007", "bob@bank.com").setBalance(235F),
    new AccountModel("100001", "tom@bank.com").setBalance(100F),
    new AccountModel("100002", "april@bank.com").setBalance(500F),
    new AccountModel("100002-001", "april@bank.com").setBalance(9387F)
  );
  protected List<AccountModel> accounts;
  
  public AccountListRepository() {
    this(defaultAccounts);
  }
  public AccountListRepository(List<AccountModel> accounts) {
    if (accounts != null) {
      this.accounts = accounts.stream()
                              .collect(Collectors.toList());
    }
    else { 
      this.accounts = new ArrayList<>();
    }
  }
  
  @Override
  public AccountModel save(String number, String owner, Float balance) {
    // TODO
    return(null);
  }

  @Override
  public AccountModel remove(String number) {
    // TODO
    return(null);
  }

  //public AccountModel getBad(String number) {
  //  return null;
  //}
  
  @Override
  public Optional<AccountModel> get(String number) {
    //for(AccountModel a : this.accounts) {
    //  if (a.getNumber().equalsIgnoreCase(number)) {
    //    return a;
    //  }
    //}
    //return null;
    
    return this.accounts.stream()
        .filter(a -> a.getNumber().equalsIgnoreCase(number))
        .findFirst();
  }

  @Override
  public Stream<AccountModel> ownedBy(String owner) {
    //List<AccountModel> accounts = new ArrayList<>();
    //for(AccountModel account : this.accounts) {
    //  if (account.getOwner().equalsIgnoreCase(owner)) {
    //    accounts.add(account); 
    //  }
    //}
    //return accounts;

    return this.accounts.stream()
        .filter(account -> account.getOwner().equalsIgnoreCase(owner));
  }
  
  @Override
  public Stream<AccountModel> all() {
    return this.accounts.stream();
  }

  @Override
  public void close() {
  }
}
