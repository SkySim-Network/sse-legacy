/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package vn.giakhanhvn.skysim.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.armor.ArmorSet;
import vn.giakhanhvn.skysim.user.DoublePlayerStatistic;

public class PlayerStatistics {
    private final UUID uuid;
    private final DoublePlayerStatistic maxHealth;
    private final DoublePlayerStatistic defense;
    private final DoublePlayerStatistic strength;
    private final DoublePlayerStatistic speed;
    private final DoublePlayerStatistic critChance;
    private final DoublePlayerStatistic critDamage;
    private final DoublePlayerStatistic magicFind;
    private final DoublePlayerStatistic ferocity;
    private final DoublePlayerStatistic abilityDamage;
    private final DoublePlayerStatistic attackSpeed;
    private final DoublePlayerStatistic intelligence;
    private final DoublePlayerStatistic trueDefense;
    private double healthRegenerationPercentBonus;
    private double manaRegenerationPercentBonus;
    private ArmorSet armorSet;
    private Map<Integer, BukkitTask> itemTicker;

    public PlayerStatistics(UUID uuid, DoublePlayerStatistic maxHealth, DoublePlayerStatistic defense, DoublePlayerStatistic strength, DoublePlayerStatistic speed, DoublePlayerStatistic critChance, DoublePlayerStatistic critDamage, DoublePlayerStatistic magicFind, DoublePlayerStatistic intelligence, DoublePlayerStatistic trueDefense, DoublePlayerStatistic ferocity, DoublePlayerStatistic abilityDamage, DoublePlayerStatistic attackSpeed, double healthRegenerationPercentBonus, double manaRegenerationPercentBonus, ArmorSet armorSet) {
        this.uuid = uuid;
        this.maxHealth = maxHealth;
        this.defense = defense;
        this.strength = strength;
        this.speed = speed;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.magicFind = magicFind;
        this.abilityDamage = abilityDamage;
        this.attackSpeed = attackSpeed;
        this.intelligence = intelligence;
        this.trueDefense = trueDefense;
        this.ferocity = ferocity;
        this.healthRegenerationPercentBonus = healthRegenerationPercentBonus;
        this.manaRegenerationPercentBonus = manaRegenerationPercentBonus;
        this.armorSet = armorSet;
        this.itemTicker = new HashMap<Integer, BukkitTask>();
    }

    public void tickItem(int slot, long interval, final Runnable runnable) {
        this.itemTicker.put(slot, new BukkitRunnable(){

            public void run() {
                if (Bukkit.getPlayer((UUID)PlayerStatistics.this.uuid) == null) {
                    this.cancel();
                    return;
                }
                runnable.run();
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, interval));
    }

    public void cancelTickingItem(int slot) {
        if (this.itemTicker.containsKey(slot)) {
            this.itemTicker.get(slot).cancel();
        }
        this.itemTicker.remove(slot);
    }

    public void zeroAll(int slot) {
        this.maxHealth.zero(slot);
        this.defense.zero(slot);
        this.strength.zero(slot);
        this.intelligence.zero(slot);
        this.speed.zero(slot);
        this.critChance.zero(slot);
        this.critDamage.zero(slot);
        this.magicFind.zero(slot);
        this.trueDefense.zero(slot);
        this.ferocity.zero(slot);
        this.abilityDamage.zero(slot);
        this.attackSpeed.zero(slot);
        this.cancelTickingItem(slot);
    }

    public String toString() {
        return this.maxHealth.addAll() + ", " + this.defense.addAll() + ", " + this.strength.addAll() + ", " + this.speed.addAll() + ", " + this.critChance.addAll() + ", " + this.critDamage.addAll() + ", " + this.magicFind.addAll() + ", " + this.intelligence.addAll() + ", " + this.ferocity.addAll() + ", " + this.abilityDamage.addAll() + ", " + this.attackSpeed.addAll();
    }

    public void boostManaRegeneration(final double percent, long ticks) {
        this.manaRegenerationPercentBonus += percent;
        new BukkitRunnable(){

            public void run() {
                PlayerStatistics.this.manaRegenerationPercentBonus -= percent;
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), ticks);
    }

    public void boostHealthRegeneration(final double percent, long ticks) {
        this.healthRegenerationPercentBonus += percent;
        new BukkitRunnable(){

            public void run() {
                PlayerStatistics.this.healthRegenerationPercentBonus -= percent;
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), ticks);
    }

    public static PlayerStatistics blank(UUID uuid) {
        return new PlayerStatistics(uuid, new DoublePlayerStatistic(100.0), new DoublePlayerStatistic(), new DoublePlayerStatistic(), new DoublePlayerStatistic(1.0), new DoublePlayerStatistic(0.3), new DoublePlayerStatistic(0.5), new DoublePlayerStatistic(), new DoublePlayerStatistic(), new DoublePlayerStatistic(), new DoublePlayerStatistic(), new DoublePlayerStatistic(), new DoublePlayerStatistic(), 0.0, 0.0, null);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public DoublePlayerStatistic getMaxHealth() {
        return this.maxHealth;
    }

    public DoublePlayerStatistic getDefense() {
        return this.defense;
    }

    public DoublePlayerStatistic getStrength() {
        return this.strength;
    }

    public DoublePlayerStatistic getSpeed() {
        return this.speed;
    }

    public DoublePlayerStatistic getCritChance() {
        return this.critChance;
    }

    public DoublePlayerStatistic getCritDamage() {
        return this.critDamage;
    }

    public DoublePlayerStatistic getMagicFind() {
        return this.magicFind;
    }

    public DoublePlayerStatistic getFerocity() {
        return this.ferocity;
    }

    public DoublePlayerStatistic getAbilityDamage() {
        return this.abilityDamage;
    }

    public DoublePlayerStatistic getAttackSpeed() {
        return this.attackSpeed;
    }

    public DoublePlayerStatistic getIntelligence() {
        return this.intelligence;
    }

    public DoublePlayerStatistic getTrueDefense() {
        return this.trueDefense;
    }

    public double getHealthRegenerationPercentBonus() {
        return this.healthRegenerationPercentBonus;
    }

    public double getManaRegenerationPercentBonus() {
        return this.manaRegenerationPercentBonus;
    }

    public ArmorSet getArmorSet() {
        return this.armorSet;
    }

    public Map<Integer, BukkitTask> getItemTicker() {
        return this.itemTicker;
    }

    public void setHealthRegenerationPercentBonus(double healthRegenerationPercentBonus) {
        this.healthRegenerationPercentBonus = healthRegenerationPercentBonus;
    }

    public void setManaRegenerationPercentBonus(double manaRegenerationPercentBonus) {
        this.manaRegenerationPercentBonus = manaRegenerationPercentBonus;
    }

    public void setArmorSet(ArmorSet armorSet) {
        this.armorSet = armorSet;
    }
}

