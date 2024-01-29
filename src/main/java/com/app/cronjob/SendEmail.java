package com.app.cronjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class SendEmail {
	@Autowired
	private CronjobService service;

	@Scheduled(cron = "0 * * ? * *")
	public void execute() {
		service.cronJob();
	}
}
