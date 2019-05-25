package io.techministry.network;

import io.techministry.BuildConfig;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

public interface IBibleApi {

    @Headers("api-key: " + BuildConfig.API_KEY_BIBLE)
    @GET("/v1/bibles/{bible_id}/books")
    Observable<BooksResponse> fetchBibBooks(@Path("bible_id") String bibleId);

}
