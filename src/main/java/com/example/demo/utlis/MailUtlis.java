package com.example.demo.utlis;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



@Component
public class MailUtlis {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String toEmail, String subject, String message) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(toEmail);
		mail.setSubject(subject);
		mail.setText(message);
		
		javaMailSender.send(mail);
		
	}
	
//	public void sendEmailWithAttachment(String toEmail, String subject, String message, String path) {
//		MimeMessage msg = javaMailSender.createMimeMessage();
//		
//		MimeMessageHelper helper;
//		try {
//			helper = new MimeMessageHelper(msg, true);
//			
//			helper.setTo(toEmail);
//			helper.setSubject(subject);
//			
//			helper.setText("<h1>Check attachment for image!!</h1>", true);
//			helper.addAttachment("Hello", new File(path));
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			
//		}
//		javaMailSender.send(msg);
//	}
	
	public void SendEmail(String toEmail) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(toEmail);
		msg.setSubject("Your New Password!!");
//		msg.setText("Here is your new password : "+UUID.randomUUID().toString().substring(0,8));
		
//		String resetLink = "http://localhost/reset";
//		msg.setText("To reset password, Please click here : "+resetLink);
		
		javaMailSender.send(msg);
	}
}
