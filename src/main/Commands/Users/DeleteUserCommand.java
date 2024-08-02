package main.Commands.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Album;
import main.Media.Playlist;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class DeleteUserCommand implements Command {

    private String command = "deleteUser";
    private final String user;
    private final int timestamp;
    private String message;

    /**
     * Constructor for the deleteUser command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public DeleteUserCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Deletes a user.
     *
     * @param users    The users to execute the command on.
     * @param artists  The artists to execute the command on.
     * @param hosts    The hosts to execute the command on.
     * @param songs    The songs to execute the command on.
     * @param podcasts The podcasts to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        if (verifyIfUser(users)) {
            return;
        } else if (verifyIfArtist(artists, users, songs)) {
            return;
        } else if (verifyIfHost(hosts, users, podcasts)) {
            return;
        } else {
            this.message = "User doesn't exist.";
        }
    }

    /**
     * Verifies if the user is a normal one and checks if it can be deleted.
     * If it can be deleted, it deletes it.
     *
     * @param users The users to execute the command on.
     * @return true if the user was normal.
     */
    public boolean verifyIfUser(final ArrayList<User> users) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user)) {
                for (User currentUser2 : users) {
                    for (Playlist currentPlaylist : currentUser.getPlaylists()) {
                        if (currentUser2.getCurrentlyPlayingPlaylist() == null) {
                            continue;
                        }
                        if (currentUser2.getCurrentlyPlayingPlaylist().getName().
                                equals(currentPlaylist.getName())) {
                            this.message = this.user + " can't be deleted.";
                            return true;
                        }
                    }
                }
                for (Playlist currentPlaylist : currentUser.getFollowedPlaylists()) {
                    currentPlaylist.setFollowers(currentPlaylist.getFollowers() - 1);
                }
                for (Song currentSong : currentUser.getPreferredSongs()) {
                    currentSong.setLikes(currentSong.getLikes() - 1);
                }

                for (User currentUser2 : users) {
                    for (Playlist currentPlaylist : currentUser.getPlaylists()) {
                        currentUser2.getFollowedPlaylists().remove(currentPlaylist);
                    }
                }

                users.remove(currentUser);
                this.message = this.user + " was successfully deleted.";
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies if the user is an artist and checks if it can be deleted.
     * If it can be deleted, it deletes it.
     *
     * @param artists The artists to execute the command on.
     * @param users   The users to execute the command on.
     * @param songs   The songs to execute the command on.
     * @return true if the user was an artist.
     */
    public boolean verifyIfArtist(final ArrayList<Artist> artists, final ArrayList<User> users,
                                  final ArrayList<Song> songs) {
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(user)) {
                for (User currentUser : users) {
                    for (Album currentAlbum : currentArtist.getAlbums()) {
                        if (currentUser.getCurrentlyPlayingPlaylist() == currentAlbum) {
                            this.message = this.user + " can't be deleted.";
                            return true;
                        }
                        for (Song currentSong : currentAlbum.getSongsPlaylist()) {
                            if (currentUser.getCurrentlyPlayingSong() == currentSong) {
                                this.message = this.user + " can't be deleted.";
                                return true;
                            }
                        }
                    }

                    if (currentUser.getPageOwner().equals(currentArtist.getName())
                            && currentUser.getCurrentPage().equals("artistPage")) {
                        this.message = this.user + " can't be deleted.";
                        return true;
                    }

                    for (Playlist currentPlaylist : currentUser.getPlaylists()) {
                        for (Song currentSong : currentPlaylist.getSongsPlaylist()) {
                            if (currentSong.getArtist().equals(currentArtist.getName())) {
                                this.message = this.user + " can't be deleted.";
                                return true;
                            }
                        }
                    }

                    if (currentUser.getLastSearchCommand() == null) {
                        continue;
                    }
                    for (String currentSearch : currentUser.getLastSearchCommand().getResults()) {
                        if (currentSearch.equals(currentArtist.getName())) {
                            this.message = this.user + " can't be deleted.";
                            return true;
                        }
                    }
                }
                for (Album currentAlbum : currentArtist.getAlbums()) {
                    for (Song currentSong : currentAlbum.getSongsPlaylist()) {
                        for (User currentUser : users) {
                            currentUser.getPreferredSongs().remove(currentSong);
                        }
                        songs.remove(currentSong);
                    }
                }
                // Poate o sa fie nevoie de o modificare pentru merch si events
                artists.remove(currentArtist);
                this.message = this.user + " was successfully deleted.";
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies if the user is a host and checks if it can be deleted.
     * If it can be deleted, it deletes it.
     *
     * @param hosts    The hosts to execute the command on.
     * @param users    The users to execute the command on.
     * @param podcasts The podcasts to execute the command on.
     * @return true if the user was a host.
     */
    public boolean verifyIfHost(final ArrayList<Host> hosts, final ArrayList<User> users,
                                final ArrayList<Podcast> podcasts) {
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(user)) {
                for (User currentUser : users) {
                    for (Podcast currentPodcast : currentHost.getPodcasts()) {
                        if (currentUser.getCurrentlyPlayingPodcast() == currentPodcast) {
                            this.message = this.user + " can't be deleted.";
                            return true;
                        }
                    }

                    if (currentUser.getPageOwner().equals(currentHost.getName())
                            && currentUser.getCurrentPage().equals("hostPage")) {
                        this.message = this.user + " can't be deleted.";
                        return true;
                    }

                    if (currentUser.getLastSearchCommand() == null) {
                        continue;
                    }
                    for (String currentSearch : currentUser.getLastSearchCommand().getResults()) {
                        if (currentSearch.equals(currentHost.getName())) {
                            this.message = this.user + " can't be deleted.";
                            return true;
                        }
                    }
                }
                for (Podcast currentPodcast : currentHost.getPodcasts()) {
                    for (User currentUser : users) {
                        currentUser.getLastEpisode().remove(currentPodcast.getId());
                        currentUser.getWatchedTimeEpisode().remove(currentPodcast.getId());
                    }
                    for (Podcast currentPodcast2 : podcasts) {
                        if (currentPodcast2.getId() > currentPodcast.getId()) {
                            currentPodcast2.setId(currentPodcast2.getId() - 1);
                        }
                    }
                    podcasts.remove(currentPodcast);
                }
                hosts.remove(currentHost);
                this.message = this.user + " was successfully deleted.";
                return true;
            }
        }
        return false;
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
    @Override
    public String getCommandName() {
        return "deleteUser";
    }
}
