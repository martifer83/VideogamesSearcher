package marti.com.example.exampleapp.view.gameDetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.entity.GameIgdbDetail;
import marti.com.example.exampleapp.entity.Song;
import marti.com.example.exampleapp.entity.SongResponse;
import marti.com.example.exampleapp.entity.gameigdbdetail.Company;
import marti.com.example.exampleapp.entity.gameigdbdetail.Genres;
import marti.com.example.exampleapp.entity.gameigdbdetail.ReleaseDate;
import marti.com.example.exampleapp.entity.gameigdbdetail.Screenshot;
import marti.com.example.exampleapp.presenter.GameDetailsPresenter;
import marti.com.example.exampleapp.utils.UtilsDate;
import marti.com.example.exampleapp.utils.UtilsImage;
import marti.com.example.exampleapp.view.BaseFragment;
import marti.com.example.exampleapp.view.FilterListener;
import marti.com.example.exampleapp.view.adapter.BaseAdapter;
import marti.com.example.exampleapp.view.adapter.CompanyListAdapter;
import marti.com.example.exampleapp.view.adapter.GenreListAdapter;
import marti.com.example.exampleapp.view.adapter.ReleaseListAdapter;
import marti.com.example.exampleapp.view.adapter.ScreenshotListAdapter;
import marti.com.example.exampleapp.view.widget.EmptyRecyclerView;

/**
 * Created by mferrando on 28/06/16.
 */
@BaseFragment.Params(layout = R.layout.fragment_game_detail)
public class GameDetailsFragment extends BaseFragment<GameDetailsPresenter> implements FilterListener, GameDetailsPresenter.View, BaseAdapter.OnItemClickListener<ReleaseDate>{

    private static String EXTRA_GAME = "EXTRA_GAME";
    private static GameIgdbDetail mCurrentGame;
    private static String provisionalCover;

    private ArrayList<ReleaseDate> mReleases;
    private ReleaseListAdapter mReleaseListAdapter;

    private ArrayList<Company> mCompanies;
    private CompanyListAdapter mCompanyListAdapter;

    private ArrayList<Genres> mGenres;
    private GenreListAdapter mGenreListAdapter;

    private ArrayList<Screenshot> mScreenshots;
    private ScreenshotListAdapter mScreenshotListAdapter;

    @Bind(R.id.name)
    protected TextView mName;
    //@Bind(R.id.slug)
    //protected TextView mSlug;
    //@Bind(R.id.created_at)
    //protected TextView mCreatedAt;
    //@Bind(R.id.release_date)
    //protected TextView mReleaseDate;

    @Bind(R.id.summary)
    protected TextView mSummary;

    @Bind(R.id.popularity)
    protected TextView mPopularity;

    @Bind(R.id.first_release_date)
    protected TextView mfirst_release_date;

    @Bind(R.id.rating)
    protected TextView mRating;

    @Bind(R.id.time_to_beat)
    protected TextView mTime_to_beat;

    @Bind(R.id.total_rating)
    protected TextView mTotal_rating;

    /*@Bind(R.id.developer)
    protected TextView mDeveloper;
    @Bind(R.id.publisher)
    protected TextView mPublisher;*/
    @Bind(R.id.cover)
    protected ImageView mCover;
    @Bind(R.id.list_releases)
    protected EmptyRecyclerView mRecyclerViewReleases;
    @Bind(R.id.empty_list_releases)
    protected TextView emptyreleasesList;

    @Bind(R.id.list_genres)
    protected EmptyRecyclerView mRecyclerViewGenres;
    @Bind(R.id.empty_list_genres)
    protected TextView emptyGenresList;
    @Bind(R.id.list_companies)
    protected EmptyRecyclerView mRecyclerViewCompanies;
    @Bind(R.id.empty_list_companies)
    protected TextView emptyCompaniesList;
    @Bind(R.id.list_screenshots)
    protected EmptyRecyclerView mRecyclerViewScreenshots;
    @Bind(R.id.empty_list_screenshots)
    protected TextView emptyScreenshotsList;


    public static GameDetailsFragment newInstance(GameIgdbDetail game, String cover) {
        mCurrentGame = game;
        GameDetailsFragment fragment = new GameDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_GAME, game);
        bundle.putString("COVER", cover);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showGameDetail(GameIgdbDetail gameIgdbDetail) {

    }

    public void showGameDetail2() {
        mName.setText(mCurrentGame.getName());
        mPopularity.setText(String.valueOf(mCurrentGame.getPopularity()));
        mfirst_release_date.setText(mCurrentGame.getFirst_release_date());
        mSummary.setText(mCurrentGame.getSummary());
        mRating.setText("Rating: " + Float.toString(mCurrentGame.getRating()));
        if(mCurrentGame.getRating() == 0)
            mRating.setVisibility(View.INVISIBLE);
        mReleases = mCurrentGame.getRelease_dates();
        mCompanies = mCurrentGame.getCompanies();
       // mGenres = mCurrentGame.getGenres();
        mScreenshots = mCurrentGame.getScreenshots();
        if(mCurrentGame.getTime_to_beat() != null)
            mTime_to_beat.setText("Time To beat: " + UtilsDate.secondsToDateString(mCurrentGame.getTime_to_beat().getNormally()));  // we get the normal time only
        mTotal_rating.setText("Total rating: " +mCurrentGame.getTotal_rating());

        populateReleasesList(mReleases);
        populateCompaniesList(mCompanies);
        populateGenresList(mGenres);
        populateScrenshotsList(mScreenshots);

        // TODO uncomment when working again
        /*if (mCurrentGame.getCover() != null){
            UtilsImage.displayImage(mCover, mCurrentGame.getCover().getUrl(), R.drawable.dummy_event_im);
        } else {
            mCover.setImageResource(R.drawable.dummy_event_im);
        }*/

        // just for test
       // provisionalCover = "//res.cloudinary.com/igdb/image/upload/t_screenshot_med/qlapgaeuto9xntsonbz3.jpg";

        UtilsImage.displayImage(mCover, provisionalCover, R.drawable.dummy_event_im);

    }

   /* @Override
    public void showEventDetail(EventDetailsData eventDetailsData) {
        mEventDetailsData = eventDetailsData;
        animateBottomToolbar();
        if (eventDetailsData != null) {
            mFriends.setText(getString(R.string.event_details_friends, getListSize(eventDetailsData.getAssistanceFriends())));
            mRides.setText(getString(R.string.event_details_rides, eventDetailsData.getRidesOnEvent()));
            mDescription.setText(eventDetailsData.getDescription());
        }
    }*/

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        return view;
    }*/

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCurrentGame = getArguments().getParcelable(EXTRA_GAME);
        provisionalCover = getArguments().getString("COVER");
        setPresenter(new GameDetailsPresenter(this));

        configureRecyclerView();

        showGameDetail2();
    }

    private void configureRecyclerView() {
        emptyreleasesList.setText("");
        mRecyclerViewReleases.setHasFixedSize(true);
        mRecyclerViewReleases.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewReleases.setEmptyView(emptyreleasesList);

        emptyGenresList.setText("");
        mRecyclerViewGenres.setHasFixedSize(true);
        mRecyclerViewGenres.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewGenres.setEmptyView(emptyGenresList);

        emptyCompaniesList.setText("");
        mRecyclerViewCompanies.setHasFixedSize(true);
        mRecyclerViewCompanies.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewCompanies.setEmptyView(emptyCompaniesList);

        emptyScreenshotsList.setText("");
        mRecyclerViewScreenshots.setHasFixedSize(true);
        mRecyclerViewScreenshots.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewScreenshots.setEmptyView(emptyScreenshotsList);
    }

    @OnClick(R.id.name)
    protected void onNameClick() {
        // TODO
    }

    private ArrayList<Song> builtEventsList(SongResponse songsResponse) {
        ArrayList<Song> songs = new ArrayList<>();

        if (songsResponse.getMySongs() != null) {
            for (Song myEvent : songsResponse.getMySongs()) {
                //    myEvent.setType(EventType.MY);
                songs.add(myEvent);
            }
        }
        return songs;
    }

    private void populateGenresList(ArrayList<Genres> genres){

        if(genres == null)
        {
            return;
        }

        if (mGenreListAdapter == null) {
            mGenres = genres;
            mGenreListAdapter = new GenreListAdapter(mGenres, new BaseAdapter.OnItemClickListener<Genres>() {
                @Override
                public void onItemClick(Genres item, int position) {

                }
            });

            mRecyclerViewGenres.setAdapter(mGenreListAdapter);
            // Add the sticky headers decoration
            // 3mRecyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapter));
        } else {
            // Update adapter
            mGenres.clear();
            mGenres.addAll(genres);
            mGenreListAdapter.setTextHighLighted(null);
            mGenreListAdapter.notifyDataSetChanged();
        }
    }

    private void populateCompaniesList(ArrayList<Company> company){

        if(company == null)
        {
            return;
        }

        if (mCompanyListAdapter == null) {
            mCompanies = company;
            mCompanyListAdapter = new CompanyListAdapter(mCompanies, new BaseAdapter.OnItemClickListener<Company>() {
                @Override
                public void onItemClick(Company item, int position) {

                }
            });
            mRecyclerViewCompanies.setAdapter(mCompanyListAdapter);
            // Add the sticky headers decoration
            // mRecyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapter));
        } else {
            // Update adapter
            mCompanies.clear();
            mCompanies.addAll(company);
            mCompanyListAdapter.setTextHighLighted(null);
            mCompanyListAdapter.notifyDataSetChanged();
        }

    }

    private void populateReleasesList(ArrayList<ReleaseDate> releases) {

        if(releases == null)
        {
            return;
        }

        if (mReleaseListAdapter == null) {
            mReleases = releases;
            mReleaseListAdapter = new ReleaseListAdapter(mReleases, this);
            mRecyclerViewReleases.setAdapter(mReleaseListAdapter);
            // Add the sticky headers decoration
            // mRecyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapter));
        } else {
            // Update adapter
            mReleases.clear();
            mReleases.addAll(releases);
            mReleaseListAdapter.setTextHighLighted(null);
            mReleaseListAdapter.notifyDataSetChanged();
        }
    }

    private void populateScrenshotsList(ArrayList<Screenshot> screenshots) {

        if(screenshots == null)
        {
            return;
        }

        if (mScreenshotListAdapter == null) {
            mScreenshots = screenshots;

            mScreenshotListAdapter = new ScreenshotListAdapter(mScreenshots, new BaseAdapter.OnItemClickListener<Screenshot>() {
                @Override
                public void onItemClick(Screenshot item, int position) {

                }
            });

            mRecyclerViewScreenshots.setAdapter(mScreenshotListAdapter);
            // Add the sticky headers decoration
            // mRecyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapter));
        } else {
            // Update adapter
            mScreenshots.clear();
            mScreenshots.addAll(screenshots);
            mScreenshotListAdapter.setTextHighLighted(null);
            mScreenshotListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateFields(String filterText) {

    }

    @Override
    public void onItemClick(ReleaseDate item, int position) {

    }

    @Override
    protected void injectDependencies() {

    }
}
