package com.microjade.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @EnableJpaAuditing() Con esto le decimos a Spring que active JPAAuditing y delegale al bean la posibilidad de que maneje la auditoria
 *
 */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "Ian Project Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Ian Fernandez",
                        email = "ianstgo@gmail.com",
                        url = "https://www.linkedin.com/in/ian-fern%C3%A1ndez-a72598179/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.pryingopenmythirdeye.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Ian Project Accounts microservice REST API Documentation",
                url = "http://pryingopenmythirdeye/swagger-ui.html"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
