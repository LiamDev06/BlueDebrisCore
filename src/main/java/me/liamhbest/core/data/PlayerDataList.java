package me.liamhbest.core.data;

import me.liamhbest.core.BlueDebrisCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerDataList implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        File file = new File(BlueDebrisCore.instance.getDataFolder(), "player_list.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> uuidList = new ArrayList<>(configuration.getStringList("uuid_list"));
        String uuid = event.getPlayer().getUniqueId().toString();

        if (!uuidList.contains(uuid)) {
            uuidList.add(uuid);
        }

        configuration.set("uuid_list", uuidList);

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }


    public static ArrayList<String> getUUIDList(){
        File file = new File(BlueDebrisCore.instance.getDataFolder(), "player_list.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return (ArrayList<String>) configuration.getStringList("uuid_list");
    }

}
















