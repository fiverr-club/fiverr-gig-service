package club.fiverr.gig.domain.model;

import club.fiverr.gig.domain.exception.InvalidGigException;

import java.time.Instant;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Gig {

    private final UUID id;
    private String slug;
    private final UUID sellerId;
    private String title;
    private String category;
    private String description;
    private final Map<PackageTier, GigPackage> packages;
    private GigStatus status;
    private final Instant createdAt;
    private Instant updatedAt;

    private Gig(
            UUID id,
            String slug,
            UUID sellerId,
            String title,
            String category,
            String description,
            Map<PackageTier, GigPackage> packages,
            GigStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.slug = slug;
        this.sellerId = sellerId;
        this.title = title;
        this.category = category;
        this.description = description;
        this.packages = new EnumMap<>(packages);
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Gig createNew(
            UUID sellerId,
            String title,
            String category,
            String description,
            List<GigPackage> packageList
    ) {
        validateCoreFields(sellerId, title, category, description);
        Map<PackageTier, GigPackage> packageMap = validateThreeTierPackages(packageList);
        Instant now = Instant.now();
        return new Gig(
                UUID.randomUUID(),
                null,
                sellerId,
                title.trim(),
                category.trim(),
                description.trim(),
                packageMap,
                GigStatus.ACTIVE,
                now,
                now
        );
    }

    public static Gig restore(
            UUID id,
            String slug,
            UUID sellerId,
            String title,
            String category,
            String description,
            List<GigPackage> packageList,
            GigStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        validateCoreFields(sellerId, title, category, description);
        Map<PackageTier, GigPackage> packageMap = validateThreeTierPackages(packageList);
        return new Gig(
                id,
                slug,
                sellerId,
                title,
                category,
                description,
                packageMap,
                status != null ? status : GigStatus.DRAFT,
                createdAt != null ? createdAt : Instant.now(),
                updatedAt != null ? updatedAt : Instant.now()
        );
    }

    private static void validateCoreFields(
            UUID sellerId,
            String title,
            String category,
            String description
    ) {
        if (sellerId == null) {
            throw new InvalidGigException("Seller ID must not be null");
        }
        if (title == null || title.trim().length() < 10 || title.trim().length() > 120) {
            throw new InvalidGigException("Gig title must be between 10 and 120 characters");
        }
        if (category == null || category.isBlank()) {
            throw new InvalidGigException("Gig category must not be blank");
        }
        if (description == null || description.trim().length() < 20) {
            throw new InvalidGigException("Gig description must be at least 20 characters");
        }
    }

    private static Map<PackageTier, GigPackage> validateThreeTierPackages(List<GigPackage> packageList) {
        if (packageList == null || packageList.size() != 3) {
            throw new InvalidGigException("A Gig must define all 3 pricing tiers: BASIC, STANDARD, and PREMIUM");
        }
        Map<PackageTier, GigPackage> map = new EnumMap<>(PackageTier.class);
        for (GigPackage pkg : packageList) {
            if (pkg == null) {
                throw new InvalidGigException("Gig package entry must not be null");
            }
            if (map.put(pkg.getTier(), pkg) != null) {
                throw new InvalidGigException("Duplicate package tier: " + pkg.getTier());
            }
        }
        if (!map.containsKey(PackageTier.BASIC)
                || !map.containsKey(PackageTier.STANDARD)
                || !map.containsKey(PackageTier.PREMIUM)) {
            throw new InvalidGigException("A Gig must include BASIC, STANDARD, and PREMIUM packages");
        }
        GigPackage basic = map.get(PackageTier.BASIC);
        GigPackage standard = map.get(PackageTier.STANDARD);
        GigPackage premium = map.get(PackageTier.PREMIUM);

        if (standard.getPrice().compareTo(basic.getPrice()) < 0) {
            throw new InvalidGigException("STANDARD package price must be greater than or equal to BASIC package price");
        }
        if (premium.getPrice().compareTo(standard.getPrice()) < 0) {
            throw new InvalidGigException("PREMIUM package price must be greater than or equal to STANDARD package price");
        }
        return map;
    }

    public void assignSlug(String slug) {
        if (slug == null || slug.isBlank()) {
            throw new InvalidGigException("Gig slug must not be blank");
        }
        this.slug = slug.trim();
        this.updatedAt = Instant.now();
    }

    public void activate() {
        this.status = GigStatus.ACTIVE;
        this.updatedAt = Instant.now();
    }

    public void pause() {
        if (this.status == GigStatus.DRAFT) {
            throw new InvalidGigException("Cannot pause a gig that is still in DRAFT status");
        }
        this.status = GigStatus.PAUSED;
        this.updatedAt = Instant.now();
    }

    public void markDraft() {
        this.status = GigStatus.DRAFT;
        this.updatedAt = Instant.now();
    }

    public GigPackage getPackage(PackageTier tier) {
        return packages.get(tier);
    }

    public List<GigPackage> getPackages() {
        return List.copyOf(packages.values());
    }

    public Map<PackageTier, GigPackage> getPackageMap() {
        return Collections.unmodifiableMap(packages);
    }

    public UUID getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public GigStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
