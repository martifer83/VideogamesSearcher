package marti.com.example.exampleapp.entity.gameigdbdetail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 12/07/16.
 */
public class ReleaseDate implements Parcelable {


    private String platform_name;
    private String release_date;

    public String getPlatform_name() {
        return platform_name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public ReleaseDate() {}

    // "De-parcel object
    protected ReleaseDate(Parcel in) {
        platform_name = in.readString();
        release_date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(platform_name);
        dest.writeString(release_date);
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

    public static final Creator<ReleaseDate> CREATOR = new Creator<ReleaseDate>() {
        public ReleaseDate createFromParcel(Parcel source) {
            return new ReleaseDate(source);
        }

        public ReleaseDate[] newArray(int size) {
            return new ReleaseDate[size];
        }
    };

}