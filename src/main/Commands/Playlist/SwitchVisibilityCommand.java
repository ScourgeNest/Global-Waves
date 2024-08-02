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

public class SwitchVisibilityCommand implements Command {
    private String command = "switchVisibility";
    private String user;
    private int timestamp;
    private String message;
    private int playlistId;

    /**
     * Constructor for the switchVisibility command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param playlistId The playlist id.
     */
    public SwitchVisibilityCommand(final String user, final int timestamp, final int playlistId) {
        this.user = user;
        this.timestamp = timestamp;
        this.playlistId = playlistId;
    }

    /**
     * Switches the visibility of a playlist.
     *
     * @param users The users to execute the command on.
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
        // Loop through each user in the provided list of users
        for (User currentUser : users) {
            // Check if the username of the current user matches 'this.user'
            if (currentUser.getUsername().equals(this.user)) {
                // Iterate through the playlists of the current user
                for (Playlist playlist : currentUser.getPlaylists()) {
                    // Check if the playlist ID matches 'this.playlistId'
                    if (playlist.getPlaylistId() == this.playlistId) {
                        // Check the visibility status of the playlist
                        if (playlist.getVisibility().equals("public")) {
                            // If the playlist is public, change visibility to private
                            playlist.setVisibility("private");
                            this.message = "Visibility status updated successfully to private.";
                            return; // Exit the method after updating visibility
                        } else {
                            // If the playlist is private, change visibility to public
                            playlist.setVisibility("public");
                            this.message = "Visibility status updated successfully to public.";
                            return; // Exit the method after updating visibility
                        }
                    }
                }
                // If the specified playlist ID isn't found in the user's playlists
                this.message = "The specified playlist ID is too high.";
            }
        }
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command name.
     *
     * @param command The command name.
     */
    public void setCommand(final String command) {
        this.command = command;
    }

    /**
     * Gets the user.
     *
     * @return The user.
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
     * Sets the user.
     *
     * @param user The user.
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
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return "switchVisibility";
    }
}
