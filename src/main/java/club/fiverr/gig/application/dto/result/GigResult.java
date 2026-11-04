package club.fiverr.gig.application.dto.result;

import club.fiverr.gig.domain.model.GigStatus;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record GigResult(
        UUID id,
        String slug,
        UUID sellerId,
        String title,
        String category,
        String description,
        List<GigPackageResult> packages,
        GigStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
