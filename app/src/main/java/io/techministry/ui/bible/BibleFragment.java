package io.techministry.ui.bible;


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
import io.techministry.network.BibleBook;

/**
 * A simple {@link Fragment} subclass.
 */
public class BibleFragment extends Fragment implements BibleScreen {

    private BiblePresenter biblePresenter;
    private BooksAdapter booksAdapter;
    private String bibleId = "de4e12af7f28f599-01";
    private RecyclerView recyclerView;

    public BibleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bible, container, false);
//        return inflater.inflate(R.layout.activity_dashboard, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        BibleApiManager bibleApiManager = ((GodWorksApplication) context.getApplicationContext()).getBibleApiManager();
        biblePresenter = new BiblePresenter(bibleApiManager.getGson(), bibleApiManager.getBibleApi(), context.getCacheDir());

        biblePresenter.bind(this);
        biblePresenter.fetchBibleBooks(bibleId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        booksAdapter = new BooksAdapter();
        recyclerView.setAdapter(booksAdapter);
    }

    @Override
    public void onNewBibleBooks(List<BibleBook> books) {
        booksAdapter.setItems(books);
    }

    @Override
    public void onDestroy() {
        biblePresenter.unbind();
        super.onDestroy();
    }

}
