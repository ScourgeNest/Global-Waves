package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Album;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class LikeCommand implements Command {
    private String command = "like";
    private String user;
    private int timestamp;
    private String message;
    /**
     * Constructor for the load command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public LikeCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Likes the currently playing song.
     *
     * @param users The users to execute the command on.
     * @param songs The songs to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                     final ArrayList<User> users, final ArrayList<Artist> artists,
                     final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getConnectionStatus().equals("offline")) {
                    this.message = this.user + " is offline.";
                    return;
                }
            }
        }
        // Iterate through the list of users
        for (User currentUser : users) {
            // Check if the current user matches the specified user
            if (currentUser.getUsername().equals(this.user)) {
                // Check if the user is not currently playing anything
                if (currentUser.getCurrentlyPlaying().equals("")) {
                    this.message = "Please load a source before liking or unliking.";
                    return;
                }

                // Initialize a variable to store the currently playing song
                Song currentlyPlaying = null;

                // Find the currently playing song in the list of songs
                for (Song song : songs) {
                    if (song == currentUser.getCurrentlyPlayingSong()) {
                        currentlyPlaying = song;
                        break;
                    }
                }

                // Check if the currently playing song was found
                if (currentlyPlaying == null) {
                    this.message = "Loaded source is not a song.";
                    return;
                }

                // Check if the user has already liked the currently playing song
                if (currentUser.getPreferredSongs().contains(currentlyPlaying)) {
                    // If liked, remove from preferred songs and decrement likes
                    currentUser.getPreferredSongs().remove(currentlyPlaying);
                    currentlyPlaying.setLikes(currentlyPlaying.getLikes() - 1);
                    for (Artist currentArtist : artists) {
                        for (Album currentAlbum : currentArtist.getAlbums()) {
                            if (currentAlbum.getSongsPlaylist().contains(currentlyPlaying)) {
                                currentAlbum.setLikes(currentAlbum.getLikes() - 1);
                            }
                        }
                    }
                    this.message = "Unlike registered successfully.";
                } else {
                    // If not liked, add to preferred songs and increment likes
                    currentUser.getPreferredSongs().add(currentlyPlaying);
                    currentlyPlaying.setLikes(currentlyPlaying.getLikes() + 1);
                    for (Artist currentArtist : artists) {
                        for (Album currentAlbum : currentArtist.getAlbums()) {
                            if (currentAlbum.getSongsPlaylist().contains(currentlyPlaying)) {
                                currentAlbum.setLikes(currentAlbum.getLikes() + 1);
                            }
                        }
                    }
                    this.message = "Like registered successfully.";
                }
            }
        }
    }


    /**
     * Gets the command.
     *
     * @return The command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command.
     *
     * @param command The command.
     */
    public void setCommand(final String command) {
        this.command = command;
    }

    /**
     * Gets the username.
     *
     * @return The username.
     */
    public String getUser() {
        return user;
    }

    /**
     * @return
     */
    @Override @JsonIgnore
    public Object getResults() {
        return null;
    }

    /**
     * Sets the username.
     *
     * @param user The username.
     */
    public void setUser(final String user) {
        this.user = user;
    }

    /**
     * Gets the timestamp.
     *
     * @return The timestamp.
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp The timestamp.
     */
    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the message.
     *
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message The message.
     */
    public void setMessage(final String message) {
        this.message = message;
    }
    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return "like";
    }
}
