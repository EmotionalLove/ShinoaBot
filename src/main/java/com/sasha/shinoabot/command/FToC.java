package com.sasha.shinoabot.command;

import com.sasha.shinoabot.ShinoaBot;
import com.sasha.simplecmdsys.SimpleCommand;

public class FToC extends SimpleCommand {
    public FToC() {
        super("ftoc");
    }


    @Override
    public void onCommand() {
        if (this.getArguments().length!=1){
            ShinoaBot.sendMessageIngame("Error: Only one number allowed.");
            return;
        }
        try {
            int f = Integer.parseInt(this.getArguments()[0]);
            int c;
            c = (f-32)*5/9;
            ShinoaBot.sendMessageIngame(c+" Degrees Celsius");
        }catch(NumberFormatException e){
            ShinoaBot.sendMessageIngame("Error: You cannot type letters.");
        }
    }
}