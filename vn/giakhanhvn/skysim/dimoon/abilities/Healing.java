/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.dimoon.abilities;

import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.dimoon.Dimoon;
import vn.giakhanhvn.skysim.dimoon.abilities.Ability;

public class Healing
implements Ability {
    @Override
    public void activate(Player player, Dimoon dimoon) {
        if (dimoon.getHealth() < 5000) {
            dimoon.heal(50);
        }
    }

    @Override
    public int getCooldown() {
        return 1;
    }
}

