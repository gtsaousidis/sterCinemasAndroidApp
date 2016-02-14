package eu_dfg_team_projects.stercinemas;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import eu_dfg_team_projects.stercinemas.API.SterCinemasAPI;
import eu_dfg_team_projects.stercinemas.Adapters.CinemasAdapter;

public class MainActivity extends AppCompatActivity {

    private CinemasAdapter mCinemasAdapter;
    private RecyclerView mCinemasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SterManager sterManager = new SterManager();

        setTitle("Ster Cinemas");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cardview_dark_background)));

        mCinemasList = (RecyclerView)findViewById(R.id.cinemasList);

        LinearLayoutManager mManager = new LinearLayoutManager(getBaseContext());
        mCinemasList.setLayoutManager(mManager);
        mCinemasList.setHasFixedSize(true);


        sterManager.getSterCinemas(4, new SterManager.OnComplete() {
            @Override
            public void complete(boolean success, ArrayList<SterCinemasAPI> sterCinemas, String error) {

                if (success){

                    mCinemasAdapter = new CinemasAdapter(getBaseContext(),sterCinemas);

                    mCinemasList.setAdapter(mCinemasAdapter);
                }

            }
        });
    }
}
