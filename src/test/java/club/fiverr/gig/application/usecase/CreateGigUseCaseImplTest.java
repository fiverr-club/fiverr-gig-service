package club.fiverr.gig.application.usecase;

import club.fiverr.gig.GigTestFixtures;
import club.fiverr.gig.application.dto.command.CreateGigCommand;
import club.fiverr.gig.application.dto.result.GigResult;
import club.fiverr.gig.application.port.out.GigEventPublisherPort;
import club.fiverr.gig.application.port.out.GigSearchIndexPort;
import club.fiverr.gig.domain.model.Gig;
import club.fiverr.gig.domain.model.GigStatus;
import club.fiverr.gig.domain.repository.GigRepository;
import club.fiverr.gig.domain.service.GigSlugService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateGigUseCaseImplTest {

    @Mock
    private GigRepository gigRepository;

    @Mock
    private GigSlugService gigSlugService;

    @Mock
    private GigSearchIndexPort gigSearchIndexPort;

    @Mock
    private GigEventPublisherPort gigEventPublisherPort;

    @InjectMocks
    private CreateGigUseCaseImpl createGigUseCase;

    @Test
    void shouldSuccessfullyCreateGigWithThreeTiersAndPublishEvents() {
        // Given
        CreateGigCommand command = GigTestFixtures.createValidCreateGigCommand();
        when(gigSlugService.generateUniqueSlug(any())).thenReturn("i-will-build-a-production-spring-boot-microservice");
        when(gigRepository.save(any(Gig.class))).thenAnswer(inv -> inv.getArgument(0));

        // When
        GigResult result = createGigUseCase.execute(command);

        // Then
        assertNotNull(result);
        assertEquals("i-will-build-a-production-spring-boot-microservice", result.slug());
        assertEquals(GigStatus.ACTIVE, result.status());
        assertEquals(3, result.packages().size());
        verify(gigRepository).save(any(Gig.class));
        verify(gigSearchIndexPort).indexGig(any(Gig.class));
        verify(gigEventPublisherPort).publishGigCreated(any(Gig.class));
    }
}
