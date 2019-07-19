package com.sasha.shinoabot.event;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTimePacket;
import com.sasha.eventsys.SimpleEventHandler;
import com.sasha.eventsys.SimpleListener;
import com.sasha.reminecraft.ReMinecraft;
import com.sasha.reminecraft.api.event.ChatReceivedEvent;
import com.sasha.reminecraft.api.event.RemoteServerPacketRecieveEvent;
import com.sasha.reminecraft.api.event.ServerOtherPlayerJoinEvent;
import com.sasha.shinoabot.Mail;
import com.sasha.shinoabot.ShinoaBot;
import com.sasha.shinoabot.ShinoaData;
import com.sasha.shinoabot.TickrateMonitor;

import java.util.ArrayList;

import static com.sasha.shinoabot.ShinoaBot.TICKRATE_MONITOR;

public class MinecraftEventListener implements SimpleListener {

    public MinecraftEventListener() {
        ShinoaBot.LOGGER.log("Now listening for events.");
    }


    @SimpleEventHandler
    public void onChatRecieved(ChatReceivedEvent event) {
        int index = event.getMessageText().indexOf(">") + 1;
        String player = event.getMessageAuthor();
        String raw = event.getMessageText().substring(index).trim();
        if (raw.startsWith(",")) {
            try {
                PlayerExecuteCommandEvent e = new PlayerExecuteCommandEvent(player, raw);
                ReMinecraft.INSTANCE.EVENT_BUS.invokeEvent(e);
                if (e.isCancelled()) return;
                PlayerExecuteCommandEvent.lastCommandExecutor = e.getPlayer();
                boolean success = ShinoaBot.COMMAND_PROCESSOR.processCommand(raw);
                if (!success) ShinoaBot.sendMessageIngame("Unknown command.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @SimpleEventHandler
    public void onTimeUpdate(RemoteServerPacketRecieveEvent e) {
        if (!(e.getRecievedPacket() instanceof ServerUpdateTimePacket)) {
            return;
        }
        TICKRATE_MONITOR.tick();
    }

    @SimpleEventHandler
    public void onPlayerJoin(ServerOtherPlayerJoinEvent e) {
        ShinoaData.rememberPlayer(e.getName());
        ArrayList<Mail> mails = ShinoaData.MailManager.getMailForUser(e.getName());
        if (mails.size() > 0) {
            int size = mails.size();
            ReMinecraft.INSTANCE.minecraftClient.getSession().send(new ClientChatPacket("/w " + e.getName() + " Welcome back, you have " + mails.size() + " new " + (size == 1 ? "message" : "messages")));
        }
        if (ShinoaData.getJoinMessage(e.getName()) == null) return;
        ShinoaBot.sendMessageIngame(e.getName() + " " + ShinoaData.getJoinMessage(e.getName()));
    }

    @SimpleEventHandler
    public void onCommandProcess(PlayerExecuteCommandEvent e) {
        ShinoaBot.LOGGER.log(e.getPlayer() + " is attempting to run \"" + e.getBody() + "\"");
    }

}
