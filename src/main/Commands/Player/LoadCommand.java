package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Commands.SearchBar.SelectCommand;
import main.Media.Album;
import main.Media.Playlist;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

import static main.TypeOfUsers.User.Update_Listens;

public class LoadCommand implements Command {
    private String command = "load";
    private String user;
    private int timestamp;
    private String message;

    /**
     * Constructor for the load command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public LoadCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Loads the currently playing song, podcast or playlist.
     *
     * @param users    The users to execute the command on.
     * @param podcasts The podcasts to execute the command on.
     * @param songs    The songs to execute the command on.
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
        // Temporary variable to store the last select command
        SelectCommand temp = null;

        // Find the last select command for the current user
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                temp = currentUser.getLastSelectCommand();
                break;
            }
        }

        // If a select command was found
        if (temp != null) {

            for (Artist currentArtist : artists) {
                if (currentArtist.getName().equals(this.user)) {
                    this.message = this.user + " is an artist.";
                    return;
                }
            }

            for (Host currentHost : hosts) {
                if (currentHost.getName().equals(this.user)) {
                    this.message = this.user + " is a host.";
                    return;
                }
            }
            this.message = "Playback loaded successfully.";

            // Loop through users again to find the user with matching username
            for (User currentUser : users) {
                if (currentUser.getUsername().equals(this.user)) {
                    // Check if the selected result matches a song
                    if (temp.getType().equals("song")) {
                        for (Song song : songs) {
                            if (song.getName().equals(temp.getSelectedResult())) {
                                // Set playback details for the selected song
                                setSong(currentUser, song);
                                Update_Listens(currentUser, artists, hosts);
                                return; // Exit the method once playback details are set
                            }
                        }
                    }

                    // Check if the selected result matches a podcast
                    if (temp.getType().equals("podcast")) {
                        for (Podcast podcast : podcasts) {
                            if (podcast.getName().equals(temp.getSelectedResult())) {
                                // Set playback details for the selected podcast
                                setPodcast(currentUser, podcast, podcasts);
                                Update_Listens(currentUser, artists, hosts);
                                return; // Exit the method once playback details are set
                            }
                        }
                    }

                    // Check if the selected result matches a playlist
                    if (temp.getType().equals("playlist")) {
                        for (Playlist playlist : currentUser.getPlaylists()) {
                            if (playlist.getName().equals(temp.getSelectedResult())) {
                                // Set playback details for the selected playlist's first song
                                for (Song song : songs) {
                                    if (song == playlist.getSongsPlaylist().get(0)) {
                                        setPlaylist(currentUser, playlist);
                                        Update_Listens(currentUser, artists, hosts);
                                        return; // Exit the method once playback details are set
                                    }
                                }
                            }
                        }
                    }
                    if (temp.getType().equals("album")) {
                        for (Artist artist : artists) {
                            for (Album album : artist.getAlbums()) {
                                if (album.getName().equals(temp.getSelectedResult())) {
                                    setAlbum(currentUser, album);
                                    Update_Listens(currentUser, artists, hosts);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            this.message = "Please select a source before attempting to load.";
        }
    }

    private void setAlbum(final User currentUser, final Album album) {
        currentUser.setCurrentlyPlayingPlaylist(album);
        currentUser.setCurrentlyPlayingSong(album.getSongsPlaylist().get(0));
        currentUser.setCurrentlyPlayingPodcast(null);
        currentUser.setCurrentlyPlayingEpisode(null);
        currentUser.setRepeat("No Repeat");
        currentUser.setDurationCurrentlyPlaying(album.getSongsPlaylist().get(0).getDuration());
        currentUser.setCurrentlyPlaying(album.getSongsPlaylist().get(0).getName());
        currentUser.setPaused(false);
        currentUser.setTimeRemaining(album.getSongsPlaylist().get(0).getDuration());
        currentUser.setTimestamp(this.timestamp);
        currentUser.setLastSelectCommand(null);
    }

    private void setPlaylist(final User currentUser, final Playlist playlist) {
        currentUser.setCurrentlyPlayingPlaylist(playlist);
        currentUser.setCurrentlyPlayingSong(playlist.getSongsPlaylist().get(0));
        currentUser.setCurrentlyPlayingPodcast(null);
        currentUser.setCurrentlyPlayingEpisode(null);
        currentUser.setRepeat("No Repeat");
        currentUser.setDurationCurrentlyPlaying(playlist.getSongsPlaylist().get(0).getDuration());
        currentUser.setCurrentlyPlaying(playlist.getSongsPlaylist().get(0).getName());
        currentUser.setPaused(false);
        currentUser.setTimeRemaining(playlist.getSongsPlaylist().get(0).getDuration());
        currentUser.setTimestamp(this.timestamp);
        currentUser.setLastSelectCommand(null);
    }

    private void setPodcast(final User currentUser, final Podcast podcast,
                            final ArrayList<Podcast> podcasts) {
        int index = podcasts.indexOf(podcast);
        currentUser.setCurrentlyPlayingPodcast(podcast);
        currentUser.setCurrentlyPlayingEpisode(podcast.getPodcastEpisodes().
                get(currentUser.getLastEpisode().get(index)));
        currentUser.setCurrentlyPlayingSong(null);
        currentUser.setCurrentlyPlayingPlaylist(null);
        currentUser.setCurrentlyPlaying(podcast.getPodcastEpisodes().get(currentUser.
                getLastEpisode().get(index)).getName());
        currentUser.setRepeat("No Repeat");
        currentUser.setDurationCurrentlyPlaying(podcast.getPodcastEpisodes().
                get(currentUser.getLastEpisode().get(index)).getDuration());
        currentUser.setPaused(false);
        currentUser.setTimeRemaining(podcast.getPodcastEpisodes().get(currentUser.
                getLastEpisode().get(index)).getDuration() - currentUser.
                getWatchedTimeEpisode().get(index));
        currentUser.setTimestamp(this.timestamp);
        currentUser.setLastSelectCommand(null);
    }

    private void setSong(final User currentUser, final Song song) {
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
     * Gets the last select command.
     *
     * @return The last select command.
     */
    public SelectCommand getLastSelectCommand(final ArrayList<Command> commands) {
        // Initialize the lastSelectCommand as null
        SelectCommand lastSelectCommand = null;

        // Iterate through the commands in reverse order
        for (int i = commands.size() - 1; i >= 0; i--) {
            Command currentCommand = commands.get(i);

            // Check if the current command is a SelectCommand
            if (isSelectCommand(currentCommand)) {
                // If it is, assign it to lastSelectCommand and exit the loop
                lastSelectCommand = (SelectCommand) currentCommand;
                break;
            }
        }
        return lastSelectCommand; // Return the last found SelectCommand, if any
    }


    /**
     * Checks if the command is a select command.
     *
     * @param currentcommand The command to check.
     * @return True if the command is a select command, false otherwise.
     */

    private boolean isSelectCommand(final Command currentcommand) {
        return currentcommand.getCommandName().equals("select");
    }

    /**
     * Gets the command.
     *
     * @return The command.
     */
    @JsonIgnore
    public String getCommandName() {
        return "load";
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
}
