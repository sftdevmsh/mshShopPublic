package org.msh;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EntityScan(basePackages = "org.msh.entity")
//@EnableJpaRepositories(basePackages = "org.msh.repositoryJpa")
@ComponentScan(basePackages = "org.msh.*")
@SpringBootApplication
public class ModuleControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleControllerApplication.class, args);
    }

}
