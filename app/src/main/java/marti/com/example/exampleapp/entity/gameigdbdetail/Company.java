package marti.com.example.exampleapp.entity.gameigdbdetail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 12/07/16.
 */
// Inner class
public class Company implements Parcelable {

    private int id;
    private boolean developer;
    private boolean publisher;
    private String name;

    public int getId() {
        return id;
    }

    public boolean isDeveloper() {
        return developer;
    }

    public boolean isPublisher() {
        return publisher;
    }

    public String getName() {
        return name;
    }

    public Company() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (developer ? 1 : 0));     //if myBoolean == true, byte == 1
        dest.writeByte((byte) (publisher ? 1 : 0));     //if myBoolean == true, byte == 1
        dest.writeString(name);
    }

    // Creator
    /*public static final Parcelable.Creator
            CREATOR
            = new Parcelable.Creator
            () {
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        public Company[] newArray(int size) {
            return new Company[size];
        }
    };*/

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        public Company createFromParcel(Parcel source) {
            return new Company(source);
        }

        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    // "De-parcel object
    private Company(Parcel in) {
        id = in.readInt();
        developer = in.readByte() != 0;     //myBoolean == true if byte != 0
        publisher = in.readByte() != 0;     //myBoolean == true if byte != 0
        name = in.readString();
    }
}