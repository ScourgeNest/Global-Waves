package main.Commands.Factory_Pattern;

import main.Commands.AllCommands;
import main.Commands.Command;
import main.Commands.Notifications.GetNotificationsCommand;
import main.Commands.Notifications.SubscribeCommand;
import main.Commands.Player.BackwardCommand;
import main.Commands.Player.ForwardCommand;
import main.Commands.Player.NextCommand;
import main.Commands.Player.PrevCommand;
import main.Commands.Player.PlayPauseCommand;
import main.Commands.Player.RepeatCommand;
import main.Commands.Player.ShuffleCommand;
import main.Commands.Player.StatusCommand;
import main.Commands.Player.LoadCommand;
import main.Commands.Player.LikeCommand;
import main.Commands.Playlist.AddRemoveInPlaylistCommand;
import main.Commands.Playlist.CreatePlaylistCommand;
import main.Commands.Playlist.FollowPlaylistCommand;
import main.Commands.Playlist.ShowPlaylistsCommand;
import main.Commands.Playlist.SwitchVisibilityCommand;
import main.Commands.SearchBar.SearchCommand;
import main.Commands.SearchBar.SelectCommand;
import main.Commands.Statistics.GetTop5AlbumsCommand;
import main.Commands.Statistics.GetTop5ArtistsCommand;
import main.Commands.Statistics.GetTop5PlaylistsCommand;
import main.Commands.Statistics.GetTop5SongsCommand;
import main.Commands.Statistics.UpdateRecommendationsCommand;
import main.Commands.Statistics.LoadRecommendationsCommand;
import main.Commands.Statistics.ShowPreferredSongsCommand;
import main.Commands.Statistics.Monetization.AdBreakCommand;
import main.Commands.Statistics.Monetization.BuyMerchCommand;
import main.Commands.Statistics.Monetization.BuyPremiumCommand;
import main.Commands.Statistics.Monetization.CancelPremiumCommand;
import main.Commands.Statistics.Monetization.SeeMerchCommand;
import main.Commands.Statistics.Wrapped.WrappedCommand;
import main.Commands.Users.AddUserCommand;
import main.Commands.Users.DeleteUserCommand;
import main.Commands.Users.GetAllUsersCommand;
import main.Commands.Users.Artists.AddAlbumCommand;
import main.Commands.Users.Artists.AddEventCommand;
import main.Commands.Users.Artists.AddMerchCommand;
import main.Commands.Users.Artists.RemoveAlbumCommand;
import main.Commands.Users.Artists.RemoveEventCommand;
import main.Commands.Users.Artists.ShowAlbumsCommand;
import main.Commands.Users.Hosts.AddAnnouncementCommand;
import main.Commands.Users.Hosts.AddPodcastCommand;
import main.Commands.Users.Hosts.RemoveAnnouncementCommand;
import main.Commands.Users.Hosts.RemovePodcastCommand;
import main.Commands.Users.Hosts.ShowPodcastsCommand;
import main.Commands.Users.NormalUsers.GetOnlineUsersCommand;
import main.Commands.Users.NormalUsers.SwitchConnectionStatusCommand;
import main.Commands.Users.NormalUsers.PageNavigation.ChangePageCommand;
import main.Commands.Users.NormalUsers.PageNavigation.NextPageCommand;
import main.Commands.Users.NormalUsers.PageNavigation.PreviousPageCommand;
import main.Commands.Users.NormalUsers.PageNavigation.PrintCurrentPageCommand;

public class CommandFactory {
    /**
     * Creates the command based on the command name.
     *
     * @param command The command.
     * @return The command.
     */
    public Command getCommand(final AllCommands command) {
        if (command.getCommand() == null) {
            return null;
        }

        return switch (command.getCommand()) {
            case "search" -> new SearchCommand(command.getUsername(),
                    command.getTimestamp(), command.getType(), command.getFilters());
            case "select" -> new SelectCommand(command.getUsername(),
                    command.getTimestamp(), command.getItemNumber());
            case "load" -> new LoadCommand(command.getUsername(),
                    command.getTimestamp());
            case "playPause" -> new PlayPauseCommand(command.getUsername(),
                    command.getTimestamp());
            case "status" -> new StatusCommand(command.getUsername(),
                    command.getTimestamp());
            case "createPlaylist" -> new CreatePlaylistCommand(command.getUsername(),
                    command.getPlaylistName(), command.getTimestamp());
            case "addRemoveInPlaylist" -> new AddRemoveInPlaylistCommand(command.getUsername(),
                    command.getTimestamp(), command.getPlaylistId());
            case "like" -> new LikeCommand(command.getUsername(),
                    command.getTimestamp());
            case "showPlaylists" -> new ShowPlaylistsCommand(command.getUsername(),
                    command.getTimestamp());
            case "showPreferredSongs" -> new ShowPreferredSongsCommand(command.getUsername(),
                    command.getTimestamp());
            case "repeat" -> new RepeatCommand(command.getUsername(),
                    command.getTimestamp());
            case "shuffle" -> new ShuffleCommand(command.getUsername(),
                    command.getTimestamp(), command.getSeed());
            case "next" -> new NextCommand(command.getUsername(),
                    command.getTimestamp());
            case "prev" -> new PrevCommand(command.getUsername(),
                    command.getTimestamp());
            case "forward" -> new ForwardCommand(command.getUsername(),
                    command.getTimestamp());
            case "backward" -> new BackwardCommand(command.getUsername(),
                    command.getTimestamp());
            case "follow" -> new FollowPlaylistCommand(command.getUsername(),
                    command.getTimestamp());
            case "switchVisibility" -> new SwitchVisibilityCommand(command.getUsername(),
                    command.getTimestamp(), command.getPlaylistId());
            case "getTop5Playlists" -> new GetTop5PlaylistsCommand(command.getTimestamp());
            case "getTop5Songs" -> new GetTop5SongsCommand(command.getTimestamp());
            case "changePage" -> new ChangePageCommand(command.getUsername(),
                    command.getTimestamp(), command.getNextPage());
            case "printCurrentPage" -> new PrintCurrentPageCommand(command.getUsername(),
                    command.getTimestamp());
            case "addUser" -> new AddUserCommand(command.getUsername(),
                    command.getTimestamp(), command.getType(), command.getAge(),
                    command.getCity());
            case "deleteUser" -> new DeleteUserCommand(command.getUsername(),
                    command.getTimestamp());
            case "showAlbums" -> new ShowAlbumsCommand(command.getUsername(),
                    command.getTimestamp());
            case "showPodcasts" -> new ShowPodcastsCommand(command.getUsername(),
                    command.getTimestamp());
            case "addAlbum" -> new AddAlbumCommand(command.getUsername(),
                    command.getTimestamp(), command.getName(), command.getReleaseYear(),
                    command.getDescription(), command.getSongs());
            case "removeAlbum" -> new RemoveAlbumCommand(command.getUsername(),
                    command.getTimestamp(), command.getName());
            case "addEvent" -> new AddEventCommand(command.getUsername(),
                    command.getTimestamp(), command.getName(), command.getDescription(),
                    command.getDate());
            case "removeEvent" -> new RemoveEventCommand(command.getUsername(),
                    command.getTimestamp(), command.getName());
            case "addMerch" -> new AddMerchCommand(command.getUsername(),
                    command.getTimestamp(), command.getName(), command.getDescription(),
                    command.getPrice());
            case "addPodcast" -> new AddPodcastCommand(command.getUsername(),
                    command.getTimestamp(), command.getName(), command.getEpisodes());
            case "removePodcast" -> new RemovePodcastCommand(command.getUsername(),
                    command.getTimestamp(), command.getName());
            case "addAnnouncement" -> new AddAnnouncementCommand(command.getUsername(),
                    command.getTimestamp(), command.getName(), command.getDescription());
            case "removeAnnouncement" -> new RemoveAnnouncementCommand(command.getUsername(),
                    command.getTimestamp(), command.getName());
            case "switchConnectionStatus" ->
                    new SwitchConnectionStatusCommand(command.getUsername(),
                    command.getTimestamp());
            case "getTop5Albums" -> new GetTop5AlbumsCommand(command.getTimestamp());
            case "getTop5Artists" -> new GetTop5ArtistsCommand(command.getTimestamp());
            case "getAllUsers" -> new GetAllUsersCommand(command.getTimestamp());
            case "getOnlineUsers" -> new GetOnlineUsersCommand(command.getTimestamp());
            case "wrapped" -> new WrappedCommand(command.getUsername(),
                    command.getTimestamp());
            case "buyPremium" -> new BuyPremiumCommand(command.getUsername(),
                    command.getTimestamp());
            case "cancelPremium" -> new CancelPremiumCommand(command.getUsername(),
                    command.getTimestamp());
            case "adBreak" -> new AdBreakCommand(command.getUsername(),
                    command.getTimestamp(), command.getPrice());
            case "subscribe" -> new SubscribeCommand(command.getUsername(),
                    command.getTimestamp());
            case "getNotifications" -> new GetNotificationsCommand(command.getUsername(),
                    command.getTimestamp());
            case "buyMerch" -> new BuyMerchCommand(command.getUsername(),
                    command.getName(), command.getTimestamp());
            case "seeMerch" -> new SeeMerchCommand(command.getUsername(),
                    command.getTimestamp());
            case "updateRecommendations" -> new UpdateRecommendationsCommand(command.getUsername(),
                    command.getTimestamp(), command.getRecommendationType());
            case "previousPage" -> new PreviousPageCommand(command.getUsername(),
                    command.getTimestamp());
            case "nextPage" -> new NextPageCommand(command.getUsername(),
                    command.getTimestamp());
            case "loadRecommendations" -> new LoadRecommendationsCommand(command.getUsername(),
                    command.getTimestamp());
            default -> null;
        };
    }
}
