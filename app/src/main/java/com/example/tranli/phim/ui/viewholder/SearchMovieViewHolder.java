package com.example.tranli.phim.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class SearchMovieViewHolder extends RecyclerView.ViewHolder {

    private View itemView;
    public ImageView mMoviePoster;
    public TextView mTitle;
    public TextView mOverview;


    public SearchMovieViewHolder(View itemView) {
        super(itemView);
    }
}
