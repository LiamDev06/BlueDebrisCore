package me.liamhbest.core.commands;

import me.liamhbest.core.utility.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class LobbyCommand extends Command {

    public LobbyCommand(){
        super("lobby", "Lobby Command", "/lobby", Arrays.asList("hub", "l", "mainhub", "mainlobby"));
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        player.sendMessage(CC.GRAY + "Sending you to the main lobby...");
        Location location = new Location(Bukkit.getWorld("lobby1"), 1, 65, 1);
        player.teleport(location);

        return false;
    }
}


















