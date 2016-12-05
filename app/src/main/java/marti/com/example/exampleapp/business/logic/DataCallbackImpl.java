package marti.com.example.exampleapp.business.logic;

import android.support.annotation.NonNull;

import marti.com.example.exampleapp.dataaccess.DataCallback;
import marti.com.example.exampleapp.entity.ErrorStatus;

/**
 * Created by mferrando on 03/06/16.
 */


class DataCallbackImpl<T> implements DataCallback<T> {

    protected final marti.com.example.exampleapp.business.logic.BusinessCallback<T> mBusinessCallback;

    public DataCallbackImpl(@NonNull marti.com.example.exampleapp.business.logic.BusinessCallback<T> businessCallback) {

        // PRECONDITIONS
        if(businessCallback == null) {
            throw new IllegalArgumentException("BusinessCallback is null");
        }

        mBusinessCallback = businessCallback;
    }

    @Override
    public void onSuccess(final T data) {
        mBusinessCallback.onSuccess(data);
    }

    @Override
    public void onNetworkConnectionFailed() {
        mBusinessCallback.onNetworkConnectionFailed();
    }

    @Override
    public void onSessionExpired() {
        mBusinessCallback.onSessionExpired();
    }

    @Override
    public void onFailure(final ErrorStatus error) {
        mBusinessCallback.onFailure(error);
    }

}
