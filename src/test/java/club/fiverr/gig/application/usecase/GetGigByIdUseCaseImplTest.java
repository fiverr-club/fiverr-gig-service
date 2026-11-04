package club.fiverr.gig.application.usecase;

import club.fiverr.gig.GigTestFixtures;
import club.fiverr.gig.application.dto.result.GigResult;
import club.fiverr.gig.domain.exception.GigNotFoundException;
import club.fiverr.gig.domain.model.Gig;
import club.fiverr.gig.domain.repository.GigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGigByIdUseCaseImplTest {

    @Mock
    private GigRepository gigRepository;

    @InjectMocks
    private GetGigByIdUseCaseImpl getGigByIdUseCase;

    @Test
    void shouldReturnGigResultWhenFound() {
        Gig gig = GigTestFixtures.createValidGig();
        when(gigRepository.findById(GigTestFixtures.DEFAULT_GIG_ID)).thenReturn(Optional.of(gig));

        GigResult result = getGigByIdUseCase.execute(GigTestFixtures.DEFAULT_GIG_ID);

        assertNotNull(result);
        assertEquals(GigTestFixtures.DEFAULT_GIG_ID, result.id());
        assertEquals(gig.getSlug(), result.slug());
    }

    @Test
    void shouldThrowGigNotFoundExceptionWhenMissing() {
        UUID missingId = UUID.randomUUID();
        when(gigRepository.findById(missingId)).thenReturn(Optional.empty());

        assertThrows(GigNotFoundException.class, () -> getGigByIdUseCase.execute(missingId));
    }
}
