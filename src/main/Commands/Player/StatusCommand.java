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
import java.util.Map;

public class StatusCommand implements Command {
    private String command;
    private String user;
    private int timestamp;
    private Map<String, Object> stats = new LinkedHashMap<>();

    /**
     * Gets the status of the currently playing song, podcast or playlist.
     *
     * @param users The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            // Find the user by username
            if (currentUser.getUsername().equals(this.user)) {
                // Set command as "status"
                this.command = "status";
                // Collect user's playback status information
                this.stats.put("name", currentUser.getCurrentlyPlaying());
                this.stats.put("remainedTime", currentUser.getTimeRemaining());
                this.stats.put("repeat", currentUser.getRepeat());
                this.stats.put("shuffle", currentUser.isShuffle());
                this.stats.put("paused", currentUser.isPaused());
            }
        }
    }

    /**
     * Constructor for the status command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public StatusCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
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
     * Gets the stats.
     *
     * @return The stats.
     */
    public Map<String, Object> getStats() {
        return stats;
    }

    /**
     * Sets the stats.
     *
     * @param stats The stats.
     */
    public void setStats(final Map<String, Object> stats) {
        this.stats = stats;
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return "status";
    }
}
