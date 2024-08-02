package main.Commands.Users.NormalUsers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class SwitchConnectionStatusCommand implements Command {
    private String command = "switchConnectionStatus";
    private String user;
    private int timestamp;
    private String message;

    /**
     * Constructor for the switchConnectionStatus command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public SwitchConnectionStatusCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Switches the connection status of a user.
     *
     * @param users   The users to execute the command on.
     * @param artists The artists to execute the command on.
     * @param hosts   The hosts to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getConnectionStatus().equals("online")) {
                    currentUser.setConnectionStatus("offline");
                } else {
                    currentUser.setConnectionStatus("online");
                }
                this.message = currentUser.getUsername() + " has changed status successfully.";
                currentUser.setTimestamp(this.timestamp);
                return;
            }
        }

        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                this.message = this.user + " is not a normal user.";
                return;
            }
        }

        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                this.message = this.user + " is not a normal user.";
                return;
            }
        }

        if (this.message == null) {
            this.message = "The username " + this.user + " doesn't exist.";
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
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    @Override
    public String getCommandName() {
        return null;
    }
}
