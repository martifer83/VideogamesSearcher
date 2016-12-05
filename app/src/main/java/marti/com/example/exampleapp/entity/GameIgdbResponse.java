package marti.com.example.exampleapp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mferrando on 23/06/16.
 */
public class GameIgdbResponse {

        private ArrayList<GameIGDB> games;  // important must be the same than response name

            // single game
        private GameIgdbDetail game;  // important must be the same than response name


        public List<GameIGDB> getMyGames() {
            return games;
        }

        public GameIgdbDetail getMyGame() {
            return game;
        }

        public void setMyGames(ArrayList<GameIGDB> myGames) {
            this.games = myGames;
        }

        public void setMyGame(GameIgdbDetail myGame) {
        this.game = myGame;
    }

}