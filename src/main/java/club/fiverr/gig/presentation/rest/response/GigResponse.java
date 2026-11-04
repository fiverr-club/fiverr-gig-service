package club.fiverr.gig.presentation.rest.response;

import club.fiverr.gig.domain.model.GigStatus;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record GigResponse(
        UUID id,
        String slug,
        UUID sellerId,
        String title,
        String category,
        String description,
        List<GigPackageResponse> packages,
        GigStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
