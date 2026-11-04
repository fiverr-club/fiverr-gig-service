package club.fiverr.gig.domain.model;

import club.fiverr.gig.GigTestFixtures;
import club.fiverr.gig.domain.exception.InvalidGigException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GigTest {

    @Test
    void shouldCreateNewGigWithValidThreeTierPackages() {
        Gig gig = Gig.createNew(
                GigTestFixtures.DEFAULT_SELLER_ID,
                "I will design scalable cloud architecture on AWS",
                "Cloud Computing",
                "Detailed AWS architecture diagrams, Terraform modules, and production readiness review.",
                GigTestFixtures.createValidThreeTierPackages()
        );

        assertNotNull(gig.getId());
        assertEquals(GigStatus.ACTIVE, gig.getStatus());
        assertEquals(3, gig.getPackages().size());
        assertEquals(new BigDecimal("50.00"), gig.getPackage(PackageTier.BASIC).getPrice());
        assertEquals(new BigDecimal("150.00"), gig.getPackage(PackageTier.STANDARD).getPrice());
        assertEquals(new BigDecimal("350.00"), gig.getPackage(PackageTier.PREMIUM).getPrice());
    }

    @Test
    void shouldRejectGigWhenStandardPriceIsLowerThanBasicPrice() {
        List<GigPackage> invalidPackages = List.of(
                new GigPackage(PackageTier.BASIC, "Basic", "Basic package desc", new BigDecimal("100.00"), 3, 1),
                new GigPackage(PackageTier.STANDARD, "Standard", "Standard package desc", new BigDecimal("80.00"), 5, 2),
                new GigPackage(PackageTier.PREMIUM, "Premium", "Premium package desc", new BigDecimal("200.00"), 7, 3)
        );

        assertThrows(InvalidGigException.class, () -> Gig.createNew(
                GigTestFixtures.DEFAULT_SELLER_ID,
                "I will build a custom Java backend service",
                "Programming & Tech",
                "High quality backend engineering with Spring Boot and PostgreSQL.",
                invalidPackages
        ));
    }

    @Test
    void shouldPauseAndReactivateGig() {
        Gig gig = GigTestFixtures.createValidGig();
        assertEquals(GigStatus.ACTIVE, gig.getStatus());

        gig.pause();
        assertEquals(GigStatus.PAUSED, gig.getStatus());

        gig.activate();
        assertEquals(GigStatus.ACTIVE, gig.getStatus());
    }
}
