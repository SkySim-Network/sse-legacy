/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.dimoon.abilities;

import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.dimoon.Dimoon;

public interface Ability {
    public void activate(Player var1, Dimoon var2);

    public int getCooldown();
}

