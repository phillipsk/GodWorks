package io.techministry.ui.bible.chapter;

import java.util.List;

import io.techministry.network.BibleChapter;

interface ChapterScreen {
    void onNewBibleChapters(List<BibleChapter> chapters);
}
