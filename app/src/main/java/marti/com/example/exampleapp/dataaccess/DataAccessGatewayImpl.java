package marti.com.example.exampleapp.dataaccess;

import android.content.Context;
import android.support.annotation.NonNull;

import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.dataaccess.rest.RestServices;
import marti.com.example.exampleapp.entity.GameResponse;
import marti.com.example.exampleapp.entity.SongResponse;

/**
 * Created by mferrando on 25/05/16.
 */
class DataAccessGatewayImpl implements DataAccessGateway { // Must have 'Package' visibility

    private final Context mContext;
    private static DataAccessGatewayImpl sDataAccessGateway;
    private final RestServices<AppRestInterface> restService;

    public static DataAccessGatewayImpl getInstance(@NonNull Context context) {

        // PRECONDITIONS
        if (context == null) {
            //throw new IllegalArgumentException("Context is null");


        }

        if (sDataAccessGateway == null) {
            sDataAccessGateway = new DataAccessGatewayImpl(context);
        }

        return sDataAccessGateway;
    }

    private DataAccessGatewayImpl(Context context) {
        mContext = context;

        /*RequestInterceptor requestInterceptor = new RequestInterceptor() {

            @Override
            public void intercept(RequestFacade request) {
                String accessToken = AppTokenManager.getInstance(mContext).getAppAccessToken();
                if (!TextUtils.isEmpty(accessToken)) {
                    request.addHeader("Authorization", "Bearer " + accessToken);
                }
            }

        };*/

        restService = new RestServices<>(
                /*AppRestInterface.class,
                context.getString(R.string.giantbomb_url)*/);
        //restService.setAuthenticator(new TokenAuthenticator(context, getService()));
    }

    private AppRestInterface getService() {
        return restService.getService();
    }


    @Override
    public void getSongs(@NonNull DataCallback<SongResponse> callback) {
        getService().getSongDetails("Jack Johnson",
                new RetrofitCallbackImpl<>(new RestCallbackImpl<>(callback)));
    }

    public void getGames(@NonNull DataCallback<GameResponse> callback, String queryText) {

        String text = "name:" + queryText;

        getService().getGameDetails(mContext.getString(R.string.giantbomb_api_key), "json", text,
                new RetrofitCallbackImpl<>(new RestCallbackImpl<>(callback)));
    }

}
