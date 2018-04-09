package marti.com.example.exampleapp.dataaccess.rest;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.dataaccess.AppRestInterface;
import marti.com.example.exampleapp.dataaccess.DataAccessGatewayIgdb;
import marti.com.example.exampleapp.dataaccess.DataCallback;
import marti.com.example.exampleapp.dataaccess.RestCallbackImpl;
import marti.com.example.exampleapp.dataaccess.RetrofitCallbackImpl;
import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;

/**
 * Created by mferrando on 23/06/16.
 */
public class DataAccessGatewayImplIgdb implements DataAccessGatewayIgdb { // Must have 'Package' visibility

    private final Context mContext;
    private static DataAccessGatewayImplIgdb sDataAccessGateway;
    private final RestServices<AppRestInterface> restService;

    public static DataAccessGatewayImplIgdb getInstance(@NonNull Context context) {

        // PRECONDITIONS
        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        }

        if (sDataAccessGateway == null) {
            sDataAccessGateway = new DataAccessGatewayImplIgdb(context);
        }

        return sDataAccessGateway;
    }

    private DataAccessGatewayImplIgdb(Context context) {
        mContext = context;

        restService = new RestServices<>(
                AppRestInterface.class,
                context.getString(R.string.igdb_url));

    }

    private AppRestInterface getService() {
        return restService.getService();
    }

    @Override
    public void getGamesById(@NonNull DataCallback<ArrayList<GameIgdbDetail>> callback, String id) {

        getService().getGamesById(id,mContext.getString(R.string.igdb_api_key),"application/json",
                new RetrofitCallbackImpl<>(new RestCallbackImpl<>(callback)));
    }

    @Override
    public void getGamesByName(@NonNull DataCallback<ArrayList<GameIGDB>> callback, String Text) {
        // https://stackoverflow.com/questions/36656827/how-to-parse-list-of-json-objects-surrounded-by-using-retrofit-and-gson
        getService().getGamesByName(Text,"name","1",mContext.getString(R.string.igdb_api_key),"application/json");
    }
}
