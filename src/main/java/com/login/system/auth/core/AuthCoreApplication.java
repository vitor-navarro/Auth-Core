package com.login.system.auth.core;

import com.login.system.auth.core.entity.UserEntity;
import com.login.system.auth.core.enums.UserRole;
import com.login.system.auth.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AuthCoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthCoreApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	//TODO refazer a lógica
	@Override
	public void run(String... args) throws Exception {
		List<UserEntity> adminsUsers = userRepository.findByRole(UserRole.ADMIN);
		if(adminsUsers.isEmpty()){
			System.out.println("Usuario admin nao foi encontrado, criando um novo");
			UserEntity admin = new UserEntity();
			admin.setUsername("Admin");
			admin.setPassword("admin"); // TODO implementar cryptografia
			admin.setRole(UserRole.ADMIN);
			System.out.println(admin);
			userRepository.save(admin);
			System.out.println("Usuario admin criado com sucesso.");
		} /*else{
			//for debug
			//System.out.println("Usuario admin encontrado");
		}*/
	}

}