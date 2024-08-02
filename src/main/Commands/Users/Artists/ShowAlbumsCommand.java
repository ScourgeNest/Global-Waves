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

public class ShowAlbumsCommand implements Command {

    private String command = "showAlbums";
    private String user;
    private int timestamp;

    private ArrayList<Album> result;

    /**
     * Constructor for the showAlbums command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public ShowAlbumsCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
        this.result = new ArrayList<>();
    }

    /**
     * Shows the albums of an artist.
     *
     * @param artists The artists to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                this.result.addAll(currentArtist.getAlbums());
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
     * Gets the result.
     *
     * @return The result.
     */
    public ArrayList<Album> getResult() {
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result The result.
     */
    public void setResult(final ArrayList<Album> result) {
        this.result = result;
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @Override
    @JsonIgnore
    public String getCommandName() {
        return null;
    }
}
