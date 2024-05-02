/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.ExperienceOrb
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.item.weapon;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Ownable;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.FerocityCalculation;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SoulWhip
implements ToolStatistics,
MaterialFunction,
Ability,
Ownable {
    public static final Map<UUID, Boolean> cd = new HashMap<UUID, Boolean>();
    public static final Map<Integer, Boolean> hit = new HashMap<Integer, Boolean>();

    @Override
    public String getAbilityName() {
        return "Flay";
    }

    @Override
    public String getAbilityDescription() {
        return "Flay your whip in an arc, dealing your melee damage to all enemies in its path";
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 0;
    }

    @Override
    public void onAbilityUse(final Player player, final SItem sItem) {
        final Player p = player;
        if (!cd.containsKey(p.getUniqueId())) {
            cd.put(p.getUniqueId(), true);
            final Vector v = p.getLocation().getDirection().normalize().multiply(0.15);
            final Location loc = p.getLocation().clone().add(0.0, 1.55, 0.0);
            final World world = loc.getWorld();
            p.playSound(loc, Sound.CAT_HISS, 0.2f, 10.0f);
            new BukkitRunnable(){
                int count = 0;

                public void run() {
                    for (final Entity e : world.getNearbyEntities(loc, 1.0, 1.0, 1.0)) {
                        if (!(e instanceof LivingEntity) || e instanceof Player || e instanceof ExperienceOrb || e instanceof ArmorStand || e instanceof Villager || e.isDead() || e.hasMetadata("NPC") || e.hasMetadata("GiantSword") || hit.containsKey(e.getEntityId())) continue;
                        hit.put(e.getEntityId(), true);
                        new BukkitRunnable(){

                            public void run() {
                                User user = User.getUser(player.getUniqueId());
                                Object[] atp = Sputnik.calculateDamage(player, player, sItem.getStack(), (LivingEntity)e, false);
                                double finalDamage1 = ((Float)atp[0]).floatValue();
                                PlayerListener.spawnDamageInd(e, ((Float)atp[2]).floatValue(), (Boolean)atp[1]);
                                FerocityCalculation.activeFerocityTimes(player, (LivingEntity)e, (int)finalDamage1, (Boolean)atp[1]);
                                user.damageEntity((Damageable)e, finalDamage1);
                            }
                        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 0L);
                        new BukkitRunnable(){

                            public void run() {
                                hit.remove(e.getEntityId());
                            }
                        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 10L);
                    }
                    for (int i = 0; i < 10; ++i) {
                        loc.add(v);
                        loc.setY(loc.getY() + (double)(50 - this.count) / 1000.0);
                        Iterator packetx = null;
                        PacketPlayOutWorldParticles packet = this.count % 2 == 0 ? new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 3.9215687E-5f, 0.0f, 0.0f, 1.0f, 0, new int[0]) : new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), -0.9f, 0.2f, 0.2f, 1.0f, 0, new int[0]);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
                        for (Entity player2 : p.getNearbyEntities(10.0, 10.0, 10.0)) {
                            if (!(player2 instanceof Player)) continue;
                            ((CraftPlayer)player2).getHandle().playerConnection.sendPacket((Packet)packet);
                        }
                        ++this.count;
                    }
                    if (this.count >= 100) {
                        this.cancel();
                    }
                    if (world.getBlockAt((int)(loc.getX() - 0.5), loc.getBlockY(), (int)(loc.getZ() - 0.5)).getType() != Material.AIR) {
                        this.cancel();
                    }
                }
            }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 1L, 1L);
            new BukkitRunnable(){

                public void run() {
                    cd.remove(p.getUniqueId());
                }
            }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 10L);
        }
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public String getDisplayName() {
        return "Soul Whip";
    }

    @Override
    public int getBaseDamage() {
        return 145;
    }

    @Override
    public double getBaseStrength() {
        return 175.0;
    }

    @Override
    public boolean displayUsage() {
        return false;
    }

    @Override
    public boolean requirementsUse(Player player, SItem sItem) {
        return User.getUser(player.getUniqueId()).getBCollection() < 25L;
    }

    @Override
    public String getAbilityReq() {
        return "&cYou do not have requirement to use this item!\n&cYou need at least &525 Sadan Kills &cto use it!";
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
    public NBTTagCompound getData() {
        return Ownable.super.getData();
    }
}

