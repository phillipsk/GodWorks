package io.techministry.db.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chapters")
public class ChapterEntity {

    /* API Response
      "id": "RUT.1",
      "bibleId": "de4e12af7f28f599-02",
      "bookId": "RUT",
      "number": "1",
      "reference": "Ruth 1"
      */
    @PrimaryKey(autoGenerate = true)
    private int keyId;
    private String id;
    private String bookId;
    private String number;
    private String reference;

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
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
