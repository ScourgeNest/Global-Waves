package main.Commands.Users.NormalUsers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class GetOnlineUsersCommand implements Command {
    private String command = "getOnlineUsers";
    private int timestamp;
    private ArrayList<String> result;

    /**
     * Constructor for the getOnlineUsers command.
     *
     * @param timestamp The timestamp.
     */
    public GetOnlineUsersCommand(final int timestamp) {
        this.timestamp = timestamp;
        this.result = new ArrayList<>();
    }

    /**
     * Gets the online users.
     *
     * @param users The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getConnectionStatus().equals("online")) {
                this.result.add(currentUser.getUsername());
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
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    @JsonIgnore
    public String getCommandName() {
        return command;
    }

    /**
     * Gets the user.
     *
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
