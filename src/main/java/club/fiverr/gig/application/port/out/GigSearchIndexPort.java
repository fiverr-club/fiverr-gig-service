package club.fiverr.gig.application.port.out;

import club.fiverr.gig.domain.model.Gig;

public interface GigSearchIndexPort {

    void indexGig(Gig gig);
}
