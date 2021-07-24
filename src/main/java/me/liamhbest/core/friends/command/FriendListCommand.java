package me.liamhbest.core.friends.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FriendListCommand extends Command {

    public FriendListCommand(){
        super("fl");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;



        return false;
    }
}






















