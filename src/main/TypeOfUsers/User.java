package main.TypeOfUsers;

import main.Commands.Notifications.Observer;
import main.Commands.SearchBar.SearchCommand;
import main.Commands.SearchBar.SelectCommand;
import main.Commands.Users.NormalUsers.PageNavigation.Page;
import main.Media.*;
import main.TypeOfUsers.Stats_and_Builder_Pattern.WrappedStatistics;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class User implements Observer {
    private final String username;
    private final int age;
    private final String city;

    private int timestamp;

    private Song currentlyPlayingSong;

    private Podcast currentlyPlayingPodcast;

    private Playlist currentlyPlayingPlaylist;

    private Episode currentlyPlayingEpisode;

    private String currentlyPlaying;

    private int timeRemaining;

    private int durationCurrentlyPlaying;

    private boolean isPaused = false;

    private boolean shuffle = false;

    private String repeat = "No Repeat";

    private ArrayList<Playlist> playlists;

    private SearchCommand lastSearchCommand;

    private SelectCommand lastSelectCommand;

    private ArrayList<Song> prefferedSongs = new ArrayList<Song>();

    private ArrayList<Integer> lastEpisode;

    private ArrayList<Integer> watchedTimeEpisode;

    private ArrayList<Integer> shuffledIndices = new ArrayList<Integer>();

    private ArrayList<Integer> originalIndices = new ArrayList<Integer>();

    private ArrayList<Playlist> followedPlaylists = new ArrayList<Playlist>();

    private String connectionStatus = "online";

    private String currentPage = "Home";

    private String pageOwner = "";

    private WrappedStatistics stats;
    private ArrayList<Song> ListenedSongsPremium;
    private ArrayList<Song> ListenedSongsFree;

    private String subscriptionType = "FREE";

    private ArrayList<LinkedHashMap<String, String>> notifications;

    private ArrayList<String> merch;

    private Song songRecommendations;
    private String playlistRecommendations;

    private ArrayList<Page> previousPages = new ArrayList<Page>();

    private ArrayList<Page> nextPages = new ArrayList<Page>();

    /**
     * Get the username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Get the city
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Get the timestamp
     *
     * @return timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Set the timestamp
     *
     * @param timestamp
     */
    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get the time remaining
     *
     * @return time remaining
     */
    public int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * Set the time remaining
     *
     * @param timeRemaining
     */
    public void setTimeRemaining(final int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    /**
     * Get the duration currently playing
     *
     * @return duration currently playing
     */
    public int getDurationCurrentlyPlaying() {
        return durationCurrentlyPlaying;
    }

    /**
     * Set the duration currently playing
     *
     * @param durationCurrentlyPlaying
     */
    public void setDurationCurrentlyPlaying(final int durationCurrentlyPlaying) {
        this.durationCurrentlyPlaying = durationCurrentlyPlaying;
    }

    /**
     * Get the is paused
     *
     * @return is paused
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Set the is paused
     *
     * @param paused
     */
    public void setPaused(final boolean paused) {
        isPaused = paused;
    }

    /**
     * Get the shuffle
     *
     * @return shuffle
     */
    public boolean isShuffle() {
        return shuffle;
    }

    /**
     * Set the shuffle
     *
     * @param shuffle
     */
    public void setShuffle(final boolean shuffle) {
        this.shuffle = shuffle;
    }

    /**
     * Get the repeat
     *
     * @return repeat
     */
    public String getRepeat() {
        return repeat;
    }

    /**
     * Set the repeat
     *
     * @param repeat
     */
    public void setRepeat(final String repeat) {
        this.repeat = repeat;
    }

    /**
     * Get the playlists
     *
     * @return playlists
     */
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Set the playlists
     *
     * @param playlists
     */
    public void setPlaylists(final ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    /**
     * Get the last search command
     *
     * @return last search command
     */
    public SearchCommand getLastSearchCommand() {
        return lastSearchCommand;
    }

    /**
     * Set the last search command
     *
     * @param lastSearchCommand
     */
    public void setLastSearchCommand(final SearchCommand lastSearchCommand) {
        this.lastSearchCommand = lastSearchCommand;
    }

    /**
     * Get the last select command
     *
     * @return last select command
     */
    public SelectCommand getLastSelectCommand() {
        return lastSelectCommand;
    }

    /**
     * Set the last select command
     *
     * @param lastSelectCommand
     */
    public void setLastSelectCommand(final SelectCommand lastSelectCommand) {
        this.lastSelectCommand = lastSelectCommand;
    }

    /**
     * Get the currently playing song
     *
     * @return currently playing song
     */
    public Song getCurrentlyPlayingSong() {
        return currentlyPlayingSong;
    }

    /**
     * Set the currently playing song
     *
     * @param currentlyPlayingSong
     */
    public void setCurrentlyPlayingSong(final Song currentlyPlayingSong) {
        this.currentlyPlayingSong = currentlyPlayingSong;
    }

    /**
     * Get the currently playing podcast
     *
     * @return currently playing podcast
     */
    public Podcast getCurrentlyPlayingPodcast() {
        return currentlyPlayingPodcast;
    }

    /**
     * Set the currently playing podcast
     *
     * @param currentlyPlayingPodcast
     */
    public void setCurrentlyPlayingPodcast(final Podcast currentlyPlayingPodcast) {
        this.currentlyPlayingPodcast = currentlyPlayingPodcast;
    }

    /**
     * Get the currently playing playlist
     *
     * @return currently playing playlist
     */
    public Playlist getCurrentlyPlayingPlaylist() {
        return currentlyPlayingPlaylist;
    }

    /**
     * Set the currently playing playlist
     *
     * @param currentlyPlayingPlaylist
     */
    public void setCurrentlyPlayingPlaylist(final Playlist currentlyPlayingPlaylist) {
        this.currentlyPlayingPlaylist = currentlyPlayingPlaylist;
    }

    /**
     * Get the currently playing episode
     *
     * @return currently playing episode
     */
    public Episode getCurrentlyPlayingEpisode() {
        return currentlyPlayingEpisode;
    }

    /**
     * Set the currently playing episode
     *
     * @param currentlyPlayingEpisode
     */
    public void setCurrentlyPlayingEpisode(final Episode currentlyPlayingEpisode) {
        this.currentlyPlayingEpisode = currentlyPlayingEpisode;
    }

    /**
     * Get the currently playing
     *
     * @return currently playing
     */
    public String getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    /**
     * Set the currently playing
     *
     * @param currentlyPlaying
     */
    public void setCurrentlyPlaying(final String currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    /**
     * Get the preferred songs
     *
     * @return preferred songs
     */
    public ArrayList<Song> getPreferredSongs() {
        return prefferedSongs;
    }

    /**
     * Set the preferred songs
     *
     * @param prefferedSongs
     */
    public void setPrefferedSongs(final ArrayList<Song> prefferedSongs) {
        this.prefferedSongs = prefferedSongs;
    }

    /**
     * Get the last episode
     *
     * @return last episode
     */
    public ArrayList<Integer> getLastEpisode() {
        return lastEpisode;
    }

    /**
     * Set the last episode
     *
     * @param lastEpisode
     */
    public void setLastEpisode(final ArrayList<Integer> lastEpisode) {
        this.lastEpisode = lastEpisode;
    }

    /**
     * Get the watched time episode
     *
     * @return watched time episode
     */
    public ArrayList<Integer> getWatchedTimeEpisode() {
        return watchedTimeEpisode;
    }

    /**
     * Set the watched time episode
     *
     * @param watchedTimeEpisode
     */
    public void setWatchedTimeEpisode(final ArrayList<Integer> watchedTimeEpisode) {
        this.watchedTimeEpisode = watchedTimeEpisode;
    }

    /**
     * Get the shuffled indices
     *
     * @return shuffled indices
     */
    public ArrayList<Integer> getShuffledIndices() {
        return shuffledIndices;
    }

    /**
     * Set the shuffled indices
     *
     * @param shuffledIndices
     */
    public void setShuffledIndices(final ArrayList<Integer> shuffledIndices) {
        this.shuffledIndices = shuffledIndices;
    }

    /**
     * Get the original indices
     *
     * @return original indices
     */
    public ArrayList<Integer> getOriginalIndices() {
        return originalIndices;
    }

    /**
     * Set the original indices
     *
     * @param originalIndices
     */
    public void setOriginalIndices(final ArrayList<Integer> originalIndices) {
        this.originalIndices = originalIndices;
    }

    /**
     * Get the followed playlists
     *
     * @return followed playlists
     */
    public ArrayList<Playlist> getFollowedPlaylists() {
        return followedPlaylists;
    }

    /**
     * Set the followed playlists
     *
     * @param followedPlaylists
     */
    public void setFollowedPlaylists(final ArrayList<Playlist> followedPlaylists) {
        this.followedPlaylists = followedPlaylists;
    }

    /**
     * Get the stats
     *
     * @return stats
     */
    public WrappedStatistics getStats() {
        return stats;
    }

    /**
     * Set the stats
     *
     * @param stats
     */
    public void setStats(WrappedStatistics stats) {
        this.stats = stats;
    }

    /**
     * Constructor for user
     *
     * @param username
     * @param age
     * @param city
     * @param podcasts
     */
    public User(final String username, final int age, final String city,
            final ArrayList<Podcast> podcasts) {
        this.username = username;
        this.age = age;
        this.city = city;
        this.playlists = new ArrayList<Playlist>();
        this.prefferedSongs = new ArrayList<Song>();
        this.lastEpisode = new ArrayList<Integer>();
        this.watchedTimeEpisode = new ArrayList<Integer>();
        for (Podcast podcast : podcasts) {
            this.lastEpisode.add(0);
            this.watchedTimeEpisode.add(0);
        }
        this.stats = new WrappedStatistics.Builder().buildTopArtist()
                .buildTopGenre().buildTopSong().buildTopAlbum().buildTopEpisode().build();
        this.ListenedSongsPremium = new ArrayList<Song>();
        this.ListenedSongsFree = new ArrayList<Song>();
        this.notifications = new ArrayList<LinkedHashMap<String, String>>();
        this.merch = new ArrayList<String>();
    }

    /**
     * Simulates the time passing for the player
     *
     * @param newTimestamp
     * @param podcasts
     */
    public void player(final int newTimestamp, final ArrayList<Podcast> podcasts, final ArrayList<Artist> artists, final ArrayList<Host> hosts) {
        // If the player is paused, do nothing
        if (!this.isPaused && !this.connectionStatus.equals("offline")) {
            // If the player is playing a song
            if (this.timeRemaining > 0) {
                // If the player is playing a song, decrease the time remaining
                this.timeRemaining = this.timeRemaining - (newTimestamp - this.timestamp);
                // If the time remaining is 0, the song is over
                if (this.timeRemaining <= 0) {
                    int timepassed = this.timeRemaining * (-1);
                    if (this.currentlyPlayingPodcast != null) {
                        playPodcast(newTimestamp, podcasts, timepassed, artists, hosts);
                    } else if (this.currentlyPlayingPlaylist != null) {
                        playPlaylist(newTimestamp, timepassed, artists, hosts);
                    } else {
                        playSong(newTimestamp, timepassed, artists, hosts);
                    }
                }
                // Set the last episode and the watched time
                int indexPodcast = podcasts.indexOf(this.currentlyPlayingPodcast);
                if (indexPodcast != -1) {
                    this.watchedTimeEpisode.set(indexPodcast,
                            this.watchedTimeEpisode.get(indexPodcast) + (newTimestamp
                                    - this.timestamp));
                }
                // Update the newTimestamp
                this.timestamp = newTimestamp;
            }
        }
    }

    /**
     * Simulates the time passing for the player or pauses it
     *
     * @param newTimestamp
     * @param podcasts
     */
    private void playPodcast(final int newTimestamp, final ArrayList<Podcast> podcasts,
                             final int timepassed, final ArrayList<Artist> artists, final ArrayList<Host> hosts) {
        int timePassed = timepassed;
        if (this.repeat.equals("No Repeat")) {
            while (timePassed >= 0) {
                // Get the index of the episode
                int indexEpisode = this.currentlyPlayingPodcast.getPodcastEpisodes()
                        .indexOf(this.currentlyPlayingEpisode);
                int indexPodcast = podcasts.indexOf(this.currentlyPlayingPodcast);
                // If the index is smaller than the size of the episodes list
                if (indexEpisode + 1 < this.currentlyPlayingPodcast.getPodcastEpisodes().size()) {
                    // Get the next episode
                    this.currentlyPlayingEpisode = this.currentlyPlayingPodcast.getPodcastEpisodes()
                            .get(indexEpisode + 1);
                    this.currentlyPlaying = this.currentlyPlayingEpisode.getName();
                    this.durationCurrentlyPlaying = this.currentlyPlayingEpisode.getDuration();
                    this.timeRemaining = this.currentlyPlayingEpisode.getDuration() - timePassed;
                    this.timestamp = newTimestamp;
                    this.lastEpisode.set(indexPodcast, indexEpisode + 1);
                    this.watchedTimeEpisode.set(indexPodcast, timePassed);
                    timePassed = timePassed - this.durationCurrentlyPlaying;
                    Update_Listens(this, artists, hosts);
                } else {
                    // Stop the Player
                    this.durationCurrentlyPlaying = 0;
                    this.currentlyPlayingEpisode = null;
                    this.currentlyPlayingPodcast = null;
                    this.currentlyPlaying = "";
                    this.timeRemaining = 0;
                    this.timestamp = newTimestamp;
                    this.isPaused = true;
                    return;
                }
            }
        } else if (this.repeat.equals("Repeat Current Song")) {
            while (timePassed >= 0) {
                // Decrease the time remaining
                this.timeRemaining = this.durationCurrentlyPlaying - timePassed;
                timePassed = timePassed - this.durationCurrentlyPlaying;
                Update_Listens(this, artists, hosts);
            }
        } else if (this.repeat.equals("Repeat All")) {
            while (timePassed >= 0) {
                // Get the index of the episode
                int indexEpisode = this.currentlyPlayingPodcast.getPodcastEpisodes()
                        .indexOf(this.currentlyPlayingEpisode);
                int indexPodcast = podcasts.indexOf(this.currentlyPlayingPodcast);
                if (indexEpisode + 1 < this.currentlyPlayingPodcast.getPodcastEpisodes().size()) {
                    // Get the next episode
                    this.currentlyPlayingEpisode = this.currentlyPlayingPodcast.
                            getPodcastEpisodes()
                            .get(indexEpisode + 1);
                    this.currentlyPlaying = this.currentlyPlayingEpisode.getName();
                    this.durationCurrentlyPlaying = this.currentlyPlayingEpisode.getDuration();
                    this.timeRemaining = this.currentlyPlayingEpisode.getDuration() - timePassed;
                    this.timestamp = newTimestamp;
                    this.lastEpisode.set(indexPodcast, indexEpisode + 1);
                    this.watchedTimeEpisode.set(indexPodcast, timePassed);
                    timePassed = timePassed - this.durationCurrentlyPlaying;
                    Update_Listens(this, artists, hosts);
                } else {
                    // Get the first episode
                    this.currentlyPlayingEpisode = this.currentlyPlayingPodcast.
                            getPodcastEpisodes().get(0);
                    this.currentlyPlaying = this.currentlyPlayingEpisode.getName();
                    this.durationCurrentlyPlaying = this.currentlyPlayingEpisode.getDuration();
                    this.timeRemaining = this.currentlyPlayingEpisode.getDuration() - timePassed;
                    this.timestamp = newTimestamp;
                    this.lastEpisode.set(indexPodcast, 0);
                    this.watchedTimeEpisode.set(indexPodcast, timePassed);
                    timePassed = timePassed - this.durationCurrentlyPlaying;
                    Update_Listens(this, artists, hosts);
                }
            }
        }
    }

    /**
     * Plays the playlist
     *
     * @param newTimestamp
     * @param timepassed
     * @param artists
     * @param hosts
     */
    private void playPlaylist(final int newTimestamp, final int timepassed, final ArrayList<Artist> artists, final ArrayList<Host> hosts) {
        int timePassed = timepassed;
        if (this.repeat.equals("No Repeat")) {
            while (timePassed >= 0) {
                if (!this.shuffle) {
                    // Get the index of the song
                    int indexSong = this.currentlyPlayingPlaylist.getSongsPlaylist()
                            .indexOf(this.currentlyPlayingSong);
                    if (indexSong + 1 < this.currentlyPlayingPlaylist.getSongsPlaylist().size()) {
                        // Get the next song
                        this.currentlyPlayingSong = this.currentlyPlayingPlaylist.
                                getSongsPlaylist()
                                .get(indexSong + 1);
                        this.currentlyPlaying = this.currentlyPlayingSong.getName();
                        this.durationCurrentlyPlaying = this.currentlyPlayingSong.getDuration();
                        this.timeRemaining = this.currentlyPlayingSong.getDuration() - timePassed;
                        this.timestamp = newTimestamp;
                        timePassed = timePassed - this.durationCurrentlyPlaying;
                        Update_Listens(this, artists, hosts);
                    } else {
                        // Stop the Player
                        this.currentlyPlayingSong = null;
                        this.currentlyPlayingPlaylist = null;
                        this.currentlyPlaying = "";
                        this.durationCurrentlyPlaying = 0;
                        this.shuffle = false;
                        this.originalIndices.clear();
                        this.shuffledIndices.clear();
                        this.timeRemaining = 0;
                        this.timestamp = newTimestamp;
                        this.isPaused = true;
                        return;
                    }
                } else {
                    // Get the index of the song
                    int indexSong = this.shuffledIndices.indexOf(
                            this.currentlyPlayingPlaylist.getSongsPlaylist().
                                    indexOf(this.currentlyPlayingSong));
                    if (indexSong + 1 < this.currentlyPlayingPlaylist.getSongsPlaylist().size()) {
                        // Get the next song
                        this.currentlyPlayingSong = this.currentlyPlayingPlaylist.
                                getSongsPlaylist()
                                .get(this.shuffledIndices.get(indexSong + 1));
                        this.currentlyPlaying = this.currentlyPlayingSong.getName();
                        this.durationCurrentlyPlaying = this.currentlyPlayingSong.getDuration();
                        this.timeRemaining = this.currentlyPlayingSong.getDuration() - timePassed;
                        this.timestamp = newTimestamp;
                        timePassed = timePassed - this.durationCurrentlyPlaying;
                        Update_Listens(this, artists, hosts);
                    } else {
                        // Stop the Player
                        this.currentlyPlayingSong = null;
                        this.currentlyPlayingPlaylist = null;
                        this.currentlyPlaying = "";
                        this.durationCurrentlyPlaying = 0;
                        this.shuffle = false;
                        this.originalIndices.clear();
                        this.shuffledIndices.clear();
                        this.timeRemaining = 0;
                        this.timestamp = newTimestamp;
                        this.isPaused = true;
                        return;
                    }
                }
            }
        } else if (this.repeat.equals("Repeat Current Song")) {
            // Decrease the time remaining
            while (timePassed >= 0) {
                this.timeRemaining = this.durationCurrentlyPlaying - timePassed;
                timePassed = timePassed - this.durationCurrentlyPlaying;
            }
        } else if (this.repeat.equals("Repeat All")) {
            while (timePassed >= 0) {
                if (!this.shuffle) {
                    // Get the index of the song
                    int indexSong = this.currentlyPlayingPlaylist.getSongsPlaylist()
                            .indexOf(this.currentlyPlayingSong);
                    if (indexSong + 1 < this.currentlyPlayingPlaylist.getSongsPlaylist().size()) {
                        // Get the next song
                        this.currentlyPlayingSong = this.currentlyPlayingPlaylist.getSongsPlaylist()
                                .get(indexSong + 1);
                        this.currentlyPlaying = this.currentlyPlayingSong.getName();
                        this.durationCurrentlyPlaying = this.currentlyPlayingSong.getDuration();
                        this.timeRemaining = this.currentlyPlayingSong.getDuration() - timePassed;
                        this.timestamp = newTimestamp;
                        timePassed = timePassed - this.durationCurrentlyPlaying;
                        Update_Listens(this, artists, hosts);
                    } else {
                        // Get the first song
                        this.currentlyPlayingSong = this.currentlyPlayingPlaylist.getSongsPlaylist()
                                .get(0);
                        this.currentlyPlaying = this.currentlyPlayingSong.getName();
                        this.durationCurrentlyPlaying = this.currentlyPlayingSong.getDuration();
                        this.timeRemaining = this.currentlyPlayingSong.getDuration() - timePassed;
                        this.timestamp = newTimestamp;
                        timePassed = timePassed - this.durationCurrentlyPlaying;
                        Update_Listens(this, artists, hosts);
                    }
                } else if (this.shuffle) {
                    // Get the index of the song
                    int indexSong = this.shuffledIndices.indexOf(this.currentlyPlayingPlaylist
                            .getSongsPlaylist().indexOf(this.currentlyPlayingSong));
                    if (indexSong + 1 < this.currentlyPlayingPlaylist.getSongsPlaylist().size()) {
                        // Get the next song
                        this.currentlyPlayingSong = this.currentlyPlayingPlaylist.getSongsPlaylist()
                                .get(this.shuffledIndices.get(indexSong + 1));
                        this.currentlyPlaying = this.currentlyPlayingSong.getName();
                        this.durationCurrentlyPlaying = this.currentlyPlayingSong.getDuration();
                        this.timeRemaining = this.currentlyPlayingSong.getDuration() - timePassed;
                        this.timestamp = newTimestamp;
                        timePassed = timePassed - this.durationCurrentlyPlaying;
                        Update_Listens(this, artists, hosts);
                    } else {
                        // Get the first song
                        this.currentlyPlayingSong = this.currentlyPlayingPlaylist.getSongsPlaylist()
                                .get(this.shuffledIndices.get(0));
                        this.currentlyPlaying = this.currentlyPlayingSong.getName();
                        this.durationCurrentlyPlaying = this.currentlyPlayingSong.getDuration();
                        this.timeRemaining = this.currentlyPlayingSong.getDuration() - timePassed;
                        this.timestamp = newTimestamp;
                        timePassed = timePassed - this.durationCurrentlyPlaying;
                        Update_Listens(this, artists, hosts);
                    }
                }
            }
        }
    }
    /**
     * Plays the song
     *
     * @param newTimestamp
     * @param timepassed
     * @param artists
     * @param hosts
     */
    private void playSong(final int newTimestamp, final int timepassed, final ArrayList<Artist> artists, final ArrayList<Host> hosts) {
        int timePassed = timepassed;
        if (this.repeat.equals("Repeat Once")) {
            // Decrease the time remaining
            this.repeat = "No Repeat";
            this.timeRemaining = this.durationCurrentlyPlaying - timePassed;
            this.timestamp = newTimestamp;
            Update_Listens(this, artists, hosts);
            return;
        } else if (this.repeat.equals("Repeat Infinite")) {
            // Decrease the time remaining
            while (timePassed >= 0) {
                this.timeRemaining = this.durationCurrentlyPlaying - timePassed;
                timePassed = timePassed - this.durationCurrentlyPlaying;
                Update_Listens(this, artists, hosts);
            }
            this.timestamp = newTimestamp;
            return;
        } else if (this.repeat.equals("No Repeat")) {
            // Stop the Player
            this.currentlyPlayingSong = null;
            this.durationCurrentlyPlaying = 0;
            this.currentlyPlayingPlaylist = null;
            this.currentlyPlayingEpisode = null;
            this.currentlyPlayingPodcast = null;
            this.currentlyPlayingSong = null;
            this.currentlyPlaying = "";
            this.timeRemaining = 0;
            this.timestamp = newTimestamp;
            this.isPaused = true;
        }
    }

    /**
     * Simulates the time passing for the player and stops it
     *
     * @param newTimestamp
     * @param podcasts
     */
    public void stopPlayer(final int newTimestamp, final ArrayList<Podcast> podcasts, final ArrayList<Artist> artists, final ArrayList<Host> hosts) {

        if (this.currentlyPlayingPodcast != null) {
            int timepassed = newTimestamp - this.timestamp;
            int indexEpisode = this.currentlyPlayingPodcast.getPodcastEpisodes().
                    indexOf(this.currentlyPlayingEpisode);
            int indexPodcast = podcasts.indexOf(this.currentlyPlayingPodcast);
            if (timepassed > this.timeRemaining) {
                timepassed = timepassed - this.timeRemaining;
                this.currentlyPlayingEpisode = this.currentlyPlayingPodcast.
                        getPodcastEpisodes().get(indexEpisode + 1);
                this.currentlyPlaying = this.currentlyPlayingEpisode.getName();
                this.durationCurrentlyPlaying = this.currentlyPlayingEpisode.getDuration();
                this.timeRemaining = this.currentlyPlayingEpisode.getDuration() - timepassed;
                this.shuffle = false;
                this.originalIndices.clear();
                this.shuffledIndices.clear();
                this.timestamp = newTimestamp;
                this.lastEpisode.set(indexPodcast, indexEpisode + 1);
                this.watchedTimeEpisode.set(indexPodcast, timepassed);
                return;
            }
        }
        this.currentlyPlayingSong = null;
        this.durationCurrentlyPlaying = 0;
        this.currentlyPlayingPlaylist = null;
        this.currentlyPlayingEpisode = null;
        this.currentlyPlayingPodcast = null;
        this.shuffle = false;
        this.originalIndices.clear();
        this.shuffledIndices.clear();
        this.currentlyPlayingSong = null;
        this.repeat = "No Repeat";
        this.currentlyPlaying = "";
        this.timeRemaining = 0;
        this.timestamp = newTimestamp;
        this.isPaused = true;
    }

    /**
     * Gets the preferred songs
     *
     * @return The preferred songs
     */
    public ArrayList<Song> getPrefferedSongs() {
        return prefferedSongs;
    }

    /**
     * Gets the connection status
     *
     * @return The connection status
     */
    public String getConnectionStatus() {
        return connectionStatus;
    }

    /**
     * Sets the connection status
     *
     * @param connectionStatus The connection status
     */
    public void setConnectionStatus(final String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    /**
     * Gets the current page
     *
     * @return The current page
     */
    public String getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets the current page
     *
     * @param currentPage The current page
     */
    public void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Gets the page owner
     *
     * @return The page owner
     */
    public String getPageOwner() {
        return pageOwner;
    }

    /**
     * Sets the page owner
     *
     * @param pageOwner The page owner
     */
    public void setPageOwner(final String pageOwner) {
        this.pageOwner = pageOwner;
    }

    /**
     * Gets the subscription type
     *
     * @return The subscription type
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * Sets the subscription type
     *
     * @param subscriptionType The subscription type
     */
    public void setSubscriptionType(final String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    /**
     * Updates the listens
     *
     * @param user
     * @param artists
     * @param hosts
     */
    public static void Update_Listens(final User user, final ArrayList<Artist> artists,
                                      final ArrayList<Host> hosts) {
        if (user.getCurrentlyPlayingPlaylist() != null) {
            for(Artist currArtist : artists) {
                if(currArtist.getName().equals(user.getCurrentlyPlayingPlaylist().getOwner())) {
                    SetStatsSong(user, currArtist);
                }
            }
        } else if (user.getCurrentlyPlayingEpisode() != null) {
            String podcast_owner = user.getCurrentlyPlayingPodcast().getOwner();
            for (Host currHost : hosts) {
                if (currHost.getName().equals(podcast_owner)) {
                    currHost.getStats().addTopEpisode(user.getCurrentlyPlayingEpisode().getName());
                    currHost.getStats().addListener(user.getUsername());
                }
            }
            user.getStats().addTopEpisode(user.getCurrentlyPlayingEpisode().getName());
        } else if (user.getCurrentlyPlayingSong() != null) {
            String song_owner = user.getCurrentlyPlayingSong().getArtist();
            for (Artist currArtist : artists) {
                if (currArtist.getName().equals(song_owner)) {
                    SetStatsSong(user, currArtist);
                    break;
                }
            }
        }
    }

    /**
     * Sets the stats for the song
     *
     * @param user
     * @param currArtist
     */
    private static void SetStatsSong(final User user, final Artist currArtist) {

        currArtist.getStats().addTopSong(user.getCurrentlyPlayingSong().getName());
        currArtist.getStats().addSong(user.getCurrentlyPlayingSong());
        currArtist.getStats().addListener(user.getUsername());
        currArtist.getStats().addTopFan(user.getUsername());
        if(user.getCurrentlyPlayingSong().getAlbum() != null) {
            currArtist.getStats().addTopAlbum(user.getCurrentlyPlayingSong().getAlbum());
            user.getStats().addTopAlbum(user.getCurrentlyPlayingSong().getAlbum());
        }
        user.getStats().addTopArtist(currArtist.getName());
        user.getStats().addTopGenre(user.getCurrentlyPlayingSong().getGenre());
        user.getStats().addTopSong(user.getCurrentlyPlayingSong().getName());
        user.addListenedSong(user.getCurrentlyPlayingSong(), user);
        user.addListenedSongFree(user.getCurrentlyPlayingSong(), user);
    }

    /**
     * Adds a song to the listened songs
     *
     * @param song
     * @param user
     */
    public void addListenedSong(final Song song, final User user) {
        if(user.getSubscriptionType().equals("PREMIUM")) {
            this.ListenedSongsPremium.add(song);
        }
    }

    /**
     * Adds a song to the listened songs on free
     *
     * @param song
     * @param user
     */
    public void addListenedSongFree(final Song song, final User user) {
        if(user.getSubscriptionType().equals("FREE")) {
            this.ListenedSongsFree.add(song);
        }
    }

    /**
     * Gets the listened songs premium
     *
     * @return The listened songs premium
     */
    public ArrayList<Song> getListenedSongsPremium() {
        return ListenedSongsPremium;
    }

    /**
     * Sets the listened songs premium
     *
     * @param listenedSongsPremium The listened songs premium
     */
    public void setListenedSongsPremium(final ArrayList<Song> listenedSongsPremium) {
        ListenedSongsPremium = listenedSongsPremium;
    }

    /**
     * Gets the listened songs free
     *
     * @return The listened songs free
     */
    public ArrayList<Song> getListenedSongsFree() {
        return ListenedSongsFree;
    }

    /**
     * Sets the listened songs free
     *
     * @param listenedSongsFree The listened songs free
     */
    public void setListenedSongsFree(final ArrayList<Song> listenedSongsFree) {
        ListenedSongsFree = listenedSongsFree;
    }

    /**
     * Gets the notifications
     *
     * @return The notifications
     */
    public ArrayList<LinkedHashMap<String, String>> getNotifications() {
        return notifications;
    }

    /**
     * Sets the notifications
     *
     * @param notifications The notifications
     */
    public void setNotifications(final ArrayList<LinkedHashMap<String, String>> notifications) {
        this.notifications = notifications;
    }

    /**
     * Notifies the user
     *
     * @param notification
     */
    public void update(final LinkedHashMap<String, String> notification) {
        this.notifications.add(notification);
    }

    /**
     * Gets the merch
     *
     * @return The merch
     */
    public ArrayList<String> getMerch() {
        return merch;
    }

    /**
     * Sets the merch
     *
     * @param merch The merch
     */
    public void setMerch(final ArrayList<String> merch) {
        this.merch = merch;
    }

    /**
     * Gets the song recommendations
     *
     * @return The song recommendations
     */
    public Song getSongRecommendations() {
        return songRecommendations;
    }

    /**
     * Sets the song recommendations
     *
     * @param songRecommendations The song recommendations
     */
    public void setSongRecommendations(final Song songRecommendations) {
        this.songRecommendations = songRecommendations;
    }

    /**
     * Gets the playlist recommendations
     *
     * @return The playlist recommendations
     */
    public String getPlaylistRecommendations() {
        return playlistRecommendations;
    }

    /**
     * Sets the playlist recommendations
     *
     * @param playlistRecommendations The playlist recommendations
     */
    public void setPlaylistRecommendations(final String playlistRecommendations) {
        this.playlistRecommendations = playlistRecommendations;
    }

    /**
     * Gets the previous pages
     *
     * @return The previous pages
     */
    public ArrayList<Page> getPreviousPages() {
        return previousPages;
    }

    /**
     * Sets the previous pages
     *
     * @param previousPages The previous pages
     */
    public void setPreviousPages(final ArrayList<Page> previousPages) {
        this.previousPages = previousPages;
    }

    /**
     * Gets the next pages
     *
     * @return The next pages
     */
    public ArrayList<Page> getNextPages() {
        return nextPages;
    }

    /**
     * Sets the next pages
     *
     * @param nextPages The next pages
     */
    public void setNextPages(final ArrayList<Page> nextPages) {
        this.nextPages = nextPages;
    }
}
