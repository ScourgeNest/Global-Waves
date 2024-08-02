package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class PlayPauseCommand implements Command {
    private String command = "playPause";
    private String user;

    private int timestamp;
    private String message;

    /**
     * Constructor for the playPause command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public PlayPauseCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Pauses or resumes the currently playing song.
     *
     * @param users    The users to execute the command on.
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
                // Check if a source is loaded for the user
                if (currentUser.getCurrentlyPlaying().isEmpty()) {
                    this.message = "Please load a source before attempting to pause or resume "
                            + "playback.";
                    return;
                }

                // If playback is paused, resume it
                if (currentUser.isPaused()) {
                    currentUser.setPaused(false);
                    currentUser.setTimestamp(this.timestamp);
                    this.message = "Playback resumed successfully.";
                } else {
                    // If playback is ongoing, pause it
                    currentUser.setPaused(true);
                    this.message = "Playback paused successfully.";

                    // Calculate elapsed time and deduct from remaining time
                    currentUser.setTimeRemaining(currentUser.getTimeRemaining()
                            - (this.timestamp - currentUser.getTimestamp()));
                    currentUser.setTimestamp(this.timestamp);
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
        return "playPause";
    }
}
