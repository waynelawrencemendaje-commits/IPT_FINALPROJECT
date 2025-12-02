package com.example.ipt_finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainWatchLater extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WatchLaterAdapter adapter;
    private TextView tvEmpty;
    private WatchLaterManager watchLaterManager;
    private ValueEventListener watchLaterListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlater);

        findViewById(R.id.iv_return).setOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.recycler_watch_later);
        tvEmpty = findViewById(R.id.tv_empty);
        watchLaterManager = WatchLaterManager.getInstance();

        setupRecyclerView();
        setupFirebaseListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        watchLaterManager.getWatchLaterMovies(watchLaterListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        watchLaterManager.removeEventListener(watchLaterListener);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new WatchLaterAdapter(this, new ArrayList<>(), movie -> watchLaterManager.removeMovie(movie));
        recyclerView.setAdapter(adapter);
    }

    private void setupFirebaseListener() {
        watchLaterListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Movie> movies = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Movie movie = snapshot.getValue(Movie.class);
                    if (movie != null) {
                        movies.add(movie);
                    }
                }
                updateUI(movies);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                tvEmpty.setText("Failed to load list.");
                tvEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        };
    }

    private void updateUI(ArrayList<Movie> movies) {
        if (movies.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        adapter.updateData(movies);
    }
}
