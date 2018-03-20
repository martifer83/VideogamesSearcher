package marti.com.example.exampleapp.business.logic;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import marti.com.example.exampleapp.dataaccess.Repository;
import marti.com.example.exampleapp.entity.GameIGDB;
import rx.Observable;
import rx.Scheduler;


/**
 * Created by mferrando on 19/03/2018.
 */

public class GetGameByNameUseCase extends AbstractUseCase<ArrayList<GameIGDB>> {

    private String name;

    @Inject
    public GetGameByNameUseCase(@NonNull Repository repository,
                        @Named("subscriber") @NonNull Scheduler subscriberScheduler,
                        @Named("observer") @NonNull Scheduler observableScheduler) {
        super(repository, subscriberScheduler, observableScheduler);
    }

    public void setParameters(String name) {
        this.name = name;
    }

    @Override
    protected Observable<ArrayList<GameIGDB>> buildUseCaseObservable() {
        return repository.getGameByName(name);
    }

}
