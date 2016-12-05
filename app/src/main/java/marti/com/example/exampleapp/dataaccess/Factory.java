package marti.com.example.exampleapp.dataaccess;

import android.content.Context;
import android.support.annotation.NonNull;

import marti.com.example.exampleapp.dataaccess.rest.DataAccessGatewayImplIgdb;

/**
 * Created by mferrando on 07/06/16.
 */
public class Factory {

    /**
     * Creates a data access gateway for none criteria.
     *
     * @param context
     * @return The data access gateway.
     */
    public static DataAccessGateway create(@NonNull Context context) {
        return DataAccessGatewayImpl.getInstance(context);
    }

    public static DataAccessGatewayIgdb createIgdbData(@NonNull Context context) {
        return DataAccessGatewayImplIgdb.getInstance(context);
    }

    /*@Override
    public HomePresenter createPresenter() {
        return DaggerHomeComponent.builder().appComponent(mApp.component()).build().getPresenter();
    }*/




}
