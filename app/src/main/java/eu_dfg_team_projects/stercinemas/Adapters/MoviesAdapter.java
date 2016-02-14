package eu_dfg_team_projects.stercinemas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import eu_dfg_team_projects.stercinemas.API.MoviesAPI;
import eu_dfg_team_projects.stercinemas.R;
import eu_dfg_team_projects.stercinemas.SingleMovieBus;

/**
 * Created by georgetsd on 13/2/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewholder> {

    private ArrayList<MoviesAPI> mMovies;
    private Context mContext;
    OnItemClickListener mItemClickListener;

    public MoviesAdapter(Context context, ArrayList<MoviesAPI> movies){
        mMovies = movies;
        mContext = context;
    }


    @Override
    public MoviesAdapter.MoviesViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.movies_layout, parent, false);
        MoviesViewholder viewHolder = new MoviesViewholder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesViewholder holder, int position) {
        holder.bindMovies(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    public class MoviesViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView movieImage;
        //public TextView movieTitle;

        public MoviesViewholder(View moviesView){
            super(moviesView);

            movieImage = (ImageView)moviesView.findViewById(R.id.moviePoster);
            //movieTitle = (TextView)moviesView.findViewById(R.id.movieTitle);

            moviesView.setOnClickListener(this);
        }

        public void bindMovies(MoviesAPI movie){

            Picasso.with(mContext).load(movie.getMoviesPoster()).into(movieImage);
          //  movieTitle.setText(movie.getMoviesName());

        }

        @Override
        public void onClick(View v) {

            int index = getAdapterPosition();

            //REGISTER THE DATA TO THE BUS MOVIE SO WE CAN USE THEM ON SINGLE MOVIE VIEW//
            EventBus.getDefault().postSticky(new SingleMovieBus(
                    mMovies.get(index).getMoviesName(),
                    mMovies.get(index).getMoviesPoster(),
                    mMovies.get(index).getMovieImages(),
                    mMovies.get(index).getScreenings()
            ));

            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
