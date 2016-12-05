package marti.com.example.exampleapp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mferrando on 14/06/16.
 */

//  http://www.giantbomb.com/api/games/?api_key=b1f2d14f1beaca51ed8288a5ca1faaf97753fcfa&format=json&filter=name:Tom
public class GameResponse {

    private ArrayList<Game> results;


    public List<Game> getMyGames() {
        return results;
    }



    public void setMyGames(ArrayList<Game> myGames) {
        this.results = myGames;
    }

}
