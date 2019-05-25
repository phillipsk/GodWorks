package io.techministry.network;

import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.nytimes.android.external.fs3.FileSystemPersister;
import com.nytimes.android.external.fs3.PathResolver;
import com.nytimes.android.external.fs3.filesystem.FileSystemFactory;
import com.nytimes.android.external.store3.base.impl.Store;
import com.nytimes.android.external.store3.base.impl.StoreBuilder;
import com.nytimes.android.external.store3.middleware.GsonParserFactory;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class BibleRepo {

    private Gson gson;
    private BibleApi bibleApi;
    private File cacheDir;

    public BibleRepo(Gson gson, BibleApi bibleApi, File cacheDir) {
        this.gson = gson;
        this.bibleApi = bibleApi;
        this.cacheDir = cacheDir;
    }
    public Observable<BooksResponse> zfetchBibleBooks(String bibleId) {
        boolean isItMainThread = Looper.myLooper() == Looper.getMainLooper();
        Log.d("TEST", "is it main thread "+ isItMainThread);
        return bibleApi.getBibleBooks(bibleId);
    }

    public Single<BooksResponse> getBibleBook(String bibleId) {
        try {
            Store<BooksResponse, String> bibleStore = StoreBuilder.<String, BufferedSource,
                    BooksResponse>parsedWithKey()
                    .fetcher(bId -> bibleApi.getBibleBooksForPersister(bId).map(ResponseBody::source))
                    .persister(FileSystemPersister.create(FileSystemFactory.create(cacheDir),
                            new PathResolver<String>() {
                        @Nonnull
                        @Override
                        public String resolve(@Nonnull String key) {
                            return "bible-"+ key;
                        }
                    }))
                    .parser(GsonParserFactory.createSourceParser(gson, BooksResponse.class))
                    .open();

            return bibleStore.fetch(bibleId);
        } catch (IOException e) {
            return Single.error(e);
        }
    }

    public Single<ChapterResponse> getBibleChapter(String bibleId, String bookId){
        try {
//            TODO: Jump to 'Store' source library. What are <T,V> types? Is there method overloading?
//            TODO: Looks like the V within <T,V> is the key written to the cache file.
            Store<ChapterResponse, String> chapterResponseStringStore = StoreBuilder.<String,
                    BufferedSource, ChapterResponse>parsedWithKey()
                    .fetcher(bId -> bibleApi.getBibleChaptersForPersister(bId,bookId)
                            .map(ResponseBody::source))
                    .persister(FileSystemPersister.create(FileSystemFactory.create(cacheDir),
                            new PathResolver<String>() {
                            @Nonnull
                            @Override
                        public String resolve(@Nonnull String key){
                            return bibleId + "-" + "chapter" + "-" + bookId;
                        }
                    }))
                    .parser(GsonParserFactory.createSourceParser(gson,ChapterResponse.class))
                    .open();
            Single<ChapterResponse> single = chapterResponseStringStore.fetch(bibleId);

            return single.doOnSuccess(chapterResponse -> Log.i("TEST", "Single emitting " + chapterResponse));  // bookid
        }catch (IOException e){
            return Single.error(e);
        }
    }
//    TODO: How may I evaluate 'bId' i.e. ?typename(bId)

}
