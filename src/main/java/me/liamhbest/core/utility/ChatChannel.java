package me.liamhbest.core.utility;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ChatChannel {

    ALL("", Arrays.asList("a", "all", "main")),
    GROUP("&eGroup >&r", Arrays.asList("g", "group", "groupchat", "gchat")),
    PARTY("&aParty >&r", Arrays.asList("p", "party", "partychat", "pchat")),
    STAFF(CC.translate("&2Staff >&r"), Arrays.asList("s", "staff", "staffchat", "schat")),
    BUILDER(CC.translate("&3Build Team >&r"), Arrays.asList("build", "b", "buildteam", "build_team", "builder", "buildchat", "builderchat")),
    SENIOR_STAFF(CC.translate("&cSenior Staff &c&l>&r"), Arrays.asList("ss", "seniorstaff", "senior", "senior_staff", "sschat", "seniorchat", "seniorstaffteam"));

    @Getter
    private final String prefix;

    @Getter
    private final List<String> alias;

    ChatChannel(String prefix, List<String> alias){
        this.prefix = prefix;
        this.alias = alias;
    }

}
