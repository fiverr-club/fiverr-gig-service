package club.fiverr.gig.application.dto.command;

import club.fiverr.gig.domain.model.PackageTier;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateGigCommand(
        UUID sellerId,
        String title,
        String category,
        String description,
        List<PackageCommand> packages
) {
    public record PackageCommand(
            PackageTier tier,
            String name,
            String description,
            BigDecimal price,
            int deliveryDays,
            int revisions
    ) {
    }
}
