package club.fiverr.gig.presentation.rest.response;

import club.fiverr.gig.domain.model.PackageTier;

import java.math.BigDecimal;

public record GigPackageResponse(
        PackageTier tier,
        String name,
        String description,
        BigDecimal price,
        int deliveryDays,
        int revisions
) {
}
