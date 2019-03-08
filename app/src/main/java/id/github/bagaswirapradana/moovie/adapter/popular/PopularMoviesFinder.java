package id.github.bagaswirapradana.moovie.adapter.popular;

import id.github.bagaswirapradana.moovie.model.PopularMovieList;
import java.util.List;

/**
 * Created by bagaswirapradana on 06/03/19.
 */
public interface PopularMoviesFinder {
    void bindPopularMovies(List<PopularMovieList> movieList);
}
