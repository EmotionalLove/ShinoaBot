package com.sasha.shinoabot.command;

import com.sasha.shinoabot.ShinoaBot;
import me.someonelove.bettercommandsystem.Command;

public class FToC extends Command {
    public FToC() {
        super("ftoc");
    }


    @Override
    public void onCommand(boolean hasArgs, String[] args) {
        if (!hasArgs || args.length != 1) {
            ShinoaBot.sendMessageIngame("Error: Only one number allowed.");
            return;
        }
        try {
            int f = Integer.parseInt(args[0]);
            int c = (f - 32) * 5 / 9;
            ShinoaBot.sendMessageIngame(c + " Degrees Celsius");
        } catch (NumberFormatException e) {
            ShinoaBot.sendMessageIngame("Error: You cannot type letters.");
        }
    }
}