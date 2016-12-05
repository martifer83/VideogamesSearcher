package marti.com.example.exampleapp.dataaccess;

import marti.com.example.exampleapp.entity.GameIgdbResponse;
import marti.com.example.exampleapp.entity.GameResponse;
import marti.com.example.exampleapp.entity.SongResponse;
import retrofit.Callback;
import retrofit.http.GET;
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

    @GET("/games/search")
    void getGamesByName(
            @Query("token") String token,
            @Query("q") String q,
            Callback<GameIgdbResponse> callback
    );


    @GET("/games/{id}")
    void getGamesById(
            @Query("token") String token,
            @Path("id") String id,
            Callback<GameIgdbResponse> callback
    );


}
