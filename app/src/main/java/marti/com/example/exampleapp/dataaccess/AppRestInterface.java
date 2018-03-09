package marti.com.example.exampleapp.dataaccess;

import java.util.List;

import marti.com.example.exampleapp.entity.GameIgdbResponse;
import marti.com.example.exampleapp.entity.GameResponse;
import marti.com.example.exampleapp.entity.SongResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by mferrando on 25/05/16.
 */
public interface AppRestInterface {

    @GET("/search")
    void getSongDetails(
            @Query("term") String term,
            Callback<SongResponse> callback
    );

    @GET("/games")
    void getGameDetails(
            @Query("api_key") String api_key,
            @Query("format") String format,
            @Query("filter") String filter,
            Callback<GameResponse> callback
    );
    // Query original: https://api-endpoint.igdb.com/games/?search=Fallout&fields=name&filter[version_parent][not_exists]=1
    // @GET("/games/&fields=name&filter[version_parent][not_exists]=1") ///games/?search=Halo
    @GET("/games/")   /// funciona
    void getGamesByName(
            @Query("search") String nom,
            @Query("fields") String fields,
            @Query("filter[version_parent][not_exists]") String filter,

            @Header("user-key") String userkey,
            @Header("Accept") String accept,
            Callback<List<GameIgdbResponse>> callback
    );



   /* @GET("/games/search") ///games/?search=Halo
    void getGamesByName(
            @Query("token") String token,
            @Query("q") String q,
            Callback<GameIgdbResponse> callback
    );*/


    @GET("/games/{id}")
    void getGamesById(
            @Query("token") String token,
            @Path("id") String id,
            Callback<GameIgdbResponse> callback
    );


}
