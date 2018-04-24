package marti.com.example.exampleapp.di.modules;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import marti.com.example.exampleapp.business.logic.GetGameByIdUseCase;
import marti.com.example.exampleapp.business.logic.GetGameByNameUseCase;
import marti.com.example.exampleapp.di.PerActivity;
import marti.com.example.exampleapp.presenter.SearchGamePagePresenter;

/**
 * Created by mferrando on 20/03/2018.
 */
@SuppressWarnings("PMD.GodClass")
@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    SearchGamePagePresenter provideSearchGamePagePresenter(
           // SearchGamePagePresenter.View view,
            GetGameByNameUseCase getGameByNameUseCase,
            GetGameByIdUseCase getGameByIdUseCase
    ) {
        return new SearchGamePagePresenter(getGameByNameUseCase, getGameByIdUseCase);
    }
}
