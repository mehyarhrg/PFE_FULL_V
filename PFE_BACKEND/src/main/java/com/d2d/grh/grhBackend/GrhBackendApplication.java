package com.d2d.grh.grhBackend;

import com.d2d.grh.grhBackend.entity.*;
import com.d2d.grh.grhBackend.models.UserRoleObject;
import com.d2d.grh.grhBackend.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class GrhBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrhBackendApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(OfferStatusService offerStatusService, DepartmentService departmentService, RoleService roleService, OfferCategoryService offerCategoryService, UserService userService, EmailService emailService, StatusService statusService){
		return args -> {
//			emailService.sendMail("secrefduetodata@gmail.com", "test subject", "test body");
//			offerCategoryService.deleteCategory(14);
//			offerCategoryService.deleteCategory(15);


//			roleService.addRole(new Role(null, "RH_MANAGER"));
//			roleService.addRole(new Role(null, "RH"));
//			roleService.addRole(new Role(null, "REFERENT"));
//			departmentService.saveDepartment(new Department(null, "Department RH",new ArrayList<>()));
//			departmentService.saveDepartment(new Department(null, "Department Marketing",new ArrayList<>()));
//			departmentService.saveDepartment(new Department(null, "Department Technique",new ArrayList<>()));
////
//			offerCategoryService.saveCategory(new OfferCategory(null, "Job Offer", new ArrayList<>()));
//			offerCategoryService.saveCategory(new OfferCategory(null, "Internship Offer", new ArrayList<>()));
////
//			statusService.saveStatus(new Status(null, "TODO", "#dc033e", new ArrayList<>()));
//			statusService.saveStatus(new Status(null, "DOING", "#e3ec5b", new ArrayList<>()));
//			statusService.saveStatus(new Status(null, "DONE", "#1cdc00", new ArrayList<>()));
//			statusService.saveStatus(new Status(null, "CLOSED", "#6b7781", new ArrayList<>()));
//			statusService.saveStatus(new Status(null, "NEW", "#e87d9a", new ArrayList<>()));
//			statusService.saveStatus(new Status(null, "INTERVIEW", "#7db8e8", new ArrayList<>()));
//			statusService.saveStatus(new Status(null, "REFUSED", "#dc033e", new ArrayList<>()));
//			statusService.saveStatus(new Status(null, "ACCEPTED", "#1cdc00", new ArrayList<>()));
//			statusService.saveStatus(new Status(null, "CONFIRMED", "#e3ec5b", new ArrayList<>()));
//			offerStatusService.saveStatus(new OfferStatus(null, "DRAFT", "#dccc21", new ArrayList<>()));
//			offerStatusService.saveStatus(new OfferStatus(null, "PUBLISHED", "#21dc56", new ArrayList<>()));
//			offerStatusService.saveStatus(new OfferStatus(null, "ARCHIVED", "#c7621c", new ArrayList<>()));
//			offerStatusService.saveStatus(new OfferStatus(null, "CLOSED", "#CLOSED",  new ArrayList<>()));
//
//////////
//			userService.addNewUser(new User(
//					null,
//					"mihyar",
//					"horrigue",
//					"mihyar270@gmail",
//					"mihyaar",
//					"1234",
//					"sayada",
//					"monastir",
//					"tunisia",
//					5035,
//					"about me",
//					"Department RH",
//					new Date(),
//					new Department(4L, "Department RH", new ArrayList<>()),
//					new ArrayList<>(),
//					new ArrayList<>(),
//					new ArrayList<>(),
//					new ArrayList<>()
//					));
			roleService.addRoleToUser(new UserRoleObject("mihyaar", "RH_MANAGER"));

		};
	}

	@Bean
	public CorsFilter corsFilter(){
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(false);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4201"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept",
				"Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origine", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
