package main.Commands.Users.NormalUsers.PageNavigation;

public class Page {

    private String name;
    private String owner;

    /**
     * Constructor for the page.
     *
     * @param name  The name of the page.
     * @param owner The owner of the page.
     */
    public Page(final String name, final String owner) {
        this.name = name;
        this.owner = owner;
    }

    /**
     * Gets the name of the page.
     *
     * @return The name of the page.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the owner of the page.
     *
     * @return The owner of the page.
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * Sets the name of the page.
     *
     * @param name The name of the page.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets the owner of the page.
     *
     * @param owner The owner of the page.
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }
}
