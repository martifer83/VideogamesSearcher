package marti.com.example.exampleapp.dataaccess;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;

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
    void getGamesById(@NonNull DataCallback<ArrayList<GameIgdbDetail>> callback, String text);
    void getGamesByName(@NonNull DataCallback<ArrayList<GameIGDB>> callback, String Text);

}
