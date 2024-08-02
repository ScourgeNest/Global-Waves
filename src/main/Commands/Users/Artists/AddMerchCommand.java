package main.Commands.Users.Artists;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class AddMerchCommand implements Command {

    private String command = "addMerch";
    private String user;
    private int timestamp;
    private String name;
    private String description;
    private int price;
    private String message;

    /**
     * Constructor for the addMerch command.
     *
     * @param user        The user.
     * @param timestamp   The timestamp.
     * @param name        The name of the merchandise.
     * @param description The description of the merchandise.
     * @param price       The price of the merchandise.
     */
    public AddMerchCommand(final String user, final int timestamp, final String name,
                           final String description, final int price) {
        this.user = user;
        this.timestamp = timestamp;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Adds merchandise.
     *
     * @param artists The artists to execute the command on.
     * @param hosts   The hosts to execute the command on.
     * @param users   The users to execute the command on.
     * @param songs   The songs to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                for (Merch currentMerch : currentArtist.getMerch()) {
                    if (currentMerch.getName().equals(this.name)) {
                        this.message = this.user + " has merchandise with the same name.";
                        return;
                    }
                }
                if (this.price < 0) {
                    this.message = "Price for merchandise can not be negative.";
                    return;
                }
                currentArtist.addMerch(this.name, this.description, this.price);
                this.message = this.user + " has added new merchandise successfully.";
                return;
            }
        }
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                this.message = this.user + " is not an artist.";
                return;
            }
        }
        this.message = "The username " + this.user + " doesn't exist.";
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
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * Gets the description.
     *
     * @return The description.
     */
    @JsonIgnore
    public String getDescription() {
        return description;
    }

    /**
     * Gets the price.
     *
     * @return The price.
     */
    @JsonIgnore
    public int getPrice() {
        return price;
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @Override
    @JsonIgnore
    public String getCommandName() {
        return "addMerch";
    }
}
