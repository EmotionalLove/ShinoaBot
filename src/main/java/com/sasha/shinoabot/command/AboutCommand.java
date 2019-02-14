package com.sasha.shinoabot.command;

import com.sasha.shinoabot.ShinoaBot;
import com.sasha.simplecmdsys.SimpleCommand;

public class AboutCommand extends SimpleCommand {
    public AboutCommand() {
        super("about");
    }

    @Override
    public void onCommand() {
        ShinoaBot.sendMessageIngame("ShinoaBot made by Phi x Sasha");
    }
}
