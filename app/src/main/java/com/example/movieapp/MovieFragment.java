package com.example.movieapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.adapter.MovieAdapter;
import com.example.movieapp.model.Response;
import com.example.movieapp.model.Result;
import com.example.movieapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment {

    private RecyclerView rvMovie;
    String API_KEY = "8ea1c3c77c7520b40afc3152d555f27b";
    String CATEGORY = "popular";

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        rvMovie = view.findViewById(R.id.moviefragment);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new GridLayoutManager(this.getContext(), 2));

        callRetrofit();

        return view;
    }

    private void callRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface movieDBService = retrofit.create(ApiInterface.class);

        Call<Response> call = movieDBService.getMovies(CATEGORY,API_KEY);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                List<Result> movieList = response.body().getResults();
                MovieAdapter myViewHolder = new MovieAdapter(getContext(), movieList);
                rvMovie.setAdapter(myViewHolder);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });


    }

}
