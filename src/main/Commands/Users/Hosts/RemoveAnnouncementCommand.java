package main.Commands.Users.Hosts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class RemoveAnnouncementCommand implements Command {
    private String command = "removeAnnouncement";
    private String user;
    private int timestamp;
    private String announcementName;
    private String message;

    /**
     * Constructor for the removeAnnouncement command.
     *
     * @param user             The user.
     * @param timestamp        The timestamp.
     * @param announcementName The name of the announcement.
     */
    public RemoveAnnouncementCommand(final String user, final int timestamp,
                                     final String announcementName) {
        this.user = user;
        this.timestamp = timestamp;
        this.announcementName = announcementName;
    }

    /**
     * Removes an announcement.
     *
     * @param hosts The hosts to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                for (Announcement currentAnnouncement : currentHost.getAnnouncements()) {
                    if (currentAnnouncement.getName().equals(this.announcementName)) {
                        currentHost.getAnnouncements().remove(currentAnnouncement);
                        this.message = this.user + " has successfully deleted the announcement.";
                        return;
                    }
                }
                this.message = this.user + " has no announcement with the given name.";
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
     * Gets the name of the announcement.
     *
     * @return The name of the announcement.
     */
    @JsonIgnore
    public String getAnnouncementName() {
        return announcementName;
    }

    /**
     * Sets the name of the announcement.
     *
     * @param announcementName The name of the announcement.
     */
    public void setAnnouncementName(final String announcementName) {
        this.announcementName = announcementName;
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
