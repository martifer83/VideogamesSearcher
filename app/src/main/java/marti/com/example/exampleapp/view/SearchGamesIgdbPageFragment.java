package marti.com.example.exampleapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

import butterknife.Bind;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.di.HasComponent;
import marti.com.example.exampleapp.di.components.ActivityComponent;
import marti.com.example.exampleapp.entity.Game;
import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;
import marti.com.example.exampleapp.presenter.SearchGamePagePresenter;
import marti.com.example.exampleapp.view.adapter.BaseAdapter;
import marti.com.example.exampleapp.view.adapter.SearchGameIgdbListAdapter;
import marti.com.example.exampleapp.view.gameDetails.GameDetailsActivity;
import marti.com.example.exampleapp.view.widget.EmptyRecyclerView;

/**
 * Created by mferrando on 23/06/16.
 */
@BaseFragment.Params(layout = R.layout.fragment_search_game)
public class SearchGamesIgdbPageFragment extends BaseFragment<SearchGamePagePresenter> implements FilterListener, SearchGamePagePresenter.View, BaseAdapter.OnItemClickListener<GameIGDB> {

    @Bind(R.id.list_game)
    protected EmptyRecyclerView mRecyclerView;
    @Bind(R.id.empty_list_game)
    protected TextView emptyList;

    @Inject
    SearchGamePagePresenter presenter;

    private SearchGameIgdbListAdapter mAdapterIdgb;
    private ArrayList<Game> mGames;
    private ArrayList<GameIGDB> mGamesIgdb;

    private String urlCover; // provisional solution;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectDependencies();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureRecyclerView();
        //setPresenter(new SearchGamePagePresenter(this, ));
        //injectDependencies();
    }

    private void configureRecyclerView() {
        emptyList.setText("");
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setEmptyView(emptyList);
    }

    @Override
    public void onResume() {
        super.onResume();
      //  Presenter p = getPresenter();
      //  getPresenter().getGamesbyName("Zelda");
    }

    @Override
    protected SearchGamePagePresenter getPresenter() {
        return presenter;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void injectDependencies() {
        try {
           // Activity a = (HasComponent<ActivityComponent> getActivity());

            ((HasComponent<ActivityComponent>) getActivity()).getComponent().inject(this);
        } catch (ClassCastException exception) {
            throw new ClassCastException(getActivity().toString() +
                    "must implement HasModule<MainComponent>");
        }
    }

    @Override
    public void onGamesReceived(ArrayList<Game> games) {

    }

    @Override
    public void onGamesListIgdbReceived(ArrayList<GameIGDB> games) {
        //mOriginalEvents = new ArrayList<>(events);
        if (mAdapterIdgb == null) {
            mGamesIgdb = games;
            Collections.sort(mGamesIgdb, new SortbyPopularity());
            mAdapterIdgb = new SearchGameIgdbListAdapter(mGamesIgdb, this);
            mRecyclerView.setAdapter(mAdapterIdgb);
            // Add the sticky headers decoration
            mRecyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapterIdgb));
        } else {
            // Update adapter
            mGamesIgdb.clear();
            mGamesIgdb.addAll(games);
            Collections.sort(mGamesIgdb, new SortbyPopularity());
            mAdapterIdgb.setTextHighLighted(null);
            mAdapterIdgb.notifyDataSetChanged();
        }
    }

    class SortbyPopularity implements Comparator<GameIGDB>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(GameIGDB a, GameIGDB b)
        {
            return 0;
        }
    }

    @Override
    public void onGameIgdbReceived(GameIgdbDetail game) {
            // Open activity
            //pass bundle, and extra parameters

        Intent mIntent = new Intent(getContext(),GameDetailsActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable("EXTRA_GAME", game);
        mBundle.putString("COVER",urlCover);
        mIntent.putExtras(mBundle);
        getActivity().startActivity(mIntent);
// ok step 1
        //getActivity().startActivity(EventDetailsActivity.newIntent(getContext(), item));

    }

    @Override
    public void onItemClick(GameIGDB item, int position) {
        // Launch  query by ID
        //setPresenter(new SearchGamePagePresenter(this,null));
        int id = item.getId();
        getPresenter().getGamesbyId(Integer.toString(id));
    }

    @Override
    public void updateFields(String filterText) {

        injectDependencies();
        getPresenter().setmView(this);
        getPresenter().getGamesbyName(filterText);

        // Update adapter if it's not null
        if (mGamesIgdb != null) {
            mGamesIgdb.clear();

            mAdapterIdgb.setTextHighLighted(filterText);
            mAdapterIdgb.notifyDataSetChanged();
        }
    }
}
