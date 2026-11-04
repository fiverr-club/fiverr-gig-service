package club.fiverr.gig.application.port.in;

import club.fiverr.gig.application.dto.command.SearchGigsCommand;
import club.fiverr.gig.application.dto.result.GigResult;

import java.util.List;

public interface SearchGigsUseCase {

    List<GigResult> execute(SearchGigsCommand command);
}
