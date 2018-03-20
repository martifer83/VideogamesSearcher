package marti.com.example.exampleapp.di.modules;

import android.app.Application;

import dagger.Module;

/**
 * Created by mferrando on 20/03/2018.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

}
