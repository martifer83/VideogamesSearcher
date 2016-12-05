package marti.com.example.exampleapp.dataaccess;

/**
 * Created by mferrando on 02/06/16.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import java.net.SocketTimeoutException;

import marti.com.example.exampleapp.entity.ErrorStatus;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.ConversionException;

/**
 * Default implementation for callbacks.
 */
public class RetrofitCallbackImpl<T> implements retrofit.Callback<T> {

    private static final String TAG = "RetrofitCallbackImpl";

    private final RestCallback<T> mRestCallback;

    public RetrofitCallbackImpl(@NonNull final RestCallback<T> restCallback) {

        // PRECONDITIONS
        if (restCallback == null) {
            throw new IllegalArgumentException("BusinessCallback is null");
        }

        mRestCallback = restCallback;
    }

    @Override
    public void success(T data, @NonNull Response response) {
        mRestCallback.onSuccess(data);
    }

    @Override
    public void failure(@NonNull RetrofitError retrofitError) {
        Log.d(TAG, "retrofitError: " + retrofitError.getMessage());

        // onNetworkConnectionFailed
        if (networkFailure(retrofitError)) {
            mRestCallback.onNetworkConnectionFailed();
        }
        // onSessionExpired
        else if (sessionFailure(retrofitError)) {
            mRestCallback.onSessionExpired();
        }
        // onCertificateFailed
        else if (certificationFailure(retrofitError)) {
            // TODO: Setup here your pinning errors
            ErrorStatus error = new ErrorStatus();
            error.setCode(-1);
            error.setTitle("Certificate pinning failure");
            error.setMessage("Certificate pinning failure");

            Log.d(TAG, error.toString());

            mRestCallback.onFailure(error);
        }
        // onFailure
        else {
            ErrorStatus error = new ErrorStatus();
            Response response = retrofitError.getResponse();
            if (response == null) {
                error.setCode(-1);
                error.setMessage(retrofitError.getMessage());
            } else {
                // Setup here your backend errors
                if (response.getStatus() == 412) {
                    try {
                   /*     ErrorServerResponse errorServerResponse = (ErrorServerResponse) retrofitError.getBodyAs(ErrorServerResponse.class);
                        ErrorServerResponse.ErrorServer errorServer = errorServerResponse.getError();
                        error.setCode(errorServer.getStatus());
                        error.setErrorServerType(errorServer.getError_key());*/
                    } catch (RuntimeException e) {
                        error.setCode(response.getStatus());
                        error.setTitle(response.getReason());
                    }
                } else {
                    error.setCode(response.getStatus());
                    error.setTitle(response.getReason());
                }
            }

            Log.d(TAG, error.toString());

            mRestCallback.onFailure(error);
        }

    }


    /**
     * Checks certificate failure
     *
     * @param retrofitError
     * @return
     */
    private boolean certificationFailure(RetrofitError retrofitError) {
        boolean certificateError = false;
        if (retrofitError.getCause() instanceof javax.net.ssl.SSLException) {
            certificateError = true;
        }
        return certificateError;
    }

    /**
     * Checks session failure
     *
     * @param retrofitError
     * @return
     */
    private boolean sessionFailure(RetrofitError retrofitError) {
        boolean sessionError = false;
        // TODO: Pass Axa sessionFailure is instanceof ConversionException
        // TODO: Check for session failure onother services
        if (retrofitError.getCause() instanceof ConversionException) {
            sessionError = true;
        }
        return sessionError;
    }

    /**
     * Checks network failure
     *
     * @param retrofitError
     * @return
     */
    private boolean networkFailure(RetrofitError retrofitError) {
        boolean networkError = false;
        if (retrofitError.getKind().equals(RetrofitError.Kind.NETWORK) ||
                retrofitError.getCause() instanceof SocketTimeoutException) {
            networkError = true;
        }
        return networkError;
    }

}
