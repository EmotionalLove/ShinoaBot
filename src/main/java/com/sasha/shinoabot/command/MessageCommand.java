package com.sasha.shinoabot.command;

import com.sasha.reminecraft.client.ReClient;
import com.sasha.shinoabot.Constants;
import com.sasha.shinoabot.Mail;
import com.sasha.shinoabot.ShinoaBot;
import com.sasha.shinoabot.ShinoaData;
import com.sasha.shinoabot.event.PlayerExecuteCommandEvent;
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
        if (args.length < 2) {
            ShinoaBot.sendMessageIngame("Not enough arguments. ,msg <player> <message>");
            return;
        }
        String player = args[0];
        if (!ShinoaData.doesRememberPlayer(player)) {
            ShinoaBot.sendMessageIngame(Constants.getSelfName() + " can't leave this player a message because it's never seen them before.");
            return;
        }
        if (ReClient.ReClientCache.INSTANCE.getGameProfileByName(player) != null) {
            ShinoaBot.sendMessageIngame(Constants.getSelfName() + " can't leave this player a message because they're already online.");
            return;
        }
        StringBuilder builder = new StringBuilder();
        // arrays start at 1 :smirk:
        for (int i = 1; i < args.length; i++) {
            builder.append(" ").append(args[i]);
        }
        String raw = builder.toString().trim();
        try {
            Mail mail = new Mail(PlayerExecuteCommandEvent.lastCommandExecutor, player, raw);
            ShinoaData.MailManager.queueMessage(mail);
            ShinoaBot.sendMessageIngame("Message will be delivered.");
        } catch (Exception x) {
            ShinoaBot.sendMessageIngame("Message is too long!");
        }
    }
}
