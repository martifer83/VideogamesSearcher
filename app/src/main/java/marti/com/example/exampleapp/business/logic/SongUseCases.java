package marti.com.example.exampleapp.business.logic;

import marti.com.example.exampleapp.business.executor.TaskManager;
import marti.com.example.exampleapp.business.executor.UseCase;
import marti.com.example.exampleapp.dataaccess.DataAccessGateway;
import marti.com.example.exampleapp.entity.SongResponse;

/**
 * Created by mferrando on 03/06/16.
 */
public class SongUseCases {

    public static void getSongs(final DataAccessGateway dataAccessGateway,
                                 final marti.com.example.exampleapp.business.logic.BusinessCallback<SongResponse> callback) {
        TaskManager.getInstance().execute(new UseCase() {
            @Override
            protected Void doInBackground() throws Exception {
                dataAccessGateway.getSongs(new DataCallbackImpl<>(callback));
                return null;
            }
        });
    }

}
