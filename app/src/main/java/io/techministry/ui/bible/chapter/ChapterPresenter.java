package io.techministry.ui.bible.chapter;

import android.util.Log;

import com.google.gson.Gson;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.techministry.network.BibleApi;
import io.techministry.network.BibleRepo;

class ChapterPresenter {

    private final BibleRepo bibleRepo;
    private ChapterScreen chapterScreen;
    private CompositeDisposable compositeDisposable;

    ChapterPresenter(Gson gson, BibleApi bibleApi, File cacheDir) {
        this.bibleRepo = new BibleRepo(gson, bibleApi, cacheDir);
        this.compositeDisposable = new CompositeDisposable();
    }

/*  // Default Constructor
    ChapterPresenter(BibleRepo bibleRepo, ChapterScreen chapterScreen, CompositeDisposable compositeDisposable) {
        this.bibleRepo = bibleRepo;
        this.chapterScreen = chapterScreen;
        this.compositeDisposable = compositeDisposable;
    }*/

    //    TODO: Why is this necessary? How Does Android OS bind?
    public void bind(ChapterScreen chapterScreen) {
        this.chapterScreen = chapterScreen;
    }

    void fetchBibleChapters(String bibleId, String bookId) {
        compositeDisposable.add(
                bibleRepo.getBibleChapter(bibleId, bookId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(chapterResponse -> chapterScreen.onNewBibleChapters(chapterResponse.chapterList),
                                throwable -> Log.e("TEST", "Error in fetch Chapters", throwable)
                        ));
    }

    void unbind() {
        compositeDisposable.clear();
    }
}

