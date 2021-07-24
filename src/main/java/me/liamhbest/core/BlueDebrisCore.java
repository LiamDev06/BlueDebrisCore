package me.liamhbest.core;

import lombok.Getter;
import lombok.SneakyThrows;
import me.liamhbest.core.commands.BuildWorldCommand;
import me.liamhbest.core.commands.ItemCommand;
import me.liamhbest.core.commands.LobbyCommand;
import me.liamhbest.core.commands.gmcommands.GmaCommand;
import me.liamhbest.core.commands.gmcommands.GmcCommand;
import me.liamhbest.core.commands.gmcommands.GmsCommand;
import me.liamhbest.core.commands.gmcommands.GmspCommand;
import me.liamhbest.core.data.DataSystem;
import me.liamhbest.core.data.PlayerDataList;
import me.liamhbest.core.friends.FriendsData;
import me.liamhbest.core.friends.FriendsJoinLeave;
import me.liamhbest.core.friends.command.FriendsCommand;
import me.liamhbest.core.manager.ChatManager;
import me.liamhbest.core.rank.RankCommand;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class BlueDebrisCore extends JavaPlugin {

    public static BlueDebrisCore instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void registerListeners(){
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new ChatManager(), this);
        pluginManager.registerEvents(new DataSystem(), this);
        pluginManager.registerEvents(new PlayerDataList(), this);
        pluginManager.registerEvents(new FriendsData(), this);
        pluginManager.registerEvents(new FriendsJoinLeave(), this);
    }

    public void registerCommands(){
        commandMap.register(pluginName, new RankCommand());
        commandMap.register(pluginName, new FriendsCommand());

        commandMap.register(pluginName, new ItemCommand());
        commandMap.register(pluginName, new BuildWorldCommand());
        commandMap.register(pluginName, new LobbyCommand());

        commandMap.register(pluginName, new GmcCommand());
        commandMap.register(pluginName, new GmsCommand());
        commandMap.register(pluginName, new GmaCommand());
        commandMap.register(pluginName, new GmspCommand());
    }


    @Getter
    private static CommandMap commandMap;
    private static final String pluginName = "BlueDebrisCore";

    @SneakyThrows
    public BlueDebrisCore(){
        final Server server = Bukkit.getServer();
        final Field bukkitCommandMap = server.getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        commandMap = (CommandMap) bukkitCommandMap.get(server);
    }

}
