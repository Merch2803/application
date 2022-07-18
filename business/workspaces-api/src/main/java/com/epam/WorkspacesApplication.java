package com.epam;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WorkspacesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkspacesApplication.class, args);
    }
}
