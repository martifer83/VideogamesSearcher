package marti.com.example.exampleapp.presenter;

import java.util.ArrayList;

import marti.com.example.exampleapp.Application;
import marti.com.example.exampleapp.business.logic.GameIgdbUseCases;
import marti.com.example.exampleapp.business.logic.GameUseCases;
import marti.com.example.exampleapp.dataaccess.DataAccessGateway;
import marti.com.example.exampleapp.dataaccess.DataAccessGatewayIgdb;
import marti.com.example.exampleapp.dataaccess.Factory;
import marti.com.example.exampleapp.entity.Game;
import marti.com.example.exampleapp.entity.GameIGDB;
import marti.com.example.exampleapp.entity.GameIgdbDetail;
import marti.com.example.exampleapp.entity.GameIgdbResponse;
import marti.com.example.exampleapp.entity.GameResponse;

/**
 * Created by mferrando on 15/06/16.
 */
public class SearchGamePagePresenter extends BasePresenter{

    private SearchGamePagePresenter.View mView;
    private DataAccessGateway mDataAccessGateway;
    private DataAccessGatewayIgdb mDataAccessGatewayIgdb;

    public interface View extends Presenter.View {
        void onGamesReceived(ArrayList<Game> games);
        void onGamesListIgdbReceived(ArrayList<GameIGDB> games);
        void onGameIgdbReceived(GameIgdbDetail game);

    }

    public SearchGamePagePresenter(SearchGamePagePresenter.View view) {
        mView = view;
        if(mView.getContext() == null) {
            mDataAccessGateway = Factory.create(Application.getInstance().getApplicationContext());
            mDataAccessGatewayIgdb = Factory.createIgdbData(Application.getInstance().getApplicationContext());
        }
        else {
            mDataAccessGateway = Factory.create(mView.getContext());
            mDataAccessGatewayIgdb = Factory.createIgdbData(mView.getContext());
        }
    }

    public void getGamesbyName(final String queryText) {
        mView.showLoading();
        GameIgdbUseCases.getGamesByName(mDataAccessGatewayIgdb, new BusinessCallbackImpl<GameIgdbResponse>(mView) {
            @Override
            public void successUIThread(GameIgdbResponse data) {
                mView.hideLoading();
                mView.onGamesListIgdbReceived(builtGamesIGDBList(data));
            }

            @Override
            public void onRetryClick() {
                getGamesbyName(queryText);
            }
        }, queryText);
    }

    public void getGamesbyId(final String queryText) {
       // mView.showLoading();
        GameIgdbUseCases.getGamesById(mDataAccessGatewayIgdb, new BusinessCallbackImpl<GameIgdbResponse>(mView) {
            @Override
            public void successUIThread(GameIgdbResponse data) {
                mView.hideLoading();
                mView.onGameIgdbReceived(builtGameIGDBDetail(data));

            }

            @Override
            public void onRetryClick() {
                getGamesbyId(queryText);
            }
        }, queryText);
    }

    public void getGames(final String queryText) {
        mView.showLoading();
        GameUseCases.getGames(mDataAccessGateway, new BusinessCallbackImpl<GameResponse>(mView) {
            @Override
            public void successUIThread(GameResponse data) {
                mView.hideLoading();
                mView.onGamesReceived(builtGamesList(data));
            }

            @Override
            public void onRetryClick() {
                getGames(queryText);
            }
        }, queryText);
    }

    private ArrayList<Game> builtGamesList(GameResponse gameResponse) {
        ArrayList<Game> games = new ArrayList<>();

        if (gameResponse.getMyGames() != null) {
            for (Game myGame : gameResponse.getMyGames()) {
                games.add(myGame);
            }
        }
        return games;
    }

    private ArrayList<GameIGDB> builtGamesIGDBList(GameIgdbResponse gameIgdbResponse) {
        ArrayList<GameIGDB> games = new ArrayList<>();

        if (gameIgdbResponse.getMyGames() != null) {
            for (GameIGDB myGame : gameIgdbResponse.getMyGames()) {
                games.add(myGame);
            }
        }
        return games;
    }

    private GameIgdbDetail builtGameIGDBDetail(GameIgdbResponse gameIgdbResponse) {
        return gameIgdbResponse.getMyGame();
    }

    private void launchActivityDetails(GameIgdbResponse gameIgdbResponse) {

        GameIgdbDetail game = gameIgdbResponse.getMyGame();

    }

}
