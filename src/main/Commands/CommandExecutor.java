package main.Commands;

import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class CommandExecutor {

    private ArrayList<Command> commands;
    private ArrayList<User> users;
    private ArrayList<Podcast> podcasts;
    private ArrayList<Song> songs;
    private ArrayList<Artist> artists;
    private ArrayList<Host> hosts;

    /**
     * Constructor for the command executor.
     *
     * @param commands The commands.
     * @param users    The users.
     * @param podcasts The podcasts.
     * @param songs    The songs.
     * @param artists  The artists.
     * @param hosts    The hosts.
     */
    public CommandExecutor(final ArrayList<Command> commands, final ArrayList<User> users,
                           final ArrayList<Podcast> podcasts, final ArrayList<Song> songs,
                           final ArrayList<Artist> artists, final ArrayList<Host> hosts) {
        this.commands = commands;
        this.users = users;
        this.podcasts = podcasts;
        this.songs = songs;
        this.artists = artists;
        this.hosts = hosts;
    }

    /**
     * Gets the commands.
     *
     * @return The commands.
     */
    public ArrayList<Command> getCommands() {
        return commands;
    }

    /**
     * Sets the commands.
     *
     * @param commands The commands.
     */
    public void setCommands(final ArrayList<Command> commands) {
        this.commands = commands;
    }

    /**
     * Gets the users.
     *
     * @return The users.
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets the users.
     *
     * @param users The users.
     */
    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Gets the podcasts.
     *
     * @return The podcasts.
     */
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
     * Gets the songs.
     *
     * @return The songs.
     */
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
     * Gets the artists.
     *
     * @return The artists.
     */
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
}
