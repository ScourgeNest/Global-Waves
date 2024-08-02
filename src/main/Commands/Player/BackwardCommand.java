package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BackwardCommand implements Command {

    private static final int BACKWARD_TIME_DEFAULT = 90;
    private final String command = "backward";
    private final String user;
    private final int timestamp;

    private String message;
    private final int backwardTime = BACKWARD_TIME_DEFAULT;

    /**
     *  Rewinds the currently playing podcast by 90 seconds.
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
                if (currentUser.getCurrentlyPlaying().isEmpty()) {
                    this.message = "Please select a source before rewinding.";
                    return;
                }
                // Check if the user is playing a podcast
                if (currentUser.getCurrentlyPlayingPodcast() != null) {
                    // Calculate time watched
                    int timeWatched = currentUser.getDurationCurrentlyPlaying()
                            - currentUser.getTimeRemaining();
                    int indexPodcast = podcasts.indexOf(currentUser.getCurrentlyPlayingPodcast());

                    // Check if rewinding exceeds the beginning of the podcast
                    if (timeWatched - this.backwardTime < 0) {
                        // Set time remaining to 0 and update watched time
                        currentUser.setTimeRemaining(0);
                        this.message = "Rewound successfully.";
                        currentUser.getWatchedTimeEpisode().set(indexPodcast, timeWatched);
                    } else {
                        // Update time remaining and watched time
                        currentUser.setTimeRemaining(currentUser.getTimeRemaining()
                                + this.backwardTime);
                        currentUser.getWatchedTimeEpisode().set(indexPodcast, timeWatched);
                        this.message = "Rewound successfully.";
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
    public BackwardCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
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
    public LinkedHashMap<String, Object> getResults() {
        return null;
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
        return "backward";
    }
}
