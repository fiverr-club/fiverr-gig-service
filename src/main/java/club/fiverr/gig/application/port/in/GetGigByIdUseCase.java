package club.fiverr.gig.application.port.in;

import club.fiverr.gig.application.dto.result.GigResult;

import java.util.UUID;

public interface GetGigByIdUseCase {

    GigResult execute(UUID gigId);
}
