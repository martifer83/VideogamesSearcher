package marti.com.example.exampleapp.view;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.view.adapter.SearchPagerAdapter;
import marti.com.example.exampleapp.view.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{

    //UI Views
    @Bind(R.id.home_pager)
    protected ViewPager mPager;

    private FilterListener mFilterListener;

    private SearchPagerAdapter pagerAdapter;

   // private SearchView searchView = null;
//    private SearchView.OnQueryTextListener queryTextListener;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SearchFragment newInstance(int columnCount) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
      //  args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt("column_count", columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_search_list, container, false);
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();

    }
    private void initViews() {
        pagerAdapter = new SearchPagerAdapter(getActivity(), getChildFragmentManager());
        mPager.setAdapter(pagerAdapter);

    }


    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //pagerAdapter = new SearchPagerAdapter(getActivity(), getChildFragmentManager());
        inflater.inflate(R.menu.menu_home, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }


    private void addListenerForTagging(MenuItem menuItem) {
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                int selectedPage = mPager.getCurrentItem();
                if (selectedPage == 0) {

                } else if (selectedPage == 1) {

                }
                return true;
            }
        });
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        Log.i("onQueryTextSubmit", query);
       // final SearchPagerAdapter pagerAdapter = new SearchPagerAdapter(getActivity(), getChildFragmentManager());
       // pagerAdapter = new SearchPagerAdapter(getActivity(), getChildFragmentManager());

        mFilterListener = (FilterListener) pagerAdapter.getFragments()[0];
        //SearchGamePagePresenter.View v = mFilterListener.
        mFilterListener.updateFields(query);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       // mFilterListener.updateFields(newText);
        return false;
    }

}
