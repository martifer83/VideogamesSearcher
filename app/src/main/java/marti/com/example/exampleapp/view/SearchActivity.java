package marti.com.example.exampleapp.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.axa.amfcore.utils.imageloader.ImageLoader;

import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.common.AbstractBaseActivity;
import marti.com.example.exampleapp.di.HasComponent;
import marti.com.example.exampleapp.di.components.ActivityComponent;
import marti.com.example.exampleapp.di.components.DaggerActivityComponent;

//@BaseActivity.Params(layout = R.layout.drawer_layout)
public class SearchActivity extends AbstractBaseActivity implements HasComponent<ActivityComponent> {

   // @Bind(R.id.toolbar)
   // protected Toolbar toolbar;

    private ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageLoader.init(this, false);

        getSupportActionBar().setTitle("GameSearch");

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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void injectDependencies(Bundle savedInstanceState) {
        component = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        component.inject(this);
    }
    @Override
    public ActivityComponent getComponent() {
        return component;
    }
}
