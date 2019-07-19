package com.sasha.shinoabot.event;

import com.sasha.eventsys.SimpleCancellableEvent;

public class PlayerExecuteCommandEvent extends SimpleCancellableEvent {

    public static String lastCommandExecutor;

    private String player;

    public String getPlayer() {
        return player;
    }

    public String getBody() {
        return body;
    }

    private String body;

    public PlayerExecuteCommandEvent(String player, String body) {
        this.player = player;
        this.body = body;
    }

}
