package com.sasha.shinoabot.event;

import com.sasha.eventsys.SimpleEventHandler;
import com.sasha.eventsys.SimpleListener;
import com.sasha.reminecraft.ReMinecraft;
import com.sasha.reminecraft.api.event.ChatReceivedEvent;
import com.sasha.reminecraft.api.event.ServerOtherPlayerJoinEvent;
import com.sasha.shinoabot.ShinoaBot;
import com.sasha.shinoabot.ShinoaData;

public class MinecraftEventListener implements SimpleListener {



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
    public void onPlayerJoin(ServerOtherPlayerJoinEvent e) {
        if (ShinoaData.getJoinMessage(e.getName()) == null) return;
        ShinoaBot.sendMessageIngame(e.getName() + " " + ShinoaData.getJoinMessage(e.getName()));
    }

    @SimpleEventHandler
    public void onCommandProcess(PlayerExecuteCommandEvent e) {
        ShinoaBot.LOGGER.log(e.getPlayer() + " is attempting to run \"" + e.getBody() + "\"");
    }

}
