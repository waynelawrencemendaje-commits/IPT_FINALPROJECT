package com.example.ipt_finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.function.Consumer;

public class WatchLaterAdapter extends RecyclerView.Adapter<WatchLaterAdapter.ViewHolder> {
    private final ArrayList<Movie> movies;
    private final Context context;
    private final Consumer<Movie> onRemoveListener;

    public WatchLaterAdapter(Context context, ArrayList<Movie> movies, Consumer<Movie> onRemoveListener) {
        this.context = context;
        this.movies = movies;
        this.onRemoveListener = onRemoveListener;
    }

    public void updateData(ArrayList<Movie> newList) {
        movies.clear();
        movies.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_watch_later_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.title);
        holder.tvDetails.setText(movie.year + "  •  " + movie.duration);
        holder.ivPoster.setImageResource(movie.posterResId);

        holder.btnRemove.setOnClickListener(v -> {
            if (onRemoveListener != null) {
                onRemoveListener.accept(movie);
                Toast.makeText(context, movie.title + " removed", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(v -> {
            Class<?> activityClass = movie.getActivityClass();
            if (activityClass != null) {
                Intent intent = new Intent(context, activityClass);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Details not available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle, tvDetails;
        ImageButton btnRemove;

        ViewHolder(View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDetails = itemView.findViewById(R.id.tv_details);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
