package complexTasks;

import complexTasks.movieService.Movie;
import complexTasks.movieService.MovieService;
import complexTasks.movieService.Rating;
import complexTasks.movieService.RatingIsOutOfBoundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieServiceTest {

    @Test
    public void addRatingToTheMovieTest() throws InterruptedException {
        MovieService movieService = new MovieService<>();

        movieService.addRatingToTheMovie("Привет", 8);

        Assertions.assertEquals(movieService.getMovieRatingList().size(), 1);

        movieService.addRatingToTheMovie("Привет", 10);

        Assertions.assertEquals(movieService.getMovieRatingList().size(), 1);

        movieService.addRatingToTheMovie("Hello", 9.2);
        movieService.addRatingToTheMovie("Hello", 5);
        movieService.addRatingToTheMovie("Hello", 3.4);

        Assertions.assertEquals(movieService.getMovieRatingList().size(), 2);

        movieService.addRatingToTheMovie("Пока", 2);
        movieService.addRatingToTheMovie("Hello", 2);
        movieService.addRatingToTheMovie("Hello", 1);

        Assertions.assertEquals(movieService.getMovieRatingList().size(), 3);
        Assertions.assertTrue(movieService.getMovieRatingList().containsKey(new Movie("Привет")));
        Assertions.assertTrue(movieService.getMovieRatingList().containsKey(new Movie("Hello")));
        Assertions.assertTrue(movieService.getMovieRatingList().containsKey(new Movie("Пока")));

        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(9.2));
        ratings.add(new Rating(5));
        ratings.add(new Rating(3.4));
        ratings.add(new Rating(2));
        ratings.add(new Rating(1));

        Assertions.assertEquals(ratings, movieService.getMovieRatingList().get(new Movie("Hello")));

        Thread t1 = new Thread(() -> movieService.addRatingToTheMovie("Jaso", 5.5));
        Thread t2 = new Thread(() -> movieService.addRatingToTheMovie("Jaso", 5.5));
        Thread t3 = new Thread(() -> movieService.addRatingToTheMovie("Jaso", 5.5));
        Thread t4 = new Thread(() -> movieService.addRatingToTheMovie("Jaso", 5.5));
        Thread t5 = new Thread(() -> movieService.addRatingToTheMovie("Jaso", 5.5));
        Thread t6 = new Thread(() -> movieService.addRatingToTheMovie("Jaso", 5.5));
        Thread t7 = new Thread(() -> movieService.addRatingToTheMovie("Jaso", 5.5));
        Thread t8 = new Thread(() -> movieService.addRatingToTheMovie("Jaso", 5.5));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();

        List<Rating> jasoRatings = (List<Rating>) movieService.getMovieRatingList().get(new Movie("Jaso"));
        Assertions.assertEquals(8, jasoRatings.size());

        Assertions.assertThrows(RatingIsOutOfBoundsException.class, () -> movieService.addRatingToTheMovie("Hello", 10.1));
        Assertions.assertThrows(RatingIsOutOfBoundsException.class, () -> movieService.addRatingToTheMovie("Hello", 0.9));
        Assertions.assertThrows(RatingIsOutOfBoundsException.class, () -> movieService.addRatingToTheMovie("Hello", -0.9));
    }

    @Test
    public void averageRatingForEachFilmTest() {
        MovieService movieService = new MovieService<>();

        movieService.addRatingToTheMovie("Привет1", 8);
        movieService.addRatingToTheMovie("Привет1", 9.1);
        movieService.addRatingToTheMovie("Привет1", 2);

        movieService.addRatingToTheMovie("Привет2", 3);
        movieService.addRatingToTheMovie("Привет2", 8);
        movieService.addRatingToTheMovie("Привет2", 4);
        movieService.addRatingToTheMovie("Привет2", 5);
        movieService.addRatingToTheMovie("Привет2", 2.2);

        movieService.addRatingToTheMovie("Привет3", 5.5);

        var result1 = (double) movieService.averageRatingForEachFilm().get(new Movie("Привет1"));
        var result2 = (double) movieService.averageRatingForEachFilm().get(new Movie("Привет2"));
        var result3 = (double) movieService.averageRatingForEachFilm().get(new Movie("Привет3"));
        var result4 = movieService.averageRatingForEachFilm().get(new Movie("Привет4"));

        result1 = new BigDecimal(result1).setScale(2, RoundingMode.HALF_UP).doubleValue();
        result2 = new BigDecimal(result2).setScale(2, RoundingMode.HALF_UP).doubleValue();
        result3 = new BigDecimal(result3).setScale(2, RoundingMode.HALF_UP).doubleValue();

        Assertions.assertEquals(6.37, result1);
        Assertions.assertEquals(4.44, result2);
        Assertions.assertEquals(5.5, result3);
        Assertions.assertEquals(null, result4);
    }

    @Test
    public void sortByAverageTest() {
        MovieService movieService = new MovieService<>();

        movieService.addRatingToTheMovie("Привет1", 8);
        movieService.addRatingToTheMovie("Привет1", 9.1);
        movieService.addRatingToTheMovie("Привет1", 2);

        movieService.addRatingToTheMovie("Привет2", 3);
        movieService.addRatingToTheMovie("Привет2", 8);
        movieService.addRatingToTheMovie("Привет2", 4);
        movieService.addRatingToTheMovie("Привет2", 5);
        movieService.addRatingToTheMovie("Привет2", 2.2);

        movieService.addRatingToTheMovie("Привет3", 5.5);

        Map<Movie, Double> sortedMovies = movieService.sortByAverage();
        List<Map.Entry<Movie, Double>> entries = new ArrayList<>(sortedMovies.entrySet());

        Assertions.assertTrue(entries.get(0).getKey().equals(new Movie("Привет2")));
        Assertions.assertTrue(entries.get(1).getKey().equals(new Movie("Привет3")));
        Assertions.assertTrue(entries.get(2).getKey().equals(new Movie("Привет1")));
        Assertions.assertEquals(3, sortedMovies.size());
    }
}
