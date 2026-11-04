package club.fiverr.gig.application.usecase;

import club.fiverr.gig.application.dto.result.GigResult;
import club.fiverr.gig.application.mapper.GigResultMapper;
import club.fiverr.gig.application.port.in.GetGigByIdUseCase;
import club.fiverr.gig.domain.exception.GigNotFoundException;
import club.fiverr.gig.domain.model.Gig;
import club.fiverr.gig.domain.repository.GigRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetGigByIdUseCaseImpl implements GetGigByIdUseCase {

    private final GigRepository gigRepository;

    public GetGigByIdUseCaseImpl(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public GigResult execute(UUID gigId) {
        Gig gig = gigRepository.findById(gigId)
                .orElseThrow(() -> new GigNotFoundException(gigId));
        return GigResultMapper.toResult(gig);
    }
}
