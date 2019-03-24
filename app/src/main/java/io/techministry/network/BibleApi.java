package io.techministry.network;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BibleApi {

    // https://api.scripture.api.bible/v1/bibles/de4e12af7f28f599-01/books/RUT/chapters
//    @GET("/v1/bibles/{bible_id}/books/RUT/chapters")
//    Observable<BooksResponse> getBibleBooks(@Path("bible_id") String bibleId);

    // @GET("/v1/bibles/{bible_id}/books")
    // Observable<BooksResponse> getBibleBooks(@Path("bible_id") String bibleId);

    @GET("/v1/bibles/{bible_id}/books")
    Single<ResponseBody> getBibleBooksForPersister(@Path("bible_id") String bibleId);

    @GET("/v1/bibles/{bible_id}/books/{book_id}/chapters")
    Single<ResponseBody> getBibleChaptersForPersister(@Path("bible_id") String bibleId,
                                                      @Path("book_id")  String bookId);

    //    @GET("/v1/bibles/{bible_id}/books/{bookId}/chapters")
//    Observable<BooksResponse> getBibleBooks(@Path("chapter_id") String bibleId, String chapterId);

}
