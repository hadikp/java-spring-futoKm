package vizsgaremek.training;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class TrainingNotFoundException extends AbstractThrowableProblem {

    public TrainingNotFoundException(Long id) {
        super(
                URI.create("training/not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Training not found: %d", id)
        );
    }
}
