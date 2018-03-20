package marti.com.example.exampleapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mferrando on 20/03/2018.
 */

/**The @PerActivity is a custom scoping annotation to permit objects whose lifetime should conform to the life of the activity to be memorized in the correct component.**/
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}