package vizsgaremek.coordinate;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CoordinateNotFoundException extends AbstractThrowableProblem {
    public CoordinateNotFoundException(Long id) {
        super(
                URI.create("coordinate/not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Coordinate not found: %d", id)
        );
    }
}
