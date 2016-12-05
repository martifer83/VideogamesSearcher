package marti.com.example.exampleapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.entity.gameigdbdetail.Genres;

/**
 * Created by mferrando on 12/07/16.
 */
public class GenreListAdapter extends BaseAdapter<Genres, GenreListAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<GenreListAdapter.HeaderViewHolder> {

    public class ViewHolder {

        @Bind(R.id.genre_name_list)
        protected TextView genre_name_list;

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String mTextHighLighted;

    public GenreListAdapter(ArrayList<Genres> items, BaseAdapter.OnItemClickListener<Genres> listener) {
        super(items, listener);
        setHasStableIds(true);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.list_genres_row;
    }

    @Override
    public ViewHolder newViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void updateViewHolder(ViewHolder holder, Genres genres) {
        holder.genre_name_list.setText(genres.getName());
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public GenreListAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_genres_row, parent, false);
        return new GenreListAdapter.HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(GenreListAdapter.HeaderViewHolder holder, int position) {
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public void setTextHighLighted(String textHighLighted) {
        mTextHighLighted = textHighLighted;
    }
}
