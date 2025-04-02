package org.msh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan(basePackages = "org.msh")
@SpringBootApplication
public class ModuleRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleRepositoryApplication.class, args);
    }

}
