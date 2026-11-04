package club.fiverr.gig.domain.exception;

import java.util.UUID;

public class GigNotFoundException extends RuntimeException {

    public GigNotFoundException(UUID gigId) {
        super("Gig not found with id: " + gigId);
    }

    public GigNotFoundException(String message) {
        super(message);
    }
}
