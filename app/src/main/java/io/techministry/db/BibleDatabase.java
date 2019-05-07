package io.techministry.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import io.techministry.db.dao.BookDao;
import io.techministry.db.dao.ChapterDao;
import io.techministry.db.entity.BookEntity;
import io.techministry.db.entity.ChapterEntity;

@Database(entities = {BookEntity.class, ChapterEntity.class}, version = 1)
public abstract class BibleDatabase extends RoomDatabase {

    public abstract BookDao bookDao();
    public abstract ChapterDao chapterDao();


}
