package main.Commands.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class GetAllUsersCommand implements Command {
    private String command = "getAllUsers";
    private int timestamp;
    private ArrayList<String> result;

    /**
     * Constructor for the getAllUsers command.
     *
     * @param timestamp The timestamp.
     */
    public GetAllUsersCommand(final int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets all the users.
     *
     * @param users   The users to execute the command on.
     * @param artists The artists to execute the command on.
     * @param hosts   The hosts to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {

        result = new ArrayList<>();
        for (User user : users) {
            result.add(user.getUsername());
        }
        for (Artist artist : artists) {
            result.add(artist.getName());
        }
        for (Host host : hosts) {
            result.add(host.getName());
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
    public ArrayList<String> getResult() {
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result The result.
     */
    public void setResult(final ArrayList<String> result) {
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
        return command;
    }

    /**
     * Gets the user.
     * @return The user.
     */
    @Override
    public String getUser() {
        return "null";
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
