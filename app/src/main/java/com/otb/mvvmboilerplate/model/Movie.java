package com.otb.mvvmboilerplate.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Mohit Rajput on 11/2/19.
 */
@Parcel
public class Movie {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("poster")
    private String poster;

    @SerializedName("summary")
    private String summary;

    @SerializedName("cast")
    private String cast;

    @SerializedName("director")
    private String director;

    @SerializedName("year")
    private String year;

    @SerializedName("trailer")
    private String trailer;

    @SerializedName("genre")
    private String genre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", summary='" + summary + '\'' +
                ", cast='" + cast + '\'' +
                ", director='" + director + '\'' +
                ", year='" + year + '\'' +
                ", trailer='" + trailer + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
