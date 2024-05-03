/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.EnderDragon
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.EulerAngle
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.item.pet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.item.pet.PetAbility;
import vn.giakhanhvn.skysim.skill.CombatSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class MagicivyPet
extends Pet {
    public static final Map<Player, Boolean> COOLDOWN = new HashMap<Player, Boolean>();

    @Override
    public List<PetAbility> getPetAbilities(SItem instance) {
        final int level = MagicivyPet.getLevel(instance);
        BigDecimal flameArrow = new BigDecimal((double)level * 0.2).setScale(1, RoundingMode.HALF_EVEN);
        final BigDecimal darkbli = new BigDecimal((double)level * 0.2).setScale(1, RoundingMode.HALF_EVEN);
        final BigDecimal blessed = new BigDecimal(level * 1).setScale(1, RoundingMode.HALF_EVEN);
        ArrayList<PetAbility> abilities = new ArrayList<PetAbility>(Collections.singletonList(new PetAbility(){

            @Override
            public String getName() {
                return "Wizarding World";
            }

            @Override
            public List<String> getDescription(SItem instance) {
                return Arrays.asList(Sputnik.trans("&7Grants &a+" + blessed.toPlainString() + " &c\u0e51 Ability Damage"));
            }
        }));
        if (instance.getRarity().isAtLeast(Rarity.EPIC)) {
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "Dark Blizzard";
                }

                @Override
                public List<String> getDescription(SItem instance) {
                    return Arrays.asList(Sputnik.trans("&7While taking damage from mobs, grants &a" + darkbli.toPlainString() + "%"), Sputnik.trans("&7to cast a &dmagic &7spell that &bfreeze &7all"), Sputnik.trans("&7mobs in &a8 blocks&7 radius for &a10s"), Sputnik.trans("&815s cooldown."));
                }

                @Override
                public void onHurt(EntityDamageByEntityEvent e, Entity damager) {
                    double c;
                    double r = SUtil.random(0.0, 1.0);
                    if (r < (c = 0.2 * (double)level / 100.0)) {
                        MagicivyPet.this.spawnIceCube((Player)e.getEntity());
                    }
                }
            });
        }
        if (instance.getRarity().isAtLeast(Rarity.EPIC)) {
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "Fantastic Mage!";
                }

                @Override
                public List<String> getDescription(SItem instance) {
                    return Arrays.asList(Sputnik.trans("&7Increase your weapon's &ebase &cAbility"), Sputnik.trans("&c Damage &7by &a" + blessed.toPlainString() + "%"));
                }
            });
        }
        if (instance.getRarity().isAtLeast(Rarity.LEGENDARY)) {
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "Power of SkySim";
                }

                @Override
                public List<String> getDescription(SItem instance) {
                    return Arrays.asList("Immune to any type of knockback.");
                }

                @Override
                public void onHurt(EntityDamageByEntityEvent e, Entity damager) {
                    Entity en = e.getEntity();
                    Vector v = new Vector(0, 0, 0);
                    SUtil.delay(() -> en.setVelocity(v), 0L);
                }
            });
        }
        return abilities;
    }

    @Override
    public Skill getSkill() {
        return CombatSkill.INSTANCE;
    }

    @Override
    public String getURL() {
        return "f41e6e4bcd2667bb284fb0dde361894840ea782efbfb717f6244e06b951c2b3f";
    }

    @Override
    public String getDisplayName() {
        return "Magicivy";
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.PET;
    }

    @Override
    public double getPerHealth() {
        return 2.0;
    }

    @Override
    public double getPerStrength() {
        return 1.0;
    }

    @Override
    public double getPerIntelligence() {
        return 150.0;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public void particleBelowA(Player p, Location l) {
        p.spigot().playEffect(l, Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
        p.spigot().playEffect(l, Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
        p.spigot().playEffect(l, Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
    }

    public void spawnIceCube(Player player) {
        int i;
        if (COOLDOWN.containsKey(player) && COOLDOWN.get(player).booleanValue()) {
            return;
        }
        COOLDOWN.put(player, true);
        SUtil.delay(() -> COOLDOWN.put(player, false), 100L);
        player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 5.0f, 0.7f);
        for (i = 1; i < 9; ++i) {
            int j = i;
            SUtil.delay(() -> this.createCircle(player.getLocation(), j), i * 1);
        }
        for (i = 0; i < 40; ++i) {
            player.getWorld().spigot().playEffect(player.getLocation().clone().add(0.0, 0.25, 0.0), Effect.SNOW_SHOVEL, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(0.0, 2.0), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 20);
        }
        player.playSound(player.getLocation(), Sound.EXPLODE, 5.0f, 0.0f);
        for (final Entity entity : player.getNearbyEntities(8.0, 8.0, 8.0)) {
            if (!(entity instanceof LivingEntity) || entity instanceof Player || entity instanceof EnderDragon || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand || entity.hasMetadata("GiantSword") || entity.hasMetadata("NoAffect") || entity.isDead()) continue;
            User user = User.getUser(player.getUniqueId());
            entity.setMetadata("frozen", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
            double b = 0.0;
            for (int i2 = 0; i2 < 2; ++i2) {
                final int d = i2;
                if (i2 == 0) {
                    b = 0.2;
                } else if (i2 == 1) {
                    b = 0.4;
                } else if (i2 == 2) {
                    b = 0.6;
                }
                final ArmorStand stands = (ArmorStand)entity.getWorld().spawn(entity.getLocation().add(0.0, b + 1.0, 0.0), ArmorStand.class);
                stands.setCustomNameVisible(false);
                stands.setVisible(false);
                stands.setArms(true);
                stands.setMarker(true);
                stands.setGravity(false);
                stands.setRightArmPose(new EulerAngle(0.0, 0.0, 12.0));
                stands.getEquipment().setItemInHand(new ItemStack(Material.PACKED_ICE));
                SUtil.delay(() -> stands.remove(), 100L);
                new BukkitRunnable(){

                    public void run() {
                        double c = 0.0;
                        if (d == 0) {
                            c = 0.2;
                        } else if (d == 1) {
                            c = 0.4;
                        } else if (d == 2) {
                            c = 0.6;
                        }
                        if (stands.isDead()) {
                            ((LivingEntity)entity).removePotionEffect(PotionEffectType.SLOW);
                            entity.removeMetadata("frozen", (Plugin)SkySimEngine.getPlugin());
                            entity.getWorld().playSound(entity.getLocation(), Sound.GLASS, 1.0f, 1.0f);
                            this.cancel();
                            return;
                        }
                        if (entity.isDead()) {
                            stands.remove();
                        }
                        ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 20));
                        stands.teleport(entity.getLocation().add(0.0, c + 1.0, 0.0));
                    }
                }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
            }
        }
    }

    private void createCircle(Location l, double radius) {
        l.setPitch(90.0f);
        Vector dist = l.getDirection();
        Location mid = l.add(dist);
        int particles = (int)(radius * 8.0);
        for (int i = 0; i < particles; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)particles;
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            Vector v = this.rotateAroundAxisX(new Vector(x, y, 0.0), l.getPitch());
            v = this.rotateAroundAxisY(v, l.getYaw());
            Location temp = mid.clone().add(v);
            l.getWorld().spigot().playEffect(temp, Effect.CLOUD, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
            l.getWorld().spigot().playEffect(temp, Effect.CLOUD, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
            l.getWorld().spigot().playEffect(temp, Effect.CLOUD, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
        }
    }

    private Vector rotateAroundAxisX(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    private Vector rotateAroundAxisY(Vector v, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }
}

