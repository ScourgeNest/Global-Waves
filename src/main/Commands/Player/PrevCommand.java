package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

import static main.TypeOfUsers.User.Update_Listens;

public class PrevCommand implements Command {
    private String command = "prev";
    private String user;

    private int timestamp;

    private String message;

    /**
     * Constructor for the prev command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public PrevCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Returns to the previous track.
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
                if (currentUser.getCurrentlyPlayingPodcast() != null) {
                    prevPodcast(users, podcasts, currentUser, artists, hosts);
                } else if (currentUser.getCurrentlyPlayingPlaylist() != null) {
                    prevPlaylist(currentUser, artists, hosts);
                } else if (currentUser.getCurrentlyPlayingSong() != null) {
                    prevSong(currentUser, artists, hosts);
                } else {
                    this.message = "Please load a source before returning to the previous"
                            + " track.";
                }
            }
        }
    }

    private void prevPodcast(final ArrayList<User> users, final ArrayList<Podcast> podcasts,
                             final User currentUser, final ArrayList<Artist> artists,
                             final ArrayList<Host> hosts) {
        int indexEpisode = currentUser.getCurrentlyPlayingPodcast().
                getPodcastEpisodes().indexOf(currentUser.getCurrentlyPlayingEpisode());
        int indexPodcast = podcasts.indexOf(currentUser.getCurrentlyPlayingPodcast());
        // Verify if the index of the episode is 0
        if (indexEpisode == 0) {
            // Set the currently playing episode to the beginning of the podcast
            this.message = "Returned to previous track successfully. The current "
                    + "track is " + currentUser.getCurrentlyPlayingEpisode().getName()
                    + ".";
            currentUser.getLastEpisode().set(indexPodcast, indexEpisode);
            currentUser.getWatchedTimeEpisode().set(indexPodcast, 0);
            currentUser.setTimeRemaining(currentUser.getCurrentlyPlayingEpisode().
                    getDuration());
            currentUser.setDurationCurrentlyPlaying(currentUser.
                    getCurrentlyPlayingEpisode().getDuration());
            currentUser.setTimestamp(this.timestamp);
            currentUser.setPaused(false);
            Update_Listens(currentUser, artists, hosts);
        } else if (indexEpisode > 0) {
            // Set the currently playing episode to the previous episode
            if (currentUser.getTimeRemaining() == currentUser.
                    getDurationCurrentlyPlaying()) {
                currentUser.setCurrentlyPlayingEpisode(currentUser.
                        getCurrentlyPlayingPodcast().getPodcastEpisodes()
                        .get(indexEpisode - 1));
                this.message = "Returned to previous track successfully. The current "
                        + "track is " + currentUser.getCurrentlyPlayingEpisode().
                        getName() + ".";
                currentUser.setCurrentlyPlaying(currentUser.
                        getCurrentlyPlayingEpisode().getName());
                currentUser.getLastEpisode().set(indexPodcast, indexEpisode - 1);
                currentUser.getWatchedTimeEpisode().set(indexPodcast, 0);
                currentUser.setTimeRemaining(currentUser.getCurrentlyPlayingEpisode().
                        getDuration());
                currentUser.setDurationCurrentlyPlaying(currentUser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentUser.setTimestamp(this.timestamp);
                currentUser.setPaused(false);
                Update_Listens(currentUser, artists, hosts);
            } else {
                // Set the currently playing episode to the beginning of the episode
                currentUser.setCurrentlyPlayingEpisode(currentUser.
                        getCurrentlyPlayingPodcast().getPodcastEpisodes()
                        .get(indexEpisode));
                this.message = "Returned to previous track successfully. The current "
                        + "track is " + currentUser.getCurrentlyPlayingEpisode().
                        getName() + ".";
                currentUser.setCurrentlyPlaying(currentUser.
                        getCurrentlyPlayingEpisode().getName());
                currentUser.getLastEpisode().set(indexPodcast, indexEpisode);
                currentUser.getWatchedTimeEpisode().set(indexPodcast, 0);
                currentUser.setTimestamp(this.timestamp);
                currentUser.setTimeRemaining(currentUser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentUser.setPaused(false);
                Update_Listens(currentUser, artists, hosts);
            }
        }
    }

    private void prevPlaylist(final User currentUser, final ArrayList<Artist> artists,
                              final ArrayList<Host> hosts) {
        if (!currentUser.isShuffle()) {
            // Get the index of the currently playing song
            int indexSong = currentUser.getCurrentlyPlayingPlaylist().
                    getSongsPlaylist()
                    .indexOf(currentUser.getCurrentlyPlayingSong());

            // Verify if the index of the song is 0
            if (indexSong == 0) {
                // Set the currently playing song to the beginning of the playlist
                this.message = "Returned to previous track successfully. The current"
                        + " track is " + currentUser.getCurrentlyPlayingSong().
                        getName() + ".";
                currentUser.setTimeRemaining(currentUser.getCurrentlyPlayingSong().
                        getDuration());
                currentUser.setTimestamp(this.timestamp);
                currentUser.setPaused(false);
                Update_Listens(currentUser, artists, hosts);
            } else if (indexSong > 0) {
                // Set the currently playing song to the previous song
                if (currentUser.getTimeRemaining() == currentUser.
                        getDurationCurrentlyPlaying()) {
                    currentUser.setCurrentlyPlayingSong(currentUser.
                            getCurrentlyPlayingPlaylist().getSongsPlaylist()
                            .get(indexSong - 1));
                    this.message = "Returned to previous track successfully. The "
                            + "current track is " + currentUser.
                            getCurrentlyPlayingSong().getName() + ".";
                    currentUser.setCurrentlyPlaying(currentUser.
                            getCurrentlyPlayingSong().getName());
                    currentUser.setCurrentlyPlayingSong(currentUser.
                            getCurrentlyPlayingPlaylist().getSongsPlaylist()
                            .get(indexSong - 1));
                    currentUser.setTimestamp(this.timestamp);
                    currentUser.setTimeRemaining(currentUser.getCurrentlyPlayingSong().
                            getDuration());
                    currentUser.setDurationCurrentlyPlaying(currentUser.
                            getCurrentlyPlayingSong().getDuration());
                    currentUser.setPaused(false);
                    Update_Listens(currentUser, artists, hosts);
                } else {
                    // Set the currently playing song to the beginning of the song
                    this.message = "Returned to previous track successfully. The"
                            + " current track is " + currentUser.
                            getCurrentlyPlayingSong().getName() + ".";
                    currentUser.setTimeRemaining(currentUser.
                            getCurrentlyPlayingSong().getDuration());
                    currentUser.setTimestamp(this.timestamp);
                    currentUser.setPaused(false);
                    Update_Listens(currentUser, artists, hosts);
                }
            }
        } else if (currentUser.isShuffle()) {
            // Get the index of the currently playing song
            int indexSong = currentUser.getShuffledIndices().indexOf(
                    currentUser.getCurrentlyPlayingPlaylist().getSongsPlaylist().
                            indexOf(currentUser.getCurrentlyPlayingSong()));
            // Verify if the index of the song is 0
            if (indexSong == 0) {
                // Set the currently playing song to the beginning of the playlist
                this.message = "Returned to previous track successfully. The "
                        + "current track is " + currentUser.
                        getCurrentlyPlayingSong().getName() + ".";
                currentUser.setTimeRemaining(currentUser.getCurrentlyPlayingSong().
                        getDuration());
                currentUser.setTimestamp(this.timestamp);
                currentUser.setPaused(false);
                Update_Listens(currentUser, artists, hosts);
            } else if (indexSong > 0) {
                // Set the currently playing song to the previous song
                if (currentUser.getTimeRemaining() == currentUser.
                        getDurationCurrentlyPlaying()) {
                    currentUser.setCurrentlyPlayingSong(currentUser.
                            getCurrentlyPlayingPlaylist().getSongsPlaylist()
                            .get(currentUser.getShuffledIndices().get(indexSong - 1)));
                    this.message = "Returned to previous track successfully. The "
                            + "current track is " + currentUser.
                            getCurrentlyPlayingSong().getName() + ".";
                    currentUser.setCurrentlyPlaying(currentUser.
                            getCurrentlyPlayingSong().getName());
                    currentUser.setTimestamp(this.timestamp);
                    currentUser.setTimeRemaining(currentUser.
                            getCurrentlyPlayingSong().getDuration());
                    currentUser.setDurationCurrentlyPlaying(currentUser.
                            getCurrentlyPlayingSong().getDuration());
                    currentUser.setPaused(false);
                    Update_Listens(currentUser, artists, hosts);
                } else {
                    // Set the currently playing song to the beginning of the song
                    this.message = "Returned to previous track successfully. The "
                            + "current track is " + currentUser.
                            getCurrentlyPlayingSong().getName() + ".";
                    currentUser.setTimeRemaining(currentUser.
                            getCurrentlyPlayingSong().getDuration());
                    currentUser.setTimestamp(this.timestamp);
                    currentUser.setPaused(false);
                    Update_Listens(currentUser, artists, hosts);
                }
            }
        }
    }

    private void prevSong(final User currentUser, final ArrayList<Artist> artists,
                          final ArrayList<Host> hosts) {
        //Set the currently playing song to the beginning of the song
        currentUser.setTimeRemaining(currentUser.getCurrentlyPlayingSong().
                getDuration());
        currentUser.setTimestamp(this.timestamp);
        currentUser.setPaused(false);
        this.message = "Returned to previous track successfully. The current"
                + " track is " + currentUser.getCurrentlyPlayingSong().
                getName() + ".";
        Update_Listens(currentUser, artists, hosts);
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
        return "prev";
    }
}
