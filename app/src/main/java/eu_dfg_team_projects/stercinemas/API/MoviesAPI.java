package eu_dfg_team_projects.stercinemas.API;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by georgetsd on 13/2/16.
 */
public class MoviesAPI extends ArrayList<MoviesAPI> implements Parcelable {

    private String id;
    private String moviesName;
    private String moviesPoster;
    private String[] movieImages;
    private ArrayList<ScreeningsAPI> mScreenings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoviesName() {
        return moviesName;
    }

    public void setMoviesName(String moviesName) {
        this.moviesName = moviesName;
    }

    public String getMoviesPoster() {
        return moviesPoster;
    }

    public void setMoviesPoster(String moviesPoster) {
        this.moviesPoster = moviesPoster;
    }

    public String[] getMovieImages() {
        return movieImages;
    }

    public void setMovieImages(String[] movieImages) {
        this.movieImages = movieImages;
    }

    public ArrayList<ScreeningsAPI> getScreenings() {
        return mScreenings;
    }

    public void setScreenings(ArrayList<ScreeningsAPI> screenings) {
        mScreenings = screenings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.moviesName);
        dest.writeString(this.moviesPoster);
        dest.writeStringArray(this.movieImages);
        dest.writeTypedList(mScreenings);
    }

    public MoviesAPI() {
    }

    protected MoviesAPI(Parcel in) {
        this.id = in.readString();
        this.moviesName = in.readString();
        this.moviesPoster = in.readString();
        this.movieImages = in.createStringArray();
        this.mScreenings = in.createTypedArrayList(ScreeningsAPI.CREATOR);
    }

    public static final Parcelable.Creator<MoviesAPI> CREATOR = new Parcelable.Creator<MoviesAPI>() {
        public MoviesAPI createFromParcel(Parcel source) {
            return new MoviesAPI(source);
        }

        public MoviesAPI[] newArray(int size) {
            return new MoviesAPI[size];
        }
    };
}
