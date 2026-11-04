package club.fiverr.gig.infrastructure.persistence.entity;

import club.fiverr.gig.domain.model.PackageTier;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

@Embeddable
public class GigPackageEmbeddable {

    @Enumerated(EnumType.STRING)
    @Column(name = "tier", nullable = false, length = 20)
    private PackageTier tier;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "delivery_days", nullable = false)
    private int deliveryDays;

    @Column(name = "revisions", nullable = false)
    private int revisions;

    public GigPackageEmbeddable() {
    }

    public GigPackageEmbeddable(
            PackageTier tier,
            String name,
            String description,
            BigDecimal price,
            int deliveryDays,
            int revisions
    ) {
        this.tier = tier;
        this.name = name;
        this.description = description;
        this.price = price;
        this.deliveryDays = deliveryDays;
        this.revisions = revisions;
    }

    public PackageTier getTier() {
        return tier;
    }

    public void setTier(PackageTier tier) {
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(int deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public int getRevisions() {
        return revisions;
    }

    public void setRevisions(int revisions) {
        this.revisions = revisions;
    }
}
