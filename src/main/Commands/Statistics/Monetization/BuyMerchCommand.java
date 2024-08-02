package main.Commands.Statistics.Monetization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class BuyMerchCommand implements Command {
    private String command = "buyMerch";
    private String user;
    @JsonIgnore
    private String name;
    private int timestamp;

    private String message;

    /**
     * Constructor for the BuyMerch command.
     *
     * @param user      The user.
     * @param name      The name of the merch.
     * @param timestamp The timestamp.
     */
    public BuyMerchCommand(final String user, final String name, final int timestamp) {
        this.user = user;
        this.name = name;
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
     * Gets the user.
     *
     * @return The user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Gets the results of the command.
     *
     * @return The results of the command.
     */
    @Override @JsonIgnore
    public Object getResults() {
        return null;
    }

    /**
     * Gets the name of the merch.
     *
     * @return The name of the merch.
     */
    public String getName() {
        return name;
    }

    /**
     * Executes the BuyMerch command.
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
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getCurrentPage().equals("artistPage")) {
                    for (Artist currentArtist : artists) {
                        if (currentUser.getPageOwner().equals(currentArtist.getName())) {
                            for (int i = 0; i < currentArtist.getMerch().size(); i++) {
                                if (currentArtist.getMerch().get(i).getName().equals(this.name)) {
                                    this.message = this.user + " has added new merch successfully.";
                                    currentUser.getMerch().add(currentArtist.getMerch().get(i).
                                            getName());
                                    currentArtist.setMerchRevenue(currentArtist.getMerchRevenue()
                                            + currentArtist.getMerch().get(i).getPrice());
                                    return;
                                }
                            }
                            this.message = "The merch " + this.name + " doesn't exist.";
                            return;
                        }
                    }
                } else {
                    this.message = "Cannot buy merch from this page.";
                    return;
                }
            }
        }
        this.message = "The username " + this.user + " doesn't exist.";
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    @Override @JsonIgnore
    public String getCommandName() {
        return null;
    }

    /**
     * Gets the timestamp of the command.
     *
     * @return The timestamp of the command.
     */
    public int getTimestamp() {
        return timestamp;
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
     * Sets the name of the merch.
     *
     * @param name The name of the merch.
     */
    public void setName(final String name) {
        this.name = name;
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
}
