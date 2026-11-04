package club.fiverr.gig.presentation.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class ServiceStatusController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> status() {
        return ResponseEntity.ok(Map.of(
                "brand", "FIVERREX",
                "service", "fiverr-gig-service",
                "status", "UP",
                "swaggerUi", "http://localhost:8083/swagger-ui/index.html",
                "openApiDocs", "http://localhost:8083/v3/api-docs",
                "timestamp", Instant.now().toString()
        ));
    }
}
