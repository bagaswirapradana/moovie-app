package id.github.bagaswirapradana.moovie.fragments.home;

public interface IHomePresenter {
    void setIHomeView(IHomeView iHomeView);
    void getAllData();
    void getMoviesPopular();
    void getMoviesTopRated();
    void getMoviesUpComing();
    void onDestroy();
}
