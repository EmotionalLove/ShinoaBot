package com.sasha.shinoabot;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.sasha.reminecraft.ReMinecraft;

public class Mail {

    private String from;
    private String to;
    private String content;

    public Mail(String from, String to, String content) {
        if (("/w " + to + " " + content).length() > 256) throw new IllegalArgumentException("Content is too long!");
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Mail(String raw) {
        String[] split = raw.split("\\\\");
        if (split.length != 3) throw new IllegalArgumentException("Argument isn't a valid Mail object.");
        this.from = split[0];
        this.to = split[1];
        this.content = split[2];
    }

    public void send() {
        ReMinecraft.INSTANCE.minecraftClient.getSession().send(new ClientChatPacket("/w " + to + " " + content));
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return from + "\\" + to + "\\" + content.replace("\\", "/");
    }
}
