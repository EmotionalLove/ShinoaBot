package com.sasha.shinoabot.localisation;


/**
 * A container fo4 localised responses
 */
public class LocalisedEntry {

    public final EnumLocale locale;
    public final String key;
    public final String response;

    public LocalisedEntry(String key, String response) {
        this.locale = LocalisedResponseManager.defaultLocale;
        this.key = key;
        this.response = response;
    }

    public LocalisedEntry(EnumLocale locale, String key, String response) {
        this.locale = locale;
        this.key = key;
        this.response = response;
    }

    public EnumLocale getLocale() {
        return locale;
    }

}
