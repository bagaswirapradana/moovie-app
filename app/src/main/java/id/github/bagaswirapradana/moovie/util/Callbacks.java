package id.github.bagaswirapradana.moovie.util;

/**
 * Created by bagaswirapradana on 08/03/19.
 */
public interface Callbacks {
    void callbackObserver(Object obj);

    interface playerCallBack {
        void onItemClickOnItem(Integer albumId);

        void onPlayingEnd();
    }

}
