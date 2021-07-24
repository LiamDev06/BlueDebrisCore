package me.liamhbest.core.friends;

import me.liamhbest.core.BlueDebrisCore;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FriendsData implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData",
                player.getUniqueId().toString() + ".yml"
        );

        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        configuration.set("playerName", player.getName());
        if (!file.exists()) {
            configuration.set("uuid", player.getUniqueId().toString());
            configuration.set("friends", new ArrayList<String>());
            configuration.set("requests-out", new ArrayList<String>());
            configuration.set("requests-in", new ArrayList<String>());
        }

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

}













