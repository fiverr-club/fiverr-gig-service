package club.fiverr.gig.infrastructure.adapter;

import club.fiverr.gig.application.port.out.GigSearchIndexPort;
import club.fiverr.gig.domain.model.Gig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GigSearchIndexAdapter implements GigSearchIndexPort {

    private static final Logger log = LoggerFactory.getLogger(GigSearchIndexAdapter.class);

    @Override
    public void indexGig(Gig gig) {
        log.info("Indexed gig in search engine: id={}, slug={}, category={}",
                gig.getId(), gig.getSlug(), gig.getCategory());
    }
}
