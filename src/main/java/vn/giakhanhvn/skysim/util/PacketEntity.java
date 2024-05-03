/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving
 *  org.bukkit.Bukkit
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitTask
 */
package vn.giakhanhvn.skysim.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.user.User;

public class PacketEntity {
    Entity entity;
    List<Player> players;
    Packet spawn;
    List<Packet> spawnPackets;
    BukkitTask tickTask;
    String permission;
    static final int showRadius = 50;
    public static List<PacketEntity> managers = new ArrayList<PacketEntity>();
    double x;
    double y;
    double z;

    public PacketEntity(Entity e) {
        this.entity = e;
        this.players = new ArrayList<Player>();
        this.updateSpawnPacket();
        managers.add(this);
        this.tickTask = Bukkit.getScheduler().runTaskTimer((Plugin)SkySimEngine.getPlugin(), () -> this.tick(), 1L, 5L);
    }

    public void setShowPermission(String permission) {
        this.permission = permission;
    }

    public void addSpawnPacket(Packet packet) {
        if (this.spawnPackets == null) {
            this.spawnPackets = new ArrayList<Packet>();
        }
        this.spawnPackets.add(packet);
        this.sendCustomPacket(packet);
    }

    public void removeSpawnPacket(Packet packet) {
        if (this.spawnPackets.contains(packet)) {
            this.spawnPackets.remove(packet);
        }
    }

    public boolean updateLocation() {
        if (this.players.isEmpty()) {
            return false;
        }
        if (this.x == this.entity.locX && this.y == this.entity.locY && this.z == this.entity.locZ) {
            return true;
        }
        this.x = this.entity.locX;
        this.y = this.entity.locY;
        this.z = this.entity.locZ;
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport((Entity)((EntityLiving)this.entity));
        for (int i = 0; i < this.players.size(); ++i) {
            Player next = this.players.get(i);
            ((CraftPlayer)next).getHandle().playerConnection.sendPacket((Packet)teleport);
        }
        return true;
    }

    public boolean playAnimation(int anim) {
        if (this.players.isEmpty()) {
            return false;
        }
        PacketPlayOutAnimation teleport = new PacketPlayOutAnimation((Entity)((EntityLiving)this.entity), anim);
        for (int i = 0; i < this.players.size(); ++i) {
            Player next = this.players.get(i);
            ((CraftPlayer)next).getHandle().playerConnection.sendPacket((Packet)teleport);
        }
        return true;
    }

    public void tick() {
        ArrayList<Player> newPlayers = new ArrayList<Player>();
        this.updateSpawnPacket();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getWorld().equals(this.entity.getBukkitEntity().getWorld()) || !(p.getLocation().distance(this.entity.getBukkitEntity().getLocation()) < 50.0)) continue;
            if (this.players.contains(p)) {
                if (this.permission != null && !User.getUser(p.getUniqueId()).hasPermission(this.permission)) continue;
                this.players.remove(p);
                newPlayers.add(p);
                continue;
            }
            if (!this.sendSpawnPacket(p)) continue;
            newPlayers.add(p);
        }
        for (Player p : this.players) {
            this.sendDestroyPacket(p);
        }
        this.players = newPlayers;
    }

    public void destroy() {
        this.hide();
        this.tickTask.cancel();
        if (managers.contains(this)) {
            managers.remove(this);
        }
    }

    public void hide() {
        for (int i = 0; i < this.players.size(); ++i) {
            Player next = this.players.get(i);
            this.sendDestroyPacket(next);
        }
    }

    protected void updateSpawnPacket() {
        this.spawn = new PacketPlayOutSpawnEntityLiving((EntityLiving)this.entity);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void spawn() {
        for (Player next : this.players) {
            this.sendSpawnPacket(next);
        }
    }

    public void sendCustomPacket(Packet packet) {
        for (Player p : this.players) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        }
    }

    protected boolean sendSpawnPacket(Player player) {
        if (this.permission != null && !User.getUser(player.getUniqueId()).hasPermission(this.permission)) {
            this.players.remove(player);
            return false;
        }
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(this.spawn);
        if (this.spawnPackets != null) {
            for (Packet packet : this.spawnPackets) {
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
            }
        }
        return true;
    }

    protected void sendDestroyPacket(Player player) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutEntityDestroy(new int[]{this.entity.getId()}));
    }

    public static void removePlayer(Player player) {
        for (PacketEntity manager : managers) {
            if (!manager.players.contains(player)) continue;
            manager.players.remove(player);
        }
    }
}

