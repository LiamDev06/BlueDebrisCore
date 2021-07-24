package me.liamhbest.core.commands;

import me.liamhbest.core.BlueDebrisCore;
import me.liamhbest.core.rank.PlayerRank;
import me.liamhbest.core.utility.BlueDebrisPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BuildWorldCommand extends Command {

    public BuildWorldCommand(){
        super("buildworld");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        BlueDebrisPlayer blueDebrisPlayer = new BlueDebrisPlayer(player);

        if (blueDebrisPlayer.getRankManager().getRank() != PlayerRank.BUILDER
        && blueDebrisPlayer.getRankManager().getRank() != PlayerRank.ADMIN
        && blueDebrisPlayer.getRankManager().getRank() != PlayerRank.OWNER) {
            blueDebrisPlayer.sendMessage("&cYou must be a builder or higher to perform this!");
            return true;
        }

        blueDebrisPlayer.sendMessage("&7Sending you to the build world...");
        World buildWorld = Bukkit.getWorld("buildworld");
        Location buildWorldLocation = new Location(buildWorld, 1, 4, 0);
        player.teleport(buildWorldLocation);

        buildWorld.setPVP(false);
        buildWorld.setStorm(false);
        buildWorld.setThundering(false);
        buildWorld.setMonsterSpawnLimit(0);

        new BukkitRunnable(){
            @Override
            public void run(){
                player.setGameMode(GameMode.CREATIVE);
                player.setHealthScale(20);
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setFlying(true);
                player.setAllowFlight(true);
            }
        }.runTaskLater(BlueDebrisCore.instance, 20L);

        return false;
    }
}





















