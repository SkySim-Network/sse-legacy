/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package vn.giakhanhvn.skysim.potion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.potion.PotionColor;
import vn.giakhanhvn.skysim.potion.PotionEffect;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;
import vn.giakhanhvn.skysim.util.TriConsumer;

public class PotionEffectType {
    private static final Map<String, PotionEffectType> POTION_EFFECT_TYPE_CACHE = new HashMap<String, PotionEffectType>();
    public static final PotionEffectType STRENGTH = new PotionEffectType(ChatColor.DARK_RED + "Strength", "strength", "Increases Strength by %s.", PotionColor.BLOOD_RED, (statistics, slot, level) -> statistics.getStrength().add((int)slot, SUtil.getOrDefault(Arrays.asList(5.0, 12.0, 20.0, 30.0, 40.0, 50.0, 60.0, 75.0), level - 1, Double.valueOf((double)level.intValue() * 10.0))), false, true);
    public static final PotionEffectType FEROCITY = new PotionEffectType(ChatColor.RED + "Ferocity", "ferocity", "Increases Ferocity by %s.", PotionColor.RED, (statistics, slot, level) -> statistics.getFerocity().add((int)slot, SUtil.getOrDefault(Arrays.asList(5.0, 12.0, 20.0, 30.0, 40.0, 50.0, 60.0, 75.0), level - 1, Double.valueOf((double)level.intValue() * 10.0))), false, true);
    public static final PotionEffectType RABBIT = new PotionEffectType(ChatColor.GREEN + "Rabbit", "rabbit", "Grants Jump Boost %s and +%s Speed.", PotionColor.DARK_GREEN, (effect, player) -> PlayerUtils.replacePotionEffect(player, new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.JUMP, (int)effect.getDuration(), effect.getLevel() % 2 == 0 ? effect.getLevel() / 2 : (effect.getLevel() + 1) / 2)), (statistics, slot, level) -> statistics.getSpeed().add((int)slot, (double)level.intValue() * 10.0 / 100.0), false, true);
    public static final PotionEffectType HEALING = new PotionEffectType(ChatColor.RED + "Healing", "healing", "Grants an instant %s Health boost.", PotionColor.RED, (effect, player) -> player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + (double)SUtil.getOrDefault(Arrays.asList(20, 50, 100, 150, 200, 300, 400, 500), effect.getLevel() - 1, Integer.valueOf((effect.getLevel() - 3) * 100)).intValue())), true, true);
    public static final PotionEffectType JUMP_BOOST = new PotionEffectType(ChatColor.AQUA + "Jump Boost", "jump_boost", "Increases your jump height.", PotionColor.TWILIGHT_BLUE, (effect, player) -> PlayerUtils.replacePotionEffect(player, new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.JUMP, (int)effect.getDuration(), effect.getLevel() - 1)), false, true);
    public static final PotionEffectType SPEED = new PotionEffectType(ChatColor.BLUE + "Speed", "speed", "Grants a %s Speed boost.", PotionColor.LIGHT_BLUE, (statistics, slot, level) -> statistics.getSpeed().add((int)slot, (double)level.intValue() * 5.0 / 100.0), false, true);
    public static final PotionEffectType ARCHERY = new PotionEffectType(ChatColor.AQUA + "Archery", "archery", "Increases Bow Damage by %s%.", PotionColor.LIGHT_BLUE, false, true);
    public static final PotionEffectType MANA = new PotionEffectType(ChatColor.BLUE + "Mana", "mana", "Grants an instant %s Mana boost.", PotionColor.DARK_BLUE, (effect, player) -> Repeater.MANA_MAP.put(player.getUniqueId(), Repeater.MANA_MAP.get(player.getUniqueId()) + SUtil.getOrDefault(Arrays.asList(25, 50, 75, 100, 150, 200, 300, 400), effect.getLevel() - 1, Integer.valueOf((effect.getLevel() - 4) * 100))), true, true);
    public static final PotionEffectType ADRENALINE = new PotionEffectType(ChatColor.RED + "Adrenaline", "adrenaline", "Grants %s Absorption health and an increase of %s Speed.", PotionColor.PURPLE, (effect, player) -> PlayerUtils.replacePotionEffect(player, new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.ABSORPTION, (int)effect.getDuration(), (int)((double)SUtil.getOrDefault(Arrays.asList(20, 40, 60, 80, 100, 150, 200, 300), effect.getLevel() - 1, Integer.valueOf((effect.getLevel() - 5) * 100)).intValue() * 0.25 - 1.0))), (statistics, slot, level) -> statistics.getSpeed().add((int)slot, (double)level.intValue() * 5.0 / 100.0), false, true);
    public static final PotionEffectType CRITICAL = new PotionEffectType(ChatColor.DARK_RED + "Critical", "critical", "Gives a %s% increase in Crit Chance and a %s% increase in Crit Damage.", PotionColor.DARK_RED, (statistics, slot, level) -> {
        statistics.getCritChance().add((int)slot, 0.05 + (double)level.intValue() * 5.0 / 100.0);
        statistics.getCritDamage().add((int)slot, (double)level.intValue() * 10.0 / 100.0);
    }, false, true);
    public static final PotionEffectType ABSORPTION = new PotionEffectType(ChatColor.GOLD + "Absorption", "absorption", "Grants a boost of %s Absorption health.", PotionColor.ORANGE, (effect, player) -> PlayerUtils.replacePotionEffect(player, new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.ABSORPTION, (int)effect.getDuration(), (int)((double)SUtil.getOrDefault(Arrays.asList(20, 40, 60, 80, 100, 150, 200, 300), effect.getLevel() - 1, Integer.valueOf((effect.getLevel() - 5) * 100)).intValue() * 0.25 - 1.0))), false, true);
    public static final PotionEffectType NIGHT_VISION = new PotionEffectType(ChatColor.DARK_PURPLE + "Night Vision", "night_vision", "Grants greater visibility at night.", PotionColor.DARK_BLUE, (effect, player) -> PlayerUtils.replacePotionEffect(player, new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.NIGHT_VISION, (int)effect.getDuration(), (int)((double)SUtil.getOrDefault(Arrays.asList(20, 40, 60, 80, 100, 150, 200, 300), effect.getLevel() - 1, Integer.valueOf((effect.getLevel() - 5) * 100)).intValue() * 0.25 - 1.0))), false, true);
    public static final PotionEffectType WATER_BREATH = new PotionEffectType(ChatColor.BLUE + "Water Breathing", "water_breath", "Grants ability to not taking drowning damage.", PotionColor.DARK_BLUE, (effect, player) -> PlayerUtils.replacePotionEffect(player, new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.WATER_BREATHING, (int)effect.getDuration(), (int)((double)SUtil.getOrDefault(Arrays.asList(20, 40, 60, 80, 100, 150, 200, 300), effect.getLevel() - 1, Integer.valueOf((effect.getLevel() - 5) * 100)).intValue() * 0.25 - 1.0))), false, true);
    public static final PotionEffectType FIRE_RESISTANCE = new PotionEffectType(ChatColor.GOLD + "Fire Resistance", "fire_resistance", "Grants immunity to fire and lava.", PotionColor.ORANGE, (effect, player) -> PlayerUtils.replacePotionEffect(player, new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE, (int)effect.getDuration(), (int)((double)SUtil.getOrDefault(Arrays.asList(20, 40, 60, 80, 100, 150, 200, 300), effect.getLevel() - 1, Integer.valueOf((effect.getLevel() - 5) * 100)).intValue() * 0.25 - 1.0))), false, true);
    public static final PotionEffectType HASTE = new PotionEffectType(ChatColor.YELLOW + "Haste", "haste", "Increases your mining speed.", PotionColor.ORANGE, (effect, player) -> {
        player.removePotionEffect(org.bukkit.potion.PotionEffectType.FAST_DIGGING);
        player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.FAST_DIGGING, (int)effect.getDuration(), effect.getLevel() - 1));
    }, false, true);
    public static final PotionEffectType RESISTANCE = new PotionEffectType(ChatColor.GREEN + "Resistance", "resistance", "Increases Defense by %s.", PotionColor.DARK_GREEN, (statistics, slot, level) -> statistics.getDefense().add((int)slot, SUtil.getOrDefault(Arrays.asList(5.0, 10.0, 15.0, 20.0, 30.0, 40.0, 50.0, 60.0), level - 1, Double.valueOf((double)level.intValue() * 10.0 - 20.0))), false, true);
    public static final PotionEffectType TRUE_RESISTANCE = new PotionEffectType(ChatColor.WHITE + "True Resistance", "true_resistance", "Increases True Defense by %s.", PotionColor.TWILIGHT_BLUE, (statistics, slot, level) -> statistics.getTrueDefense().add((int)slot, (double)level.intValue() * 5.0), false, true);
    public static final PotionEffectType MAGIC_FIND = new PotionEffectType(ChatColor.AQUA + "Magic Find", "magic_find", "Increases Magic Find by %s%.", PotionColor.LIGHT_BLUE, (statistics, slot, level) -> statistics.getMagicFind().add((int)slot, (double)level.intValue() * 0.02), false, true);
    public static final PotionEffectType STAMINA = new PotionEffectType(ChatColor.GREEN + "Stamina", "stamina", "Grants an instant %s Health and %s Mana boost.", PotionColor.TWILIGHT_BLUE, (effect, player) -> {
        player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + (double)SUtil.getOrDefault(Arrays.asList(150, 215, 315, 515), effect.getLevel() - 1, Integer.valueOf((effect.getLevel() + 1) * 100 + 15)).intValue()));
        Repeater.MANA_MAP.put(player.getUniqueId(), Repeater.MANA_MAP.get(player.getUniqueId()) + 50 * effect.getLevel());
    }, true, true);
    public static final PotionEffectType SPIRIT = new PotionEffectType(ChatColor.AQUA + "Spirit", "spirit", "Grants a %s increase in Speed and a %s% increase in Crit Damage.", PotionColor.LIGHT_BLUE, (statistics, slot, level) -> {
        statistics.getSpeed().add((int)slot, (double)level.intValue() * 10.0 / 100.0);
        statistics.getCritDamage().add((int)slot, (double)level.intValue() * 10.0 / 100.0);
    }, false, true);
    private final String name;
    private final String namespace;
    private final String description;
    private final PotionColor color;
    private final BiConsumer<PotionEffect, Player> onDrink;
    private final TriConsumer<PlayerStatistics, Integer, Integer> statsUpdate;
    private final boolean instant;
    private final boolean buff;

    public PotionEffectType(String name, String namespace, String description, PotionColor color, BiConsumer<PotionEffect, Player> onDrink, TriConsumer<PlayerStatistics, Integer, Integer> statsUpdate, boolean instant, boolean buff) {
        this.name = name;
        this.namespace = namespace;
        this.description = description;
        this.color = color;
        this.onDrink = onDrink;
        this.statsUpdate = statsUpdate;
        this.instant = instant;
        this.buff = buff;
        POTION_EFFECT_TYPE_CACHE.put(namespace, this);
    }

    public PotionEffectType(String name, String namespace, String description, PotionColor color, TriConsumer<PlayerStatistics, Integer, Integer> statsUpdate, boolean instant, boolean buff) {
        this(name, namespace, description, color, null, statsUpdate, instant, buff);
    }

    public PotionEffectType(String name, String namespace, String description, PotionColor color, BiConsumer<PotionEffect, Player> onDrink, boolean instant, boolean buff) {
        this(name, namespace, description, color, onDrink, null, instant, buff);
    }

    public PotionEffectType(String name, String namespace, String description, PotionColor color, boolean instant, boolean buff) {
        this(name, namespace, description, color, null, null, instant, buff);
    }

    public static PotionEffectType getByNamespace(String namespace) {
        return POTION_EFFECT_TYPE_CACHE.get(namespace.toLowerCase());
    }

    public String getDescription(Object ... objects) {
        String description = this.description;
        for (Object object : objects) {
            description = Sputnik.trans(description.replaceFirst("%s", String.valueOf(object)));
        }
        return description;
    }

    public boolean equals(Object o) {
        if (!(o instanceof PotionEffectType)) {
            return false;
        }
        return ((PotionEffectType)o).namespace.equals(this.namespace);
    }

    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public PotionColor getColor() {
        return this.color;
    }

    public BiConsumer<PotionEffect, Player> getOnDrink() {
        return this.onDrink;
    }

    public TriConsumer<PlayerStatistics, Integer, Integer> getStatsUpdate() {
        return this.statsUpdate;
    }

    public boolean isInstant() {
        return this.instant;
    }

    public boolean isBuff() {
        return this.buff;
    }
}

