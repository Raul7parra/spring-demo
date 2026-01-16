package es.fplumara.dam2.springdemo.security;

import es.fplumara.dam2.springdemo.domain.AppUser;
import es.fplumara.dam2.springdemo.domain.Role;
import es.fplumara.dam2.springdemo.repositories.RoleRepository;
import es.fplumara.dam2.springdemo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecuritySeedData {

    @Bean
    @Profile({"dev","test"})
    CommandLineRunner seedUsers(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            Role userRole = roleRepo.findByName("USER").orElseGet(() -> roleRepo.save(new Role("USER")));
            Role adminRole = roleRepo.findByName("ADMIN").orElseGet(() -> roleRepo.save(new Role("ADMIN")));

            if (userRepo.findByUsername("user").isEmpty()) {
                AppUser u = new AppUser("user", encoder.encode("user123"), true);
                u.addRole(userRole);
                userRepo.save(u);
            }

            if (userRepo.findByUsername("admin").isEmpty()) {
                AppUser a = new AppUser("admin", encoder.encode("admin123"), true);
                a.addRole(adminRole);
                userRepo.save(a);
            }
        };
    }
}
