package main.Commands.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class LoadRecommendationsCommand implements Command {
    private String command = "loadRecommendations";
    private String user;
    private int timestamp;
    private String message;

    /**
     * Constructor for the loadRecommendations command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public LoadRecommendationsCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    @Override @JsonIgnore
    public String getCommandName() {
        return null;
    }

    /**
     * Gets the timestamp of the command.
     *
     * @return The timestamp of the command.
     */
    @Override
    public int getTimestamp() {
        return this.timestamp;
    }

    /**
     * Gets the user of the command.
     *
     * @return The user of the command.
     */
    @Override
    public String getUser() {
        return this.user;
    }

    /**
     * Gets the results of the command.
     *
     * @return The results of the command.
     */
    @Override @JsonIgnore
    public Object getResults() {
        return null;
    }

    /**
     * Executes the loadRecommendations command.
     *
     * @param songs
     * @param podcasts
     * @param users
     * @param artists
     * @param hosts
     */
    @Override
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                setSong(currentUser, currentUser.getSongRecommendations());
                this.message = "Playback loaded successfully.";
                return;
            }
        }
        this.message = "The username " + user + " doesn't exist.";
    }

    /**
     * Sets the song.
     *
     * @param currentUser
     * @param song
     */
    private void setSong(final User currentUser, final Song song) {
        if (song == null) {
            return;
        }
        currentUser.setCurrentlyPlayingSong(song);
        currentUser.setCurrentlyPlayingPodcast(null);
        currentUser.setCurrentlyPlayingEpisode(null);
        currentUser.setCurrentlyPlayingPlaylist(null);
        currentUser.setRepeat("No Repeat");
        currentUser.setDurationCurrentlyPlaying(song.getDuration());
        currentUser.setCurrentlyPlaying(song.getName());
        currentUser.setPaused(false);
        currentUser.setTimeRemaining(song.getDuration());
        currentUser.setTimestamp(this.timestamp);
        currentUser.setLastSelectCommand(null);
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
     * Sets the user.
     *
     * @param user The user.
     */
    public void setUser(final String user) {
        this.user = user;
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
}
