package id.github.bagaswirapradana.moovie.adapter.rated;

import id.github.bagaswirapradana.moovie.model.TopRatedMovieList;
import java.util.List;

/**
 * Created by bagaswirapradana on 06/03/19.
 */
public interface TopRatedMoviesFinder {
    void bindTopRatedMovies(List<TopRatedMovieList> topRatedMovieLists);
}
