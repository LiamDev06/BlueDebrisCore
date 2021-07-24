package me.liamhbest.core.commands.settings;

import me.liamhbest.core.rank.PlayerRank;
import me.liamhbest.core.utility.BlueDebrisPlayer;
import me.liamhbest.core.utility.CC;
import me.liamhbest.core.utility.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class SettingsGUI {

    public static HashMap<UUID, Inventory> settingsMenu = new HashMap<>();
    public static Inventory getSettingsMenu(Player player) {
        BlueDebrisPlayer blueDebrisPlayer = new BlueDebrisPlayer(player);
        Inventory inventory = Bukkit.createInventory(null, 54, "Settings");

        ItemStack joinNotification = new ItemStack(Material.NAME_TAG);
        ItemMeta joinNotifMeta = joinNotification.getItemMeta();
        ArrayList<String> joinNotifyLore = new ArrayList<>();
        if (blueDebrisPlayer.getRankManager().getRank() == PlayerRank.MEMBER) {
            joinNotifMeta.setDisplayName(CC.translate("&cLobby Join Message"));
            joinNotifyLore.add(CC.translate("&7Sends a message in the lobby when"));
            joinNotifyLore.add(CC.translate("&7you join the server. The message changes"));
            joinNotifyLore.add(CC.translate("&7depending on your current rank."));
            joinNotifyLore.add(" ");
            joinNotifyLore.add(CC.translate("&cThis requires " + PlayerRank.VIP.getDisplayNameWithColor() + " &cor above!"));

        } else {

        }
        joinNotifMeta.setLore(joinNotifyLore);
        joinNotification.setItemMeta(joinNotifMeta);

        settingsMenu.put(player.getUniqueId(), inventory);
        return inventory;
    }
}












