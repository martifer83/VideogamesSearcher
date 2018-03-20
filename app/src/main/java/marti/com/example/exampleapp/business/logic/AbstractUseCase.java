package marti.com.example.exampleapp.business.logic;

import android.support.annotation.NonNull;

import marti.com.example.exampleapp.dataaccess.Repository;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mferrando on 19/03/2018.
 */

public abstract class AbstractUseCase<T> {

    protected final Repository repository;
    private final Scheduler subscriberScheduler;
    private final Scheduler observableScheduler;
    private final CompositeSubscription compositeSubscription;
//    private Subscription subscription;

    protected AbstractUseCase(@NonNull Repository repository,
                              @NonNull Scheduler subscriberScheduler,
                              @NonNull Scheduler observableScheduler) {

        this.repository = repository;
        this.subscriberScheduler = subscriberScheduler;
        this.observableScheduler = observableScheduler;
        compositeSubscription = new CompositeSubscription();
    }

    protected abstract Observable<T> buildUseCaseObservable();


    public void subscribe(Subscriber<T> subscriber) {
        Subscription subscription = this.buildUseCaseObservable()
                .subscribeOn(subscriberScheduler)
                .observeOn(observableScheduler)
                .subscribe(subscriber);
        compositeSubscription.add(subscription);
    }

    public void subscribe() {
        Subscription subscription = this.buildUseCaseObservable()
                .subscribeOn(subscriberScheduler)
                .observeOn(observableScheduler)
                .subscribe();
        compositeSubscription.add(subscription);
    }

    public void unsubscribe() {
        if(!compositeSubscription.isUnsubscribed()){
            compositeSubscription.clear();
        }
    }

    public boolean isUnsubscribed() {
        return compositeSubscription.isUnsubscribed();
    }
}