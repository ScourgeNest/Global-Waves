package main.Commands;

import main.Media.Episode;
import main.Media.Song;

import java.util.ArrayList;
import java.util.Map;

public class AllCommands {
    private String command;
    private String username;
    private int timestamp;
    private String type;
    private Map<String, Object> filters;
    private int itemNumber;
    private int playlistId;
    private int seed;
    private String playlistName;
    private int age;
    private String city;
    private String name;
    private int releaseYear;
    private String description;
    private ArrayList<Song> songs;
    private String date;
    private int price;
    private ArrayList<Episode> episodes;
    private String nextPage;
    private String recommendationType;

    /**
     * Gets the command.
     *
     * @return The command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Gets the username.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
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
     * Gets the type.
     *
     * @return The type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the filters.
     *
     * @return The filters.
     */
    public Map<String, Object> getFilters() {
        return filters;
    }

    /**
     * Gets the item number.
     *
     * @return The item number.
     */
    public int getItemNumber() {
        return itemNumber;
    }

    /**
     * Gets the playlist id.
     *
     * @return The playlist id.
     */
    public int getPlaylistId() {
        return playlistId;
    }

    /**
     * Gets the seed.
     *
     * @return The seed.
     */
    public int getSeed() {
        return seed;
    }

    /**
     * Gets the playlist name.
     *
     * @return The playlist name.
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * Sets the command.
     *
     */
    public void setCommand(final String command) {
        this.command = command;
    }

    /**
     * Sets the username.
     *
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Sets the timestamp.
     *
     */
    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Sets the type.
     *
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Sets the filters.
     *
     */
    public void setFilters(final Map<String, Object> filters) {
        this.filters = filters;
    }

    /**
     * Sets the item number.
     *
     */
    public void setItemNumber(final int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Sets the playlist id.
     *
     */
    public void setPlaylistId(final int playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Sets the seed.
     *
     */
    public void setSeed(final int seed) {
        this.seed = seed;
    }

    /**
     * Sets the playlist name.
     *
     */
    public void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * Sets the age.
     *
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     */
    public void setAge(final int age) {
        this.age = age;
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
     * Sets the city.
     *
     */
    public void setCity(final String city) {
        this.city = city;
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
     * Sets the name.
     *
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the release year.
     *
     * @return The release year.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year.
     *
     */
    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the songs.
     *
     * @return The songs.
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the songs.
     *
     */
    public void setSongs(final ArrayList<Song> songs) {
        this.songs = songs;
    }

    /**
     * Gets the date.
     *
     * @return The date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Gets the price.
     *
     * @return The price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     */
    public void setPrice(final int price) {
        this.price = price;
    }

    /**
     * Gets the episodes.
     *
     * @return The episodes.
     */
    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    /**
     * Sets the episodes.
     *
     */
    public void setEpisodes(final ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    /**
     * Gets the next page.
     *
     * @return The next page.
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * Sets the next page.
     *
     */
    public void setNextPage(final String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * Gets the recommendation type.
     *
     * @return The recommendation type.
     */
    public String getRecommendationType() {
        return recommendationType;
    }

    /**
     * Sets the recommendation type.
     *
     */
    public void setRecommendationType(final String recommendationType) {
        this.recommendationType = recommendationType;
    }
}
