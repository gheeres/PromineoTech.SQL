package com.promineotech.bank.service;

import com.promineotech.bank.model.AccountModel;

public interface AccountService {
  /**
   * Opens or creates a new account.
   * @param owner The unique id of the owner of the account.
   * @param initialDeposit initial deposit / amount for the account.
   * @return The created account information.
   */
  public AccountModel open(String owner, Float initialDeposit);
  
  /**
   * Closes or removes an account.
   * @param number The unique account identifier.
   * @return The closed account information.
   */
  public AccountModel close(String number);
  
  /**
   * Retrieves existing account information.
   * @param number The unique account identifier.
   * @return The account if found, otherwise returns null.
   */
  public AccountModel getBalance(String number);
  
  /**
   * Deposits the specified amount into the account.
   * @param number The unique account identifier.
   * @param amount The amount to deposit.
   * @return The updated account information.
   */
  public AccountModel deposit(String number, Float amount);
  
  /**
   * Withdraws the specified amount from the account.
   * @param number The unique account identifier.
   * @param amount The amount to withdraw.
   * @return The updated account information.
   */
  public AccountModel withdraw(String number, Float amount);
}
