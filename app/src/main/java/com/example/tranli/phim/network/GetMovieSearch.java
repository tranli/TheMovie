package com.example.tranli.phim.network;

import com.example.tranli.phim.model.Movie;
import com.example.tranli.phim.model.MoviePageResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetMovieSearch {
    @GET("search/movie/")
    Call<MoviePageResult> getMovieSearch(@Query("query")  String name, @Query("api_key") String userkey);

}
