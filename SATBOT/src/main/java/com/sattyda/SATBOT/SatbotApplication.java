package com.sattyda.SATBOT;

import com.sattyda.SATBOT.entities.QnA;
import com.sattyda.SATBOT.repos.QRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SatbotApplication {

	@Autowired
	QRepo qRepo;

	public static void main(String[] args) {
		SpringApplication.run(SatbotApplication.class, args);
	}

	@Bean
	public void addData(){
//		qRepo.save(new QnA( null, "Campus Size" , "1200acres" , "Campus" ));
//		qRepo.save(new QnA( null, "Campus Color" , "Green" , "Campus" ));
//		qRepo.save(new QnA( null, "Number of Departments" , "10" , "Department" ));
//		qRepo.save(new QnA( null, "Male Female Ratio" , "50:50" , "Ratio" ));
	}

}
