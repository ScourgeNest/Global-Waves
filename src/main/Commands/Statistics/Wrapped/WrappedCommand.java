package main.Commands.Statistics.Wrapped;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


public class WrappedCommand implements Command {
    private static final long MAX_COUNTER = 5;
    private String command = "wrapped";
    private final String user;
    private final int timestamp;
    private LinkedHashMap<String, Object> result = new LinkedHashMap<>();
    private String type;

    private String message;

    /**
     * Constructor for the wrapped command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public WrappedCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Constructor for the wrapped command.
     */
    public WrappedCommand() {
        this.user = "";
        this.timestamp = 0;
    }

    /**
     * Executes the command.
     *
     * @param users The users to execute the command on.
     * @param artists The artists to execute the command on.
     * @param hosts The hosts to execute the command on.
     * @param songs The songs to execute the command on.
     * @param podcasts The podcasts to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        setType(users, artists, hosts);
        verifyUser(users);
        verifyArtist(artists);
        verifyHost(hosts);
        setResultOrMessage();
    }

    /**
     * Sets the result or message.
     */
    private void setResultOrMessage() {
        LinkedHashMap<String, Object> temp = this.getResult();
        switch (this.getType()) {
            case "user" -> {
                boolean allEmpty = temp.containsKey("topArtists")
                        && ((Map<?, ?>) temp.get("topArtists")).isEmpty()
                        && temp.containsKey("topGenres")
                        && ((Map<?, ?>) temp.get("topGenres")).isEmpty()
                        && temp.containsKey("topSongs")
                        && ((Map<?, ?>) temp.get("topSongs")).isEmpty()
                        && temp.containsKey("topAlbums")
                        && ((Map<?, ?>) temp.get("topAlbums")).isEmpty()
                        && temp.containsKey("topEpisodes")
                        && ((Map<?, ?>) temp.get("topEpisodes")).isEmpty();
                if (allEmpty) {
                    this.command = "wrappedmessageuser";
                } else {
                    this.command = "wrappeduser";
                }
            }
            case "artist" -> {
                boolean allEmpty = temp.containsKey("topAlbums")
                        && ((Map<?, ?>) temp.get("topAlbums")).isEmpty()
                        && temp.containsKey("topSongs")
                        && ((Map<?, ?>) temp.get("topSongs")).isEmpty()
                        && temp.containsKey("topFans")
                        && ((ArrayList<?>) temp.get("topFans")).isEmpty()
                        && temp.containsKey("listeners")
                        && (temp.get("listeners")).equals(0);
                if (allEmpty) {
                    this.command = "wrappedmessageartist";
                } else {
                    this.command = "wrappedartist";
                }
            }
            case "host" -> {
                boolean allEmpty = temp.containsKey("topEpisodes")
                        && ((Map<?, ?>) temp.get("topEpisodes")).isEmpty()
                        && temp.containsKey("listeners")
                        && (temp.get("listeners")).equals(0);
                if (allEmpty) {
                    this.command = "wrappedmessagehost";
                } else {
                    this.command = "wrappedhost";
                }
            }
            default -> {
                this.command = "wrappedmessage";
                this.message = "The username " + user + " doesn't exist.";
            }
        }
    }

    /**
     * Sets the type.
     *
     * @param users The users to set the type on.
     * @param artists The artists to set the type on.
     * @param hosts The hosts to set the type on.
     */
    private void setType(final ArrayList<User> users, final ArrayList<Artist> artists,
                         final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                this.type = "user";
                return;
            }
        }

        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                this.type = "artist";
                return;
            }
        }

        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                this.type = "host";
                return;
            }
        }
    }

    /**
     * Verifies if the user is a user.
     *
     * @param users The users to verify the user on.
     */
    public void verifyUser(final ArrayList<User> users) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                LinkedHashMap<String, Integer> topArtists = new LinkedHashMap<String, Integer>();
                LinkedHashMap<String, Integer> topGenres = new LinkedHashMap<String, Integer>();
                LinkedHashMap<String, Integer> topSongs = new LinkedHashMap<String, Integer>();
                LinkedHashMap<String, Integer> topAlbums = new LinkedHashMap<String, Integer>();
                LinkedHashMap<String, Integer> topEpisodes = new LinkedHashMap<String, Integer>();

                //get the top artists
                LinkedHashMap<String, Integer> sortedArtists =
                        new LinkedHashMap<String, Integer>(currentUser.getStats().getTopArtists());
                sortedArtists = sortByValue(sortedArtists);
                LinkedHashMap<String, Integer> sortedGenres =
                        new LinkedHashMap<String, Integer>(currentUser.getStats().getTopGenres());
                sortedGenres = sortByValue(sortedGenres);
                LinkedHashMap<String, Integer> sortedSongs =
                        new LinkedHashMap<String, Integer>(currentUser.getStats().getTopSongs());
                sortedSongs = sortByValue(sortedSongs);
                LinkedHashMap<String, Integer> sortedAlbums =
                        new LinkedHashMap<String, Integer>(currentUser.getStats().getTopAlbums());
                sortedAlbums = sortByValue(sortedAlbums);
                LinkedHashMap<String, Integer> sortedEpisodes =
                        new LinkedHashMap<String, Integer>(currentUser.getStats().getTopEpisodes());
                sortedEpisodes = sortByValue(sortedEpisodes);

                int counter = 0;
                for (Map.Entry<String, Integer> entry : sortedArtists.entrySet()) {
                    if (counter == MAX_COUNTER) {
                        break;
                    }
                    topArtists.put(entry.getKey(), entry.getValue());
                    counter++;
                }

                counter = 0;
                for (Map.Entry<String, Integer> entry : sortedGenres.entrySet()) {
                    if (counter == MAX_COUNTER) {
                        break;
                    }
                    topGenres.put(entry.getKey(), entry.getValue());
                    counter++;
                }

                counter = 0;
                for (Map.Entry<String, Integer> entry : sortedSongs.entrySet()) {
                    if (counter == MAX_COUNTER) {
                        break;
                    }
                    topSongs.put(entry.getKey(), entry.getValue());
                    counter++;
                }

                counter = 0;
                for (Map.Entry<String, Integer> entry : sortedAlbums.entrySet()) {
                    if (counter == MAX_COUNTER) {
                        break;
                    }
                    topAlbums.put(entry.getKey(), entry.getValue());
                    counter++;
                }

                counter = 0;
                for (Map.Entry<String, Integer> entry : sortedEpisodes.entrySet()) {
                    if (counter == MAX_COUNTER) {
                        break;
                    }
                    topEpisodes.put(entry.getKey(), entry.getValue());
                    counter++;
                }

                this.result.put("topArtists", topArtists);
                this.result.put("topGenres", topGenres);
                this.result.put("topSongs", topSongs);
                this.result.put("topAlbums", topAlbums);
                this.result.put("topEpisodes", topEpisodes);
                this.type = "user";

                return;
            }
        }
    }

    /**
     * Verifies if the user is an artist.
     *
     * @param artists The artists to verify the user on.
     */
    public void verifyArtist(final ArrayList<Artist> artists) {
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {

                LinkedHashMap<String, Integer> topAlbums = new LinkedHashMap<String, Integer>();
                LinkedHashMap<String, Integer> topSongs = new LinkedHashMap<String, Integer>();
                ArrayList<String> topFans = new ArrayList<String>();
                ArrayList<String> listeners =
                        new ArrayList<String>(currentArtist.getStats().getListeners());

                //get the top artists
                LinkedHashMap<String, Integer> sortedAlbums =
                        new LinkedHashMap<String, Integer>(currentArtist.getStats().getTopAlbums());
                sortedAlbums = sortByValue(sortedAlbums);
                LinkedHashMap<String, Integer> sortedSongs =
                        new LinkedHashMap<String, Integer>(currentArtist.getStats().getTopSongs());
                sortedSongs = sortByValue(sortedSongs);



                int counter = 0;
                for (Map.Entry<String, Integer> entry : sortedSongs.entrySet()) {
                    if (counter == MAX_COUNTER) {
                        break;
                    }
                    topSongs.put(entry.getKey(), entry.getValue());
                    counter++;
                }

                counter = 0;
                for (Map.Entry<String, Integer> entry : sortedAlbums.entrySet()) {
                    if (counter == MAX_COUNTER) {
                        break;
                    }
                    topAlbums.put(entry.getKey(), entry.getValue());
                    counter++;
                }
                //sort fans by listens and then alphabetically
                LinkedHashMap<String, Integer> sortedFans =
                        new LinkedHashMap<String, Integer>(currentArtist.getStats().getTopFans());
                sortedFans = sortByValue(sortedFans);

                counter = 0;
                for (Map.Entry<String, Integer> entry : sortedFans.entrySet()) {
                    if (counter == MAX_COUNTER) {
                        break;
                    }
                    topFans.add(entry.getKey());
                    counter++;
                }


                this.result.put("topAlbums", topAlbums);
                this.result.put("topSongs", topSongs);
                this.result.put("topFans", topFans);
                this.result.put("listeners", listeners.size());
                this.type = "artist";

                return;
            }
        }
    }

    /**
     * Verifies if the user is a host.
     *
     * @param hosts The hosts to verify the user on.
     */
    public void verifyHost(final ArrayList<Host> hosts) {
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {

                LinkedHashMap<String, Integer> topEpisodes = new LinkedHashMap<String, Integer>();
                ArrayList<String> listeners =
                        new ArrayList<String>(currentHost.getStats().getListeners());

                //get the top episodes
                LinkedHashMap<String, Integer> sortedEpisodes =
                        new LinkedHashMap<String, Integer>(currentHost.getStats().getTopEpisodes());
                sortedEpisodes = sortByValue(sortedEpisodes);

                int counter = 0;
                for (Map.Entry<String, Integer> entry : sortedEpisodes.entrySet()) {
                    if (counter == MAX_COUNTER) {
                        break;
                    }
                    topEpisodes.put(entry.getKey(), entry.getValue());
                    counter++;
                }

                this.result.put("topEpisodes", topEpisodes);
                this.result.put("listeners", listeners.size());
                this.type = "host";

                return;
            }
        }
    }


    /**
     * Sorts a hashmap by value.
     *
     * @param hm The hashmap to sort.
     * @return The sorted hashmap.
     */
    public static LinkedHashMap<String, Integer> sortByValue(final HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        ArrayList<Map.Entry<String, Integer>> list =
                new ArrayList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list reverse order
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(final Map.Entry<String, Integer> o1,
                               final Map.Entry<String, Integer> o2) {
                if (Objects.equals(o1.getValue(), o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        LinkedHashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
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
        return result;
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
     * Gets the result.
     *
     * @return The result.
     */
    public LinkedHashMap<String, Object> getResult() {
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result The result.
     */
    public void setResult(final LinkedHashMap<String, Object> result) {
        this.result = result;
    }

    /**
     * Gets the type.
     *
     * @return The type.
     */
    @JsonIgnore
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type The type.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the message.
     *
     * @return The message.
     */
    @JsonIgnore
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
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    @Override @JsonIgnore
    public String getCommandName() {
        return command;
    }
}
