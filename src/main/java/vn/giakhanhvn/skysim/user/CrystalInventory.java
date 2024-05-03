/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.user;

import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;

public class CrystalInventory {
    public static void load(Player player) {
        User user = User.getUser(player.getUniqueId());
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(user.getUuid());
        statistics.zeroAll(150);
        statistics.getMaxHealth().set(150, 10.0 * (double)user.getCrystalLVL(0));
        statistics.getDefense().set(150, 5.0 * (double)user.getCrystalLVL(1));
        statistics.getCritDamage().set(150, 0.01 * (double)user.getCrystalLVL(2));
        statistics.getCritChance().set(150, 0.005 * (double)user.getCrystalLVL(3));
        statistics.getIntelligence().set(150, 5.0 * (double)user.getCrystalLVL(4));
        statistics.getFerocity().set(150, 3.0 * (double)user.getCrystalLVL(5));
        statistics.getMagicFind().set(150, 0.02 * (double)user.getCrystalLVL(6));
        statistics.getStrength().set(150, 5.0 * (double)user.getCrystalLVL(7));
        for (int i = 0; i <= 7; ++i) {
            player.sendMessage(String.valueOf(user.getCrystalLVL(i)));
        }
    }
}

