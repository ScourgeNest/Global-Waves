package main.Commands.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class AddUserCommand implements Command {
    private final String command = "addUser";
    private final String user;
    private final int timestamp;
    private String message;
    private final String type;
    private final int age;
    private final String city;

    /**
     * Constructor for the addPodcast command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param type      The type of the user.
     * @param age       The age of the user.
     * @param city      The city of the user.
     */
    public AddUserCommand(final String user, final int timestamp, final String type,
                          final int age, final String city) {
        this.user = user;
        this.timestamp = timestamp;
        this.type = type;
        this.age = age;
        this.city = city;
    }

    /**
     * Adds a user.
     *
     * @param users    The users to execute the command on.
     * @param artists  The artists to execute the command on.
     * @param hosts    The hosts to execute the command on.
     * @param podcasts The podcasts to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                this.message = "The username " + this.user + " is already taken.";
                return;
            }
        }

        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                this.message = "The username " + this.user + " is already taken.";
                return;
            }
        }

        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                this.message = "The username " + this.user + " is already taken.";
                return;
            }
        }

        if (this.message == null) {
            switch (this.type) {
                case "user" -> {
                    users.add(new User(this.user, this.age, this.city, podcasts));
                    this.message = "The username " + this.user + " has been added successfully.";
                }
                case "artist" -> {
                    artists.add(new Artist(this.user, this.age, this.city));
                    this.message = "The username " + this.user + " has been added successfully.";
                }
                case "host" -> {
                    hosts.add(new Host(this.user, this.age, this.city));
                    this.message = "The username " + this.user + " has been added successfully.";
                }
                default -> {
                    return;
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
     * Sets the message.
     *
     * @param message The message.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    @JsonIgnore
    public String getCommandName() {
        return null;
    }
}
