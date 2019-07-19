package com.sasha.shinoabot.command;

import com.sasha.shinoabot.Constants;
import com.sasha.shinoabot.ShinoaBot;
import me.someonelove.bettercommandsystem.Command;

public class MessageCommand extends Command {

    public MessageCommand() {
        super("msg");
    }

    @Override
    public void onCommand(boolean hasArgs, String[] args) {
        if (!hasArgs) {
            ShinoaBot.sendMessageIngame(Constants.ARGS_REQD);
            return;
        }
    }
}
