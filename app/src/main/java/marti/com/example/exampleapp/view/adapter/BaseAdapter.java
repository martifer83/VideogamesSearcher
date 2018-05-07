package marti.com.example.exampleapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import marti.com.example.exampleapp.R;

/**
 * Created by mferrando on 08/06/16.
 */
public abstract class BaseAdapter<I, V> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    public static final int VIEW_TYPE_ROW = 1;
    public static final int VIEW_TYPE_HEADER = 2;

    public interface OnItemClickListener<I> {
        void onItemClick(I item, int position);
    }


    public abstract V newViewHolder();

    public abstract int getLayoutResId(int viewType);

    public abstract void updateViewHolder(V holder, I item);


    public ArrayList<I> getmItems() {
        return mItems;
    }

    private ArrayList<I> mItems;
    private OnItemClickListener<I> mOnItemClickListener;


    public BaseAdapter(ArrayList<I> items, OnItemClickListener<I> listener) {
        mItems = items;
        mOnItemClickListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = getLayoutResId(viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        view.setBackgroundResource(R.drawable.list_item_background);

        final ViewHolder<V> holder = new ViewHolder<>(view, newViewHolder());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = holder.getLayoutPosition();
                    I item = getItem(position);
                    mOnItemClickListener.onItemClick(item, position);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        updateViewHolder((V)holder.getHolder(), getItem(position));
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public I getItem(int position) {
        return mItems.get(position);
    }


    public void setItems(List<I> items) {
        this.mItems.clear();
        this.mItems.addAll(items);
        notifyDataSetChanged();
    }


    class ViewHolder<H> extends RecyclerView.ViewHolder {

        private H holder;

        public ViewHolder(View itemView, H holder) {
            super(itemView);
            this.holder = holder;
            ButterKnife.bind(holder, itemView);
        }

        H getHolder() {
            return holder;
        }
    }

}