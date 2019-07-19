package com.sasha.shinoabot;

import com.sasha.reminecraft.util.YML;

public class ShinoaData {

    public static void setJoinMessage(String player, String message) {
        YML yml = new YML(Constants.DATA_FILE);
        yml.set("joins." + player.toLowerCase(), message);
        yml.save();
    }

    public static String getJoinMessage(String player) {
        YML yml = new YML(Constants.DATA_FILE);
        if (!yml.exists("joins." + player.toLowerCase())) return null;
        return yml.getString("joins." + player.toLowerCase());
    }

}
