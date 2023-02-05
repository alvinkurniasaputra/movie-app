package com.example.movieapp.rest;

//import com.example.movieapp.model.MovieSearchResponse;
import com.example.movieapp.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/{category}")
    Call<Response> getMovies(
            @Path("category") String category, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<Response> getQuery(
            @Query("api_key") String apiKey, @Query("language") String language, @Query("query") String Query);


}
