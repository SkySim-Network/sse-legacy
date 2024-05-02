/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;

public class TemporaryStats {
    private User targettedUser;
    public float health;
    public float defense;
    public float strength;
    public float speed;
    public float critChance;
    public float critDamage;
    public float intelligence;
    public float ferocity;
    public float attackSpeed;
    public float abilityDamage;
    public float trueDefense;
    public float magicFind;
    public float damageBonus;
    public UUID uuid;
    public static Map<UUID, TemporaryStats> TEMP_CACHE = new HashMap<UUID, TemporaryStats>();

    public TemporaryStats(User targettedUser) {
        this.targettedUser = targettedUser;
        this.uuid = targettedUser.getUuid();
        this.health = 0.0f;
        this.defense = 0.0f;
        this.strength = 0.0f;
        this.critChance = 0.0f;
        this.critDamage = 0.0f;
        this.speed = 0.0f;
        this.intelligence = 0.0f;
        this.abilityDamage = 0.0f;
        this.trueDefense = 0.0f;
        this.attackSpeed = 0.0f;
        this.magicFind = 0.0f;
        this.ferocity = 0.0f;
        TEMP_CACHE.put(this.targettedUser.getUuid(), this);
    }

    public void cleanUp() {
        if (TEMP_CACHE.containsKey(this.uuid)) {
            TEMP_CACHE.remove(this.uuid);
        }
        if (Bukkit.getPlayer((UUID)this.targettedUser.getUuid()) != null) {
            PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(this.targettedUser.getUuid());
            statistics.zeroAll(152);
        }
    }

    public static void cleanStats(UUID uuid) {
        if (TEMP_CACHE.containsKey(uuid)) {
            TEMP_CACHE.remove(uuid);
        }
        if (Bukkit.getPlayer((UUID)uuid) != null) {
            PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(uuid);
            statistics.zeroAll(152);
        }
    }

    public void cleanStats() {
        if (Bukkit.getPlayer((UUID)this.uuid) != null && PlayerUtils.STATISTICS_CACHE.get(this.uuid) != null) {
            PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(this.uuid);
            this.health = 0.0f;
            this.defense = 0.0f;
            this.strength = 0.0f;
            this.critChance = 0.0f;
            this.critDamage = 0.0f;
            this.speed = 0.0f;
            this.intelligence = 0.0f;
            this.abilityDamage = 0.0f;
            this.trueDefense = 0.0f;
            this.attackSpeed = 0.0f;
            this.magicFind = 0.0f;
            this.ferocity = 0.0f;
            statistics.zeroAll(152);
        }
    }

    public void update() {
        if (Bukkit.getPlayer((UUID)this.targettedUser.getUuid()) == null) {
            this.cleanUp();
            return;
        }
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(this.targettedUser.getUuid());
        statistics.zeroAll(152);
        statistics.getMaxHealth().set(152, Double.valueOf(this.health));
        statistics.getDefense().set(152, Double.valueOf(this.defense));
        statistics.getStrength().set(152, Double.valueOf(this.strength));
        statistics.getCritChance().set(152, Double.valueOf(this.critChance));
        statistics.getCritDamage().set(152, Double.valueOf(this.critDamage));
        statistics.getIntelligence().set(152, Double.valueOf(this.intelligence));
        statistics.getFerocity().set(152, Double.valueOf(this.ferocity));
        statistics.getMagicFind().set(152, Double.valueOf(this.magicFind));
        statistics.getSpeed().set(152, Double.valueOf(this.speed));
        statistics.getTrueDefense().set(152, Double.valueOf(this.trueDefense));
        statistics.getAttackSpeed().set(152, Double.valueOf(this.attackSpeed));
        statistics.getAbilityDamage().set(152, Double.valueOf(this.abilityDamage));
    }

    public static TemporaryStats getFromPlayer(Player p) {
        if (TEMP_CACHE.containsKey(p.getUniqueId())) {
            return TEMP_CACHE.get(p.getUniqueId());
        }
        return null;
    }

    public static TemporaryStats getFromPlayer(UUID u) {
        if (TEMP_CACHE.containsKey(u)) {
            return TEMP_CACHE.get(u);
        }
        return null;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getHealth() {
        return this.health;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    public float getDefense() {
        return this.defense;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getStrength() {
        return this.strength;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setCritChance(float critChance) {
        this.critChance = critChance;
    }

    public float getCritChance() {
        return this.critChance;
    }

    public void setCritDamage(float critDamage) {
        this.critDamage = critDamage;
    }

    public float getCritDamage() {
        return this.critDamage;
    }

    public void setIntelligence(float intelligence) {
        this.intelligence = intelligence;
    }

    public float getIntelligence() {
        return this.intelligence;
    }

    public void setFerocity(float ferocity) {
        this.ferocity = ferocity;
    }

    public float getFerocity() {
        return this.ferocity;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
    }

    public void setAbilityDamage(float abilityDamage) {
        this.abilityDamage = abilityDamage;
    }

    public float getAbilityDamage() {
        return this.abilityDamage;
    }

    public void setTrueDefense(float trueDefense) {
        this.trueDefense = trueDefense;
    }

    public float getTrueDefense() {
        return this.trueDefense;
    }

    public void setMagicFind(float magicFind) {
        this.magicFind = magicFind;
    }

    public float getMagicFind() {
        return this.magicFind;
    }

    public void setDamageBonus(float damageBonus) {
        this.damageBonus = damageBonus;
    }

    public float getDamageBonus() {
        return this.damageBonus;
    }
}

