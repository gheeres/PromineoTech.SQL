package com.promineotech.bank;

import java.io.IOException;
import java.util.stream.Stream;
import com.promineotech.bank.model.AccountModel;
import com.promineotech.bank.repository.AccountRepository;
import com.promineotech.bank.repository.JdbcAccountRepository;

public class Application {
  private final String connectionString = "jdbc:mysql://localhost:3306/bank?allowMultiQueries=true";
  private final String username = "root";
  private final String password = "password";

  public static void main(String[] args) {
    System.out.println("Start");
    new Application().run(args);
    System.out.println("End");
  }

  public AccountRepository GetRepository(String[] args) {
    //return(new StaticAccountRepository());
    return(new JdbcAccountRepository(connectionString, username, password));
    ////String filename = "/home/gheeres/Projects/java/SQL.demo/Account.csv";
    //String filename = "C:\\Users\\micro\\eclipse-workspace\\Banking-Master\\Account.csv";
    //System.out.println("Don't forget to set the full path for the Account.csv file. Current: " + filename);
    //return(new CSVAccountRepository(filename));
  }
  
  public void run(String[] args) {
    try (AccountRepository repository = GetRepository(args)) {
      Stream<AccountModel> accounts = repository.all();
      accounts = accounts.sorted((a1,a2) -> a1.getBalance().compareTo(a2.getBalance()));
      accounts.forEach((account) -> {
        System.out.println(account);
      });
    } catch (IOException e) {
    }
  }
}
