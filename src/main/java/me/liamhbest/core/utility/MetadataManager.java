package me.liamhbest.core.utility;

import me.liamhbest.core.BlueDebrisCore;
import me.liamhbest.core.data.DataSystem;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MetadataManager {

    private final OfflinePlayer offlinePlayer;
    public MetadataManager(OfflinePlayer offlinePlayer){
        this.offlinePlayer = offlinePlayer;
    }

    public ChatChannel getChatChannel(){
        return ChatChannel.valueOf(DataSystem.getDataFile(offlinePlayer).getString("data.chat_channel"));
    }

    public void setChatChannel(ChatChannel chatChannel){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/PlayerData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        configuration.set("data.chat_channel", chatChannel.name());

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

}











