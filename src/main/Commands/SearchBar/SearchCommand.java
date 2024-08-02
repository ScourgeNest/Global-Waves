package main.Commands.SearchBar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Album;
import main.Media.Playlist;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;
import java.util.Map;

public class SearchCommand implements Command {

    private static final int MAX_SEARCH = 5;
    private String command = "search";
    private String user;
    private int timestamp;
    private String type;
    private Map<String, Object> filters;
    private String message;
    private ArrayList<String> results;
    private ArrayList<Song> songs;
    private ArrayList<Playlist> playlists;
    private ArrayList<Podcast> podcasts;
    private ArrayList<Artist> artists;
    private ArrayList<Host> hosts;
    private ArrayList<Album> albums;

    /**
     * Constructor for the search command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param type      The type.
     * @param filters   The filters.
     */
    public SearchCommand(final String user, final int timestamp, final String type,
                         final Map<String, Object> filters) {
        this.user = user;
        this.timestamp = timestamp;
        this.type = type;
        this.filters = filters;
        this.songs = new ArrayList<Song>();
        this.playlists = new ArrayList<Playlist>();
        this.podcasts = new ArrayList<Podcast>();
        this.artists = new ArrayList<Artist>();
        this.hosts = new ArrayList<Host>();
        this.albums = new ArrayList<Album>();
    }

    /**
     * Executes the search command.
     *
     * @param songArrayList    The songs to execute the command on.
     * @param podcastArrayList The podcasts to execute the command on.
     * @param userArrayList    The users to execute the command on.
     */
    @Override
    public void execute(final ArrayList<Song> songArrayList,
                        final ArrayList<Podcast> podcastArrayList,
                        final ArrayList<User> userArrayList,
                        final ArrayList<Artist> artistArrayList,
                        final ArrayList<Host> hostArrayList) {

        this.results = new ArrayList<String>(); // Initialize results as an empty ArrayList

        for (User currentUser : userArrayList) {
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getConnectionStatus().equals("offline")) {
                    this.message = this.user + " is offline.";
                    return;
                }
            }
        }

        // Check the type of the search
        if (this.type.equals("song")) {
            this.results = searchSongs(songArrayList);
        } else if (this.type.equals("podcast")) {
            this.results = searchPodcasts(podcastArrayList);
        } else if (this.type.equals("playlist")) {
            this.results = searchPlaylists(userArrayList);
        } else if (this.type.equals("artist")) {
            this.results = seatchArtists(artistArrayList);
        } else if (this.type.equals("host")) {
            this.results = searchHosts(hostArrayList);
        } else if (this.type.equals("album")) {
            this.results = searchAlbums(artistArrayList);
        } else {
            this.message = "Invalid search type";
            return;
        }

    // Set the message
        this.message = "Search returned " + this.results.size() + " results";

        // Set the last search command for the user
        for (User currentuser : userArrayList) {
            if (currentuser.getUsername().equals(this.user)) {
                currentuser.setLastSearchCommand(this);
            }
        }
    }

    private ArrayList<String> searchAlbums(final ArrayList<Artist> artistArrayList) {
        ArrayList<String> filteredAlbums = new ArrayList<>();
        int maxAlbums = MAX_SEARCH;
        int albumsAdded = 0;
        // Iterate through the list of artists
        for (Artist currentArtist : artistArrayList) {
            if (albumsAdded >= maxAlbums) {
                break;
            }
            // Check if the artist's albums are empty
            for (int i = 0; i < currentArtist.getAlbums().size(); i++) {
                // Check if the maximum albums limit is reached
                if (albumsAdded >= maxAlbums) {
                    break;
                }
                // Check if the album matches the filters
                if (matchesFiltersAlbums(currentArtist.getAlbums().get(i))) {
                    filteredAlbums.add(currentArtist.getAlbums().get(i).getName());
                    this.albums.add(currentArtist.getAlbums().get(i));
                    albumsAdded++;
                }
            }
        }
        return filteredAlbums;
    }

    private boolean matchesFiltersAlbums(final Album matchAlbum) {
        // Iterate through the filters
        for (Map.Entry<String, Object> filterEntry : this.filters.entrySet()) {
            // Get the filter name and value
            String filterName = filterEntry.getKey();
            Object filterValue = filterEntry.getValue();
            // Check the filter name
            switch (filterName) {
                case "name":
                    if (!matchAlbum.getName().startsWith((String) filterValue)) {
                        return false;
                    }
                    break;
                case "owner":
                    if (!matchAlbum.getOwner().equals(filterValue)) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private ArrayList<String> searchHosts(final ArrayList<Host> hostArrayList) {
        ArrayList<String> filteredHosts = new ArrayList<>();
        int maxHosts = MAX_SEARCH;
        int hostsAdded = 0;
        // Iterate through the list of hosts
        for (Host currentHost : hostArrayList) {
            if (hostsAdded >= maxHosts) {
                break;
            }
            // Check if the host matches the filters
            if (matchesFiltersHosts(currentHost)) {
                filteredHosts.add(currentHost.getName());
                this.hosts.add(currentHost);
                hostsAdded++;
            }
        }
        return filteredHosts;
    }

    private boolean matchesFiltersHosts(final Host currentHost) {
        // Iterate through the filters
        for (Map.Entry<String, Object> filterEntry : this.filters.entrySet()) {
            // Get the filter name and value
            String filterName = filterEntry.getKey();
            Object filterValue = filterEntry.getValue();
            // Check the filter name
            switch (filterName) {
                case "name":
                    if (!currentHost.getName().startsWith((String) filterValue)) {
                        return false;
                    }
                    break;
                case "podcast":
                    boolean podcastFound = false;
                    for (int i = 0; i < currentHost.getPodcasts().size(); i++) {
                        if (currentHost.getPodcasts().get(i).getName().
                                equals((String) filterValue)) {
                            podcastFound = true;
                            break;
                        }
                    }
                    if (!podcastFound) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }


    private ArrayList<String> seatchArtists(final ArrayList<Artist> artistArrayList) {
        ArrayList<String> filteredArtists = new ArrayList<>();
        int maxArtists = MAX_SEARCH;
        int artistsAdded = 0;
        // Iterate through the list of artists
        for (Artist currentArtist : artistArrayList) {
            if (artistsAdded >= maxArtists) {
                break;
            }
            // Check if the artist matches the filters
            if (matchesFiltersArtists(currentArtist)) {
                filteredArtists.add(currentArtist.getName());
                this.artists.add(currentArtist);
                artistsAdded++;
            }
        }
        return filteredArtists;
    }

    private boolean matchesFiltersArtists(final Artist artist) {
        // Iterate through the filters
        for (Map.Entry<String, Object> filterEntry : this.filters.entrySet()) {
            // Get the filter name and value
            String filterName = filterEntry.getKey();
            Object filterValue = filterEntry.getValue();
            // Check the filter name
            switch (filterName) {
                case "name":
                    if (!artist.getName().startsWith((String) filterValue)) {
                        return false;
                    }
                    break;
                case "event":
                    boolean eventFound = false;
                    for (int i = 0; i < artist.getEvents().size(); i++) {
                        if (artist.getEvents().get(i).getName().equals((String) filterValue)) {
                            eventFound = true;
                            break;
                        }
                    }
                    if (!eventFound) {
                        return false;
                    }
                    break;
                case "album":
                    boolean albumFound = false;
                    for (int i = 0; i < artist.getAlbums().size(); i++) {
                        if (artist.getAlbums().get(i).getName().equals((String) filterValue)) {
                            albumFound = true;
                            break;
                        }
                    }
                    if (!albumFound) {
                        return false;
                    }
                    break;
                case "merch":
                    boolean merchFound = false;
                    for (int i = 0; i < artist.getMerch().size(); i++) {
                        if (artist.getMerch().get(i).getName().equals((String) filterValue)) {
                            merchFound = true;
                            break;
                        }
                    }
                    if (!merchFound) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    /**
     * Searches for playlists.
     *
     * @param users The users to execute the command on.
     * @return The filtered playlists.
     */
    public ArrayList<String> searchPlaylists(final ArrayList<User> users) {
        ArrayList<String> filteredPlaylists = new ArrayList<>();
        int maxPlaylists = MAX_SEARCH;
        int playlistsAdded = 0;
        // Iterate through the list of users
        for (User currentUser : users) {
            if (playlistsAdded >= maxPlaylists) {
                break;
            }
            // Check if the user's playlists are empty
            for (int i = 0; i < currentUser.getPlaylists().size(); i++) {
                // Check if the maximum playlists limit is reached
                Playlist playlist = currentUser.getPlaylists().get(i);
                if (playlistsAdded >= maxPlaylists) {
                    break;
                }
                // Check if the playlist is private and the user is not the owner
                if (playlist.getVisibility().equals("private") && !playlist.getOwner().
                        equals(this.user)) {
                    continue;
                }
                // Check if the playlist matches the filters
                if (matchesFiltersPlaylists(currentUser.getPlaylists().get(i))) {
                    filteredPlaylists.add(currentUser.getPlaylists().get(i).getName());
                    this.playlists.add(currentUser.getPlaylists().get(i));
                    playlistsAdded++;
                }
            }
        }
        return filteredPlaylists;
    }

    /**
     * Checks if a playlist matches the filters.
     *
     * @param playlist The playlist to check.
     * @return True if the playlist matches the filters, false otherwise.
     */
    public boolean matchesFiltersPlaylists(final Playlist playlist) {
        // Iterate through the filters
        for (Map.Entry<String, Object> filterEntry : this.filters.entrySet()) {
            // Get the filter name and value
            String filterName = filterEntry.getKey();
            Object filterValue = filterEntry.getValue();
            // Check the filter name
            switch (filterName) {
                case "name":
                    if (!playlist.getName().startsWith((String) filterValue)) {
                        return false;
                    }
                    break;
                case "owner":
                    if (!playlist.getOwner().equals((String) filterValue)) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    /**
     * Searches for songs.
     *
     * @param songArrayList The songs to execute the command on.
     * @return The filtered songs.
     */
    public ArrayList<String> searchSongs(final ArrayList<Song> songArrayList) {
        // Initialize the filtered songs list
        ArrayList<String> filteredSongs = new ArrayList<>();
        int maxSongs = MAX_SEARCH;
        int songsAdded = 0;
        // Iterate through the songs
        for (Song song : songArrayList) {
            // Check if the maximum songs limit is reached
            if (songsAdded >= maxSongs) {
                break; // Break the loop if the maximum songs limit is reached
            }
            // Check if the song matches the filters
            if (matchesFiltersSongs(song)) {
                filteredSongs.add(song.getName());
                this.songs.add(song);
                songsAdded++;
            }
        }
        return filteredSongs;
    }

    /**
     * Searches for podcasts.
     *
     * @param podcastArrayList The podcasts to execute the command on.
     * @return The filtered podcasts.
     */
    public ArrayList<String> searchPodcasts(final ArrayList<Podcast> podcastArrayList) {
        // Initialize the filtered podcasts list
        ArrayList<String> filteredPodcasts = new ArrayList<>();
        int maxPodcasts = MAX_SEARCH;
        int podcastsAdded = 0;
        // Iterate through the podcasts
        for (Podcast podcast : podcastArrayList) {
            // Check if the maximum podcasts limit is reached
            if (podcastsAdded >= maxPodcasts) {
                break;
            }

            // Check if the podcast matches the filters
            if (matchesFiltersPodcasts(podcast)) {
                filteredPodcasts.add(podcast.getName());
                this.podcasts.add(podcast);
                podcastsAdded++;
            }

        }
        return filteredPodcasts;
    }

    /**
     * Checks if a song matches the filters.
     *
     * @param song The song to check.
     * @return True if the song matches the filters, false otherwise.
     */
    private boolean matchesFiltersSongs(final Song song) {
        // Iterate through the filters
        for (Map.Entry<String, Object> filterEntry : this.filters.entrySet()) {
            String filterName = filterEntry.getKey();
            Object filterValue = filterEntry.getValue();
            // Check the filter name
            switch (filterName) {
                case "name":
                    if (!song.getName().toLowerCase().startsWith(((String)
                            filterValue).toLowerCase())) {
                        return false;
                    }
                    break;
                case "album":
                    if (song.getAlbum() == null
                            || !song.getAlbum().equalsIgnoreCase((String) filterValue)) {
                        return false;
                    }
                    break;
                case "tags":
                    if (!matchesTags(song.getTags(), (ArrayList<String>) filterValue)) {
                        return false;
                    }
                    break;
                case "lyrics":
                    if (!song.getLyrics().toLowerCase().contains(((String) (filterValue)).
                            toLowerCase())) {
                        return false;
                    }
                    break;
                case "genre":
                    if (!song.getGenre().equalsIgnoreCase((String) filterValue)) {
                        return false;
                    }
                    break;
                case "releaseYear":
                    if (!matchesReleaseYear(song, (String) filterValue)) {
                        return false;
                    }
                    break;
                case "artist", "owner":
                    if (!song.getArtist().equalsIgnoreCase((String) filterValue)) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if a podcast matches the filters.
     *
     * @param podcast The podcast to check.
     * @return True if the podcast matches the filters, false otherwise.
     */
    private boolean matchesFiltersPodcasts(final Podcast podcast) {
        // Iterate through the filters
        for (Map.Entry<String, Object> filterEntry : this.filters.entrySet()) {
            String filterName = filterEntry.getKey();
            Object filterValue = filterEntry.getValue();
            // Check the filter name
            switch (filterName) {
                case "name":
                    if (!podcast.getName().startsWith((String) filterValue)) {
                        return false;
                    }
                    break;
                case "owner":
                    if (!podcast.getOwner().equals((String) filterValue)) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if a song matches the tags.
     *
     * @param songTags    The song tags.
     * @param filterTags The filter tags.
     * @return True if the song matches the tags, false otherwise.
     */
    private boolean matchesTags(final ArrayList<String> songTags,
                                final ArrayList<String> filterTags) {
        // Verify that all filter tags are present in the song tags
        for (String tag : filterTags) {
            if (!songTags.contains(tag)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a song matches the release year.
     *
     * @param song        The song.
     * @param filterValue The filter value.
     * @return True if the song matches the release year, false otherwise.
     */
    private boolean matchesReleaseYear(final Song song, final String filterValue) {
        if (song.getReleaseYear() == null) {
            return false;
        }

        // Remove non-numeric characters from the filter value to get the year
        String yearString = filterValue.replaceAll("[^0-9]", "");

        // Check if the filterValue starts with "<" or ">"
        if (filterValue.startsWith("<")) {
            int filterYear = Integer.parseInt(yearString);
            return song.getReleaseYear() < filterYear;
        } else if (filterValue.startsWith(">")) {
            int filterYear = Integer.parseInt(yearString);
            return song.getReleaseYear() > filterYear;
        } else {
            // If no operator is provided, perform an exact match
            int filterYear = Integer.parseInt(yearString);
            return song.getReleaseYear().equals(filterYear);
        }
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command name.
     *
     * @param command The command name.
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
     * Gets the filters.
     *
     * @return The filters.
     */
    @JsonIgnore
    public Map<String, Object> getFilters() {
        return filters;
    }

    /**
     * Sets the filters.
     *
     * @param filters The filters.
     */
    public void setFilters(final Map<String, Object> filters) {
        this.filters = filters;
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
     * Gets the results.
     *
     * @return The results.
     */
    public ArrayList<String> getResults() {
        return results;
    }

    /**
     * Sets the results.
     *
     * @param results The results.
     */
    public void setResults(final ArrayList<String> results) {
        this.results = results;
    }

    /**
     * Gets the songs.
     *
     * @return The songs.
     */
    @JsonIgnore
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the songs.
     *
     * @param songs The songs.
     */
    public void setSongs(final ArrayList<Song> songs) {
        this.songs = songs;
    }

    /**
     * Gets the playlists.
     *
     * @return The playlists.
     */
    @JsonIgnore
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Sets the playlists.
     *
     * @param playlists The playlists.
     */
    public void setPlaylists(final ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    /**
     * Gets the podcasts.
     *
     * @return The podcasts.
     */
    @JsonIgnore
    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    /**
     * Sets the podcasts.
     *
     * @param podcasts The podcasts.
     */
    public void setPodcasts(final ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    /**
     * Gets the artists.
     *
     * @return The artists.
     */
    @JsonIgnore
    public ArrayList<Artist> getArtists() {
        return artists;
    }

    /**
     * Sets the artists.
     *
     * @param artists The artists.
     */
    public void setArtists(final ArrayList<Artist> artists) {
        this.artists = artists;
    }

    /**
     * Gets the hosts.
     *
     * @return The hosts.
     */
    @JsonIgnore
    public ArrayList<Host> getHosts() {
        return hosts;
    }

    /**
     * Sets the hosts.
     *
     * @param hosts The hosts.
     */
    public void setHosts(final ArrayList<Host> hosts) {
        this.hosts = hosts;
    }

    /**
     * Gets the albums.
     *
     * @return The albums.
     */
    @JsonIgnore
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    /**
     * Sets the albums.
     *
     * @param albums The albums.
     */
    public void setAlbums(final ArrayList<Album> albums) {
        this.albums = albums;
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return "search";
    }
}
