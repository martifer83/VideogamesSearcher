package marti.com.example.exampleapp.view;

/**
 * Created by mferrando on 16/06/16.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
    public @interface Params {
        int layout();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Params annotation = getClass().getAnnotation(Params.class);
        if (annotation == null) {
            throw new TypeNotPresentException(Params.class.getSimpleName(), null);
        }
        setContentView(annotation.layout());
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /*@Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return SecurePreferences.getSecurePreferences(this, name);
    }*/

}
