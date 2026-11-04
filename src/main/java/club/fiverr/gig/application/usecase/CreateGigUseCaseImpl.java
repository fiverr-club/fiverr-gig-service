package club.fiverr.gig.application.usecase;

import club.fiverr.gig.application.dto.command.CreateGigCommand;
import club.fiverr.gig.application.dto.result.GigResult;
import club.fiverr.gig.application.mapper.GigResultMapper;
import club.fiverr.gig.application.port.in.CreateGigUseCase;
import club.fiverr.gig.application.port.out.GigEventPublisherPort;
import club.fiverr.gig.application.port.out.GigSearchIndexPort;
import club.fiverr.gig.domain.model.Gig;
import club.fiverr.gig.domain.model.GigPackage;
import club.fiverr.gig.domain.repository.GigRepository;
import club.fiverr.gig.domain.service.GigSlugService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreateGigUseCaseImpl implements CreateGigUseCase {

    private final GigRepository gigRepository;
    private final GigSlugService gigSlugService;
    private final GigSearchIndexPort gigSearchIndexPort;
    private final GigEventPublisherPort gigEventPublisherPort;

    public CreateGigUseCaseImpl(
            GigRepository gigRepository,
            GigSlugService gigSlugService,
            GigSearchIndexPort gigSearchIndexPort,
            GigEventPublisherPort gigEventPublisherPort
    ) {
        this.gigRepository = gigRepository;
        this.gigSlugService = gigSlugService;
        this.gigSearchIndexPort = gigSearchIndexPort;
        this.gigEventPublisherPort = gigEventPublisherPort;
    }

    @Override
    @Transactional
    public GigResult execute(CreateGigCommand command) {
        List<GigPackage> packages = command.packages() == null ? List.of() :
                command.packages().stream()
                        .map(pkg -> new GigPackage(
                                pkg.tier(),
                                pkg.name(),
                                pkg.description(),
                                pkg.price(),
                                pkg.deliveryDays(),
                                pkg.revisions()
                        ))
                        .toList();

        Gig gig = Gig.createNew(
                command.sellerId(),
                command.title(),
                command.category(),
                command.description(),
                packages
        );

        String uniqueSlug = gigSlugService.generateUniqueSlug(gig.getTitle());
        gig.assignSlug(uniqueSlug);

        Gig saved = gigRepository.save(gig);

        gigSearchIndexPort.indexGig(saved);
        gigEventPublisherPort.publishGigCreated(saved);

        return GigResultMapper.toResult(saved);
    }
}
