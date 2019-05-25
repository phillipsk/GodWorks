package io.techministry.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksResponse {

    @SerializedName("data")
    public List<BibleBook> books;
//    public List books;

    public BooksResponse(List<BibleBook> bibleBooks) {
        this.books = bibleBooks;
    }

    @Override
    public String toString() {
        return "BooksResponse{" +
                "bibleBooks=" + books +
                '}';
    }

    public List<BibleBook> getBooks() {
        return books;
    }

    public void setBooks(List<BibleBook> books) {
        this.books = books;
    }
}
