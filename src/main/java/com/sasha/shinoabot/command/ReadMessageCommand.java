package com.sasha.shinoabot.command;

import com.sasha.shinoabot.Constants;
import com.sasha.shinoabot.Mail;
import com.sasha.shinoabot.ShinoaBot;
import com.sasha.shinoabot.ShinoaData;
import com.sasha.shinoabot.event.PlayerExecuteCommandEvent;
import me.someonelove.bettercommandsystem.Command;

import java.util.ArrayList;

public class ReadMessageCommand extends Command {

    public ReadMessageCommand() {
        super("read");
    }

    @Override
    public void onCommand(boolean hasArgs, String[] args) {
        if (!hasArgs) {
            ShinoaBot.sendMessageIngame(Constants.ARGS_REQD);
            return;
        }
        ArrayList<Mail> mails = ShinoaData.MailManager.getMailForUser(PlayerExecuteCommandEvent.lastCommandExecutor);
        if (mails.isEmpty()) {
            ShinoaBot.sendMessageIngame("You have no messages.");
            return;
        }
        int index = Integer.parseInt(args[0]);
        if (index > mails.size() || index < 0) {
            ShinoaBot.sendMessageIngame("Invalid selection.");
            return;
        }
        Mail mail = mails.get(index);
        ShinoaBot.sendMessageIngame("from " + mail.getFrom() + " to " + mail.getTo() + ": " + mail.getContent());
        // todo delte msg
    }
}
