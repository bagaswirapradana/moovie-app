package id.github.bagaswirapradana.moovie.activities.detail;

public interface IDetailPresenter{
    void getDetailMovieData(String id);
    void getSimilarMovieData(String id);
    void onDestroy();
    void getTrailerMovie(String id);
}
