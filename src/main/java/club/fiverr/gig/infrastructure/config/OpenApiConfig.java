package club.fiverr.gig.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gigServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FIVERREX. — Gig Catalog Service API")
                        .version("1.0.0")
                        .description("FIVERREX Gig Catalog & 3-Tier Pricing Microservice (DDD + Hexagonal Architecture)")
                        .contact(new Contact()
                                .name("FIVERREX Platform Engineering")
                                .url("https://fiverrex.club")))
                .servers(List.of(
                        new Server().url("http://localhost:8083").description("Gig Service Direct (:8083)"),
                        new Server().url("http://localhost:8080").description("FIVERREX API Gateway (:8080)")
                ));
    }
}
