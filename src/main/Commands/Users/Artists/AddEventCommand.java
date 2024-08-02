package main.Commands.Users.Artists;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Commands.Command;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;

import java.util.ArrayList;

public class AddEventCommand implements Command {

    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = 2023;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY_31 = 31;
    private static final int MAX_DAY_30 = 30;
    private static final int MAX_DAY_FEB = 28;
    private static final int MAX_DAY_FEB_LEAP = 29;
    private static final int FEB = 2;
    private static final int LEAP_YEAR_PERIOD_4 = 4;
    private static final int LEAP_YEAR_PERIOD_100 = 100;
    private static final int LEAP_YEAR_PERIOD_400 = 400;
    private static final int MAX_DATE_LENGTH = 10;
    private static final int MAX_DATE_COMPONENTS = 3;
    private static final int APRIL = 4;
    private static final int JUNE = 6;
    private static final int SEPTEMBER = 9;
    private static final int NOVEMBER = 11;


    private String command = "addEvent";
    private String user;
    private int timestamp;
    private String message;
    private String name;
    private String description;
    private String date;

    /**
     * Constructor for the addEvent command.
     *
     * @param user        The user.
     * @param timestamp   The timestamp.
     * @param name        The name of the event.
     * @param description The description of the event.
     * @param date        The date of the event.
     */
    public AddEventCommand(final String user, final int timestamp, final String name,
                           final String description, final String date) {
        this.user = user;
        this.timestamp = timestamp;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    /**
     * Adds an event.
     *
     * @param artists The artists to execute the command on.
     * @param hosts   The hosts to execute the command on.
     * @param users   The users to execute the command on.
     * @param songs   The songs to execute the command on.
     */
    public void execute(final ArrayList<Song> songs, final ArrayList<Podcast> podcasts,
                        final ArrayList<User> users, final ArrayList<Artist> artists,
                        final ArrayList<Host> hosts) {
        for (Artist currentArtist : artists) {
            if (currentArtist.getName().equals(this.user)) {
                for (Event currentEvent : currentArtist.getEvents()) {
                    if (currentEvent.getName().equals(this.name)) {
                        this.message = this.user + " has another event with the same name.";
                        return;
                    }
                }
                if (!verifyDate(this.date)) {
                    this.message = "Event for " + this.user + " does not have a valid date.";
                    return;
                }
                currentArtist.addEvent(this.name, this.description, this.date, this.user);
                this.message = this.user + " has added new event successfully.";
                return;
            }
        }
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(this.user)) {
                this.message = this.user + " is not an artist.";
                return;
            }
        }
        this.message = "The username " + this.user + " doesn't exist.";
    }

    /**
     * Verifies the date.
     *
     * @param dateToVerify The date.
     * @return True if the date is valid, false otherwise.
     */
    private boolean verifyDate(final String dateToVerify) {
        if (dateToVerify.length() != MAX_DATE_LENGTH) {
            return false;
        }

        String[] components = dateToVerify.split("-");
        if (components.length != MAX_DATE_COMPONENTS) {
            return false;
        }

        try {
            int day = Integer.parseInt(components[0]);
            int month = Integer.parseInt(components[1]);
            int year = Integer.parseInt(components[2]);

            if (year < MIN_YEAR || year > MAX_YEAR || month < MIN_MONTH || month > MAX_MONTH
                    || day < MIN_DAY || day > MAX_DAY_31) {
                return false;
            }

            if (month == FEB && day > MAX_DAY_FEB) {
                if ((year % LEAP_YEAR_PERIOD_4 == 0 && year % LEAP_YEAR_PERIOD_100 != 0)
                        || year % LEAP_YEAR_PERIOD_400 == 0) {
                    if (day > MAX_DAY_FEB_LEAP) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if ((month == APRIL || month == JUNE || month == SEPTEMBER
                        || month == NOVEMBER) && day > MAX_DAY_30) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
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
     * Gets the name.
     *
     * @return The name.
     */
    @JsonIgnore
    public String getName() {
        return name;
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
     * Gets the description.
     *
     * @return The description.
     */
    @JsonIgnore
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description The description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the date.
     *
     * @return The date.
     */
    @JsonIgnore
    public String getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date The date.
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @Override
    @JsonIgnore
    public String getCommandName() {
        return "addEvent";
    }
}
