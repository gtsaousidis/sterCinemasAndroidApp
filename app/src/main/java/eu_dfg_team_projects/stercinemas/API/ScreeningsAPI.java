package eu_dfg_team_projects.stercinemas.API;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by georgetsd on 13/2/16.
 */
public class ScreeningsAPI implements Parcelable {

    private String date;
    private String[] times;

    public String[] getTimes() {
        return times;
    }

    public void setTimes(String[] times) {
        this.times = times;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeStringArray(this.times);
    }

    public ScreeningsAPI() {
    }

    protected ScreeningsAPI(Parcel in) {
        this.date = in.readString();
        this.times = in.createStringArray();
    }

    public static final Parcelable.Creator<ScreeningsAPI> CREATOR = new Parcelable.Creator<ScreeningsAPI>() {
        public ScreeningsAPI createFromParcel(Parcel source) {
            return new ScreeningsAPI(source);
        }

        public ScreeningsAPI[] newArray(int size) {
            return new ScreeningsAPI[size];
        }
    };
}
