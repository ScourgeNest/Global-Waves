package main.Commands.Users.Artists;

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

public class RemoveAlbumCommand implements Command {
    private String command = "removeAlbum";
    private String user;
    private int timestamp;
    @JsonIgnore
    private String albumName;
    private String message;

    /**
     * Constructor for the removeAlbum command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param albumName The name of the album.
     */
    public RemoveAlbumCommand(final String user, final int timestamp, final String albumName) {
        this.user = user;
        this.timestamp = timestamp;
        this.albumName = albumName;
        this.message = null;
    }

    /**
     * Removes an album.
     *
     * @param users   The users to execute the command on.
     * @param artists The artists to execute the command on.
     * @param hosts   The hosts to execute the command on.
     * @param songs   The songs to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {

        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                this.message = this.user + " is not an artist.";
                return;
            }
        }
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                this.message = this.user + " is not an artist.";
                return;
            }
        }
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                this.message = this.user + " is an artist.";
            }
        }
        if (this.message == null) {
            this.message = "The username " + this.user + " doesn't exist.";
            return;
        }
        Album deleteAlbum = null;
        Artist artistOfAlbum = null;
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                artistOfAlbum = currentArtist;
                for (Album currentAlbum : currentArtist.getAlbums()) {
                    if (currentAlbum.getName().equals(this.albumName)) {
                        deleteAlbum = currentAlbum;
                        break;
                    }
                }
                this.message = this.user + " doesn't have an album with the given name.";
            }
            if (deleteAlbum != null) {
                break;
            }
        }
        if (deleteAlbum == null) {
            return;
        }
        for (User currentUser : users) {
            if (currentUser.getCurrentlyPlayingSong() == null) {
                continue;
            }
            if (currentUser.getCurrentlyPlayingPlaylist() == null) {
                continue;
            }
            if (currentUser.getCurrentlyPlayingPlaylist().getName().
                    equals(deleteAlbum.getName())) {
                this.message = this.user + " can't delete this album.";
                return;
            }
            for (Song currentSong : deleteAlbum.getSongsPlaylist()) {
                if (currentUser.getCurrentlyPlayingSong().getName().
                        equals(currentSong.getName())) {
                    this.message = this.user + " can't delete this album.";
                    return;
                }
                if (currentUser.getCurrentlyPlayingPlaylist() != null) {
                    if (currentUser.getCurrentlyPlayingPlaylist().
                            getSongsPlaylist().contains(currentSong)) {
                        this.message = this.user + " can't delete this album.";
                        return;
                    }
                }
            }
        }

        for (User currentUser : users) {
            currentUser.getFollowedPlaylists().remove(deleteAlbum);
            for (Song currentSong : deleteAlbum.getSongsPlaylist()) {
                currentUser.getPreferredSongs().remove(currentSong);
            }
            for (Playlist currentPlaylist : currentUser.getPlaylists()) {
                currentPlaylist.getSongsPlaylist().removeAll(deleteAlbum.getSongsPlaylist());
                // S-ar putea sa trebuiasca sa fac aici pentru shuffle si repeat
            }
        }
        for (Song currentSong : deleteAlbum.getSongsPlaylist()) {
            songs.remove(currentSong);
        }
        artistOfAlbum.getAlbums().remove(deleteAlbum);
        this.message = this.user + " deleted the album successfully.";
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
     * Gets the album name.
     *
     * @return The album name.
     */
    public String getAlbumName() {
        return albumName;
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
     * Gets the name.
     *
     * @return The name.
     */
    @Override
    @JsonIgnore
    public String getCommandName() {
        return "removeAlbum";
    }
}
