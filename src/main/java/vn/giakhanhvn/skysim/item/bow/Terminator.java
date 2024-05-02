/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Effect
 *  org.bukkit.GameMode
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
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.entity.EntityShootBowEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.item.bow;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
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
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.enchantment.Enchantment;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.PlayerBoostStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.item.bow.BowFunction;
import vn.giakhanhvn.skysim.item.weapon.EdibleMace;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.Groups;
import vn.giakhanhvn.skysim.util.InventoryUpdate;
import vn.giakhanhvn.skysim.util.ManaReplacement;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class Terminator
implements ToolStatistics,
BowFunction,
Ability {
    public static final Map<UUID, Integer> CountTerm = new HashMap<UUID, Integer>();
    public static final Map<UUID, Boolean> USABLE_TERM = new HashMap<UUID, Boolean>();

    @Override
    public String getDisplayName() {
        return "Terminator";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.RANGED_WEAPON;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.BOW;
    }

    @Override
    public int getBaseDamage() {
        return 310;
    }

    @Override
    public double getBaseCritDamage() {
        return 2.5;
    }

    @Override
    public double getBaseStrength() {
        return 50.0;
    }

    @Override
    public double getBaseAttackSpeed() {
        return 40.0;
    }

    @Override
    public boolean displayKills() {
        return false;
    }

    @Override
    public void onInteraction(PlayerInteractEvent e) {
        Player shooter = e.getPlayer();
        if (!CountTerm.containsKey(shooter.getUniqueId())) {
            CountTerm.put(shooter.getUniqueId(), 0);
        }
        if (shooter.getPlayer().getInventory().contains(Material.ARROW) || shooter.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && CountTerm.get(shooter.getUniqueId()) < 3) {
                shooter.updateInventory();
                if (USABLE_TERM.containsKey(shooter.getUniqueId()) && !USABLE_TERM.get(shooter.getUniqueId()).booleanValue()) {
                    return;
                }
                if (shooter.getGameMode() != GameMode.CREATIVE) {
                    InventoryUpdate.removeInventoryItems(shooter.getInventory(), Material.ARROW, 1);
                }
                shooter.playSound(shooter.getLocation(), Sound.SHOOT_ARROW, 1.0f, 1.0f);
                Location location = shooter.getEyeLocation().add(shooter.getEyeLocation().getDirection().toLocation(shooter.getWorld()));
                Location l = location.clone();
                l.setYaw(location.getYaw());
                Arrow a = shooter.getWorld().spawnArrow(l, l.getDirection(), 2.1f, 1.6f);
                a.setShooter((ProjectileSource)shooter);
                l.setYaw(location.getYaw() - 13.5f);
                shooter.getWorld().spawnArrow(l, l.getDirection(), 2.1f, 1.6f).setShooter((ProjectileSource)shooter);
                l.setYaw(location.getYaw() + 13.5f);
                shooter.getWorld().spawnArrow(l, l.getDirection(), 2.1f, 1.6f).setShooter((ProjectileSource)shooter);
                USABLE_TERM.put(shooter.getUniqueId(), false);
                PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(shooter.getUniqueId());
                double atkSpeed = Math.min(100L, Math.round(statistics.getAttackSpeed().addAll()));
                SUtil.delay(() -> USABLE_TERM.put(shooter.getUniqueId(), true), (long)(14.0 / (1.0 + atkSpeed / 100.0)));
            } else if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                shooter.updateInventory();
                if (USABLE_TERM.containsKey(shooter.getUniqueId()) && !USABLE_TERM.get(shooter.getUniqueId()).booleanValue()) {
                    return;
                }
                if (shooter.getGameMode() != GameMode.CREATIVE) {
                    InventoryUpdate.removeInventoryItems(shooter.getInventory(), Material.ARROW, 1);
                }
                shooter.playSound(shooter.getLocation(), Sound.SHOOT_ARROW, 1.0f, 1.0f);
                Location location = shooter.getEyeLocation().add(shooter.getEyeLocation().getDirection().toLocation(shooter.getWorld()));
                Location l = location.clone();
                l.setYaw(location.getYaw());
                Arrow a2 = shooter.getWorld().spawnArrow(l, l.getDirection(), 2.2f, 1.7f);
                a2.setShooter((ProjectileSource)shooter);
                l.setYaw(location.getYaw() - 13.5f);
                shooter.getWorld().spawnArrow(l, l.getDirection(), 2.2f, 1.7f).setShooter((ProjectileSource)shooter);
                l.setYaw(location.getYaw() + 13.5f);
                shooter.getWorld().spawnArrow(l, l.getDirection(), 2.2f, 1.7f).setShooter((ProjectileSource)shooter);
                USABLE_TERM.put(shooter.getUniqueId(), false);
                PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(shooter.getUniqueId());
                double atkSpeed = Math.min(100L, Math.round(statistics.getAttackSpeed().addAll()));
                SUtil.delay(() -> USABLE_TERM.put(shooter.getUniqueId(), true), (long)(14.0 / (1.0 + atkSpeed / 100.0)));
            }
        }
    }

    @Override
    public void onBowShoot(SItem bow, EntityShootBowEvent e) {
        Player player = (Player)e.getEntity();
        e.setCancelled(true);
        player.updateInventory();
    }

    @Override
    public String getAbilityName() {
        return "Salvation";
    }

    @Override
    public String getAbilityDescription() {
        return Sputnik.trans("Can be casted after landing &63 &7hits. &7Shoot a beam, penetrating up to &e5 &7foes and dealing &c2x &7the damage an arrow would. &7The beam always crits.");
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 0;
    }

    @Override
    public void onAbilityUse(Player player, SItem sItem) {
        if (!CountTerm.containsKey(player.getUniqueId())) {
            CountTerm.put(player.getUniqueId(), 0);
        }
        String ACT = "true";
        if (CountTerm.get(player.getUniqueId()) >= 3) {
            Location blockLocation = player.getTargetBlock((Set)null, 30).getLocation();
            Location crystalLocation = player.getEyeLocation().add(0.0, -0.1, 0.0);
            Vector vector = blockLocation.clone().add(0.1, 0.0, 0.1).toVector().subtract(crystalLocation.clone().toVector());
            double count = 40.0;
            int manaPool = SUtil.blackMagic(PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId()).getIntelligence().addAll() + 100.0);
            int cost = PlayerUtils.getFinalManaCost(player, sItem, 100);
            boolean take = PlayerUtils.takeMana(player, cost);
            if (!take) {
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                final long c = System.currentTimeMillis();
                Repeater.MANA_REPLACEMENT_MAP.put(player.getUniqueId(), new ManaReplacement(){

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
            CountTerm.put(player.getUniqueId(), 0);
            player.getWorld().playSound(player.getLocation(), Sound.GHAST_FIREBALL, 1.0f, 1.0f);
            for (int i = 1; i <= (int)count; ++i) {
                for (Entity entity : player.getWorld().getNearbyEntities(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), 0.7, 1.0, 0.7)) {
                    if (ACT == "false") {
                        return;
                    }
                    if (entity.isDead() || !(entity instanceof LivingEntity) || entity.hasMetadata("GiantSword") || entity instanceof Player || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand) continue;
                    User user = User.getUser(player.getUniqueId());
                    double enchantBonus = 0.0;
                    double potionBonus = 0.0;
                    double bonusDamage = 0.0;
                    PlayerStatistics statistics1 = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
                    double critDamage = statistics1.getCritDamage().addAll();
                    double speed = statistics1.getSpeed().addAll();
                    double realSpeed = speed * 100.0 % 25.0;
                    double realSpeedDIV = speed - realSpeed;
                    double realSpeedDIVC = realSpeedDIV / 25.0;
                    PlayerInventory inv = player.getInventory();
                    SItem helmet = SItem.find(inv.getHelmet());
                    if (helmet != null) {
                        if (helmet.getType() == SMaterial.WARDEN_HELMET) {
                            enchantBonus += (100.0 + 20.0 * realSpeedDIVC) / 100.0;
                        } else if (helmet.getType() == SMaterial.HIDDEN_VOIDLINGS_WARDEN_HELMET) {
                            enchantBonus += (100.0 + 30.0 * realSpeedDIVC) / 100.0;
                        }
                    }
                    for (Enchantment enchantment : sItem.getEnchantments()) {
                        EnchantmentType type1 = enchantment.getType();
                        int level = enchantment.getLevel();
                        if (type1 == EnchantmentType.POWER) {
                            enchantBonus += (double)(level * 8) / 100.0;
                        }
                        if (type1 == EnchantmentType.SMITE && Groups.UNDEAD_MOBS.contains(entity.getType())) {
                            enchantBonus += (double)(level * 8) / 100.0;
                        }
                        if (type1 == EnchantmentType.ENDER_SLAYER && Groups.ENDERMAN.contains(entity.getType())) {
                            enchantBonus += (double)(level * 12) / 100.0;
                        }
                        if (type1 == EnchantmentType.BANE_OF_ARTHROPODS && Groups.ARTHROPODS.contains(entity.getType())) {
                            enchantBonus += (double)(level * 8) / 100.0;
                        }
                        if (type1 == EnchantmentType.DRAGON_HUNTER && Groups.ENDERDRAGON.contains(entity.getType())) {
                            enchantBonus += (double)(level * 8) / 100.0;
                        }
                        if (type1 == EnchantmentType.CRITICAL) {
                            critDamage += (double)(level * 10) / 100.0;
                        }
                        if (type1 != EnchantmentType.SOUL_EATER || !PlayerUtils.SOUL_EATER_MAP.containsKey(player.getUniqueId()) || PlayerUtils.SOUL_EATER_MAP.get(player.getUniqueId()) == null) continue;
                        bonusDamage += PlayerUtils.SOUL_EATER_MAP.get(player.getUniqueId()).getStatistics().getDamageDealt() * (double)(level * 2);
                        PlayerUtils.SOUL_EATER_MAP.put(player.getUniqueId(), null);
                    }
                    SMaterial material = sItem.getType();
                    double hpbwea = 0.0;
                    if (sItem.getDataInt("hpb") > 0) {
                        hpbwea = sItem.getDataInt("hpb") * 2;
                    }
                    PlayerBoostStatistics playerBoostStatistics = (PlayerBoostStatistics)material.getStatistics();
                    double baseDamage = (5.0 + ((double)playerBoostStatistics.getBaseDamage() + hpbwea)) * (1.0 + statistics1.getStrength().addAll() / 100.0);
                    int combatLevel = Skill.getLevel(User.getUser(player.getUniqueId()).getCombatXP(), false);
                    double weaponBonus = 0.0;
                    double armorBonus = 1.0;
                    int critChanceMul = 100;
                    int chance = SUtil.random(0, 100);
                    if (chance > critChanceMul) {
                        critDamage = 0.0;
                    }
                    double damageMultiplier = 1.0 + (double)combatLevel * 0.04 + enchantBonus + weaponBonus;
                    double finalCritDamage = critDamage;
                    double finalDamage = baseDamage * damageMultiplier * armorBonus * (1.0 + finalCritDamage) + bonusDamage;
                    double finalPotionBonus = potionBonus;
                    if (entity.isDead() || !(entity instanceof LivingEntity) || entity instanceof Player || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand || entity instanceof Item || entity instanceof ItemFrame) continue;
                    if (EdibleMace.edibleMace.containsKey(player.getUniqueId()) && EdibleMace.edibleMace.get(player.getUniqueId()).booleanValue()) {
                        EdibleMace.edibleMace.put(player.getUniqueId(), false);
                    }
                    if (EntityManager.DEFENSE_PERCENTAGE.containsKey(entity)) {
                        int defensepercent = EntityManager.DEFENSE_PERCENTAGE.get(entity);
                        if (defensepercent > 100) {
                            defensepercent = 100;
                        }
                        finalDamage -= finalDamage * (double)defensepercent / 100.0;
                    }
                    user.damageEntity((Damageable)entity, finalDamage * 1.2);
                    player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 0.0f);
                    PlayerListener.spawnDamageInd(entity, finalDamage * 1.2, true);
                    ACT = "false";
                }
                player.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), Effect.COLOURED_DUST, 0, 1, 0.5882353f, 0.03529412f, 0.007843138f, 1.0f, 0, 64);
                player.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), Effect.COLOURED_DUST, 0, 1, 0.84313726f, 0.03529412f, 0.007843138f, 1.0f, 0, 64);
            }
        }
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public boolean displayUsage() {
        return false;
    }

    @Override
    public void onBowHit(Entity hit, Player shooter, Arrow arrow, SItem weapon, AtomicDouble finalDamage) {
        if (hit.isDead()) {
            return;
        }
        if (!(hit instanceof LivingEntity)) {
            return;
        }
        if (hit.hasMetadata("GiantSword")) {
            return;
        }
        if (hit instanceof Player || hit instanceof Villager || hit instanceof ArmorStand) {
            return;
        }
        CountTerm.put(shooter.getUniqueId(), CountTerm.get(shooter.getUniqueId()) + 1);
    }

    @Override
    public boolean isEnchanted() {
        return true;
    }
}

