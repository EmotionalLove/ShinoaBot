package com.sasha.shinoabot;

import com.sasha.reminecraft.util.YML;

import java.util.ArrayList;
import java.util.List;

public class ShinoaData {

    public static void setJoinMessage(String player, String message) {
        YML yml = new YML(Constants.DATA_FILE);
        yml.set("joins." + player.toLowerCase(), message);
        yml.save();
    }

    public static String getJoinMessage(String player) {
        YML yml = new YML(Constants.DATA_FILE);
        if (!yml.exists("joins." + player.toLowerCase())) return null;
        return yml.getString("joins." + player.toLowerCase());
    }

    public static void rememberPlayer(String player) {
        YML yml = new YML(Constants.DATA_FILE);
        yml.set("memory." + player.toLowerCase(), System.currentTimeMillis());
        yml.save();
    }

    public static boolean doesRememberPlayer(String player) {
        YML yml = new YML(Constants.DATA_FILE);
        return yml.exists("memory." + player.toLowerCase());
    }

    public static class MailManager {
        public static void queueMessage(Mail mail) {
            YML yml = new YML(Constants.DATA_FILE);
            if (!yml.exists("mails." + mail.getTo().toLowerCase())) {
                List<String> arr = new ArrayList<>();
                arr.add(mail.toString());
                yml.set("mails." + mail.getTo().toLowerCase(), arr);
            } else {
                List<String> arr = yml.getStringList("mails." + mail.getTo().toLowerCase());
                arr.add(mail.toString());
                yml.set("mails." + mail.getTo().toLowerCase(), arr);
            }
            yml.save();
        }

        public static ArrayList<Mail> getMailForUser(String player) {
            YML yml = new YML(Constants.DATA_FILE);
            if (!yml.exists("mails." + player.toLowerCase())) {
                return new ArrayList<>();
            }
            ArrayList<Mail> mailList = new ArrayList<>();
            for (String s : yml.getStringList("mails." + player.toLowerCase())) {
                mailList.add(new Mail(s));
            }
            return mailList;
        }
    }
}
