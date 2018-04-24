package marti.com.example.exampleapp.common;

import android.util.Log;

import java.util.ArrayList;

import marti.com.example.exampleapp.BuildConfig;
import marti.com.example.exampleapp.common.error.ErrorManagerInterface;
import marti.com.example.exampleapp.common.error.ErrorMessage;
import rx.Subscriber;
//import com.fernandocejas.frodo.annotation.RxLogSubscriber;

//@RxLogSubscriber
public abstract class AbstractSubscriber<T> extends Subscriber<T> {

    private final ErrorManagerInterface errorManager;

    public AbstractSubscriber(ErrorManagerInterface errorManager) {
        this.errorManager = errorManager;
    }

    @Override
    public void onNext(T t) {
        // no-op by default.
    }

    @Override
    public void onCompleted() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable e) {
        if (BuildConfig.DEBUG) Log.e("Subscriber", getClass().getName(), e);
        ErrorMessage errorMessage = errorManager.processError(e);
        onError(errorMessage);
    }

    protected abstract void onError(ErrorMessage errorMessage);

}

