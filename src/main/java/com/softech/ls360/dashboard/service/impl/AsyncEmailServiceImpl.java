package com.softech.ls360.dashboard.service.impl;

import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.softech.ls360.dashboard.service.AsyncEmailService;

@Service
public class AsyncEmailServiceImpl implements AsyncEmailService {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private JavaMailSender mailSender;
	
	@Async
	@Override
	public Future<String> sendEmail(SimpleMailMessage mailMessage) {
		
		logger.info("Start execution of async. sending email");
		
		MimeMessage message = mailSender.createMimeMessage();
	 
		try{
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			 
			helper.setFrom(mailMessage.getFrom());
			helper.setTo(mailMessage.getTo());
			helper.setCc(mailMessage.getCc());
			helper.setSubject(mailMessage.getSubject());
			helper.setText(mailMessage.getText(), true);
	 
			mailSender.send(message);
			
		} catch (MessagingException e) {
			logger.error("Exception occurs in sending email: ", e);
			throw new MailParseException(e);
		}
		     
		 logger.info("Complete execution of async. Email send successfully");
		
		 // Wrap the result in an AsyncResult
	    return new AsyncResult<String>("Email send successfully");
	}


	@Async
	@Override
	public Future<String> sendEmailWithFileAttachment(SimpleMailMessage mailMessage, String attachFile) {
		
		logger.info("Start execution of async. Sending email with file attachment");
		
		MimeMessage message = mailSender.createMimeMessage();
	 
		try{
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			 
			helper.setFrom(mailMessage.getFrom());
			helper.setTo(mailMessage.getTo());
			helper.setCc(mailMessage.getCc());
			helper.setSubject(mailMessage.getSubject());
			helper.setText(mailMessage.getText());
	 
			FileSystemResource file = new FileSystemResource(attachFile);
			helper.addAttachment(file.getFilename(), file);
			
			mailSender.send(message);
			
		} catch (MessagingException e) {
			logger.error("Exception occurs in sending email with file attachment: " + attachFile, e);
			throw new MailParseException(e);
		}
		     
		logger.info("Complete execution of async. Email with file attachment " + attachFile + " send successfully.");
	
		// Wrap the result in an AsyncResult
        return new AsyncResult<String>("Attachment File successfully send: " + attachFile);
	}

}
