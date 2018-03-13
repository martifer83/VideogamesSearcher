package marti.com.example.exampleapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import marti.com.example.exampleapp.entity.gameigdbdetail.BeatingTime;
import marti.com.example.exampleapp.entity.gameigdbdetail.Company;
import marti.com.example.exampleapp.entity.gameigdbdetail.Cover;
import marti.com.example.exampleapp.entity.gameigdbdetail.ReleaseDate;
import marti.com.example.exampleapp.entity.gameigdbdetail.Screenshot;

/**
 * Created by mferrando on 27/06/16.
 */
public class GameIgdbDetail implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("summary")
    private String summary;
    @SerializedName("storyline")
    private String storyline;
    @SerializedName("first_release_date")
    private String first_release_date;
    @SerializedName("popularity")
    private float popularity;



    @SerializedName("time_to_beat")
    private BeatingTime time_to_beat;

    public String getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(String total_rating) {
        this.total_rating = total_rating;
    }

    @SerializedName("total_rating")
    private String total_rating;



    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String getFirst_release_date() {
        return first_release_date;
    }

    public void setFirst_release_date(String first_release_date) {
        this.first_release_date = first_release_date;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public BeatingTime getTime_to_beat() {
        return time_to_beat;
    }

    public void setTime_to_beat(BeatingTime time_to_beat) {
        this.time_to_beat = time_to_beat;
    }


   // @SerializedName("release_date")
   // private String release_date;

    //private ArrayList<Genres> genres;

    @SerializedName("rating")
    private float rating;
   // @SerializedName("release_dates")
    private ArrayList<ReleaseDate> release_dates;
    //@SerializedName("companies")
    private ArrayList<Company> companies;

    private ArrayList<Screenshot> screenshots;
    @SerializedName("cover")
    private Cover cover;

    // image - icon_url
    // platforms - name

    // platforms -- abbreviation

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreated_at() {
        return created_at;
    }

   /* public String getRelease_date() {
        return release_date;
    }*/

    public float getRating() {
        return rating;
    }

    public String getSummary() {
        return summary;
    }

    public ArrayList<ReleaseDate> getRelease_dates() {
        return release_dates;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    /*public ArrayList<Genres> getGenres() {
        return genres;
    }*/

    public ArrayList<Screenshot> getScreenshots() {
        return screenshots;
    }

    public Cover getCover() {
        return cover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.created_at);
        dest.writeString(this.summary);
        //dest.writeTypedList(genres);


        dest.writeFloat(this.rating);
        dest.writeTypedList(release_dates);
        dest.writeTypedList(companies);
        dest.writeTypedList(screenshots);

        dest.writeTypedObject(this.time_to_beat,0);
        dest.writeFloat(this.popularity);
        dest.writeString(this.total_rating);
        dest.writeString(this.first_release_date);

   //     dest.writeParcelable(this.cover, 0);


    }

    public GameIgdbDetail() {
    }
 // http://stackoverflow.com/questions/3513665/problem-in-implementing-parcelable-containing-other-parcelable
    protected GameIgdbDetail(Parcel in) {

        this.id = in.readInt();
        this.name = in.readString();
        this.created_at = in.readString();
        this.summary = in.readString();
    //    this.genres = in.createTypedArrayList(Genres.CREATOR);

        this.rating = in.readFloat();
        this.release_dates = in.createTypedArrayList(ReleaseDate.CREATOR);
        this.companies = in.createTypedArrayList(Company.CREATOR);
        this.screenshots = in.createTypedArrayList(Screenshot.CREATOR);

        this.time_to_beat = in.readTypedObject(BeatingTime.CREATOR);
        this.popularity = in.readFloat();
        this.total_rating = in.readString();
        this.first_release_date = in.readString();

    //    this.cover =(Cover)in.readParcelable(Cover.class.getClassLoader());
    }

    public static final Parcelable.Creator<GameIgdbDetail> CREATOR = new Parcelable.Creator<GameIgdbDetail>() {
        public GameIgdbDetail createFromParcel(Parcel source) {
            return new GameIgdbDetail(source);
        }

        public GameIgdbDetail[] newArray(int size) {
            return new GameIgdbDetail[size];
        }
    };


}
