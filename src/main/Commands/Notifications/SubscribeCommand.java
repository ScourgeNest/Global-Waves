package main.Commands.Notifications;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class SubscribeCommand implements Command {

    private String command = "subscribe";
    private String user;
    private int timestamp;

    private String message;

    /**
     * Constructor for the subscribe command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public SubscribeCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
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
     * Sets the user.
     *
     * @param user The user.
     */
    public void setUser(final String user) {
        this.user = user;
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
     * Executes the subscribe command.
     *
     * @param songs
     * @param podcasts
     * @param users
     * @param artists
     * @param hosts
     */
    @Override
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser: users) {
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getCurrentPage().equals("hostPage")) {
                    for (Host currentHost: hosts) {
                        if (currentHost.getName().equals(currentUser.getPageOwner())) {
                            if (currentHost.getSubscribers().contains(currentUser)) {
                                this.message = this.user + " unsubscribed from "
                                        + currentHost.getName() + " successfully.";
                                currentHost.getSubscribers().remove(currentUser);
                            } else {
                                currentHost.getSubscribers().add(currentUser);
                                this.message = this.user + " subscribed to "
                                        + currentHost.getName() + " successfully.";
                            }
                        }
                    }
                } else if (currentUser.getCurrentPage().equals("artistPage")) {
                    for (Artist currentArtist: artists) {
                        if (currentArtist.getName().equals(currentUser.getPageOwner())) {
                            if (currentArtist.getSubscribers().contains(currentUser)) {
                                this.message = this.user + " unsubscribed from "
                                        + currentArtist.getName() + " successfully.";
                                currentArtist.getSubscribers().remove(currentUser);
                            } else {
                                currentArtist.getSubscribers().add(currentUser);
                                this.message = this.user + " subscribed to "
                                        + currentArtist.getName() + " successfully.";
                            }
                        }
                    }
                } else {
                    this.message = "To subscribe you need to be on the page of an artist or host.";
                }
                return;
            }
        }
        this.message = this.user + "The username " + this.user + " doesn't exist.";
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    @Override @JsonIgnore
    public String getCommandName() {
        return this.command;
    }

    /**
     * Gets the timestamp.
     *
     * @return The timestamp.
     */
    @Override
    public int getTimestamp() {
        return this.timestamp;
    }

    /**
     * Gets the user.
     *
     * @return The user.
     */
    @Override
    public String getUser() {
        return this.user;
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
