package marti.com.example.exampleapp.presenter;

import java.util.ArrayList;

import marti.com.example.exampleapp.business.logic.SongUseCases;
import marti.com.example.exampleapp.dataaccess.DataAccessGateway;
import marti.com.example.exampleapp.dataaccess.DataAccessGatewayIgdb;
import marti.com.example.exampleapp.dataaccess.Factory;
import marti.com.example.exampleapp.entity.Game;
import marti.com.example.exampleapp.entity.GameResponse;
import marti.com.example.exampleapp.entity.Song;
import marti.com.example.exampleapp.entity.SongResponse;

/**
 * Created by mferrando on 05/05/16.
 */
public class SearchPresenter extends BasePresenter {

    /*public interface View extends Presenter.View {
        void showSongDetail(Song song);
    }*/

    public interface View extends Presenter.View {
        void onSongsReceived(ArrayList<Song> events);
        void onGamesReceived(ArrayList<Game> games);
    }

    private View mView;
    private DataAccessGateway mDataAccessGateway;
    private DataAccessGatewayIgdb mDataAccessGatewayIgdb;

    public SearchPresenter(SearchPresenter.View view) {
        mView = view;
        mDataAccessGateway = Factory.create(view.getContext());
        mDataAccessGatewayIgdb = Factory.createIgdbData(view.getContext());


    }


    private ArrayList<Song> builtSongsList(SongResponse songResponse) {
        ArrayList<Song> songs = new ArrayList<>();

        if (songResponse.getMySongs() != null) {
            for (Song mySong : songResponse.getMySongs()) {
                //mySong.setType(EventType.MY);
                songs.add(mySong);
            }
        }


        return songs;
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


    public void getSongs() {
        mView.showLoading();
        SongUseCases.getSongs(mDataAccessGateway, new BusinessCallbackImpl<SongResponse>(mView) {
            @Override
            public void successUIThread(SongResponse data) {
                mView.hideLoading();
                //  mView.onSongsReceived(builtSongsList(data));
            }

            @Override
            public void onRetryClick() {
                getSongs();
            }
        });
    }







}
