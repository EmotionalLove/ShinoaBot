package com.sasha.shinoabot;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.sasha.reminecraft.ReMinecraft;
import com.sasha.reminecraft.api.RePlugin;
import com.sasha.reminecraft.logging.ILogger;
import com.sasha.reminecraft.logging.LoggerBuilder;
import com.sasha.shinoabot.command.AboutCommand;
import com.sasha.shinoabot.command.FToC;
import com.sasha.shinoabot.event.MinecraftEventListener;
import com.sasha.simplecmdsys.SimpleCommandProcessor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ShinoaBot extends RePlugin {

    public static ScheduledExecutorService time = Executors.newScheduledThreadPool(2);
    public static final ILogger LOGGER = LoggerBuilder.buildProperLogger("ShinoaBot");
    public static final SimpleCommandProcessor BOT_CMD_PROC = new SimpleCommandProcessor(",");

    @Override
    public void onPluginInit() {
        // register the LocalisedResponses when the plugin is first initialised, so that we can access them as soon as possible
        time.scheduleWithFixedDelay(()->{
            sendMessageIngame("https://discord.gg/eZC45rm");
        },600l,600l, TimeUnit.SECONDS);
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
            BOT_CMD_PROC.register(FToC.class);
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
