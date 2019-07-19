package com.sasha.shinoabot;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.sasha.eventsys.SimpleListener;
import com.sasha.reminecraft.ReMinecraft;
import com.sasha.reminecraft.api.RePlugin;
import com.sasha.reminecraft.logging.ILogger;
import com.sasha.reminecraft.logging.LoggerBuilder;
import com.sasha.shinoabot.command.*;
import com.sasha.shinoabot.event.MinecraftEventListener;
import me.someonelove.bettercommandsystem.CommandProcessor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ShinoaBot extends RePlugin implements SimpleListener {

    public static ScheduledExecutorService time = Executors.newScheduledThreadPool(2);
    public static final ILogger LOGGER = LoggerBuilder.buildProperLogger("ShinoaBot");
    public static final CommandProcessor COMMAND_PROCESSOR = new CommandProcessor(",");
    public static final TickrateMonitor TICKRATE_MONITOR = new TickrateMonitor();

    @Override
    public void onPluginInit() {
        TICKRATE_MONITOR.reset();
        this.getReMinecraft().EVENT_BUS.registerListener(new TickrateMonitor());
        this.getReMinecraft().EVENT_BUS.registerListener(this);
    }

    @Override
    public void onPluginEnable() {
        TICKRATE_MONITOR.reset();
        time.schedule(() -> this.getReMinecraft().EVENT_BUS.registerListener(new MinecraftEventListener()), 5L, TimeUnit.SECONDS);
        time.scheduleAtFixedRate(() -> {
            if ((!TICKRATE_MONITOR.isServerResponding()) && TICKRATE_MONITOR.timeLastTimeUpdate != -1L) {
                ShinoaBot.sendMessageIngame("Server is not responding! Last tick was " + (System.currentTimeMillis() - TICKRATE_MONITOR.timeLastTimeUpdate) + "ms ago! <");
            }
        }, 10L, 5L, TimeUnit.SECONDS);
        LOGGER.log("ShinoaBot " + Constants.VERSION + " enabled.");
    }

    @Override
    public void onPluginDisable() {
        LOGGER.log("ShinoaBot " + Constants.VERSION + " disabled.");
        time.shutdown();
        time = Executors.newScheduledThreadPool(2);
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
        COMMAND_PROCESSOR.registerCommand(new MessageCommand());
        COMMAND_PROCESSOR.registerCommand(new MessagesCommand());
        COMMAND_PROCESSOR.registerCommand(new ReadMessageCommand());
    }

    @Override
    public void registerConfig() {

    }

    public static void sendMessageIngame(String s) {
        ReMinecraft.INSTANCE.minecraftClient.getSession().send(new ClientChatPacket("> " + s));
    }
}
