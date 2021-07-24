package me.liamhbest.core.friends;

import me.liamhbest.core.BlueDebrisCore;
import me.liamhbest.core.utility.privacy.FriendPrivacy;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FriendsManager {

    private final OfflinePlayer offlinePlayer;
    public FriendsManager(OfflinePlayer offlinePlayer){
        this.offlinePlayer = offlinePlayer;
    }

    public List<String> getFriendsListUUID(){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getStringList("friends");
    }

    public List<String> getRequestsOutListUUID(){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getStringList("requests-out");
    }

    public List<String> getRequestsInListUUID(){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getStringList("requests-in");
    }

    //The player I AM getting a friend request
    public void addRequestIn(String goingInUUID){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> list = new ArrayList<>(configuration.getStringList("requests-in"));

        list.add(goingInUUID);
        configuration.set("requests-in", list);

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void removeRequestIn(String goingInUUID){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> list = new ArrayList<>(configuration.getStringList("requests-in"));

        list.remove(goingInUUID);
        configuration.set("requests-in", list);

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    //The player that I AM sending the request from
    public void addRequestOut(String goingOutUUID){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> list = new ArrayList<>(configuration.getStringList("requests-out"));

        list.add(goingOutUUID);
        configuration.set("requests-out", list);

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void removeRequestOut(String goingOutUUID){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> list = new ArrayList<>(configuration.getStringList("requests-out"));

        list.remove(goingOutUUID);
        configuration.set("requests-out", list);

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void addFriend(String friendUUID){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> list = new ArrayList<>(configuration.getStringList("friends"));

        list.add(friendUUID);
        configuration.set("friends", list);

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void removeFriend(String friendUUID){
        File file = new File(BlueDebrisCore.instance.getDataFolder() + "/FriendsData", offlinePlayer.getUniqueId().toString() + ".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> list = new ArrayList<>(configuration.getStringList("friends"));

        list.remove(friendUUID);
        configuration.set("friends", list);

        try {
            configuration.save(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

}












