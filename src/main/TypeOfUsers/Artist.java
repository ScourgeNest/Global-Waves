package main.TypeOfUsers;

import main.Commands.Notifications.Observer;
import main.Commands.Users.Artists.Event;
import main.Commands.Users.Artists.Merch;
import main.Media.Album;
import main.Media.Song;
import main.TypeOfUsers.Stats_and_Builder_Pattern.WrappedStatistics;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Artist {
    private final String name;
    private final int age;
    private final String city;
    private ArrayList<Album> albums;
    private ArrayList<String> albumNames;
    private ArrayList<Merch> merch;
    private ArrayList<Event> events;
    private int likes = 0;
    private double songRevenue = 0.0;
    private double merchRevenue = 0.0;
    private String mostProfitableSong = "N/A";
    private WrappedStatistics stats;
    private ArrayList<User> subscribers;

    /**
     * Constructor for the artist.
     *
     * @param name The name of the artist.
     * @param age  The age of the artist.
     * @param city The city of the artist.
     */
    public Artist(final String name, final int age, final String city) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.albums = new ArrayList<Album>();
        this.events = new ArrayList<Event>();
        this.merch = new ArrayList<Merch>();
        this.albumNames = new ArrayList<String>();
        this.stats = new WrappedStatistics.Builder().buildTopAlbum().buildTopSong()
                .buildTopFan().buildListener().build();
        this.subscribers = new ArrayList<User>();
    }

    /**
     * Adds an album to the artist.
     *
     * @param albumName   The albumName of the album.
     * @param releaseYear The release year of the album.
     * @param description The description of the album.
     * @param owner       The owner of the album.
     * @param songs       The songs of the album.
     */
    public void addAlbum(final String albumName, final int releaseYear, final String description,
                         final String owner, final ArrayList<Song> songs) {
        this.albums.add(new Album(albumName, releaseYear, description, owner, songs));
        this.albumNames.add(albumName);
        notifyUsers("New Album", "New Album from " + this.name + ".", this.subscribers);
    }

    /**
     * Sends the notification to the subscribers.
     *
     * @param title       The title of the notification. (New Album, New Merchandise, New Event)
     * @param message     The message of the notification.
     * @param subscribers The subscribers of the artist.
     */
    public static void notifyUsers(final String title, final String message,
                                   final ArrayList<User> subscribers) {
        LinkedHashMap<String, String> notification = new LinkedHashMap<>();
        notification.put("name", title);
        notification.put("description", message);
        for (Observer subscriber : subscribers) {
            subscriber.update(notification);
        }
    }

    /**
     * Adds a merch to the artist.
     *
     * @param albumName   The albumName of the merch.
     * @param description The description of the merch.
     * @param price       The price of the merch.
     */
    public void addMerch(final String albumName, final String description, final int price) {
        this.merch.add(new Merch(albumName, description, price, this.name));
        notifyUsers("New Merchandise", "New Merchandise from " + this.name + ".", this.subscribers);
    }

    /**
     * Adds an event to the artist.
     *
     * @param albumName   The albumName of the event.
     * @param description The description of the event.
     * @param date        The date of the event.
     * @param owner       The owner of the event.
     */
    public void addEvent(final String albumName, final String description,
                         final String date, final String owner) {
        this.events.add(new Event(albumName, description, date, owner));
        notifyUsers("New Event", "New Event from " + this.name + ".", this.subscribers);
    }

    /**
     * Gets the name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age.
     *
     * @return The age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the city.
     *
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the albums.
     *
     * @return The albums.
     */
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    /**
     * Sets the albums.
     *
     * @param albums The albums.
     */
    public void setAlbums(final ArrayList<Album> albums) {
        this.albums = albums;
    }

    /**
     * Gets the merch.
     *
     * @return The merch.
     */
    public ArrayList<Merch> getMerch() {
        return merch;
    }

    /**
     * Sets the merch.
     *
     * @param merch The merch.
     */
    public void setMerch(final ArrayList<Merch> merch) {
        this.merch = merch;
    }

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Sets the events.
     *
     * @param events The events.
     */
    public void setEvents(final ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * Gets the album names.
     *
     * @return The album names.
     */
    public ArrayList<String> getAlbumNames() {
        return albumNames;
    }

    /**
     * Sets the album names.
     *
     * @param albumNames The album names.
     */
    public void setAlbumNames(final ArrayList<String> albumNames) {
        this.albumNames = albumNames;
    }

    /**
     * Gets the likes.
     *
     * @return The likes.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Sets the likes.
     *
     * @param likes The likes.
     */
    public void setLikes(final int likes) {
        this.likes = likes;
    }

    /**
     * Gets the stats.
     *
     * @return The stats.
     */
    public WrappedStatistics getStats() {
        return stats;
    }

    /**
     * Sets the stats.
     *
     * @param stats The stats.
     */
    public void setStats(final WrappedStatistics stats) {
        this.stats = stats;
    }

    /**
     * Gets the merch revenue.
     *
     * @return The merch revenue.
     */
    public double getMerchRevenue() {
        return merchRevenue;
    }

    /**
     * Sets the merch revenue.
     *
     * @param merchRevenue The merch revenue.
     */
    public void setMerchRevenue(final double merchRevenue) {
        this.merchRevenue = merchRevenue;
    }

    /**
     * Gets the song revenue.
     *
     * @return The song revenue.
     */
    public double getSongRevenue() {
        return songRevenue;
    }

    /**
     * Sets the song revenue.
     *
     * @param songRevenue The song revenue.
     */
    public void setSongRevenue(final double songRevenue) {
        this.songRevenue = songRevenue;
    }

    /**
     * Gets the most profitable song.
     *
     * @return The most profitable song.
     */
    public String getMostProfitableSong() {
        return mostProfitableSong;
    }

    /**
     * Sets the most profitable song.
     *
     * @param mostProfitableSong The most profitable song.
     */
    public void setMostProfitableSong(final String mostProfitableSong) {
        this.mostProfitableSong = mostProfitableSong;
    }

    /**
     * Gets the subscribers.
     *
     * @return The subscribers.
     */
    public ArrayList<User> getSubscribers() {
        return subscribers;
    }

    /**
     * Sets the subscribers.
     *
     * @param subscribers The subscribers.
     */
    public void setSubscribers(final ArrayList<User> subscribers) {
        this.subscribers = subscribers;
    }
}

