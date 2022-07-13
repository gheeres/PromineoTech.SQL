package com.promineotech.bank;

import java.io.IOException;
import java.util.stream.Stream;
import com.promineotech.bank.model.AccountModel;
import com.promineotech.bank.repository.AccountCSVRepository;
import com.promineotech.bank.repository.AccountRepository;

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
    //return(new AccountListRepository());
    //return(new AccountJDBCRepository(connectionString, username, password));
    return(new AccountCSVRepository("./Data/Account.csv"));
    //return(new EmptyAccountRepository());
  }
  
  public void run(String[] args) {
    try (AccountRepository repository = GetRepository(args)) {
      // TODO
    } catch (IOException e) {
    }
  }
}
