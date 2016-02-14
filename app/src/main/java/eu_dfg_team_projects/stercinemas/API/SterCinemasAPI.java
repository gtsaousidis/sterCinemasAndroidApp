package eu_dfg_team_projects.stercinemas.API;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by georgetsd on 13/2/16.
 */
public class SterCinemasAPI implements Parcelable {

    private Number id;
    private String cinemasName;
    private ArrayList<MoviesAPI> mMovies = new ArrayList<MoviesAPI>();
    private String mPhoto;


    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getCinemasName() {
        return cinemasName;
    }

    public void setCinemasName(String cinemasName) {
        this.cinemasName = cinemasName;
    }

    public ArrayList<MoviesAPI> getMovies() {
        return mMovies;
    }

    public void setMovies(ArrayList<MoviesAPI> movies) {
        mMovies = movies;
    }
    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.id);
        dest.writeString(this.cinemasName);
        dest.writeTypedList(mMovies);
        dest.writeString(mPhoto);
    }

    public SterCinemasAPI() {

    }

    protected SterCinemasAPI(Parcel in) {
        this.id = (Number) in.readSerializable();
        this.cinemasName = in.readString();
        this.mMovies = in.createTypedArrayList(MoviesAPI.CREATOR);
        this.mPhoto = in.readString();

    }

    public static final Parcelable.Creator<SterCinemasAPI> CREATOR = new Parcelable.Creator<SterCinemasAPI>() {
        public SterCinemasAPI createFromParcel(Parcel source) {
            return new SterCinemasAPI(source);
        }

        public SterCinemasAPI[] newArray(int size) {
            return new SterCinemasAPI[size];
        }
    };
}
