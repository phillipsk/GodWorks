package io.techministry.ui.bible;

import android.os.Looper;
import android.util.Log;

import io.reactivex.Observable;
import io.techministry.network.BibleApi;
import io.techministry.network.BooksResponse;

public class BibleRepo {

    private BibleApi bibleApi;

    public BibleRepo(BibleApi bibleApi) {
        this.bibleApi = bibleApi;
    }

    public Observable<BooksResponse> fetchBooks(String bibleId) {
        boolean isItMainThread = Looper.myLooper() == Looper.getMainLooper();
        Log.d("TEST", "is it main thread "+ isItMainThread);
        return bibleApi.getBibleBooks(bibleId);
    }
}
