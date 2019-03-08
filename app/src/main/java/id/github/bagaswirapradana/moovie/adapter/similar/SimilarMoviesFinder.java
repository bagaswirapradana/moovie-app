package id.github.bagaswirapradana.moovie.adapter.similar;

import id.github.bagaswirapradana.moovie.model.SimilarMovieList;
import java.util.List;

/**
 * Created by bagaswirapradana on 06/03/19.
 */
public interface SimilarMoviesFinder {
    void bindSimilarMovies(List<SimilarMovieList> similarMovieLists);
}
