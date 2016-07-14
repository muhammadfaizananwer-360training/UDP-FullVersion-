package com.softech.ls360.dashboard.service;

import java.util.concurrent.Future;

import org.springframework.mail.SimpleMailMessage;

public interface AsyncEmailService {

	Future<String> sendEmailWithFileAttachment(SimpleMailMessage mailMessage, String file);
	Future<String> sendEmail(SimpleMailMessage mailMessage);
}
