package marti.com.example.exampleapp.dataaccess;

import java.util.ArrayList;

import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.dataaccess.rest.RestServices;
import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;
import rx.Observable;

/**
 * Created by mferrando on 19/03/2018.
 */

public class CloudRepository {

    private final RestServices<AppRestInterface> restService;

    public CloudRepository(RestServices<AppRestInterface> restService) {
        this.restService = restService;
    }

    public Observable<ArrayList<GameIGDB>> getGameByName(String name) {

        //marti.com.example.exampleapp.Application.getInstance().getApplicationContext().getString(R.string.igdb_api_key)

        return restService.getService().getGamesByName(name,"name,popularity,rating,cover",50 ,"1",marti.com.example.exampleapp.Application.getInstance().getApplicationContext().getString(R.string.igdb_api_key), "application/json");

    }

    public Observable<ArrayList<GameIgdbDetail>> getGameById(String id) {

        //marti.com.example.exampleapp.Application.getInstance().getApplicationContext().getString(R.string.igdb_api_key)

        return restService.getService().getGameById(id,"*", marti.com.example.exampleapp.Application.getInstance().getApplicationContext().getString(R.string.igdb_api_key), "application/json");

    }


}
