/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.potion;

import vn.giakhanhvn.skysim.potion.PotionEffect;
import vn.giakhanhvn.skysim.util.SUtil;

public class ActivePotionEffect {
    private final PotionEffect effect;
    private long remaining;

    public ActivePotionEffect(PotionEffect effect, long remaining) {
        this.effect = effect;
        this.remaining = remaining;
    }

    public String getRemainingDisplay() {
        return SUtil.getFormattedTime(this.remaining);
    }

    public PotionEffect getEffect() {
        return this.effect;
    }

    public long getRemaining() {
        return this.remaining;
    }

    public void setRemaining(long remaining) {
        this.remaining = remaining;
    }
}

