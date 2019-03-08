package id.github.bagaswirapradana.moovie.fragments.home;

import id.github.bagaswirapradana.moovie.model.PopularMovie;
import id.github.bagaswirapradana.moovie.model.TopRatedMovie;
import id.github.bagaswirapradana.moovie.model.UpComingMovie;

public interface IHomeView {
    void showToast(String s);
    void displayMoviesPopular(PopularMovie popularMovie);
    void displayMoviesTopRated(TopRatedMovie topRatedMovie);
    void displayMovieUpComing(UpComingMovie upComingMovie);
    void displayError(String s);
    void showShimmer();
    void hideShimmer();
    void initViews();
}
