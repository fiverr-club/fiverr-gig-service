package club.fiverr.gig.infrastructure.persistence.repository;

import club.fiverr.gig.domain.model.Gig;
import club.fiverr.gig.domain.model.GigStatus;
import club.fiverr.gig.domain.repository.GigRepository;
import club.fiverr.gig.infrastructure.persistence.entity.GigEntity;
import club.fiverr.gig.infrastructure.persistence.mapper.GigPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaGigRepository implements GigRepository {

    private final GigJpaRepository jpaRepository;
    private final GigPersistenceMapper mapper;

    public JpaGigRepository(GigJpaRepository jpaRepository, GigPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Gig save(Gig gig) {
        GigEntity saved = jpaRepository.save(mapper.toEntity(gig));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Gig> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Gig> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug).map(mapper::toDomain);
    }

    @Override
    public boolean existsBySlug(String slug) {
        return jpaRepository.existsBySlug(slug);
    }

    @Override
    public List<Gig> searchActiveGigs(String keyword, String category) {
        return jpaRepository.searchByKeywordAndCategory(
                        GigStatus.ACTIVE,
                        keyword == null ? "" : keyword,
                        category == null ? "" : category
                ).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
