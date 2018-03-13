package marti.com.example.exampleapp.entity.gameigdbdetail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mferrando on 13/03/2018.
 */

public class BeatingTime implements Parcelable {

    private int hastly;
    private int normally;
    private int completely;

    public int getHastly() {
        return hastly;
    }

    public void setHastly(int hastly) {
        this.hastly = hastly;
    }

    public int getNormally() {
        return normally;
    }

    public void setNormally(int normally) {
        this.normally = normally;
    }

    public int getCompletely() {
        return completely;
    }

    public void setCompletely(int completely) {
        this.completely = completely;
    }



    protected BeatingTime(Parcel in) {
        hastly = in.readInt();
        normally = in.readInt();
        completely = in.readInt();
    }

    public static final Creator<BeatingTime> CREATOR = new Creator<BeatingTime>() {
        @Override
        public BeatingTime createFromParcel(Parcel in) {
            return new BeatingTime(in);
        }

        @Override
        public BeatingTime[] newArray(int size) {
            return new BeatingTime[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(hastly);
        parcel.writeInt(normally);
        parcel.writeInt(completely);
    }
}