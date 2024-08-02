package main.Commands.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class ShowPreferredSongsCommand implements Command {
    private String command = "showPreferredSongs";
    private String user;
    private int timestamp;
    private ArrayList<String> result;

    /**
     * Constructor for the showPreferredSongs command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public ShowPreferredSongsCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
        this.result = new ArrayList<String>();
    }

    /**
     * Shows the preferred songs.
     *
     * @param users The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        // Iterate through the list of users
        for (User currentUser : users) {

            // Find the user by username
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getPreferredSongs().isEmpty()) {
                    return;
                }
                // Loop through the user's preferred songs and add them to the result list
                for (int i = 0; i < currentUser.getPreferredSongs().size(); i++) {
                    this.result.add(currentUser.getPreferredSongs().get(i).getName());
                }
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
    @Override
    public Object getResults() {
        return result;
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
    @JsonIgnore
    public String getCommandName() {
        return "showPrefferedSongs";
    }
}
