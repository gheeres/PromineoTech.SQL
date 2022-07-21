package com.promineotech.bank.service;

public interface NotificationSender {
  /**
   * Send a message notification.
   * @param message The message to send.
   */
  public void notify(String message);
}
