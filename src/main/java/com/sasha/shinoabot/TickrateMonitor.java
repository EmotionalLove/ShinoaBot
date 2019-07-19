package com.sasha.shinoabot;

import com.sasha.eventsys.SimpleListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class TickrateMonitor implements SimpleListener {

    private final float[] tickRates = new float[3];
    private int nextIndex = 0;
    public long timeLastTimeUpdate;
    public boolean ticked = false;

    public float getTickRate() {
        float numTicks = 0.0F;
        float sumTickRates = 0.0F;
        for (float tickRate : tickRates) {
            if (tickRate > 0.0F) {
                sumTickRates += tickRate;
                numTicks += 1.0F;
            }
        }
        try {
            return fround(sumTickRates / numTicks, 2);
        } catch (NumberFormatException e) {
            return 20f;
        }
    }



    public void tick() {
        ticked = true;
        if (timeLastTimeUpdate != -1L) {
            float timeElapsed = (float) (System.currentTimeMillis() - timeLastTimeUpdate) / 1000.0F;
            tickRates[(nextIndex % tickRates.length)] = clamp(20.0F / timeElapsed, 0.0F, 20.0F);
            nextIndex += 1;
        }
        timeLastTimeUpdate = System.currentTimeMillis();
    }

    public boolean isServerResponding() {
        return ticked && timeLastTimeUpdate == -1 || System.currentTimeMillis() - timeLastTimeUpdate <= 5000L;
    }

    public void reset() {
        nextIndex = 0;
        ticked = false;
        timeLastTimeUpdate = -1L;
        Arrays.fill(tickRates, 0.0F);
    }

    private static float clamp(float now, float min, float max) {
        if (now < min) return min;
        if (now > max) return max;
        return now;
    }

    private static float fround(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value + "");
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

}