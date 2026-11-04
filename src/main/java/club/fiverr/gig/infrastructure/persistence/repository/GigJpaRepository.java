package club.fiverr.gig.infrastructure.persistence.repository;

import club.fiverr.gig.domain.model.GigStatus;
import club.fiverr.gig.infrastructure.persistence.entity.GigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GigJpaRepository extends JpaRepository<GigEntity, UUID> {

    Optional<GigEntity> findBySlug(String slug);

    boolean existsBySlug(String slug);

    @Query("""
            SELECT g FROM GigEntity g
            WHERE g.status = :status
              AND (:keyword = '' OR LOWER(g.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(g.description) LIKE LOWER(CONCAT('%', :keyword, '%')))
              AND (:category = '' OR LOWER(g.category) = LOWER(:category))
            ORDER BY g.createdAt DESC
            """)
    List<GigEntity> searchByKeywordAndCategory(
            @Param("status") GigStatus status,
            @Param("keyword") String keyword,
            @Param("category") String category
    );
}
