package io.techministry;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import io.techministry.db.dao.BookDao;
import io.techministry.db.entity.BookEntity;
import io.techministry.db.local.BibleDatabase;

public class BibleBooksRepository {
    private BookDao mBookDao;
    private List<BookEntity> mBookEntityList;

    public BibleBooksRepository(Application application) {
        BibleDatabase bibleDatabase = BibleDatabase.getInstance(application);
        mBookDao = bibleDatabase.bookDao();

        mBookEntityList = mBookDao.getAll();
    }

    public void insertBibleBooks (BookEntity bookEntity){
        new insertBibleBooks(mBookDao).execute(bookEntity);
    }

    public List<BookEntity> getRepoBibleBooks() {
        return mBookEntityList;
    }

    public void deleteBibleBooks(){
        new deleteBibleBooks(mBookDao).execute();
    }

    private static class insertBibleBooks extends AsyncTask<BookEntity,Void,Void>{

        private BookDao mAsyncTaskDao;

        insertBibleBooks(BookDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            mAsyncTaskDao.insertAll(bookEntities[0]);
//            mAsyncTaskDao.insertAll();
            return null;
        }
    }

    public static class deleteBibleBooks extends AsyncTask<BookEntity,Void,Void>{

        private BookDao mAsyncTaskDao;

        deleteBibleBooks(BookDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
