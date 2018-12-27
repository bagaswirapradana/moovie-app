package id.github.bagaswirapradana.moovie.fragments.feed;

import id.github.bagaswirapradana.moovie.model.Feed;

public interface IFeedView {
    void showShimmer();
    void hideShimmer();
    void showToast(String s);
    void displayError(String e);
    void displayFeed(Feed feed);
}
