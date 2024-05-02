/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.ItemFrame
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
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
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.item.pet.PetAbility;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.skill.CombatSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class TankPet
extends Pet {
    public static final Map<Player, Boolean> COOLDOWN = new HashMap<Player, Boolean>();

    @Override
    public List<PetAbility> getPetAbilities(SItem instance) {
        final int level = TankPet.getLevel(instance);
        final BigDecimal tankCannon = new BigDecimal((double)level * 0.3).setScale(1, RoundingMode.HALF_EVEN);
        final BigDecimal toml = new BigDecimal((double)level * 0.1).setScale(1, RoundingMode.HALF_EVEN);
        final BigDecimal buffstat = new BigDecimal((double)level * 0.2).setScale(1, RoundingMode.HALF_EVEN);
        ArrayList<PetAbility> abilities = new ArrayList<PetAbility>(Collections.singletonList(new PetAbility(){

            @Override
            public String getName() {
                return "Super Tank Cannon";
            }

            @Override
            public List<String> getDescription(SItem instance) {
                return Arrays.asList(Sputnik.trans("&7Grant &e" + tankCannon.toPlainString() + "% &7chance"), Sputnik.trans("&7to shoot a &cCannon Bullet &7that deal"), Sputnik.trans("&62x &7of your last damage to all mobs"), Sputnik.trans("&7within &a4 &7blocks of the impact."), Sputnik.trans("&810s cooldown."));
            }

            @Override
            public void onDamage(EntityDamageByEntityEvent e) {
                double c;
                double r = SUtil.random(0.0, 1.0);
                if (r < (c = 0.3 * (double)level / 100.0)) {
                    if (e.getDamager() instanceof Arrow) {
                        TankPet.this.spawnBullet((Player)((Arrow)e.getDamager()).getShooter());
                    } else {
                        TankPet.this.spawnBullet((Player)e.getDamager());
                    }
                }
            }
        }));
        if (instance.getRarity().isAtLeast(Rarity.EPIC)) {
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "Tank of the Motherland";
                }

                @Override
                public List<String> getDescription(SItem instance) {
                    return Arrays.asList(Sputnik.trans("&7Grant &e" + toml.toPlainString() + "% &7chance to immune"), Sputnik.trans("&7to damage (except True Damage)."));
                }

                @Override
                public void onHurt(EntityDamageByEntityEvent e, Entity damager) {
                    double c;
                    double r = SUtil.random(0.0, 1.0);
                    if (r < (c = 0.1 * (double)level / 100.0)) {
                        BigDecimal dmgabs = new BigDecimal(e.getDamage()).setScale(1, RoundingMode.HALF_EVEN);
                        ((Player)e.getEntity()).playSound(((Player)e.getEntity()).getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                        ((Player)e.getEntity()).sendMessage(Sputnik.trans("&eTank of the Motherland ability has absorbed &c" + SUtil.commaify(dmgabs.doubleValue()) + " &edamage for you!"));
                        e.setDamage(0.0);
                    }
                }
            });
        }
        if (instance.getRarity().isAtLeast(Rarity.LEGENDARY)) {
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "Forever Stading";
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
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "Power of the USSR";
                }

                @Override
                public List<String> getDescription(SItem instance) {
                    return Arrays.asList("Increases most stats by " + ChatColor.GREEN + buffstat.toPlainString() + "%");
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
        return "b9ae2dfb5d44e97e3a95af9071711a3d0fb6899cd3a568df1c00daf4dabe918f";
    }

    @Override
    public String getDisplayName() {
        return "Mini T-34";
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.PET;
    }

    @Override
    public double getPerDefense() {
        return 3.0;
    }

    @Override
    public double getPerTrueDefense() {
        return 1.0;
    }

    @Override
    public double getPerCritDamage() {
        return 0.01;
    }

    @Override
    public double getPerStrength() {
        return 2.0;
    }

    @Override
    public double getPerCritChance() {
        return 0.001;
    }

    @Override
    public double getPerFerocity() {
        return 0.25;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public void particleBelowA(Player p, Location l) {
        p.spigot().playEffect(l, Effect.SMALL_SMOKE, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
        p.spigot().playEffect(l, Effect.COLOURED_DUST, 0, 1, 0.003921569f, 0.003921569f, 0.003921569f, 1.0f, 0, 64);
        p.spigot().playEffect(l, Effect.COLOURED_DUST, 0, 1, 0.45882353f, 0.45882353f, 0.45882353f, 1.0f, 0, 64);
        p.spigot().playEffect(l, Effect.COLOURED_DUST, 0, 1, 0.003921569f, 0.003921569f, 0.003921569f, 1.0f, 0, 64);
        p.spigot().playEffect(l, Effect.COLOURED_DUST, 0, 1, 0.45882353f, 0.45882353f, 0.45882353f, 1.0f, 0, 64);
    }

    public void spawnBullet(final Player player) {
        if (COOLDOWN.containsKey(player) && COOLDOWN.get(player).booleanValue()) {
            return;
        }
        player.getWorld().playSound(player.getLocation(), Sound.GHAST_FIREBALL, 1.0f, 1.0f);
        player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1.0f, 2.0f);
        Location location = player.getLocation().add(0.0, -0.7, 0.0);
        final ArmorStand stand = (ArmorStand)player.getWorld().spawn(location.add(player.getLocation().getDirection().multiply(2)), ArmorStand.class);
        stand.setVisible(false);
        stand.setMarker(true);
        stand.setGravity(false);
        COOLDOWN.put(player, true);
        SUtil.delay(() -> {
            COOLDOWN.put(player, false);
            player.sendMessage(Sputnik.trans("&6Super Tank Cannon &ais now available."));
        }, 200L);
        stand.getEquipment().setHelmet(SUtil.getSkullURLStack("ITEM", "3e1e5c81fb9d64b74996fd171489deadbb8cb772d52cf8b566e3bc102301044", 1, ""));
        double baseDMG = 100.0;
        if (PlayerListener.LAST_DAMAGE_DEALT.containsKey(player)) {
            baseDMG = PlayerListener.LAST_DAMAGE_DEALT.get(player);
        }
        final double base = baseDMG;
        new BukkitRunnable(){
            int c = 0;

            public void run() {
                if (stand.isDead()) {
                    stand.getWorld().playEffect(stand.getLocation(), Effect.EXPLOSION, 0);
                    this.cancel();
                    return;
                }
                if (stand.getLocation().add(0.0, 2.0, 0.0).getBlock().getType() != Material.AIR) {
                    stand.remove();
                    TankPet.this.playParticleAndSound((Entity)stand);
                    TankPet.this.dmgEntityInRange((Entity)stand, player, base * 2.0);
                    this.cancel();
                    return;
                }
                if (TankPet.scanEntityNear((Entity)stand, 1, 1, 1).size() > 0) {
                    stand.remove();
                    TankPet.this.playParticleAndSound((Entity)stand);
                    TankPet.this.dmgEntityInRange((Entity)stand, player, base * 2.0);
                    this.cancel();
                    return;
                }
                if (this.c >= 50) {
                    stand.remove();
                    stand.getWorld().playEffect(stand.getLocation(), Effect.EXPLOSION, 0);
                    this.cancel();
                    return;
                }
                ++this.c;
                stand.getWorld().spigot().playEffect(stand.getLocation().add(0.0, 2.0, 0.0), Effect.FLAME, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
                stand.getWorld().spigot().playEffect(stand.getLocation().add(0.0, 2.0, 0.0), Effect.FLAME, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
                stand.getWorld().spigot().playEffect(stand.getLocation().add(0.0, 2.0, 0.0), Effect.LARGE_SMOKE, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
                stand.getWorld().spigot().playEffect(stand.getLocation().add(0.0, 2.0, 0.0), Effect.LARGE_SMOKE, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
                stand.teleport(stand.getLocation().add(stand.getLocation().getDirection().normalize().multiply(1)));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void dmgEntityInRange(Entity e, Player owner, double damage) {
        ArrayList<Entity> le = new ArrayList<Entity>();
        int td = 0;
        for (Entity entity1 : e.getNearbyEntities(4.0, 4.0, 4.0)) {
            if (entity1.isDead() || !(entity1 instanceof LivingEntity) || entity1 instanceof Player || entity1 instanceof EnderDragonPart || entity1 instanceof Villager || entity1 instanceof ArmorStand || entity1 instanceof Item || entity1 instanceof ItemFrame || entity1.hasMetadata("GiantSword")) continue;
            if (EntityManager.DEFENSE_PERCENTAGE.containsKey(entity1)) {
                int defensepercent = EntityManager.DEFENSE_PERCENTAGE.get(entity1);
                if (defensepercent > 100) {
                    defensepercent = 100;
                }
                damage -= damage * (double)defensepercent / 100.0;
            }
            User.getUser(owner.getUniqueId()).damageEntity((Damageable)entity1, damage);
            PlayerListener.customDMGIND(entity1, Sputnik.trans("&c" + String.valueOf(Math.round(damage)) + "\u273a"));
            PlayerListener.customDMGIND(entity1, Sputnik.trans("&c&lBOOM!"));
            le.add(entity1);
            td = (int)((double)td + damage);
        }
        if (le.size() > 0) {
            owner.playSound(owner.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 0.0f);
            if (le.size() > 1) {
                owner.sendMessage(Sputnik.trans("&7Your Super Tank Cannon hit &c" + le.size() + " &7enemies for &c" + SUtil.commaify(Math.round(td)) + " &7damage."));
            } else {
                owner.sendMessage(Sputnik.trans("&7Your Super Tank Cannon hit &c" + le.size() + " &7enemy for &c" + SUtil.commaify(Math.round(td)) + " &7damage."));
            }
        }
    }

    public static List<Entity> scanEntityNear(Entity e, int arg0, int arg1, int arg2) {
        ArrayList<Entity> re = new ArrayList<Entity>();
        for (Entity entity1 : e.getNearbyEntities((double)arg0, (double)arg1, (double)arg2)) {
            if (entity1.isDead() || !(entity1 instanceof LivingEntity) || entity1 instanceof Player || entity1 instanceof EnderDragonPart || entity1 instanceof Villager || entity1 instanceof ArmorStand || entity1 instanceof Item || entity1 instanceof ItemFrame || entity1.hasMetadata("GiantSword") || entity1.hasMetadata("NoAffect")) continue;
            re.add(entity1);
        }
        return re;
    }

    public void playParticleAndSound(Entity e) {
        e.getLocation().getWorld().playSound(e.getLocation(), Sound.EXPLODE, 2.0f, 1.3f);
        e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION_HUGE, 0);
    }
}

