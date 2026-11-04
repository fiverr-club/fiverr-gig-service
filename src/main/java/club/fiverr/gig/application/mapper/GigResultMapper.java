package club.fiverr.gig.application.mapper;

import club.fiverr.gig.application.dto.result.GigPackageResult;
import club.fiverr.gig.application.dto.result.GigResult;
import club.fiverr.gig.domain.model.Gig;
import club.fiverr.gig.domain.model.GigPackage;

import java.util.List;

public final class GigResultMapper {

    private GigResultMapper() {
    }

    public static GigResult toResult(Gig gig) {
        List<GigPackageResult> packages = gig.getPackages().stream()
                .map(GigResultMapper::toPackageResult)
                .toList();

        return new GigResult(
                gig.getId(),
                gig.getSlug(),
                gig.getSellerId(),
                gig.getTitle(),
                gig.getCategory(),
                gig.getDescription(),
                packages,
                gig.getStatus(),
                gig.getCreatedAt(),
                gig.getUpdatedAt()
        );
    }

    public static GigPackageResult toPackageResult(GigPackage pkg) {
        return new GigPackageResult(
                pkg.getTier(),
                pkg.getName(),
                pkg.getDescription(),
                pkg.getPrice(),
                pkg.getDeliveryDays(),
                pkg.getRevisions()
        );
    }
}
