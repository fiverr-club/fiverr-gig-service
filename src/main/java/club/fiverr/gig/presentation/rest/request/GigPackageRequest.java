package club.fiverr.gig.presentation.rest.request;

import club.fiverr.gig.domain.model.PackageTier;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record GigPackageRequest(
        @NotNull PackageTier tier,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @DecimalMin("5.00") BigDecimal price,
        @Min(1) @Max(90) int deliveryDays,
        @Min(0) int revisions
) {
}
