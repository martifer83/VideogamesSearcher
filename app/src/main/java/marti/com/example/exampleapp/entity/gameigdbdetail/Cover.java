package marti.com.example.exampleapp.entity.gameigdbdetail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 08/07/16.
 */
public class Cover implements Parcelable {
    private String url;
    private int height;
    private int width;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public Cover() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeInt(height);
        dest.writeInt(width);
    }


    public static final Creator<Cover> CREATOR = new Creator<Cover>() {
        public Cover createFromParcel(Parcel source) {
            return new Cover(source);
        }

        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };

        /*// Creator
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public Cover createFromParcel(Parcel in) {
                return new Cover(in);
            }

            public Cover[] newArray(int size) {
                return new Cover[size];
            }
        };*/

    // "De-parcel object
    protected Cover(Parcel in) {
        url = in.readString();
        height = in.readInt();
        width = in.readInt();
    }
}