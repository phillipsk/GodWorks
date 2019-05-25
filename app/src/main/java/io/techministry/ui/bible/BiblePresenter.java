package io.techministry.ui.bible;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.techministry.BibleBooksRepository;
import io.techministry.db.entity.BookEntity;
import io.techministry.network.BibleApi;
import io.techministry.network.BibleBook;
import io.techministry.network.BibleRepo;
import io.techministry.network.BooksResponse;
import io.techministry.network.ObservableHelper;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;


public class BiblePresenter {

    private final BibleRepo bibleRepo;
    private BibleScreen bibleScreen;
    private CompositeDisposable compositeDisposable;
    private BooksResponse booksResponse;

    private ObservableHelper observableHelper;
    private BibleBooksRepository mBibleBooksRepository;



    public BiblePresenter(Gson gson, BibleApi bibleApi, File cacheDir) {
        this.bibleRepo = new BibleRepo(gson, bibleApi, cacheDir);
        this.compositeDisposable = new CompositeDisposable();
//        Refactor below
//        this.observableHelper = observableHelper;
//        mBibleBooksRepository = new BibleBooksRepository(application);
    }

    public BiblePresenter(Application application, ObservableHelper observableHelper) {
        this.observableHelper = observableHelper;
        mBibleBooksRepository = new BibleBooksRepository(application);
        bibleRepo = null;
    }

    public void bind(BibleScreen bibleScreen) {
        this.bibleScreen = bibleScreen;
    }

    public void getBibBook(String bibleVersion){
        String bibID = "de4e12af7f28f599-02";
        Log.v("Verbose Tag","okhttp -> Begin API request");
        Log.d("okhttp Tag","okhttp -> Begin API request");
        Observable<BooksResponse> getBibBooks = observableHelper.getBibBooks(bibID);
        getBibBooks.subscribeOn(rx.schedulers.Schedulers.io())
                .retry(1)
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends BooksResponse>>() {
                    @Override
                    public Observable<? extends BooksResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<BooksResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.v("okhttp Tag","onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("okhttp Tag","onError");

                    }

                    @Override
                    public void onNext(BooksResponse bibleBook) {
//                      if (articlesWrapper.getArticles().size() == 0) {
                        if (bibleBook.getBooks().size() == 0){
                            Log.e("getBooks","get books is 0");
//                            Toast.makeText(,"Get Books = 0",Toast.LENGTH_LONG).show();
//                            itemView.setOnClickListener(v -> Toast.makeText(v.getContext(),"Clicked on " + chapter.getId(),Toast.LENGTH_LONG).show());
                        }else {
                            Log.d("getBooks","get books is NOT <> 0");
                            List<BibleBook> bibleBookList = new ArrayList<>();
                            BibleBook books = new BibleBook();
                            BookEntity bookEntity;

                            mBibleBooksRepository.deleteBibleBooks();

                            for (int i = 0; i < bibleBook.getBooks().size(); i++){
//                                if ()
                                bibleBookList.add(bibleBook.getBooks().get(i));
                                bookEntity = new BookEntity(bibleBook.getBooks().get(i).getId(),
                                        bibleBook.getBooks().get(i).getBookId(),
                                        bibleBook.getBooks().get(i).getName(),
                                        bibleBook.getBooks().get(i).getNameLong());

                                mBibleBooksRepository.insertBibleBooks(bookEntity);
//                                Timber.d("TopHeadlines successfully added to database");
                                Log.d("add to DB","Bible Books successfully added to database");

                            }

                            }

                    }
                });

    }
    /*
    *         Observable<ArticlesWrapper> getTopHeadlines = observableHelper.getTopHeadlines(countryCode,
                sources, category, query, page);
                */


    public void fetchBibleBooks(String bibleId) {
        compositeDisposable.add(
                bibleRepo.getBibleBook(bibleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(booksResponse ->
                                bibleScreen.onNewBibleBooks(
                                        booksResponse.books),
                        throwable -> Log.e("TEST", "Error in fetch Bible books", throwable)));
    }

    public void zfetchBibleBooks(String bibleId) {
        boolean add = compositeDisposable.add(bibleRepo.zfetchBibleBooks(bibleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BooksResponse>() {
                               @Override
                               public void accept(BooksResponse booksResponse) throws Exception {
                                   bibleScreen.onNewBibleBooks(booksResponse.books);
                               }
                           },
                        throwable -> Log.e("TEST", "Error in fetch Bible books", throwable)));
//        compositeDisposable.add(bibleApi.getBibleBooks(bibleId).subscribeOn(Scheduler.))
    }



    public void unbind() {
        compositeDisposable.clear();
    }

}
