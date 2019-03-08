package id.github.bagaswirapradana.moovie.fragments.feed;

public interface IFeedPresenter {
    void setIFeedView(IFeedView iFeedView);
    void getFeedData();
    void onDestroy();
}
