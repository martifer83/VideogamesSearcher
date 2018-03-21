package marti.com.example.exampleapp.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import marti.com.example.exampleapp.Application;
import marti.com.example.exampleapp.di.components.ApplicationComponent;
import marti.com.example.exampleapp.di.modules.ActivityModule;

/**
 * Created by mferrando on 21/03/2018.
 */

public abstract class AbstractBaseActivity extends AppCompatActivity {

    private ActivityModule activityModule;

    protected ActivityModule getActivityModule() {
        if (activityModule == null) {
            activityModule = new ActivityModule(this);
        }
        return activityModule;
    }

    protected ApplicationComponent getApplicationComponent() {
        return Application.getApplicationComponent();
    }

    protected abstract void injectDependencies(Bundle savedInstanceState);

}
