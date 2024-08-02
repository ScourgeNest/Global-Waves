package main.Media;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Song> songsPlaylist;
    private ArrayList<String> songs = new ArrayList<String>();
    private int playlistId;
    private String owner;
    private String visibility = "public";
    private int followers = 0;
    private int createdAt;

    /**
     * Constructor for Playlist
     *
     * @param name the name of the playlist
     */
    public Playlist(final String name) {
        this.name = name;
        this.songsPlaylist = new ArrayList<Song>();
        this.songs = new ArrayList<String>();
    }

    /**
     * Get the name of the playlist
     *
     * @return the name of the playlist
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the playlist
     *
     * @param name the name of the playlist
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the songs of the playlist
     *
     * @return the songs of the playlist
     */
    @JsonIgnore
    public ArrayList<Song> getSongsPlaylist() {
        return songsPlaylist;
    }

    /**
     * Set the songs of the playlist
     *
     * @param songsPlaylist the songs of the playlist
     */
    public void setSongsPlaylist(final ArrayList<Song> songsPlaylist) {
        this.songsPlaylist = songsPlaylist;
    }

    /**
     * Get the ID of the playlist
     *
     * @return the ID of the playlist
     */
    @JsonIgnore
    public int getPlaylistId() {
        return playlistId;
    }

    /**
     * Set the ID of the playlist
     *
     * @param playlistId the ID of the playlist
     */
    public void setPlaylistId(final int playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Get the owner of the playlist
     *
     * @return the owner of the playlist
     */
    @JsonIgnore
    public String getOwner() {
        return owner;
    }

    /**
     * Set the owner of the playlist
     *
     * @param owner the owner of the playlist
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     * Get the visibility of the playlist
     *
     * @return the visibility of the playlist
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Set the visibility of the playlist
     *
     * @param visibility the visibility of the playlist
     */
    public void setVisibility(final String visibility) {
        this.visibility = visibility;
    }

    /**
     * Get the followers of the playlist
     *
     * @return the followers of the playlist
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * Set the followers of the playlist
     *
     * @param followers the followers of the playlist
     */
    public void setFollowers(final int followers) {
        this.followers = followers;
    }

    /**
     * Get the songs of the playlist
     *
     * @return the songs of the playlist
     */
    public ArrayList<String> getSongs() {
        return songs;
    }

    /**
     * Set the songs of the playlist
     *
     * @param songs the songs of the playlist
     */
    public void setSongs(final ArrayList<String> songs) {
        this.songs = songs;
    }

    /**
     * Get the creation date of the playlist
     *
     * @return the creation date of the playlist
     */
    @JsonIgnore
    public int getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the creation date of the playlist
     *
     * @param createdAt the creation date of the playlist
     */
    public void setCreatedAt(final int createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns false because this is not an album
     *
     * @return false
     */
    @JsonIgnore
    public boolean isAlbum() {
        return false;
    }
}
