package me.liamhbest.core.friends.command;

import me.liamhbest.core.rank.PlayerRank;
import me.liamhbest.core.utility.BlueDebrisPlayer;
import me.liamhbest.core.utility.CC;
import me.liamhbest.core.utility.privacy.FriendPrivacy;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FriendsCommand extends Command {

    public FriendsCommand(){
        super("friends", "The main friend command", "/friends", Arrays.asList("friend", "f"));
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        BlueDebrisPlayer blueDebrisPlayer = new BlueDebrisPlayer(player);

        if (args.length == 0){
            //Help List
            player.sendMessage(CC.GRAY + CC.MENU_BAR + "-----------------------------");
            player.sendMessage(CC.translate("&b/f help &8- &7Sends the friends help list"));
            player.sendMessage(CC.translate("&b/f list <page> &8- &7View all your current friends"));
            player.sendMessage(CC.translate("&b/f <player> &8- &7Send a friend request to a player"));
            player.sendMessage(CC.translate("&b/f add <player> &8- &7Send a friend request to a player"));
            player.sendMessage(CC.translate("&b/f remove <player> &8- &7Remove a friend"));
            player.sendMessage(CC.translate("&b/f requests &8- &7View your current friend requests"));
            player.sendMessage(CC.translate("&b/f accept &8- &7Accept a friend request from a player"));
            player.sendMessage(CC.translate("&b/f deny &8- &7Deny a friend request from a player"));
            player.sendMessage(CC.GRAY + CC.MENU_BAR + "-----------------------------");
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            player.sendMessage(CC.GRAY + CC.MENU_BAR + "-----------------------------");
            player.sendMessage(CC.translate("&b/f help &8- &7Sends the friends help list"));
            player.sendMessage(CC.translate("&b/f list <page> &8- &7View all your current friends"));
            player.sendMessage(CC.translate("&b/f <player> &8- &7Send a friend request to a player"));
            player.sendMessage(CC.translate("&b/f add <player> &8- &7Send a friend request to a player"));
            player.sendMessage(CC.translate("&b/f remove <player> &8- &7Remove a friend"));
            player.sendMessage(CC.translate("&b/f requests &8- &7View your current friend requests"));
            player.sendMessage(CC.translate("&b/f accept &8- &7Accept a friend request from a player"));
            player.sendMessage(CC.translate("&b/f deny &8- &7Deny a friend request from a player"));
            player.sendMessage(CC.GRAY + CC.MENU_BAR + "-----------------------------");
            return true;
        }

        if (args[0].equalsIgnoreCase("list")) {
            if (blueDebrisPlayer.getFriendsManager().getFriendsListUUID().isEmpty()) {
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.sendMessage("&cYou do not have any friends! Add friends with /friend [IGN]");
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                return true;
            }

            if (args.length > 1){
                try {
                    int page = Integer.parseInt(args[1]);


                } catch (Exception exception){
                    blueDebrisPlayer.sendMessage("&cThat is not a valid page input!");
                }
                return true;
            }
            //Send friend list page 1
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            player.sendMessage(CC.translate("&b&lFriends List &a[Page 1]"));

            for (String uuid : blueDebrisPlayer.getFriendsManager().getFriendsListUUID()){
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
                player.sendMessage(CC.translate("&8- &4" + offlinePlayer.getName() + " &fis in main lobby"));
            }

            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");

            return true;
        }

        if (args[0].equalsIgnoreCase("accept")) {
            if (blueDebrisPlayer.getFriendsManager().getRequestsInListUUID().isEmpty()) {
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.sendMessage("&cYou do not have any pending requests!");
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                return true;
            }

            if (args.length > 1){
                OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[1]);
                BlueDebrisPlayer targetBluePlayer = new BlueDebrisPlayer(targetPlayer);

                if (targetBluePlayer.getFriendsManager().getRequestsOutListUUID().contains(player.getUniqueId().toString())) {
                    //yes request can be accepted
                    String playerRankPrefix = blueDebrisPlayer.getRankManager().getRank().getPrefixWithSpace();
                    String targetPlayerRankPrefix = targetBluePlayer.getRankManager().getRank().getPrefixWithSpace();

                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    blueDebrisPlayer.sendMessage("&bYou are now friends with " + targetPlayerRankPrefix + targetPlayer.getName());
                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    blueDebrisPlayer.getFriendsManager().removeRequestIn(targetPlayer.getUniqueId().toString());
                    blueDebrisPlayer.getFriendsManager().addFriend(targetPlayer.getUniqueId().toString());

                    targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    targetBluePlayer.sendMessage("&bYou are now friends with " + playerRankPrefix + player.getName());
                    targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    targetBluePlayer.getFriendsManager().removeRequestOut(player.getUniqueId().toString());
                    targetBluePlayer.getFriendsManager().addFriend(player.getUniqueId().toString());

                } else {
                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    blueDebrisPlayer.sendMessage("&cThis player does not have an active friend request to you!");
                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                }

            } else {
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.sendMessage("&cPlease specify a player to accept with /f accept [IGN]");
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            }

            return true;
        }

        if (args[0].equalsIgnoreCase("deny")) {
            if (blueDebrisPlayer.getFriendsManager().getRequestsInListUUID().isEmpty()) {
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.sendMessage("&cYou do not have any pending requests!");
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                return true;
            }

            if (args.length > 1){
                OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[1]);
                BlueDebrisPlayer targetBluePlayer = new BlueDebrisPlayer(targetPlayer);

                if (targetBluePlayer.getFriendsManager().getRequestsOutListUUID().contains(player.getUniqueId().toString())) {
                    //yes request can be denied
                    String playerRankPrefix = blueDebrisPlayer.getRankManager().getRank().getPrefixWithSpace();
                    String targetPlayerRankPrefix = targetBluePlayer.getRankManager().getRank().getPrefixWithSpace();

                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    blueDebrisPlayer.sendMessage("&bYou denied the friend request from " + targetPlayerRankPrefix + targetPlayer.getName());
                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    blueDebrisPlayer.getFriendsManager().removeRequestIn(targetPlayer.getUniqueId().toString());

                    targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    targetBluePlayer.sendMessage("&bYour friend request to " + playerRankPrefix + player.getName() + " &bgot denied.");
                    targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    targetBluePlayer.getFriendsManager().removeRequestOut(player.getUniqueId().toString());

                } else {
                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    blueDebrisPlayer.sendMessage("&cThis player does not have an active friend request to you!");
                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                }

            } else {
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.sendMessage("&cPlease specify a player to deny with /f deny [IGN]");
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            }

            return true;
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (blueDebrisPlayer.getFriendsManager().getFriendsListUUID().isEmpty()) {
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.sendMessage("&cYou do not have any friends yet!");
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                return true;
            }

            if (args.length > 1){
                OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[1]);
                BlueDebrisPlayer targetBluePlayer = new BlueDebrisPlayer(targetPlayer);
                String playerRankPrefix = blueDebrisPlayer.getRankManager().getRank().getPrefixWithSpace();
                String targetPlayerRankPrefix = targetBluePlayer.getRankManager().getRank().getPrefixWithSpace();

                if (blueDebrisPlayer.getFriendsManager().getFriendsListUUID()
                        .contains(targetPlayer.getUniqueId().toString())
                && targetBluePlayer.getFriendsManager().getFriendsListUUID()
                        .contains(player.getUniqueId().toString())) {

                    blueDebrisPlayer.sendMessage("&bYou removed " + targetPlayerRankPrefix + targetPlayer.getName() + " &bfrom your friend list. You are not friends anymore!");
                    targetBluePlayer.sendMessage(playerRankPrefix + player.getName() + " &bremoved you from their friend list. You are not friends anymore!");

                    blueDebrisPlayer.getFriendsManager().removeFriend(targetPlayer.getUniqueId().toString());
                    targetBluePlayer.getFriendsManager().removeFriend(player.getUniqueId().toString());

                } else {
                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                    blueDebrisPlayer.sendMessage("&cYou are not friends with this player!");
                    player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                }

            } else {
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.sendMessage("&cPlease specify a player to deny with /f remove [IGN]");
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            }

            return true;
        }

        if (args[0].equalsIgnoreCase("requests")) {
            player.sendMessage(CC.GOLD + "It's cool that you performed this command but liam is lazy so this is not done yet thanks.");
            return true;
        }

        if (args[0].equalsIgnoreCase("yoink")) {
            if (blueDebrisPlayer.getRankManager().getRank() != PlayerRank.ADMIN &&
            blueDebrisPlayer.getRankManager().getRank() != PlayerRank.OWNER) {
                blueDebrisPlayer.sendMessage("&cYou are not allowed to perform this command!");
                return true;
            }

            if (args.length > 1){
                OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[0]);
                BlueDebrisPlayer targetBluePlayer = new BlueDebrisPlayer(targetPlayer);
                String playerRankPrefix = blueDebrisPlayer.getRankManager().getRank().getPrefixWithSpace();
                String targetPlayerRankPrefix = targetBluePlayer.getRankManager().getRank().getPrefixWithSpace();

                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.sendMessage("&bYoink completed! You are now friends with " + targetPlayerRankPrefix + targetPlayer.getName());
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.getFriendsManager().removeRequestIn(targetPlayer.getUniqueId().toString());
                blueDebrisPlayer.getFriendsManager().addFriend(targetPlayer.getUniqueId().toString());

                targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                targetBluePlayer.sendMessage("&bYou are now friends with " + playerRankPrefix + player.getName() + " &bdue to them force adding you");
                targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                targetBluePlayer.getFriendsManager().removeRequestOut(player.getUniqueId().toString());
                targetBluePlayer.getFriendsManager().addFriend(player.getUniqueId().toString());

            } else {
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                blueDebrisPlayer.sendMessage("&cPlease specify someone to yoink!");
                player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            }

            return true;
        }

        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[0]);
        BlueDebrisPlayer targetBluePlayer = new BlueDebrisPlayer(targetPlayer);
        if (!targetBluePlayer.hasJoinedServer()) {
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            blueDebrisPlayer.sendMessage("&cThis player has never played on Blue Debris before!");
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            return true;
        }

        String playerRankPrefix = blueDebrisPlayer.getRankManager().getRank().getPrefixWithSpace();
        String targetPlayerRankPrefix = targetBluePlayer.getRankManager().getRank().getPrefixWithSpace();

        if (player.getUniqueId() == targetPlayer.getUniqueId()) {
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            blueDebrisPlayer.sendMessage("&cYou cannot friend request yourself!");
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            return true;
        }

        if (targetBluePlayer.getFriendsManager().getRequestsInListUUID().contains(player.getUniqueId().toString())
                || targetBluePlayer.getFriendsManager().getRequestsOutListUUID().contains(player.getUniqueId().toString())) {
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            blueDebrisPlayer.sendMessage("&cYou already have a pending friend request to this player!");
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            return true;
        }

        if (blueDebrisPlayer.getFriendsManager().getFriendsListUUID().contains(targetPlayer.getUniqueId().toString())) {
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            blueDebrisPlayer.sendMessage("&cYou are already friends with this player!");
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            return true;
        }

        if (blueDebrisPlayer.getFriendsManager().getRequestsOutListUUID().size() >= 10){
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            blueDebrisPlayer.sendMessage("&cYou can only have 10 outgoing request at the same time!");
            player.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            return true;
        }

        boolean canSendFriendRequest = false;
        if (targetBluePlayer.getPrivacyManager().getFriendsPrivacy().contains(FriendPrivacy.ALL)) {
            canSendFriendRequest = true;
        }

        if (canSendFriendRequest) {
            blueDebrisPlayer.getFriendsManager().addRequestOut(targetPlayer.getUniqueId().toString());
            targetBluePlayer.getFriendsManager().addRequestIn(player.getUniqueId().toString());

            blueDebrisPlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
            blueDebrisPlayer.sendMessage("&bYou sent a friend request to " +
                    targetPlayerRankPrefix + targetPlayer.getName());
            blueDebrisPlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");

            try {
                targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
                targetBluePlayer.sendMessage("&bYou received a friend request from " +
                        playerRankPrefix + player.getName());

                TextComponent accept = new TextComponent("[ACCEPT]");
                accept.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                accept.setBold(true);
                accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend accept " + player.getName()));
                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Accept friend request").color(net.md_5.bungee.api.ChatColor.GRAY)
                                .italic(true).create()));

                TextComponent deny = new TextComponent("[DENY]");
                deny.setColor(ChatColor.RED);
                deny.setBold(true);
                deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend deny " + player.getName()));
                deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Deny friend request").color(net.md_5.bungee.api.ChatColor.GRAY)
                                .italic(true).create()));

                TextComponent space = new TextComponent("         ");
                accept.addExtra(space);
                accept.addExtra(deny);

                targetBluePlayer.getPlayer().spigot().sendMessage(accept);
                targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");

            } catch (NullPointerException ignored){

            }

        } else {
            targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-------------------------------");
            targetBluePlayer.sendMessage("&cYou are not allowed to send friend requests to this player!");
            targetBluePlayer.sendMessage("&cThey need to lower their friend privacy first with &f/settings privacy");
            targetBluePlayer.sendMessage(CC.AQUA + CC.MENU_BAR + "-----------------------------");
        }

        return false;
    }
}






















