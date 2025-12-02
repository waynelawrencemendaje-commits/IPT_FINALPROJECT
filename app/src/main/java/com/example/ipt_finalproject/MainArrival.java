package com.example.ipt_finalproject;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainArrival extends AppCompatActivity {

    private final Movie arrival = MovieDatabase.ARRIVAL;
    private WatchLaterManager watchLaterManager;
    private MaterialButton btnWatchLater;
    private ValueEventListener watchLaterListener;
    private boolean isMovieInWatchLater = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        btnWatchLater = findViewById(R.id.btn_watch_later);
        watchLaterManager = WatchLaterManager.getInstance();

        updateWatchLaterButton();

        btnWatchLater.setOnClickListener(v -> {
            if (isMovieInWatchLater) {
                watchLaterManager.removeMovie(arrival);
                Toast.makeText(this, "Removed from Watch Later", Toast.LENGTH_SHORT).show();
            } else {
                watchLaterManager.addMovie(arrival);
                Toast.makeText(this, "Added to Watch Later", Toast.LENGTH_SHORT).show();
            }
        });

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
        if (watchLaterListener != null) {
            watchLaterManager.removeEventListener(watchLaterListener);
        }
    }

    private void setupFirebaseListener() {
        watchLaterListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                isMovieInWatchLater = false; 
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Movie movie = snapshot.getValue(Movie.class);
                    if (movie != null && movie.title.equals(arrival.title)) {
                        isMovieInWatchLater = true;
                        break;
                    }
                }
                updateWatchLaterButton();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainArrival.this, "Failed to load Watch Later status.", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void updateWatchLaterButton() {
        if (isMovieInWatchLater) {
            btnWatchLater.setIconTintResource(android.R.color.holo_green_light);
        } else {
            btnWatchLater.setIconTintResource(android.R.color.white);
        }
    }
}
