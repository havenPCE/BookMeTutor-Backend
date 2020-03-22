package com.pce.BookMeTutor.Controllers;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pce.BookMeTutor.Model.Dto.Requests.EmailRequest;
import com.pce.BookMeTutor.Model.Dto.Responses.EmailResponse;
import com.pce.BookMeTutor.Repo.UserRepo;
import com.pce.BookMeTutor.Services.EmailService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	EmailService emailService;

	@PostMapping("/email")
	@ResponseBody
	public EmailResponse sendEmail(@RequestBody EmailRequest emailRequest)
			throws MessagingException, IOException {

		emailService.sendMail(emailRequest.getTo(), emailRequest.getSubject(),
				emailRequest.getText());
		return new EmailResponse("Mail Sent to " + emailRequest.getTo());
	}

}
