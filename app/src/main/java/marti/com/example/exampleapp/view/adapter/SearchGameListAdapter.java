package marti.com.example.exampleapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.entity.Game;
import marti.com.example.exampleapp.entity.Platforms;
import marti.com.example.exampleapp.utils.UtilsImage;

/**
 * Created by mferrando on 15/06/16.
 */
public class SearchGameListAdapter extends BaseAdapter<Game, SearchGameListAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<SearchGameListAdapter.HeaderViewHolder> {

    public class ViewHolder {
        @Bind(R.id.image)
        protected ImageView image;
        @Bind(R.id.name)
        protected TextView name;
        @Bind(R.id.aliases)
        protected TextView aliases;
        @Bind(R.id.original_release_data)
        protected TextView original_release_data;
        @Bind(R.id.platforms)
        protected TextView platforms;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String mTextHighLighted;

    public SearchGameListAdapter(ArrayList<Game> items, BaseAdapter.OnItemClickListener<Game> listener) {
        super(items, listener);
        setHasStableIds(true);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.list_item_games_row;
    }

    @Override
    public ViewHolder newViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void updateViewHolder(ViewHolder holder, Game game) {
        // UtilsEditText.setHighLightedText(holder.name, item.getName(), mTextHighLighted);
        holder.name.setText(game.getName());
        holder.aliases.setText(game.getAliases());
        holder.original_release_data.setText(game.getOriginal_release_date());

        String composedPlatforms = "";

        List<Platforms> platforms =  game.getPlatforms();

        for (int i = 0; i < platforms.size(); i++) {

            composedPlatforms+= " - " + platforms.get(i).getName();

        }

        holder.platforms.setText(composedPlatforms);

        // check if null

        if (game.getImage() != null){
           UtilsImage.displayImage(holder.image, game.getImage().getMedium_url(), R.drawable.dummy_event_im);
        } else {
            holder.image.setImageResource(R.drawable.dummy_event_im);
        }
    }

    @Override
    public long getHeaderId(int position) {
        return 0;  //getItem(position).getType().hashCode();
    }

    @Override
    public SearchGameListAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_events_header, parent, false);
        return new SearchGameListAdapter.HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(SearchGameListAdapter.HeaderViewHolder holder, int position) {
      //  holder.name.setText("Header Game"); // set title
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public void setTextHighLighted(String textHighLighted) {
        mTextHighLighted = textHighLighted;
    }
}
