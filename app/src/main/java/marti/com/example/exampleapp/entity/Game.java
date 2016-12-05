package marti.com.example.exampleapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mferrando on 14/06/16.
 */
public class Game implements Parcelable {


    private String name;
    private String aliases;
    private String original_release_date;
    private GameImages image;
    private List<Platforms> platforms;


    // image - icon_url
    // platforms - name
    // platforms -- abbreviation



    public String getName() {
        return name;
    }
    public String getAliases() {
        return aliases;
    }
    public String getOriginal_release_date() {return original_release_date; }
    public GameImages getImage() {
        return image;
    }

    public List<Platforms> getPlatforms() {
        return platforms;
    }
    public void setPlatforms(List<Platforms> platforms) {
        this.platforms = platforms;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.aliases);
        dest.writeString(this.original_release_date);
        dest.writeParcelable(this.image, 0);
        dest.writeList(this.platforms);
    }

    public Game() {
    }

    protected Game(Parcel in) {
        this.name = in.readString();
        this.aliases = in.readString();
        this.original_release_date = in.readString();
        this.image = in.readParcelable(GameImages.class.getClassLoader());
        this.platforms = new ArrayList<Platforms>();
        in.readTypedList(this.platforms, Platforms.CREATOR);
    }


    public static final Creator<Game> CREATOR = new Creator<Game>() {
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}