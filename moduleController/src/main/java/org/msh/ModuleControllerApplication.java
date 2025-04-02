package org.msh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = "org.msh.repositoryJpa")
//@ComponentScan(basePackages = {"org.msh.controller"
//                                ,"org.msh.service"
//                                ,"org.msh.repository"})
@ComponentScan(basePackages = "org.msh.*")
@SpringBootApplication
public class ModuleControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleControllerApplication.class, args);
    }

}
