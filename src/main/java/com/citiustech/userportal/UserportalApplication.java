package com.citiustech.userportal;

import com.citiustech.userportal.entity.AppUser;
import com.citiustech.userportal.entity.AppUserRole;
import com.citiustech.userportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class UserportalApplication implements CommandLineRunner {
	final UserService userService;

	public static void main(String[] args) {
        System.out.println("started working on 12 am");
		System.out.println("pause working on 1:30 pm");
		System.out.println("resume working on 3:10 PM");
		System.out.println("stop working on 4:10 PM");
		System.out.println("resume working on 6:00 pm");
		System.out.println("stop working on 6:30 pm");
		System.out.println("start working on 12:10 pm");
		System.out.println("pause working on 12:30 pm");
		System.out.println("start working on 11:50 am" );
		SpringApplication.run(UserportalApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... params) throws Exception {
		AppUser admin = new AppUser();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@email.com");
		admin.setCompanyName("Citius Tech");
		admin.setAppUserRoles(new ArrayList<>(Arrays.asList(AppUserRole.ROLE_ADMIN)));

		userService.signup(admin);

		AppUser client = new AppUser();
		client.setUsername("client");
		client.setPassword("client");
		client.setEmail("client@email.com");
		client.setCompanyName("Citius Tech");
		client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_CLIENT)));

		userService.signup(client);
	}
}