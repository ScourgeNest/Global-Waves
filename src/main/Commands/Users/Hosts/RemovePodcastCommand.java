package main.Commands.Users.Hosts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class RemovePodcastCommand implements Command {
    private String command = "removePodcast";
    private String user;
    private int timestamp;
    @JsonIgnore
    private String podcastName;
    private String message;

    /**
     * Constructor for the removePodcast command.
     *
     * @param user        The user.
     * @param timestamp   The timestamp.
     * @param podcastName The name of the podcast.
     */
    public RemovePodcastCommand(final String user, final int timestamp, final String podcastName) {
        this.user = user;
        this.timestamp = timestamp;
        this.podcastName = podcastName;
    }

    /**
     * Removes a podcast.
     *
     * @param users    The users to execute the command on.
     * @param artists  The artists to execute the command on.
     * @param hosts    The hosts to execute the command on.
     * @param podcasts The podcasts to execute the command on.
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

        boolean foundHost = false;
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                foundHost = true;
                boolean foundPodcast = false;
                for (Podcast currentPodcast : currentHost.getPodcasts()) {
                    if (currentPodcast.getName().equals(this.podcastName)) {
                        foundPodcast = true;
                        break;
                    }
                }
                if (!foundPodcast) {
                    this.message = this.user + " doesn't have a podcast with the given name.";
                    return;
                }
            }
        }

        if (!foundHost) {
            this.message = "The username " + this.user + " doesn't exist.";
            return;
        }

        for (User currentUser : users) {
            if (currentUser.getCurrentlyPlayingPodcast() == null) {
                continue;
            }
            if (currentUser.getCurrentlyPlayingPodcast().getName().equals(this.podcastName)) {
                this.message = this.user + " can't delete this podcast.";
                return;
            }
        }

        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(this.user)) {
                for (Podcast currentPodcast : currentHost.getPodcasts()) {
                    if (!currentPodcast.getName().equals(this.podcastName)) {
                        continue;
                    }
                    for (User currentUser : users) {
                        currentUser.getLastEpisode().remove(currentPodcast.getId());
                        currentUser.getWatchedTimeEpisode().remove(currentPodcast.getId());
                    }
                    for (Podcast currentPodcast2 : podcasts) {
                        if (currentPodcast2.getId() > currentPodcast.getId()) {
                            currentPodcast2.setId(currentPodcast2.getId() - 1);
                        }
                    }
                    podcasts.remove(currentPodcast);
                    currentHost.getPodcasts().remove(currentPodcast);
                    this.message = this.user + " deleted the podcast successfully.";
                    return;
                }
            }
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
     * Gets the name of the podcast.
     *
     * @return The name of the podcast.
     */
    public String getPodcastName() {
        return podcastName;
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
     * Gets the command name.
     *
     * @return The command name.
     */
    @Override
    @JsonIgnore
    public String getCommandName() {
        return "removePodcast";
    }
}
