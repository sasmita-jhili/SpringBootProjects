package com.app.cronjob;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.emailservice.EmailService;
import com.app.repo.UserRepo;

@Service
public class CronjobService {
	private static final Logger logger = Logger.getLogger(CronjobService.class);
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserRepo userRepo;

	public void cronJob() {
		List<String> list = userRepo.findAllEmail();
		if (!list.isEmpty()) {
			list.forEach(mail -> {
				emailService.sendEmail(mail, "messagge", "Hello Mam,Tomorrow your class time is 10.30pm ");

				logger.info("Message sent successfully : " + mail);
			});

		}

	}
}
