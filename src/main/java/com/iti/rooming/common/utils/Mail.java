package com.iti.rooming.common.utils;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.iti.rooming.common.config.PropertyManager;
import com.iti.rooming.common.exception.RoomingException;

public class Mail {
	public static final int ACTIVATION = 0;
	public static final int RESET_PASSWORD = 1;

	public static void sendMail(String email, String url, int type) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Properties props;
				try {
					props = getMailProps();
					Session session = createSession(props);
					sendMassage(session, props, email, url, type);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	private static void sendMassage(Session session, Properties props,
			String email, String url, int type) throws AddressException,
			MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("roomingiti@gmail.com"));

		if (type == ACTIVATION) {
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Roomingapp Activte Account");
			message.setText("Welcome to our RoomingApp, activation your account: "
					+ url);

		} else if (type == RESET_PASSWORD) {
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Roomingapp Reset Password");
			message.setText("your reset password url: " + url);

		}
		Transport.send(message);
	}

	private static Session createSession(final Properties props) {
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"roomingiti@gmail.com", "approoming=-09");
					}
				});
		return session;
	}

	private static Properties getMailProps() throws IOException,
			RoomingException {
		Properties props = PropertyManager.getProperty("mail");
		return props;
	}
}
