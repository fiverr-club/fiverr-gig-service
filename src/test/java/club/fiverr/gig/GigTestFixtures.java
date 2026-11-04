package club.fiverr.gig;

import club.fiverr.gig.application.dto.command.CreateGigCommand;
import club.fiverr.gig.domain.model.Gig;
import club.fiverr.gig.domain.model.GigPackage;
import club.fiverr.gig.domain.model.GigStatus;
import club.fiverr.gig.domain.model.PackageTier;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public final class GigTestFixtures {

    public static final UUID DEFAULT_GIG_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    public static final UUID DEFAULT_SELLER_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");

    private GigTestFixtures() {
    }

    public static List<GigPackage> createValidThreeTierPackages() {
        return List.of(
                new GigPackage(
                        PackageTier.BASIC,
                        "Starter Microservice",
                        "Single Spring Boot microservice with basic CRUD endpoints",
                        new BigDecimal("50.00"),
                        3,
                        1
                ),
                new GigPackage(
                        PackageTier.STANDARD,
                        "Production DDD Service",
                        "Hexagonal DDD Spring Boot service with PostgreSQL & unit tests",
                        new BigDecimal("150.00"),
                        5,
                        3
                ),
                new GigPackage(
                        PackageTier.PREMIUM,
                        "Enterprise Microservice Suite",
                        "Full DDD microservice with Docker, CI/CD, and observability",
                        new BigDecimal("350.00"),
                        7,
                        5
                )
        );
    }

    public static Gig createValidGig() {
        return Gig.restore(
                DEFAULT_GIG_ID,
                "i-will-build-a-spring-boot-microservice",
                DEFAULT_SELLER_ID,
                "I will build a production Spring Boot microservice",
                "Programming & Tech",
                "Complete Java 21 Spring Boot 3.3.5 backend engineered with Domain-Driven Design.",
                createValidThreeTierPackages(),
                GigStatus.ACTIVE,
                Instant.parse("2026-01-01T00:00:00Z"),
                Instant.parse("2026-01-01T00:00:00Z")
        );
    }

    public static CreateGigCommand createValidCreateGigCommand() {
        return new CreateGigCommand(
                DEFAULT_SELLER_ID,
                "I will build a production Spring Boot microservice",
                "Programming & Tech",
                "Complete Java 21 Spring Boot 3.3.5 backend engineered with Domain-Driven Design.",
                List.of(
                        new CreateGigCommand.PackageCommand(
                                PackageTier.BASIC,
                                "Starter Microservice",
                                "Single Spring Boot microservice with basic CRUD endpoints",
                                new BigDecimal("50.00"),
                                3,
                                1
                        ),
                        new CreateGigCommand.PackageCommand(
                                PackageTier.STANDARD,
                                "Production DDD Service",
                                "Hexagonal DDD Spring Boot service with PostgreSQL & unit tests",
                                new BigDecimal("150.00"),
                                5,
                                3
                        ),
                        new CreateGigCommand.PackageCommand(
                                PackageTier.PREMIUM,
                                "Enterprise Microservice Suite",
                                "Full DDD microservice with Docker, CI/CD, and observability",
                                new BigDecimal("350.00"),
                                7,
                                5
                        )
                )
        );
    }
}
