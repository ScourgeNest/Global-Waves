package main.Media;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Album extends Playlist {
    private String name;
    private int releaseYear;
    private String description;
    private String owner;
    private ArrayList<Song> songsPlaylist = new ArrayList<>();
    private ArrayList<String> songs;
    private String visibility = "public";
    private int followers = 0;
    private int likes = 0;

    /**
     * Constructor for the album.
     *
     * @param name        The name of the album.
     * @param releaseYear The release year of the album.
     * @param description The description of the album.
     * @param owner       The owner of the album.
     * @param songs       The songs of the album.
     */
    public Album(final String name, final int releaseYear, final String description,
                 final String owner, final ArrayList<Song> songs) {
        super(name);
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.owner = owner;
        this.songsPlaylist = songs;
        this.songs = new ArrayList<>();
        for (Song currentSong : songs) {
            this.songs.add(currentSong.getName());
        }
    }

    /**
     * Gets the name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name The name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the release year.
     *
     * @return The release year.
     */
    @JsonIgnore
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year.
     *
     * @param releaseYear The release year.
     */
    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the description.
     *
     * @return The description.
     */
    @JsonIgnore
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description The description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the songs.
     *
     * @return The songs.
     */
    @JsonIgnore
    public ArrayList<Song> getSongsPlaylist() {
        return songsPlaylist;
    }

    /**
     * Sets the songs.
     *
     * @param playlistSongs The songs.
     */
    public void setAlbumSongs(final ArrayList<Song> playlistSongs) {
        this.songsPlaylist = playlistSongs;
    }

    /**
     * Gets the songs.
     *
     * @return The songs.
     */
    public ArrayList<String> getSongs() {
        return songs;
    }

    /**
     * Sets the songs.
     *
     * @param songs The songs.
     */
    public void setSongs(final ArrayList<String> songs) {
        this.songs = songs;
    }

    /**
     * Gets the owner.
     *
     * @return The owner.
     */
    @JsonIgnore
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner.
     *
     * @param owner The owner.
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     * Returns a string representation of the album.
     *
     * @return A string representation of the album.
     */
    @JsonIgnore
    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets the visibility.
     *
     * @return The visibility.
     */
    @Override
    @JsonIgnore
    public String getVisibility() {
        return visibility;
    }

    /**
     * Sets the visibility.
     *
     * @param visibility The visibility.
     */
    @Override
    public void setVisibility(final String visibility) {
        this.visibility = visibility;
    }

    /**
     * Gets the followers.
     *
     * @return The followers.
     */
    @Override
    @JsonIgnore
    public int getFollowers() {
        return followers;
    }

    /**
     * Sets the followers.
     *
     * @param followers The followers.
     */
    @Override
    public void setFollowers(final int followers) {
        this.followers = followers;
    }

    /**
     * Gets the songs playlist.
     *
     * @return The songs playlist.
     */
    @Override
    public void setSongsPlaylist(final ArrayList<Song> songsPlaylist) {
        this.songsPlaylist = songsPlaylist;
    }

    /**
     * Gets the likes.
     *
     * @return The likes.
     */
    @JsonIgnore
    public int getLikes() {
        return likes;
    }

    /**
     * Sets the likes.
     *
     * @param likes The likes.
     */
    public void setLikes(final int likes) {
        this.likes = likes;
    }

    /**
     * Returns true because it is an album.
     *
     * @return True.
     */
    @Override
    public boolean isAlbum() {
        return true;
    }
}
