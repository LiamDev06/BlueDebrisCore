package me.liamhbest.core.commands.settings;

import me.liamhbest.core.utility.CC;
import me.liamhbest.core.utility.SoundManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class SettingsCommand extends BukkitCommand {

    public SettingsCommand(){
        super("settings");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        player.sendMessage(CC.GRAY + "Viewing settings menu...");

        return false;
    }
}














