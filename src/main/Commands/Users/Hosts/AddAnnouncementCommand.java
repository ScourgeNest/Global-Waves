package main.Commands.Users.Hosts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.User;
import main.TypeOfUsers.Host;

import java.util.ArrayList;

public class AddAnnouncementCommand implements Command {
    private String command = "addAnnouncement";
    private String user;
    private int timestamp;
    private String name;
    private String description;
    private String message;

    /**
     * Constructor for the addAnnouncement command.
     *
     * @param user        The user.
     * @param timestamp   The timestamp.
     * @param name        The name of the announcement.
     * @param description The description of the announcement.
     */
    public AddAnnouncementCommand(final String user, final int timestamp, final String name,
                                  final String description) {
        this.user = user;
        this.timestamp = timestamp;
        this.name = name;
        this.description = description;
    }

    /**
     * Adds an announcement.
     *
     * @param hosts   The hosts to execute the command on.
     * @param artists The artists to execute the command on.
     * @param users   The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        Announcement newAnnouncement = new Announcement(this.name, this.user, this.description);
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                this.message = this.user + " is not a host.";
                return;
            }
        }
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                this.message = this.user + " is not a host.";
                return;
            }
        }
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                currentHost.addAnnouncement(newAnnouncement);
            }
        }
        this.message = this.user + " has successfully added new announcement.";
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
     * Gets the name.
     *
     * @return The name.
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name The name.
     */
    public void setName(final String name) {
        this.name = name;
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
     * Sets the description.
     *
     * @param description The description.
     */
    public void setDescription(final String description) {
        this.description = description;
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
    @Override
    @JsonIgnore
    public String getCommandName() {
        return command;
    }
}
