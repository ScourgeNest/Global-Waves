package main.TypeOfUsers.Stats_and_Builder_Pattern;

import main.Media.Song;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WrappedStatistics {

    private LinkedHashMap<String, Integer> topArtists;
    private LinkedHashMap<String, Integer> topGenres;
    private LinkedHashMap<String, Integer> topSongs;
    private ArrayList<Song> songs;
    private LinkedHashMap<String, Integer> topAlbums;
    private LinkedHashMap<String, Integer> topEpisodes;
    private LinkedHashMap<String, Integer> topFans;
    private ArrayList<String> listeners;

    /**
     * Constructor for the wrapped statistics.
     */
    public static class Builder {

        private LinkedHashMap<String, Integer> topArtists;
        private LinkedHashMap<String, Integer> topGenres;
        private LinkedHashMap<String, Integer> topSongs;
        private LinkedHashMap<String, Integer> topAlbums;
        private LinkedHashMap<String, Integer> topEpisodes;
        private LinkedHashMap<String, Integer> topFans;
        private ArrayList<String> listeners;

        /**
         * Constructor for the builder.
         */
        public Builder() {
        }

        /**
         * Builds the top artists.
         *
         * @return The builder.
         */
        public Builder buildTopArtist() {
            this.topArtists = new LinkedHashMap<String, Integer>();
            return this;
        }

        /**
         * Builds the top genres.
         *
         * @return The builder.
         */
        public Builder buildTopGenre() {
            this.topGenres = new LinkedHashMap<String, Integer>();
            return this;
        }

        /**
         * Builds the top songs.
         *
         * @return The builder.
         */
        public Builder buildTopSong() {
            this.topSongs = new LinkedHashMap<String, Integer>();
            return this;
        }

        /**
         * Builds the top albums.
         *
         * @return The builder.
         */
        public Builder buildTopAlbum() {
            this.topAlbums = new LinkedHashMap<String, Integer>();
            return this;
        }

        /**
         * Builds the top episodes.
         *
         * @return The builder.
         */
        public Builder buildTopEpisode() {
            this.topEpisodes = new LinkedHashMap<String, Integer>();
            return this;
        }

        /**
         * Builds the top fans.
         *
         * @return The builder.
         */
        public Builder buildTopFan() {
            this.topFans = new LinkedHashMap<String, Integer>();
            return this;
        }

        /**
         * Builds the listeners.
         *
         * @return The builder.
         */
        public Builder buildListener() {
            this.listeners = new ArrayList<String>();
            return this;
        }

        /**
         * Builds the statistics.
         *
         * @return The statistics.
         */
        public WrappedStatistics build() {
            WrappedStatistics stats = new WrappedStatistics();
            stats.topArtists = this.topArtists;
            stats.topGenres = this.topGenres;
            stats.topSongs = this.topSongs;
            stats.topAlbums = this.topAlbums;
            stats.topEpisodes = this.topEpisodes;
            stats.topFans = this.topFans;
            stats.listeners = this.listeners;
            stats.songs = new ArrayList<Song>();
            return stats;
        }
    }

    /**
     * Adds a top artist or increments the number of times the artist has been listened to.
     */
    public void addTopArtist(final String artist) {
        if (this.topArtists.containsKey(artist)) {
            this.topArtists.put(artist, this.topArtists.get(artist) + 1);
        } else {
            this.topArtists.put(artist, 1);
        }
    }

    /**
     * Adds a top genre or increments the number of times the genre has been listened to.
     */

    public void addTopGenre(final String genre) {
        if (this.topGenres.containsKey(genre)) {
            this.topGenres.put(genre, this.topGenres.get(genre) + 1);
        } else {
            this.topGenres.put(genre, 1);
        }
    }

    /**
     * Adds a top song or increments the number of times the song has been listened to.
     */
    public void addTopSong(final String song) {
        if (this.topSongs.containsKey(song)) {
            this.topSongs.put(song, this.topSongs.get(song) + 1);
        } else {
            this.topSongs.put(song, 1);
        }
    }

    /**
     * Adds a top album or increments the number of times the album has been listened to.
     */
    public void addTopAlbum(final String album) {
        if (this.topAlbums.containsKey(album)) {
            this.topAlbums.put(album, this.topAlbums.get(album) + 1);
        } else {
            this.topAlbums.put(album, 1);
        }
    }

    /**
     * Adds a top episode or increments the number of times the episode has been listened to.
     */
    public void addTopEpisode(final String episode) {
        if (this.topEpisodes.containsKey(episode)) {
            this.topEpisodes.put(episode, this.topEpisodes.get(episode) + 1);
        } else {
            this.topEpisodes.put(episode, 1);
        }
    }

    /**
     * Adds a top fan or increments the number of times the fan has listened to the user.
     */
    public void addTopFan(final String fan) {
        if (this.topFans.containsKey(fan)) {
            this.topFans.put(fan, this.topFans.get(fan) + 1);
        } else {
            this.topFans.put(fan, 1);
        }
    }

    /**
     * Adds a listener.
     */
    public void addListener(final String listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    /**
     * Adds a song.
     */
    public void addSong(final Song song) {
        this.songs.add(song);
    }

    /**
     * Gets the top artists.
     *
     * @return The top artists.
     */
    public LinkedHashMap<String, Integer> getTopArtists() {
        return topArtists;
    }

    /**
     * Sets the top artists.
     *
     * @param topArtists The top artists.
     */
    public void setTopArtists(final LinkedHashMap<String, Integer> topArtists) {
        this.topArtists = topArtists;
    }

    /**
     * Gets the top genres.
     *
     * @return The top genres.
     */
    public LinkedHashMap<String, Integer> getTopGenres() {
        return topGenres;
    }

    /**
     * Sets the top genres.
     *
     * @param topGenres The top genres.
     */
    public void setTopGenres(final LinkedHashMap<String, Integer> topGenres) {
        this.topGenres = topGenres;
    }

    /**
     * Gets the top songs.
     *
     * @return The top songs.
     */
    public LinkedHashMap<String, Integer> getTopSongs() {
        return topSongs;
    }

    /**
     * Sets the top songs.
     *
     * @param topSongs The top songs.
     */
    public void setTopSongs(final LinkedHashMap<String, Integer> topSongs) {
        this.topSongs = topSongs;
    }

    /**
     * Gets the top albums.
     *
     * @return The top albums.
     */
    public LinkedHashMap<String, Integer> getTopAlbums() {
        return topAlbums;
    }

    /**
     * Sets the top albums.
     *
     * @param topAlbums The top albums.
     */
    public void setTopAlbums(final LinkedHashMap<String, Integer> topAlbums) {
        this.topAlbums = topAlbums;
    }

    /**
     * Gets the top episodes.
     *
     * @return The top episodes.
     */
    public LinkedHashMap<String, Integer> getTopEpisodes() {
        return topEpisodes;
    }

    /**
     * Sets the top episodes.
     *
     * @param topEpisodes The top episodes.
     */
    public void setTopEpisodes(final LinkedHashMap<String, Integer> topEpisodes) {
        this.topEpisodes = topEpisodes;
    }

    /**
     * Gets the top fans.
     *
     * @return The top fans.
     */
    public LinkedHashMap<String, Integer> getTopFans() {
        return topFans;
    }

    /**
     * Sets the top fans.
     *
     * @param topFans The top fans.
     */
    public void setTopFans(final LinkedHashMap<String, Integer> topFans) {
        this.topFans = topFans;
    }

    /**
     * Gets the listeners.
     *
     * @return The listeners.
     */
    public ArrayList<String> getListeners() {
        return listeners;
    }

    /**
     * Sets the listeners.
     *
     * @param listeners The listeners.
     */
    public void setListeners(final ArrayList<String> listeners) {
        this.listeners = listeners;
    }

    /**
     * Gets the songs.
     *
     * @return The songs.
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the songs.
     *
     * @param songs The songs.
     */
    public void setSongs(final ArrayList<Song> songs) {
        this.songs = songs;
    }
}
