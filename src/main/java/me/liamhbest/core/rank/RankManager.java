package me.liamhbest.core.rank;

import me.liamhbest.core.BlueDebrisCore;
import me.liamhbest.core.data.DataSystem;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class RankManager {

    private final OfflinePlayer offlinePlayer;
    public RankManager(OfflinePlayer offlinePlayer){
        this.offlinePlayer = offlinePlayer;
    }

    public PlayerRank getRank(){
        FileConfiguration playerConfig = DataSystem.getDataFile(offlinePlayer);
        return PlayerRank.valueOf(playerConfig.getString("rank.currentRank"));
    }

    public void setRank(PlayerRank playerRank){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/PlayerData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        configuration.set("rank.currentRank", playerRank.name());

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public PlayerRank getStoringRank(){
        FileConfiguration playerConfig = DataSystem.getDataFile(offlinePlayer);
        return PlayerRank.valueOf(playerConfig.getString("rank.storingRank"));
    }

    public void setSaveRank(PlayerRank playerRank){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/PlayerData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        configuration.set("rank.storingRank", playerRank.name());

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

}













