package marti.com.example.exampleapp.presenter;

import android.content.Intent;

/**
 * Created by mferrando on 29/04/16.
 */
public abstract class BasePresenter implements Presenter {

    @Override
    public void resume() { }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { }

}
