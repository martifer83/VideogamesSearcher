package marti.com.example.exampleapp.entity.gameigdbdetail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 15/07/16.
 */
public class Screenshot implements Parcelable {


    private String url;
    private String title;
    private int width;
    private int height;
    private String id;


    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getId() {
        return id;
    }

    public Screenshot() {
    }

    // "De-parcel object
    protected Screenshot(Parcel in) {
        url = in.readString();
        title = in.readString();
        width = in.readInt();
        height = in.readInt();
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(title);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(id);
    }

    public static final Creator<Screenshot> CREATOR = new Creator<Screenshot>() {
        public Screenshot createFromParcel(Parcel source) {
            return new Screenshot(source);
        }

        public Screenshot[] newArray(int size) {
            return new Screenshot[size];
        }
    };

}