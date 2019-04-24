package io.techministry.network;

public class BibleChapter {
    /*      "id": "RUT.1",
      "bibleId": "de4e12af7f28f599-02",
      "bookId": "RUT",
      "number": "1",
      "reference": "Ruth 1"
      */

    String id;
    String bookId;
    String number;
    String reference;

    public BibleChapter(String id, String bookId, String number, String reference) {
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

    public String getNumber() {
        return number;
    }

    public String getReference() {
        return reference;
    }
}
