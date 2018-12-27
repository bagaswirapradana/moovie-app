package id.github.bagaswirapradana.moovie.network;

import id.github.bagaswirapradana.moovie.model.DetailMovie;
import id.github.bagaswirapradana.moovie.model.Feed;
import id.github.bagaswirapradana.moovie.model.PopularMovie;
import id.github.bagaswirapradana.moovie.model.SimilarMovie;
import id.github.bagaswirapradana.moovie.model.TopRatedMovie;
import id.github.bagaswirapradana.moovie.model.UpComingMovie;
import id.github.bagaswirapradana.moovie.model.Videos;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("movie/{movie_id}/similar")
    Observable<SimilarMovie> getSimilarMovie(@Path("movie_id") String movie_id, @Query("api_key")String api_key, @Query("language")String language);

    @GET("movie/{movie_id}")
    Observable<DetailMovie> getDetailMovie(@Path("movie_id") String movie_id, @Query("api_key")String api_key, @Query("language")String language, @Query("append_to_response")String response);

    @GET("movie/{movie_id}/videos")
    Observable<Videos> getVideosTrailer(@Path("movie_id") String movie_id, @Query("api_key")String api_key);

    @GET("trending/all/day")
    Observable<Feed> getDataFeed(@Query("api_key") String api_key);

    @GET("movie/popular")
    Observable<PopularMovie> getDataPopularMovie(@Query("api_key") String api_key, @Query("language") String language);

    @GET("movie/top_rated")
    Observable<TopRatedMovie> getDataTopRatedMovie(@Query("api_key") String api_key, @Query("language") String language);

    @GET("movie/upcoming")
    Observable<UpComingMovie> getDataUpComingMovie(@Query("api_key") String api_key, @Query("language") String language);
}
