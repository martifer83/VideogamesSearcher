package marti.com.example.exampleapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mferrando on 24/05/16.
 */
public class SongResponse implements Serializable {

    public Integer resultCount;

    private ArrayList<Song> results;

    public Integer getResults(){
        return resultCount;
    }

    public List<Song> getMySongs() {
        return results;
    }

    public void setResults(int results){
        this.resultCount = results;
    }

    public void setMySongs(ArrayList<Song> mySongs) {
        this.results = mySongs;
    }


}
