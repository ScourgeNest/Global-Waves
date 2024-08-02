package main.Commands.Users.Hosts;

public class Announcement {
    private String name;
    private String owner;
    private String description;

    /**
     * Constructor for the announcement.
     *
     * @param name        The name of the announcement.
     * @param owner       The owner of the announcement.
     * @param description The description of the announcement.
     */
    public Announcement(final String name, final String owner, final String description) {
        this.name = name;
        this.owner = owner;
        this.description = description;
    }

    /**
     * Gets the name of the announcement.
     *
     * @return The name of the announcement.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the announcement.
     *
     * @param name The name of the announcement.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the owner of the announcement.
     *
     * @return The owner of the announcement.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the announcement.
     *
     * @param owner The owner of the announcement.
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     * Gets the description of the announcement.
     *
     * @return The description of the announcement.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the announcement.
     *
     * @param description The description of the announcement.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the announcement.
     *
     * @return A string representation of the announcement.
     */
    public String toString() {
        return this.name + ":\n\t" + this.description + "\n";
    }
}
