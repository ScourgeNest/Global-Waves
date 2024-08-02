package main.Commands.Users.NormalUsers.PageNavigation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class PreviousPageCommand implements Command {
    private String command = "previousPage";
    private String user;
    private int timestamp;
    private String message;

    /**
     * Constructor for the previousPage command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public PreviousPageCommand(final String user, final int timestamp) {
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
     * Gets the results.
     *
     * @return The results.
     */
    @Override @JsonIgnore
    public Object getResults() {
        return null;
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
                if (!currentUser.getPreviousPages().isEmpty()) {
                    Page page = new Page(currentUser.getCurrentPage(), currentUser.getPageOwner());
                    currentUser.getNextPages().add(0, page);

                    currentUser.setCurrentPage(currentUser.getPreviousPages().
                            get(currentUser.getPreviousPages().size() - 1).getName());
                    currentUser.setPageOwner(currentUser.getPreviousPages().
                            get(currentUser.getPreviousPages().size() - 1).getOwner());
                    currentUser.getPreviousPages().remove(currentUser.
                            getPreviousPages().size() - 1);
                    this.message = "The user " + this.user
                            + " has navigated successfully to the previous page.";
                } else {
                    this.message = this.user + " is trying to access a non-existent page.";
                }
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
}
