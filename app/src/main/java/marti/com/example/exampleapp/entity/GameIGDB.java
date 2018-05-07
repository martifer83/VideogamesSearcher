package marti.com.example.exampleapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import marti.com.example.exampleapp.entity.gameigdbdetail.Cover;

/**
 * Created by mferrando on 22/06/16.
 */
public class GameIGDB implements Parcelable {

    private int id;
    private String name;
    private Cover cover;
    private float popularity;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPopularity() {
        return popularity;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeFloat(this.popularity);
        dest.writeTypedObject(this.cover,0);
    }

    public GameIGDB() {
    }

    protected GameIGDB(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.popularity = in.readFloat();
        this.cover =in.readParcelable(Cover.class.getClassLoader());
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