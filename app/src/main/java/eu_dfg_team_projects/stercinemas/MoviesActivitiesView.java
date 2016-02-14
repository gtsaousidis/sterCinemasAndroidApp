package eu_dfg_team_projects.stercinemas;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import eu_dfg_team_projects.stercinemas.API.MoviesAPI;
import eu_dfg_team_projects.stercinemas.Adapters.MoviesAdapter;

public class MoviesActivitiesView extends AppCompatActivity {

    public static final String MoviesData = "Movies";
    private RecyclerView mMoviesList;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private MoviesAdapter mMoviesAdapter;

    private ArrayList<MoviesAPI> mMoviesData;
    private String cinemaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_activities_view);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cardview_dark_background)));

        mMoviesData = getIntent().getParcelableArrayListExtra(MoviesData);
        cinemaName = getIntent().getStringExtra("CinemaName");

        setTitle(cinemaName);

        mMoviesList = (RecyclerView)findViewById(R.id.moviesList);

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,1);
        mMoviesList.setLayoutManager(mStaggeredGridLayoutManager);
        mMoviesList.setHasFixedSize(true);

        mMoviesAdapter = new MoviesAdapter(this, mMoviesData);
        mMoviesList.setAdapter(mMoviesAdapter);

        mMoviesAdapter.setOnItemClickListener(new MoviesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                ImageView poster = (ImageView)view.findViewById(R.id.moviePoster);

                Pair<View, String> imagePair = Pair.create((View) poster, "tImage");

                Intent intent = new Intent(MoviesActivitiesView.this, SinlgeMovieView.class);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MoviesActivitiesView.this, imagePair);
                ActivityCompat.startActivity(MoviesActivitiesView.this, intent, options.toBundle());
            }
        });
    }

}
