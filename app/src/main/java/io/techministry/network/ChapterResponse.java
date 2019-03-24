package io.techministry.network;

import java.util.List;

public class ChapterResponse {
    public List<BibleChapter> chapterList;

    public ChapterResponse(List<BibleChapter> chapterList) {
        this.chapterList = chapterList;
    }

    @Override
    public String toString() {
        return "ChapterResponse{" +
                "bibleChapter=" + chapterList +
                '}';
    }
}
