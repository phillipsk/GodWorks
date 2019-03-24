package io.techministry.network;

public class BibleChapter {
    /*      "id": "RUT.1",
      "bibleId": "de4e12af7f28f599-02",
      "bookId": "RUT",
      "number": "1",
      "reference": "Ruth 1"
      */

    private String id;
    private String bookId;
    private int number;
    private String reference;

    public BibleChapter(String id, String bookId, int number, String reference) {
        this.id = id;
        this.bookId = bookId;
        this.number = number;
        this.reference = reference;
    }

    public String getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public int getNumber() {
        return number;
    }

    public String getReference() {
        return reference;
    }
}
