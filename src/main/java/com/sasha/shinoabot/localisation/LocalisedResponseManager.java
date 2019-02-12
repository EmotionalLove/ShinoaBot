package com.sasha.shinoabot.localisation;

import com.sasha.shinoabot.Constants;

import java.util.ArrayList;

public abstract class LocalisedResponseManager {

    private ArrayList<LocalisedEntry> localisedEntries = new ArrayList<>();

    public LocalisedResponseManager() {
        this.registerLocalisedResponse(EnumLocale.ENG, "response.unknown", "Unknown response.");
        this.registerLocalisedResponse(EnumLocale.JAPANESE, "response.unknown", "!");
    }

    /**
     * Add the given LocalisedEntry to the arrayList, so that it can be resolved
     *
     * @param entry The entry
     */
    public void registerLocalisedResponse(LocalisedEntry entry) {
        localisedEntries.add(entry);
    }

    /**
     * Create a Localised Entry from the given parametres and add it to the arrayList, so that it can be resolved.
     *
     * @param locale   The language that the response is in.
     * @param key      The key to refer to this response with.
     * @param response The full length response text.
     */
    public void registerLocalisedResponse(EnumLocale locale, String key, String response) {
        localisedEntries.add(new LocalisedEntry(locale, key, response));
    }

    /**
     * Search the localisedEntries arraylist for a LocalisedEntry that contains a response for the key and DEFAULT locale
     *
     * @param key key
     * @return The response
     */
    public String resolve(String key) {
        return this.resolve(Constants.defaultLocale, key);
    }

    /**
     * Search the localisedEntries arraylist for a LocalisedEntry that contains a response for the key and lcoale
     *
     * @param locale language
     * @param key    key
     * @return The response
     */
    public String resolve(EnumLocale locale, String key) {
        for (LocalisedEntry localisedEntry : localisedEntries) {
            if (localisedEntry.locale == locale && localisedEntry.key.equalsIgnoreCase(key)) {
                return localisedEntry.response;
            }
        }
        return this.resolve(locale, "response.unknown");
    }

}
