package com.sasha.shinoabot.event;

import com.sasha.eventsys.SimpleEventHandler;
import com.sasha.eventsys.SimpleListener;
import com.sasha.reminecraft.api.event.ChatReceivedEvent;
import com.sasha.shinoabot.ShinoaBot;

public class MinecraftEventListener implements SimpleListener {
    @SimpleEventHandler
    public void onChatRecieved(ChatReceivedEvent event) {
        int index = event.getMessageText().indexOf(">") + 1;
        String raw = event.getMessageText().substring(index).trim();
        if (raw.startsWith(",")) ShinoaBot.BOT_CMD_PROC.processCommand(raw);
    }

}
