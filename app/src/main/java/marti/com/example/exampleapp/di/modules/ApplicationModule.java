package marti.com.example.exampleapp.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import marti.com.example.exampleapp.dataaccess.AppRestInterface;
import marti.com.example.exampleapp.dataaccess.CloudRepository;
import marti.com.example.exampleapp.dataaccess.Repository;
import marti.com.example.exampleapp.dataaccess.RepositoryImpl;
import marti.com.example.exampleapp.dataaccess.rest.RestServices;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mferrando on 20/03/2018.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Named("subscriber")
    @Singleton
    Scheduler provideSubscriberScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named("observer")
    @Singleton
    Scheduler provideObserverScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    Repository provideRepository(CloudRepository cloudRepository
                                 ) {
        return new RepositoryImpl(cloudRepository);
    }

    @Provides
    @Singleton
    RestServices<AppRestInterface> provideRestServices(
                                     ) {
        return new RestServices<AppRestInterface>();
    }

    @Provides
    @Singleton
    CloudRepository provideCloudRepository(RestServices<AppRestInterface> restService) {
        return new CloudRepository(restService);
    }

}
