package me.liamhbest.core.utility;

import lombok.Getter;
import me.liamhbest.core.data.DataSystem;
import me.liamhbest.core.data.PlayerDataList;
import me.liamhbest.core.friends.FriendsManager;
import me.liamhbest.core.rank.RankManager;
import me.liamhbest.core.utility.privacy.PrivacyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class BlueDebrisPlayer {

    private final OfflinePlayer offlinePlayer;

    @Getter
    private final RankManager rankManager;

    @Getter
    private final MetadataManager metadataManager;

    @Getter
    private final FriendsManager friendsManager;

    @Getter
    private final PrivacyManager privacyManager;

    public BlueDebrisPlayer(OfflinePlayer offlinePlayer){
        this.offlinePlayer = offlinePlayer;
        this.rankManager = new RankManager(offlinePlayer);
        this.metadataManager = new MetadataManager(offlinePlayer);
        this.friendsManager = new FriendsManager(offlinePlayer);
        this.privacyManager = new PrivacyManager(offlinePlayer);
    }

    public Player getPlayer(){
        if (offlinePlayer.isOnline()){
            return Bukkit.getPlayer(offlinePlayer.getUniqueId());
        } else {
            return null;
        }
    }

    public void sendMessage(String message){
        if (getPlayer() == null) return;

        try {
            getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));

        } catch (NullPointerException ignored){

        }
    }

    public boolean hasJoinedServer(){
       return PlayerDataList.getUUIDList().contains(offlinePlayer.getUniqueId().toString());
    }

}
