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

public class ShowPlaylistsCommand implements Command {

    private String command = "showPlaylists";
    private String user;
    private int timestamp;
    private ArrayList<Playlist> result;

    /**
     * Constructor for the showPlaylists command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public ShowPlaylistsCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
        this.result = new ArrayList<Playlist>();
    }

    /**
     * Shows the playlists.
     *
     * @param users The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        // Iterate through the list of users
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                // Check if the user's playlists are empty
                if (currentUser.getPlaylists().isEmpty()) {
                    return; // If empty, return without adding anything to the result
                }
                // Loop through the currentUser's playlists and create Playlist objects
                // with their details
                for (int i = 0; i < currentUser.getPlaylists().size(); i++) {
                    Playlist playlist = new Playlist(currentUser.getPlaylists().get(i).getName());
                    playlist.setOwner(currentUser.getPlaylists().get(i).getOwner());
                    playlist.setPlaylistId(currentUser.getPlaylists().get(i).getPlaylistId());
                    playlist.setVisibility(currentUser.getPlaylists().get(i).getVisibility());
                    playlist.setFollowers(currentUser.getPlaylists().get(i).getFollowers());
                    playlist.setSongs(currentUser.getPlaylists().get(i).getSongs());
                    playlist.setSongsPlaylist(currentUser.getPlaylists().get(i).
                            getSongsPlaylist());
                    // Add the assembled Playlist object to the result list
                    this.result.add(playlist);
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
        return result;
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
     * Gets the result.
     *
     * @return The result.
     */
    public ArrayList<Playlist> getResult() {
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result The result.
     */
    public void setResult(final ArrayList<Playlist> result) {
        this.result = result;
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return "showPlaylists";
    }
}
