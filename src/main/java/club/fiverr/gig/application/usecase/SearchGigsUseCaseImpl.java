package club.fiverr.gig.application.usecase;

import club.fiverr.gig.application.dto.command.SearchGigsCommand;
import club.fiverr.gig.application.dto.result.GigResult;
import club.fiverr.gig.application.mapper.GigResultMapper;
import club.fiverr.gig.application.port.in.SearchGigsUseCase;
import club.fiverr.gig.domain.repository.GigRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchGigsUseCaseImpl implements SearchGigsUseCase {

    private final GigRepository gigRepository;

    public SearchGigsUseCaseImpl(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GigResult> execute(SearchGigsCommand command) {
        String keyword = command.keyword() == null ? "" : command.keyword().trim();
        String category = command.category() == null ? "" : command.category().trim();
        return gigRepository.searchActiveGigs(keyword, category).stream()
                .map(GigResultMapper::toResult)
                .toList();
    }
}
