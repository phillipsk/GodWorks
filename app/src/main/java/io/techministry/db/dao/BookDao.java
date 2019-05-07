package io.techministry.db.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.techministry.db.BibleDatabase;

@Dao
public interface BookDao {

    @Query("SELECT * FROM books")
    LiveData<List<BibleDatabase>> getAll();

    @Insert
    void insertAll(BibleDatabase... pBooks);

    @Query("DELETE FROM books")
    void deleteAll();
}
