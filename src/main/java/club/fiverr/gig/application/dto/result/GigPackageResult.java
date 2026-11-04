package club.fiverr.gig.application.dto.result;

import club.fiverr.gig.domain.model.PackageTier;

import java.math.BigDecimal;

public record GigPackageResult(
        PackageTier tier,
        String name,
        String description,
        BigDecimal price,
        int deliveryDays,
        int revisions
) {
}
