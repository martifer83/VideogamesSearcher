package marti.com.example.exampleapp.di.components;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import marti.com.example.exampleapp.dataaccess.Repository;
import marti.com.example.exampleapp.di.modules.ApplicationModule;
import rx.Scheduler;

/**
 * Created by mferrando on 20/03/2018.
 */
// Constraints this component to one-per-application or unscoped bindings.
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context context();

    @Named("subscriber")
    Scheduler subscriber();

    //@Provides
    @Named("observer")
    Scheduler observer();

    Repository repository();
}
