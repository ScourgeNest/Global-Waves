package main.Commands.Notifications;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GetNotificationsCommand implements Command {
    private final String command = "getNotifications";
    private final String user;
    private final int timestamp;
    private final ArrayList<LinkedHashMap<String, String>> notifications;

    public GetNotificationsCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
        this.notifications = new ArrayList<>();
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
     * Executes the command.
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
                this.notifications.addAll(currentUser.getNotifications());
                currentUser.getNotifications().clear();
                return;
            }
        }
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
     *  Gets the timestamp.
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

    /**
     * Gets the notifications.
     *
     * @return The notifications.
     */
    public ArrayList<LinkedHashMap<String, String>> getNotifications() {
        return notifications;
    }
}
