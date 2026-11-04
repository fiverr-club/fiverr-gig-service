package club.fiverr.gig.infrastructure.persistence.mapper;

import club.fiverr.gig.domain.model.Gig;
import club.fiverr.gig.domain.model.GigPackage;
import club.fiverr.gig.infrastructure.persistence.entity.GigEntity;
import club.fiverr.gig.infrastructure.persistence.entity.GigPackageEmbeddable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GigPersistenceMapper {

    public GigEntity toEntity(Gig domain) {
        GigEntity entity = new GigEntity();
        entity.setId(domain.getId());
        entity.setSlug(domain.getSlug());
        entity.setSellerId(domain.getSellerId());
        entity.setTitle(domain.getTitle());
        entity.setCategory(domain.getCategory());
        entity.setDescription(domain.getDescription());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        List<GigPackageEmbeddable> packageEntities = domain.getPackages().stream()
                .map(pkg -> new GigPackageEmbeddable(
                        pkg.getTier(),
                        pkg.getName(),
                        pkg.getDescription(),
                        pkg.getPrice(),
                        pkg.getDeliveryDays(),
                        pkg.getRevisions()
                ))
                .toList();
        entity.setPackages(packageEntities);
        return entity;
    }

    public Gig toDomain(GigEntity entity) {
        List<GigPackage> packages = entity.getPackages().stream()
                .map(pkg -> new GigPackage(
                        pkg.getTier(),
                        pkg.getName(),
                        pkg.getDescription(),
                        pkg.getPrice(),
                        pkg.getDeliveryDays(),
                        pkg.getRevisions()
                ))
                .toList();

        return Gig.restore(
                entity.getId(),
                entity.getSlug(),
                entity.getSellerId(),
                entity.getTitle(),
                entity.getCategory(),
                entity.getDescription(),
                packages,
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
