package io.techministry.ui.bible.chapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.techministry.R;
import io.techministry.network.BibleChapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterFragment extends Fragment implements ChapterScreen {
/* // TODO: What are typed parameters? Would this work?
    public class ChapterFragment<ChapterPresenter> extends Fragment implements ChapterScreen {
    private ChapterPresenter chapterPresenter;*/

    private ChapterPresenter chapterPresenter;
    private ChapterAdapter chapterAdapter;
    private String bibleId = "de4e12af7f28f599-02";
    private RecyclerView recyclerView;

    public ChapterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter, container, false);
    }

    @Override
    public void onNewBibleChapter(List<BibleChapter> chapters) {

    }
}
