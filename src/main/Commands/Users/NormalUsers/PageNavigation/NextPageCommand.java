package main.Commands.Users.NormalUsers.PageNavigation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class NextPageCommand implements Command {
    private String command = "nextPage";
    private String user;
    private int timestamp;
    private String message;

    /**
     * Constructor for the nextPage command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public NextPageCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Gets the command.
     *
     * @return The command.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Gets the user.
     *
     * @return The user.
     */
    public String getUser() {
        return this.user;
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
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                Page page = new Page(currentUser.getCurrentPage(), currentUser.getPageOwner());
                currentUser.getPreviousPages().add(page);

                if (currentUser.getNextPages().isEmpty()) {
                    this.message = "There are no pages left to go forward.";
                    return;
                }
                currentUser.setCurrentPage(currentUser.getNextPages().get(0).getName());
                currentUser.setPageOwner(currentUser.getNextPages().get(0).getOwner());
                currentUser.getNextPages().remove(0);

                this.message = "The user " + this.user
                        + " has navigated successfully to the next page.";
            } else {
                this.message = this.user + " is trying to access a non-existent page.";
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
        return null;
    }

    /**
     * Gets the timestamp.
     *
     * @return The timestamp.
     */
    public int getTimestamp() {
        return this.timestamp;
    }

    /**
     * Gets the message.
     *
     * @return The message.
     */
    public String getMessage() {
        return this.message;
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
