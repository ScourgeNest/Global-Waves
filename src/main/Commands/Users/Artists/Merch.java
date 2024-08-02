package main.Commands.Users.Artists;

public class Merch {
    private String name;
    private String description;
    private double price;
    private String owner;

    /**
     * Constructor for the merch.
     *
     * @param name        The name of the merch.
     * @param description The description of the merch.
     * @param price       The price of the merch.
     * @param owner       The owner of the merch.
     */
    public Merch(final String name, final String description,
                 final double price, final String owner) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.owner = owner;
    }

    /**
     * Gets the name of the merch.
     *
     * @return The name of the merch.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the merch.
     *
     * @param name The name of the merch.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the description of the merch.
     *
     * @return The description of the merch.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the merch.
     *
     * @param description The description of the merch.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the price of the merch.
     *
     * @return The price of the merch.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the merch.
     *
     * @param price The price of the merch.
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * Gets the owner of the merch.
     *
     * @return The owner of the merch.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the merch.
     *
     * @param owner The owner of the merch.
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     * Returns a string representation of the merch.
     *
     * @return A string representation of the merch.
     */
    @Override
    public String toString() {
        return name + " - " + price + ":\n\t" + description;
    }
}
