package com.sasha.shinoabot;

import com.sasha.reminecraft.api.RePlugin;
import com.sasha.shinoabot.command.AboutCommand;
import com.sasha.simplecmdsys.SimpleCommandProcessor;

public class ShinoaBot extends RePlugin {

    public static final SimpleCommandProcessor BOT_CMD_PROC = new SimpleCommandProcessor(",");

    @Override
    public void onPluginInit() {
    }

    @Override
    public void onPluginEnable() {

    }

    @Override
    public void onPluginDisable() {

    }

    @Override
    public void onPluginShutdown() {

    }

    @Override
    public void registerCommands() {
        try {
            BOT_CMD_PROC.register(AboutCommand.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerConfig() {

    }

    public static void sendMessageIngame(String s) {

    }
}
