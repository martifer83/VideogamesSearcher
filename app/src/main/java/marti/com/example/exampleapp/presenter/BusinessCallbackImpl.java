package marti.com.example.exampleapp.presenter;

/**
 * Created by mferrando on 06/06/16.
 */

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.business.logic.BusinessCallback;
import marti.com.example.exampleapp.entity.ErrorStatus;

/**
 * Receives bussines layer callbacks.
 * Defines the operations for network, session and common errors.
 */
public abstract class BusinessCallbackImpl<T> implements BusinessCallback<T> {

    private final Presenter.View mView;

    public BusinessCallbackImpl(Presenter.View view) {
        mView = view;
    }

    @Override
    public void onSuccess(final T data) {
        if (mView == null || mView.getContext() == null) {
            Log.d(getClass().getName(), "mView is null!!!");
            return;
        }

        (new Handler(Looper.getMainLooper())).post(new Runnable() {
            @Override
            public void run() {
                successUIThread(data);
            }
        });
    }

    @Override
    public void onNetworkConnectionFailed() {
        if (mView == null || mView.getContext() == null) {
            Log.d(getClass().getName(), "mView is null!!!");
            return;
        }

        mView.hideLoading();
        Snackbar snackbar = Snackbar.make(mView.getFragment().getView(), "R.string.error_network", Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(mView.getContext().getResources().getColor(R.color.app_black))  // color primary
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRetryClick();
                    }
                });
        snackbar.getView().setBackgroundColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void onSessionExpired() {
        ErrorStatus error = new ErrorStatus();
        error.setCode(-1);
        error.setMessage("onSessionExpired");
        onFailure(error);
    }

    @Override
    public void onFailure(final ErrorStatus error) {
        (new Handler(Looper.getMainLooper())).post(new Runnable() {
            @Override
            public void run() {
                if (mView != null && mView.getContext() != null) {
                    mView.hideLoading();

                    if (error.getCode() == -1) {
                        mView.showError(error.getTitle(), error.getMessage());
                    }
                   /* else if (error.getCode() == 412 && error.getErrorServerType() != null) { // Errores del servidor localizados
                        mView.showError(null, mView.getContext().getString(error.getErrorServerType().getStringRes()));
                    }
                    else {
                        mView.showError(null, mView.getContext().getString(R.string.error_unknown));
                    }
                    */
                }
            }
        });
    }

    /**
     * Runs on UI thread.
     * @param data
     */
    public void successUIThread(T data) {}

    public abstract void onRetryClick();
}

