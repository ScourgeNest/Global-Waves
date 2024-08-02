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

public class SelectCommand implements Command {

    private String command = "select";
    private String user;
    private int timestamp;
    private int itemNumber;
    private String message;
    private String selectedResult;
    private String type;
    private ArrayList<Song> songs;
    private ArrayList<Playlist> playlists;
    private ArrayList<Podcast> podcasts;
    private ArrayList<Artist> artists;
    private ArrayList<Host> hosts;
    private ArrayList<Album> albums;

    /**
     * Constructor for the select command.
     *
     * @param user       The user.
     * @param timestamp  The timestamp.
     * @param itemNumber The item number.
     */
    public SelectCommand(final String user, final int timestamp, final int itemNumber) {
        this.user = user;
        this.timestamp = timestamp;
        this.itemNumber = itemNumber;
        this.songs = new ArrayList<Song>();
        this.playlists = new ArrayList<Playlist>();
        this.podcasts = new ArrayList<Podcast>();
        this.artists = new ArrayList<Artist>();
        this.hosts = new ArrayList<Host>();
        this.albums = new ArrayList<Album>();
    }

    /**
     * Selects a song or podcast from the search results.
     *
     * @param users    The users to execute the command on.
     */
    public void execute(final ArrayList<Song> songList, final ArrayList<Podcast> podcastList,
                        final ArrayList<User> users, final ArrayList<Artist> artistList,
                        final ArrayList<Host> hostList) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getConnectionStatus().equals("offline")) {
                    this.message = this.user + " is offline.";
                    return;
                }
            }
        }
        // Parse through the users
        for (User currentUser : users) {
            // Find the user by username
            if (currentUser.getUsername().equals(this.user)) {
                SearchCommand temp = currentUser.getLastSearchCommand();
                // Check if the user has searched for something
                if (temp != null) {
                    // Check if the search results are empty
                    if (!temp.getResults().isEmpty()) {
                        // Check if the item number is within the range of the search results
                        if (this.itemNumber > temp.getResults().size() || this.itemNumber <= 0) {
                            this.message = "The selected ID is too high.";
                            this.type = temp.getType();
                            temp = null;
                        } else {
                            // Set the selected result to the result at the specified item number
                            this.type = temp.getType();
                            selectByType(temp, currentUser);
                            if (this.type.equals("artist") || this.type.equals("host")) {
                                return;
                            }
                            this.message = "Successfully selected " + this.selectedResult + ".";
                        }
                    } else {
                        this.message = "The selected ID is too high.";
                        temp = null;
                    }
                } else {
                    this.message = "Please conduct a search before making a selection.";
                }
                // Set the last select command to the current user
                if (temp != null) {
                    currentUser.setLastSelectCommand(this);
                } else {
                    currentUser.setLastSelectCommand(null);
                }
                currentUser.setLastSearchCommand(null);
            }
        }

    }

    /**
     * Gets the command.
     *
     * @return The command.
     */
    public String getCommand() {
        return "select";
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
     * @return
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
     * Gets the item number.
     *
     * @return The item number.
     */
    @JsonIgnore
    public int getItemNumber() {
        return itemNumber;
    }

    /**
     * Sets the item number.
     *
     * @param itemNumber The item number.
     */
    public void setItemNumber(final int itemNumber) {
        this.itemNumber = itemNumber;
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
     * Gets the command name.
     *
     * @return The command name.
     */
    @JsonIgnore
    public String getCommandName() {
        return "select";
    }

    /**
     * Gets the selected result.
     *
     * @return The selected result.
     */
    @JsonIgnore
    public String getSelectedResult() {
        return selectedResult;
    }

    /**
     * Sets the selected result.
     *
     * @param selectedResult The selected result.
     */
    public void setSelectedResult(final String selectedResult) {
        this.selectedResult = selectedResult;
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
     * Selects the result by type.
     *
     * @param temp       The search command.
     * @param currentUser The current user.
     */
    public void selectByType(final SearchCommand temp, final User currentUser) {
        if (this.type.equals("song")) {
            this.selectedResult = temp.getSongs().get(this.itemNumber - 1).getName();
            this.songs.add(temp.getSongs().get(this.itemNumber - 1));
        } else if (this.type.equals("album")) {
            this.selectedResult = temp.getAlbums().get(this.itemNumber - 1).getName();
            this.albums.add(temp.getAlbums().get(this.itemNumber - 1));
        } else if (this.type.equals("playlist")) {
            this.selectedResult = temp.getPlaylists().get(this.itemNumber - 1).getName();
            this.playlists.add(temp.getPlaylists().get(this.itemNumber - 1));
        } else if (this.type.equals("artist")) {
            this.selectedResult = temp.getArtists().get(this.itemNumber - 1).getName();
            this.artists.add(temp.getArtists().get(this.itemNumber - 1));
            for (Artist currentArtist : artists) {
                if (currentArtist.getName().equals(this.selectedResult)) {
                    this.message = "Successfully selected " + this.selectedResult
                            + "'s page.";
                    currentUser.setCurrentPage("artistPage");
                    currentUser.setPageOwner(this.selectedResult);
                    currentUser.setLastSelectCommand(this);
                    currentUser.setLastSearchCommand(null);
                }
            }
        } else if (this.type.equals("host")) {
            this.selectedResult = temp.getHosts().get(this.itemNumber - 1).getName();
            this.hosts.add(temp.getHosts().get(this.itemNumber - 1));
            for (Host currentHost : hosts) {
                if (currentHost.getName().equals(this.selectedResult)) {
                    this.message = "Successfully selected " + this.selectedResult
                            + "'s page.";
                    currentUser.setCurrentPage("hostPage");
                    currentUser.setPageOwner(this.selectedResult);
                    currentUser.setLastSelectCommand(this);
                    currentUser.setLastSearchCommand(null);
                }
            }
        } else if (this.type.equals("podcast")) {
            this.selectedResult = temp.getPodcasts().get(this.itemNumber - 1).getName();
            this.podcasts.add(temp.getPodcasts().get(this.itemNumber - 1));
        }
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
}
