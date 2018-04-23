package marti.com.example.exampleapp.dataaccess;

/**
 * Created by mferrando on 19/03/2018.
 */

import java.util.ArrayList;

import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;
import rx.Observable;

public interface Repository {

    Observable<ArrayList<GameIGDB>> getGameByName(String name);
    Observable<ArrayList<GameIgdbDetail>> getGameById(int id);
}