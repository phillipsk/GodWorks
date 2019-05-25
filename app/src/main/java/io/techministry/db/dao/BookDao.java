package io.techministry.db.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.techministry.db.entity.BookEntity;

@Dao
public interface BookDao {

    @Query("SELECT * FROM books")
    List<BookEntity> getAll();
//    LiveData<List<BibleDatabase>> getAll();

    @Insert
    void insertAll(BookEntity... books);

    @Query("DELETE FROM books")
    void deleteAll();
}
