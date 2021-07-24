package me.liamhbest.core.manager;

import me.liamhbest.core.utility.BlueDebrisPlayer;
import me.liamhbest.core.utility.CC;
import me.liamhbest.core.utility.ChatChannel;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatManager implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        BlueDebrisPlayer blueDebrisPlayer = new BlueDebrisPlayer(player);
        String[] words = event.getMessage().split(" ");
        List<Player> worldPlayers = player.getWorld().getPlayers();
        ArrayList<String> mentionedPlayers = new ArrayList<>();
        //Did you know that liamhbest and notmaixe are very cool

        event.setCancelled(true);

        for (String s : words){
            if (worldPlayers.contains(Bukkit.getPlayer(s))) {
                mentionedPlayers.add(s);
            }
        }

        StringBuilder message = new StringBuilder();

        if (blueDebrisPlayer.getMetadataManager().getChatChannel() == ChatChannel.ALL) {
            for (Player targetPlayer : player.getWorld().getPlayers()){

                if (blueDebrisPlayer.getRankManager().getRank().isStaffRank()) {
                    targetPlayer.sendMessage(blueDebrisPlayer.getRankManager().getRank()
                            .getPrefixWithSpace() + player.getName() + " " +
                            CC.translate("&f&l>&r " + event.getMessage())
                    );

                } else {
                    targetPlayer.sendMessage(blueDebrisPlayer.getRankManager().getRank()
                            .getPrefixWithSpace() + player.getName() + " " +
                            CC.translate("&f&l>&r ") + event.getMessage()
                    );

                }

            }
        }

    }

}















