package com.example.ipt_finalproject;

import com.google.firebase.database.Exclude;

public class Movie {
    public String title;
    public String year;
    public String duration;
    public int posterResId;
    public String activityClassName;  // This tells which activity to open

    // Default constructor required for calls to DataSnapshot.getValue(Movie.class)
    public Movie() {
    }

    public Movie(String title, String year, String duration, int posterResId, Class<?> activityClass) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.posterResId = posterResId;
        if (activityClass != null) {
            this.activityClassName = activityClass.getName();
        }
    }

    @Exclude
    public Class<?> getActivityClass() {
        if (activityClassName == null) {
            return null;
        }
        try {
            return Class.forName(activityClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
