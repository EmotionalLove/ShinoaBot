package com.sasha.shinoabot.command;

import com.sasha.shinoabot.ShinoaBot;
import com.sasha.shinoabot.TickrateMonitor;
import me.someonelove.bettercommandsystem.Command;

public class TickrateCommand extends Command {
    public TickrateCommand() {
        super("tps");
    }


    @Override
    public void onCommand(boolean hasArgs, String[] args) {
        ShinoaBot.sendMessageIngame(TickrateMonitor.getTickRate() + " tps - last tick was " + (System.currentTimeMillis() - TickrateMonitor.timeLastTimeUpdate) + "ms ago." + (!TickrateMonitor.isServerResponding() ? " (server not responding)" : ""));
    }
}