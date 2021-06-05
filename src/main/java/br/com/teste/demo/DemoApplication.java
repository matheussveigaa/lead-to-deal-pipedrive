package br.com.teste.demo;

import br.com.teste.demo.infrastructure.background.jobs.PromoteLeadToDealJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private JobScheduler jobScheduler;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void scheduleRecurrently() {
		jobScheduler.<PromoteLeadToDealJob>scheduleRecurrently(x -> x.handle(), Cron.minutely());
	}
}
