package main.Commands.Statistics.Wrapped;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class ResponseWrapped implements Command {

    private final String command = "wrapped";
    private final String user;
    private final int timestamp;

    /**
     * Constructor for the wrapped command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public ResponseWrapped(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Does Nothing.
     */
    @Override
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {

    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    public String getCommandName() {
        return null;
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
     * Gets the timestamp.
     *
     * @return The timestamp.
     */
    @Override
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the user.
     *
     * @return The user.
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * Gets the results.
     *
     * @return The results.
     */
    @Override @JsonIgnore
    public Object getResults() {
        return null;
    }
}
