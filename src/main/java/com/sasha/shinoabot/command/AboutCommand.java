package com.sasha.shinoabot.command;

import com.sasha.shinoabot.ShinoaBot;
import com.sasha.simplecmdsys.SimpleCommand;

public class AboutCommand extends SimpleCommand {
    public AboutCommand() {
        super("about");
    }

    @Override
    public void onCommand() {
        // this will get the text that we register in ShinoaBot.onPluginInit() by the key we specified then, and send it
        ShinoaBot.sendMessageIngame("about");
    }
}
