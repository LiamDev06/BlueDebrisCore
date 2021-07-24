package me.liamhbest.core.data;

import me.liamhbest.core.BlueDebrisCore;
import me.liamhbest.core.rank.PlayerRank;
import me.liamhbest.core.utility.ChatChannel;
import me.liamhbest.core.utility.privacy.FriendPrivacy;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class DataSystem implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        File playerDataFile = new File(BlueDebrisCore.instance.getDataFolder() + "/PlayerData", player.getUniqueId().toString() + ".yml");
        FileConfiguration playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);

        playerDataConfig.set("playerName", player.getName());
        playerDataConfig.set("lastLogin", System.currentTimeMillis());

        if (!playerDataFile.exists()) {
            playerDataConfig.set("uuid", player.getUniqueId().toString());
            playerDataConfig.set("firstLogin", System.currentTimeMillis());
            playerDataConfig.set("rank.currentRank", PlayerRank.MEMBER.name());
            playerDataConfig.set("rank.storingRank", "");
            playerDataConfig.set("data.networkLevel", 1);
            playerDataConfig.set("data.networkEXP", 0);
            playerDataConfig.set("data.chat_channel", ChatChannel.ALL.name());
            playerDataConfig.set("privacy.friends", FriendPrivacy.ALL.name());
            playerDataConfig.set("staff.buildmode", false);
            playerDataConfig.set("staff.notifymode", false);
        }

        try {
            playerDataConfig.save(playerDataFile);
        } catch (IOException exception){
            exception.printStackTrace();
        }

    }

    public static FileConfiguration getDataFile(OfflinePlayer player){
        String uuid = player.getUniqueId().toString();
        File dataFile = new File(BlueDebrisCore.instance.getDataFolder() + "/PlayerData", uuid + ".yml");
        return YamlConfiguration.loadConfiguration(dataFile);
    }

}















