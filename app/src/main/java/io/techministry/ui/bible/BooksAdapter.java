package io.techministry.ui.bible;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.techministry.R;
import io.techministry.network.BibleBook;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private List<BibleBook> items;
    private BooksListener mBooksListener;

    public BooksAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    public void setItems(List<BibleBook> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        public BookViewHolder(View itemView) {
            super(itemView);
        }

        void bind(BibleBook book) {
            ((TextView) itemView.findViewById(R.id.info_text)).setText(book.getName());
//            itemView.findViewById(R.id.start);

            itemView.setOnClickListener(view -> {
                if (mBooksListener != null)
                    mBooksListener.onBookSelected(book);
            });
/*            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/
//            itemView.setOnClickListener(View -> Toast.makeText(View.getContext(), "Clicked on " + book.getNameLong(), Toast.LENGTH_LONG).show());
        }
    }

    public void setBooksListener(BooksListener booksListener) {
        mBooksListener = booksListener;
    }

    public interface BooksListener {
        void onBookSelected(BibleBook book);
    }
}
