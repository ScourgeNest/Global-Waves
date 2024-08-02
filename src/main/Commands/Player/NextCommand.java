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

public class NextCommand implements Command {
    private String command = "next";
    private String user;
    private int timestamp;

    private String message;

    /**
     * Constructor for the next command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public NextCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Skips to the next track.
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
        for (User currentuser : users) {
            // Check if the current user matches the specified user
            if (currentuser.getUsername().equals(this.user)) {
                // Check if the user is not currently playing anything
                if (currentuser.getCurrentlyPlaying().isEmpty()) {
                    // Set the message and return
                    this.message = "Please load a source before skipping to the next track.";
                    return;
                } else if (currentuser.getCurrentlyPlayingPodcast() != null) {
                    nextPodcast(users, podcasts, currentuser, artists, hosts);
                } else if (currentuser.getCurrentlyPlayingPlaylist() != null) {
                    nextPlaylist(users, currentuser, artists, hosts);
                } else if (currentuser.getCurrentlyPlayingSong() != null) {
                    nextSong(users, currentuser, artists, hosts);
                }
            }
        }
    }

    /**
     * Skips to the next podcast.
     *
     * @param users    The users to execute the command on.
     * @param podcasts The podcasts to execute the command on.
     */
    private void nextPodcast(final ArrayList<User> users, final ArrayList<Podcast> podcasts,
                             final User currentuser, final ArrayList<Artist> artists,
                             final ArrayList<Host> hosts) {

        if (currentuser.getRepeat().equals("No Repeat")) {
            // Get the index of the currently playing episode
            int indexEpisode = currentuser.getCurrentlyPlayingPodcast().
                    getPodcastEpisodes()
                    .indexOf(currentuser.getCurrentlyPlayingEpisode());
            int indexPodcast = podcasts.indexOf(currentuser.
                    getCurrentlyPlayingPodcast());
            // Check if the currently playing episode is not the last episode
            if (indexEpisode + 1 < currentuser.getCurrentlyPlayingPodcast().
                    getPodcastEpisodes().size()) {
                // Set the currently playing episode to the next episode
                currentuser.setCurrentlyPlayingEpisode(currentuser.
                        getCurrentlyPlayingPodcast().getPodcastEpisodes()
                        .get(indexEpisode + 1));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimestamp(this.timestamp);
                currentuser.getLastEpisode().set(indexPodcast, indexEpisode + 1);
                currentuser.getWatchedTimeEpisode().set(indexPodcast, 0);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + "track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            } else {
                // Stop the Player
                currentuser.setDurationCurrentlyPlaying(0);
                currentuser.setCurrentlyPlayingEpisode(null);
                currentuser.setCurrentlyPlayingPodcast(null);
                currentuser.setCurrentlyPlaying("");
                currentuser.setTimeRemaining(0);
                currentuser.setTimestamp(this.timestamp);
                currentuser.setRepeat("No Repeat");
                currentuser.setShuffle(false);
                currentuser.setPaused(true);
                this.message = "Please load a source before skipping to the next "
                        + "track.";
                return;
            }
        } else if (currentuser.getRepeat().equals("Repeat Once")) {
            // Get the index of the currently playing episode
            int indexEpisode = currentuser.getCurrentlyPlayingPodcast().
                    getPodcastEpisodes()
                    .indexOf(currentuser.getCurrentlyPlayingEpisode());
            int indexPodcast = podcasts.indexOf(currentuser.
                    getCurrentlyPlayingPodcast());
            // Check if the currently playing episode is not the last episode
            if (indexEpisode + 1 < currentuser.getCurrentlyPlayingPodcast().
                    getPodcastEpisodes().size()) {
                // Set the currently playing episode to the next episode
                currentuser.setCurrentlyPlayingEpisode(currentuser.
                        getCurrentlyPlayingPodcast().getPodcastEpisodes()
                        .get(indexEpisode + 1));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimestamp(this.timestamp);
                currentuser.getLastEpisode().set(indexPodcast, indexEpisode + 1);
                currentuser.getWatchedTimeEpisode().set(indexPodcast, 0);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + "track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            } else {
                // Set the currently playing episode to the first episode
                currentuser.setCurrentlyPlayingEpisode(currentuser.
                        getCurrentlyPlayingPodcast().getPodcastEpisodes().get(0));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimestamp(this.timestamp);
                currentuser.getLastEpisode().set(indexPodcast, 0);
                currentuser.getWatchedTimeEpisode().set(indexPodcast, 0);
                currentuser.setRepeat("No Repeat");
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + "track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            }
        } else if (currentuser.getRepeat().equals("Repeat Infinite")) {
            // Get the index of the currently playing episode
            int indexEpisode = currentuser.getCurrentlyPlayingPodcast().getPodcastEpisodes()
                    .indexOf(currentuser.getCurrentlyPlayingEpisode());
            int indexPodcast = podcasts.indexOf(currentuser.
                    getCurrentlyPlayingPodcast());
            // Check if the currently playing episode is not the last episode
            if (indexEpisode + 1 < currentuser.getCurrentlyPlayingPodcast().
                    getPodcastEpisodes().size()) {
                // Set the currently playing episode to the next episode
                currentuser.setCurrentlyPlayingEpisode(currentuser.
                        getCurrentlyPlayingPodcast().getPodcastEpisodes()
                        .get(indexEpisode + 1));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimestamp(this.timestamp);
                currentuser.getLastEpisode().set(indexPodcast, indexEpisode + 1);
                currentuser.getWatchedTimeEpisode().set(indexPodcast, 0);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current"
                        + " track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            } else {
                // Set the currently playing episode to the first episode
                currentuser.setCurrentlyPlayingEpisode(currentuser.
                        getCurrentlyPlayingPodcast().getPodcastEpisodes().get(0));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingEpisode().getDuration());
                currentuser.setTimestamp(this.timestamp);
                currentuser.getLastEpisode().set(indexPodcast, 0);
                currentuser.getWatchedTimeEpisode().set(indexPodcast, 0);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + " track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            }
        }
    }

    /**
     * Skips to the next playlist.
     *
     * @param users    The users to execute the command on.
     * @param currentuser The current user.
     */
    private void nextPlaylist(final ArrayList<User> users, final User currentuser,
                              final ArrayList<Artist> artists, final ArrayList<Host> hosts) {
        if (!currentuser.isShuffle()) {
            shuffleOff(currentuser, artists, hosts);
        } else {
            shuffleOn(currentuser, artists, hosts);
        }
    }

    private void shuffleOff(final User currentuser, final ArrayList<Artist> artists,
                            final ArrayList<Host> hosts) {
        // Check if the user is not on repeat
        if (currentuser.getRepeat().equals("No Repeat")) {
            // Get the index of the currently playing song
            int indexSong = currentuser.getCurrentlyPlayingPlaylist().
                    getSongsPlaylist()
                    .indexOf(currentuser.getCurrentlyPlayingSong());
            // Check if the currently playing song is not the last song
            if (indexSong + 1 < currentuser.getCurrentlyPlayingPlaylist().
                    getSongsPlaylist().size()) {
                // Set the currently playing song to the next song
                currentuser.setCurrentlyPlayingSong(currentuser.
                        getCurrentlyPlayingPlaylist().getSongsPlaylist()
                        .get(indexSong + 1));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimestamp(this.timestamp);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + "track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            } else {
                // Stop the Player
                currentuser.setDurationCurrentlyPlaying(0);
                currentuser.setCurrentlyPlayingSong(null);
                currentuser.setCurrentlyPlayingPlaylist(null);
                currentuser.setCurrentlyPlaying("");
                currentuser.setTimeRemaining(0);
                currentuser.setTimestamp(this.timestamp);
                currentuser.setRepeat("No Repeat");
                currentuser.setShuffle(false);
                currentuser.setPaused(true);
                this.message = "Please load a source before skipping to the next "
                        + "track.";
                Update_Listens(currentuser, artists, hosts);
                return;
            }
            // Check if the user is on repeat all
        } else if (currentuser.getRepeat().equals("Repeat All")) {
            // Get the index of the currently playing song
            int indexSong = currentuser.getCurrentlyPlayingPlaylist().
                    getSongsPlaylist()
                    .indexOf(currentuser.getCurrentlyPlayingSong());
            // Check if the currently playing song is not the last song
            if (indexSong + 1 < currentuser.getCurrentlyPlayingPlaylist().
                    getSongsPlaylist().size()) {
                // Set the currently playing song to the next song
                currentuser.setCurrentlyPlayingSong(currentuser.
                        getCurrentlyPlayingPlaylist().getSongsPlaylist()
                        .get(indexSong + 1));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimestamp(this.timestamp);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + "track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            } else {
                // Set the currently playing song to the first song
                currentuser.setCurrentlyPlayingSong(currentuser.
                        getCurrentlyPlayingPlaylist().getSongsPlaylist().get(0));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimestamp(this.timestamp);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + "track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            }
        } else if (currentuser.getRepeat().equals("Repeat Current Song")) {
            // Set the time remaining to the duration of the currently playing song
            currentuser.setTimeRemaining(currentuser.getCurrentlyPlayingSong().
                    getDuration());
            currentuser.setTimestamp(timestamp);
            currentuser.setPaused(false);
            this.message = "Skipped to next track successfully. The current "
                    + "track is " + currentuser.getCurrentlyPlaying() + ".";
            Update_Listens(currentuser, artists, hosts);
        }
    }

    private void shuffleOn(final User currentuser, final ArrayList<Artist> artists,
                           final ArrayList<Host> hosts) {
        // Check if the user is not on repeat
        if (currentuser.getRepeat().equals("No Repeat")) {
            // Get the index of the currently playing song
            int indexSong = currentuser.getShuffledIndices().indexOf(currentuser.
                    getCurrentlyPlayingPlaylist()
                    .getSongsPlaylist().indexOf(currentuser.
                            getCurrentlyPlayingSong()));
            // Check if the currently playing song is not the last song
            if (indexSong + 1 < currentuser.getCurrentlyPlayingPlaylist().
                    getSongsPlaylist().size()) {
                // Set the currently playing song to the next song
                currentuser.setCurrentlyPlayingSong(currentuser.
                        getCurrentlyPlayingPlaylist().getSongsPlaylist()
                        .get(currentuser.getShuffledIndices().
                                get(indexSong + 1)));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimestamp(timestamp);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + "track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            } else {
                // Stop the Player
                currentuser.setDurationCurrentlyPlaying(0);
                currentuser.setCurrentlyPlayingSong(null);
                currentuser.setCurrentlyPlayingPlaylist(null);
                currentuser.setCurrentlyPlaying("");
                currentuser.setTimeRemaining(0);
                currentuser.setRepeat("No Repeat");
                currentuser.setShuffle(false);
                currentuser.setTimestamp(timestamp);
                currentuser.setPaused(true);
                this.message = "Please load a source before skipping to the next "
                        + "track.";
                return;
            }
        } else if (currentuser.getRepeat().equals("Repeat All")) {
            // Get the index of the currently playing song
            int indexSong = currentuser.getShuffledIndices().
                    indexOf(currentuser.getCurrentlyPlayingPlaylist()
                            .getSongsPlaylist().indexOf(currentuser.
                                    getCurrentlyPlayingSong()));
            if (indexSong + 1 < currentuser.getCurrentlyPlayingPlaylist().
                    getSongsPlaylist().size()) {
                currentuser.setCurrentlyPlayingSong(currentuser.
                        getCurrentlyPlayingPlaylist().getSongsPlaylist()
                        .get(currentuser.getShuffledIndices().
                                get(indexSong + 1)));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimestamp(timestamp);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + "track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            } else {
                // Set the currently playing song to the first song
                currentuser.setCurrentlyPlayingSong(currentuser.
                        getCurrentlyPlayingPlaylist().getSongsPlaylist().get(0));
                currentuser.setCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getName());
                currentuser.setDurationCurrentlyPlaying(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimeRemaining(currentuser.
                        getCurrentlyPlayingSong().getDuration());
                currentuser.setTimestamp(timestamp);
                currentuser.setPaused(false);
                this.message = "Skipped to next track successfully. The current "
                        + "track is " + currentuser.getCurrentlyPlaying() + ".";
                Update_Listens(currentuser, artists, hosts);
            }
        } else if (currentuser.getRepeat().equals("Repeat Current Song")) {
            // Set the time remaining to the duration of the currently playing song
            currentuser.setTimeRemaining(currentuser.getCurrentlyPlayingSong().
                    getDuration());
            currentuser.setTimestamp(timestamp);
            currentuser.setPaused(false);
            this.message = "Skipped to next track successfully. The current "
                    + "track is " + currentuser.getCurrentlyPlaying() + ".";
            Update_Listens(currentuser, artists, hosts);
        }
    }

    /**
     * Skips to the next song.
     *
     * @param users    The users to execute the command on.
     * @param currentuser The current user.
     */
    private void nextSong(final ArrayList<User> users, final User currentuser,
                          final ArrayList<Artist> artists, final ArrayList<Host> hosts) {
        // Check if the user is not on repeat
        if (currentuser.getRepeat().equals("No Repeat")) {
            this.message = "Please load a source before skipping to the next "
                    + "track.";
        } else if (currentuser.getRepeat().equals("Repeat Once")) {
            // Set the time remaining to the duration of the currently playing song
            currentuser.setCurrentlyPlaying(currentuser.getCurrentlyPlayingSong().
                    getName());
            currentuser.setDurationCurrentlyPlaying(currentuser.
                    getCurrentlyPlayingSong().getDuration());
            currentuser.setTimeRemaining(currentuser.
                    getCurrentlyPlayingSong().getDuration());
            currentuser.setTimestamp(timestamp);
            currentuser.setPaused(false);
            currentuser.setRepeat("No Repeat");
            this.message = "Skipped to next track successfully. The current "
                    + "track is " + currentuser.getCurrentlyPlaying() + ".";
            Update_Listens(currentuser, artists, hosts);
        } else if (currentuser.getRepeat().equals("Repeat Infinite")) {
            // Set the time remaining to the duration of the currently playing song
            currentuser.setCurrentlyPlaying(currentuser.
                    getCurrentlyPlayingSong().getName());
            currentuser.setDurationCurrentlyPlaying(currentuser.
                    getCurrentlyPlayingSong().getDuration());
            currentuser.setTimeRemaining(currentuser.
                    getCurrentlyPlayingSong().getDuration());
            currentuser.setTimestamp(timestamp);
            currentuser.setPaused(false);
            this.message = "Skipped to next track successfully. The current "
                    + "track is " + currentuser.getCurrentlyPlaying() + ".";
            Update_Listens(currentuser, artists, hosts);
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
        return "next";
    }
}
