package co.edu.unbosque.loom.configuration;

import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.unbosque.loom.model.Rol;
import co.edu.unbosque.loom.model.Usuario;
import co.edu.unbosque.loom.repository.RolRepository;
import co.edu.unbosque.loom.repository.UsuarioRepository;

@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(UsuarioRepository userRepo, RolRepository rolRepo, PasswordEncoder passwordEncoder) {

		return args -> {
			// Optional<User> found = userRepo.findByUsername(AESUtil.encrypt("admin"));
			Optional<Usuario> found = userRepo.findByUsername("AdminMavayir");
			if (found.isPresent()) {
				log.info("Admin already exists,  skipping admin creating  ...");
				System.out.println("admin creado");
			} else {
				// userRepo.save(new User(AESUtil.encrypt("admin"),
				// AESUtil.encrypt("1234567890")));
				userRepo.save(
					    new Usuario(
					        "AdminMavayir",                       // username
					        "0000000000",                          // documento
					        "Admin",                               // primerNombre
					        "",                                    // segundoNombre
					        "Principal",                           // primerApellido
					        "",                                    // segundoApellido
					        "Sin dirección",                       // direccion
					        "Bogotá",                              // ciudad
					        passwordEncoder.encode("Admin1234*"), // contraseña
					        rolRepo.findByIdRol(3).get(),                             // rol
					        new ArrayList<>()                      // contactos
					    )
					);

			
			}
		};
	}

}
