package io.techministry.network;

import java.io.Serializable;
import java.util.List;

public class BibleBook implements Serializable {

    /*      "id": "EXO",
      "bibleId": "de4e12af7f28f599-01",
      "abbreviation": "Exo",
      "name": "Exodus",
      "nameLong": "The Second Book of Moses, called Exodus"
      */
    String id;
    String abbreviation;
    String name;
    String nameLong;

    private List<BibleBook> Books;

    public List<BibleBook> getBooks() {
        return Books;
    }

    public void setBooks(List<BibleBook> books) {
        Books = books;
    }

    public BibleBook() {
    }

    public BibleBook(String id, String abbreviation, String name, String nameLong) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.nameLong = nameLong;
    }

    public String getId() {
        return id;
    }

    public String getBookId() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getNameLong() {
        return nameLong;
    }

}
