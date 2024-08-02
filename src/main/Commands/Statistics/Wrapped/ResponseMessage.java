package main.Commands.Statistics.Wrapped;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseMessage extends ResponseWrapped {

    private String command = "wrapped";
    private String user;
    private int timestamp;
    private String message;
    private String type;

    /**
     * Constructor for the wrapped command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param type      The type of the command.
     */
    public ResponseMessage(final String user, final int timestamp, final String type) {
        super(user, timestamp);
        this.user = user;
        this.timestamp = timestamp;
        this.type = type;
        switch (type) {
            case "user":
                this.message = "No data to show for user " + user + ".";
                break;
            case "artist":
                this.message = "No data to show for artist " + user + ".";
                break;
            case "host":
                this.message = "No data to show for host " + user + ".";
                break;
            default:
                this.message = "BUM BICI";
                break;
        }
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the name of the command.
     *
     * @param command The name of the command.
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
     * Gets the message.
     *
     * @return The message.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets the type of the command.
     *
     * @return The type of the command.
     */
    @JsonIgnore
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the command.
     *
     * @param type The type of the command.
     */
    public void setType(final String type) {
        this.type = type;
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
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    @Override @JsonIgnore
    public String getCommandName() {
        return command;
    }
}
