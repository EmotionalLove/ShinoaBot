package com.sasha.shinoabot;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.sasha.reminecraft.ReMinecraft;
import com.sasha.reminecraft.api.RePlugin;
import com.sasha.reminecraft.logging.ILogger;
import com.sasha.reminecraft.logging.LoggerBuilder;
import com.sasha.shinoabot.command.AboutCommand;
import com.sasha.shinoabot.event.MinecraftEventListener;
import com.sasha.shinoabot.localisation.EnumLocale;
import com.sasha.shinoabot.localisation.LocalisedResponseManager;
import com.sasha.simplecmdsys.SimpleCommandProcessor;

public class ShinoaBot extends RePlugin {

    public static final ILogger LOGGER = LoggerBuilder.buildProperLogger("ShinoaBot");
    public static final SimpleCommandProcessor BOT_CMD_PROC = new SimpleCommandProcessor(",");
    public static final LocalisedResponseManager LANG_MANAGER = new LocalisedResponseManager();

    @Override
    public void onPluginInit() {
        // register the LocalisedResponses when the plugin is first initialised, so that we can access them as soon as possible
        LANG_MANAGER.registerLocalisedResponse(EnumLocale.ENG, "shinoa.about", "ShinoaBot " + Constants.VERSION);
        LANG_MANAGER.registerLocalisedResponse(EnumLocale.ENG, "shinoa.about", "シノアBOT " + Constants.VERSION);
        this.getReMinecraft().EVENT_BUS.registerListener(new MinecraftEventListener());
    }

    @Override
    public void onPluginEnable() {
        LOGGER.log("ShinoaBot " + Constants.VERSION + " enabled.");
    }

    @Override
    public void onPluginDisable() {
        LOGGER.log("ShinoaBot " + Constants.VERSION + " disabled.");
    }

    @Override
    public void onPluginShutdown() {
        LOGGER.log("ShinoaBot " + Constants.VERSION + " shut down.");
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
