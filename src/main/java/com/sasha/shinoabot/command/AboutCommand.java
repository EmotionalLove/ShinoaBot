package com.sasha.shinoabot.command;

import com.sasha.shinoabot.Constants;
import com.sasha.shinoabot.ShinoaBot;
import com.sasha.simplecmdsys.SimpleCommand;
import me.someonelove.bettercommandsystem.Command;

public class AboutCommand extends Command {
    public AboutCommand() {
        super("about");
    }

    @Override
    public void onCommand(boolean hasArgs, String[] args) {
        ShinoaBot.sendMessageIngame("ShinoaBot " + Constants.VERSION);
    }
}
