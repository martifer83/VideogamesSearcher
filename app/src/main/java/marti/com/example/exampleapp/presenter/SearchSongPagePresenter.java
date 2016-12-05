package marti.com.example.exampleapp.presenter;

import java.util.ArrayList;

import marti.com.example.exampleapp.business.logic.SongUseCases;
import marti.com.example.exampleapp.dataaccess.DataAccessGateway;
import marti.com.example.exampleapp.dataaccess.Factory;
import marti.com.example.exampleapp.entity.Song;
import marti.com.example.exampleapp.entity.SongResponse;

/**
 * Created by mferrando on 08/06/16.
 */
public class SearchSongPagePresenter extends BasePresenter {

    private SearchSongPagePresenter.View mView;
    private DataAccessGateway mDataAccessGateway;

    public interface View extends Presenter.View {
        void onEventsReceived(ArrayList<Song> events);
    }

    public SearchSongPagePresenter(SearchSongPagePresenter.View view) {
        mView = view;
        mDataAccessGateway = Factory.create(mView.getContext());
    }

    public void getEvents() {
      //  mView.showLoading();
        SongUseCases.getSongs(mDataAccessGateway, new BusinessCallbackImpl<SongResponse>(mView) {
            @Override
            public void successUIThread(SongResponse data) {
        //        mView.hideLoading();
                mView.onEventsReceived(builtEventsList(data));
            }

            @Override
            public void onRetryClick() {
                getEvents();
            }
        });
    }

    private ArrayList<Song> builtEventsList(SongResponse songsResponse) {
        ArrayList<Song> songs = new ArrayList<>();

        if (songsResponse.getMySongs() != null) {
            for (Song myEvent : songsResponse.getMySongs()) {
            //    myEvent.setType(EventType.MY);
                songs.add(myEvent);
            }
        }
        return songs;
    }


}
