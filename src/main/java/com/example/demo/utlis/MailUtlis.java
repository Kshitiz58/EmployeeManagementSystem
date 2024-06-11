package com.example.demo.utlis;

//import java.util.UUID;

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
	
	public void SendEmail(String toEmail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("Password reset Link.");
//		message.setText("Here is your new password : "+UUID.randomUUID().toString().substring(0,3));
		String resetLink = "https://employeemanagementsystem.up.railway.app/resetpassword";
		message.setText("To reset your password, Please follow the link : "+resetLink+ 
				"\n\n\nRegards,\nEMS\nKathmandu, Nepal");
		
		
		javaMailSender.send(message);
	}
	

}
