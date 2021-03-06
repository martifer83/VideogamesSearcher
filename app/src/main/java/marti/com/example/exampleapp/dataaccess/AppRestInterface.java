package marti.com.example.exampleapp.dataaccess;

import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;

import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;
import marti.com.example.exampleapp.entity.GameResponse;
import marti.com.example.exampleapp.entity.SongResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedInput;
import rx.Observable;

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

    // https://stackoverflow.com/questions/24610243/retrofit-error-url-query-string-must-not-have-replace-block
    // Query original: https://api-endpoint.igdb.com/games/?search=Fallout&fields=name&filter[version_parent][not_exists]=1
    // Query with cover: https://api-endpoint.igdb.com/games/?search=Fallout&fields=name,cover&filter[version_parent][not_exists]=1
    // @GET("/games/&fields=name&filter[version_parent][not_exists]=1")
    /*@GET("/games/")   /// funciona
    void getGamesByName(
            @Query("search") String nom,
            @Query("fields") String fields,
            @Query("filter[version_parent][not_exists]") String filter,

            @Header("user-key") String userkey,
            @Header("Accept") String accept,
            Callback<ArrayList<GameIGDB>> callback
    );*/

    // RX API V2
    /*@GET("/games/")
    Observable<ArrayList<GameIGDB>> getGamesByName(
            @Query("search") String nom,
            @Query("fields") String fields,
            @Query("limit") int limit,
            @Query("filter[version_parent][not_exists]") String filter,

            @Header("user-key") String userkey,
            @Header("Accept") String accept
    );*/

    // Migrate to V4


    @POST("/search/")
    Observable<ArrayList<GameIGDB>> getGamesByName(
            @Header("Authorization") String auth,
            @Header("Client-ID") String client_id,
            @Body TypedInput body
    );
///

   /* @GET("/games/search") ///games/?search=Halo
    void getGamesByName(
            @Query("token") String token,
            @Query("q") String q,
            Callback<GameIgdbResponse> callback
    );*/

   /*
    Get all information from a specific game
    /games/1942?fields=*
    */

    /*@GET("/games/{id}?fields*")
    void getGamesById(
            @Path("id") String id,
            @Header("user-key") String userkey, //
            @Header("Accept") String accept,
            Callback<ArrayList<GameIgdbDetail>> callback
    );
*/
    // getGameByIdExample
    //https://api-endpoint.igdb.com/games/1942?fields=name&filter[version_parent][not_exists]=1
    //https://api-endpoint.igdb.com/games/1942?fields=*

    @POST("/games/")
    Observable<ArrayList<GameIgdbDetail>> getGameById(
            @Header("user-key") String userkey,
            @Header("Accept") String accept,
            @Body TypedInput body
    );

   /*@GET("/games/{id}")
    void getGamesById(
            @Query("token") String token,
            @Path("id") String id,
            Callback<GameIgdbResponse> callback
    );*/

}
