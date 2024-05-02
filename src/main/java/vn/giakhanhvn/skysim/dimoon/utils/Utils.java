/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.dimoon.utils;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.dimoon.Dimoon;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class Utils {
    public static String format(String str) {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)str);
    }

    public static void bossMessage(String message) {
        if (SkySimEngine.getPlugin().dimoon == null) {
            return;
        }
        for (Player p : SkySimEngine.getPlugin().dimoon.getEntity().getWorld().getPlayers()) {
            p.playSound(p.getLocation(), Sound.WITHER_IDLE, 0.5f, 1.0f);
            p.sendMessage(Utils.format("&4[BOSS] Dimoon&f: " + message));
        }
        Dimoon.a.setCustomNameVisible(true);
        Dimoon.a.setCustomName(Sputnik.trans("&f&l" + message));
        SUtil.delay(() -> {
            if (Dimoon.a.getCustomName().contains(message)) {
                Dimoon.a.setCustomNameVisible(false);
                Dimoon.a.setCustomName(Sputnik.trans("&f&l"));
            }
        }, 40L);
    }

    public static String commaInt(int num) {
        String str = String.valueOf(num);
        for (int i = str.length() - 3; i > 0; i -= 3) {
            str = str.substring(0, i) + "," + str.substring(i);
        }
        return str;
    }
}

