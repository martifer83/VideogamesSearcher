package marti.com.example.exampleapp.view.adapter;

/**
 * Created by mferrando on 07/06/16.
 */

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.entity.Song;
import marti.com.example.exampleapp.utils.UtilsImage;

public class SearchSongListAdapter extends BaseAdapter<Song, SearchSongListAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<SearchSongListAdapter.HeaderViewHolder> {

    public class ViewHolder {
        @Bind(R.id.image)
        protected ImageView image;
        @Bind(R.id.artist_name)
        protected TextView artist_name;
        @Bind(R.id.track_name)
        protected TextView track_name;
        @Bind(R.id.location)
        protected TextView location;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.artist_name)
        protected TextView artist_name;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String mTextHighLighted;

    public SearchSongListAdapter(ArrayList<Song> items, BaseAdapter.OnItemClickListener<Song> listener) {
        super(items, listener);
        setHasStableIds(true);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.list_item_events_row;
    }

    @Override
    public ViewHolder newViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void updateViewHolder(ViewHolder holder, Song song) {
       // UtilsEditText.setHighLightedText(holder.name, item.getName(), mTextHighLighted);
        holder.artist_name.setText(song.getArtistName());
        holder.track_name.setText(song.getTrackName());
       // holder.location.setVisibility(View.VISIBLE);
        /*if (!TextUtils.isEmpty(song.getLocation())) {
            UtilsEditText.setHighLightedText(holder.location, song.getLocation(), mTextHighLighted);
        } else {
            holder.location.setVisibility(View.GONE);
        }*/

        if (!TextUtils.isEmpty(song.getArtworkUrl30())) {
            UtilsImage.displayImage(holder.image, song.getArtworkUrl30(), R.drawable.dummy_event_im);
        } else {
            holder.image.setImageResource(R.drawable.dummy_event_im);
        }
    }

    @Override
    public long getHeaderId(int position) {
        return 0;  //getItem(position).getType().hashCode();
    }

    @Override
    public SearchSongListAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_events_header, parent, false);
        return new SearchSongListAdapter.HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(SearchSongListAdapter.HeaderViewHolder holder, int position) {
        holder.artist_name.setText("Test Header"); // set artist
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public void setTextHighLighted(String textHighLighted) {
        mTextHighLighted = textHighLighted;
    }
}
