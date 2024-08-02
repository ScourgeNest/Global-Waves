package main.Media;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Podcast {
    private String name;
    private String owner;
    private ArrayList<Episode> podcastEpisodes;
    private ArrayList<String> episodes;
    private int id;

    /**
     * Constructor for Podcast
     *
     * @param name            the name of the podcast
     * @param owner           the owner of the podcast
     * @param podcastEpisodes the episodes of the podcast
     */
    public Podcast(final String name, final String owner,
                   final ArrayList<Episode> podcastEpisodes, final int id) {
        this.name = name;
        this.owner = owner;
        this.podcastEpisodes = podcastEpisodes;
        this.id = id;
        this.episodes = new ArrayList<String>();
        for (Episode episode : podcastEpisodes) {
            assert false;
            this.episodes.add(episode.getName());
        }
    }

    /**
     * Get the name of the podcast
     *
     * @return the name of the podcast
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the podcast
     *
     * @param name the name of the podcast
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the owner of the podcast
     *
     * @return the owner of the podcast
     */
    @JsonIgnore
    public String getOwner() {
        return owner;
    }

    /**
     * Set the owner of the podcast
     *
     * @param owner the owner of the podcast
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     * Get the episodes of the podcast
     *
     * @return the episodes of the podcast
     */
    @JsonIgnore
    public ArrayList<Episode> getPodcastEpisodes() {
        return podcastEpisodes;
    }

    /**
     * Set the episodes of the podcast
     *
     * @param podcastEpisodes the episodes of the podcast
     */
    public void setPodcastEpisodes(final ArrayList<Episode> podcastEpisodes) {
        this.podcastEpisodes = podcastEpisodes;
    }

    /**
     * Get the ID of the podcast
     *
     * @return the ID of the podcast
     */
    @JsonIgnore
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the podcast
     *
     * @param id the ID of the podcast
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Get the episodes of the podcast
     *
     * @return the episodes of the podcast
     */
    public ArrayList<String> getEpisodes() {
        return episodes;
    }

    /**
     * Set the episodes of the podcast
     *
     * @param episodes the episodes of the podcast
     */
    public void setEpisodes(final ArrayList<String> episodes) {
        this.episodes = episodes;
    }

    /**
     * Returns a string representation of the Podcast.
     *
     * @return A string representation of the Podcast.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(":\n\t[");
        int i = 0;
        for (Episode episode : this.podcastEpisodes) {
            sb.append(episode.toString());
            if (i != this.podcastEpisodes.size() - 1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("]");
        return sb.toString();
    }
}
