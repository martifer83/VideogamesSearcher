package marti.com.example.exampleapp.view.widget;

/**
 * Created by mferrando on 07/06/16.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import marti.com.example.exampleapp.R;

/*
* This class wrapps a loading box, with it you decople your abstractions from the libraries you
* use to implement on it. You can change 'CircleProgressBar' with another lib and everything
        * should work with no modification.
        */
public class LoadingBox extends FrameLayout {

    // Views
    @Bind(android.R.id.progress)
    protected ProgressBar mProgressBar;

    public LoadingBox(Context context, @ColorRes int colorRes) {
        super(context);
        init(colorRes);
    }

    public LoadingBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(R.color.app_red);
    }

    public LoadingBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(R.color.app_red);  // canviar aqui i testejar
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(R.color.app_red);
    }

    private void init(@ColorRes int colorRes) {
        inflate(getContext(), R.layout.lay_loading_box, this);


        ButterKnife.bind(this);
        hideLoading();

        // Sacado de la libreria 'material-dialogs', DialogInit.setupProgressDialog
        /*int color = getResources().getColor(colorRes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mProgressBar.setIndeterminateDrawable(new CircularProgressDrawable(
                    color, getResources().getDimension(com.afollestad.materialdialogs.R.dimen.circular_progress_border)));
            MDTintHelper.setTint(mProgressBar, color, true);
        } else {
            MDTintHelper.setTint(mProgressBar, color);
        }*/
    }

    public void onDestroyView() {

        ButterKnife.unbind(this);
    }

    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

}
