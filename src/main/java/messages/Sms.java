
/**
 * SMS class Author: Tomasz Turek Description: SMS class contains the implementation of functions
 * governing SMS construction and sending via Twillio API.
 *
 */

package messages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Sms extends Messages {

  // Twilio Api credentials
  private static String ACCOUNT_SID;
  private static String AUTH_TOKEN;

  // Api source phone number
  private String senderPhoneNumber = "+19179050846";

  // recipient phone number
  private String recipientPhoneNumber;

  // sms constructor
  public Sms(String userMessage) {
    super(userMessage);
    getCredentials();

    System.out.println(this.AUTH_TOKEN + " " + this.ACCOUNT_SID);

  }

  /**
   * Get credentials auth
   */
  public void getCredentials() {

    File file = new File("credentials.txt");

    Scanner credentials;

    try {
      credentials = new Scanner(file);


      String Id = (credentials.next()).substring(3);

      String Token = (credentials.next()).substring(6);

      this.ACCOUNT_SID = Id;

      this.AUTH_TOKEN = Token;

    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }

  };

  /**
   * @return the senderPhoneNumber
   */
  public String getSenderPhoneNumber() {
    return senderPhoneNumber;
  }

  /**
   * @param senderPhoneNumber the senderPhoneNumber to set
   */
  public void setSenderPhoneNumber(String senderPhoneNumber) {
    this.senderPhoneNumber = senderPhoneNumber;
  }

  /**
   * @return the recipientPhoneNumber
   */
  public String getRecipientPhoneNumber() {
    return recipientPhoneNumber;
  }

  /**
   * @param recipientPhoneNumber the recipientPhoneNumber to set
   */
  public void setRecipientPhoneNumber(String recipientPhoneNumber) {
    this.recipientPhoneNumber = recipientPhoneNumber;
  }

  /**
   * Log message information
   * 
   */

  @Override
  public String toString() {
    return "Sms [recipientPhoneNumber=" + recipientPhoneNumber + ", senderPhoneNumber="
        + senderPhoneNumber + ", message=" + userMessage + "]";
  }

  /**
   * Send SMS message to its destination phone number
   * 
   */

  @Override
  public void sendMessage() {

    // perform action async to prevent thread blocking.


    new Thread(() -> {
      try {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(this.recipientPhoneNumber), // to
            new PhoneNumber(this.senderPhoneNumber), // from
            this.userMessage).create();

        message.getSid();

        System.out.println("Message Delivered");

      } catch (com.twilio.exception.ApiException e) {
        System.out.println("Message Undeliverable");

      }
    }).start();


  }

}
