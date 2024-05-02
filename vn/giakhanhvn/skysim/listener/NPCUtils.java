/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.citizensnpcs.api.npc.NPC
 *  org.bukkit.entity.Entity
 */
package vn.giakhanhvn.skysim.listener;

import java.util.HashMap;
import java.util.Map;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import vn.giakhanhvn.skysim.util.SLog;

public class NPCUtils {
    public static final Map<Entity, Integer> HP_CURRENT_NPCMOB = new HashMap<Entity, Integer>();
    public static final Map<Entity, Integer> HP_MAX_NPCMOB = new HashMap<Entity, Integer>();
    public static final Map<Entity, Integer> DAMAGE_NPCMOB = new HashMap<Entity, Integer>();

    public static void setNPCHealth(NPC npc, int health) {
        if (npc.getEntity() == null) {
            return;
        }
        if (HP_CURRENT_NPCMOB.containsKey(npc.getEntity())) {
            if (HP_MAX_NPCMOB.containsKey(npc.getEntity())) {
                if (HP_MAX_NPCMOB.get(npc.getEntity()) >= HP_CURRENT_NPCMOB.get(npc.getEntity())) {
                    HP_CURRENT_NPCMOB.put(npc.getEntity(), health);
                } else {
                    SLog.severe("NPC STATS ERROR! You can't set Health > Max Health! Cannot set Health >>> " + npc.toString());
                }
            } else {
                SLog.severe("NPC STATS ERROR! Max health is null! Cannot set Health >>> " + npc.toString());
            }
        } else {
            SLog.severe("NPC STATS ERROR! Health is null! >>> " + npc.toString());
        }
    }

    public static void setNPCMaxHealth(NPC npc, int health) {
        if (npc.getEntity() == null) {
            return;
        }
        HP_MAX_NPCMOB.put(npc.getEntity(), health);
        HP_CURRENT_NPCMOB.put(npc.getEntity(), health);
    }

    public static int getNPCHealth(NPC npc) {
        if (npc.getEntity() == null) {
            return 0;
        }
        int returnstatement = 0;
        if (HP_CURRENT_NPCMOB.containsKey(npc.getEntity()) && HP_MAX_NPCMOB.containsKey(npc.getEntity())) {
            returnstatement = HP_CURRENT_NPCMOB.get(npc.getEntity());
        }
        return returnstatement;
    }

    public static int getNPCMaxHealth(NPC npc) {
        if (npc.getEntity() == null) {
            return 0;
        }
        int returnstatement = 0;
        if (HP_CURRENT_NPCMOB.containsKey(npc.getEntity()) && HP_MAX_NPCMOB.containsKey(npc.getEntity())) {
            returnstatement = HP_MAX_NPCMOB.get(npc.getEntity());
        }
        return returnstatement;
    }

    public static boolean updateNPCHealth(NPC npc) {
        if (npc.getEntity() == null) {
            return false;
        }
        boolean isSuccess = false;
        if (HP_CURRENT_NPCMOB.containsKey(npc.getEntity()) && !HP_MAX_NPCMOB.containsKey(npc.getEntity())) {
            HP_CURRENT_NPCMOB.remove(npc.getEntity());
            isSuccess = true;
        }
        return isSuccess;
    }

    public static boolean isNPCVaild(NPC npc) {
        boolean isVaild = false;
        if (npc.getEntity() != null) {
            isVaild = true;
        }
        return isVaild;
    }
}

