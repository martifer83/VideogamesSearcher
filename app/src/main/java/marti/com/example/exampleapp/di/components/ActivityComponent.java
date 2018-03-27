package marti.com.example.exampleapp.di.components;

import android.support.v7.app.AppCompatActivity;

import dagger.Component;
import marti.com.example.exampleapp.di.PerActivity;
import marti.com.example.exampleapp.di.modules.ActivityModule;
import marti.com.example.exampleapp.presenter.SearchGamePagePresenter;
import marti.com.example.exampleapp.view.SearchActivity;
import marti.com.example.exampleapp.view.SearchFragment;
import marti.com.example.exampleapp.view.SearchGamesIgdbPageFragment;

/**
 * Created by mferrando on 20/03/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    AppCompatActivity activity();

    void inject(SearchActivity searchActivity);

    void inject(SearchFragment searchFragment);

    void inject(SearchGamesIgdbPageFragment searchGamesIgdbPageFragment);

    void inject(SearchGamePagePresenter SearchGamePagePresenter);

}
