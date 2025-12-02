package com.example.ipt_finalproject;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WatchLaterManager {
    private static WatchLaterManager instance;
    private DatabaseReference watchLaterRef;

    private WatchLaterManager() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            watchLaterRef = FirebaseDatabase.getInstance().getReference("watchLater").child(userId);
        }
    }

    public static synchronized WatchLaterManager getInstance() {
        if (instance == null) {
            instance = new WatchLaterManager();
        }
        return instance;
    }

    public void addMovie(Movie movie) {
        if (watchLaterRef != null) {
            watchLaterRef.child(movie.title).setValue(movie);
        }
    }

    public void removeMovie(Movie movie) {
        if (watchLaterRef != null) {
            watchLaterRef.child(movie.title).removeValue();
        }
    }

    public void getWatchLaterMovies(ValueEventListener listener) {
        if (watchLaterRef != null) {
            watchLaterRef.addValueEventListener(listener);
        }
    }

    public void removeEventListener(ValueEventListener listener) {
        if (watchLaterRef != null) {
            watchLaterRef.removeEventListener(listener);
        }
    }
}
