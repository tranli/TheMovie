package com.example.tranli.phim.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranli.phim.R;
import com.example.tranli.phim.model.Movie;
import com.example.tranli.phim.model.MoviePageResult;
import com.example.tranli.phim.model.MovieTrailerResult;
import com.example.tranli.phim.network.GetMovieSearch;
import com.example.tranli.phim.network.GetMovieTrailerService;
import com.example.tranli.phim.network.RetrofitInstance;
import com.example.tranli.phim.ui.adapter.SearchMovieAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovie extends AppCompatActivity {

private EditText edtSearch;
private Button btnSearch;

    RecyclerView recyclerView;
    SearchMovieAdapter adapter;
    ArrayList<Movie> ResultsMovies = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        recyclerView = findViewById(R.id.rv_search_movie);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchMovie.this);
        recyclerView.setLayoutManager(layoutManager);

        edtSearch = findViewById(R.id.edt_search_movie);
        btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetMovieSearch movieSearch = RetrofitInstance.getRetrofitInstance().create(GetMovieSearch.class);
                Call<MoviePageResult> call = movieSearch.getMovieSearch(edtSearch.getText().toString(), "54a98b457d866fba1f75e7fedeea6031");

                call.enqueue(new Callback<MoviePageResult>() {
                    @Override
                    public void onResponse(Call<MoviePageResult> call, Response<MoviePageResult> response) {



                         ArrayList<Movie> movies = response.body().getMovieResult();
//                         ResultsMovies.addAll(movies);
                        adapter = new SearchMovieAdapter(movies, SearchMovie.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MoviePageResult> call, Throwable t) {

                    }
                });
            }
        });


    }
}
