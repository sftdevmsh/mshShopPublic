package org.msh.ctrl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

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
