/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.item.weapon;

import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.FerocityCalculation;
import vn.giakhanhvn.skysim.util.Sputnik;

public class ShadowFury
implements ToolStatistics,
MaterialFunction,
Ability {
    @Override
    public int getBaseDamage() {
        return 310;
    }

    @Override
    public double getBaseStrength() {
        return 130.0;
    }

    @Override
    public double getBaseSpeed() {
        return 0.3;
    }

    @Override
    public String getDisplayName() {
        return "Shadow Fury";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.WEAPON;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.SWORD;
    }

    @Override
    public String getLore() {
        return "";
    }

    @Override
    public String getAbilityName() {
        return "Shadow Fury";
    }

    @Override
    public String getAbilityDescription() {
        return "Rapidly teleports you to up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "enemies within " + ChatColor.YELLOW + "12 " + ChatColor.GRAY + "blocks, rooting each of them and allowing you to hit them.";
    }

    @Override
    public void onAbilityUse(final Player player, final SItem sItem) {
        boolean count1 = false;
        List<Entity> inRange = player.getNearbyEntities(12.0, 12.0, 12.0);
        List<Entity> filteredList = new ArrayList<Entity>();
        for (Entity e : inRange) {
            if (!(e instanceof Damageable) || e == player || e instanceof ArmorStand || e instanceof Player || e.hasMetadata("NPC") || e.hasMetadata("GiantSword")) continue;
            if (filteredList.size() >= 5) break;
            filteredList.add(e);
        }
        if (!inRange.isEmpty()) {
            new BukkitRunnable(){
                private int run = 0;

                public void run() {
                    if (this.run < filteredList.size()) {
                        if (!((Entity)filteredList.get(this.run)).isDead()) {
                            player.teleport(((Entity)filteredList.get(this.run)).getLocation().add(((Entity)filteredList.get(this.run)).getLocation().getDirection().multiply(-1)));
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3.0f, 1.0f);
                            User user = User.getUser(player.getUniqueId());
                            Entity e = (Entity)filteredList.get(this.run);
                            Object[] atp = Sputnik.calculateDamage(player, player, sItem.getStack(), (LivingEntity)e, false);
                            double finalDamage1 = ((Float)atp[0]).floatValue();
                            PlayerListener.spawnDamageInd(e, ((Float)atp[2]).floatValue(), (Boolean)atp[1]);
                            FerocityCalculation.activeFerocityTimes(player, (LivingEntity)e, (int)finalDamage1, (Boolean)atp[1]);
                            user.damageEntity((Damageable)e, finalDamage1);
                            for (Player p : player.getWorld().getPlayers()) {
                                ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((net.minecraft.server.v1_8_R3.Entity)((CraftLivingEntity)player).getHandle(), 0));
                            }
                        }
                        ++this.run;
                    } else {
                        this.cancel();
                    }
                }
            }.runTaskTimer(SkySimEngine.getPlugin(), 1L, 5L);
        } else {
            player.sendMessage(ChatColor.RED + "No nearby target found.");
        }
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 50;
    }

    @Override
    public int getManaCost() {
        return 0;
    }
}

