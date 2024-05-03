/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityFireworks
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus
 *  net.minecraft.server.v1_8_R3.World
 *  org.bukkit.FireworkEffect
 *  org.bukkit.Location
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.Firework
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.meta.FireworkMeta
 */
package vn.giakhanhvn.skysim.util;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityFireworks;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class SSU
extends EntityFireworks {
    Player[] players = null;
    boolean gone = false;

    public SSU(World world, Player ... p) {
        super(world);
        this.players = p;
        this.a(new float[]{0.25f, 0.25f});
    }

    public void t_() {
        if (this.gone) {
            return;
        }
        if (!this.world.isClientSide) {
            this.gone = true;
            if (this.players != null) {
                if (this.players.length > 0) {
                    for (Player player : this.players) {
                        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityStatus((Entity)this, (byte) 17));
                    }
                } else {
                    this.world.broadcastEntityEffect((Entity)this, (byte)17);
                }
            }
            this.die();
        }
    }

    public static void spawn(Location location, FireworkEffect effect, Player ... players) {
        try {
            SSU firework = new SSU((World)((CraftWorld)location.getWorld()).getHandle(), players);
            FireworkMeta meta = ((Firework)firework.getBukkitEntity()).getFireworkMeta();
            meta.addEffect(effect);
            ((Firework)firework.getBukkitEntity()).setFireworkMeta(meta);
            firework.setPosition(location.getX(), location.getY(), location.getZ());
            if (((CraftWorld)location.getWorld()).getHandle().addEntity((Entity)firework)) {
                firework.setInvisible(true);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

