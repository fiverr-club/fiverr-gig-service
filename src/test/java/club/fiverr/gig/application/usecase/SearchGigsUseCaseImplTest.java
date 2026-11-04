package club.fiverr.gig.application.usecase;

import club.fiverr.gig.GigTestFixtures;
import club.fiverr.gig.application.dto.command.SearchGigsCommand;
import club.fiverr.gig.application.dto.result.GigResult;
import club.fiverr.gig.domain.repository.GigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchGigsUseCaseImplTest {

    @Mock
    private GigRepository gigRepository;

    @InjectMocks
    private SearchGigsUseCaseImpl searchGigsUseCase;

    @Test
    void shouldSearchActiveGigsByKeywordAndCategory() {
        when(gigRepository.searchActiveGigs("Spring Boot", "Programming & Tech"))
                .thenReturn(List.of(GigTestFixtures.createValidGig()));

        List<GigResult> results = searchGigsUseCase.execute(
                new SearchGigsCommand("Spring Boot", "Programming & Tech")
        );

        assertEquals(1, results.size());
        assertEquals(GigTestFixtures.DEFAULT_GIG_ID, results.getFirst().id());
        verify(gigRepository).searchActiveGigs("Spring Boot", "Programming & Tech");
    }
}
