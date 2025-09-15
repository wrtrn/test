package complexTasks.movieService;

import java.util.Objects;

public class Rating<T extends Number> {
    private final T rating;

    public T getRating() {
        return rating;
    }

    public Rating(T rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rating<?> rating1 = (Rating<?>) o;
        return Objects.equals(rating, rating1.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rating);
    }
}
