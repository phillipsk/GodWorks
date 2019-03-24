package io.techministry.ui.bible.chapter;

import io.reactivex.disposables.CompositeDisposable;
import io.techministry.network.BibleRepo;

class ChapterPresenter {

    private final BibleRepo bibleRepo;
    private ChapterScreen chapterScreen;
    private CompositeDisposable compositeDisposable;


    ChapterPresenter(BibleRepo bibleRepo, ChapterScreen chapterScreen, CompositeDisposable compositeDisposable) {
        this.bibleRepo = bibleRepo;
        this.chapterScreen = chapterScreen;
        this.compositeDisposable = compositeDisposable;
    }

//    TODO: Why is this necessary? How Does Android OS bind?
    public void bind(ChapterScreen chapterScreen){
        this.chapterScreen = chapterScreen;
    }

    void fetchBibleChapters(String bibleId){
        compositeDisposable.add(bibleRepo.get)
    }
}

