package vizsgaremek.trackpoint;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class TrackPointNotFoundException extends AbstractThrowableProblem {

    public TrackPointNotFoundException(Long id) {
        super(
                URI.create("trackpoint/not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("TrackPoint not found: %d", id)
        );
    }
}
