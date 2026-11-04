package club.fiverr.gig.infrastructure.config;

import club.fiverr.gig.domain.model.Gig;
import club.fiverr.gig.domain.model.GigPackage;
import club.fiverr.gig.domain.model.GigStatus;
import club.fiverr.gig.domain.model.PackageTier;
import club.fiverr.gig.domain.repository.GigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Configuration
public class GigDataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(GigDataInitializer.class);

    private final GigRepository gigRepository;

    public GigDataInitializer(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Override
    public void run(String... args) {
        if (!gigRepository.search("", "").isEmpty()) {
            return;
        }

        Instant now = Instant.now();

        Gig gig1 = Gig.restore(
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                "i-will-architect-java-21-spring-boot-ddd-microservices-and-k8s-helm-charts",
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                "I will architect Java 21 Spring Boot DDD microservices and K8s Helm charts",
                "Programming & Tech",
                "End-to-end Hexagonal DDD microservices engineered with Java 21, Spring Boot 3.3, PostgreSQL, Redis, Kafka, and Kubernetes Helm deployment.",
                List.of(
                        new GigPackage(
                                PackageTier.BASIC,
                                "Single DDD Microservice",
                                "1 Spring Boot 3.3 Hexagonal DDD microservice with unit tests and Dockerfile",
                                new BigDecimal("195.00"),
                                3,
                                2
                        ),
                        new GigPackage(
                                PackageTier.STANDARD,
                                "3 Microservices + API Gateway",
                                "3 DDD microservices + Spring Cloud Gateway + Docker Compose stack",
                                new BigDecimal("495.00"),
                                5,
                                5
                        ),
                        new GigPackage(
                                PackageTier.PREMIUM,
                                "Full Ecosystem + K8s Helm",
                                "Complete microservices platform + Kafka events + Kubernetes Helm production charts",
                                new BigDecimal("995.00"),
                                7,
                                10
                        )
                ),
                GigStatus.ACTIVE,
                now.minusSeconds(86400 * 5),
                now.minusSeconds(86400 * 2)
        );

        Gig gig2 = Gig.restore(
                UUID.fromString("22222222-2222-2222-2222-222222222222"),
                "i-will-build-a-next-js-15-react-19-saas-marketplace-with-tailwind-css",
                UUID.fromString("22222222-2222-2222-2222-222222222222"),
                "I will build a Next.js 15 React 19 SaaS marketplace with Tailwind CSS",
                "Programming & Tech",
                "Pixel-perfect, responsive Next.js 15 App Router web application with TypeScript, Tailwind CSS v4, SSR/ISR optimization, and API integration.",
                List.of(
                        new GigPackage(
                                PackageTier.BASIC,
                                "Landing Page & Auth UI",
                                "Responsive Next.js 15 landing page + authentication screens with Tailwind CSS",
                                new BigDecimal("150.00"),
                                2,
                                2
                        ),
                        new GigPackage(
                                PackageTier.STANDARD,
                                "Full Marketplace Frontend",
                                "Catalog search, Gig detail drawer, 3-tier checkout & order dashboard",
                                new BigDecimal("390.00"),
                                4,
                                4
                        ),
                        new GigPackage(
                                PackageTier.PREMIUM,
                                "Enterprise Full-Stack Portal",
                                "Complete Buyer/Seller workspace, real-time messaging UI, analytics & Playwright E2E tests",
                                new BigDecimal("790.00"),
                                6,
                                8
                        )
                ),
                GigStatus.ACTIVE,
                now.minusSeconds(86400 * 4),
                now.minusSeconds(86400)
        );

        Gig gig3 = Gig.restore(
                UUID.fromString("33333333-3333-3333-3333-333333333333"),
                "i-will-design-a-complete-figma-design-system-and-high-converting-brand-identity",
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                "I will design a complete Figma design system and high-converting brand identity",
                "Graphics & Design",
                "Comprehensive Figma component library, design tokens, light/dark themes, interactive prototypes, and modern brand guidelines.",
                List.of(
                        new GigPackage(
                                PackageTier.BASIC,
                                "Core Brand & UI Kit",
                                "Typography, color tokens, logo variations, and 15 core Figma components",
                                new BigDecimal("120.00"),
                                2,
                                2
                        ),
                        new GigPackage(
                                PackageTier.STANDARD,
                                "Complete Design System",
                                "60+ auto-layout Figma components, responsive variants, and 8 key screens",
                                new BigDecimal("295.00"),
                                4,
                                4
                        ),
                        new GigPackage(
                                PackageTier.PREMIUM,
                                "Full Product UI/UX Suite",
                                "120+ components, interactive prototype, motion specs, and developer handoff guide",
                                new BigDecimal("595.00"),
                                6,
                                6
                        )
                ),
                GigStatus.ACTIVE,
                now.minusSeconds(86400 * 3),
                now
        );

        gigRepository.save(gig1);
        gigRepository.save(gig2);
        gigRepository.save(gig3);

        log.info("Seeded 3 active FIVERREX gigs into Gig Catalog database");
    }
}
