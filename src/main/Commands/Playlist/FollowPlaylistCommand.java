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

public class FollowPlaylistCommand implements Command {
    private String command = "follow";
    private String user;
    private int timestamp;
    private String message;

    /**
     * Constructor for the followPlaylist command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public FollowPlaylistCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Follows or unfollows a playlist.
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
        // Iterate through the list of users
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                // Check if the user has a last select command
                if (currentUser.getLastSelectCommand() != null) {
                    String playlist = currentUser.getLastSelectCommand().getSelectedResult();
                    // Find the playlist to follow or unfollow
                    for (User currentUser1 : users) {
                        for (Playlist playlist1 : currentUser1.getPlaylists()) {
                            // Check if the playlist matches the selected result
                            if (playlist1.getName().equals(playlist)) {
                                // Prevent following or unfollowing one's own playlist
                                if (currentUser1.getUsername().equals(currentUser.getUsername())) {
                                    this.message = "You cannot follow or unfollow your own "
                                            + "playlist.";
                                    return;
                                }
                                // Follow or unfollow the playlist based on its presence in the
                                // currentUser's followed playlists
                                if (currentUser.getFollowedPlaylists().contains(playlist1)) {
                                    playlist1.setFollowers(playlist1.getFollowers() - 1);
                                    currentUser.getFollowedPlaylists().remove(playlist1);
                                    this.message = "Playlist unfollowed successfully.";
                                    return;
                                } else {
                                    playlist1.setFollowers(playlist1.getFollowers() + 1);
                                    currentUser.getFollowedPlaylists().add(playlist1);
                                    this.message = "Playlist followed successfully.";
                                    return;
                                }
                            }
                        }
                    }
                    this.message = "The selected source is not a playlist.";
                } else {
                    this.message = "Please select a source before following or unfollowing.";
                }
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
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return "followPlaylist";
    }
}
