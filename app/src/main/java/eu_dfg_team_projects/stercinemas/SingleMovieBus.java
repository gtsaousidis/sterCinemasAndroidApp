package eu_dfg_team_projects.stercinemas;

import java.util.ArrayList;

import eu_dfg_team_projects.stercinemas.API.ScreeningsAPI;

/**
 * Created by georgetsd on 14/2/16.
 */
public class SingleMovieBus {

    public final String movieName;
    public final String mPoster;
    public final String[] mImages;
    public final ArrayList<ScreeningsAPI> mScreeningsAPIs;

    public SingleMovieBus(

            String name,
            String poster,
            String[] images,
            ArrayList<ScreeningsAPI> screens){

        movieName = name;
        mPoster = poster;
        mImages = images;
        mScreeningsAPIs = screens;
    }

    public String getMovieName(){
        return movieName;
    }

    public String getPoster(){
        return mPoster;
    }

    public String[] getImages(){
        return mImages;
    }

    public ArrayList<ScreeningsAPI> getScreeningsAPIs(){
        return mScreeningsAPIs;
    }



}
