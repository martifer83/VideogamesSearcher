package marti.com.example.exampleapp.dataaccess;

import android.support.annotation.NonNull;

import marti.com.example.exampleapp.entity.GameResponse;
import marti.com.example.exampleapp.entity.SongResponse;

/**
 * Created by mferrando on 02/06/16.
 */
public interface DataAccessGateway {

    /**
     * Executes getEvents action.
     * Async method.
     *
     * @param callback DataCallback listener.
     */
    void getSongs(@NonNull DataCallback<SongResponse> callback);
    void getGames(@NonNull DataCallback<GameResponse> callback, String text);

}
