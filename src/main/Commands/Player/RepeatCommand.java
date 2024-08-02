package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class RepeatCommand implements Command {
    private String command = "repeat";
    private String user;
    private int timestamp;
    private String message;

    /**
     * Constructor for the repeat command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public RepeatCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Repeats the currently playing song, podcast or playlist.
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
                // Check if the user is playing a playlist
                if (currentUser.getCurrentlyPlayingPlaylist() != null) {
                    // Adjust repeat mode for playlist
                    if (currentUser.getRepeat().equals("No Repeat")) {
                        currentUser.setRepeat("Repeat All");
                    } else if (currentUser.getRepeat().equals("Repeat All")) {
                        currentUser.setRepeat("Repeat Current Song");
                    } else if (currentUser.getRepeat().equals("Repeat Current Song")) {
                        currentUser.setRepeat("No Repeat");
                    }
                    // Set message based on the changed repeat mode
                    this.message = "Repeat mode changed to " + currentUser.getRepeat().
                            toLowerCase() + ".";
                    return;

                // Check if the currentUser is playing a song
                } else if (currentUser.getCurrentlyPlayingSong() != null) {
                    // Adjust repeat mode for song
                    if (currentUser.getRepeat().equals("No Repeat")) {
                        currentUser.setRepeat("Repeat Once");
                    } else if (currentUser.getRepeat().equals("Repeat Once")) {
                        currentUser.setRepeat("Repeat Infinite");
                    } else if (currentUser.getRepeat().equals("Repeat Infinite")) {
                        currentUser.setRepeat("No Repeat");
                    }
                    // Set message based on the changed repeat mode
                    this.message = "Repeat mode changed to " + currentUser.getRepeat().
                            toLowerCase() + ".";
                    return;

                // Check if the currentUser is playing a podcast
                } else if (currentUser.getCurrentlyPlayingPodcast() != null) {
                    // Adjust repeat mode for podcast
                    if (currentUser.getRepeat().equals("No Repeat")) {
                        currentUser.setRepeat("Repeat Once");
                    } else if (currentUser.getRepeat().equals("Repeat Once")) {
                        currentUser.setRepeat("Repeat Infinite");
                    } else if (currentUser.getRepeat().equals("Repeat Infinite")) {
                        currentUser.setRepeat("No Repeat");
                    }
                    // Set message based on the changed repeat mode
                    this.message = "Repeat mode changed to " + currentUser.getRepeat().
                            toLowerCase() + ".";
                    return;
                } else {
                    // If no source is loaded, prompt to load a source
                    this.message = "Please load a source before setting the repeat status.";
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
        return "repeat";
    }
}
