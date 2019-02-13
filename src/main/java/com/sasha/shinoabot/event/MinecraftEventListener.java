package com.sasha.shinoabot.event;

import com.sasha.eventsys.SimpleEventHandler;
import com.sasha.eventsys.SimpleListener;
import com.sasha.reminecraft.api.event.ChatReceivedEvent;

public class MinecraftEventListener implements SimpleListener {

    //死ぬたいです
    //死ぬたいです
    //馬鹿です馬鹿です
    //ごめんなさい
    @SimpleEventHandler
    public void onChatRecieved(ChatReceivedEvent event) {
        int index = event.getMessageText().indexOf(">") + 1;
    }

}
