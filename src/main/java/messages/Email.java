
/** 
 *  Email class
 *  Author: Tomasz Turek
 *	Description: Email class contains the implementation of functions governing 
 *	email construction and sending via SMTP server.
 *
 */


package messages;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Email class responsible for sending messages in Email form
 */

public class Email extends Messages {

	// credentials for Smtp gmail account
	private static final String username = "broadcastercs622@gmail.com";
	private static final String password = "MetCs@622";

	// address of the Stmp account
	private String senderEmailAddress = "broadcastercs622@gmail.com";

	// destination email
	private String recipientEmailAddress;

	// subject
	private String subject;

	// Email message constructor
	public Email(String recipientEmail, String subject, String userMessage) {
		super(userMessage);
		this.recipientEmailAddress = recipientEmail;
		this.subject = subject;

	}

	/**
	 * @return the recipientEmailAddress
	 */
	public String getRecipientEmailAddress() {
		return recipientEmailAddress;
	}

	/**
	 * @param recipientEmailAddress the recipientEmailAddress to set
	 */
	public void setRecipientEmailAddress(String recipientEmailAddress) {
		this.recipientEmailAddress = recipientEmailAddress;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Send Email message to its destination email address
	 * 
	 * @throws AddressException
	 * @throws MessagingException
	 * 
	 */

	@Override
	public void sendMessage() throws AddressException, MessagingException {

		System.out.println("SENDING EMAIL...");

		// stmp properties
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		// connection to stmp account
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// generate message to be sent
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(this.senderEmailAddress));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.recipientEmailAddress));
		message.setSubject(this.subject);
		message.setText(this.userMessage);

		// send message via stmp account
		Transport.send(message);

		System.out.println("Email sent");

	}

	/**
	 * Log message information
	 * 
	 */

	@Override
	public String toString() {
		return "Email [recipientEmailAddress=" + recipientEmailAddress + ", subject=" + subject + ", message="
				+ userMessage + "]";
	}

}