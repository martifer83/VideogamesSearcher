package marti.com.example.exampleapp.dataaccess;

import marti.com.example.exampleapp.entity.ErrorStatus;

/**
 * Created by mferrando on 25/05/16.
 */
public interface DataCallback<T> {

    /**
     * Success callback.
     * @param data
     */
    void onSuccess(T data);

    /**
     * No network connection error.
     */
    void onNetworkConnectionFailed();

    /**
     * Session expired error.
     */
    void onSessionExpired();


    /**
     * Other error.
     * @param error The error.
     */
    void onFailure(ErrorStatus error);

}
