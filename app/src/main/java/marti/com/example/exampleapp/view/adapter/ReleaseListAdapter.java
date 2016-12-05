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
import marti.com.example.exampleapp.entity.gameigdbdetail.ReleaseDate;

/**
 * Created by mferrando on 11/07/16.
 */
public class ReleaseListAdapter extends BaseAdapter<ReleaseDate, ReleaseListAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<ReleaseListAdapter.HeaderViewHolder> {

    public class ViewHolder {

        @Bind(R.id.platform_name_list)
        protected TextView platform_name_list;
        @Bind(R.id.release_date_list)
        protected TextView release_date_list;

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String mTextHighLighted;

    public ReleaseListAdapter(ArrayList<ReleaseDate> items, BaseAdapter.OnItemClickListener<ReleaseDate> listener) {
        super(items, listener);
        setHasStableIds(true);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.list_releases_row;
    }

    @Override
    public ViewHolder newViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void updateViewHolder(ViewHolder holder, ReleaseDate releaseDate) {

        holder.platform_name_list.setText(releaseDate.getPlatform_name());
        holder.release_date_list.setText(releaseDate.getRelease_date());
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public ReleaseListAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_releases_row, parent, false);
        return new ReleaseListAdapter.HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(ReleaseListAdapter.HeaderViewHolder holder, int position) {
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public void setTextHighLighted(String textHighLighted) {
        mTextHighLighted = textHighLighted;
    }
}

