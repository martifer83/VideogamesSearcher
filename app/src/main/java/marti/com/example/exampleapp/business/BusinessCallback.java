package marti.com.example.exampleapp.business.logic;

/**
 * Created by mferrando on 03/06/16.
 */

import marti.com.example.exampleapp.entity.ErrorStatus;

/**
 * Common interface for callbacks.
 * @param <T> Data to retrieve.
 */
public interface BusinessCallback<T> {

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
