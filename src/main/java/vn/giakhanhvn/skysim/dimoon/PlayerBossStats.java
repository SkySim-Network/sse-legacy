/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.dimoon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerBossStats {
    private static Map<UUID, PlayerBossStats> CACHE = new HashMap<UUID, PlayerBossStats>();
    public int catalystsPlaced;
    public int damagePlace;

    public PlayerBossStats(UUID uuid) {
        CACHE.put(uuid, this);
    }

    public static PlayerBossStats get(UUID u) {
        if (CACHE.containsKey(u)) {
            return CACHE.get(u);
        }
        return null;
    }
}

