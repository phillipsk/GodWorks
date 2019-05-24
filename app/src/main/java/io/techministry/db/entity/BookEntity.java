package io.techministry.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* API Response
"id": "EXO",
"bibleId": "de4e12af7f28f599-01",
"abbreviation": "Exo",
"name": "Exodus",
"nameLong": "The Second Book of Moses, called Exodus"
*/

@Entity(tableName = "books")
public class BookEntity {

//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo
//    private int keyId;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "abbreviation")
    private String abbreviation;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "nameLong")
    private String nameLong;

    public BookEntity(String id, String abbreviation, String name, String nameLong) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.nameLong = nameLong;
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
