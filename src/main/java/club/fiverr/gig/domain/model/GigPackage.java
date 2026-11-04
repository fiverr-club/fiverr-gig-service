package club.fiverr.gig.domain.model;

import club.fiverr.gig.domain.exception.InvalidGigException;

import java.math.BigDecimal;
import java.util.Objects;

public final class GigPackage {

    private final PackageTier tier;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int deliveryDays;
    private final int revisions;

    public GigPackage(
            PackageTier tier,
            String name,
            String description,
            BigDecimal price,
            int deliveryDays,
            int revisions
    ) {
        if (tier == null) {
            throw new InvalidGigException("Package tier must not be null");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidGigException("Package name must not be blank");
        }
        if (description == null || description.isBlank()) {
            throw new InvalidGigException("Package description must not be blank");
        }
        if (price == null || price.compareTo(BigDecimal.valueOf(5)) < 0) {
            throw new InvalidGigException("Package price must be at least $5.00");
        }
        if (deliveryDays < 1 || deliveryDays > 90) {
            throw new InvalidGigException("Package delivery days must be between 1 and 90");
        }
        if (revisions < 0) {
            throw new InvalidGigException("Package revisions cannot be negative");
        }
        this.tier = tier;
        this.name = name.trim();
        this.description = description.trim();
        this.price = price;
        this.deliveryDays = deliveryDays;
        this.revisions = revisions;
    }

    public PackageTier getTier() {
        return tier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public int getRevisions() {
        return revisions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GigPackage that)) return false;
        return deliveryDays == that.deliveryDays
                && revisions == that.revisions
                && tier == that.tier
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && (price != null && that.price != null && price.compareTo(that.price) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tier, name, description, price.stripTrailingZeros(), deliveryDays, revisions);
    }
}
