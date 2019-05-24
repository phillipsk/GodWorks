package io.techministry.ui.bible;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.techministry.GWApp;
import io.techministry.R;
import io.techministry.network.BibleApiManager;
import io.techministry.network.BibleBook;
import io.techministry.network.ObservableHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class BibleFragment extends Fragment implements BibleScreen {

    private BiblePresenter biblePresenter;
    private BooksAdapter booksAdapter;
    private String bibleId = "de4e12af7f28f599-02";
    private RecyclerView recyclerView;
    private BibleFragmentDelegate mBibleFragmentDelegate;
    private ObservableHelper mObservableHelper;

    public BibleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ObservableHelper.getInstance();
//        mObservableHelper.
        biblePresenter.getBibBook("null");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("TEST", "BibleFragment created.");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bible, container, false);
//        return inflater.inflate(R.layout.activity_dashboard, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof BibleFragmentDelegate) {
            mBibleFragmentDelegate = (BibleFragmentDelegate) context;
        } else
            throw new IllegalStateException("The activity " + context + " does not implement the BibleFragmentDelegate interface.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBibleFragmentDelegate = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        booksAdapter = new BooksAdapter();
        booksAdapter.setBooksListener(book -> mBibleFragmentDelegate.onBookSelected(book));
        recyclerView.setAdapter(booksAdapter);

        BibleApiManager bibleApiManager = ((GWApp) getActivity().getApplicationContext()).getBibleApiManager();
        biblePresenter = new BiblePresenter(bibleApiManager.getGson(), bibleApiManager.getBibleApi(), getActivity().getCacheDir());

        biblePresenter.bind(this);
        // TODO: get the data from the cache if available instead of making a new HTTP request everytime
//        biblePresenter.fetchBibleBooks(bibleId);
        biblePresenter.zfetchBibleBooks(bibleId);
        Toast.makeText(getActivity(), "ON VIEW CREATED", Toast.LENGTH_LONG).show();
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

    public interface BibleFragmentDelegate {
        void onBookSelected(BibleBook book);
    }
}
