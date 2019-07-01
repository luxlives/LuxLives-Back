package com.luxlivesback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LuxLivesBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuxLivesBackApplication.class, args);
	}
	
	/*@Autowired
	private UsuariosRepository usuariosRepository;	*/
	
	/*@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Usuarios usuarios = new Usuarios();
			usuarios.setNome("teste");
			usuarios.setEmail("teste@teste.com");
			usuarios.setLogin("teste");
			usuarios.setPassword(PasswordUtils.gerarBCrypt("teste"));
			usuarios.setTipo("ADMIN");
			System.out.println(usuarios.toString());
			usuariosRepository.save(usuarios);
		};
	}*/
	
	/*@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			usuariosRepository.findById((long) 3).ifPresent(user -> {
				user.setPassword(PasswordUtils.gerarBCrypt("teste"));
				usuariosRepository.save(user);
			});
			usuariosRepository.findById((long) 4).ifPresent(user -> {
				user.setPassword(PasswordUtils.gerarBCrypt("teste"));
				usuariosRepository.save(user);
			});
			usuariosRepository.findById((long) 5).ifPresent(user -> {
				user.setPassword(PasswordUtils.gerarBCrypt("teste"));
				usuariosRepository.save(user);
			});
		};
	}*/
	
	

}

//Documentação
//https://docs.spring.io/spring-boot/docs/2.0.8.RELEASE/reference/htmlsingle/
