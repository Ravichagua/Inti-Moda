package com.intimoda.app;

import com.intimoda.app.jpa.model.Producto;
import com.intimoda.app.jpa.model.User;
import com.intimoda.app.jpa.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.intimoda.app.jpa.repository.ProductoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		System.out.println("Recursos cargados!");
	}
	@Bean
	public CommandLineRunner initData(PasswordEncoder passwordEncoder, UserRepository userRepo, ProductoRepository productoRepository) {
		return args -> {
			/*List<Producto> productos = List.of(
					new Producto(null, "Casaca Blanca", "Silla ergonómica con respaldo de malla", 149.99f, 10, "Casacas", "CasacaBlanca.png"),
					new Producto(null, "Casaca Gris", "Escritorio con luces LED y soporte para monitor", 299.90f, 5, "Casacas", "CasacaGris.png"),
					new Producto(null, "Casaca Jean", "Lámpara con diseño minimalista y luz cálida", 79.50f, 20, "Casacas", "CasacaJean.png"),
					new Producto(null, "Casaca Naranja", "Sofá tapizado en tela gris con cojines incluidos", 899.00f, 2, "Casacas", "CasacaNaranja.png"),
					new Producto(null, "Casaca Negra", "Mesa de centro de madera con vidrio templado", 199.99f, 7, "Casacas", "CasacaNegra.png"),
					new Producto(null, "Abrigo Marron", "Estantería blanca ideal para libros y decoración", 129.00f, 15, "Invierno", "AbrigoMarron.png")
			);

			productoRepository.saveAll(productos);
			*/
			if (userRepo.findByEmail("admin@admin").isEmpty()) {
				User admin = new User();
				admin.setEmail("admin@admin");
				admin.setPassword(passwordEncoder.encode("admin"));

				admin.setRole("ADMIN"); // Solo texto plano

				userRepo.save(admin);
			}



		};

	}



}
