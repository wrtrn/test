package learning.expressJava.complexTasks.movieService;

public class RatingIsOutOfBoundsException extends RuntimeException {
    public RatingIsOutOfBoundsException(String message) {
        super(message);
    }
}
