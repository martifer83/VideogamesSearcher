package marti.com.example.exampleapp.dataaccess;

import android.support.annotation.NonNull;

import marti.com.example.exampleapp.entity.ErrorStatus;

/**
 * Created by mferrando on 06/06/16.
 */
public class RestCallbackImpl<T> implements RestCallback<T> {

    protected final DataCallback<T> mDataCallback;

    public RestCallbackImpl(@NonNull DataCallback<T> dataCallback) {

        // PRECONDITIONS
        if(dataCallback == null) {
            throw new IllegalArgumentException("BusinessCallback is null");
        }

        mDataCallback = dataCallback;
    }


    @Override
    public void onSuccess(final T data) {
        mDataCallback.onSuccess(data);
    }

    @Override
    public void onNetworkConnectionFailed() {
        mDataCallback.onNetworkConnectionFailed();
    }

    @Override
    public void onSessionExpired() {
        mDataCallback.onSessionExpired();
    }

    @Override
    public void onFailure(final ErrorStatus error) {
        mDataCallback.onFailure(error);
    }

}