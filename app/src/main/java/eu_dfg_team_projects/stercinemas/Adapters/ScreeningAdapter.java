package eu_dfg_team_projects.stercinemas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import eu_dfg_team_projects.stercinemas.API.ScreeningsAPI;
import eu_dfg_team_projects.stercinemas.R;

/**
 * Created by georgetsd on 13/2/16.
 */
public class ScreeningAdapter extends RecyclerView.Adapter<ScreeningAdapter.ScreensViewholder> {

    private ArrayList<ScreeningsAPI> mScreens;
    private Context mContext;


    public ScreeningAdapter(Context context, ArrayList<ScreeningsAPI> screens){
        mScreens = screens;
        mContext = context;
    }


    @Override
    public ScreeningAdapter.ScreensViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.screening_layout, parent, false);
        ScreensViewholder viewHolder = new ScreensViewholder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScreensViewholder holder, int position) {
        holder.bindMovies(mScreens.get(position));
    }

    @Override
    public int getItemCount() {
        return mScreens.size();
    }


    public class ScreensViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mDateScreen;
        public TextView mHoursScreen;


        public ScreensViewholder(View screensView){
            super(screensView);

            mDateScreen = (TextView)screensView.findViewById(R.id.dateScreen);
            mHoursScreen = (TextView)screensView.findViewById(R.id.times);

            screensView.setOnClickListener(this);
        }

        public void bindMovies(ScreeningsAPI screen){

            String times = "";

            for (int i = 0; i < screen.getTimes().length; i++){

                times += screen.getTimes()[i] + "  ";

            }

            mDateScreen.setText(screen.getDate());
            mHoursScreen.setText(times);
        }

        @Override
        public void onClick(View v) {

            int index = getAdapterPosition();

        }
    }
}
