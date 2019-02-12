package com.sasha.shinoabot;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.sasha.reminecraft.ReMinecraft;
import com.sasha.reminecraft.api.RePlugin;
import com.sasha.shinoabot.command.AboutCommand;
import com.sasha.shinoabot.localisation.EnumLocale;
import com.sasha.shinoabot.localisation.LocalisedResponseManager;
import com.sasha.simplecmdsys.SimpleCommandProcessor;

public class ShinoaBot extends RePlugin {

    public static final SimpleCommandProcessor BOT_CMD_PROC = new SimpleCommandProcessor(",");
    public static final LocalisedResponseManager LANG_MANAGER = new LocalisedResponseManager();

    @Override
    public void onPluginInit() {
        // register the LocalisedResponses when the plugin is first initialised, so that we can access them as soon as possible
        LANG_MANAGER.registerLocalisedResponse(EnumLocale.ENG, "shinoa.about", "ShinoaBot " + Constants.VERSION);
        LANG_MANAGER.registerLocalisedResponse(EnumLocale.ENG, "shinoa.about", "シノアBOT " + Constants.VERSION);
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
        ReMinecraft.INSTANCE.minecraftClient.getSession().send(new ClientChatPacket("> " + s));
    }
}
