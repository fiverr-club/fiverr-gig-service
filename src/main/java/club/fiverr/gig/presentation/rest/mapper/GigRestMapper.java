package club.fiverr.gig.presentation.rest.mapper;

import club.fiverr.gig.application.dto.command.CreateGigCommand;
import club.fiverr.gig.application.dto.result.GigPackageResult;
import club.fiverr.gig.application.dto.result.GigResult;
import club.fiverr.gig.presentation.rest.request.CreateGigRequest;
import club.fiverr.gig.presentation.rest.response.GigPackageResponse;
import club.fiverr.gig.presentation.rest.response.GigResponse;

import java.util.List;

public final class GigRestMapper {

    private GigRestMapper() {
    }

    public static CreateGigCommand toCommand(CreateGigRequest request) {
        List<CreateGigCommand.PackageCommand> packageCommands = request.packages().stream()
                .map(pkg -> new CreateGigCommand.PackageCommand(
                        pkg.tier(),
                        pkg.name(),
                        pkg.description(),
                        pkg.price(),
                        pkg.deliveryDays(),
                        pkg.revisions()
                ))
                .toList();

        return new CreateGigCommand(
                request.sellerId(),
                request.title(),
                request.category(),
                request.description(),
                packageCommands
        );
    }

    public static GigResponse toResponse(GigResult result) {
        List<GigPackageResponse> packageResponses = result.packages().stream()
                .map(GigRestMapper::toPackageResponse)
                .toList();

        return new GigResponse(
                result.id(),
                result.slug(),
                result.sellerId(),
                result.title(),
                result.category(),
                result.description(),
                packageResponses,
                result.status(),
                result.createdAt(),
                result.updatedAt()
        );
    }

    public static GigPackageResponse toPackageResponse(GigPackageResult result) {
        return new GigPackageResponse(
                result.tier(),
                result.name(),
                result.description(),
                result.price(),
                result.deliveryDays(),
                result.revisions()
        );
    }
}
