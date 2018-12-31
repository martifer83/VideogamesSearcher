package marti.com.example.exampleapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import marti.com.example.exampleapp.entity.gameigdbdetail.Cover;

public class GameV3 implements Parcelable{

    private int id;
    private String name;
    private float popularity;
    private float rating;
    private String first_release_date;

    public static final Creator<GameV3> CREATOR = new Creator<GameV3>() {
        @Override
        public GameV3 createFromParcel(Parcel in) {
            return new GameV3(in);
        }

        @Override
        public GameV3[] newArray(int size) {
            return new GameV3[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFirst_release_date() {
        return first_release_date;
    }

    public void setFirst_release_date(String first_release_date) {
        this.first_release_date = first_release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(popularity);
        dest.writeFloat(rating);
        dest.writeString(first_release_date);
    }

    public GameV3() {
    }

    protected GameV3(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.popularity = in.readFloat();
        this.rating = in.readFloat();
        this.first_release_date =in.readString();
    }


}
