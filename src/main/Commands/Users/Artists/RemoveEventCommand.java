package main.Commands.Users.Artists;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class RemoveEventCommand implements Command {
    private String command = "removeEvent";
    private String user;
    private int timestamp;
    private String message;
    @JsonIgnore
    private String name;

    /**
     * Constructor for the removeEvent command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param name      The name of the event.
     */
    public RemoveEventCommand(final String user, final int timestamp,
                              final String name) {
        this.user = user;
        this.timestamp = timestamp;
        this.name = name;
    }

    /**
     * Removes an event.
     *
     * @param artists The artists to execute the command on.
     * @param hosts   The hosts to execute the command on.
     * @param users   The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                for (Event currentEvent : currentArtist.getEvents()) {
                    if (currentEvent.getName().equals(this.name)) {
                        currentArtist.getEvents().remove(currentEvent);
                        this.message = this.user + " deleted the event successfully.";
                        return;
                    }
                }
                this.message = this.user + " doesn't have an event with the given name.";
                return;
            }
        }
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                this.message = this.user + " is not an artist.";
                return;
            }
        }
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                this.message = this.user + " is not an artist.";
                return;
            }
        }
        this.message = "The username " + this.user + " doesn't exist.";
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Gets the username.
     *
     * @return The username.
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
     * Gets the name of the event.
     *
     * @return The name of the event.
     */
    public String getName() {
        return name;
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
