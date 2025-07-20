package com.company.educalink.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "EducaLink API",
                description = "Connecting your knowledge with the future",
                version = "1.0.0",
                contact = @Contact(
                        name = "Sandro Barros",
                        url = "https://sandro-dev.netlify.app/",
                        email = "sstivenbarroscaballero0826@gmail.com"
                ),
                license = @License(
                        name = "Standard Software Use License for Sandro Barros",
                        url = "https://sandro-dev.netlify.app/"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {
}
