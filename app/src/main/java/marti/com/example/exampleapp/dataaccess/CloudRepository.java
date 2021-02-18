package marti.com.example.exampleapp.dataaccess;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import java.nio.charset.Charset;
import java.util.ArrayList;

import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.dataaccess.rest.RestServices;
import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
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

        String body;
        //body = "fields game.name, game.popularity, game.rating, game.cover, game.first_release_date; search &quot"+name+"&quot";//; where game != null";

        ///body = "search \u0022Mario\u0022"; //; where game != null";


        //rBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),body);
       // String test = rBody.toString();


 /// exemple body v4 fields name; search "Europa Universalis";

        body = "fields *; search \""+ name +"\";";

        // popularity no hi es
        //body = "fields game.name, game.popularity, game.rating, game.cover, game.first_release_date; search \""+ name +"\";";



        TypedInput requestBody = new TypedByteArray(
                "application/json", body.getBytes(Charset.forName("UTF-8")));


        //return restService.getService().getGamesByName(name,"name,popularity,rating,cover,first_release_date",50 ,"1",marti.com.example.exampleapp.Application.getInstance().getApplicationContext().getString(R.string.igdb_api_key), "application/json");
        return restService.getService().getGamesByName(marti.com.example.exampleapp.Application.getInstance().getApplicationContext().getString(R.string.token), marti.com.example.exampleapp.Application.getInstance().getApplicationContext().getString(R.string.client_id), requestBody);

    }

    public Observable<ArrayList<GameIgdbDetail>> getGameById(String id) {

        //marti.com.example.exampleapp.Application.getInstance().getApplicationContext().getString(R.string.igdb_api_key)



        // exemple:String body = "fields *; where id = 1942;";



        String body;
        body = "fields *; where id = " + id;

        TypedInput requestBody = new TypedByteArray(
                "application/json", body.getBytes(Charset.forName("UTF-8")));

        return restService.getService().getGameById( marti.com.example.exampleapp.Application.getInstance().getApplicationContext().getString(R.string.igdb_api_key), "application/json", requestBody);

    }


}
