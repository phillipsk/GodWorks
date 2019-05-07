package io.techministry.ui.bible.chapter;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.techministry.GodWorksApplication;
import io.techministry.R;
import io.techministry.network.BibleApiManager;
import io.techministry.network.BibleBook;
import io.techministry.network.BibleChapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterFragment extends Fragment implements ChapterScreen {
    /* // TODO: What are typed parameters? Would this work?
        public class ChapterFragment<ChapterPresenter> extends Fragment implements ChapterScreen {
        private ChapterPresenter chapterPresenter;*/
    private static final String KEY_BOOK = "key_book";

    private ChapterPresenter chapterPresenter;
    private ChapterAdapter chapterAdapter;
    private String bibleId = "de4e12af7f28f599-02";
    private RecyclerView recyclerView;
//    private String bookId = "HAB";

    public ChapterFragment() {
        // Required empty public constructor
    }

    public static ChapterFragment getInstance(BibleBook book) {
        ChapterFragment fragment = new ChapterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_BOOK, book);
        fragment.setArguments(bundle);
        return fragment;
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
                bibleApiManager.getBibleApi(), context.getCacheDir());
        chapterPresenter.bind(this);

        /*

        Bible fragment: should load the data the first time, and then load the data from the cache
        Chapter fragment: will load the chapters the first time from the remote api, and then from cache

         */
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_ch);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        chapterAdapter = new ChapterAdapter();
        recyclerView.setAdapter(chapterAdapter);

        Bundle args = getArguments();
        BibleBook book = (BibleBook) args.getSerializable(KEY_BOOK);
        if (book != null) {
            chapterPresenter.fetchBibleChapters(bibleId, book.getBookId());
        }
    }

    @Override
    public void onNewBibleChapters(List<BibleChapter> chapters) {
        Log.i("TESTING", "Incoming data: " + chapters);
        chapterAdapter.setItems(chapters);
    }

    @Override
    public void onDestroy() {
        chapterPresenter.unbind();
        super.onDestroy();
    }


}
