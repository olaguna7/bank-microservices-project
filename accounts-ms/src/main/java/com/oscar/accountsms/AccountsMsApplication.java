package com.oscar.accountsms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API documentation",
                description = "Bank accounts microservice REST API documentation",
                version = "v1",
                contact = @Contact(
                        name = "Óscar Laguna",
                        email = "oscarlagunacaminero@gmail.com"
                )
        )
)
public class AccountsMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsMsApplication.class, args);
    }

}
