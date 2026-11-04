package club.fiverr.gig.domain.service;

import club.fiverr.gig.domain.repository.GigRepository;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Locale;
import java.util.UUID;

@Service
public class GigSlugService {

    private final GigRepository gigRepository;

    public GigSlugService(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    public String generateUniqueSlug(String title) {
        String normalized = Normalizer.normalize(title == null ? "gig" : title, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-");

        if (normalized.isBlank()) {
            normalized = "gig";
        }

        if (!gigRepository.existsBySlug(normalized)) {
            return normalized;
        }

        String candidate = normalized + "-" + UUID.randomUUID().toString().substring(0, 6);
        while (gigRepository.existsBySlug(candidate)) {
            candidate = normalized + "-" + UUID.randomUUID().toString().substring(0, 6);
        }
        return candidate;
    }
}
