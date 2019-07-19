package com.sasha.shinoabot.command;

import com.sasha.shinoabot.ShinoaBot;
import me.someonelove.bettercommandsystem.Command;

import static com.sasha.shinoabot.ShinoaBot.TICKRATE_MONITOR;

public class TickrateCommand extends Command {
    public TickrateCommand() {
        super("tps");
    }


    @Override
    public void onCommand(boolean hasArgs, String[] args) {
        ShinoaBot.sendMessageIngame(TICKRATE_MONITOR.getTickRate() + " tps - last tick was " + (System.currentTimeMillis() - TICKRATE_MONITOR.timeLastTimeUpdate) + "ms ago." + (!TICKRATE_MONITOR.isServerResponding() ? " (server not responding)" : ""));
    }
}