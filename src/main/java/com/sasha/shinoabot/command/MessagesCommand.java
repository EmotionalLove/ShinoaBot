package com.sasha.shinoabot.command;

import com.sasha.shinoabot.Mail;
import com.sasha.shinoabot.ShinoaBot;
import com.sasha.shinoabot.ShinoaData;
import com.sasha.shinoabot.event.PlayerExecuteCommandEvent;
import me.someonelove.bettercommandsystem.Command;

import java.util.ArrayList;

public class MessagesCommand extends Command {

    public MessagesCommand() {
        super("msgs");
    }

    @Override
    public void onCommand(boolean hasArgs, String[] args) {
        ArrayList<Mail> mails = ShinoaData.MailManager.getMailForUser(PlayerExecuteCommandEvent.lastCommandExecutor);
        if (mails.isEmpty()) {
            ShinoaBot.sendMessageIngame("You have no messages.");
            return;
        }
        int page = !hasArgs ? 1 : Integer.parseInt(args[0]);
        int maxPages = (mails.size() / 5) + 1;
        StringBuilder builder = new StringBuilder("(page " + page + "/" + maxPages + ") ");
        for (int i = (maxPages * page) - 5 < 0 ? 0 : (maxPages * page) - 5; i <= (mails.size() < maxPages * page ? mails.size() : maxPages * page); i++) {
            builder.append("(").append(i).append(") ").append("from ").append(mails.get(i).getFrom()).append(i == mails.size() - 1 ? "" : " - ");
        }
        ShinoaBot.sendMessageIngame(builder.toString());
    }
}
