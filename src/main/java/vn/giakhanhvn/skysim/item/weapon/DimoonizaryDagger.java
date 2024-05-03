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
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.ItemFrame
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.EulerAngle
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.item.weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.DefenseReplacement;
import vn.giakhanhvn.skysim.util.FerocityCalculation;
import vn.giakhanhvn.skysim.util.ManaReplacement;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class DimoonizaryDagger
implements ToolStatistics,
MaterialFunction,
Ability {
    private static final Map<Player, Integer> swordThrows = new HashMap<Player, Integer>();
    int currentswordThrows;

    @Override
    public int getBaseDamage() {
        return 350;
    }

    @Override
    public double getBaseStrength() {
        return 300.0;
    }

    @Override
    public double getBaseFerocity() {
        return 25.0;
    }

    @Override
    public double getBaseCritDamage() {
        return 0.3;
    }

    @Override
    public double getBaseCritChance() {
        return 0.15;
    }

    @Override
    public String getDisplayName() {
        return "Dimoonizary Dagger";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.MYTHIC;
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
    public String getAbilityName() {
        return "Daggy Daze";
    }

    @Override
    public String getAbilityDescription() {
        return "Fling your " + ChatColor.LIGHT_PURPLE + "Dagger" + ChatColor.GRAY + " damaging all enemies in its path dealing " + ChatColor.RED + "30%" + ChatColor.GRAY + " melee damage. Consecutive throws stack " + ChatColor.RED + "2x " + ChatColor.GRAY + "damage but cost " + ChatColor.BLUE + "2x " + ChatColor.GRAY + "mana up to" + ChatColor.YELLOW + " 16x";
    }

    @Override
    public void onAbilityUse(final Player player1, final SItem sItem) {
        final ArrayList le = new ArrayList();
        if (!swordThrows.containsKey(player1)) {
            swordThrows.put(player1, 0);
        }
        swordThrows.put(player1, swordThrows.get(player1) + 1);
        int currentswordThrows1 = swordThrows.get(player1);
        double damageBoost = 0.0;
        int counter = 0;
        int manaCost = 0;
        this.currentswordThrows = swordThrows.get(player1);
        switch (this.currentswordThrows - 1) {
            case 0: {
                manaCost = 160;
                damageBoost = 0.3;
                counter = 0;
                break;
            }
            case 1: {
                manaCost = 320;
                damageBoost = 0.6;
                counter = 1;
                break;
            }
            case 2: {
                manaCost = 640;
                damageBoost = 1.2;
                counter = 2;
                break;
            }
            case 3: {
                manaCost = 1280;
                damageBoost = 2.4;
                counter = 3;
                break;
            }
            default: {
                manaCost = 2560;
                damageBoost = 4.8;
                counter = 4;
            }
        }
        final double damageBoost1 = damageBoost;
        int manaPool = SUtil.blackMagic(PlayerUtils.STATISTICS_CACHE.get(player1.getUniqueId()).getIntelligence().addAll() + 100.0);
        final int cost = PlayerUtils.getFinalManaCost(player1, sItem, manaCost);
        boolean take = PlayerUtils.takeMana(player1, cost);
        if (!take) {
            player1.playSound(player1.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
            final long c = System.currentTimeMillis();
            Repeater.MANA_REPLACEMENT_MAP.put(player1.getUniqueId(), new ManaReplacement(){

                @Override
                public String getReplacement() {
                    return "" + ChatColor.RED + ChatColor.BOLD + "NOT ENOUGH MANA";
                }

                @Override
                public long getEnd() {
                    return c + 1500L;
                }
            });
            return;
        }
        final long c = System.currentTimeMillis();
        Repeater.DEFENSE_REPLACEMENT_MAP.put(player1.getUniqueId(), new DefenseReplacement(){

            @Override
            public String getReplacement() {
                return ChatColor.AQUA + "-" + cost + " Mana (" + ChatColor.GOLD + DimoonizaryDagger.this.getAbilityName() + ChatColor.AQUA + ")";
            }

            @Override
            public long getEnd() {
                return c + 2000L;
            }
        });
        Location throwLoc = player1.getLocation().add(0.0, 0.5, 0.0);
        final Vector throwVec = player1.getLocation().add(player1.getLocation().getDirection().multiply(10)).toVector().subtract(player1.getLocation().toVector()).normalize().multiply(1.2);
        final ArmorStand armorStand1 = (ArmorStand)player1.getWorld().spawnEntity(throwLoc, EntityType.ARMOR_STAND);
        armorStand1.getEquipment().setItemInHand(SItem.of(SMaterial.HIDDEN_DIMOONIZARY_DAGGER).getStack());
        armorStand1.setGravity(false);
        armorStand1.setVisible(false);
        armorStand1.setMarker(true);
        Player bukkitPlayer = player1.getPlayer();
        final Vector teleportTo = bukkitPlayer.getLocation().getDirection().normalize().multiply(1);
        final Vector[] previousVector = new Vector[]{throwVec};
        new BukkitRunnable(){
            private int run = -1;

            public void run() {
                boolean back;
                int angle;
                Vector newVector;
                int ran;
                int j = 0;
                int i = ran = 0;
                int num = 90;
                Object loc = null;
                ++this.run;
                ++j;
                if (this.run > 100) {
                    this.cancel();
                    return;
                }
                if (armorStand1.isDead()) {
                    this.cancel();
                    return;
                }
                Location locof = armorStand1.getLocation();
                locof.setY(locof.getY() + 1.0);
                if (locof.getBlock().getType() != Material.AIR) {
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
                if (j >= 25) {
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
                double xPos = armorStand1.getRightArmPose().getX();
                armorStand1.setRightArmPose(new EulerAngle(xPos + 0.5, 0.0, 0.0));
                previousVector[0] = newVector = new Vector(throwVec.getX(), previousVector[0].getY() - 0.03, throwVec.getZ());
                armorStand1.setVelocity(newVector);
                if (i < 13) {
                    angle = i * 20 + num;
                    back = false;
                } else {
                    angle = i * 20 - num;
                    back = true;
                }
                if (locof.getBlock().getType() != Material.AIR && locof.getBlock().getType() != Material.WATER) {
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
                
                for (int q = 0; q < 3; ++q) {
                    armorStand1.getWorld().spigot().playEffect(armorStand1.getLocation().clone().add(0.0, 1.75, 0.0), Effect.MAGIC_CRIT, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(0.0, 0.5), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 100);
                }
                for (Entity e : armorStand1.getNearbyEntities(1.0, 1.0, 1.0)) {
                    if (!(e instanceof LivingEntity) || e == player1.getPlayer()) continue;
                    Damageable entity = (Damageable)e;
                    if (le.contains((LivingEntity)e) || entity.isDead() || !(entity instanceof LivingEntity) || entity instanceof Player || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand || entity instanceof Item || entity instanceof ItemFrame || entity.hasMetadata("GiantSword") || entity.hasMetadata("VWE")) continue;
                    User user = User.getUser(player1.getUniqueId());
                    Object[] atp = Sputnik.calculateDamage(player1, player1, sItem.getStack(), (LivingEntity)entity, false);
                    double finalDamage1 = (double)((Float)atp[0]).floatValue() * damageBoost1;
                    le.add((LivingEntity)e);
                    PlayerListener.spawnDamageInd((Entity)entity, (double)((Float)atp[2]).floatValue() * damageBoost1, (Boolean)atp[1]);
                    FerocityCalculation.activeFerocityTimes(player1, (LivingEntity)entity, (int)finalDamage1, (Boolean)atp[1]);
                    user.damageEntity(entity, finalDamage1);
                    if (damageBoost1 == 1.6) {
                        swordThrows.replace(player1, 0);
                    }
                    player1.setHealth(Math.min(player1.getMaxHealth(), player1.getHealth() + 50.0));
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 1L, 1L);
        new BukkitRunnable(){

            public void run() {
                armorStand1.remove();
                this.cancel();
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 40L);
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 5;
    }

    @Override
    public boolean displayCooldown() {
        return false;
    }

    @Override
    public boolean displayUsage() {
        return false;
    }

    @Override
    public int getManaCost() {
        return 0;
    }
}

