package marti.com.example.exampleapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import marti.com.example.exampleapp.entity.gameigdbdetail.Company;
import marti.com.example.exampleapp.entity.gameigdbdetail.Cover;
import marti.com.example.exampleapp.entity.gameigdbdetail.Genres;
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
    @SerializedName("slug")
    private String slug;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("summary")
    private String summary;

   // @SerializedName("release_date")
   // private String release_date;

    private ArrayList<Genres> genres;

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

    public String getSlug() {
        return slug;
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

    public ArrayList<Genres> getGenres() {
        return genres;
    }

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
       // dest.writeString(this.slug);
        //dest.writeString(this.created_at);
        dest.writeString(this.summary);
        dest.writeTypedList(genres);
     //   dest.writeValue(this.rating);
        dest.writeFloat(this.rating);

        dest.writeTypedList(release_dates);
        dest.writeTypedList(companies);
        dest.writeTypedList(screenshots);

   //     dest.writeParcelable(this.cover, 0);


    }

    public GameIgdbDetail() {
    }
 // http://stackoverflow.com/questions/3513665/problem-in-implementing-parcelable-containing-other-parcelable
    protected GameIgdbDetail(Parcel in) {

        this.id = in.readInt();
        this.name = in.readString();
     //   this.slug = in.readString();
        //this.created_at = in.readString();
        this.summary = in.readString();
        this.genres = in.createTypedArrayList(Genres.CREATOR);
        this.rating = in.readFloat();

        this.release_dates = in.createTypedArrayList(ReleaseDate.CREATOR);

        this.companies = in.createTypedArrayList(Company.CREATOR);
        this.screenshots = in.createTypedArrayList(Screenshot.CREATOR);

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
