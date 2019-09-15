package com.packt.volunteerhoursrecorder;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.packt.volunteerhoursrecorder.domain.HourRepository;
import com.packt.volunteerhoursrecorder.domain.Hours;
import com.packt.volunteerhoursrecorder.domain.Role;
import com.packt.volunteerhoursrecorder.domain.RoleName;
import com.packt.volunteerhoursrecorder.domain.RoleRepository;
import com.packt.volunteerhoursrecorder.domain.User;
import com.packt.volunteerhoursrecorder.domain.UserRepository;

@SpringBootApplication
@EntityScan(basePackageClasses = { 
		VolunteerhoursrecorderApplication.class,
		Jsr310JpaConverters.class 
})

public class VolunteerhoursrecorderApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HourRepository repository;
	
	@Autowired
	RoleRepository roleReposity;
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	public static void main(String[] args) {
		SpringApplication.run(VolunteerhoursrecorderApplication.class, args);
	}
	
	@Bean
	
	CommandLineRunner runner () {
		 
		
		return args -> {
			Role role1 = new Role(RoleName.ROLE_USER);
			Role role2 = new Role(RoleName.ROLE_ADMIN);
			Role role3 = new Role(RoleName.ROLE_TEACHER);
			roleReposity.save(role1);
			roleReposity.save(role2);
			roleReposity.save(role3);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			User user1 = new User("Danyuan Wang","admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "bob@mail.com");
			User user2 = new User("Lingyuan Yan", "user", "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "hi@mail.com");
			userRepository.save(user1);
			userRepository.save(user2);

			// Save demo data to database
			repository.save(new Hours(20, "confirm@mail.com", formatter.parse("2019/08/24"), user1));
			repository.save(new Hours(9, "confirm@mail.com", formatter.parse("2019/08/24"), user2)); 
			repository.save(new Hours(28, "confirm@mail.com", formatter.parse("2019/08/24"), user2));

			
		};
	}

}
