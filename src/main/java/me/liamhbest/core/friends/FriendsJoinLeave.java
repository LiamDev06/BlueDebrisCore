package me.liamhbest.core.friends;

import me.liamhbest.core.BlueDebrisCore;
import me.liamhbest.core.utility.BlueDebrisPlayer;
import me.liamhbest.core.utility.CC;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class FriendsJoinLeave implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        BlueDebrisPlayer blueDebrisPlayer = new BlueDebrisPlayer(player);

        if (!blueDebrisPlayer.getFriendsManager().getRequestsInListUUID().isEmpty()) {
            new BukkitRunnable(){

                @Override
                public void run(){
                    blueDebrisPlayer.sendMessage("&bYou have pending friend requests! View them with &c/f requests&b!");
                }

            }.runTaskLater(BlueDebrisCore.instance, 20L);
        }

        for (String s : blueDebrisPlayer.getFriendsManager().getFriendsListUUID()){
            try {
                Player targetPlayer = Bukkit.getPlayer(UUID.fromString(s));
                targetPlayer.sendMessage(CC.translate("&a&l>> &fYour friend " +
                        blueDebrisPlayer.getRankManager().getRank().getPrefixWithSpace() +
                        player.getName() + " &fjoined the server!")
                );

            } catch (NullPointerException ignored){

            }
        }

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        BlueDebrisPlayer blueDebrisPlayer = new BlueDebrisPlayer(player);

        for (String s : blueDebrisPlayer.getFriendsManager().getFriendsListUUID()){
            try {
                Player targetPlayer = Bukkit.getPlayer(UUID.fromString(s));
                targetPlayer.sendMessage(CC.translate("&a&l>> &fYour friend " +
                        blueDebrisPlayer.getRankManager().getRank().getPrefixWithSpace() +
                        player.getName() + " &fleft the server!")
                );

            } catch (NullPointerException ignored){

            }
        }

    }

}























