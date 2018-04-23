package marti.com.example.exampleapp.business.logic;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import marti.com.example.exampleapp.dataaccess.Repository;
import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;
import rx.Observable;
import rx.Scheduler;

public class GetGameByIdUseCase extends AbstractUseCase<ArrayList<GameIgdbDetail>> {

private int id;

@Inject
public GetGameByIdUseCase(@NonNull Repository repository,
@Named("subscriber") @NonNull Scheduler subscriberScheduler,
@Named("observer") @NonNull Scheduler observableScheduler) {
        super(repository, subscriberScheduler, observableScheduler);
        }

public void setParameters(int id) {
        this.id = id;
        }

@Override
protected Observable<ArrayList<GameIgdbDetail>> buildUseCaseObservable() {
        return repository.getGameById(id);
        }
}
