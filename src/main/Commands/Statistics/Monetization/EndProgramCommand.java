package main.Commands.Statistics.Monetization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Album;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static main.Commands.Statistics.Monetization.CancelPremiumCommand.monetizationPhase;

public class EndProgramCommand implements Command {
    private static final double ONE_HUNDRED = 100.0;
    private final String command = "endProgram";
    private LinkedHashMap<String, Object> result;

    /**
     * Constructor for the endProgram command.
     */
    public EndProgramCommand() {
        result = new LinkedHashMap<>();
    }

    /**
     * Gives the revenue to the artists and the songs.
     * and gets the most profitable song for each artist.
     *
     * @param users
     * @param artists
     * @param songs
     * @param podcasts
     * @param hosts
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {

        for (User currentUser : users) {
            if (currentUser.getSubscriptionType().equals("PREMIUM")) {
                monetizationPhase(currentUser, artists);
                currentUser.getListenedSongsPremium().clear();
            }
        }

        for (Artist currentArtist : artists) {
            double songRevenue = currentArtist.getSongRevenue();
            songRevenue = Math.round(songRevenue * ONE_HUNDRED) / ONE_HUNDRED;
            currentArtist.setSongRevenue(songRevenue);
            mostProfitableSong(currentArtist);
        }

        ArrayList<Artist> listenedArtists = new ArrayList<>();
        for (Artist currentArtist : artists) {
            if (!currentArtist.getStats().getListeners().isEmpty()
                    || currentArtist.getMerchRevenue() != 0.0) {
                listenedArtists.add(currentArtist);
            }
        }

        //Get the top artists
        listenedArtists.sort((o1, o2) -> {
            double value1 = o1.getSongRevenue() + o1.getMerchRevenue();
            double value2 = o2.getSongRevenue() + o2.getMerchRevenue();
            if (value1 == value2) {
                return o1.getName().compareTo(o2.getName());
            }
            return Double.compare(value2, value1);
        });

        for (Artist currentArtist : listenedArtists) {
            LinkedHashMap<String, Object> temp = new LinkedHashMap<>();
            temp.put("merchRevenue", currentArtist.getMerchRevenue());
            temp.put("songRevenue", currentArtist.getSongRevenue());
            temp.put("ranking", listenedArtists.indexOf(currentArtist) + 1);
            if (currentArtist.getMostProfitableSong() != null) {
                temp.put("mostProfitableSong", currentArtist.getMostProfitableSong());
            } else {
                temp.put("mostProfitableSong", "N/A");
            }
            result.put(currentArtist.getName(), temp);
        }
    }

    /**
     * Gets the most profitable song for each artist.
     *
     * @param currentArtist
     */
    private void mostProfitableSong(final Artist currentArtist) {
        String mostProfitableSong = null;
        double mostProfitableSongRevenue = 0.0;
        for (Album currentAlbum : currentArtist.getAlbums()) {
            for (Song currentSong : currentAlbum.getSongsPlaylist()) {
                if (currentSong.getRevenue() > mostProfitableSongRevenue) {
                    mostProfitableSongRevenue = currentSong.getRevenue();
                    mostProfitableSong = currentSong.getName();
                }
            }
        }

        if (mostProfitableSong != null) {
            currentArtist.setMostProfitableSong(mostProfitableSong);
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
     * Gets the result.
     *
     * @return The result.
     */
    public LinkedHashMap<String, Object> getResult() {
        return result;
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

    /**
     * Gets the timestamp of the command.
     *
     * @return The timestamp of the command.
     */
    @Override @JsonIgnore
    public int getTimestamp() {
        return -1;
    }

    /**
     * Gets the user of the command.
     *
     * @return The user of the command.
     */
    @Override @JsonIgnore
    public String getUser() {
        return "";
    }

    /**
     * Gets the results of the command.
     *
     * @return The results of the command.
     */
    @Override @JsonIgnore
    public Object getResults() {
        return result;
    }
}
