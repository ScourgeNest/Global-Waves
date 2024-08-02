package main.Commands.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Album;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class GetTop5ArtistsCommand implements Command {
    private static final int MAX_ARTISTS = 5;
    private String command = "getTop5Artists";
    private int timestamp;
    private ArrayList<String> result;

    /**
     * Constructor for the getTop5Artists command.
     *
     * @param timestamp The timestamp.
     */
    public GetTop5ArtistsCommand(final int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the top 5 artists.
     *
     * @param artists The artists to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        this.result = new ArrayList<>();

        for (Artist currentArtist : artists) {
            currentArtist.setLikes(0);
            for (Album currentAlbum : currentArtist.getAlbums()) {
                currentArtist.setLikes(currentArtist.getLikes() + currentAlbum.getLikes());
            }
        }

        artists.sort((artist1, artist2) -> {
            if (artist1.getLikes() == artist2.getLikes()) {
                return artist1.getName().compareTo(artist2.getName());
            }
            return artist2.getLikes() - artist1.getLikes();
        });

        int counter = 0;
        for (Artist currentArtist : artists) {
            if (counter == MAX_ARTISTS) {
                break;
            }
            this.result.add(currentArtist.getName());
            counter++;
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
    @Override @JsonIgnore
    public String getCommandName() {
        return this.command;
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
}
