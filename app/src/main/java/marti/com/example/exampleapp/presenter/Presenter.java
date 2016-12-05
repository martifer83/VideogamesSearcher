package marti.com.example.exampleapp.presenter;

/**
 * Created by mferrando on 29/04/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Represents a Presenter in a model view presenter (MVP) pattern.
 */
public interface Presenter {
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onActivityResult() method.
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);

    /**
     * Default interface for screen common (most used) operations.
     */
    interface View {
        /**
         * @return The current context.
         */
//        @NonNull
        Context getContext();

        /**
         * @return The application context.
         */
//        @NonNull
        Context getApplicationContext();

        /**
         * @return The activity.
         */
        @NonNull
        Activity getActivity();

        /**
         * @return The fragment, if any.
         */
        @Nullable
        Fragment getFragment();

        /**
         * Add here your calls to your widget/library to show user info messages.
         *
         * @param title   Message box title, null when not needed.
         * @param message Message box text.
         */
        void showInfo(String title, String message);
        void showInfo(@StringRes int message);

        /**
         * Add here your calls to your widget/library to show user error messages.
         *
         * @param title   Message box title, null when not needed.
         * @param message Message box text.
         */
        void showError(String title, String message);

        /**
         * Shows loading widget/library or another kind of loading view.
         */
        void showLoading();

        /**
         * Hides loading widget/library or another kind of loading view.
         */
        void hideLoading();

    }

}
