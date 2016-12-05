package marti.com.example.exampleapp.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import marti.com.example.exampleapp.view.SearchGamesIgdbPageFragment;

/**
 * Created by mferrando on 07/06/16.
 */
public class SearchPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 1;
    private static final int TAB_SONGS = 0;
    private static final int TAB_RIDES = 1;

    private Context mContext;
    private Fragment[] fragments = new Fragment[]{new SearchGamesIgdbPageFragment()};  // expand to multiple fragment
//    new Fragment[]{new SearchGamesPageFragment()};  // expand to multiple fragment

    public SearchPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    //@Override
    public Fragment getItem(int position) {
       return fragments[0];
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB_SONGS:
                return "events";
            case TAB_RIDES:
                return "trips";
            default:
                return super.getPageTitle(position);
        }
    }

    public Fragment[] getFragments() {
        return fragments;
    }
}

