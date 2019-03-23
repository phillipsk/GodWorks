package io.techministry.ui.bible;

import java.util.List;

import io.techministry.network.BibleBook;

public interface BibleScreen {

    void onNewBibleBooks(List<BibleBook> books);

}
