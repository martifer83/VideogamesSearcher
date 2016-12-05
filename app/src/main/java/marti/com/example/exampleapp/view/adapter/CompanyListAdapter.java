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
import marti.com.example.exampleapp.entity.gameigdbdetail.Company;

/**
 * Created by mferrando on 12/07/16.
 */
public class CompanyListAdapter extends BaseAdapter<Company, CompanyListAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<CompanyListAdapter.HeaderViewHolder> {

    public class ViewHolder {

        @Bind(R.id.company_name_list)
        protected TextView company_name_list;

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String mTextHighLighted;

    public CompanyListAdapter(ArrayList<Company> items, BaseAdapter.OnItemClickListener<Company> listener) {
        super(items, listener);
        setHasStableIds(true);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.list_companies_row;
    }

    @Override
    public ViewHolder newViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void updateViewHolder(ViewHolder holder, Company company) {
        holder.company_name_list.setText( (company.isDeveloper() ? "Developer: " : "Publisher: ") + company.getName());
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public CompanyListAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_companies_row, parent, false);
        return new CompanyListAdapter.HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(CompanyListAdapter.HeaderViewHolder holder, int position) {
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public void setTextHighLighted(String textHighLighted) {
        mTextHighLighted = textHighLighted;
    }
}
