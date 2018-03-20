package marti.com.example.exampleapp.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.entity.Game;
import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;
import marti.com.example.exampleapp.presenter.Presenter;
import marti.com.example.exampleapp.presenter.SearchGamePagePresenter;
import marti.com.example.exampleapp.view.adapter.BaseAdapter;
import marti.com.example.exampleapp.view.adapter.SearchGameListAdapter;
import marti.com.example.exampleapp.view.widget.EmptyRecyclerView;

/**
 * Created by mferrando on 15/06/16.
 */


    @BaseFragment.Params(layout = R.layout.fragment_search_game)
    public class SearchGamesPageFragment extends BaseFragment<SearchGamePagePresenter> implements FilterListener, SearchGamePagePresenter.View, BaseAdapter.OnItemClickListener<Game> {

        @Bind(R.id.list_game)
        protected EmptyRecyclerView mRecyclerView;
        @Bind(R.id.empty_list_game)
        protected TextView emptyList;

        private SearchGameListAdapter mAdapter;
        private ArrayList<Game> mGames;

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            configureRecyclerView();
            setPresenter(new SearchGamePagePresenter(this));
        }

        private void configureRecyclerView() {
            emptyList.setText("");
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setEmptyView(emptyList);
            //mAdapter = new SearchGameListAdapter(mGames, this);
        }

        @Override
        public void onResume() {
            super.onResume();
            Presenter p = getPresenter();

            // Call by default
            getPresenter().getGamesbyName("Zelda");

        }

    @Override
    protected void injectDependencies() {

    }


    @Override
        public void onGamesReceived(ArrayList<Game> games) {
            //mOriginalEvents = new ArrayList<>(events);
            if (mAdapter == null) {
                mGames = games;
                mAdapter = new SearchGameListAdapter(mGames, this);
                mRecyclerView.setAdapter(mAdapter);
                // Add the sticky headers decoration
                mRecyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapter));
            } else {
                // Update adapter
                mGames.clear();
                mGames.addAll(games);
                mAdapter.setTextHighLighted(null);
                mAdapter.notifyDataSetChanged();

            }
        }

    @Override
    public void onGameIgdbReceived(GameIgdbDetail game) {

    }

    @Override
    public void onGamesListIgdbReceived(ArrayList<GameIGDB> games) {

    }


        @Override
        public void onItemClick(Game item, int position) {
            // getActivity().startActivity(EventDetailsActivity.newIntent(getContext(), item));
        }

        @Override
        public void updateFields(String filterText) {

            setPresenter(new SearchGamePagePresenter(this));
            getPresenter().getGames(filterText);

            // Update adapter
            mGames.clear();
            //  mSongs.addAll(currentEvents);
            mAdapter.setTextHighLighted(filterText);
            mAdapter.notifyDataSetChanged();
        }
}
