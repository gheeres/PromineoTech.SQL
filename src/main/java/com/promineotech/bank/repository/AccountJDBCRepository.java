package com.promineotech.bank.repository;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;
import com.promineotech.bank.model.AccountModel;

public class AccountJDBCRepository implements AccountRepository, Closeable {
  private Connection connection;
  
  public AccountJDBCRepository(String connectionString, String username, 
                               String password) {
    try {
      connection = DriverManager.getConnection(connectionString, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void close() throws IOException {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public AccountModel save(String number, String owner, Float balance) {
    //TODO
    return null;
  }

  @Override
  public AccountModel remove(String number) {
    //TODO
    return null;
  }

  @Override
  public Optional<AccountModel> get(String number) {
    //TODO
    return Optional.empty();
  }

  @Override
  public Stream<AccountModel> ownedBy(String owner) {
    //TODO
    return Stream.empty();
  }

  @Override
  public Stream<AccountModel> all() {
    //TODO
    return Stream.empty();
  }
}
