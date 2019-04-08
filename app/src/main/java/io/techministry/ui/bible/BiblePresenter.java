package io.techministry.ui.bible;

import android.util.Log;

import com.google.gson.Gson;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.techministry.network.BibleApi;
import io.techministry.network.BibleRepo;

public class BiblePresenter {

    private final BibleRepo bibleRepo;
    private BibleScreen bibleScreen;
    private CompositeDisposable compositeDisposable;

    public BiblePresenter(Gson gson, BibleApi bibleApi, File cacheDir) {
        this.bibleRepo = new BibleRepo(gson, bibleApi, cacheDir);
        this.compositeDisposable = new CompositeDisposable();
    }

    public void bind(BibleScreen bibleScreen) {
        this.bibleScreen = bibleScreen;
    }

    public void fetchBibleBooks(String bibleId) {
        compositeDisposable.add(
                bibleRepo.getBibleBook(bibleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(booksResponse ->
                                bibleScreen.onNewBibleBooks(
                                        booksResponse.data),
                        throwable -> Log.e("TEST", "Error in fetch Bible books", throwable)));
    }

    public void unbind() {
        compositeDisposable.clear();
    }

}
