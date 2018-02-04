package com.spring.oauth;

import com.spring.oauth.entities.Role;
import com.spring.oauth.entities.User;
import com.spring.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

@SpringBootApplication
public class SpringOauthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOauthDemoApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {

		if (repo.count() == 0) {
			repo.save(new User("user", "user", Arrays.asList(new Role("ADMIN"))));
		}

		builder.userDetailsService(username -> new CustomUserDetails(repo.findByUsername(username)));
	}
}


