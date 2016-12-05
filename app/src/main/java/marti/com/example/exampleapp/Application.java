package marti.com.example.exampleapp;

import android.support.multidex.MultiDexApplication;

import com.axa.amfcore.utils.imageloader.ImageLoader;

/**
 * Created by mferrando on 13/04/16.
 */
public class Application extends MultiDexApplication {

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        // https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/
        instance = this;

        // TODO agregar pinning
        ImageLoader.init(this, false);


    }

    public static Application getInstance() {
        return instance;
    }



}
