package expressJava.complexTasks.movieService;

import java.util.Objects;

public class Movie {
    private final String movieName;

    public Movie(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return Objects.equals(movieName, movie.movieName);
    }
}
