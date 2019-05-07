package io.techministry.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* API Response
  "id": "RUT.1",
  "bibleId": "de4e12af7f28f599-02",
  "bookId": "RUT",
  "number": "1",
  "reference": "Ruth 1"
  */

@Entity(tableName = "chapters")
public class ChapterEntity {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "bookId")
    private String bookId;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "reference")
    private String reference;

    public ChapterEntity(String id, String bookId, String number, String reference) {
        this.id = id;
        this.bookId = bookId;
        this.number = number;
        this.reference = reference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
