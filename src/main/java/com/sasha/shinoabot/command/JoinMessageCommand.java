package com.sasha.shinoabot.command;

import com.sasha.shinoabot.Constants;
import com.sasha.shinoabot.ShinoaBot;
import com.sasha.shinoabot.ShinoaData;
import com.sasha.shinoabot.event.PlayerExecuteCommandEvent;
import me.someonelove.bettercommandsystem.Command;

public class JoinMessageCommand extends Command {

    public JoinMessageCommand() {
        super("jmsg");
    }

    @Override
    public void onCommand(boolean hasArgs, String[] args) {
        if (!hasArgs) {
            ShinoaBot.sendMessageIngame(Constants.ARGS_REQD);
            return;
        }
        if (args.length < 2) {
            ShinoaBot.sendMessageIngame("Not enough arguments. ,jmsg <player> <message>");
            return;
        }
        String player = args[0];
        StringBuilder builder = new StringBuilder();
        // arrays start at 1 :smirk:
        for (int i = 1; i < args.length; i++) {
            builder.append(" ").append(args[i]);
        }
        String raw = builder.toString().trim();
        if (player.equalsIgnoreCase(PlayerExecuteCommandEvent.lastCommandExecutor)) {
            ShinoaData.setJoinMessage(player, "tried to set their own join message >:");
        }
        ShinoaData.setJoinMessage(player, raw);
        ShinoaBot.sendMessageIngame(player + "'s join message has been set.");
    }
}
