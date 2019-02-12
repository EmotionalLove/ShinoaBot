package com.sasha.shinoabot.localisation;


/**
 * A container fo4 localised responses
 */
public class LocalisedEntry {

    public final EnumLocales locale;
    public final String key;
    public final String response;

    public LocalisedEntry(String key, String response) {
        this.locale = ConstantLocalisedResponses.defaultLocale;
        this.key = key;
        this.response = response;
    }

    public LocalisedEntry(EnumLocales locale, String key, String response) {
        this.locale = locale;
        this.key = key;
        this.response = response;
    }

    public EnumLocales getLocale() {
        return locale;
    }

}
