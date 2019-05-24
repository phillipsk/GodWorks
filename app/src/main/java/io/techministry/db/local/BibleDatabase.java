package io.techministry.db.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import io.techministry.db.dao.BookDao;
import io.techministry.db.dao.ChapterDao;
import io.techministry.db.entity.BookEntity;
import io.techministry.db.entity.ChapterEntity;

@Database(entities = {BookEntity.class, ChapterEntity.class}, version = 1)
public abstract class BibleDatabase extends RoomDatabase {

    public abstract BookDao bookDao();
    public abstract ChapterDao chapterDao();

    private static BibleDatabase INSTANCE;
/*
    public BibleDatabase() {
    }

    public static BibleDatabase getInstance(){
        if (instance == null) {
            instance = new BibleDatabase();
        }
        return instance;
    }*/


    public static BibleDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BibleDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    BibleDatabase.class, "bible.db")
                                    .fallbackToDestructiveMigration()
                                    .build();
                }
            }
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
