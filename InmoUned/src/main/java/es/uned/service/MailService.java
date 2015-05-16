package es.uned.service;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.mail.MailSender;

//import org.springframework.mail.SimpleMailMessage;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.mail.MailSender;

import org.springframework.mail.SimpleMailMessage;

  
  

/**
 * MailSender Service
 * 
 * 
 * @author José Antonio Pérez Reyes
 *
 */
//@Service("mailService")
@Transactional(readOnly = true)
public class MailService {

	//@Autowired
	MailSender mailSender;

	@Transactional(readOnly = false)
	public void sendMail(String from, String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);

		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);

	}
/*
	public void sendAlertMail(String alert) {

		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);

		mailMessage.setText(alert);

		mailSender.send(mailMessage);

	}*/


	public MailSender getMailSender() {
		return mailSender;
	}


	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	

}
