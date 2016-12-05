package marti.com.example.exampleapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.entity.gameigdbdetail.Screenshot;
import marti.com.example.exampleapp.utils.UtilsImage;

/**
 * Created by mferrando on 15/07/16.
 */
public class ScreenshotListAdapter extends BaseAdapter<Screenshot, ScreenshotListAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<ScreenshotListAdapter.HeaderViewHolder> {

    public class ViewHolder {

        @Bind(R.id.screenshot_url_list)
        protected ImageView screenshot_url_list;

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String mTextHighLighted;

    public ScreenshotListAdapter(ArrayList<Screenshot> items, BaseAdapter.OnItemClickListener<Screenshot> listener) {
        super(items, listener);
        setHasStableIds(true);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.list_screenshots_row;
    }

    @Override
    public ViewHolder newViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void updateViewHolder(ViewHolder holder, Screenshot screenshot) {

        String urlTest ="//res.cloudinary.com/igdb/image/upload/t_cover_small/yp33krrpx2nyy4hv52aa.png";  // teesting with cover

        if (screenshot.getUrl() != null){
            UtilsImage.displayImage(holder.screenshot_url_list, "http:"+ screenshot.getUrl(), R.drawable.dummy_screenshot);
        } else {
            holder.screenshot_url_list.setImageResource(R.drawable.dummy_screenshot);
        }

    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public ScreenshotListAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_screenshots_row, parent, false);
        return new ScreenshotListAdapter.HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(ScreenshotListAdapter.HeaderViewHolder holder, int position) {
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public void setTextHighLighted(String textHighLighted) {
        mTextHighLighted = textHighLighted;
    }
}
