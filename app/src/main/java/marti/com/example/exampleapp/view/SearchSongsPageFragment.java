package marti.com.example.exampleapp.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.entity.Song;
import marti.com.example.exampleapp.presenter.SearchSongPagePresenter;
import marti.com.example.exampleapp.view.adapter.BaseAdapter;
import marti.com.example.exampleapp.view.adapter.SearchSongListAdapter;
import marti.com.example.exampleapp.view.widget.EmptyRecyclerView;
/**
 * Created by mferrando on 07/06/16.
 */

@BaseFragment.Params(layout = R.layout.fragment_search_song)
public class SearchSongsPageFragment extends BaseFragment<SearchSongPagePresenter> implements FilterListener, SearchSongPagePresenter.View, BaseAdapter.OnItemClickListener<Song> {

    @Bind(R.id.list_songs)
    protected EmptyRecyclerView mRecyclerView;
    @Bind(R.id.empty_list)
    protected TextView emptyList;




    private SearchSongListAdapter mAdapter;
    private ArrayList<Song> mSongs;
    //private ArrayList<Song> mOriginalEvents;


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

      //  mRecyclerView = (EmptyRecyclerView) container.findViewById(R.id.empty_list);
      //  emptyList = (TextView) container.findViewById(R.id.list_songs);
       //
        return super.onCreateView( inflater,  container,  savedInstanceState);

    }*/

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureRecyclerView();
        setPresenter(new SearchSongPagePresenter(this));

    }



    private void configureRecyclerView() {
        emptyList.setText("empty");
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setEmptyView(emptyList);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getEvents();

    }

    @Override
    public void onEventsReceived(ArrayList<Song> events) {
        //mOriginalEvents = new ArrayList<>(events);
        if (mAdapter == null) {
            mSongs = events;
            mAdapter = new SearchSongListAdapter(mSongs, this);
            mRecyclerView.setAdapter(mAdapter);
            // Add the sticky headers decoration
            mRecyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapter));
        } else {
            // Update adapter
            mSongs.clear();
            mSongs.addAll(events);
            mAdapter.setTextHighLighted(null);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(Song item, int position) {
       // getActivity().startActivity(EventDetailsActivity.newIntent(getContext(), item));
    }

    @Override
    public void updateFields(String filterText) {
        /*if (mOriginalEvents == null) return;

        ArrayList<Song> currentEvents = new ArrayList<>();
        for (Song event : mOriginalEvents) {
            String normalizeFilterString = UtilsString.normalizeString(filterText);
            if(event.getName() != null && event.getLocation() != null) {
                if (UtilsString.normalizeString(event.getName()).contains(normalizeFilterString) ||
                        UtilsString.normalizeString(event.getLocation()).contains(normalizeFilterString)) {
                    currentEvents.add(event);
                }
            }
        }*/

        // Update adapter
        mSongs.clear();
      //  mSongs.addAll(currentEvents);
        mAdapter.setTextHighLighted(filterText);
        mAdapter.notifyDataSetChanged();
    }
}
