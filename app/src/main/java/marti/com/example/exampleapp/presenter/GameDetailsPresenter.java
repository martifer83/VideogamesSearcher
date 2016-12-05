package marti.com.example.exampleapp.presenter;

import marti.com.example.exampleapp.dataaccess.DataAccessGatewayIgdb;
import marti.com.example.exampleapp.dataaccess.Factory;
import marti.com.example.exampleapp.entity.GameIgdbDetail;

/**
 * Created by mferrando on 28/06/16.
 */
public class GameDetailsPresenter extends BasePresenter {

    public interface View extends Presenter.View {
        void showGameDetail(GameIgdbDetail gameIgdbDetail);
    }

    private View mView;
    private DataAccessGatewayIgdb mDataAccessGatewayIgdb;

    public GameDetailsPresenter(View view) {
        mView = view;
        mDataAccessGatewayIgdb = Factory.createIgdbData(view.getContext());
    }

    public void getEventDetails(final String eventId) {
      //  mView.showLoading();
    }

}