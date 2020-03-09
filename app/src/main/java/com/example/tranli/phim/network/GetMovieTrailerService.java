package com.example.tranli.phim.network;

import com.example.tranli.phim.model.MovieTrailerResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetMovieTrailerService {
    @GET("movie/{id}/videos")
    Call<MovieTrailerResult> getTrailers(@Path("id") int movieId, @Query("api_key") String userkey);
}