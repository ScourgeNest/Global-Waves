package main.Commands.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class GetTop5SongsCommand implements Command {

    private static final int TOP5 = 5;
    private String command = "getTop5Songs";
    private int timestamp;
    private ArrayList<String> result = new ArrayList<String>();

    /**
     * Constructor for the getTop5Songs command.
     *
     * @param timestamp The timestamp.
     */
    public GetTop5SongsCommand(final int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the top 5 songs.
     *
     * @param songs The songs to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        // Retrieve top 5 songs
        for (int i = 0; i < Math.min(TOP5, songs.size()); i++) {
            int maxLikes = -1;
            Song maxSong = null;
            // Find the song with the most likes
            for (Song song : songs) {
                if (song.getLikes() > maxLikes && !result.contains(song.getName())) {
                    maxLikes = song.getLikes();
                    maxSong = song;
                }
            }
            if (maxSong != null) {
                result.add(maxSong.getName());
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
        return "getTop5Songs";
    }
}
