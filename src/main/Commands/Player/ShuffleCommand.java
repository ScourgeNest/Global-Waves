package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;

public class ShuffleCommand implements Command {
    private String command = "shuffle";
    private String user;
    private int timestamp;
    private int seed;
    private String message;

    /**
     * Constructor for the shuffle command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param seed      The seed.
     */
    public ShuffleCommand(final String user, final int timestamp, final int seed) {
        this.user = user;
        this.timestamp = timestamp;
        this.seed = seed;
    }

    /**
     * Shuffles the currently playing playlist.
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
        for (User currentUser : users) {
            // Find the user by username
            if (currentUser.getUsername().equals(this.user)) {
                // Check if there's a loaded source for the user
                if (currentUser.getCurrentlyPlaying().equals("")) {
                    this.message = "Please load a source before using the shuffle function.";
                    return;
                }
                // Check if the loaded source is a playlist
                if (currentUser.getCurrentlyPlayingPlaylist() == null) {
                    this.message = "The loaded source is not a playlist or an album.";
                    return;
                }
                // If shuffle is currently inactive, activate it
                if (!currentUser.isShuffle()) {
                    currentUser.setShuffle(true);
                    this.message = "Shuffle function activated successfully.";
                    // Populate original indices list with song indices
                    for (int i = 0; i < currentUser.getCurrentlyPlayingPlaylist().
                            getSongs().size(); i++) {
                        currentUser.getOriginalIndices().add(i);
                    }
                    // Copy original indices to shuffled indices and shuffle them based on the seed
                    currentUser.getShuffledIndices().addAll(currentUser.getOriginalIndices());
                    Random rand = new Random(this.seed);
                    Collections.shuffle(currentUser.getShuffledIndices(), rand);

                } else {
                    // If shuffle is active, deactivate it and clear indices
                    currentUser.setShuffle(false);
                    currentUser.getOriginalIndices().clear();
                    currentUser.getShuffledIndices().clear();
                    this.message = "Shuffle function deactivated successfully.";
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
     * Gets the seed.
     *
     * @return The seed.
     */
    @JsonIgnore
    public int getSeed() {
        return seed;
    }

    /**
     * Sets the seed.
     *
     * @param seed The seed.
     */
    public void setSeed(final int seed) {
        this.seed = seed;
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
        return "shuffle";
    }
}
