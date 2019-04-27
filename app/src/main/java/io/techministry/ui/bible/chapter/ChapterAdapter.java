package io.techministry.ui.bible.chapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.techministry.R;
import io.techministry.network.BibleChapter;

class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private List<BibleChapter> bibleChapterList;

    public ChapterAdapter(){
        this.bibleChapterList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_chapters,parent,false));
    }

/* // TODO: Default constructor differs
    public ChapterAdapter(List<BibleChapter> bibleChapterList) {
        this.bibleChapterList = bibleChapterList;
    }*/

    // TODO: This is the default onCreateViewHolder method auto-generated. Review BooksAdapter.java Constructor for <Generic> BooksAdapter.BookViewHolder parameter
/*
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_base_chapters,parent,false)) {
        };

    }
*/

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        holder.bind(bibleChapterList.get(position));
    }

    public void setItems(List<BibleChapter> data){
        this.bibleChapterList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bibleChapterList.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder{

        public ChapterViewHolder(View itemView) {
            super(itemView);
        }

        void bind(BibleChapter chapter){
            ((TextView) itemView.findViewById(R.id.info_text)).setText(chapter.getNumber());

            itemView.setOnClickListener(v -> Toast.makeText(v.getContext(),"Clicked on " + chapter.getId(),Toast.LENGTH_LONG).show());
        }
    }
}
