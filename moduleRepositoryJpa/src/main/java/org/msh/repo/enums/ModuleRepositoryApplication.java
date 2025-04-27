package org.msh.repo.enums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EntityScan(basePackages = "org.msh")
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) //in case datasource no needed immediately
@SpringBootApplication
public class ModuleRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleRepositoryApplication.class, args);
    }

}
