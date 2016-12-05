package marti.com.example.exampleapp.entity.gameigdbdetail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 12/07/16.
 */
public class Genres implements Parcelable {


    private String name;


    public String getName() {
        return name;
    }



    public Genres() {
    }

    // "De-parcel object
    protected Genres(Parcel in) {
       name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    // Creator
    /*public static final Parcelable.Creator
            CREATOR
            = new Parcelable.Creator
            () {
        public ReleaseDate createFromParcel(Parcel in) {
            return new ReleaseDate(in);
        }

        public ReleaseDate[] newArray(int size) {
            return new ReleaseDate[size];
        }
    };*/

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        public Genres createFromParcel(Parcel source) {
            return new Genres(source);
        }

        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };

}