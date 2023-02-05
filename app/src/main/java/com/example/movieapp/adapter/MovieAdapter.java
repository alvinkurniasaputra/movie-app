package com.example.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieapp.DetailMovie;
import com.example.movieapp.R;
import com.example.movieapp.model.Result;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private List<Result> listMovie;

    public MovieAdapter(Context context, List<Result> listMovie){
        this.context = context;
        this.listMovie = listMovie;
    }
    public void setListMovie(List<Result> list) {
        listMovie = list;
    }

    @NonNull
    @NotNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movie, viewGroup, false);
        MovieAdapter.MyViewHolder viewHolder = new MovieAdapter.MyViewHolder(view);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), DetailMovie.class);
                Result result = new Result();
                result.setOriginalTitle(listMovie.get(viewHolder.getAdapterPosition()).getOriginalTitle());
                result.setDescription(listMovie.get(viewHolder.getAdapterPosition()).getDescription());
                result.setPosterPath(listMovie.get(viewHolder.getAdapterPosition()).getPosterPath());
                intent.putExtra(DetailMovie.EXTRA_MOVIE, result);
                viewGroup.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieAdapter.MyViewHolder myViewHolder, int i) {
        Result movie = listMovie.get(i);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w185" + listMovie.get(i).getPosterPath())
                .apply(new RequestOptions().override(350, 550))
                .into(myViewHolder.image);
        myViewHolder.tvTitle.setText(movie.getTitle());

    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView tvTitle, tvDescription;
        LinearLayout linearLayout;

        MyViewHolder(@NonNull final View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.cover);
            tvTitle = itemView.findViewById(R.id.title);
            linearLayout = itemView.findViewById(R.id.layoutMovie);


        }

    }

}
