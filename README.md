# Copyright 2024 Niculici Mihai-Daniel - 325CA
<div align="center"><img src="https://tenor.com/view/gigachad-gif-23352842.gif" width="500px"></div>

# Proiect GlobalWaves  - Etapa 3

<div align="center"><img src="https://tenor.com/view/listening-to-music-spongebob-gif-8009182.gif" width="300px"></div>

#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa3](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1)


## Skel Structure

## src/
- **fileio/**
    - **input/**
        - `EpisodeInput.java`: Handles input related to episodes.
        - `LibraryInput.java`: Handles input related to the library.
        - `PodcastInput.java`: Handles input related to podcasts.
        - `SongInput.java`: Handles input related to songs.
        - `UserInput.java`: Handles input related to users.

- **main/**
    - **Commands/**
        - `AllCommands.java`: Collection of all available commands.
        - `Command.java`: Base interface for all command classes.
        - `CommandExecutor.java`: Executes commands.
        - **Factory_Pattern/**
            - `CommandFactory.java`: Factory for creating commands.
        - **Notifications/**
            - `GetNotificationsCommand.java`: Retrieves notifications.
            - `Observer.java`: Observer interface for notifications.
            - `SubscribeCommand.java`: Subscribes to notifications.
        - **Player/**
            - `BackwardCommand.java`: Goes to the previous item in the playlist or media list.
            - `ForwardCommand.java`: Goes to the next item in the playlist or media list.
            - `LikeCommand.java`: Marks a song as liked or adds it to the liked songs list.
            - `LoadCommand.java`: Loads a playlist or media content.
            - `NextCommand.java`: Skips to the next item in the playlist or media list.
            - `PlayPauseCommand.java`: Toggles between play and pause state.
            - `PrevCommand.java`: Goes to the previous item in the playlist or media list.
            - `RepeatCommand.java`: Sets the repeat mode for the current playlist or song.
            - `ShuffleCommand.java`: Shuffles the current playlist.
            - `StatusCommand.java`: Displays the status of the player or current media.
        - **Playlist/**
            - `AddRemoveInPlaylistCommand.java`: Adds or removes a song from a playlist.
            - `CreatePlaylistCommand.java`: Creates a new playlist.
            - `FollowPlaylistCommand.java`: Follows a playlist to get updates.
            - `ShowPlaylistsCommand.java`: Displays the content of a playlist.
            - `SwitchVisibilityCommand.java`: Changes the visibility of a playlist.
        - **SearchBar/**
            - `SearchCommand.java`: Searches for media content.
            - `SelectCommand.java`: Selects a specific search result or item.
        - **Statistics/**
            - `GetTop5AlbumsCommand.java`: Retrieves the top 5 most popular albums.
            - `GetTop5ArtistsCommand.java`: Retrieves the top 5 most popular artists.
            - `GetTop5PlaylistsCommand.java`: Retrieves the top 5 most popular playlists.
            - `GetTop5SongsCommand.java`: Retrieves the top 5 most played or liked songs.
            - `ShowPreferredSongsCommand.java`: Displays the user's preferred songs or playlists.
        - **Users/**
            - `AddUserCommand.java`: Adds a new user to the system.
            - **Artists/**
                - `AddAlbumCommand.java`: Adds an album to an artist's collection.
                - `AddEventCommand.java`: Adds an event related to an artist.
                - `AddMerchCommand.java`: Adds merchandise related to an artist.
                - `Event.java`: Represents an event related to an artist.
                - `Merch.java`: Represents merchandise related to an artist.
                - `RemoveAlbumCommand.java`: Removes an album from an artist's collection.
                - `RemoveEventCommand.java`: Removes an event related to an artist.
                - `ShowAlbumsCommand.java`: Displays albums related to an artist.
            - `ChangePageCommand.java`: Changes the current page or view.
            - `DeleteUserCommand.java`: Deletes a user from the system.
            - `GetAllUsersCommand.java`: Retrieves all users in the system.
            - **Hosts/**
                - `AddAnnouncementCommand.java`: Adds an announcement by a host.
                - `AddPodcastCommand.java`: Adds a podcast by a host.
                - `Announcement.java`: Represents an announcement by a host.
                - `RemoveAnnouncementCommand.java`: Removes an announcement by a host.
                - `RemovePodcastCommand.java`: Removes a podcast by a host.
                - `ShowPodcastsCommand.java`: Displays podcasts by a host.
            - **NormalUsers/**
                - `GetOnlineUsersCommand.java`: Retrieves users who are currently online.
                - **PageNavigation/**
                    - `ChangePageCommand.java`: Changes the current page or view.
                    - `NextPageCommand.java`: Navigates to the next page.
                    - `Page.java`: Represents a page or view.
                    - `PreviousPageCommand.java`: Navigates to the previous page.
                    - `PrintCurrentPageCommand.java`: Prints the current page or view.
                - `SwitchConnectionStatusCommand.java`: Toggles the connection status of a user.

    - `Main.java`: Entry point to the application.

    - **Media/**
        - `Album.java`: Represents an album.
        - `Episode.java`: Represents an episode.
        - `Library.java`: Manages media in the library.
        - `Playlist.java`: Represents a playlist.
        - `Podcast.java`: Represents a podcast.
        - `Song.java`: Represents a song.

    - `Test.java`: Class for running tests.

    - **TypeOfUsers/**
        - `Artist.java`: Represents an artist user type.
        - `Host.java`: Represents a host user type.
        - **Stats_and_Builder_Pattern/**
            - `WrappedStatistics.java`: Represents wrapped statistics.
        - `User.java`: Represents a general user type.
- `input` - contains the tests and library in JSON format
- `ref` - contains all reference output for the tests in JSON format

<div align="center"><img src="https://tenor.com/view/mujikcboro-seriymujik-gif-24361533.gif" width="500px"></div>


## Implementation
    
### The action method performs several operations:

### Reading Input Data:

* It uses ObjectMapper to read a JSON file into a LibraryInput object containing songs, podcasts, users, etc.

### Data Transformation:
* It creates ArrayLists of podcasts, songs, users, and commands based on the input data, converting input entities to domain objects.

### Command Execution:
* It executes various commands (like search, select, load, etc.) based on the input commands received, interacting with users, songs, and podcasts accordingly.

### Command Creation and Execution:
* It iterates through the input commands, creates specific command objects, executes those commands on users, and records them in the commands list.

### Output Generation:
* Finally, it generates an output containing the executed commands in JSON format.
    
## Design
The design follows a procedural approach, reading input, transforming it into objects, executing commands sequentially, recording the commands, and generating an output.
    Commands are executed based on their type, interacting with users, songs, and podcasts.

<div align="center"><img src="https://tenor.com/view/giga-chad-gif-23143840.gif" width="500px"></div>

## Implementation Details
- In the User class are all the methods for Player.
- I made Library Singleton because I wanted to have only one instance of it.
- I used the Factory Pattern for creating commands.
- I used the Observer Pattern for notifications.
- I used the Builder Pattern for statistics.

<div align="center"><img src="https://tenor.com/view/pov-you-giga-chad-chad-meme-gif-25615024.gif" width="500px"></div>
