package marti.com.example.exampleapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.axa.amfcore.utils.imageloader.ImageLoader;

import marti.com.example.exampleapp.R;

//@BaseActivity.Params(layout = R.layout.drawer_layout)
public class SearchActivity extends AppCompatActivity {

   // @Bind(R.id.toolbar)
   // protected Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // ButterKnife.bind(this);

        setContentView(R.layout.activity_main);

        ImageLoader.init(this, false);

        getSupportActionBar().setTitle("GameSearch");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        // launch fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new SearchFragment()).commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // ButterKnife.unbind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

       // getMenuInflater().inflate(R.menu.menu_home, menu);
        //MenuItem item = menu.findItem(R.id.badge);
        //MenuItemCompat.setActionView(item, R.layout.badge_layout);
        //RelativeLayout badgeLayout = (RelativeLayout)  MenuItemCompat.getActionView(item);


//        MenuItem searchItem = menu.findItem(R.id.action_search);

  //      SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

    /*    SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }
*/


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
