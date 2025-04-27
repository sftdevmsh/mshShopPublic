package org.msh;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
//@EntityScan(basePackages = "org.msh.entity")
//@EnableJpaRepositories(basePackages = "org.msh.repositoryJpa")
@ComponentScan(basePackages = "org.msh.*")
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "online shop", version = "1.0" , description = "java/spring and mysql"))
public class ModuleControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleControllerApplication.class, args);
    }

}
