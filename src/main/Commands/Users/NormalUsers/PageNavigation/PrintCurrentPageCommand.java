package main.Commands.Users.NormalUsers.PageNavigation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Commands.Users.Artists.Event;
import main.Commands.Users.Artists.Merch;
import main.Commands.Users.Hosts.Announcement;
import main.Media.Album;
import main.Media.Playlist;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class PrintCurrentPageCommand implements Command {

    private static final int MAX_OUTPUT = 5;
    private final String user;
    private final String command = "printCurrentPage";
    private final int timestamp;
    private String message;

    /**
     * Constructor for the printCurrentPage command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     */
    public PrintCurrentPageCommand(final String user, final int timestamp) {
        this.user = user;
        this.timestamp = timestamp;
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
     * Gets the command.
     *
     * @return The command.
     */
    public String getCommand() {
        return command;
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
        return command;
    }

    /**
     * Prints the current page.
     *
     * @param users   The users.
     * @param artists The artists.
     * @param hosts   The hosts.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getConnectionStatus().equals("offline")) {
                    this.message = this.user + " is offline.";
                    return;
                }
            }
        }
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                if (currentUser.getCurrentPage().equals("artistPage")) {
                    printArtistPage(artists, currentUser);
                } else if (currentUser.getCurrentPage().equals("hostPage")) {
                    printHostPage(hosts, currentUser);
                } else if (currentUser.getCurrentPage().equals("Home")) {
                    printHomePage(currentUser);
                } else if (currentUser.getCurrentPage().equals("LikedContent")) {
                    printLikenContentPage(currentUser);
                }
                return;
            }
        }
    }

    private void printArtistPage(final ArrayList<Artist> artists, final User currentUser) {
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(currentUser.getPageOwner())) {
                StringBuilder output = new StringBuilder();
                output.append("Albums:\n");
                if (currentArtist.getAlbums().isEmpty()) {
                    output.append("\t").append("[]").append("\n");
                }

                int totalAlbums = currentArtist.getAlbums().size();
                for (int i = 0; i < totalAlbums; i++) {
                    if (i == 0) {
                        output.append("\t[");
                    }
                    Album album = currentArtist.getAlbums().get(i);
                    output.append(album.getName());
                    if (i == totalAlbums - 1) {
                        output.append("]\n");
                    } else {
                        output.append(", ");
                    }
                }

                output.append("\n");

                output.append("Merch:\n");
                if (currentArtist.getMerch().isEmpty()) {
                    output.append("\t").append("[]").append("\n");
                }
                int totalMerch = currentArtist.getMerch().size();
                for (int i = 0; i < totalMerch; i++) {
                    if (i == 0) {
                        output.append("\t[");
                    }
                    Merch merch = currentArtist.getMerch().get(i);
                    output.append(merch.toString());
                    if (i == totalMerch - 1) {
                        output.append("]\n");
                    } else {
                        output.append(", ");
                    }
                }
                output.append("\n");

                output.append("Events:\n");
                if (currentArtist.getEvents().isEmpty()) {
                    output.append("\t").append("[]");
                }
                int totalEvents = currentArtist.getEvents().size();
                for (int i = 0; i < totalEvents; i++) {
                    if (i == 0) {
                        output.append("\t[");
                    }
                    Event event = currentArtist.getEvents().get(i);
                    output.append(event.toString());
                    if (i == totalEvents - 1) {
                        output.append("]");
                    } else {
                        output.append(", ");
                    }
                }
                this.message = output.toString();
            }
        }
    }

    private void printHostPage(final ArrayList<Host> hosts, final User currentUser) {
        for (Host currentHost : hosts) {
            if (currentHost.getName().equals(currentUser.getPageOwner())) {
                ArrayList<Podcast> podcasts = currentHost.getPodcasts();
                ArrayList<Announcement> announcements = currentHost.getAnnouncements();
                StringBuilder output = new StringBuilder();
                output.append("Podcasts:\n");
                if (podcasts.isEmpty()) {
                    output.append("\t").append("[]").append("\n");
                }
                int totalPodcasts = podcasts.size();
                for (int i = 0; i < totalPodcasts; i++) {
                    if (i == 0) {
                        output.append("\t[");
                    }
                    Podcast podcast = podcasts.get(i);
                    output.append(podcast.toString());
                    if (i == totalPodcasts - 1) {
                        output.append("\n]\n");
                    } else {
                        output.append("\n, ");
                    }
                }
                output.append("\n");

                output.append("Announcements:\n");
                if (announcements.isEmpty()) {
                    output.append("\t").append("[]");
                }
                int totalAnnouncements = announcements.size();
                for (int i = 0; i < totalAnnouncements; i++) {
                    if (i == 0) {
                        output.append("\t[");
                    }
                    Announcement announcement = announcements.get(i);
                    output.append(announcement.toString());
                    if (i == totalAnnouncements - 1) {
                        output.append("]");
                    } else {
                        output.append(", ");
                    }
                }
                this.message = output.toString();
            }
        }
    }

    private void printHomePage(final User currentUser) {
        ArrayList<String> songs = new ArrayList<>();
        ArrayList<Song> sortSongs = new ArrayList<>(currentUser.getPrefferedSongs());
        sortSongs.sort((o1, o2) -> {
            int likes1 = o1.getLikes();
            int likes2 = o2.getLikes();
            return likes2 - likes1;
        });
        // max 5 songs
        for (Song currentSong : sortSongs) {
            if (songs.size() == MAX_OUTPUT) {
                break;
            }
            songs.add(currentSong.getName());
        }
        ArrayList<String> playlists = new ArrayList<>();
        ArrayList<Playlist> sortPlaylists = new ArrayList<>(currentUser.getFollowedPlaylists());
        sortPlaylists.sort((o1, o2) -> {
            int likes1 = 0;
            for (Song song : o1.getSongsPlaylist()) {
                likes1 += song.getLikes();
            }
            int likes2 = 0;
            for (Song song : o2.getSongsPlaylist()) {
                likes2 += song.getLikes();
            }
            return likes2 - likes1;
        });
        // max 5 playlists
        for (Playlist currentPlaylist : sortPlaylists) {
            if (playlists.size() == MAX_OUTPUT) {
                break;
            }
            playlists.add(currentPlaylist.getName());
        }

        ArrayList<String> songsRecommendations = new ArrayList<>();
        if (currentUser.getSongRecommendations() != null) {
            songsRecommendations.add(currentUser.getSongRecommendations().getName());
        }

        ArrayList<String> playlistsRecommendations = new ArrayList<>();
        if (currentUser.getPlaylistRecommendations() != null) {
            playlistsRecommendations.add(currentUser.getPlaylistRecommendations());
        }

        this.message = "Liked songs:\n\t" + songs + "\n\nFollowed playlists:\n\t"
                + playlists + "\n\nSong recommendations:\n\t"
                + songsRecommendations + "\n\nPlaylists recommendations:\n\t"
                + playlistsRecommendations;
    }

    private void printLikenContentPage(final User currentUser) {
        StringBuilder output = new StringBuilder();
        output.append("Liked songs:\n");
        if (currentUser.getPrefferedSongs().isEmpty()) {
            output.append("\t").append("[]").append("\n");
        }
        int totalSongs = currentUser.getPrefferedSongs().size();
        for (int i = 0; i < totalSongs; i++) {
            if (i == 0) {
                output.append("\t[");
            }
            Song song = currentUser.getPrefferedSongs().get(i);
            output.append(song.toString());
            if (i == totalSongs - 1) {
                output.append("]\n");
            } else {
                output.append(", ");
            }
        }
        output.append("\n");

        output.append("Followed playlists:\n");
        if (currentUser.getFollowedPlaylists().isEmpty()) {
            output.append("\t").append("[]");
        }
        int totalPlaylists = currentUser.getFollowedPlaylists().size();
        for (int i = 0; i < totalPlaylists; i++) {
            if (i == 0) {
                output.append("\t[");
            }
            Playlist playlist = currentUser.getFollowedPlaylists().get(i);
            output.append(playlist.getName()).append(" - ").append(playlist.getOwner());
            if (i == totalPlaylists - 1) {
                output.append("]");
            } else {
                output.append(", ");
            }
        }
        this.message = output.toString();
    }
}
