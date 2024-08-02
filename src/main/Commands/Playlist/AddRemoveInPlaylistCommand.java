package main.Commands.Playlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Playlist;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class AddRemoveInPlaylistCommand implements Command {

    private String command = "addRemoveInPlaylist";
    private String user;
    private int timestamp;
    private int playlistId;
    private String message;

    /**
     * Constructor for the addRemoveInPlaylist command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param playlistId The playlist id.
     */
    public AddRemoveInPlaylistCommand(final String user, final int timestamp,
                                      final int playlistId) {
        this.user = user;
        this.timestamp = timestamp;
        this.playlistId = playlistId;
    }

    /**
     * Adds or removes a song from a playlist.
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
        for (User currentUser : users) {
            // Find the user by username
            if (currentUser.getUsername().equals(this.user)) {
                Playlist temp = null;

                // Search for the playlist with the specified playlistId
                for (Playlist playlist : currentUser.getPlaylists()) {
                    if (playlist.getPlaylistId() == this.playlistId) {
                        temp = playlist;
                        break;
                    }
                }

                // Check if the playlist exists
                if (temp == null) {
                    this.message = "The specified playlist does not exist.";
                    return;
                }

                // Check if there is a currently loaded source
                if (currentUser.getCurrentlyPlaying().equals("")) {
                    this.message = "Please load a source before adding to or removing"
                            + " from the playlist.";
                    return;
                }

                // Check if the loaded source is a song
                Boolean isSong = false;
                for (Song song : songs) {
                    if (song == currentUser.getCurrentlyPlayingSong()) {
                        isSong = true;
                        break;
                    }
                }
                if (!isSong) {
                    this.message = "The loaded source is not a song.";
                    return;
                }

                // Check if the song is already in the playlist
                Boolean isSongInPlaylist = false;
                for (Song song : currentUser.getPlaylists().get(this.playlistId - 1).
                        getSongsPlaylist()) {
                    if (song.equals(currentUser.getCurrentlyPlayingSong())) {
                        isSongInPlaylist = true;
                        break;
                    }
                }

                // Add or remove the song from the playlist based on its presence
                if (isSongInPlaylist) {
                    currentUser.getPlaylists().get(this.playlistId - 1).getSongsPlaylist().
                            remove(currentUser.getCurrentlyPlayingSong());
                    currentUser.getPlaylists().get(this.playlistId - 1).getSongs().
                            remove(currentUser.getCurrentlyPlayingSong().getName());
                    this.message = "Successfully removed from playlist.";
                    return;
                } else {
                    currentUser.getPlaylists().get(this.playlistId - 1).getSongsPlaylist().
                            add(currentUser.getCurrentlyPlayingSong());
                    currentUser.getPlaylists().get(this.playlistId - 1).getSongs().
                            add(currentUser.getCurrentlyPlayingSong().getName());
                    this.message = "Successfully added to playlist.";
                    return;
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
     * Gets the playlist id.
     *
     * @return The playlist id.
     */
    @JsonIgnore
    public int getPlaylistId() {
        return playlistId;
    }

    /**
     * Sets the playlist id.
     *
     * @param playlistId The playlist id.
     */
    public void setPlaylistId(final int playlistId) {
        this.playlistId = playlistId;
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
        return "addRemoveInPlaylist";
    }
}
