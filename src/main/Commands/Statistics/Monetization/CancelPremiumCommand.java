package main.Commands.Statistics.Monetization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class CancelPremiumCommand implements Command {
    private static final int BANII_PE_BEAT = 1_000_000;
    private final String command = "cancelPremium";
    private String user;
    private int timestamp;
    private String message;

    /**
     * Constructor for the cancelPremium command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public CancelPremiumCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Gives the revenue to the artists and the songs.
     *
     * @param user
     * @param artists
     */
    public static void monetizationPhase(final User user, final ArrayList<Artist> artists) {
        //Posibil trebuie frecata
        int totalSongs = user.getListenedSongsPremium().size();
        for (Song currentSong : user.getListenedSongsPremium()) {
            double value = (double) BANII_PE_BEAT / totalSongs;
            currentSong.setRevenue(currentSong.getRevenue() + value);
        }
        for (Artist currentArtist : artists) {
            int artistSongs = 0;
            for (Song currentSong : user.getListenedSongsPremium()) {
                if (currentSong.getArtist().equals(currentArtist.getName())) {
                    artistSongs++;
                }
            }
            if (artistSongs != 0) {
                double value = ((double) BANII_PE_BEAT / totalSongs) * artistSongs;
                currentArtist.setSongRevenue(currentArtist.getSongRevenue() + value);
            }
        }
    }

    /**
     * Cancels the premium subscription.
     *
     * @param users The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user)) {
                if (currentUser.getSubscriptionType().equals("PREMIUM")) {
                    currentUser.setSubscriptionType("FREE");
                    monetizationPhase(currentUser, artists);
                    currentUser.getListenedSongsPremium().clear();
                    message = user + " cancelled the subscription successfully.";
                } else {
                    message = user + " is not a premium user.";
                }
                return;
            }
        }
        message = "The username " + user + " doesn't exist.";
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
     * Gets the results of the command.
     *
     * @return The results of the command.
     */
    @Override @JsonIgnore
    public Object getResults() {
        return null;
    }

    /**
     * Sets the user of the command.
     *
     * @param user The user of the command.
     */
    public void setUser(final String user) {
        this.user = user;
    }

    /**
     * Gets the timestamp of the command.
     *
     * @return The timestamp of the command.
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the command.
     *
     * @param timestamp The timestamp of the command.
     */
    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the message of the command.
     *
     * @return The message of the command.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the command.
     *
     * @param message The message of the command.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    @Override @JsonIgnore
    public String getCommandName() {
        return command;
    }
}
