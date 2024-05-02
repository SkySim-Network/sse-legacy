/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.comphenix.protocol.wrappers.WrappedSignedProperty
 *  com.google.common.util.concurrent.AtomicDouble
 *  com.sk89q.worldedit.EditSession
 *  com.sk89q.worldedit.LocalWorld
 *  com.sk89q.worldedit.Vector
 *  com.sk89q.worldedit.WorldEdit
 *  com.sk89q.worldedit.WorldEditException
 *  com.sk89q.worldedit.blocks.BaseBlock
 *  com.sk89q.worldedit.bukkit.BukkitWorld
 *  com.sk89q.worldedit.extent.Extent
 *  com.sk89q.worldedit.extent.clipboard.Clipboard
 *  com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat
 *  com.sk89q.worldedit.extent.clipboard.io.ClipboardReader
 *  com.sk89q.worldedit.function.operation.Operation
 *  com.sk89q.worldedit.function.operation.Operations
 *  com.sk89q.worldedit.session.ClipboardHolder
 *  com.sk89q.worldedit.util.io.Closer
 *  com.sk89q.worldedit.world.registry.WorldData
 *  com.xxmicloxx.NoteBlockAPI.model.Song
 *  com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer
 *  com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder
 *  me.libraryaddict.disguise.disguisetypes.FlagWatcher
 *  me.libraryaddict.disguise.disguisetypes.MobDisguise
 *  me.libraryaddict.disguise.disguisetypes.PlayerDisguise
 *  net.md_5.bungee.api.ChatColor
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.ClickEvent
 *  net.md_5.bungee.api.chat.ClickEvent$Action
 *  net.md_5.bungee.api.chat.HoverEvent
 *  net.md_5.bungee.api.chat.HoverEvent$Action
 *  net.md_5.bungee.api.chat.TextComponent
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  net.minecraft.server.v1_8_R3.EntityInsentient
 *  net.minecraft.server.v1_8_R3.EntityItem
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.EntityZombie
 *  net.minecraft.server.v1_8_R3.EnumAnimation
 *  net.minecraft.server.v1_8_R3.EnumParticle
 *  net.minecraft.server.v1_8_R3.Item
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles
 *  net.minecraft.server.v1_8_R3.PathEntity
 *  net.minecraft.server.v1_8_R3.Vec3D
 *  net.minecraft.server.v1_8_R3.World
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.Effect
 *  org.bukkit.FireworkEffect
 *  org.bukkit.FireworkEffect$Builder
 *  org.bukkit.FireworkEffect$Type
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Server
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Enderman
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.inventory.EntityEquipment
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.util;

import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import com.google.common.util.concurrent.AtomicDouble;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.util.io.Closer;
import com.sk89q.worldedit.world.registry.WorldData;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityItem;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.EnumAnimation;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.PathEntity;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.command.AccessTimedCommand;
import vn.giakhanhvn.skysim.dungeons.BlessingChest;
import vn.giakhanhvn.skysim.dungeons.Blessings;
import vn.giakhanhvn.skysim.dungeons.ItemChest;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanBossManager;
import vn.giakhanhvn.skysim.entity.nms.VoidgloomSeraph;
import vn.giakhanhvn.skysim.extra.beam.Beam;
import vn.giakhanhvn.skysim.gui.BossMenu;
import vn.giakhanhvn.skysim.gui.PetsGUI;
import vn.giakhanhvn.skysim.gui.TradeMenu;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.user.UserStash;
import vn.giakhanhvn.skysim.util.DiscordWebhook;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SSU;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.SputnikPlayer;
import vn.giakhanhvn.skysim.util.TradeUtil;

public class Sputnik {
    public Random random = new Random();
    public static final Map<Server, Integer> RunThisSession = new HashMap<Server, Integer>();
    public static final Map<SItem, Integer> MidasStaff = new HashMap<SItem, Integer>();
    public static final Map<SItem, Long> MidasStaffDmg = new HashMap<SItem, Long>();
    public static final Map<UUID, Integer> CoinsTakenOut = new HashMap<UUID, Integer>();
    public static final Map<UUID, Boolean> IsInsideTheBeam = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Boolean> CooldownAbs = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Boolean> HaveDMGReduction = new HashMap<UUID, Boolean>();
    public static final Map<Entity, Location> MAP_PARTICLE_1 = new HashMap<Entity, Location>();

    public static String trans(String content) {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)content);
    }

    public static void sendHeadRotation(Entity e, float yaw, float pitch) {
        EntityZombie pl = ((CraftZombie)e).getHandle();
        pl.setLocation(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), yaw, pitch);
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport((net.minecraft.server.v1_8_R3.Entity)pl);
        Sputnik.sendPacket(e.getWorld(), (Packet)packet);
    }

    public static void sendColoredFireWork(Color color, Location loc) {
        FireworkEffect.Builder builder = FireworkEffect.builder();
        FireworkEffect effect = builder.flicker(false).trail(false).with(FireworkEffect.Type.BURST).withColor(color).build();
        SSU.spawn(loc, effect, new Player[0]);
    }

    public static void sendReportToMotherland(String message) {
    }

    public static void spawnEnumParticlePIW(EnumParticle effect, Location loc, int arg1, int arg2, int arg3, int arg4, int arg5, World world) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(effect, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)arg1, (float)arg2, (float)arg3, (float)arg4, arg5, new int[0]);
        for (Player online : world.getPlayers()) {
            ((CraftPlayer)online).getHandle().playerConnection.sendPacket((Packet)packet);
        }
    }

    public static void spawnEnumParticleCSO(EnumParticle effect, Location loc, int arg1, int arg2, int arg3, int arg4, int arg5, Player player) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(effect, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)arg1, (float)arg2, (float)arg3, (float)arg4, arg5, new int[0]);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
    }

    public static void spawnEnumParticleLOP(EnumParticle effect, Location loc, int arg1, int arg2, int arg3, int arg4, int arg5, Player[] p) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(effect, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)arg1, (float)arg2, (float)arg3, (float)arg4, arg5, new int[0]);
        for (Player online : p) {
            ((CraftPlayer)online).getHandle().playerConnection.sendPacket((Packet)packet);
        }
    }

    public static void spawnEnumParticlePIS(EnumParticle effect, Location loc, int arg1, int arg2, int arg3, int arg4, int arg5) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(effect, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)arg1, (float)arg2, (float)arg3, (float)arg4, arg5, new int[0]);
        for (Player online : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)online).getHandle().playerConnection.sendPacket((Packet)packet);
        }
    }

    public static void midasFlame(Entity entity) {
        float angle_1 = ((LivingEntity)entity).getEyeLocation().getYaw() / 60.0f;
        Location loc1_a = ((LivingEntity)entity).getEyeLocation().add(Math.cos(angle_1), 0.0, Math.sin(angle_1));
        Location loc2_a = ((LivingEntity)entity).getEyeLocation().subtract(Math.cos(angle_1), 0.0, Math.sin(angle_1));
        entity.getWorld().playEffect(entity.getLocation(), Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc1_a, Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc2_a, Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(entity.getLocation(), Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc1_a, Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc2_a, Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(entity.getLocation(), Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc1_a, Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc2_a, Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(entity.getLocation(), Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc1_a, Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc2_a, Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(entity.getLocation(), Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc1_a, Effect.LAVA_POP, 3);
        entity.getWorld().playEffect(loc2_a, Effect.LAVA_POP, 3);
    }

    public static void midasCalcDamage(Entity entity1, Player player, SItem sItem) {
        String act = "True";
        if (act == "True") {
            act = "False";
            for (Entity entity : entity1.getWorld().getNearbyEntities(entity1.getLocation(), 1.0, 4.0, 1.0)) {
                int count = 0;
                if (entity.isDead() || !(entity instanceof LivingEntity) || entity.hasMetadata("GiantSword") || entity.hasMetadata("NoAffect") || entity instanceof Player || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand) continue;
                User user = User.getUser(player.getUniqueId());
                double baseDamage = Sputnik.calculateMagicDamage(entity, player, 32000, 0.3);
                user.damageEntityIgnoreShield((Damageable)((LivingEntity)entity), (int)baseDamage);
                ++count;
                PlayerListener.spawnDamageInd(entity, baseDamage, false);
                if (!MidasStaff.containsKey(sItem)) {
                    MidasStaff.put(sItem, 0);
                }
                if (!MidasStaffDmg.containsKey(sItem)) {
                    MidasStaffDmg.put(sItem, 0L);
                }
                MidasStaff.put(sItem, MidasStaff.get(sItem) + count);
                MidasStaffDmg.put(sItem, (long)((double)MidasStaff.get(sItem).intValue() * baseDamage));
            }
        }
    }

    public static Integer midasCalcCounter(Entity entity1, Player player) {
        return 0;
    }

    @Deprecated
    public static int makeZero() {
        return 0;
    }

    public static void smartGiveItem(ItemStack item, Player p) {
        if (p.getInventory().firstEmpty() != -1) {
            p.getInventory().addItem(new ItemStack[]{item});
        } else if (User.getUser(p.getUniqueId()) != null) {
            UserStash us = UserStash.getStash(p.getUniqueId());
            us.addItemInStash(item);
        }
    }

    public static void GiveItem(ItemStack item, Player p) {
        p.getInventory().addItem(new ItemStack[]{item});
    }

    public static boolean isFullInv(Player p) {
        return p.getInventory().firstEmpty() == -1;
    }

    public static String calMagicLore(Player p, int baseMagicDmg, double scale) {
        if (p == null) {
            return SUtil.commaify(baseMagicDmg);
        }
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(p.getUniqueId());
        int manaPool = SUtil.blackMagic(100.0 + statistics.getIntelligence().addAll());
        PlayerInventory inv = p.getInventory();
        SItem helmet = SItem.find(inv.getHelmet());
        if (helmet != null) {
            if (helmet.getType() == SMaterial.DARK_GOGGLES) {
                baseMagicDmg += baseMagicDmg * 25 / 100;
            } else if (helmet.getType() == SMaterial.SHADOW_GOGGLES) {
                baseMagicDmg += baseMagicDmg * 35 / 100;
            } else if (helmet.getType() == SMaterial.WITHER_GOGGLES) {
                baseMagicDmg += baseMagicDmg * 45 / 100;
            }
        }
        return SUtil.commaify((double)baseMagicDmg * ((double)(manaPool / 100) * 0.3 + 1.0));
    }

    public static void endermanCarryBlock(Enderman e, Material mat) {
        e.setCarriedMaterial(new ItemStack(mat).getData());
    }

    public static void showFakeItem(Location loc, ItemStack material, Player p) {
        EntityItem item = new EntityItem((net.minecraft.server.v1_8_R3.World)((CraftWorld)loc.getWorld()).getHandle());
        item.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0.0f, 0.0f);
        item.setItemStack(CraftItemStack.asNMSCopy((ItemStack)material));
        PacketPlayOutSpawnEntity packet = new PacketPlayOutSpawnEntity((net.minecraft.server.v1_8_R3.Entity)item, 2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
    }

    public static void summonCircle(Entity e, Location location, int size) {
        Beam beam = new Beam(location, location.add(e.getLocation().getDirection().multiply(30)));
        beam.start();
        for (int d = 0; d <= 90; ++d) {
            Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            particleLoc.setX(location.getX() + Math.cos(d) * (double)size);
            particleLoc.setZ(location.getZ() + Math.sin(d) * (double)size);
            beam.setStartingPosition(location);
            beam.setEndingPosition(location.add(e.getLocation().getDirection().multiply(size)));
        }
    }

    public static void entityBeam(final ArmorStand stand, Location location1, final Player p, final Entity e) {
        final Beam beam = new Beam(stand.getLocation().clone().add(stand.getLocation().getDirection().normalize().multiply(20)), stand.getLocation().clone().add(stand.getLocation().getDirection().normalize().multiply(-20)));
        stand.setGravity(false);
        beam.start();
        new BukkitRunnable(){

            public void run() {
                if (stand.isDead()) {
                    if (beam.isActive()) {
                        beam.stop();
                    }
                    this.cancel();
                    return;
                }
                Location location = stand.getLocation();
                location.setYaw(stand.getLocation().getYaw() + 2.25f);
                stand.teleport(location);
                Sputnik.getEntity(stand.getLocation().add(stand.getLocation().getDirection().multiply(20)), stand.getLocation().add(stand.getLocation().getDirection().multiply(-20)), p, e);
                beam.setEndingPosition(stand.getLocation().add(stand.getLocation().getDirection().multiply(0 - Sputnik.findArgo(p.getLocation(), stand.getLocation()))));
                beam.setStartingPosition(stand.getLocation().add(stand.getLocation().getDirection().normalize().multiply(Sputnik.findArgo(p.getLocation(), stand.getLocation()))));
                if (!beam.isActive() && !stand.isDead()) {
                    beam.update();
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    public static int findArgo(Location arg0, Location arg1) {
        double dist = arg0.distance(arg1);
        if (dist < 5.0) {
            return 10;
        }
        return 20;
    }

    public static void RemoveEntityArray(ArrayList<Entity> a) {
        for (Entity e : a) {
            if (e == null) continue;
            e.remove();
        }
    }

    public static void RemoveEntityArray(List<Entity> a) {
        for (Entity e : a) {
            if (e == null) continue;
            e.remove();
        }
    }

    public static void createPet(Player player) {
        Pet.PetItem pet = User.getUser(player.getUniqueId()).getActivePet();
        Pet petclass = User.getUser(player.getUniqueId()).getActivePetClass();
        if (pet != null && petclass != null) {
            PetsGUI.spawnFlyingHeads(player, petclass, pet.toItem().getStack());
        }
    }

    public static void getEntity(Location finaldestination, Location ended, Player player, Entity e) {
        Location blockLocation = finaldestination;
        Location crystalLocation = ended;
        org.bukkit.util.Vector vector = blockLocation.clone().add(0.1, 0.0, 0.1).toVector().subtract(crystalLocation.clone().toVector());
        double count = 25.0;
        for (int i = 1; i <= (int)count; ++i) {
            for (Entity entity : ended.getWorld().getNearbyEntities(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), 0.2, 0.0, 0.2)) {
                if (entity != player) continue;
                IsInsideTheBeam.put(player.getUniqueId(), true);
                return;
            }
        }
    }

    public static void drawLine(Location point1, Location point2, double space) {
        float angle_1 = point1.getYaw() / 60.0f;
        Location loc1 = point1.add(Math.cos(angle_1), 0.0, Math.sin(angle_1));
        float angle_2 = point2.getYaw() / 60.0f;
        Location loc2 = point2.subtract(Math.cos(angle_2), 0.0, Math.sin(angle_2));
        Location blockLocation = loc1;
        Location crystalLocation = loc2;
        org.bukkit.util.Vector vector = blockLocation.clone().add(0.1, 0.0, 0.1).toVector().subtract(crystalLocation.clone().toVector());
        double count = 13.0;
        double length = 0.0;
        for (int i = 1; i <= (int)count; ++i) {
            point1.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), Effect.COLOURED_DUST, 0, 1, 0.8627451f, 0.03529412f, 0.007843138f, 1.0f, 0, 64);
            point1.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), Effect.COLOURED_DUST, 0, 1, 1.0196079f, 0.03529412f, 0.007843138f, 1.0f, 0, 64);
        }
    }

    public static void drawLineforMovingPoints(Location point1, Location point2, double space, Player p, Integer i1uu, Entity e) {
        Location blockLocation = point1;
        Location crystalLocation = point2;
        int i1 = 0;
        if (VoidgloomSeraph.NUKEKUBI_DAMAGE.containsKey(p)) {
            i1 = VoidgloomSeraph.NUKEKUBI_DAMAGE.get(p);
        }
        org.bukkit.util.Vector vector = blockLocation.clone().toVector().subtract(crystalLocation.clone().toVector());
        double count = 30.0;
        double length = 0.0;
        for (int i = 1; i <= (int)count; ++i) {
            org.bukkit.util.Vector v = vector.clone().multiply((double)i / count);
            SUtil.delay(() -> point1.getWorld().spigot().playEffect(crystalLocation.clone().add(v), Effect.MAGIC_CRIT, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30), 0L);
        }
    }

    public static void ferocityParticle(LivingEntity e) {
        int side = SUtil.random(0, 1);
        float angle_1 = e.getLocation().clone().add(0.0, 1.5, 0.0).getYaw() / 60.0f;
        Location loc1 = e.getLocation().clone().add(0.0, 1.5, 0.0).add(Math.cos(angle_1), 0.0, Math.sin(angle_1));
        Location loc2 = e.getLocation().clone().add(0.0, 1.5, 0.0).subtract(Math.cos(angle_1), 0.0, Math.sin(angle_1));
        if (side == 1) {
            Location up = loc1.add(0.0, 1.0, 0.0);
            Location down = loc2.add(0.0, -1.0, 0.0);
            Sputnik.drawLine(up, down, 0.0);
        } else {
            Location up2 = loc1.add(0.0, -1.0, 0.0);
            Location down2 = loc2.add(0.0, 1.0, 0.0);
            Sputnik.drawLine(up2, down2, 0.0);
        }
    }

    public static String format(float value) {
        String[] arr = new String[]{"", "k", "M", "B", "T", "P", "E"};
        int index = 0;
        float realvalue = value;
        while (value / 1000.0f >= 1.0f) {
            value /= 1000.0f;
            ++index;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        if (realvalue < 1000000.0f) {
            decimalFormat = new DecimalFormat("#");
        }
        String finalr = String.format("%s%s", decimalFormat.format(value), arr[index]);
        String fin = finalr.replaceAll(",", ".");
        if (realvalue <= 20000.0f && realvalue > 0.0f) {
            fin = String.valueOf((long)Math.round(realvalue));
        }
        return fin;
    }

    public static String formatFull(float value) {
        String[] arr = new String[]{"", "k", "M", "B", "T", "P", "E"};
        int index = 0;
        float realvalue = value;
        while (value / 1000.0f >= 1.0f) {
            value /= 1000.0f;
            ++index;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String finalr = String.format("%s%s", decimalFormat.format(value), arr[index]);
        String fin = finalr.replaceAll(",", ".");
        if (realvalue <= 1000.0f && realvalue > 0.0f) {
            fin = SUtil.commaify(Math.round(realvalue));
        }
        return fin;
    }

    public static void createHelix(final Entity e) {
        final Location loc = e.getLocation();
        final int radius = 1;
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                for (double y = 0.0; y <= 1.5; y += 0.05) {
                    double x = (double)radius * Math.cos(y);
                    double z = (double)radius * Math.sin(y);
                    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.CRIT, true, (float)(loc.getX() + x), (float)(loc.getY() + y), (float)(loc.getZ() + z), 0.0f, 0.0f, 0.0f, 0.0f, 1, new int[0]);
                    for (Player online : e.getWorld().getPlayers()) {
                        ((CraftPlayer)online).getHandle().playerConnection.sendPacket((Packet)packet);
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 5L);
    }

    public static void witherShieldActive2(final Player p) {
        if (!HaveDMGReduction.containsKey(p.getUniqueId())) {
            HaveDMGReduction.put(p.getUniqueId(), false);
        }
        if (CooldownAbs.containsKey(p.getUniqueId())) {
            boolean isActivable = CooldownAbs.get(p.getUniqueId());
            if (!isActivable) {
                CooldownAbs.put(p.getUniqueId(), true);
                HaveDMGReduction.put(p.getUniqueId(), true);
                p.playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 1.25f, 0.25f);
                PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(p.getUniqueId());
                EntityHuman human = ((CraftHumanEntity)p).getHandle();
                double critDmg = statistics.getCritDamage().addAll();
                double absorbsion = critDmg * 100.0 * 300.0 / 100.0;
                float apply = Math.round(absorbsion);
                SputnikPlayer.setCustomAbsorptionHP(p, apply);
                Sputnik.createCircle2(p, 0.7, 1);
                new BukkitRunnable(){

                    public void run() {
                        int finalHP = Math.round(SputnikPlayer.getCustomAbsorptionHP(p) * 50 / 100);
                        SputnikPlayer.setCustomAbsorptionHP(p, 0.0f);
                        p.setHealth(Math.min(p.getMaxHealth(), p.getHealth() + (double)finalHP));
                        HaveDMGReduction.put(p.getUniqueId(), false);
                        new BukkitRunnable(){

                            public void run() {
                                CooldownAbs.put(p.getUniqueId(), false);
                            }
                        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 100L);
                    }
                }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 100L);
            }
        } else {
            CooldownAbs.put(p.getUniqueId(), false);
            Sputnik.witherShieldActive2(p);
        }
    }

    public static void witherShieldActive(final Player p) {
        if (!HaveDMGReduction.containsKey(p.getUniqueId())) {
            HaveDMGReduction.put(p.getUniqueId(), false);
        }
        if (CooldownAbs.containsKey(p.getUniqueId())) {
            boolean isActivable = CooldownAbs.get(p.getUniqueId());
            if (!isActivable) {
                CooldownAbs.put(p.getUniqueId(), true);
                HaveDMGReduction.put(p.getUniqueId(), true);
                p.playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 1.25f, 0.25f);
                PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(p.getUniqueId());
                EntityHuman human = ((CraftHumanEntity)p).getHandle();
                double critDmg = statistics.getCritDamage().addAll();
                double absorbsion = critDmg * 100.0 * 150.0 / 100.0;
                float apply = Math.round(absorbsion);
                SputnikPlayer.setCustomAbsorptionHP(p, apply);
                Sputnik.createCircle(p, 0.7, 1);
                new BukkitRunnable(){

                    public void run() {
                        int finalHP = Math.round(SputnikPlayer.getCustomAbsorptionHP(p) * 50 / 100);
                        SputnikPlayer.setCustomAbsorptionHP(p, 0.0f);
                        p.setHealth(Math.min(p.getMaxHealth(), p.getHealth() + (double)finalHP));
                        HaveDMGReduction.put(p.getUniqueId(), false);
                        new BukkitRunnable(){

                            public void run() {
                                CooldownAbs.put(p.getUniqueId(), false);
                            }
                        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 100L);
                    }
                }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 100L);
            }
        } else {
            CooldownAbs.put(p.getUniqueId(), false);
            Sputnik.witherShieldActive(p);
        }
    }

    private static void createCircle(Player player, double radius, int distance) {
        org.bukkit.util.Vector dist = player.getEyeLocation().getDirection().multiply(distance);
        Location mid = player.getEyeLocation().add(dist);
        int particles = 18;
        for (int i = 0; i < particles; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)particles;
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            org.bukkit.util.Vector v = Sputnik.rotateAroundAxisX(new org.bukkit.util.Vector(x, y, 0.0), player.getEyeLocation().getPitch());
            v = Sputnik.rotateAroundAxisY(v, player.getEyeLocation().getYaw());
            Location temp = mid.clone().add(v);
            player.getWorld().spigot().playEffect(temp, Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
        }
    }

    private static void createCircle2(Player player, double radius, int distance) {
        org.bukkit.util.Vector dist = player.getEyeLocation().getDirection().multiply(distance);
        Location mid = player.getEyeLocation().add(dist);
        int particles = 18;
        for (int i = 0; i < particles; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)particles;
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            org.bukkit.util.Vector v = Sputnik.rotateAroundAxisX(new org.bukkit.util.Vector(x, y, 0.0), player.getEyeLocation().getPitch());
            v = Sputnik.rotateAroundAxisY(v, player.getEyeLocation().getYaw());
            Location temp = mid.clone().add(v);
            player.getWorld().spigot().playEffect(temp, Effect.FLAME, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
        }
    }

    private static org.bukkit.util.Vector rotateAroundAxisX(org.bukkit.util.Vector v, double angle) {
        angle = Math.toRadians(angle);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    private static org.bukkit.util.Vector rotateAroundAxisY(org.bukkit.util.Vector v, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static void playActivateSound(Player p) {
        p.playSound(p.getLocation(), Sound.GHAST_MOAN, 1.0f, 2.0f);
        p.playSound(p.getLocation(), Sound.GHAST_SCREAM2, 1.0f, 2.0f);
        SUtil.delay(() -> p.playSound(p.getLocation(), Sound.GHAST_SCREAM2, 0.5f, 1.5f), 2L);
    }

    public static void playDeActivateSound(Player p) {
        p.playSound(p.getLocation(), Sound.GHAST_SCREAM2, 1.0f, 1.5f);
        p.playSound(p.getLocation(), Sound.GHAST_SCREAM2, 1.0f, 1.1f);
    }

    public static void playFuckingSoundOfVoidgloomThatTookForeverToMake(final Player p, final Entity e) {
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                for (Entity e2 : p.getWorld().getNearbyEntities(p.getLocation(), 20.0, 20.0, 20.0)) {
                    if (!(e2 instanceof Player)) continue;
                    Player player = (Player)e2;
                    player.getWorld().playSound(player.getLocation(), Sound.ZOMBIE_UNFECT, 0.1f, 0.7f);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 1L, 5L);
    }

    public static void moveTo(LivingEntity entity, Location moveTo, float speed) {
        EntityLiving nmsEntity = ((CraftLivingEntity)entity).getHandle();
        PathEntity path = ((EntityInsentient)nmsEntity).getNavigation().a(moveTo.getX(), moveTo.getY(), moveTo.getZ());
        ((EntityInsentient)nmsEntity).getNavigation().a(path, (double)speed);
    }

    public static String buildcustomString(String entityname, Integer level, boolean hideLVL) {
        if (level == 0) {
            return ChatColor.RED + entityname;
        }
        String returnstring = Sputnik.trans("&8[&7Lvl" + level + "&8] &c" + entityname);
        if (hideLVL) {
            returnstring = Sputnik.trans(entityname);
        }
        return returnstring;
    }

    public static String entityNameTag(LivingEntity entity, String customstring) {
        String returnstring = "";
        customstring = Sputnik.trans(customstring);
        int hp = (int)entity.getMaxHealth() / 2;
        int hp1 = (int)entity.getHealth();
        int hp2 = (int)entity.getMaxHealth();
        if (!VoidgloomSeraph.HIT_SHIELD.containsKey(entity)) {
            if (hp1 <= hp && hp2 < 50000 && !entity.hasMetadata("SlayerBoss")) {
                returnstring = ChatColor.RED + customstring + " " + ChatColor.YELLOW + hp1 + ChatColor.GRAY + "/" + ChatColor.GREEN + hp2 + ChatColor.RED + "\u2764";
            } else if (hp1 > hp && hp2 < 50000 && !entity.hasMetadata("SlayerBoss")) {
                returnstring = ChatColor.RED + customstring + " " + ChatColor.GREEN + hp1 + ChatColor.GRAY + "/" + ChatColor.GREEN + hp2 + ChatColor.RED + "\u2764";
            } else if (hp2 > 50000 && hp1 > hp && !entity.hasMetadata("SlayerBoss")) {
                returnstring = ChatColor.RED + customstring + " " + ChatColor.GREEN + Sputnik.format(hp1) + ChatColor.GRAY + "/" + ChatColor.GREEN + Sputnik.format(hp2) + ChatColor.RED + "\u2764";
            } else if (hp2 > 50000 && hp1 <= hp && !entity.hasMetadata("SlayerBoss")) {
                returnstring = ChatColor.RED + customstring + " " + ChatColor.YELLOW + Sputnik.format(hp1) + ChatColor.GRAY + "/" + ChatColor.GREEN + Sputnik.format(hp2) + ChatColor.RED + "\u2764";
            } else if (hp1 <= hp && hp2 < 50000 && entity.hasMetadata("SlayerBoss")) {
                returnstring = ChatColor.RED + customstring + " " + ChatColor.YELLOW + hp1 + ChatColor.RED + "\u2764";
            } else if (hp1 > hp && hp2 < 50000 && entity.hasMetadata("SlayerBoss")) {
                returnstring = ChatColor.RED + customstring + " " + ChatColor.GREEN + hp1 + ChatColor.RED + "\u2764";
            } else if (hp2 > 50000 && hp1 > hp && entity.hasMetadata("SlayerBoss")) {
                returnstring = ChatColor.RED + customstring + " " + ChatColor.GREEN + Sputnik.format(hp1) + ChatColor.RED + "\u2764";
            } else if (hp2 > 50000 && hp1 <= hp && entity.hasMetadata("SlayerBoss")) {
                returnstring = ChatColor.RED + customstring + " " + ChatColor.YELLOW + Sputnik.format(hp1) + ChatColor.RED + "\u2764";
            }
        } else {
            int hitshield = VoidgloomSeraph.HIT_SHIELD.get(entity);
            int hitshieldmax = VoidgloomSeraph.HIT_SHIELD_MAX.get(entity);
            String defineHitShield = Sputnik.trans("&f&l" + hitshield + " Hits");
            if (hitshield <= hitshieldmax / 2 && hitshield > hitshieldmax * 25 / 100) {
                defineHitShield = Sputnik.trans("&d&l" + hitshield + " Hits");
            } else if (hitshield <= hitshieldmax * 25 / 100 && hitshield != 1) {
                defineHitShield = Sputnik.trans("&5&l" + hitshield + " Hits");
            } else if (hitshield == 1) {
                defineHitShield = Sputnik.trans("&5&l" + hitshield + " Hit");
            }
            returnstring = ChatColor.RED + customstring + " " + defineHitShield;
        }
        return returnstring;
    }

    public static PlayerDisguise applyPacketNPC(Entity entity, String skinURLorUsername, String URL_2, boolean isURLSkin) {
        PlayerDisguise playerDisguise = new PlayerDisguise("");
        try {
            Method m = FlagWatcher.class.getDeclaredMethod("setValue", Integer.TYPE, Object.class);
            m.setAccessible(true);
            try {
                m.invoke((Object)playerDisguise.getWatcher(), 10, (byte)127);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        playerDisguise.setShowName(false);
        if (isURLSkin && URL_2 != null) {
            playerDisguise.getGameProfile().getProperties().put("textures", new WrappedSignedProperty("textures", skinURLorUsername, URL_2));
        } else {
            playerDisguise.setSkin(skinURLorUsername);
        }
        playerDisguise.setEntity(entity);
        playerDisguise.startDisguise();
        return playerDisguise;
    }

    public static void applyPacketGiant(Entity entity) {
        MobDisguise d = new MobDisguise(EntityType.GIANT);
        d.setEntity(entity);
        d.startDisguise();
    }

    public static void applyPacketGolem(Entity entity) {
        MobDisguise d = new MobDisguise(EntityType.IRON_GOLEM);
        d.setEntity(entity);
        d.startDisguise();
    }

    public static MobDisguise applyPacketSkeleton(Entity entity) {
        MobDisguise d = new MobDisguise(EntityType.SKELETON);
        d.setEntity(entity);
        d.startDisguise();
        return d;
    }

    public static void sendPacket(World w, Packet pk) {
        for (Player p : w.getPlayers()) {
            EntityPlayer player = ((CraftPlayer)p).getHandle();
            player.playerConnection.sendPacket(pk);
        }
    }

    public static float randomVector() {
        return -0.1f + (float)(Math.random() * 0.2);
    }

    public static void createSphere(final Location loc) {
        new BukkitRunnable(){
            double phi = 0.0;

            public void run() {
                this.phi += 0.3141592653589793;
                for (double theta = 0.0; theta <= Math.PI * 2; theta += 0.09817477042468103) {
                    double r = 1.5;
                    double x = r * Math.cos(theta) * Math.sin(this.phi);
                    double y = r * Math.cos(this.phi) + 1.5;
                    double z = r * Math.sin(theta) * Math.sin(this.phi);
                    loc.add(x, y, z);
                    Location p1 = loc.clone();
                    loc.subtract(x, y, z);
                    p1.getWorld().playEffect(p1, Effect.FLAME, 3);
                    if (!(this.phi > Math.PI * 4)) continue;
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 0L);
    }

    public static final float getAngle(org.bukkit.util.Vector point1, org.bukkit.util.Vector point2) {
        double dx = point2.getX() - point1.getX();
        double dz = point2.getZ() - point1.getZ();
        float angle = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0f;
        if (angle < 0.0f) {
            angle += 360.0f;
        }
        return angle;
    }

    public static void setEyeLocation(Entity entity, Location location1, Location location2) {
        entity.getLocation().setYaw(Sputnik.getAngle(new org.bukkit.util.Vector(location1.getX(), 0.0, location1.getZ()), location2.toVector()));
    }

    private static double distanceSquared(org.bukkit.util.Vector from, org.bukkit.util.Vector to) {
        double dx = to.getBlockX() - from.getBlockX();
        double dz = to.getBlockZ() - from.getBlockZ();
        return dx * dx + dz * dz;
    }

    public static org.bukkit.util.Vector calculateVelocityBlock(org.bukkit.util.Vector from, org.bukkit.util.Vector to, int heightGain) {
        double gravity = 0.115;
        int endGain = to.getBlockY() - from.getBlockY();
        double horizDist = Math.sqrt(Sputnik.distanceSquared(from, to));
        int gain = heightGain;
        double maxGain = gain > endGain + gain ? (double)gain : (double)(endGain + gain);
        double a = -horizDist * horizDist / (4.0 * maxGain);
        double b = horizDist;
        double c = -endGain;
        double slope = -b / (2.0 * a) - Math.sqrt(b * b - 4.0 * a * c) / (2.0 * a);
        double vy = Math.sqrt(maxGain * gravity);
        double vh = vy / slope;
        int dx = to.getBlockX() - from.getBlockX();
        int dz = to.getBlockZ() - from.getBlockZ();
        double mag = Math.sqrt(dx * dx + dz * dz);
        double dirx = (double)dx / mag;
        double dirz = (double)dz / mag;
        double vx = vh * dirx;
        double vz = vh * dirz;
        return new org.bukkit.util.Vector(vx, vy, vz);
    }

    public static org.bukkit.util.Vector r(org.bukkit.util.Vector from, org.bukkit.util.Vector to, int heightGain) {
        double gravity = 0.3;
        int endGain = to.getBlockY() - from.getBlockY();
        double horizDist = Math.sqrt(Sputnik.distanceSquared(from, to));
        int gain = heightGain;
        double maxGain = gain > endGain + gain ? (double)gain : (double)(endGain + gain);
        double a = -horizDist * horizDist / (4.0 * maxGain);
        double b = horizDist;
        double c = -endGain;
        double slope = -b / (2.0 * a) - Math.sqrt(b * b - 4.0 * a * c) / (2.0 * a);
        double vy = Math.sqrt(maxGain * gravity);
        double vh = vy / slope;
        int dx = to.getBlockX() - from.getBlockX();
        int dz = to.getBlockZ() - from.getBlockZ();
        double mag = Math.sqrt(dx * dx + dz * dz);
        double dirx = (double)dx / mag;
        double dirz = (double)dz / mag;
        double vx = vh * dirx;
        double vz = vh * dirz;
        return new org.bukkit.util.Vector(vx, vy, vz);
    }

    public static int itemCount(Player player, String type) {
        int count = 0;
        PlayerInventory inv = player.getInventory();
        for (ItemStack is : inv.getContents()) {
            if (is == null || !SItem.isSpecItem(is) || SItem.of(is) == null || !is.getItemMeta().getDisplayName().contains(type)) continue;
            count += is.getAmount();
        }
        return count;
    }

    public static void zero(Entity killed) {
        if (!(killed instanceof LivingEntity)) {
            return;
        }
        EntityEquipment ep = ((LivingEntity)killed).getEquipment();
        ep.setHelmet(null);
        ep.setChestplate(null);
        ep.setLeggings(null);
        ep.setBoots(null);
        ep.setItemInHand(null);
    }

    public static List<Block> pasteSchematicRep(String schematic, boolean withAir, float lx, float ly, float lz, World w) {
        ArrayList<Block> lb = new ArrayList<Block>();
        File schem = new File(SkySimEngine.getPlugin().getDataFolder() + File.separator + "/schematics/" + schematic + ".schematic");
        BukkitWorld world = new BukkitWorld(w);
        Closer closer = Closer.create();
        FileInputStream fis = null;
        try {
            fis = (FileInputStream)closer.register((Closeable)new FileInputStream(schem));
        }
        catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        BufferedInputStream bis = (BufferedInputStream)closer.register((Closeable)new BufferedInputStream(fis));
        ClipboardReader reader = null;
        try {
            reader = ClipboardFormat.SCHEMATIC.getReader((InputStream)bis);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        Clipboard clipboard = null;
        try {
            clipboard = reader.read(world.getWorldData());
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        Location minYLoc = null;
        int minY = Integer.MAX_VALUE;
        for (int x = 0; x < clipboard.getRegion().getWidth(); ++x) {
            for (int y = 0; y < clipboard.getRegion().getHeight(); ++y) {
                for (int z = 0; z < clipboard.getRegion().getLength(); ++z) {
                    Vector minimumPoint = clipboard.getMinimumPoint();
                    Vector clipboardLoc = new Vector(minimumPoint.getBlockX() + x, minimumPoint.getBlockY() + y, minimumPoint.getBlockZ() + z);
                    BaseBlock baseBlock = clipboard.getBlock(clipboardLoc);
                    if (baseBlock.getId() == 0) continue;
                    Location newLocation = new Location(w, (double)lx, (double)ly, (double)lz).add((double)x, (double)y, (double)z);
                    Vector loc = new Vector(newLocation.getBlockX(), newLocation.getBlockY(), newLocation.getBlockZ());
                    try {
                        world.setBlock(loc, baseBlock);
                        if (y < minY && baseBlock.getId() != 165 && clipboard.getBlock(clipboardLoc.add(0, 1, 0)).getId() == 0 && newLocation.getBlock().getType().isSolid()) {
                            minY = y;
                            minYLoc = newLocation;
                        }
                        lb.add(w.getBlockAt(newLocation));
                        continue;
                    }
                    catch (WorldEditException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            closer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lb;
    }

    public static boolean pasteSchematic(String schematic, boolean withAir, float x, float y, float z, World w) {
        Location location = new Location(w, (double)x, (double)y, (double)z);
        try {
            Clipboard clipboard;
            Vector pasteLocation = new Vector(location.getX(), location.getY(), location.getZ());
            BukkitWorld pasteWorld = new BukkitWorld(location.getWorld());
            WorldData pasteWorldData = pasteWorld.getWorldData();
            File schem = new File(SkySimEngine.getPlugin().getDataFolder() + File.separator + "/schematics/" + schematic + ".schematic");
            try {
                clipboard = ClipboardFormat.SCHEMATIC.getReader((InputStream)new FileInputStream(schem)).read(pasteWorldData);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard, pasteWorldData);
            EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession((LocalWorld)pasteWorld, -1);
            Operation operation = clipboardHolder.createPaste((Extent)editSession, pasteWorldData).to(pasteLocation).ignoreAirBlocks(!withAir).build();
            Operations.complete((Operation)operation);
            return true;
        }
        catch (WorldEditException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static String formatTime(int z) {
        int seconds = z;
        int p1 = seconds % 60;
        int p2 = seconds / 60;
        int p3 = p2 % 60;
        String a = String.valueOf(p1);
        String b = String.valueOf(p2 /= 60);
        String c = String.valueOf(p3);
        if (p1 < 10) {
            a = "0" + String.valueOf(p1);
        }
        if (p2 < 10) {
            b = "0" + String.valueOf(p2);
        }
        if (p3 < 10) {
            c = "0" + String.valueOf(p3);
        }
        if (p2 == 0) {
            return c + "m " + a + "s";
        }
        return b + "h " + c + "m " + a + "s";
    }

    public static int runningFloors() {
        int i = 0;
        for (World w : Bukkit.getWorlds()) {
            if (!w.getName().contains("f6") || w.getName().equals("f6")) continue;
            ++i;
        }
        return i;
    }

    public static int rf_() {
        int i = 0;
        if (RunThisSession.containsKey(Bukkit.getServer())) {
            i = RunThisSession.get(Bukkit.getServer());
        } else {
            RunThisSession.put(Bukkit.getServer(), 0);
        }
        return i;
    }

    public static void startRoom(Player player) {
        if (Sputnik.runningFloors() >= 5) {
            player.sendMessage(Sputnik.trans("&cSorry! The number of running rooms has reached 5, please try again in a moment. You can support the server so we can handle more rooms at &bhttps://store.skysim.sbs"));
            BossMenu.ableToJoin.put(player, true);
            return;
        }
        SkySimEngine plugin = SkySimEngine.getPlugin();
        plugin.config.set("runMade", plugin.config.getLong("runMade") + 1L);
        plugin.config.save();
        ArrayList<Player> plist = new ArrayList<Player>();
        plist.add(player);
        SUtil.delay(() -> player.sendMessage(ChatColor.GREEN + "Entering The Catacombs Demo - Floor 6!"), 10L);
        SUtil.delay(() -> player.sendMessage(ChatColor.GRAY + "Preparing the boss for you, please wait..."), 20L);
        SUtil.delay(() -> player.sendMessage(ChatColor.GRAY + "Hooking up request, and sending you to that world..."), 30L);
        SUtil.delay(() -> player.sendMessage(ChatColor.GRAY + " "), 40L);
        SUtil.delay(() -> SadanBossManager.startFloor(plist), 50L);
        SUtil.delay(() -> BossMenu.ableToJoin.put(player, true), 60L);
    }

    public static Integer dmgc(int damage, Player p, Entity e) {
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(p.getUniqueId());
        if (statistics == null) {
            return 0;
        }
        double defense = statistics.getDefense().addAll();
        int dmglater = (int)Math.round((double)damage - (double)damage * (defense / (defense + 100.0)));
        User.getUser(p.getUniqueId()).damage(dmglater, EntityDamageEvent.DamageCause.ENTITY_ATTACK, e);
        p.damage(1.0E-6, null);
        return dmglater;
    }

    public static double calculateMagicDamage(Entity entity, Player player, int baseMagicDmg, double scale) {
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
        int manaPool = SUtil.blackMagic(100.0 + statistics.getIntelligence().addAll());
        User user = User.getUser(player.getUniqueId());
        PlayerInventory inv = player.getInventory();
        SItem helmet = SItem.find(inv.getHelmet());
        if (helmet != null) {
            if (helmet.getType() == SMaterial.DARK_GOGGLES) {
                baseMagicDmg += baseMagicDmg * 25 / 100;
            } else if (helmet.getType() == SMaterial.SHADOW_GOGGLES) {
                baseMagicDmg += baseMagicDmg * 35 / 100;
            } else if (helmet.getType() == SMaterial.WITHER_GOGGLES) {
                baseMagicDmg += baseMagicDmg * 45 / 100;
            }
        }
        Pet pet = user.getActivePetClass();
        Pet.PetItem active1 = user.getActivePet();
        if (active1 != null && pet.getDisplayName().equals("Magicivy")) {
            Pet.PetItem active = user.getActivePet();
            int level = Pet.getLevel(active.getXp(), active.getRarity());
            baseMagicDmg = (int)((float)baseMagicDmg + (float)baseMagicDmg * ((float)level / 100.0f));
        }
        double baseDamage = (double)baseMagicDmg * ((double)(manaPool / 100) * scale + 1.0);
        if (EntityManager.DEFENSE_PERCENTAGE.containsKey(entity)) {
            int defensepercent = EntityManager.DEFENSE_PERCENTAGE.get(entity);
            if (defensepercent > 100) {
                defensepercent = 100;
            }
            baseDamage -= baseDamage * (double)defensepercent / 100.0;
        }
        return baseDamage;
    }

    public static PlayerUtils.DamageResult calculateNormalDamage(Entity entity, Player player, int baseDamage) {
        ItemStack weapon = player.getInventory().getItemInHand();
        PlayerUtils.DamageResult result = PlayerUtils.getDamageDealt(weapon, player, entity, player instanceof Arrow);
        return result;
    }

    public static void playHitShieldParticle(final Entity e) {
        new BukkitRunnable(){
            int loc = 0;

            public void run() {
                ArrayList locs = Sputnik.getCircle(e.getLocation(), 0.5, 40);
                e.getWorld().spigot().playEffect((Location)locs.get(this.loc), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                ++this.loc;
                if (this.loc == 40) {
                    this.loc = 0;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    public static void playSoulWell(final Entity e, String id) {
        final HashMap S = new HashMap();
        MAP_PARTICLE_1.put(e, e.getLocation());
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)SkySimEngine.getPlugin(), new Runnable(){
            Random random = new Random();

            void startSoulWell() {
                final int num = this.random.nextInt(Integer.MAX_VALUE);
                S.put(num, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)SkySimEngine.getPlugin(), new Runnable(){
                    Location height;
                    int loc;
                    int lifeSpan;
                    {
                        this.height = MAP_PARTICLE_1.get(e);
                        this.loc = 0;
                        this.lifeSpan = 0;
                    }

                    @Override
                    public void run() {
                        ArrayList locs = Sputnik.getCircle(this.height, 0.5, 40);
                        MAP_PARTICLE_1.get(e).getWorld().spigot().playEffect((Location)locs.get(this.loc), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                        ++this.loc;
                        ++this.lifeSpan;
                        this.height.add(0.0, 0.045, 0.0);
                        if (this.loc == 40) {
                            this.loc = 0;
                        }
                        if (this.lifeSpan == 40) {
                            Bukkit.getScheduler().cancelTask(((Integer)S.get(num)).intValue());
                            S.remove(num);
                        }
                    }
                }, 0L, 1L));
            }

            @Override
            public void run() {
                this.startSoulWell();
            }
        }, 0L, 1L);
        new BukkitRunnable(){

            public void run() {
                MAP_PARTICLE_1.put(e, e.getLocation());
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    private static ArrayList<Location> getCircle(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = Math.PI * 2 / (double)amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for (int i = 0; i < amount; ++i) {
            double angle = (double)i * increment;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

    private static ArrayList<Location> getCircleReverse(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = Math.PI * 2 / (double)amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for (int i = 0; i < amount; ++i) {
            double angle = (double)i * increment;
            double x = center.getX() - radius * Math.cos(angle);
            double z = center.getZ() - radius * Math.sin(angle);
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

    public static void setTotalExperience(Player player, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Experience is negative!");
        }
        player.setExp(0.0f);
        player.setLevel(0);
        player.setTotalExperience(0);
        int amount = exp;
        while (amount > 0) {
            int expToLevel = Sputnik.getExpAtLevel(player);
            if ((amount -= expToLevel) >= 0) {
                player.giveExp(expToLevel);
                continue;
            }
            player.giveExp(amount += expToLevel);
            amount = 0;
        }
    }

    private static int getExpAtLevel(Player player) {
        return Sputnik.getExpAtLevel(player.getLevel());
    }

    public static int getExpAtLevel(int level) {
        if (level > 29) {
            return 62 + (level - 30) * 7;
        }
        if (level > 15) {
            return 17 + (level - 15) * 3;
        }
        return 17;
    }

    public static int getExpToLevel(int level) {
        int exp = 0;
        for (int currentLevel = 0; currentLevel < level; ++currentLevel) {
            exp += Sputnik.getExpAtLevel(currentLevel);
        }
        if (exp < 0) {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }

    public static int getTotalExperience(Player player) {
        int exp = Math.round((float)Sputnik.getExpAtLevel(player) * player.getExp());
        int currentLevel = player.getLevel();
        while (currentLevel > 0) {
            exp += Sputnik.getExpAtLevel(--currentLevel);
        }
        if (exp < 0) {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }

    public static int getExpUntilNextLevel(Player player) {
        int exp = Math.round((float)Sputnik.getExpAtLevel(player) * player.getExp());
        int nextLevel = player.getLevel();
        return Sputnik.getExpAtLevel(nextLevel) - exp;
    }

    public static void tradeIntitize(final Player target, final Player p) {
        if (SkySimEngine.getPlugin() != null && !SkySimEngine.getPlugin().config.getBoolean("enableTrade")) {
            p.sendMessage(Sputnik.trans("&cTrading has been temporary disabled!"));
            return;
        }
        if (p == target) {
            p.sendMessage(Sputnik.trans("&cYou cannot trade with yourself!"));
            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
            return;
        }
        UUID uuid = UUID.randomUUID();
        if (!target.isOnline()) {
            p.sendMessage(Sputnik.trans("&cCannot find player with that name, maybe they've gone offline?"));
            p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
            return;
        }
        if (!p.getWorld().equals(target.getWorld())) {
            p.sendMessage(ChatColor.RED + "You can't trade with that player!");
            p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
            return;
        }
        if (p.getLocation().distance(target.getLocation()) > 5.0) {
            p.sendMessage(ChatColor.RED + "You are too far away to trade with that player!");
            p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
            return;
        }
        if (TradeUtil.hasRequest(target, p)) {
            p.sendMessage(ChatColor.RED + "Woah there! You already have an /trade request");
            p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
            return;
        }
        new BukkitRunnable(){
            int t = 0;

            public void run() {
                ++this.t;
                if (TradeUtil.isTrading(p) || TradeUtil.isTrading(target)) {
                    this.cancel();
                    return;
                }
                if (!(this.t < 200 || TradeUtil.hasRequest(p, target) || TradeUtil.isTrading(p) || TradeUtil.isTrading(target))) {
                    this.cancel();
                    p.sendMessage(Sputnik.trans("&cThe /trade request to " + target.getDisplayName() + " &cexpired!"));
                    target.sendMessage(Sputnik.trans("&cThe /trade request from " + p.getDisplayName() + " &cexpired!"));
                    p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                    target.playSound(target.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                    TradeUtil.resetTrade(p);
                    TradeUtil.resetTrade(target);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        if (TradeUtil.hasRequest(p, target)) {
            p.playSound(p.getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
            target.playSound(target.getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
            new TradeMenu(p, target, uuid).open();
            return;
        }
        p.playSound(p.getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
        target.playSound(target.getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
        p.sendMessage(Sputnik.trans("&aYou have sent a trade request to &b" + target.getDisplayName() + "&a."));
        TextComponent message = new TextComponent(Sputnik.trans("&b" + p.getName() + " &ahas sent you a trade request. &bClick here &ato accept."));
        UUID accessKey = UUID.randomUUID();
        AccessTimedCommand.KEYS.add(accessKey);
        SUtil.delay(() -> AccessTimedCommand.KEYS.remove(accessKey), 200L);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (BaseComponent[])new TextComponent[]{new TextComponent(ChatColor.GOLD + "Click to trade!")}));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/attc " + accessKey + " " + p.getName()));
        target.spigot().sendMessage((BaseComponent)message);
        TradeUtil.requestTrade(p, target);
    }

    public static ArmorStand spawnStaticDialougeBox(final Entity e, Location l) {
        final ArmorStand as = (ArmorStand)e.getWorld().spawn(l, ArmorStand.class);
        as.setVisible(false);
        as.setMarker(true);
        as.setCustomNameVisible(false);
        as.setGravity(false);
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    SUtil.delay(() -> as.remove(), 0L);
                    this.cancel();
                    return;
                }
                if (as.isDead()) {
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        return as;
    }

    public static ArmorStand spawnStaticDialougeBox(final Entity e, double yoffset) {
        final ArmorStand as = (ArmorStand)e.getWorld().spawn(e.getLocation().add(0.0, yoffset, 0.0), ArmorStand.class);
        as.setVisible(false);
        as.setMarker(true);
        as.setCustomNameVisible(false);
        as.setGravity(false);
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    SUtil.delay(() -> as.remove(), 0L);
                    this.cancel();
                    return;
                }
                if (as.isDead()) {
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        return as;
    }

    public static ArmorStand spawnDialougeBox(final Entity e, final double yoffset) {
        final ArmorStand as = (ArmorStand)e.getWorld().spawn(e.getLocation().add(0.0, yoffset, 0.0), ArmorStand.class);
        as.setVisible(false);
        as.setMarker(true);
        as.setCustomNameVisible(false);
        as.setGravity(false);
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    SUtil.delay(() -> as.remove(), 20L);
                    this.cancel();
                    return;
                }
                if (as.isDead()) {
                    this.cancel();
                    return;
                }
                as.teleport(e.getLocation().add(0.0, yoffset, 0.0));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        return as;
    }

    public static String createStarStringFrom(SItem sitem) {
        int amount = sitem.getDataInt("itemStar");
        if (amount <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        String star = "&6\u272a";
        String mstar = "&c\u272a";
        int iam = 0;
        iam = Math.min(5, amount);
        int iams = 0;
        iams = Math.min(10, amount);
        if (amount <= 5) {
            for (int i = 0; i < 5; ++i) {
                sb.append(star);
                if (i < iam - 1) {
                    continue;
                }
                break;
            }
        } else {
            iams = amount - 5;
            for (int i = 0; i < 5; ++i) {
                if (iams > i) {
                    sb.append(mstar);
                    continue;
                }
                sb.append(star);
            }
        }
        return Sputnik.trans(sb.toString());
    }

    public static String createStarStringFromAmount(int amount) {
        if (amount <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        String star = "&6\u272a";
        String mstar = "&c\u272a";
        int iam = 0;
        iam = Math.min(5, amount);
        int iams = 0;
        iams = Math.min(10, amount);
        if (amount <= 5) {
            for (int i = 0; i < 5; ++i) {
                sb.append(star);
                if (i < iam - 1) {
                    continue;
                }
                break;
            }
        } else {
            iams = amount - 5;
            for (int i = 0; i < 5; ++i) {
                if (iams > i) {
                    sb.append(mstar);
                    continue;
                }
                sb.append(star);
            }
        }
        return Sputnik.trans(sb.toString());
    }

    public static Object[] calculateDamage(Player player, Player damager, ItemStack weapon, LivingEntity damaged, boolean isBow) {
        PlayerUtils.DamageResult result = PlayerUtils.getDamageDealt(weapon, player, (Entity)damaged, isBow);
        float displayDmg = new AtomicDouble(result.getFinalDamage()).floatValue();
        if (EntityManager.DEFENSE_PERCENTAGE.containsKey(damaged)) {
            int defensepercent = EntityManager.DEFENSE_PERCENTAGE.get(damaged);
            if (defensepercent > 100) {
                defensepercent = 100;
            }
            displayDmg -= displayDmg * (float)defensepercent / 100.0f;
        }
        return new Object[]{Float.valueOf(new AtomicDouble(result.getFinalDamage()).floatValue()), result.didCritDamage(), Float.valueOf(displayDmg)};
    }

    public static double calMagicDamage(Player p, int baseDamage, double mult) {
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(p.getUniqueId());
        int manaPool = SUtil.blackMagic(100.0 + statistics.getIntelligence().addAll());
        int baseMagicDmg1 = baseDamage;
        baseMagicDmg1 = (int)((double)baseMagicDmg1 + (double)baseMagicDmg1 * (statistics.getAbilityDamage().addAll() / 100.0));
        PlayerInventory inv = p.getInventory();
        SItem helmet = SItem.find(inv.getHelmet());
        if (helmet != null) {
            if (helmet.getType() == SMaterial.DARK_GOGGLES) {
                baseMagicDmg1 += baseMagicDmg1 * 25 / 100;
            } else if (helmet.getType() == SMaterial.SHADOW_GOGGLES) {
                baseMagicDmg1 += baseMagicDmg1 * 35 / 100;
            } else if (helmet.getType() == SMaterial.WITHER_GOGGLES) {
                baseMagicDmg1 += baseMagicDmg1 * 45 / 100;
            }
        }
        return (double)baseMagicDmg1 * ((double)(manaPool / 100) * mult + 1.0);
    }

    public static void sendWebhook(String content) {
        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/935193761795940404/3IdoSzkoXBU8UQb-X_mfizQgXYZZYiQ61FH9KPgm-gaeuUGjfhoTKvaWUFiQjwh55jKN");
        webhook.setUsername("SkySim Logger Assistant [v0.1-BETA]");
        webhook.setAvatarUrl("https://cdn.discordapp.com/attachments/884749251568082964/935357971368656916/AAAAA.png");
        webhook.setContent(content);
        try {
            webhook.execute();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendAsyncWebhook(final String content) {
        new BukkitRunnable(){

            public void run() {
                DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/935193761795940404/3IdoSzkoXBU8UQb-X_mfizQgXYZZYiQ61FH9KPgm-gaeuUGjfhoTKvaWUFiQjwh55jKN");
                webhook.setUsername("SkySim Logger Assistant [v0.1-BETA]");
                webhook.setAvatarUrl("https://cdn.discordapp.com/attachments/884749251568082964/935357971368656916/AAAAA.png");
                webhook.setContent(content);
                try {
                    webhook.execute();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously((Plugin)SkySimEngine.getPlugin());
    }

    public static void sendEatingAnimation(LivingEntity e) {
        net.minecraft.server.v1_8_R3.ItemStack itemstack = CraftItemStack.asNMSCopy((ItemStack)e.getEquipment().getItemInHand());
        Location l = e.getLocation();
        float pitch = l.getPitch();
        float yaw = l.getYaw();
        Random random = new Random();
        if (itemstack.m() == EnumAnimation.EAT) {
            for (int j = 0; j < 5; ++j) {
                Vec3D vec3d = new Vec3D(((double)random.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
                vec3d = vec3d.a(-pitch * 3.141593f / 180.0f);
                vec3d = vec3d.b(-yaw * 3.141593f / 180.0f);
                double d0 = (double)(-random.nextFloat()) * 0.6 - 0.3;
                Vec3D vec3d1 = new Vec3D(((double)random.nextFloat() - 0.5) * 0.3, d0, 0.6);
                vec3d1 = vec3d1.a(-pitch * 3.141593f / 180.0f);
                vec3d1 = vec3d1.b(-yaw * 3.141593f / 180.0f);
                vec3d1 = vec3d1.add(l.getX(), l.getY() + (double)1.69f, l.getZ());
                Sputnik.sendPacket(e.getWorld(), (Packet)new PacketPlayOutWorldParticles(EnumParticle.ITEM_CRACK, true, (float)vec3d1.a, (float)vec3d1.b, (float)vec3d1.c, (float)vec3d.a, (float)((double)((float)vec3d.b) + 0.05), (float)vec3d.c, 0.75f, 0, new int[]{Item.getId((Item)itemstack.getItem()), itemstack.getData()}));
            }
            e.getWorld().playSound(l, Sound.EAT, 0.5f + 0.5f * (float)random.nextInt(2), (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f);
        }
    }

    public static String roundComma(float i) {
        return new BigDecimal(i).setScale(1, RoundingMode.HALF_EVEN).toPlainString();
    }

    public static ItemChest makeChestItemLoot(Location loc, ItemStack type, boolean locked, byte rot) {
        loc.getBlock().setType(Material.CHEST);
        Block bl = loc.getBlock();
        bl.setData(rot);
        ItemChest bc = new ItemChest(type, bl, rot);
        bc.setLocked(locked);
        return bc;
    }

    public static BlessingChest makeChestBlessings(Location loc, Blessings type, boolean locked, byte rot) {
        loc.getBlock().setType(Material.CHEST);
        Block bl = loc.getBlock();
        bl.setData(rot);
        BlessingChest bc = new BlessingChest(type, bl, rot);
        bc.setLocked(locked);
        return bc;
    }

    public static boolean tpAbilUsable(Player p) {
        if (p.getWorld().getName().contains("arena") && SkySimEngine.getPlugin().dimoon != null) {
            return SkySimEngine.getPlugin().dimoon.stunned;
        }
        return true;
    }

    public static void teleport(Player p, Location loc) {
        if (!Sputnik.tpAbilUsable(p)) {
            p.sendMessage(Sputnik.trans("&cA Magicial Force have stopped you from using this teleport ability!"));
            return;
        }
        p.teleport(loc);
    }

    public static PositionSongPlayer playNativeSound(String filename, int radius, int volume, boolean loop, final Location loc) {
        Song song = NBSDecoder.parse((File)new File(SkySimEngine.getPlugin().getDataFolder() + File.separator + "/songs/" + filename + ".nbs"));
        final PositionSongPlayer esp = new PositionSongPlayer(song);
        esp.setDistance(radius);
        esp.setVolume((byte)100);
        esp.setLoop(loop);
        esp.setTargetLocation(loc);
        loc.getWorld().getPlayers().forEach(pl -> esp.addPlayer(pl));
        esp.setPlaying(true);
        new BukkitRunnable(){

            public void run() {
                if (!esp.isPlaying()) {
                    this.cancel();
                    return;
                }
                for (Player p : loc.getWorld().getPlayers()) {
                    if (esp.getPlayerUUIDs().contains(p.getUniqueId())) continue;
                    esp.addPlayer(p);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        return esp;
    }

    public static void playSound(File filename, int radius, int volume, boolean loop, final Player p, final Location loc) {
        Song song = NBSDecoder.parse((File)filename);
        final PositionSongPlayer esp = new PositionSongPlayer(song);
        esp.setDistance(radius);
        esp.setVolume((byte)100);
        esp.setLoop(loop);
        esp.setTargetLocation(loc);
        p.getLocation().getWorld().getPlayers().forEach(pl -> esp.addPlayer(pl));
        esp.setPlaying(true);
        User.getUser(p.getUniqueId()).setPlayingSong(true);
        new BukkitRunnable(){

            public void run() {
                if (p == null || !p.isOnline()) {
                    esp.destroy();
                    this.cancel();
                    return;
                }
                if (!User.getUser(p.getUniqueId()).isPlayingSong()) {
                    esp.destroy();
                    this.cancel();
                    return;
                }
                for (Player p2 : loc.getWorld().getPlayers()) {
                    if (esp.getPlayerUUIDs().contains(p2.getUniqueId())) continue;
                    esp.addPlayer(p2);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }
}

