package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class ForwardCommand implements Command {

    private static final int FORWARD_TIME = 90;
    private String command = "forward";
    private String user;
    private int timestamp;

    private String message;

    public ForwardCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     *  Fast forwards the currently playing podcast by 90 seconds.
     *
     * @param users    The users to execute the command on.
     * @param podcasts The podcasts to execute the command on.
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
                    this.message = "Please load a source before attempting to forward.";
                    return;
                }
                // Check if the user is playing a podcast
                if (currentUser.getCurrentlyPlayingPodcast() != null) {
                    int indexPodcast = podcasts.indexOf(currentUser.getCurrentlyPlayingPodcast());
                    int timeWatched = currentUser.getDurationCurrentlyPlaying()
                            - currentUser.getTimeRemaining();

                    // Check if forward time exceeds the total duration of the podcast
                    if (timeWatched + FORWARD_TIME > currentUser.getDurationCurrentlyPlaying()) {
                        // Set time remaining to the end of the podcast and update watched time
                        currentUser.setTimeRemaining(currentUser.getDurationCurrentlyPlaying());
                        currentUser.getWatchedTimeEpisode().set(indexPodcast, 0);
                        this.message = "Skipped forward successfully.";
                    } else {
                        // Update time remaining and watched time
                        currentUser.setTimeRemaining(currentUser.getTimeRemaining() - FORWARD_TIME);
                        currentUser.getWatchedTimeEpisode().set(indexPodcast,
                                currentUser.getDurationCurrentlyPlaying()
                                        - currentUser.getTimeRemaining());
                        this.message = "Skipped forward successfully.";
                    }
                } else {
                    this.message = "The loaded source is not a podcast.";
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
        return "forwardBackward";
    }
}
