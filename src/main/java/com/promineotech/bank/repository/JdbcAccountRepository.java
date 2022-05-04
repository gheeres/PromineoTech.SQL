package com.promineotech.bank.repository;

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

public class JdbcAccountRepository implements AccountRepository {
  private Connection connection;
    
  public JdbcAccountRepository(String connectionString, String username, 
                               String password) {
    try {
      connection = DriverManager.getConnection(connectionString, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
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
    //Prepare SQL Statement
    final String sql = "INSERT INTO Bank (Account,Owner,Balance) VALUES (?,?,?);";
    PreparedStatement statement;
    try {
      statement = connection.prepareStatement(sql);
      //Add / bind parameters
      statement.setString(1,number);
      statement.setString(2,owner);
      statement.setDouble(3,balance);
      
      //Execute / Open Reader
      int count = statement.executeUpdate();
      if (count > 0) {
        Optional<AccountModel> account = get(number);
        if (account.isPresent()) {
          return(account.get());
        }
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return(null);
  }

  @Override
  public AccountModel remove(String number) {
    Optional<AccountModel> account = get(number);
    if (account.isPresent()) {
      //Prepare SQL Statement
      final String sql = "DELETE FROM Bank WHERE Account = ?;";
      PreparedStatement statement;
      try {
        statement = connection.prepareStatement(sql);
        //Add / bind parameters
        statement.setString(1,number);
        
        //Execute / Open Reader
        int count = statement.executeUpdate();
        if (count > 0) {
          return(account.get());
        }
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return(null);
  }

  @Override
  public Optional<AccountModel> get(String number) {
    //Prepare SQL Statement
    final String sql = "SELECT Account,Owner,Balance,TransactionDate FROM account WHERE Account = ?;";
    PreparedStatement statement;
    try {
      statement = connection.prepareStatement(sql);

      //Add / bind parameters
      statement.setString(1, number);
      
      //Execute / Open Reader
      ResultSet rs = statement.executeQuery();
      
      //Iterate (row by row)
      while(rs.next()) {
        AccountModel account = new AccountModel(rs.getString("Account"), 
                                                rs.getString("Owner"),
                                                rs.getFloat("Balance"),
                                                rs.getDate("TransactionDate"));
        return(Optional.of(account));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }  
    return(Optional.ofNullable(null));  
  }

  @Override
  public Stream<AccountModel> ownedBy(String owner) {
    //Prepare SQL Statement
    final String sql = "SELECT Account,Owner,Balance,TransactionDate FROM account WHERE Owner = ?;";
    PreparedStatement statement;
    try {
      statement = connection.prepareStatement(sql);

      //Add / bind parameters
      statement.setString(1, owner);
      
      //Execute / Open Reader
      ResultSet rs = statement.executeQuery();
      
      //Iterate (row by row)
      List<AccountModel> accounts = new ArrayList<AccountModel>();
       while(rs.next()) {
        AccountModel account = new AccountModel(rs.getString("Account"), 
                                                rs.getString("Owner"),
                                                rs.getFloat("Balance"),
                                                rs.getDate("TransactionDate"));
        accounts.add(account);
      }
      return(accounts.stream());
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }  
    return(Stream.empty());  
  }

  @Override
  public Stream<AccountModel> all() {
    //Prepare SQL Statement
    final String sql = "SELECT Account,Owner,Balance,TransactionDate FROM account ORDER BY Account;";
    PreparedStatement statement;
    try {
      statement = connection.prepareStatement(sql);

      //Add / bind parameters
      
      //Execute / Open Reader
      ResultSet rs = statement.executeQuery();
      
      //Iterate (row by row)
      List<AccountModel> accounts = new ArrayList<AccountModel>();
       while(rs.next()) {
        AccountModel account = new AccountModel(rs.getString("Account"), 
                                                rs.getString("Owner"),
                                                rs.getFloat("Balance"),
                                                rs.getDate("TransactionDate"));
        accounts.add(account);
      }
      return(accounts.stream());
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }  
    return(Stream.empty());  
  }
}
