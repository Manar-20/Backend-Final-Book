package com.sda.finalProject;

import com.sda.finalProject.entity.Role;
import com.sda.finalProject.entity.User;
import com.sda.finalProject.service.impl.RoleService;
import com.sda.finalProject.service.impl.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
			}
		};
	}

	@Bean
	public CommandLineRunner dataInitializer(RoleService roleService, UserService userService) {
		return args -> {
			initializeRoles(roleService);
			initializeAdmin(userService);
		};
	}

	private void initializeRoles(RoleService roleService) {
		// Check if roles are already populated
		if (roleService.findByName("ROLE_USER") == null) {
			// Roles are not present, so initialize them
			Role roleUser = new Role("ROLE_USER");
			Role roleAdmin = new Role("ROLE_ADMIN");

			roleService.saveRole(roleUser);
			roleService.saveRole(roleAdmin);
		}
	}

	private void initializeAdmin(UserService userService) {
		User admin = new User("Manar","Manar@gmail.com","1421");
		userService.saveAdmin(admin);
	}
}
