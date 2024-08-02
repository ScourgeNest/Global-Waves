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

public class CreatePlaylistCommand implements Command {
    private String command = "createPlaylist";
    private String user;
    private int timestamp;
    private String message;
    private String playlistName;

    /**
     * Constructor for the createPlaylist command.
     *
     * @param user        The user.
     * @param playlistName The playlist name.
     * @param timestamp   The timestamp.
     */
    public CreatePlaylistCommand(final String user, final String playlistName,
                                 final int timestamp) {
        this.user = user;
        this.playlistName = playlistName;
        this.timestamp = timestamp;
    }

    /**
     * Creates a playlist.
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
        // Create a new playlist
        Playlist newplaylist = new Playlist(this.playlistName);

        // Find the user by username
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                // Check if a playlist with the same name already exists
                for (Playlist playlist : currentUser.getPlaylists()) {
                    if (playlist.getName().equals(this.playlistName)) {
                        this.message = "A playlist with the same name already exists.";
                        return;
                    }
                }

                // Set properties for the new playlist
                newplaylist.setVisibility("public");
                newplaylist.setOwner(currentUser.getUsername());
                newplaylist.setCreatedAt(this.timestamp);
                newplaylist.setPlaylistId(currentUser.getPlaylists().size() + 1);
                newplaylist.setName(this.playlistName);

                // Add the new playlist to the currentUser's playlists
                currentUser.getPlaylists().add(newplaylist);

                // Set success message
                this.message = "Playlist created successfully.";
            }
        }
    }


    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return "createPlaylist";
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
     * Gets the playlist name.
     *
     * @return The playlist name.
     */
    @JsonIgnore
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * Sets the playlist name.
     *
     * @param playlistName The playlist name.
     */
    public void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
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
}
