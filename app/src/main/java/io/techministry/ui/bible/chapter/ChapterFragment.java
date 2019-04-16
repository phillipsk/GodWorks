package io.techministry.ui.bible.chapter;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.techministry.GodWorksApplication;
import io.techministry.R;
import io.techministry.network.BibleApiManager;
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
    private String bookId = "RUT";
//    private String bookId = "HAB";

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
    public void onAttach(Context context) {
        super.onAttach(context);
        BibleApiManager bibleApiManager =
                ((GodWorksApplication) context.getApplicationContext())
                        .getBibleApiManager();
        chapterPresenter = new ChapterPresenter(bibleApiManager.getGson(),
                bibleApiManager.getBibleApi(),context.getCacheDir());
        chapterPresenter.bind(this);
        chapterPresenter.fetchBibleChapters(bibleId,bookId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_ch);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);
        chapterAdapter = new ChapterAdapter();
        recyclerView.setAdapter(chapterAdapter);
    }

    @Override
    public void onNewBibleChapters(List<BibleChapter> chapters) {
        chapterAdapter.setItems(chapters);
    }

    @Override
    public void onDestroy() {
        chapterPresenter.unbind();
        super.onDestroy();
    }


}
