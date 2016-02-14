package eu_dfg_team_projects.stercinemas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import eu_dfg_team_projects.stercinemas.API.SterCinemasAPI;
import eu_dfg_team_projects.stercinemas.MoviesActivitiesView;
import eu_dfg_team_projects.stercinemas.R;

/**
 * Created by georgetsd on 13/2/16.
 */
public class CinemasAdapter extends RecyclerView.Adapter<CinemasAdapter.CinemasViewHolder> {

    private ArrayList<SterCinemasAPI> mCinemas;
    private Context mContext;

    public CinemasAdapter(Context context, ArrayList<SterCinemasAPI> cinemas){
        mCinemas = cinemas;
        mContext = context;
    }


    @Override
    public CinemasAdapter.CinemasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.cinemas_layout, parent, false);
        CinemasViewHolder viewHolder = new CinemasViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CinemasViewHolder holder, int position) {
        holder.bindCinemas(mCinemas.get(position));
    }

    @Override
    public int getItemCount() {
        return mCinemas.size();
    }


    public class CinemasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView cinemaImage;
        public TextView cinemaTitle;

        public CinemasViewHolder(View cinemasView){
            super(cinemasView);

            cinemaImage = (ImageView)cinemasView.findViewById(R.id.cinemaImage);
            cinemaTitle = (TextView)cinemasView.findViewById(R.id.cinemaTitle);

            cinemasView.setOnClickListener(this);
        }

        public void bindCinemas(SterCinemasAPI cinema){

            Picasso.with(mContext).load(cinema.getPhoto()).into(cinemaImage);
            cinemaTitle.setText(cinema.getCinemasName());
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(itemView.getContext(), MoviesActivitiesView.class);
            intent.putParcelableArrayListExtra("Movies", mCinemas.get(getAdapterPosition()).getMovies());
            intent.putExtra("CinemaName", mCinemas.get(getAdapterPosition()).getCinemasName());
            itemView.getContext().startActivity(intent);


        }
    }
}
