package io.techministry.db.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.techministry.db.entity.ChapterEntity;

@Dao
public interface ChapterDao {

    @Query("SELECT * FROM chapters")
    List<ChapterEntity> getAll();
//    LiveData<List<BibleDatabase>> getAll();

    @Insert
    void insertAll(ChapterEntity... chapters);

    @Query("DELETE FROM chapters")
    void deleteAll();
}
