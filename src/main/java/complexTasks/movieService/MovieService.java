package complexTasks.movieService;

import java.util.*;
import java.util.stream.Collectors;

public class MovieService<T extends Number> {

    protected HashMap<Movie, List<Rating>> movieRatingList = new HashMap<>();

    public Map<Movie, List<Rating>> getMovieRatingList() {
        return movieRatingList;
    }

    public synchronized boolean addRatingToTheMovie(String movieName, T movieRating) {
        if (movieRating.doubleValue() > 10 || movieRating.doubleValue() < 1)
            throw new RatingIsOutOfBoundsException("Incorrect rating");
        else {
            Movie movie = new Movie(movieName);
            Rating rating = new Rating(movieRating);
            List<Rating> ratings = movieRatingList.getOrDefault(movie, new ArrayList<>());
            ratings.add(rating);
            movieRatingList.put(movie, ratings);
            return true;
        }
    }

    public Map<Movie, Double> averageRatingForEachFilm() {
        return movieRatingList.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToDouble(r -> r.getRating().doubleValue())
                                .average()
                                .orElse(0.0)
                ));
    }

    public LinkedHashMap<Movie, Double> sortByAverage() {
        return averageRatingForEachFilm().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
