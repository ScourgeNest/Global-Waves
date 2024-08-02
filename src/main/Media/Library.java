package main.Media;

import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import main.TypeOfUsers.User;

import java.util.ArrayList;

import static main.Main.createSongs;
import static main.Main.createPodcasts;
import static main.Main.createUsers;


public final class Library {
    private static final Library INSTANCE = null;
    private static ArrayList<Song> songs;
    private static ArrayList<Song> defaultSongs;
    private static ArrayList<Podcast> podcasts;
    private static ArrayList<Podcast> defaultPodcasts;
    private static ArrayList<User> users;
    private static ArrayList<User> defaultUsers;

    /**
     * Constructor for the library.
     */
    private Library() {
        this.songs = new ArrayList<Song>();
        this.podcasts = new ArrayList<Podcast>();
        this.users = new ArrayList<User>();
    }

    /**
     * Loads the library.
     *
     * @param songsInput    The songs input.
     * @param podcastsInput The podcasts input.
     * @param usersInput    The users input.
     */
    public void loadLibrary(final ArrayList<SongInput> songsInput,
                            final ArrayList<PodcastInput> podcastsInput,
                            final ArrayList<UserInput> usersInput) {
        if (INSTANCE != null) {
            return;
        }
        // Creating the songs
        this.songs = createSongs(songsInput);

        this.defaultSongs = createSongs(songsInput);

        // Creating the podcasts
        this.podcasts = createPodcasts(podcastsInput);

        this.defaultPodcasts = createPodcasts(podcastsInput);

        // Creating the users
        this.users = createUsers(usersInput, podcasts);

        this.defaultUsers = createUsers(usersInput, podcasts);
    }

    /**
     * Gets the instance of the library.
     *
     * @return The instance of the library.
     */
    public static Library getInstance() {
        if (INSTANCE == null) {
            return new Library();
        }
        return INSTANCE;
    }

    /**
     * Adds a song to the library.
     *
     * @param song The song to be added.
     */
    public void addSongs(final Song song) {
        this.songs.add(song);
    }

    /**
     * Adds a podcast to the library.
     *
     * @param podcast The podcast to be added.
     */
    public void addPodcasts(final Podcast podcast) {
        this.podcasts.add(podcast);
    }

    /**
     * Adds a user to the library.
     *
     * @param user The user to be added.
     */
    public void addUsers(final User user) {
        this.users.add(user);
    }

    /**
     * Gets the songs from the library.
     *
     * @return The songs from the library.
     */
    public static ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Gets the podcasts from the library.
     *
     * @return The podcasts from the library.
     */
    public static ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    /**
     * Gets the users from the library.
     *
     * @return The users from the library.
     */
    public static ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets the songs from the library.
     *
     * @param songs The songs from the library.
     */
    public void setSongs(final ArrayList<Song> songs) {
        this.songs = songs;
    }

    /**
     * Sets the podcasts from the library.
     *
     * @param podcasts The podcasts from the library.
     */
    public void setPodcasts(final ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    /**
     * Sets the users from the library.
     *
     * @param users The users from the library.
     */
    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Resets the library.
     */
    public static void resetLibrary() {
        songs = defaultSongs;
        podcasts = defaultPodcasts;
        users = defaultUsers;
    }
}
