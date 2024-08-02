package main.Media;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Song {
    private String name;
    private Integer duration;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private Integer releaseYear;
    private String artist;
    private double revenue = 0.0;

    private int likes = 0;

    /**
     * Constructor for Song
     */
    public Song() {

    }

    /**
     * Constructor for Song
     *
     * @param name        the name of the song
     * @param duration    the duration of the song
     * @param album       the album of the song
     * @param tags        the tags of the song
     * @param lyrics      the lyrics of the song
     * @param genre       the genre of the song
     * @param releaseYear the release year of the song
     * @param artist      the artist of the song
     */
    public Song(final String name, final Integer duration, final String album,
            final ArrayList<String> tags, final String lyrics, final String genre,
            final Integer releaseYear, final String artist) {
        this.name = name;
        this.duration = duration;
        this.album = album;
        this.tags = tags;
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    /**
     * Get the name of the song
     *
     * @return the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the song
     *
     * @param name the name of the song
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the duration of the song
     *
     * @return the duration of the song
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Set the duration of the song
     *
     * @param duration the duration of the song
     */
    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    /**
     * Get the album of the song
     *
     * @return the album of the song
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Set the album of the song
     *
     * @param album the album of the song
     */
    public void setAlbum(final String album) {
        this.album = album;
    }

    /**
     * Get the tags of the song
     *
     * @return the tags of the song
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Set the tags of the song
     *
     * @param tags the tags of the song
     */
    public void setTags(final ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
     * Get the lyrics of the song
     *
     * @return the lyrics of the song
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * Set the lyrics of the song
     *
     * @param lyrics the lyrics of the song
     */
    public void setLyrics(final String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     * Get the genre of the song
     *
     * @return the genre of the song
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set the genre of the song
     *
     * @param genre the genre of the song
     */
    public void setGenre(final String genre) {
        this.genre = genre;
    }

    /**
     * Get the release year of the song
     *
     * @return the release year of the song
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * Set the release year of the song
     *
     * @param releaseYear the release year of the song
     */
    public void setReleaseYear(final Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Get the artist of the song
     *
     * @return the artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Set the artist of the song
     *
     * @param artist the artist of the song
     */
    public void setArtist(final String artist) {
        this.artist = artist;
    }

    /**
     * Get the likes of the song
     *
     * @return the likes of the song
     */
    @JsonIgnore
    public int getLikes() {
        return likes;
    }

    /**
     * Set the likes of the song
     *
     * @param likes the likes of the song
     */
    public void setLikes(final int likes) {
        this.likes = likes;
    }

    /**
     * Get the revenue of the song
     *
     * @return the revenue of the song
     */
    public double getRevenue() {
        return revenue;
    }

    /**
     * Set the revenue of the song
     *
     * @param revenue the revenue of the song
     */
    public void setRevenue(final double revenue) {
        this.revenue = revenue;
    }

    /**
     * Returns a string representation of the song.
     *
     * @return A string representation of the song.
     */
    @Override
    public String toString() {
        return name + " - " + artist;
    }
}
