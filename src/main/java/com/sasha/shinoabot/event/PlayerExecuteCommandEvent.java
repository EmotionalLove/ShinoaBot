package com.sasha.shinoabot.event;

import com.sasha.eventsys.SimpleCancellableEvent;

public class PlayerExecuteCommandEvent extends SimpleCancellableEvent {

    private String player;
    private String body;

    public PlayerExecuteCommandEvent(String player, String body) {
        this.player = player;
        this.body = body;
    }

}
