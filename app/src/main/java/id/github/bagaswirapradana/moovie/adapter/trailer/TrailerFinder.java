package id.github.bagaswirapradana.moovie.adapter.trailer;

import id.github.bagaswirapradana.moovie.activities.detail.IDetailMovieView;
import id.github.bagaswirapradana.moovie.model.VideosList;
import java.util.List;

/**
 * Created by bagaswirapradana on 08/03/19.
 */
public interface TrailerFinder {
    void bindTrailerMovies(List<VideosList> videosLists);
    void setIDetailMovie(IDetailMovieView iDetailMovie);
}
