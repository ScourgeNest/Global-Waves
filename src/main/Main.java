package main;

import checker.Checker;
import checker.CheckerConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.input.EpisodeInput;
import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import main.Commands.AllCommands;
import main.Commands.Command;
import main.Commands.Factory_Pattern.CommandFactory;
import main.Commands.Statistics.Monetization.EndProgramCommand;
import main.Commands.Statistics.Wrapped.ResponseMessage;
import main.Commands.Statistics.Wrapped.ResponseResult;
import main.Media.Episode;
import main.Media.Podcast;
import main.Media.Song;
import main.TypeOfUsers.Artist;
import main.TypeOfUsers.Host;
import main.TypeOfUsers.User;
import main.Commands.CommandExecutor;
import main.Media.Library;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

import static main.Media.Library.resetLibrary;

/**
 * The entry point to this homework. It runs the checker that tests your
 * implentation.
 */
public final class Main {
    static final String LIBRARY_PATH = CheckerConstants.TESTS_PATH + "library/library.json";

    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().startsWith("library")) {
                continue;
            }
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }
        Checker.calculateScore();
    }

    /**
     * @param filePathInput  for input file
     * @param filePathOutput for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePathInput,
            final String filePathOutput) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LibraryInput library = objectMapper.readValue(new File(LIBRARY_PATH), LibraryInput.class);

        ArrayNode outputs = objectMapper.createArrayNode();

        // Getting the podcasts from the input file
        ArrayList<PodcastInput> podcastsInput = library.getPodcasts();

        // Getting the songs from the input file
        ArrayList<SongInput> songsInput = library.getSongs();

        // Getting the users from the input file
        ArrayList<UserInput> usersInput = library.getUsers();

        // Getting the commands from the input files
        ArrayList<AllCommands> inputCommands = objectMapper.readValue(
                new File(CheckerConstants.TESTS_PATH + filePathInput),
                new TypeReference<ArrayList<AllCommands>>() {
                });
        Library.getInstance().loadLibrary(songsInput, podcastsInput, usersInput);
        // Creating the artists and hosts
        ArrayList<Artist> artists = new ArrayList<Artist>();
        ArrayList<Host> hosts = new ArrayList<Host>();

        // Creating the commands
        ArrayList<Command> commands = new ArrayList<Command>();
        CommandExecutor executor = new CommandExecutor(commands, Library.getUsers(),
                Library.getPodcasts(), Library.getSongs(), artists,
                hosts);

        runCommands(commands, executor, inputCommands);

        resetLibrary();

        // Creating the outputs
        createOutput(commands, objectMapper, outputs);

        // Writing the outputs
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePathOutput), outputs);
    }

    /**
     * Runs the commands.
     *
     * @param commands     The commands.
     * @param executor     The executor.
     * @param allCommands  The all commands.
     */
    private static void runCommands(final ArrayList<Command> commands,
                                    final CommandExecutor executor,
                                    final ArrayList<AllCommands> allCommands) {

        CommandFactory factory = new CommandFactory();
        for (final AllCommands command : allCommands) {
            Command commandObject = factory.getCommand(command);
            if (commandObject != null) {
                playerTimestamp(commandObject, executor.getSongs(), executor.getPodcasts(),
                        executor.getUsers(), executor.getArtists(), executor.getHosts());
                commandObject.execute(executor.getSongs(), executor.getPodcasts(),
                        executor.getUsers(), executor.getArtists(), executor.getHosts());
                if (!verifyWrappedResponse(commandObject, commands)) {
                    commands.add(commandObject);
                }
            }
        }
        EndProgramCommand endProgram = new EndProgramCommand();
        endProgram.execute(executor.getSongs(), executor.getPodcasts(),
                executor.getUsers(), executor.getArtists(), executor.getHosts());
        commands.add(endProgram);
    }

    /**
     * Player timestamp.
     *
     * @param command  The command.
     * @param songs    The songs.
     * @param podcasts The podcasts.
     * @param users    The users.
     * @param artists  The artists.
     * @param hosts    The hosts.
     */
    private static void playerTimestamp(final Command command, final ArrayList<Song> songs,
                                        final ArrayList<Podcast> podcasts,
                                        final ArrayList<User> users,
                                        final ArrayList<Artist> artists,
                                        final ArrayList<Host> hosts) {
        for (User user : users) {
            user.player(command.getTimestamp(), podcasts, artists, hosts);
            if (command.getCommandName() != null) {
                if (command.getCommandName().equals("search") && command.getUser().
                        equals(user.getUsername())) {
                    user.stopPlayer(command.getTimestamp(), podcasts, artists, hosts);
                }
            }
        }
    }

    /**
     * Verify wrapped response.
     *
     * @param command  The command.
     * @param commands The commands.
     * @return True if the response is wrapped, false otherwise.
     */
    private static boolean verifyWrappedResponse(final Command command,
                                                 final ArrayList<Command> commands) {
        if (command.getCommandName() != null) {
            if (command.getCommandName().equals("wrappedmessageuser")) {
                commands.add(new ResponseMessage(command.getUser(), command.getTimestamp(),
                        "user"));
                return true;
            } else if (command.getCommandName().equals("wrappedmessageartist")) {
                commands.add(new ResponseMessage(command.getUser(), command.getTimestamp(),
                        "artist"));
                return true;
            } else if (command.getCommandName().equals("wrappedmessagehost")) {
                commands.add(new ResponseMessage(command.getUser(), command.getTimestamp(),
                        "host"));
                return true;
            } else if (command.getCommandName().equals("wrappeduser") || command.getCommandName().
                    equals("wrappedartist")
                    || command.getCommandName().equals("wrappedhost")) {
                commands.add(new ResponseResult(command.getUser(), command.getTimestamp(),
                        (LinkedHashMap<String, Object>) command.getResults()));
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Creates the podcasts.
     *
     * @param podcastsInput The podcasts input.
     * @return The podcasts.
     */
    public static ArrayList<Podcast> createPodcasts(final ArrayList<PodcastInput> podcastsInput) {
        ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
        int id = 0;
        for (PodcastInput podcast : podcastsInput) {
            ArrayList<Episode> episodes = new ArrayList<Episode>();
            for (EpisodeInput episode : podcast.getEpisodes()) {
                episodes.add(new Episode(episode.getName(), episode.getDuration(),
                        episode.getDescription()));
            }
            podcasts.add(new Podcast(podcast.getName(), podcast.getOwner(), episodes, id));
            id++;
        }
        return podcasts;
    }

    /**
     * Creates the songs.
     *
     * @param songsInput The songs input.
     * @return The songs.
     */
    public static ArrayList<Song> createSongs(final ArrayList<SongInput> songsInput) {
        ArrayList<Song> songs = new ArrayList<Song>();
        for (SongInput song : songsInput) {
            songs.add(new Song(song.getName(), song.getDuration(), song.getAlbum(),
                    song.getTags(), song.getLyrics(), song.getGenre(), song.getReleaseYear(),
                    song.getArtist()));
        }
        return songs;
    }

    /**
     * Creates the users.
     *
     * @param usersInput The users input.
     * @param podcasts   The podcasts.
     * @return The users.
     */
    public static ArrayList<User> createUsers(final ArrayList<UserInput> usersInput,
                                              final ArrayList<Podcast> podcasts) {
        ArrayList<User> users = new ArrayList<User>();
        for (UserInput user : usersInput) {
            users.add(new User(user.getUsername(), user.getAge(), user.getCity(), podcasts));
        }
        return users;
    }

    /**
     * Creates the output.
     *
     * @param commands     The commands.
     * @param objectMapper The object mapper.
     * @param outputs      The outputs.
     */
    private static void createOutput(final ArrayList<Command> commands,
                                     final ObjectMapper objectMapper, final ArrayNode outputs) {
        if (!commands.isEmpty()) {
            for (Command command : commands) {
                outputs.add(objectMapper.valueToTree(command));
            }
        }
    }
}
