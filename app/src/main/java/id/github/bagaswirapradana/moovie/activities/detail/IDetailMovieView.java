package id.github.bagaswirapradana.moovie.activities.detail;

import id.github.bagaswirapradana.moovie.model.DetailMovie;
import id.github.bagaswirapradana.moovie.model.SimilarMovie;
import id.github.bagaswirapradana.moovie.model.Videos;

public interface IDetailMovieView {
    void initViews();
    void setTrailerPlayer(String key);
    void onTrailerClicked(String key);
    void setDetailData(DetailMovie detailMovie);
    void setSimilarMovieData(SimilarMovie similarMovie);
    void setTrailerMovieData(Videos videos);
}
