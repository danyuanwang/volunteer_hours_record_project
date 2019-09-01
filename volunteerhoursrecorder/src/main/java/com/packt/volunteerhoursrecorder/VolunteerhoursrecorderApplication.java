package com.packt.volunteerhoursrecorder;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.packt.volunteerhoursrecorder.domain.HourRepository;
import com.packt.volunteerhoursrecorder.domain.Hours;
import com.packt.volunteerhoursrecorder.domain.User;
import com.packt.volunteerhoursrecorder.domain.UserRepository;

@SpringBootApplication
public class VolunteerhoursrecorderApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HourRepository repository;
	

	public static void main(String[] args) {
		SpringApplication.run(VolunteerhoursrecorderApplication.class, args);
	}
	
	@Bean
	
	CommandLineRunner runner () {
		 
		
		return args -> {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			User user1 = new User(20,"admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "bob@mail.com", "ADMIN");
			User user2 = new User(37, "user", "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "hi@mail.com", "USER");
			userRepository.save(user1);
			userRepository.save(user2);

			// Save demo data to database
			repository.save(new Hours(20, "confirm@mail.com", formatter.parse("2019/08/24"), user1));
			repository.save(new Hours(9, "confirm@mail.com", formatter.parse("2019/08/24"), user2)); 
			repository.save(new Hours(28, "confirm@mail.com", formatter.parse("2019/08/24"), user2));

			
		};
	}

}
