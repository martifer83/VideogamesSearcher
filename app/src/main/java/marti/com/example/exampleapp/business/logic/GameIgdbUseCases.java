package marti.com.example.exampleapp.business.logic;

import java.util.ArrayList;

import marti.com.example.exampleapp.business.executor.TaskManager;
import marti.com.example.exampleapp.business.executor.UseCase;
import marti.com.example.exampleapp.dataaccess.DataAccessGatewayIgdb;
import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;

/**
 * Created by mferrando on 23/06/16.
 */
public class GameIgdbUseCases {

    public static void getGamesById(final DataAccessGatewayIgdb dataAccessGatewayIgdb,
                                    final marti.com.example.exampleapp.business.logic.BusinessCallback<ArrayList<GameIgdbDetail>> callback, final String text) {
        TaskManager.getInstance().execute(new UseCase() {
            @Override
            protected Void doInBackground() throws Exception {

                dataAccessGatewayIgdb.getGamesById(new DataCallbackImpl<>(callback), text);
                return null;
            }
        });
    }

    public static void getGamesByName(final DataAccessGatewayIgdb dataAccessGatewayIgdb,
                                      final marti.com.example.exampleapp.business.logic.BusinessCallback<ArrayList<GameIGDB>> callback, final String text) {
        TaskManager.getInstance().execute(new UseCase() {
            @Override
            protected Void doInBackground() throws Exception {
                dataAccessGatewayIgdb.getGamesByName(new DataCallbackImpl<>(callback), text);
                return null;
            }
        });

    }




}




