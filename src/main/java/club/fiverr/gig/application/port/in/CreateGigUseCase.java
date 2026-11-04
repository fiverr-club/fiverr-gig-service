package club.fiverr.gig.application.port.in;

import club.fiverr.gig.application.dto.command.CreateGigCommand;
import club.fiverr.gig.application.dto.result.GigResult;

public interface CreateGigUseCase {

    GigResult execute(CreateGigCommand command);
}
