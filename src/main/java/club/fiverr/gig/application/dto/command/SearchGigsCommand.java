package club.fiverr.gig.application.dto.command;

public record SearchGigsCommand(
        String keyword,
        String category
) {
}
