package main.Commands;

import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

/**
 * Interface representing a command.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @param songs    The songs to execute the command on.
     * @param podcasts The podcasts to execute the command on.
     * @param users    The users to execute the command on.
     * @param artists  The artists to execute the command on.
     * @param hosts    The hosts to execute the command on.
     */
    void execute(ArrayList<Song> songs, ArrayList<Podcast> podcasts,
                 ArrayList<User> users, ArrayList<Artist> artists,
                 ArrayList<Host> hosts);

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    String getCommandName();

    /**
     * Gets the timestamp.
     *
     * @return The timestamp.
     */
    int getTimestamp();

    /**
     * Gets the user.
     *
     * @return The user.
     */
    String getUser();

    /**
     * Gets the results.
     *
     * @return The results.
     */
    Object getResults();
}
