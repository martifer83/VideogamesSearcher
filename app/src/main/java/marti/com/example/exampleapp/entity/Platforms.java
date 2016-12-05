package marti.com.example.exampleapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 21/06/16.
 */
public class Platforms implements Parcelable {

    private String name;

    public String getName() {
        return name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public Platforms() {
    }

    protected Platforms(Parcel in) {
        this.name = in.readString();

    }


    public static final Creator<Platforms> CREATOR = new Creator<Platforms>() {
        public Platforms createFromParcel(Parcel source) {
            return new Platforms(source);
        }

        public Platforms[] newArray(int size) {
            return new Platforms[size];
        }
    };

}
