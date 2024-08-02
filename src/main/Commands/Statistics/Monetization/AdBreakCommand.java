package main.Commands.Statistics.Monetization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class AdBreakCommand implements Command {

    private static final int AD_DURATION = 10;
    private static final int AD_RELEASEYEAR = 2023;
    private String command = "adBreak";
    private String user;
    private int timestamp;
    private String message;
    private double price;
    private Song adSong;

    /**
     * Constructor for the adBreak command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param price     The price.
     */
    public AdBreakCommand(final String user, final int timestamp, final double price) {
        this.user = user;
        this.timestamp = timestamp;
        this.price = price;
        setAdSong();
    }

    /**
     * Sets the ad song.
     */
    private void setAdSong() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("#ad");
        tags.add("#globalwaves");
        tags.add("#premium");

        adSong = new Song("Ad Break", AD_DURATION, "Buy Premium", tags,
                "Buy GlobalWaves Premium, please, I beg you", "advertisement",
                AD_RELEASEYEAR, "GlobalWaves");
    }
    /**
     * Executes the command.
     *
     * @param songs    The songs to execute the command on.
     * @param podcasts The podcasts to execute the command on.
     * @param users    The users to execute the command on.
     * @param artists  The artists to execute the command on.
     * @param hosts    The hosts to execute the command on.
     */

    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getCurrentlyPlayingSong() == null) {
                    this.message = this.user + " is not playing any music.";
                    return;
                }
                if (currentUser.getCurrentlyPlaying().isEmpty()) {
                    this.message = this.user + " is not playing any music.";
                    return;
                }
                if (currentUser.getSubscriptionType().equals("PREMIUM")) {
                    this.message = this.user + " is a premium user.";
                    return;
                }
                currentUser.setCurrentlyPlayingSong(this.adSong);
                currentUser.setCurrentlyPlayingPodcast(null);
                currentUser.setCurrentlyPlayingEpisode(null);
                currentUser.setCurrentlyPlayingPlaylist(null);
                currentUser.setRepeat("No Repeat");
                currentUser.setDurationCurrentlyPlaying(this.adSong.getDuration());
                currentUser.setCurrentlyPlaying(this.adSong.getName());
                currentUser.setPaused(false);
                currentUser.setTimeRemaining(this.adSong.getDuration());
                currentUser.setTimestamp(this.timestamp);
                currentUser.setLastSelectCommand(null);
                monetizationFree(currentUser, artists);
                currentUser.getListenedSongsFree().clear();
                this.message = "Ad inserted successfully.";
                return;
            }
        }
        this.message = "The username " + this.user + " doesn't exist.";
    }

    /**
     * Monetization for free users.
     *
     * @param currentUser
     * @param artists
     */
    private void monetizationFree(final User currentUser, final ArrayList<Artist> artists) {
        if (currentUser.getSubscriptionType().equals("FREE")) {
            double totalSongs = currentUser.getListenedSongsFree().size();
            for (Song song : currentUser.getListenedSongsFree()) {
                double value = price / totalSongs;
                song.setRevenue(song.getRevenue() + value);
            }

            for (Artist artist : artists) {
                int artistSongs = 0;
                for (Song song : currentUser.getListenedSongsFree()) {
                    if (song.getArtist().equals(artist.getName())) {
                        artistSongs++;
                    }
                }
                if (artistSongs != 0) {
                    double value = (price / totalSongs) * artistSongs;
                    artist.setSongRevenue(artist.getSongRevenue() + value);
                }
            }
        }
    }

    /**
     * Gets the ad song.
     *
     * @return The ad song.
     */
    @JsonIgnore
    public Song getAdSong() {
        return adSong;
    }

    /**
     * Sets the command.
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
     * Gets the price.
     *
     * @return The price.
     */
    @JsonIgnore
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price The price.
     */
    public void setPrice(final double price) {
        this.price = price;
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
