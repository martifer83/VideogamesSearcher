package marti.com.example.exampleapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 15/06/16.
 */
public class GameImages implements Parcelable {

    private String tiny_url;
    private String medium_url;

    public String getTiny_url() {
        return tiny_url;
    }
    public String getMedium_url() {
        return medium_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tiny_url);
        dest.writeString(this.medium_url);
    }

    public GameImages() {
    }

    protected GameImages(Parcel in) {
        this.tiny_url = in.readString();
        this.medium_url = in.readString();
    }

    public static final Creator<GameImages> CREATOR = new Creator<GameImages>() {
        public GameImages createFromParcel(Parcel source) {
            return new GameImages(source);
        }

        public GameImages[] newArray(int size) {
            return new GameImages[size];
        }
    };
}
