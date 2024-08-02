package main.TypeOfUsers;

import main.Commands.Users.Hosts.Announcement;
import main.Media.Podcast;
import main.TypeOfUsers.Stats_and_Builder_Pattern.WrappedStatistics;

import java.util.ArrayList;

import static main.TypeOfUsers.Artist.notifyUsers;

public class Host {
    private final String name;
    private final int age;
    private final String city;
    private ArrayList<Podcast> podcasts;
    private ArrayList<Announcement> announcements;
    private WrappedStatistics stats;
    private ArrayList<User> subscribers;

    /**
     * Constructor for the host.
     *
     * @param name The name of the host.
     * @param age  The age of the host.
     * @param city The city of the host.
     */
    public Host(final String name, final int age, final String city) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.subscribers = new ArrayList<User>();
        this.podcasts = new ArrayList<Podcast>();
        this.announcements = new ArrayList<Announcement>();
        this.stats = new WrappedStatistics.Builder().buildTopEpisode().buildListener().build();
    }

    /**
     * Adds an announcement to the host.
     *
     * @param announcement The announcement to be added.
     */
    public void addAnnouncement(final Announcement announcement) {
        this.announcements.add(announcement);
        notifyUsers("New Announcement",  "New Announcement from "
                + this.name + ".", this.subscribers);
    }

    /**
     * Adds a podcast to the host.
     *
     * @param podcast The podcast to be added.
     */
    public void addPodcast(final Podcast podcast) {
        this.podcasts.add(podcast);
        notifyUsers("New Podcast",  "New Podcast from " + this.name
                + ".", this.subscribers);
    }

    /**
     * Gets the name of the host.
     *
     * @return The name of the host.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the host.
     *
     * @return The age of the host.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the city of the host.
     *
     * @return The city of the host.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the podcasts of the host.
     *
     * @return The podcasts of the host.
     */
    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    /**
     * Sets the podcasts of the host.
     *
     * @param podcasts The podcasts of the host.
     */
    public void setPodcasts(final ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    /**
     * Gets the announcements of the host.
     *
     * @return The announcements of the host.
     */
    public ArrayList<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * Sets the announcements of the host.
     *
     * @param announcements The announcements of the host.
     */
    public void setAnnouncements(final ArrayList<Announcement> announcements) {
        this.announcements = announcements;
    }

    /**
     * Gets the stats of the host.
     *
     * @return The stats of the host.
     */
    public WrappedStatistics getStats() {
        return stats;
    }

    /**
     * Sets the stats of the host.
     *
     * @param stats The stats of the host.
     */
    public void setStats(final WrappedStatistics stats) {
        this.stats = stats;
    }

    /**
     * Gets the subscribers of the host.
     *
     * @return The subscribers of the host.
     */
    public ArrayList<User> getSubscribers() {
        return subscribers;
    }

    /**
     * Sets the subscribers of the host.
     *
     * @param subscribers The subscribers of the host.
     */
    public void setSubscribers(final ArrayList<User> subscribers) {
        this.subscribers = subscribers;
    }

    /**
     * Adds a subscriber to the host.
     *
     * @param subscriber The subscriber to be added.
     */
    public void addSubscriber(final User subscriber) {
        this.subscribers.add(subscriber);
    }
}
