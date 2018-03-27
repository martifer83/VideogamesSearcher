package marti.com.example.exampleapp;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.axa.amfcore.utils.imageloader.ImageLoader;

import marti.com.example.exampleapp.di.components.ApplicationComponent;
import marti.com.example.exampleapp.di.components.DaggerApplicationComponent;
import marti.com.example.exampleapp.di.modules.ApplicationModule;

/**
 * Created by mferrando on 13/04/16.
 */
public class Application extends android.app.Application {

    private static Application instance;

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/
        instance = this;

        // TODO agregar pinning
        ImageLoader.init(this, false);
        initializeInjector();
    }

    public static Application getInstance() {
        return instance;
    }

    private void initializeInjector() {
        applicationComponent = prepareAppComponent();
    }

    @NonNull
    ApplicationComponent prepareAppComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    @VisibleForTesting
    public void setComponent(ApplicationComponent applicationComponent) {
        Application.applicationComponent = applicationComponent;
    }

}
