package main.Media;

public class Episode {
    private String name;
    private Integer duration;
    private String description;

    /**
     * Constructor for Episode
     */
    public Episode() {

    }

    public Episode(final String name, final Integer duration, final String description) {
        this.name = name;
        this.duration = duration;
        this.description = description;
    }

    /**
     * Get the name of the episode
     *
     * @return the name of the episode
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the episode
     *
     * @param name the name of the episode
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the duration of the episode
     *
     * @return the duration of the episode
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Set the duration of the episode
     *
     * @param duration the duration of the episode
     */
    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    /**
     * Get the description of the episode
     *
     * @return the description of the episode
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the episode
     *
     * @param description the description of the episode
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the episode
     *
     * @return a string representation of the episode
     */
    public String toString() {
        return this.name + " - " + this.description;
    }
}
