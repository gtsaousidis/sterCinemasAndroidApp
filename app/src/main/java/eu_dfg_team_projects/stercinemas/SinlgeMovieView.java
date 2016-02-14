package eu_dfg_team_projects.stercinemas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.greenrobot.event.EventBus;
import eu_dfg_team_projects.stercinemas.Adapters.ScreeningAdapter;

public class SinlgeMovieView extends Activity {

    private SingleMovieBus mMovieBus;

    private ImageView mSingleMoviePoster;
    private TextView mTitleMovie;

    private ScreeningAdapter mScreenAdapter;
    private RecyclerView mListScreens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinlge_movie_view);

        mSingleMoviePoster = (ImageView)findViewById(R.id.singleMoviePoster);
        mTitleMovie = (TextView)findViewById(R.id.singleMovieTitle);

        EventBus.getDefault().registerSticky(this);

    }

    public void onEventMainThread(SingleMovieBus event){
        mMovieBus = event;

        displayTheMovieData();

        super.onStop();
    }

    private void displayTheMovieData() {

        mTitleMovie.setText(mMovieBus.getMovieName());
        Picasso.with(getBaseContext()).load(mMovieBus.getPoster()).into(mSingleMoviePoster);

        mListScreens = (RecyclerView)findViewById(R.id.screensList);

        LinearLayoutManager mManager = new LinearLayoutManager(getBaseContext());
        mListScreens.setLayoutManager(mManager);
        mListScreens.setHasFixedSize(true);

        mScreenAdapter = new ScreeningAdapter(getBaseContext(),mMovieBus.getScreeningsAPIs());

        mListScreens.setAdapter(mScreenAdapter);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
