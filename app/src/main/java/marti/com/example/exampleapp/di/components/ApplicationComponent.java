package marti.com.example.exampleapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import marti.com.example.exampleapp.di.modules.ApplicationModule;

/**
 * Created by mferrando on 20/03/2018.
 */
// Constraints this component to one-per-application or unscoped bindings.
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    //Context context();
}
