package marti.com.example.exampleapp.view;

/**
 * Created by mferrando on 07/06/16.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import marti.com.example.exampleapp.Application;
import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.presenter.Presenter;
import marti.com.example.exampleapp.view.widget.LoadingBox;

/**
 * Common functionalities for fragments.
 * Handles life cycle of presenters.
 * Adds pretty toast (Crouton) for showXXX methods.
 */
public abstract class BaseFragment<T extends Presenter> extends Fragment implements Presenter.View {

    private T presenter;

    private LoadingBox mLoadingBox;

    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
    public @interface Params {
        int layout();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Params annotation = getClass().getAnnotation(Params.class);
        if (annotation == null) {
            throw new TypeNotPresentException(Params.class.getSimpleName(), null);
        }

        View view = inflater.inflate(annotation.layout(), container, false);
        ButterKnife.bind(this, view);
        createLoadingBox();
        if (view instanceof RelativeLayout) {
            ((RelativeLayout) view).addView(mLoadingBox);
            return view;
        } else {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            relativeLayout.addView(view);
            relativeLayout.addView(mLoadingBox);
            return relativeLayout;
        }
    }

    private void createLoadingBox() {
        mLoadingBox = new LoadingBox(getContext(), getLoadingBoxColor());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mLoadingBox.setLayoutParams(layoutParams);
    }

    protected int getLoadingBoxColor() {
        return R.color.app_main_blue;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.resume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
           presenter.destroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (presenter != null) {
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public Context getApplicationContext() {
        return Application.getInstance().getApplicationContext();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    protected T getPresenter() {
        return this.presenter;
    }

    protected void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showInfo(@StringRes int message) {
        showInfo(null, getString(message));
    }

    @Override
    public void showInfo(String title, String message) {
        String text = title == null ? message : title + "\n" + message;
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String title, String message) {
        String text = title == null ? message : title + "\n" + message;
        Snackbar snackbar = Snackbar.make(getView(), text, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void showLoading() {
        if (mLoadingBox != null) {
            mLoadingBox.showLoading();
        } else {
           /// TODO: add utilsDialog
       //     UtilsDialog.showProgressDialog(getContext());
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadingBox != null) {
            mLoadingBox.hideLoading();
        } else {
       //     UtilsDialog.dismissProgressDialog();
        }
    }

}
