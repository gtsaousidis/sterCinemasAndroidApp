package eu_dfg_team_projects.stercinemas;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import eu_dfg_team_projects.stercinemas.API.MoviesAPI;
import eu_dfg_team_projects.stercinemas.API.ScreeningsAPI;
import eu_dfg_team_projects.stercinemas.API.SterCinemasAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by georgetsd on 13/2/16.
 */
public class SterManager extends Activity {

    private String urlCinemas = "http://ster-cinemas-d-api.herokuapp.com/schedule";



    public void getSterCinemas(final Number cinemasNumber, final OnComplete callWhenFinished){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(String.valueOf(urlCinemas)).build();
        final okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callWhenFinished.complete(false, null, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    final String cinemasData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try{
                                ArrayList<SterCinemasAPI> sterCinemas = new ArrayList<SterCinemasAPI>();

                                sterCinemas = getDataFromCinemas(cinemasData);
                                callWhenFinished.complete(true, sterCinemas, null);
                            }
                            catch (JSONException e) {
                                Log.e("Tell me something", "Exception caught: ", e);
                                callWhenFinished.complete(false, null, e.toString());
                            }
                        }
                    });
                }
            }
        });

    }

    public ArrayList<SterCinemasAPI> getDataFromCinemas(String cinemasData) throws JSONException{
        JSONArray cinemas = new JSONArray(cinemasData);
        ArrayList<SterCinemasAPI> cinemasAPIs = new ArrayList<SterCinemasAPI>(cinemasData.length());

        for(int i = 0; i < cinemas.length(); i++){
            SterCinemasAPI cinema = new SterCinemasAPI();

            JSONObject cinemasInfo = cinemas.getJSONObject(i);
            cinema.setCinemasName(cinemasInfo.getString("name"));
            cinema.setId(cinemasInfo.getDouble("id"));
            cinema.setPhoto(cinemasInfo.getString("photo"));

            JSONArray moviesPerCinema = cinemasInfo.getJSONArray("movies");

            ArrayList<MoviesAPI> moviesData = new ArrayList<MoviesAPI>(moviesPerCinema.length());

            for (int g = 0; g <moviesPerCinema.length(); g++){
                MoviesAPI movie = new MoviesAPI();

                JSONObject movieInfo = moviesPerCinema.getJSONObject(g);

                //SET THE ID ON THE MOVIE//
                movie.setId(movieInfo.getString("id"));

                //SET THE NAME OF THE MOVIE//
                movie.setMoviesName(movieInfo.getString("name"));

                //SET THE POSTER OF THE MOVIE//
                movie.setMoviesPoster(movieInfo.getString("poster"));

                //Taking the images//
                JSONArray movieImages = movieInfo.getJSONArray("images");

                String[] images =  null;

                //SET THE IMAGE ON THE MOVIE//
                movie.setMovieImages(fillArrayWithString(images, movieImages));

                //Take the screenings//
                JSONArray movieScreenings = movieInfo.getJSONArray("screenings");
                ArrayList<ScreeningsAPI> screeningsAPIs = new ArrayList<ScreeningsAPI>(movieScreenings.length());

                for (int r = 0; r < movieScreenings.length(); r++){
                    ScreeningsAPI movieScreens = new ScreeningsAPI();
                    JSONObject screen = movieScreenings.getJSONObject(r);

                    movieScreens.setDate(screen.getString("date"));

                    JSONArray jsonTimes = screen.getJSONArray("times");

                    String[] times = null;
                    movieScreens.setTimes(fillArrayWithString(times, jsonTimes));

                    screeningsAPIs.add(movieScreens);
                }

                //SET THE SCREENING ON THE MOVIE//
                movie.setScreenings(screeningsAPIs);
                moviesData.add(movie);
            }

            //Set the movies data to cinema//
            cinema.setMovies(moviesData);

            //Add cinema to array cinemas//
            cinemasAPIs.add(cinema);
        }

        return cinemasAPIs;
    }

    public String[] fillArrayWithString(String[] data, JSONArray dataLength) throws JSONException {

        data = new String[dataLength.length()];

        for(int k = 0; k <dataLength.length(); k++){

            String string = dataLength.getString(k);

            data[k] = string;
        }

        return data;
    }

    public interface OnComplete{
        void complete(boolean success, ArrayList<SterCinemasAPI> sterCinemas, String error);
    }
}
