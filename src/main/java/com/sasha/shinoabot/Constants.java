package com.sasha.shinoabot;


import com.sasha.reminecraft.ReMinecraft;
import com.sasha.reminecraft.client.ReClient;

public class Constants {

    public static String VERSION = "1.0.0 beta";
    public static final String DATA_FILE = "ShinoaData.yml";
    public static final String ARGS_REQD = "This command requires arguments.";

    public static String getSelfName() {
        return ReClient.ReClientCache.INSTANCE.playerName;
    }


}
