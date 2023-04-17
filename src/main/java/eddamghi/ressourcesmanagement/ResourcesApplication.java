package eddamghi.ressourcesmanagement;

import eddamghi.ressourcesmanagement.DAO.entities.Resource;
import eddamghi.ressourcesmanagement.DAO.repositories.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Date;
@RequiredArgsConstructor
@SpringBootApplication
public class ResourcesApplication {
	private final ResourceRepository resourceRepository;

	public static void main(String[] args) {
		SpringApplication.run(ResourcesApplication.class, args);

	}
	@Bean
	public void test() {
		var resource = Resource.builder()
				.title("book")
				.purchaseDate(Date.from(Instant.now()))
				.price(3000.0)
				.note(10)
				.build();
		resourceRepository.save(resource);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}

