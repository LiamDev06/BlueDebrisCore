package me.liamhbest.core.utility.privacy;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;

public class PrivacyManager {

    private final OfflinePlayer offlinePlayer;
    public PrivacyManager(OfflinePlayer offlinePlayer){
        this.offlinePlayer = offlinePlayer;
    }

    public ArrayList<FriendPrivacy> getFriendsPrivacy(){
        return new ArrayList<>();
    }

    public void addFriendsPrivacy(FriendPrivacy friendPrivacy){

    }

    public void removeFriendsPrivacy(FriendPrivacy friendPrivacy){

    }



    public ArrayList<GroupPrivacy> getGroupPrivacy(){
        return new ArrayList<>();
    }

    public void addGroupPrivacy(GroupPrivacy groupPrivacy){

    }

    public void removeGroupPrivacy(GroupPrivacy groupPrivacy){

    }



    public ArrayList<PartyPrivacy> getPartyPrivacy(){
        return new ArrayList<>();
    }

    public void addPartyPrivacy(PartyPrivacy partyPrivacy){

    }

    public void removePartyPrivacy(PartyPrivacy partyPrivacy){

    }



    public ArrayList<MessagesPrivacy> getMessagesPrivacy(){
        return new ArrayList<>();
    }

    public void addMessagesPrivacy(MessagesPrivacy messagesPrivacy){

    }

    public void removeMessagesPrivacy(MessagesPrivacy messagesPrivacy){

    }

}






