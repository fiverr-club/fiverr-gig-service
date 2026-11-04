package club.fiverr.gig.presentation.rest;

import club.fiverr.gig.application.dto.command.SearchGigsCommand;
import club.fiverr.gig.application.dto.result.GigResult;
import club.fiverr.gig.application.port.in.CreateGigUseCase;
import club.fiverr.gig.application.port.in.GetGigByIdUseCase;
import club.fiverr.gig.application.port.in.SearchGigsUseCase;
import club.fiverr.gig.presentation.rest.mapper.GigRestMapper;
import club.fiverr.gig.presentation.rest.request.CreateGigRequest;
import club.fiverr.gig.presentation.rest.response.GigResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/gigs")
public class GigController {

    private final CreateGigUseCase createGigUseCase;
    private final GetGigByIdUseCase getGigByIdUseCase;
    private final SearchGigsUseCase searchGigsUseCase;

    public GigController(
            CreateGigUseCase createGigUseCase,
            GetGigByIdUseCase getGigByIdUseCase,
            SearchGigsUseCase searchGigsUseCase
    ) {
        this.createGigUseCase = createGigUseCase;
        this.getGigByIdUseCase = getGigByIdUseCase;
        this.searchGigsUseCase = searchGigsUseCase;
    }

    @PostMapping
    public ResponseEntity<GigResponse> createGig(@Valid @RequestBody CreateGigRequest request) {
        GigResult result = createGigUseCase.execute(GigRestMapper.toCommand(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(GigRestMapper.toResponse(result));
    }

    @GetMapping("/{gigId}")
    public ResponseEntity<GigResponse> getGigById(@PathVariable UUID gigId) {
        GigResult result = getGigByIdUseCase.execute(gigId);
        return ResponseEntity.ok(GigRestMapper.toResponse(result));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GigResponse>> searchGigs(
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "") String category
    ) {
        List<GigResponse> responses = searchGigsUseCase.execute(new SearchGigsCommand(keyword, category))
                .stream()
                .map(GigRestMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }
}
