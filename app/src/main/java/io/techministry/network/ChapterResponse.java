package io.techministry.network;

import java.util.List;

public class ChapterResponse {
    public List<BibleChapter> chapterList;

    public ChapterResponse(List<BibleChapter> chList) {
        this.chapterList = chList;
    }

    @Override
    public String toString() {
        return "ChapterResponse{" +
                "bibleChapter=" + chapterList +
                '}';
    }
}
