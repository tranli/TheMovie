package com.example.tranli.phim.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranli.phim.R;
import com.example.tranli.phim.model.Movie;
import com.example.tranli.phim.model.MovieTrailerResult;
import com.example.tranli.phim.network.GetMovieTrailerService;
import com.example.tranli.phim.network.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tranli.phim.ui.activity.MainActivity.movieImagePathBuilder;


@SuppressWarnings("ALL")
public class MovieActivity extends AppCompatActivity {
    @BindView(R.id.movie_activity_title) TextView mMovieTitle;
    @BindView(R.id.movie_activity_poster) ImageView mMoviePoster;
    @BindView(R.id.movie_activity_overview) TextView mMovieOverview;
    @BindView(R.id.movie_activity_release_date) TextView mMovieReleaseDate;
    @BindView(R.id.movie_activity_rating) TextView mMovieRating;

    //Just comment something


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Movie mMovie = (Movie) bundle.getSerializable("movie");

        getTrailer(mMovie.getId());
        populateActivity(mMovie);

    }

    private void populateActivity(Movie mMovie){
        Picasso.with(this).load(movieImagePathBuilder(mMovie.getPosterPath())).into(mMoviePoster);
        mMovieTitle.setText(mMovie.getTitle());
        mMovieOverview.setText(mMovie.getOverview());
        mMovieReleaseDate.setText(mMovie.getReleaseDate());

        String userRatingText = String.valueOf(mMovie.getVoteAverage()) + "/10";
        mMovieRating.setText(userRatingText);
    }

    private void getTrailer(int movieId) {
        GetMovieTrailerService movieTrailerService = RetrofitInstance.getRetrofitInstance().create(GetMovieTrailerService.class);
        Call<MovieTrailerResult> call = movieTrailerService.getTrailers(movieId, "54a98b457d866fba1f75e7fedeea6031");


        call.enqueue(new Callback<MovieTrailerResult>() {
            @Override
            public void onResponse(Call<MovieTrailerResult> call, Response<MovieTrailerResult> response) {
                Log.wtf("MovieActivity", "https://youtube.com/watch?v=" + response.body().getTrailerResult().get(0).getKey());

            }

            @Override
            public void onFailure(Call<MovieTrailerResult> call, Throwable t) {
                Toast.makeText(MovieActivity.this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }
        });
    }


}