package me.liamhbest.core.utility.privacy;

import lombok.Getter;

public enum FriendPrivacy {

    ALL("Everyone"),
    PARTY_MEMBERS("Only Party members"),
    GROUP_MEMBERS("Only Group member"),
    GROUP_AND_PARTY("Only Party + Group members"),
    ONLY_STAFF("Only from staff");

    @Getter
    private final String prefix;

    FriendPrivacy(String prefix){
        this.prefix = prefix;
    }

}
