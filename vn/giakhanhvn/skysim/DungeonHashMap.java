/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DungeonHashMap {
    public static final Map<Player, Boolean> IsPlayingDungeon = new HashMap<Player, Boolean>();
    public static final Map<World, Integer> DungeonScore = new HashMap<World, Integer>();
    public static final Map<Player, Double> ExperienceEarned = new HashMap<Player, Double>();
    public static final Map<Player, Integer> DeathCount = new HashMap<Player, Integer>();
    public static final Map<Player, Boolean> IsAGhost = new HashMap<Player, Boolean>();
    public static final Map<World, Integer> SecretFound = new HashMap<World, Integer>();
    public static final Map<World, Integer> MaximumSecrets = new HashMap<World, Integer>();
    public static final Map<World, Integer> WorldRespawnSecond = new HashMap<World, Integer>();
    public static final Map<Player, Integer> RespawnSecondLeft = new HashMap<Player, Integer>();
    public static final Map<Player, Boolean> PlayerReady = new HashMap<Player, Boolean>();
    public static final Map<Player, Boolean> PlayerBeingRevived = new HashMap<Player, Boolean>();
    public static final Map<Player, Boolean> AutoRevived = new HashMap<Player, Boolean>();
    public static final Map<Player, ItemStack[]> PlayerInventory = new HashMap<Player, ItemStack[]>();
    public static final Map<Player, ItemStack[]> PlayerArmorInventory = new HashMap<Player, ItemStack[]>();

    public static boolean isDead(Player player) {
        if (!IsAGhost.containsKey(player)) {
            return false;
        }
        return IsAGhost.get(player) != false;
    }

    public static String GetDungeonScore(World world) {
        int dgs;
        if (world == null) {
            return null;
        }
        if (!world.getName().equals("dungeon")) {
            return null;
        }
        if (!DungeonScore.containsKey(world)) {
            DungeonScore.put(world, 0);
        }
        if ((dgs = DungeonScore.get(world).intValue()) < 0) {
            DungeonScore.put(world, 0);
        } else {
            if (dgs < 100 && dgs > 0) {
                return "D";
            }
            if (dgs > 100 && dgs <= 159) {
                return "C";
            }
            if (dgs > 159 && dgs <= 229) {
                return "B";
            }
            if (dgs > 229 && dgs <= 269) {
                return "A";
            }
            if (dgs > 269 && dgs <= 299) {
                return "S";
            }
            if (dgs >= 300) {
                return "S+";
            }
        }
        return null;
    }
}

