package club.fiverr.gig.infrastructure.adapter;

import club.fiverr.gig.application.port.out.GigEventPublisherPort;
import club.fiverr.gig.domain.model.Gig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GigEventPublisherAdapter implements GigEventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(GigEventPublisherAdapter.class);

    @Override
    public void publishGigCreated(Gig gig) {
        log.info("Published GigCreatedEvent: gigId={}, sellerId={}, slug={}",
                gig.getId(), gig.getSellerId(), gig.getSlug());
    }
}
