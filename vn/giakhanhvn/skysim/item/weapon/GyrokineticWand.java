/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.EnderDragon
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.item.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.AbilityActivation;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Ownable;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.ShapedRecipe;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.WandStatistics;
import vn.giakhanhvn.skysim.slayer.SlayerBossType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.BlockFallAPI;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class GyrokineticWand
implements WandStatistics,
MaterialFunction,
Ability,
Ownable {
    @Override
    public String getDisplayName() {
        return "Gyrokinetic Wand";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.WAND;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.WAND;
    }

    @Override
    public String getLore() {
        return null;
    }

    @Override
    public String getAbilityName() {
        return "Gravity Storm";
    }

    @Override
    public AbilityActivation getAbilityActivation() {
        return AbilityActivation.LEFT_CLICK;
    }

    @Override
    public String getAbilityDescription() {
        return Sputnik.trans("Create a large &5rift &7at aimed location, pulling all mobs together.");
    }

    @Override
    public boolean requirementsUse(Player player, SItem sItem) {
        return SlayerBossType.SlayerMobType.ENDERMAN.getLevelForXP(User.getUser(player.getUniqueId()).getEndermanSlayerXP()) < 6;
    }

    @Override
    public String getAbilityReq() {
        return "&cYou do not have requirement to use this item!\n&cYou need &5Enderman Slayer 6 &cto use it!\n&eTalk to Maddox in the Hub to unlock the requirement!";
    }

    @Override
    public void onAbilityUse(Player player, SItem sItem) {
        this.startGyrowandAbility(player);
        Repeater.MANA_REGEN_DEC.put(player.getUniqueId(), true);
        SUtil.delay(() -> Repeater.MANA_REGEN_DEC.put(player.getUniqueId(), false), 60L);
    }

    public void pullingMobsTo(final Location l) {
        for (final Entity entity : l.getWorld().getNearbyEntities(l, 8.0, 8.0, 8.0)) {
            if (entity.isDead() || !(entity instanceof LivingEntity) || entity.hasMetadata("GiantSword") || entity.hasMetadata("NoAffect") || entity instanceof Player || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand || entity instanceof EnderDragon) continue;
            Location loc = entity.getLocation().clone();
            new BukkitRunnable(){
                Location look;
                int t;

                public void run() {
                    if (this.t >= 25) {
                        this.cancel();
                        return;
                    }
                    this.look = entity.getLocation().setDirection(l.toVector().subtract(entity.getLocation().toVector()));
                    this.look.add(this.look.getDirection().normalize().multiply(1));
                    if (entity.getLocation().distance(l) > 0.5) {
                        Location nl = new Location(this.look.getWorld(), this.look.getX(), this.look.getY(), this.look.getZ(), entity.getLocation().getYaw(), entity.getLocation().getPitch());
                        if (!entity.hasMetadata("LD")) {
                            entity.teleport(nl);
                        } else {
                            ((CraftZombie)entity).getHandle().setPositionRotation(nl.getX(), nl.getY(), nl.getZ(), nl.getYaw(), nl.getPitch());
                        }
                    } else {
                        Location lc = entity.getLocation();
                        lc.setY(entity.getLocation().getY() + 0.5);
                        if (!entity.hasMetadata("LD")) {
                            entity.teleport(lc);
                        } else {
                            ((CraftZombie)entity).getHandle().setPositionRotation(lc.getX(), lc.getY(), lc.getZ(), lc.getYaw(), lc.getPitch());
                        }
                    }
                    ++this.t;
                }
            }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
        }
    }

    public void cylinderReset(Location loc, int r) {
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        World w = loc.getWorld();
        int rSquared = r * r;
        for (int x = cx - r; x <= cx + r; ++x) {
            for (int z = cz - r; z <= cz + r; ++z) {
                if ((cx - x) * (cx - x) + (cz - z) * (cz - z) > rSquared) continue;
                Location l = new Location(w, (double)x, (double)cy, (double)z);
                l.getBlock().getState().update();
            }
        }
    }

    public void startGyrowandAbility(Player p) {
        Location loc = p.getLocation();
        Location sloc = loc.clone().add(loc.getDirection().multiply(10));
        if (sloc.getBlock().getType() == Material.AIR) {
            Location cacheLocation = sloc.getBlock().getLocation();
            for (int y = cacheLocation.getBlockY(); y > 0; --y) {
                if (cacheLocation.subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR) continue;
                for (int i = 0; i < 40; ++i) {
                    this.a(cacheLocation.clone().add(0.0, 1.0, 0.0), 0 + i * 12);
                }
                this.pullingMobsTo(cacheLocation.clone().add(0.0, 1.0, 0.0));
                break;
            }
        } else if (sloc.getBlock().getType() != Material.AIR) {
            Location cacheLocation = sloc.getBlock().getLocation();
            for (int y = cacheLocation.getBlockY(); y > 0; ++y) {
                if (cacheLocation.add(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR) continue;
                for (int i = 0; i < 40; ++i) {
                    this.a(cacheLocation.clone().add(0.0, 0.0, 0.0), 0 + i * 12);
                }
                this.pullingMobsTo(cacheLocation.clone().add(0.0, 0.0, 0.0));
                break;
            }
        }
        this.gyroWandActive(p, loc, 8, 6);
        SUtil.delay(() -> this.gyroWandActive(p, loc, 8, 6), 5L);
        SUtil.delay(() -> this.gyroWandActive(p, loc, 6, 4), 10L);
        SUtil.delay(() -> this.gyroWandActive(p, loc, 4, 2), 15L);
        SUtil.delay(() -> this.gyroWandActive(p, loc, 3, 1), 20L);
        SUtil.delay(() -> this.gyroWandActive(p, loc, 2, 1), 25L);
        SUtil.delay(() -> this.gyroWandActive(p, loc, 1, 0), 30L);
        SUtil.delay(() -> this.cylinderReset(loc, 10), 32L);
    }

    public void gyroWandActive(Player player, Location loc, int arg1, int arg2) {
        block2: {
            Location sloc;
            block3: {
                sloc = loc.clone().add(loc.getDirection().multiply(10));
                if (sloc.getBlock().getType() != Material.AIR) break block3;
                Location cacheLocation = sloc.getBlock().getLocation();
                for (int y = cacheLocation.getBlockY(); y > 0; --y) {
                    if (cacheLocation.subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR) continue;
                    this.gyroWand(player, cacheLocation.add(0.0, 0.0, 0.0), arg1, arg2);
                    this.cylinderReset(cacheLocation.add(0.0, 0.0, 0.0), 10);
                    break block2;
                }
                break block2;
            }
            if (sloc.getBlock().getType() == Material.AIR) break block2;
            Location cacheLocation = sloc.getBlock().getLocation();
            for (int y = cacheLocation.getBlockY(); y > 0; ++y) {
                if (cacheLocation.add(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR) continue;
                this.gyroWand(player, cacheLocation.clone().add(0.0, -1.0, 0.0), arg1, arg2);
                this.cylinderReset(cacheLocation.add(0.0, -1.0, 0.0), 10);
                break;
            }
        }
    }

    public void gyroWand(Player p, Location l, int arg0, int arg1) {
        Material[] mat = new Material[]{Material.OBSIDIAN, Material.AIR, Material.STAINED_GLASS, Material.STAINED_CLAY, Material.AIR};
        Material[] mat_r = new Material[]{Material.OBSIDIAN, Material.STAINED_GLASS, Material.STAINED_CLAY};
        List<Block> a = this.cylinder(l, arg0);
        List<Block> b = this.cylinder(l, arg1);
        for (Block bl : new ArrayList<Block>(a)) {
            if (!b.contains(bl)) continue;
            a.remove(bl);
        }
        ArrayList<Location> aA = new ArrayList<Location>();
        for (Block bl : a) {
            aA.add(bl.getLocation().add(0.5, 0.0, 0.5));
        }
        if (arg1 != 0) {
            for (Location loc : aA) {
                byte data = 0;
                int r = GyrokineticWand.random(0, 4);
                Material mats = mat[r];
                if (mats == Material.STAINED_GLASS) {
                    data = 2;
                } else if (mats == Material.STAINED_CLAY) {
                    data = 11;
                }
                BlockFallAPI.sendVelocityBlock(loc, mats, data, loc.getWorld(), 10, new Vector(0.0, 0.225, 0.0));
            }
        } else {
            for (Location loc : aA) {
                byte data = 0;
                int r = GyrokineticWand.random(0, 2);
                Material mats = mat_r[r];
                if (mats == Material.STAINED_GLASS) {
                    data = 2;
                } else if (mats == Material.STAINED_CLAY) {
                    data = 11;
                }
                BlockFallAPI.sendVelocityBlock(loc, mats, data, loc.getWorld(), 10, new Vector(0.0, 0.225, 0.0));
                BlockFallAPI.sendVelocityBlock(l.getBlock().getLocation().add(0.5, 0.0, 0.5), mats, data, loc.getWorld(), 10, new Vector(0.0, 0.225, 0.0));
            }
        }
    }

    public static int random(int min, int max) {
        if (min < 0) {
            min = 0;
        }
        if (max < 0) {
            max = 0;
        }
        return new Random().nextInt(max - min + 1) + min;
    }

    public List<Block> cylinder(Location loc, int r) {
        ArrayList<Block> bl = new ArrayList<Block>();
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        World w = loc.getWorld();
        int rSquared = r * r;
        for (int x = cx - r; x <= cx + r; ++x) {
            for (int z = cz - r; z <= cz + r; ++z) {
                if ((cx - x) * (cx - x) + (cz - z) * (cz - z) > rSquared) continue;
                Location l = new Location(w, (double)x, (double)cy, (double)z);
                bl.add(l.getBlock());
            }
        }
        return bl;
    }

    public void a(final Location location, final float startYaw) {
        new BukkitRunnable(){
            float cout;
            int b;
            double c;
            {
                this.cout = startYaw;
                this.b = 0;
                this.c = 8.0;
            }

            public void run() {
                if (this.b >= 22) {
                    this.cancel();
                    return;
                }
                Location loc = location.clone();
                ++this.b;
                loc.setYaw(this.cout);
                loc.setPitch(0.0f);
                if (this.c > 0.0) {
                    this.c -= 0.3;
                }
                loc.add(loc.getDirection().normalize().multiply(this.c));
                location.getWorld().spigot().playEffect(loc, Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                location.getWorld().spigot().playEffect(loc, Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                this.cout += 10.0f;
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 300;
    }

    @Override
    public int getManaCost() {
        return 1500;
    }

    @Override
    public void load() {
        ShapedRecipe recipe = new ShapedRecipe(SMaterial.HIDDEN_GYROKINETIC_WAND);
        recipe.shape("a", "b", "c");
        recipe.set('a', SMaterial.HIDDEN_GYRO_EYE);
        recipe.set('b', SMaterial.HIDDEN_REFINED_POWDER);
        recipe.set('c', SMaterial.HIDDEN_COMPRESSED_BITS, 5);
    }

    @Override
    public NBTTagCompound getData() {
        return WandStatistics.super.getData();
    }
}

