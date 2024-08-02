package main.Commands.Users.Hosts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Episode;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class AddPodcastCommand implements Command {
    private String command = "addPodcast";
    private String user;
    private int timestamp;
    private String name;
    private ArrayList<Episode> episodes;
    private String message;

    /**
     * Constructor for the addPodcast command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param name      The name of the podcast.
     * @param episodes  The episodes of the podcast.
     */
    public AddPodcastCommand(final String user, final int timestamp, final String name,
                             final ArrayList<Episode> episodes) {
        this.user = user;
        this.timestamp = timestamp;
        this.name = name;
        this.episodes = episodes;
    }

    /**
     * Adds a podcast.
     *
     * @param hosts    The hosts to execute the command on.
     * @param users    The users to execute the command on.
     * @param podcasts The podcasts to execute the command on.
     * @param artists  The artists to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                this.message = this.user + " is not a host.";
                return;
            }
        }
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                this.message = this.user + " is not a host.";
                return;
            }
        }
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                for (Podcast currentPodcast : currentHost.getPodcasts()) {
                    if (currentPodcast.getName().equals(this.name)) {
                        this.message = this.user + " has another podcast with the same name.";
                        return;
                    }
                }
            }
        }
        for (Episode currentEpisode : this.episodes) {
            for (Episode currentEpisode2 : this.episodes) {
                if (currentEpisode.getName().equals(currentEpisode2.getName())
                        && currentEpisode != currentEpisode2) {
                    this.message = this.user + " has the same episode in this podcast.";
                    return;
                }
            }
        }
        int idPodcast = podcasts.size();
        Podcast newPodcast = new Podcast(this.name, this.user, this.episodes, idPodcast);
        for (User currentUser : users) {
            currentUser.getWatchedTimeEpisode().add(0);
            currentUser.getLastEpisode().add(0);
        }
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                currentHost.addPodcast(newPodcast);
            }
        }
        podcasts.add(newPodcast);
        this.message = this.user + " has added new podcast successfully.";
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
     * @return
     */
    @Override @JsonIgnore
    public Object getResults() {
        return null;
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
     * Gets the name.
     *
     * @return The name.
     */
    @JsonIgnore
    public String getName() {
        return name;
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
     * Sets the name.
     *
     * @param name The name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the episodes.
     *
     * @return The episodes.
     */
    @JsonIgnore
    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    /**
     * Sets the episodes.
     *
     * @param episodes The episodes.
     */
    public void setEpisodes(final ArrayList<Episode> episodes) {
        this.episodes = episodes;
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
    @Override
    @JsonIgnore
    public String getCommandName() {
        return "addPodcastCommand";
    }
}
