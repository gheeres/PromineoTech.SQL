package com.promineotech.bank;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;
import com.promineotech.bank.model.AccountModel;
import com.promineotech.bank.repository.AccountJDBCRepository;
import com.promineotech.bank.repository.AccountRepository;
import com.promineotech.bank.service.AccountService;
import com.promineotech.bank.service.DefaultAccountService;

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
    return(new AccountJDBCRepository(connectionString, username, password));
    //return(new AccountCSVRepository("./Data/Account.csv"));
    //return(new EmptyAccountRepository());
  }
  
  //void anonymousOutput(AccountModel account) {
  //  System.out.println(account);
  //}
  
  public void run(String[] args) {
    Scanner input = new Scanner(System.in);
    try (AccountRepository repository = GetRepository(args)) {
      AccountService service = new DefaultAccountService(repository);
      //service.open
      
      
      Stream<AccountModel> accounts = repository.all();
      //Stream<AccountModel> accounts = repository.ownedBy("bob@bank.com");
      //for (AccountModel account : accounts.toList()) {
      //  System.out.println(account);
      //}
      accounts.forEach(account -> {
        System.out.println(account); 
      });
      
      //AccountModel existing = ((AccountListRepository) repository)
      //    .getBad("");
      //if (existing != null) {
      //  System.out.println("Account: " + existing.toString());
      //}
      
      System.out.println("Enter the account information to load:");
      String number = input.nextLine();
      Optional<AccountModel> result = repository.get(number);
      //if (result.isPresent()) {
      //  System.out.println(result.get().toString());
      //}
      //else {
      //  System.out.println("Requested account not found.");
      //}
      result.ifPresentOrElse(a -> System.out.println(a.toString()), 
        () -> System.out.println("Requested account not found.")
      );
      
    } catch (IOException e) {
    }
  }
}
