package io.techministry.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class BookEntity {

    /*      API Response
  "id": "EXO",
  "bibleId": "de4e12af7f28f599-01",
  "abbreviation": "Exo",
  "name": "Exodus",
  "nameLong": "The Second Book of Moses, called Exodus"
  */
    @PrimaryKey(autoGenerate = true)
    private int keyId;
    private String id;
    private String abbreviation;
    private String name;
    private String nameLong;

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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLong() {
        return nameLong;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }
}
