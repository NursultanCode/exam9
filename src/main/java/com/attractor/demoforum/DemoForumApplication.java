package com.attractor.demoforum;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring5.SpringTemplateEngine;

@SpringBootApplication
public class DemoForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoForumApplication.class, args);
    }

}
