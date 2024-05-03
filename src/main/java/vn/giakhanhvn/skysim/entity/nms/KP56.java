/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  org.bukkit.Location
 *  org.bukkit.craftbukkit.v1_8_R3.CraftServer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 */
package vn.giakhanhvn.skysim.entity.nms;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import vn.giakhanhvn.skysim.entity.nms.SNMSEntity;

public class KP56
extends CraftPlayer
implements SNMSEntity {
    private int iq = 0;

    public KP56(CraftServer server, EntityPlayer entity, int iq) {
        super(server, entity);
        this.iq = iq;
    }

    public int _INVALID_getLastDamage() {
        return this.iq;
    }

    public void _INVALID_setLastDamage(int damage) {
    }

    public void _INVALID_damage(int amount) {
    }

    public void _INVALID_damage(int amount, Entity source) {
    }

    public int _INVALID_getHealth() {
        return 0;
    }

    public void _INVALID_setHealth(int health) {
    }

    public int _INVALID_getMaxHealth() {
        return 0;
    }

    public void _INVALID_setMaxHealth(int health) {
    }

    @Override
    public LivingEntity spawn(Location location) {
        return null;
    }
}

