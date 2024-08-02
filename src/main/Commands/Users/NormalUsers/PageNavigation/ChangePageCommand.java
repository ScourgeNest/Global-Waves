package main.Commands.Users.NormalUsers.PageNavigation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class ChangePageCommand implements Command {
    private String command = "changePage";
    private String user;
    private int timestamp;
    private String nextPage;
    private String message;

    /**
     * Constructor for the changePage command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param nextPage  The next page.
     */
    public ChangePageCommand(final String user, final int timestamp, final String nextPage) {
        this.user = user;
        this.timestamp = timestamp;
        this.nextPage = nextPage;
    }

    /**
     * Changes the page.
     *
     * @param users The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                currentUser.getNextPages().clear();
                if (this.nextPage.equals("LikedContent")) {
                    Page page = new Page(currentUser.getCurrentPage(), currentUser.getPageOwner());
                    currentUser.getPreviousPages().add(page);

                    currentUser.setCurrentPage("LikedContent");
                    currentUser.setPageOwner("");
                    this.message = this.user + " accessed " + this.nextPage + " successfully.";
                } else if (this.nextPage.equals("Home")) {
                    Page page = new Page(currentUser.getCurrentPage(), currentUser.getPageOwner());
                    currentUser.getPreviousPages().add(page);

                    currentUser.setCurrentPage("Home");
                    currentUser.setPageOwner("");
                    this.message = this.user + " accessed " + this.nextPage + " successfully.";
                } else if (this.nextPage.equals("Artist")) {
                    if (currentUser.getCurrentlyPlayingSong() != null) {
                        for (Artist currentArtist : artists) {
                            if (currentArtist.getName().equals(currentUser.
                                    getCurrentlyPlayingSong().getArtist())) {
                                Page page = new Page(currentUser.getCurrentPage(),
                                        currentUser.getPageOwner());
                                currentUser.getPreviousPages().add(page);

                                currentUser.setCurrentPage("artistPage");
                                currentUser.setPageOwner(currentArtist.getName());
                                this.message = this.user + " accessed Artist successfully.";
                            }
                        }
                    } else {
                        this.message = this.user + " is trying to access a non-existent page.";
                    }
                } else if (this.nextPage.equals("Host")) {
                    if (currentUser.getCurrentlyPlayingPodcast() != null) {
                        for (Host currentHost : hosts) {
                            if (currentHost.getName().equals(currentUser.
                                    getCurrentlyPlayingPodcast().getOwner())) {
                                Page page = new Page(currentUser.getCurrentPage(),
                                        currentUser.getPageOwner());
                                currentUser.getPreviousPages().add(page);

                                currentUser.setCurrentPage("hostPage");
                                currentUser.setPageOwner(currentHost.getName());
                                this.message = this.user + " accessed Host successfully.";
                            }
                        }
                    } else {
                        this.message = this.user + " is trying to access a non-existent page.";
                    }
                } else {
                    this.message = this.user + " is trying to access a non-existent page.";
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
     * Gets the results.
     *
     * @return The results.
     */
    @Override @JsonIgnore
    public Object getResults() {
        return null;
    }

    /**
     * Gets the nextpage.
     *
     * @return The nextpage.
     */
    @JsonIgnore
    public String getNextPage() {
        return nextPage;
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
     * Sets the nextpage.
     *
     * @param nextPage The nextpage.
     */
    public void setNextPage(final String nextPage) {
        this.nextPage = nextPage;
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
        return "changePage";
    }
}
