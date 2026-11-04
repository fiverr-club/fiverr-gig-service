package club.fiverr.gig.presentation.rest.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record CreateGigRequest(
        @NotNull UUID sellerId,
        @NotBlank @Size(min = 10, max = 120) String title,
        @NotBlank String category,
        @NotBlank @Size(min = 20, max = 4000) String description,
        @NotNull @Size(min = 3, max = 3) List<@Valid GigPackageRequest> packages
) {
}
