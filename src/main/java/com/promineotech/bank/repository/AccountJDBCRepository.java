package com.promineotech.bank.repository;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

  /**
   * Retrieves and instance of AccountModel from the ResultSet
   * @param rs The result set / current row.
   * @return The instance.
   */
  private AccountModel toAccountModel(ResultSet rs) {
    try {
      return new AccountModel(rs.getString("Account"),
                              rs.getString("Owner"),
                              rs.getFloat("Balance"),
                              rs.getDate("TransactionDate"));
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
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
    final String sql = "SELECT Account,Owner,Balance,TransactionDate" 
                     + " FROM Account WHERE Account = ?";
    PreparedStatement statement;
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, number);

      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        AccountModel account = toAccountModel(rs);
        if (account != null) {
          return Optional.ofNullable(account);
        }
      }
    } catch (SQLException e) {
    }

    return Optional.empty();
  }

  @Override
  public Stream<AccountModel> ownedBy(String owner) {
    final String sql = "SELECT Account,Owner,Balance,TransactionDate" 
                     + " FROM Account WHERE Owner = ?";    
    PreparedStatement statement;
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, owner);

      ResultSet rs = statement.executeQuery();
      List<AccountModel> accounts = new ArrayList<>();
      while (rs.next()) {
        AccountModel account = toAccountModel(rs);
        if (account != null) {
          accounts.add(account);
        }
      }
      return accounts.stream();
    } catch (SQLException e) {
    }

    return Stream.empty();
  }

  @Override
  public Stream<AccountModel> all() {
    final String sql = "SELECT Account,Owner,Balance,TransactionDate"
                     + " FROM Account";
    PreparedStatement statement;
    try {
      statement = connection.prepareStatement(sql);
      
      ResultSet rs = statement.executeQuery();
      List<AccountModel> accounts = new ArrayList<>();
      while(rs.next()) {
        AccountModel account = toAccountModel(rs);
        if (account != null) {
          accounts.add(account);
        }
      }
      return accounts.stream();
    } catch(SQLException e) {
    }

    return Stream.empty();
  }
}
