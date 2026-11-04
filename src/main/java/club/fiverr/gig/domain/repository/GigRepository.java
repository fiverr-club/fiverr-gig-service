package club.fiverr.gig.domain.repository;

import club.fiverr.gig.domain.model.Gig;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GigRepository {

    Gig save(Gig gig);

    Optional<Gig> findById(UUID id);

    Optional<Gig> findBySlug(String slug);

    boolean existsBySlug(String slug);

    List<Gig> searchActiveGigs(String keyword, String category);
}
