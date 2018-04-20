package marti.com.example.exampleapp.presenter;

import java.util.ArrayList;

import javax.inject.Inject;

import marti.com.example.exampleapp.Application;
import marti.com.example.exampleapp.business.logic.GameIgdbUseCases;
import marti.com.example.exampleapp.business.logic.GameUseCases;
import marti.com.example.exampleapp.business.logic.GetGameByNameUseCase;
import marti.com.example.exampleapp.common.AbstractSubscriber;
import marti.com.example.exampleapp.common.error.ErrorManagerInterface;
import marti.com.example.exampleapp.common.error.ErrorMessage;
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

    private GetGameByNameUseCase getGameByNameUseCase = null;


    public interface View extends Presenter.View {
        void onGamesReceived(ArrayList<Game> games);
        void onGamesListIgdbReceived(ArrayList<GameIGDB> games);
        void onGameIgdbReceived(GameIgdbDetail game);

    }

    public SearchGamePagePresenter(SearchGamePagePresenter.View view){

        mView = view;
        // Todo delete DataAccesGateway
        if(mView.getContext() == null) {
            mDataAccessGateway = Factory.create(Application.getInstance().getApplicationContext());
            mDataAccessGatewayIgdb = Factory.createIgdbData(Application.getInstance().getApplicationContext());
        }
        else {
            mDataAccessGateway = Factory.create(mView.getContext());
            mDataAccessGatewayIgdb = Factory.createIgdbData(mView.getContext());
        }
        
    }
    
    @Inject
    public SearchGamePagePresenter(/*SearchGamePagePresenter.View view,*/ GetGameByNameUseCase useCase){
        //mView = view;
        if(useCase != null)
            getGameByNameUseCase = useCase;
    }

    /*public SearchGamePagePresenter(SearchGamePagePresenter.View view) {
        mView = view;

        if(mView.getContext() == null) {
            mDataAccessGateway = Factory.create(Application.getInstance().getApplicationContext());
            mDataAccessGatewayIgdb = Factory.createIgdbData(Application.getInstance().getApplicationContext());
        }
        else {
            mDataAccessGateway = Factory.create(mView.getContext());
            mDataAccessGatewayIgdb = Factory.createIgdbData(mView.getContext());
        }
    }*/

    public void setmView(SearchGamePagePresenter.View view){
        mView = view;
    }

    public void getGamesbyName(final String queryText) {
        mView.showLoading();
        // old version useCase
      /*  GameIgdbUseCases.getGamesByName(mDataAccessGatewayIgdb, new BusinessCallbackImpl<ArrayList<GameIGDB>>(mView) {
            @Override
            public void successUIThread(ArrayList<GameIGDB> data) {
                mView.hideLoading();
                mView.onGamesListIgdbReceived(data);
            }

            @Override
            public void onRetryClick() {
                getGamesbyName(queryText);
            }
        }, queryText);
        */
        // new version UseCase
        getGameByNameUseCase.setParameters(queryText);
        getGameByNameUseCase.subscribe();

    }

    public void getGamesbyId(final String queryText) {
       // mView.showLoading();
        GameIgdbUseCases.getGamesById(mDataAccessGatewayIgdb, new BusinessCallbackImpl<ArrayList<GameIgdbDetail>>(mView) {
            @Override
            public void successUIThread(ArrayList<GameIgdbDetail> data) {
                mView.hideLoading();
                mView.onGameIgdbReceived(data.get(0)); /// TODO check
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

    private class GamesSubscriber extends AbstractSubscriber<String> {
        public GamesSubscriber(ErrorManagerInterface errorManager) {
            super(errorManager);
        }

        @Override
        public void onNext(ArrayList<GameIGDB> data) {
            // do stuff
            mView.hideLoading();
            mView.onGamesListIgdbReceived(data);
        }

        @Override
        protected void onError(ErrorMessage errorMessage) {
            // do nothing
        }
    }

}
