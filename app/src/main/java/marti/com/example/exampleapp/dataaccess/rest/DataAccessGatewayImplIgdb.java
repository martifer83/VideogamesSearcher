package marti.com.example.exampleapp.dataaccess.rest;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.dataaccess.AppRestInterface;
import marti.com.example.exampleapp.dataaccess.DataAccessGatewayIgdb;
import marti.com.example.exampleapp.dataaccess.DataCallback;
import marti.com.example.exampleapp.dataaccess.RestCallbackImpl;
import marti.com.example.exampleapp.dataaccess.RetrofitCallbackImpl;
import marti.com.example.exampleapp.entity.GameIgdbResponse;

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
    public void getGamesByName(@NonNull DataCallback<List<GameIgdbResponse>> callback, String queryText) {
    // https://stackoverflow.com/questions/36656827/how-to-parse-list-of-json-objects-surrounded-by-using-retrofit-and-gson
        String nom = "Fallout";

        getService().getGamesByName(nom,"name","1",mContext.getString(R.string.igdb_api_key),"application/json",
                new RetrofitCallbackImpl<>(new RestCallbackImpl<>(callback)));
    }

    @Override
    public void getGamesById(@NonNull DataCallback<GameIgdbResponse> callback,String id) {


        getService().getGamesById(mContext.getString(R.string.igdb_api_key), id,
                new RetrofitCallbackImpl<>(new RestCallbackImpl<>(callback)));
    }

}
