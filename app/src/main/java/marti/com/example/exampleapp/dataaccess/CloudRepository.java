package marti.com.example.exampleapp.dataaccess;

import java.util.ArrayList;

import marti.com.example.exampleapp.dataaccess.rest.RestServices;
import marti.com.example.exampleapp.entity.GameIGDB;
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
        return restService.getService().getGamesByName(name,"","","","");
    }

}
