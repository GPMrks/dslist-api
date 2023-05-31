package com.gpmrks.dslistapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "DSList API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Guilherme M.", email = "guilhermepereiramarques@hotmail.com", url = "https://www.linkedin.com/in/guilherme-p-marques/"
                ),
                description = "API para Gerenciamento de Games"
        ),
        servers = {
                @Server(
                url = "https://dslist-api-production-94b4.up.railway.app/",
                description = "Production"),
                @Server(
                url = "http://localhost:8080",
                description = "Development"
        )}
)
public class SpringDoc {
}
