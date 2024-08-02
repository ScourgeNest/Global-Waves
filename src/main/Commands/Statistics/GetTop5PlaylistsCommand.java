package main.Commands.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Playlist;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;
import java.util.Collections;

public class GetTop5PlaylistsCommand implements Command {

    private static final int TOP5 = 5;
    private String command = "getTop5Playlists";
    private int timestamp;
    private ArrayList<String> result = new ArrayList<String>();

    /**
     * Constructor for the getTop5Playlists command.
     *
     * @param timestamp The timestamp.
     */
    public GetTop5PlaylistsCommand(final int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the top 5 playlists.
     *
     * @param users The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        // Collect all playlists from all users
        ArrayList<Playlist> allPlaylists = new ArrayList<Playlist>();
        for (User user : users) {
            for (Playlist playlist : user.getPlaylists()) {
                allPlaylists.add(playlist);
            }
        }

        // Sort playlists by followers count and createdAt timestamp
        Collections.sort(allPlaylists, (p1, p2) -> {
            int compareByFollowers = Integer.compare(p2.getFollowers(), p1.getFollowers());
            if (compareByFollowers != 0) {
                return compareByFollowers;
            }
            return Long.compare(p1.getCreatedAt(), p2.getCreatedAt());
        });
        // Retrieve top 5 playlists
        int count = Math.min(TOP5, allPlaylists.size());
        for (int i = 0; i < count; i++) {
            this.result.add(allPlaylists.get(i).getName());
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
     * Gets the timestamp.
     *
     * @return The timestamp.
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the user.
     *
     * @return The user.
     */
    @Override
    public String getUser() {
        return "null";
    }

    /**
     * Gets the results.
     *
     * @return The results.
     */
    @Override
    public Object getResults() {
        return result;
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
     * Gets the result.
     *
     * @return The result.
     */
    public ArrayList<String> getResult() {
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result The result.
     */
    public void setResult(final ArrayList<String> result) {
        this.result = result;
    }
    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return "getTop5Playlists";
    }
}
