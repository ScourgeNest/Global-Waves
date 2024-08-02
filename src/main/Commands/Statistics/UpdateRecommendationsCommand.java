package main.Commands.Statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;
import java.util.Random;

public class UpdateRecommendationsCommand implements Command {

    private static final int RECOMMENDATION_TIME = 30;
    private String command = "updateRecommendations";
    private String user;
    private int timestamp;
    private String message;
    private String recommendationType;

    /**
     * Constructor for the updateRecommendations command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param recommendationType The recommendation type.
     */
    public UpdateRecommendationsCommand(final String user,
                                        final int timestamp, final String recommendationType) {
        this.user = user;
        this.timestamp = timestamp;
        this.recommendationType = recommendationType;
    }

    /**
     * Executes the updateRecommendations command.
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
                switch (this.recommendationType) {
                    case "random_song" -> {
                        if (currentUser.getDurationCurrentlyPlaying()
                                - currentUser.getTimeRemaining() >= RECOMMENDATION_TIME) {

                            int seed = currentUser.getDurationCurrentlyPlaying()
                                    - currentUser.getTimeRemaining();
                            ArrayList<Song> songsList = new ArrayList<Song>();

                            Random random = new Random(seed);

                            for (Song currentSong : songs) {
                                if (currentSong.getGenre().equals(currentUser.
                                        getCurrentlyPlayingSong().getGenre())) {
                                    songsList.add(currentSong);
                                }
                            }

                            //random song
                            int randomIndex = random.nextInt(songsList.size());
                            Song randomSong = songsList.get(randomIndex);

                            currentUser.setSongRecommendations(randomSong);

                        }
                    }
                    case "random_playlist" -> {
                        currentUser.setPlaylistRecommendations(this.user + "'s recommendations");
                    }
                    case "fans_playlist" -> {
                        for (Artist currentArtist : artists) {
                            if (currentArtist.getName().equals(currentUser.
                                    getCurrentlyPlayingSong().getArtist())) {
                                currentUser.setPlaylistRecommendations(currentArtist.getName()
                                        + " Fan Club recommendations");
                            }
                        }
                    }
                    default -> {
                        this.message = "Invalid recommendation type.";
                        return;
                    }
                }
                this.message = "The recommendations for user " + this.user
                        + " have been updated successfully.";
                return;
            }
        }
        this.message = "The username " + user + " doesn't exist.";
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
     * Gets the timestamp of the command.
     *
     * @return The timestamp of the command.
     */
    @Override
    public int getTimestamp() {
        return this.timestamp;
    }

    /**
     * Gets the user of the command.
     *
     * @return The user of the command.
     */
    @Override
    public String getUser() {
        return this.user;
    }

    /**
     * Gets the results of the command.
     *
     * @return The results of the command.
     */
    @Override @JsonIgnore
    public Object getResults() {
        return this.message;
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
     * Sets the user.
     *
     * @param user The user.
     */
    public void setUser(final String user) {
        this.user = user;
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
     * Gets the recommendation type.
     *
     * @return The recommendation type.
     */
    @JsonIgnore
    public String getRecommendationType() {
        return recommendationType;
    }

    /**
     * Sets the recommendation type.
     *
     * @param recommendationType The recommendation type.
     */
    public void setRecommendationType(final String recommendationType) {
        this.recommendationType = recommendationType;
    }
}
