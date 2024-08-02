package main.Commands.Users.Artists;

public class Event {
    private String name;
    private String description;
    private String date;
    private String owner;

    /**
     * Constructor for the event.
     *
     * @param name        The name of the event.
     * @param description The description of the event.
     * @param date        The date of the event.
     * @param owner       The owner of the event.
     */
    public Event(final String name, final String description,
                 final String date, final String owner) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.owner = owner;
    }

    /**
     * Gets the name of the event.
     *
     * @return The name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the event.
     *
     * @param name The name of the event.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the event.
     *
     * @param description The description of the event.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the date of the event.
     *
     * @return The date of the event.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the event.
     *
     * @param date The date of the event.
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Gets the owner of the event.
     *
     * @return The owner of the event.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the event.
     *
     * @param owner The owner of the event.
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return name + " - " + date + ":\n\t" + description;
    }
}
