package main.Commands.Users.Artists;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Album;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class AddAlbumCommand implements Command {
    private String command = "addAlbum";
    private String user;
    private int timestamp;
    private String message;
    private String name;
    private int releaseYear;
    private String description;
    private ArrayList<Song> songs;

    /**
     * Constructor for the addAlbum command.
     *
     * @param user        The user.
     * @param timestamp   The timestamp.
     * @param name        The name of the album.
     * @param releaseYear The release year of the album.
     * @param description The description of the album.
     * @param songs       The songs of the album.
     */
    public AddAlbumCommand(final String user, final int timestamp, final String name,
            final int releaseYear, final String description,
            final ArrayList<Song> songs) {
        this.user = user;
        this.timestamp = timestamp;
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.songs = songs;
    }

    /**
     * Adds an album.
     *
     * @param artists The artists to execute the command on.
     * @param hosts   The hosts to execute the command on.
     * @param users   The users to execute the command on.
     * @param songArrayList   The songs to execute the command on.
     */
    public void execute(final ArrayList<Song> songArrayList, final ArrayList<Podcast> podcasts,
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
        this.message = "The username " + this.user + " doesn't exist.";

        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                for (Album currentAlbum : currentArtist.getAlbums()) {
                    if (currentAlbum.getName().equals(this.name)) {
                        this.message = this.user + " has another album with the same name.";
                        return;
                    }
                }
            }
        }
        for (Song currentSong : this.songs) {
            for (Song currentSong2 : this.songs) {
                if (currentSong.getName().equals(currentSong2.getName())
                        && currentSong != currentSong2) {
                    this.message = this.user + " has the same song at least twice in this album.";
                    return;
                }
            }
        }

        songArrayList.addAll(this.songs);
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                currentArtist.addAlbum(this.name, this.releaseYear, this.description,
                                        this.user, this.songs);
                this.message = this.user + " has added new album successfully.";
                return;
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
     * Gets the name of the album.
     *
     * @return The name of the album.
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the album.
     *
     * @param name The name of the album.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the release year of the album.
     *
     * @return The release year of the album.
     */
    @JsonIgnore
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year of the album.
     *
     * @param releaseYear The release year of the album.
     */
    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the description of the album.
     *
     * @return The description of the album.
     */
    @JsonIgnore
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the album.
     *
     * @param description The description of the album.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the songs of the album.
     *
     * @return The songs of the album.
     */
    @JsonIgnore
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the songs of the album.
     *
     * @param songs The songs of the album.
     */
    public void setSongs(final ArrayList<Song> songs) {
        this.songs = songs;
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return command;
    }
}
