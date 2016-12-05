package marti.com.example.exampleapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 22/06/16.
 */
public class GameIGDB implements Parcelable {


    //simple
    private int id;
    private String name;
    private String release_date;
    private String cover;

    // detalied

    private float rating;

    // image - icon_url
    // platforms - name


    // platforms -- abbreviation

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCover() {
        return cover;
    }

    public String getRelease_date() {return release_date; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.release_date);
        dest.writeString(this.cover);
        dest.writeFloat(this.rating);
    }

    public GameIGDB() {
    }

    protected GameIGDB(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        this.release_date = in.readString();
        this.cover = in.readString();
        this.rating = in.readFloat();
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}