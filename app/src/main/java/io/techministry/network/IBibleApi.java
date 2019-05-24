package io.techministry.network;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface IBibleApi {


    @GET("/v1/bibles/{bible_id}/books")
    Observable<BibleBook> fetchBibBooks(@Path("bible_id") String bibleId);

}
