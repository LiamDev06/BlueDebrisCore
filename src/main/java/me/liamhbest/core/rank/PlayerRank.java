package me.liamhbest.core.rank;

import lombok.Getter;
import me.liamhbest.core.utility.CC;
import org.bukkit.ChatColor;

public enum PlayerRank {

    OWNER(ChatColor.RED.toString() + ChatColor.BOLD.toString(), CC.translate("&4&l[OWNER]"), CC.translate("&4&l[OWNER] "), true, "OWNER", CC.translate("&4&lOWNER")),
    ADMIN(ChatColor.RED.toString(), CC.translate("&c[ADMIN]"),CC.translate("&c[ADMIN] "), true, "ADMIN", CC.translate("&cADMIN")),
    BUILDER(ChatColor.DARK_AQUA.toString(), CC.translate("&3[BUILD]"),CC.translate("&3[BUILD] "), true, "BUILDER", CC.translate("&3BUILDER")),
    MODERATOR(ChatColor.DARK_GREEN.toString(), CC.translate("&2[MOD]"),CC.translate("&2[MOD] "), true, "MODERATOR", CC.translate("&2MODERATOR")),
    HELPER(ChatColor.DARK_AQUA.toString(), CC.translate("&3[HELPER]"),CC.translate("&3[HELPER] "), true, "HELPER", CC.translate("&eHELPER")),
    PARTNER(ChatColor.LIGHT_PURPLE.toString(), CC.translate("&d[PARTNER]"),CC.translate("&d[PARTNER] "), false, "PARTNER", CC.translate("&dPARTNER")),
    YOUTUBE(ChatColor.RED.toString(), CC.translate("&c[YOU&fTUBE&c]"),CC.translate("&c[YOU&fTUBE&c] "), false, "YOUTUBE", CC.translate("&cYOUTUBE")),
    TWITCH(ChatColor.DARK_PURPLE.toString(), CC.translate("&5[TWI&fCH&5]"),CC.translate("&5[TWI&fCH&5] "), false, "TWITCH", CC.translate("&5TWITCH")),
    DELUXE(ChatColor.GOLD.toString(), CC.translate("&6&l[DELUXE]&6"),CC.translate("&6&l[DELUXE]&6 "), false, "DELUXE", CC.translate("&6&lDELUXE")),
    PREMIUM_PLUS(ChatColor.AQUA.toString(), CC.translate("&b[PREMIUM&9%&b]"),CC.translate("&b[PREMIUM&9%&b] "), false, "PREMIUM+", CC.translate("&bPREMIUM%")),
    PREMIUM(ChatColor.AQUA.toString(), CC.translate("&b[PREMIUM]"),CC.translate("&b[PREMIUM] "), false, "PREMIUM", CC.translate("&bPREMIUM")),
    VIP_PLUS(ChatColor.GREEN.toString(), CC.translate("&a[VIP&9%&a]"),CC.translate("&a[VIP&9%&a] "), false, "VIP+", CC.translate("&VIP%")),
    VIP(ChatColor.GREEN.toString(), CC.translate("&a[VIP]"),CC.translate("&a[VIP] "), false, "VIP", CC.translate("&aVIP")),
    MEMBER(ChatColor.GREEN.toString(), CC.translate("&7[Member]"),CC.translate("&7[Member] "), false, "Member", CC.translate("&7Member"));

    @Getter
    private final String color;

    @Getter
    private final String prefix;

    @Getter
    private final String prefixWithSpace;

    @Getter
    private final boolean isStaffRank;

    @Getter
    private final String displayName;

    @Getter
    private final String displayNameWithColor;

    PlayerRank(String color, String prefix, String prefixWithSpace, boolean isStaffRank, String displayName, String displayNameWithColor){
        this.color = color;
        this.prefix = prefix;
        this.isStaffRank = isStaffRank;
        this.displayName = displayName;
        this.displayNameWithColor = displayNameWithColor;
        this.prefixWithSpace = prefixWithSpace;
    }

}
