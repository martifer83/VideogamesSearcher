package marti.com.example.exampleapp.business.logic;

import marti.com.example.exampleapp.business.executor.TaskManager;
import marti.com.example.exampleapp.business.executor.UseCase;
import marti.com.example.exampleapp.dataaccess.DataAccessGateway;
import marti.com.example.exampleapp.entity.GameResponse;

/**
 * Created by mferrando on 15/06/16.
 */
public class GameUseCases {

    public static void getGames(final DataAccessGateway dataAccessGateway,
                                final marti.com.example.exampleapp.business.logic.BusinessCallback<GameResponse> callback, final String text) {
        TaskManager.getInstance().execute(new UseCase() {
            @Override
            protected Void doInBackground() throws Exception {
                dataAccessGateway.getGames(new DataCallbackImpl<>(callback), text);
                return null;
            }
        });
    }
}
