package com.login.system.auth.core;

import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.enums.UserRole;
import com.login.system.auth.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class AuthCoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthCoreApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		List<UserEntity> adminsUsers = userRepository.findByRole(UserRole.ADMIN);
		if (adminsUsers.isEmpty()) {
			System.out.println("Usuario admin nao foi encontrado, criando um novo");

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			UserEntity admin = new UserEntity();
			admin.setUsername("Admin");
			admin.setPassword(passwordEncoder.encode("admin")); // Encode corretamente
			admin.setRole(UserRole.ADMIN);

			userRepository.save(admin);
			System.out.println("Usuario admin criado com sucesso.");
		} /*else{
			//for debug
			//System.out.println("Usuario admin encontrado");
		}*/
	}

}