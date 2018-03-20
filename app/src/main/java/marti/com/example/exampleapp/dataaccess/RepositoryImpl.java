package marti.com.example.exampleapp.dataaccess;

import java.util.ArrayList;

import marti.com.example.exampleapp.entity.GameIGDB;
import rx.Observable;

/**
 * Created by mferrando on 19/03/2018.
 */

public class RepositoryImpl implements Repository {

    private final CloudRepository cloudRepository;

    public RepositoryImpl(CloudRepository cloudRepository) {
        this.cloudRepository = cloudRepository;
    }

    @Override
    public Observable<ArrayList<GameIGDB>> getGameByName(String name) {
        return cloudRepository.getGameByName(name);
    }
}
