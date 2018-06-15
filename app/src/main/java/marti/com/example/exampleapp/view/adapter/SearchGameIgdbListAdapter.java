package marti.com.example.exampleapp.view.adapter;

import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.utils.UtilsDate;
import marti.com.example.exampleapp.utils.UtilsImage;

/**
 * Created by mferrando on 23/06/16.
 */
public class SearchGameIgdbListAdapter extends BaseAdapter<GameIGDB, SearchGameIgdbListAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<SearchGameIgdbListAdapter.HeaderViewHolder> {

    public class ViewHolder {
        @Bind(R.id.game_cover)
        protected ImageView cover;

        @Bind(R.id.name)
        protected TextView name;

       // @Bind(R.id.popularity)
       // protected TextView popularity;

        @Bind(R.id.release_data)
        protected TextView release_data;

        @Bind(R.id.rating)
        protected TextView rating;


    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String mTextHighLighted;

    public SearchGameIgdbListAdapter(ArrayList<GameIGDB> items, BaseAdapter.OnItemClickListener<GameIGDB> listener) {
        super(items, listener);
        setHasStableIds(true);
    }



    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.list_item_gamesigdb_row;
    }

    @Override
    public ViewHolder newViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void updateViewHolder(ViewHolder holder, GameIGDB game) {
        // UtilsEditText.setHighLightedText(holder.name, item.getName(), mTextHighLighted);
        holder.name.setText(game.getName());

        String rel = game.getRelease_date();

        //if(game.getRelease_date()!=0.0f) {
            String releaseData = "Release data: " + UtilsDate.getDateFromTimeStamp(Long.parseLong(game.getRelease_date()));
            holder.release_data.setText(releaseData);
        //}
        holder.rating.setText("Rating: " + Float.toString(Math.round(game.getRating())));
        // check if null

        if (game.getCover() != null){
            UtilsImage.displayImage(holder.cover, "http:"+game.getCover().getUrl(), R.drawable.dummy_event_im);
        } else {
            holder.cover.setImageResource(R.drawable.dummy_event_im);
        }
    }

    @Override
    public long getHeaderId(int position) {
        return 0;  //getItem(position).getType().hashCode();
    }

    @Override
    public SearchGameIgdbListAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_events_header, parent, false);
        return new SearchGameIgdbListAdapter.HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(SearchGameIgdbListAdapter.HeaderViewHolder holder, int position) {
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public void setTextHighLighted(String textHighLighted) {
        mTextHighLighted = textHighLighted;
    }
}
