package io.techministry.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChapterResponse {
    @SerializedName("data")
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
