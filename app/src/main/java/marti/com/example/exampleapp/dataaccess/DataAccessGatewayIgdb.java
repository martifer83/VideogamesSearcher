package marti.com.example.exampleapp.dataaccess;

import android.support.annotation.NonNull;

import java.util.List;

import marti.com.example.exampleapp.entity.GameIgdbResponse;

/**
 * Created by mferrando on 23/06/16.
 */
public interface DataAccessGatewayIgdb {

    /**
     * Executes getEvents action.
     * Async method.
     *
     * @param callback DataCallback listener.
     */
    void getGamesById(@NonNull DataCallback<GameIgdbResponse> callback, String text);
    void getGamesByName(@NonNull DataCallback<List<GameIgdbResponse>> callback, String Text);

}
