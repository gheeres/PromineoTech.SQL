package com.promineotech.bank.repository;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;
import com.promineotech.bank.model.AccountModel;

public class EmptyAccountRepository implements AccountRepository {
  public void close() throws IOException {
  }

  public AccountModel save(String number, String owner, Float balance) {
    return null;
  }

  public AccountModel remove(String number) {
    return null;
  }

  public Optional<AccountModel> get(String number) {
    return Optional.empty();
  }

  public Stream<AccountModel> ownedBy(String owner) {
    return Stream.empty();
  }

  public Stream<AccountModel> all() {
    return Stream.empty();
  }
}
