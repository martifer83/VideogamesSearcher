package marti.com.example.exampleapp.di.components;

import dagger.Component;
import marti.com.example.exampleapp.di.PerActivity;
import marti.com.example.exampleapp.di.modules.ActivityModule;
import marti.com.example.exampleapp.view.SearchActivity;
import marti.com.example.exampleapp.view.SearchGamesIgdbPageFragment;

/**
 * Created by mferrando on 20/03/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SearchGamesIgdbPageFragment searchGamesIgdbPageFragment);

    void inject(SearchActivity searchActivity);

}
