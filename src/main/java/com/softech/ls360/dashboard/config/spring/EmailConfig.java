package com.softech.ls360.dashboard.config.spring;

import java.io.File;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.softech.ls360.dashboard.util.SpringUtil;


@Configuration
@PropertySources({
	@PropertySource("classpath:email/email.properties")
})
public class EmailConfig {

	@Autowired
	private Environment env;
	
	@Bean(name="mailSender")
	public JavaMailSender mailSender() {
		
		 JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		 mailSender.setSession(getEmailSession());
		 return mailSender;
	 }
	
	 @Bean
	 public MailMessage mailSettings() {
		 SimpleMailMessage mailMessage = new SimpleMailMessage();
		 mailMessage.setFrom(env.getProperty("mail.from"));
		 mailMessage.setTo(env.getProperty("mail.to").split(","));
		 mailMessage.setCc(env.getProperty("mail.cc").split(","));
		 mailMessage.setBcc(env.getProperty("mail.bcc").split(","));
		 mailMessage.setSubject(env.getProperty("mail.subject"));
		 mailMessage.setText(env.getProperty("mail.body"));
		 return mailMessage;
	 }
	
	private Session getEmailSession() {
	    
		 Properties emailProperties = SpringUtil.loadPropertiesFileFromClassPath("email" + File.separator + "general-mail-settings.properties");
		    
		 final String userName = emailProperties.getProperty("360user");
		 final String password = emailProperties.getProperty("360Password");
		 
		Session session = null;
		try {
			
			session = Session.getInstance(emailProperties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);		
				}		
			});  //end of anonymous class  		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
		
	} //end of getEmailSession()
	
}
