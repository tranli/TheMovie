package com.example.tranli.phim.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranli.phim.R;
import com.example.tranli.phim.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.tranli.phim.ui.activity.MainActivity.movieImagePathBuilder;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.ViewHolder>{

    private Context mContext;
    public ArrayList<Movie> mMovies;

    //hello kitty
    public SearchMovieAdapter(ArrayList<Movie> movies, Context mContext) {
        this.mMovies = movies;
        this.mContext = mContext;

    }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Nạp layout cho View biểu diễn phần tử
            View Movies = inflater.inflate(R.layout.search_movie_card, parent, false);

            ViewHolder viewHolder = new ViewHolder(Movies);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Movie movie = mMovies.get(position);

            holder.mTitle.setText(movie.getTitle());
            holder.mOverview.setText(movie.getOverview());
            Picasso.with(mContext).load(movieImagePathBuilder(movie.getPosterPath())).into(holder.mPoster);

        }

        @Override
        public int getItemCount() {
            return mMovies.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            private View itemview;
            public ImageView mPoster;
            public TextView mTitle;
            public TextView mOverview;

            public ViewHolder(View itemView) {
                super(itemView);
                mPoster = (ImageView) itemView.findViewById(R.id.img_movie_poster);
                mTitle = itemView.findViewById(R.id.tv_title);
                mOverview = itemView.findViewById(R.id.tv_overview);

            }
        }


    }
