package com.sasha.shinoabot;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.sasha.reminecraft.ReMinecraft;
import com.sasha.reminecraft.api.RePlugin;
import com.sasha.reminecraft.logging.ILogger;
import com.sasha.reminecraft.logging.LoggerBuilder;
import com.sasha.shinoabot.command.AboutCommand;
import com.sasha.shinoabot.command.JoinMessageCommand;
import com.sasha.shinoabot.command.TickrateCommand;
import com.sasha.shinoabot.event.MinecraftEventListener;
import me.someonelove.bettercommandsystem.CommandProcessor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ShinoaBot extends RePlugin {

    public static ScheduledExecutorService time = Executors.newScheduledThreadPool(2);
    public static final ILogger LOGGER = LoggerBuilder.buildProperLogger("ShinoaBot");
    public static final CommandProcessor COMMAND_PROCESSOR = new CommandProcessor(",");

    @Override
    public void onPluginInit() {
        this.getReMinecraft().EVENT_BUS.registerListener(new MinecraftEventListener());
        this.getReMinecraft().EVENT_BUS.registerListener(new TickrateMonitor());
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
        COMMAND_PROCESSOR.registerCommand(new AboutCommand());
        COMMAND_PROCESSOR.registerCommand(new TickrateCommand());
        COMMAND_PROCESSOR.registerCommand(new JoinMessageCommand());
    }

    @Override
    public void registerConfig() {

    }

    public static void sendMessageIngame(String s) {
        ReMinecraft.INSTANCE.minecraftClient.getSession().send(new ClientChatPacket("> " + s));
    }
}
