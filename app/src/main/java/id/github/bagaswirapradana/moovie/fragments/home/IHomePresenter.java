package id.github.bagaswirapradana.moovie.fragments.home;

public interface IHomePresenter {
    void getAllData();
    void getMoviesPopular();
    void getMoviesTopRated();
    void getMoviesUpComing();
    void onDestroy();
}
