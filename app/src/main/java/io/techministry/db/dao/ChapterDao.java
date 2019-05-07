package io.techministry.db.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.techministry.db.BibleDatabase;

@Dao
public interface ChapterDao {

    @Query("SELECT * FROM chapters")
    LiveData<List<BibleDatabase>> getAll();

    @Insert
    void insertAll(BibleDatabase... pChapters);

    @Query("DELETE FROM chapters")
    void deleteAll();
}
