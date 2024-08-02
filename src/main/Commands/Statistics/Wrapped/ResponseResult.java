package main.Commands.Statistics.Wrapped;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashMap;

public class ResponseResult extends ResponseWrapped {
    private String command = "wrapped";
    private String user;
    private int timestamp;
    private LinkedHashMap<String, Object> result;

    /**
     * Constructor for the wrapped command.
     *
     * @param user      The user.
     * @param timestamp The timestamp.
     * @param result    The result.
     */
    public ResponseResult(final String user, final int timestamp,
                          final LinkedHashMap<String, Object> result) {
        super(user, timestamp);
        this.user = user;
        this.timestamp = timestamp;
        this.result = result;
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    public LinkedHashMap<String, Object> getResult() {
        return result;
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
     * Sets the result.
     *
     * @param result The result.
     */
    public void setResult(final LinkedHashMap<String, Object> result) {
        this.result = result;
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
