package marti.com.example.exampleapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 24/05/16.
 */
public class Song implements Parcelable {


    private String artistName;
    private String collectionName;
    private String trackName;
    private String artworkUrl30;

    public String getArtistName() {
        return artistName;
    }
    public String getCollectionName() {
        return collectionName;
    }
    public String getTrackName() {
        return trackName;
    }
    public String getArtworkUrl30() {return artworkUrl30; }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.artistName);
        dest.writeString(this.collectionName);
        dest.writeString(this.trackName);
        dest.writeString(this.artworkUrl30);
    }


    public Song() {
    }

    protected Song(Parcel in) {
        this.artistName = in.readString();
        this.collectionName = in.readString();
        this.trackName = in.readString();
        this.artworkUrl30 = in.readString();
    }


    public static final Creator<Song> CREATOR = new Creator<Song>() {
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}



