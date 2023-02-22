package com.fundamentosplatzi.springboot.fundamentos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependeny;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner{
	
	Log logger = LogFactory.getLog(FundamentosApplication.class);
	
	private ComponentDependeny componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	
	private UserRepository userRepository;
	private UserService userService;
	
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependeny componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//this.saveUser();
		this.saveWithErrorTransaction();
		//this.getInfoUser();
		//ejemplosAnteriores();
	}
	
	private void getInfoUser() {
		/*logger.info("El usuario filtrado es: " + 
					userRepository.findByUserEmail("user1@email.com")
					.orElseThrow(() -> new RuntimeException("No se encontró el usuario")));
		
		userRepository.findAndSort("user", Sort.by("id").descending())
								.stream()
								.forEach(user -> logger.info("Usuario method sort: " + user));
		
		userRepository.findByName("gerson")
			.stream()
			.forEach(user -> logger.info("Usuario con query method: " + user));
		
		userRepository.findByEmailAndName("user1@email.com","gerson")
			.stream()
			.forEach(user -> logger.info("Usuario EmailAndName: " + user));
		
		userRepository.findByNameLike("%user%")
			.stream().forEach(user -> logger.info("User by NameLike " + user));
		
		userRepository.findByNameOrEmail("user7", "")
		.stream().forEach(user -> logger.info("User by Name Or Email " + user));*/
		
		userRepository.findByBirthDateBetween(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 4, 30))
			.stream()
			.forEach(user -> logger.info("Usuario con rango de fechas: " + user));
		
		userRepository.findByNameLikeOrderByIdDesc("%user%")
			.stream()
			.forEach(user -> logger.info("User encontrado con name like order: " + user));
		
		
		userRepository.findByNameContainingOrderByIdDesc("user")
			.stream()
			.forEach(user -> logger.info("User encontrado con name containing order: " + user));
		
		
		logger.info("Usuario a partir de NameParameter: " + 
					userRepository.getByAllBirthDateAndEmail(LocalDate.of(2023, 2, 21), "user1@email.com"));
		
	}
	
	private void saveUser() {
		User user1 = new User("gerson", "user1@email.com", LocalDate.of(2023, 2, 21));
		User user2 = new User("user2", "user2@email.com", LocalDate.of(2023, 3, 21));
		User user3 = new User("user3", "usr3n@email.com", LocalDate.of(2023, 4, 21));
		User user4 = new User("user4", "user4@email.com", LocalDate.of(2023, 5, 21));
		User user5 = new User("gerson", "user5@email.com", LocalDate.of(2023, 6, 21));
		User user6 = new User("user6", "user6@email.com", LocalDate.of(2023, 7, 21));
		User user7 = new User("user7", "user7@email.com", LocalDate.of(2023, 8, 21));
		User user8 = new User("user8", "user8@email.com", LocalDate.of(2023, 9, 21));
		
		List<User> listUsers = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);
		listUsers.stream().forEach(userRepository::save);
		
	}
	
	private void ejemplosAnteriores() {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		logger.error("Este es un error en la aplicación");
	}
	
	private void saveWithErrorTransaction() {
		
		User test1 = new User("Test transactional 1", "transact1@email.com", LocalDate.now());
		User test2 = new User("Test transactional 2", "transact2@email.com", LocalDate.now());
		User test3 = new User("Test transactional 3", "transact3@email.com", LocalDate.now());
		User test4 = new User("Test transactional 4", "transact4@email.com", LocalDate.now());
		
		List<User> users = Arrays.asList(test1, test2, test3, test4);
		
		try {
			userService.saveTransactional(users);	
		} catch (Exception e) {
			logger.error("Excepcion dentro del método transactional " + e);
		}
		logger.error("=== MI LISTA DE USUARIOS ===");
		userService.getAllUsers().stream()
			.forEach(user -> logger.info("Este es el usuario en el método transactional: " + user));
	}
	
	

}
