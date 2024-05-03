/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.milkbowl.vault.economy.Economy
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.BlockFace
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.user;

import com.google.common.util.concurrent.AtomicDouble;
import java.io.File;
import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.config.Config;
import vn.giakhanhvn.skysim.dungeons.ItemSerial;
import vn.giakhanhvn.skysim.enchantment.Enchantment;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.Watcher;
import vn.giakhanhvn.skysim.entity.nms.SlayerBoss;
import vn.giakhanhvn.skysim.extra.protocol.PacketInvoker;
import vn.giakhanhvn.skysim.gui.SlayerGUI;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.AbilityActivation;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.PlayerBoostStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.TickingMaterial;
import vn.giakhanhvn.skysim.item.accessory.AccessoryFunction;
import vn.giakhanhvn.skysim.item.armor.ArmorSet;
import vn.giakhanhvn.skysim.item.armor.VoidlingsWardenHelmet;
import vn.giakhanhvn.skysim.item.armor.gigachad.GigachadSet;
import vn.giakhanhvn.skysim.item.armor.minichad.MinichadSet;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.item.weapon.EdibleMace;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.potion.ActivePotionEffect;
import vn.giakhanhvn.skysim.reforge.Reforge;
import vn.giakhanhvn.skysim.skill.CombatSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.slayer.SlayerBossType;
import vn.giakhanhvn.skysim.slayer.SlayerQuest;
import vn.giakhanhvn.skysim.user.DoublePlayerStatistic;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.BlankWorldCreator;
import vn.giakhanhvn.skysim.util.DefenseReplacement;
import vn.giakhanhvn.skysim.util.Groups;
import vn.giakhanhvn.skysim.util.ManaReplacement;
import vn.giakhanhvn.skysim.util.SLog;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public final class PlayerUtils {
    public static final Map<UUID, Boolean> AUTO_SLAYER = new HashMap<UUID, Boolean>();
    public static final Map<UUID, UUID> USER_SESSION_ID = new HashMap<UUID, UUID>();
    public static final Map<UUID, PlayerStatistics> STATISTICS_CACHE = new HashMap<UUID, PlayerStatistics>();
    public static final Map<UUID, List<SMaterial>> COOLDOWN_MAP = new HashMap<UUID, List<SMaterial>>();
    public static final Map<UUID, SEntity> SOUL_EATER_MAP = new HashMap<UUID, SEntity>();
    public static final Map<UUID, Long> COOKIE_DURATION_CACHE = new HashMap<UUID, Long>();
    public static final Map<UUID, Integer> LAST_KILLED_MAPPING = new HashMap<UUID, Integer>();

    public static PlayerStatistics getStatistics(Player player) {
        PlayerInventory inv = player.getInventory();
        SItem helmet = SItem.find(inv.getHelmet());
        SItem chestplate = SItem.find(inv.getChestplate());
        SItem leggings = SItem.find(inv.getLeggings());
        SItem boots = SItem.find(inv.getBoots());
        SItem hand = SItem.find(inv.getItemInHand());
        List<SItem> items = Arrays.asList(helmet, chestplate, leggings, boots);
        PlayerStatistics statistics = PlayerStatistics.blank(player.getUniqueId());
        for (int i = 0; i < items.size(); ++i) {
            SItem sItem = items.get(i);
            PlayerUtils.updateArmorStatistics(sItem, statistics, i);
        }
        PlayerUtils.updateSetStatistics(player, helmet, chestplate, leggings, boots, statistics);
        PlayerUtils.updateHandStatistics(hand, statistics);
        PlayerUtils.updatePetStatistics(statistics);
        PlayerUtils.updateInventoryStatistics(player, statistics);
        return statistics;
    }

    public static PlayerStatistics updateHandStatistics(SItem hand, PlayerStatistics statistics) {
        double hpbwea = 0.0;
        double ferogrant = 0.0;
        double mfgrant = 0.0;
        double a = 0.0;
        Player player = Bukkit.getPlayer(statistics.getUuid());
        User user = User.getUser(player.getUniqueId());
        Pet.PetItem active = user.getActivePet();
        int level = 0;
        Pet pet = (Pet)SMaterial.GUNGA_PET.getGenericInstance();
        DoublePlayerStatistic strength = statistics.getStrength();
        DoublePlayerStatistic intelligence = statistics.getIntelligence();
        DoublePlayerStatistic speed = statistics.getSpeed();
        DoublePlayerStatistic critChance = statistics.getCritChance();
        DoublePlayerStatistic critDamage = statistics.getCritDamage();
        DoublePlayerStatistic ferocity = statistics.getFerocity();
        DoublePlayerStatistic magicFind = statistics.getMagicFind();
        DoublePlayerStatistic defense = statistics.getDefense();
        DoublePlayerStatistic trueDefense = statistics.getTrueDefense();
        DoublePlayerStatistic atkSpeed = statistics.getAttackSpeed();
        statistics.zeroAll(4);
        statistics.getFerocity().set(153, Double.valueOf(user.getBonusFerocity()));
        if (hand != null && hand.getType().getStatistics().getType() != GenericItemType.ARMOR && hand.getType().getStatistics().getType() != GenericItemType.ACCESSORY) {
            Reforge reforge = hand.getReforge() == null ? Reforge.blank() : hand.getReforge();
            strength.set(4, reforge.getStrength().getForRarity(hand.getRarity()));
            critDamage.set(4, reforge.getCritDamage().getForRarity(hand.getRarity()));
            critChance.set(4, reforge.getCritChance().getForRarity(hand.getRarity()));
            intelligence.set(4, reforge.getIntelligence().getForRarity(hand.getRarity()));
            ferocity.set(4, reforge.getFerocity().getForRarity(hand.getRarity()));
            atkSpeed.set(4, reforge.getAttackSpeed().getForRarity(hand.getRarity()));
            PlayerBoostStatistics handStatistics = hand.getType().getBoostStatistics();
            if (handStatistics != null) {
                strength.add(4, handStatistics.getBaseStrength());
                critDamage.add(4, handStatistics.getBaseCritDamage());
                critChance.add(4, handStatistics.getBaseCritChance());
                intelligence.add(4, handStatistics.getBaseIntelligence());
                ferocity.add(4, handStatistics.getBaseFerocity());
                atkSpeed.add(4, handStatistics.getBaseAttackSpeed());
            }
        } else {
            strength.zero(4);
            intelligence.zero(4);
            critChance.zero(4);
            critDamage.zero(4);
            intelligence.zero(4);
            speed.zero(4);
            ferocity.zero(4);
            atkSpeed.zero(4);
        }
        if (hand != null && hand.getType() == SMaterial.TERMINATOR) {
            critChance.sub(4, statistics.getCritChance().addAll() - statistics.getCritChance().addAll() / 4.0);
        }
        if (hand != null) {
            if (hand.getEnchantment(EnchantmentType.VICIOUS) != null) {
                ferogrant = hand.getEnchantment(EnchantmentType.VICIOUS).getLevel();
            }
            if (hand.getEnchantment(EnchantmentType.LUCKINESS) != null) {
                mfgrant = (double)hand.getEnchantment(EnchantmentType.LUCKINESS).getLevel() * 0.02;
            }
        }
        if (hand != null && hand.getEnchantment(EnchantmentType.CHIMERA) != null) {
            double lvl = hand.getEnchantment(EnchantmentType.CHIMERA).getLevel();
            if (active != null) {
                level = Pet.getLevel(active.getXp(), active.getRarity());
                pet = (Pet)active.getType().getGenericInstance();
                a = 20.0 * lvl / 100.0;
            }
        }
        if (hand != null && hand.getDataInt("hpb") > 0) {
            hpbwea = hand.getDataInt("hpb") * 2;
        }
        ferocity.add(4, ferogrant);
        magicFind.add(4, mfgrant);
        strength.add(4, hpbwea);
        if (hand != null && hand.getType().getStatistics().getType() != GenericItemType.ARMOR && (user.toBukkitPlayer().getWorld().getName().contains("f6") || user.toBukkitPlayer().getWorld().getName().contains("dungeon"))) {
            ItemSerial is = ItemSerial.getItemBoostStatistics(hand);
            defense.add(4, is.getDefense());
            strength.add(4, is.getStrength());
            intelligence.add(4, is.getIntelligence());
            critChance.add(4, is.getCritchance());
            critDamage.add(4, is.getCritdamage());
            speed.add(4, is.getSpeed());
            magicFind.add(4, is.getMagicFind());
            atkSpeed.add(4, is.getAtkSpeed());
            ferocity.add(4, is.getFerocity());
        }
        defense.add(4, a * (pet.getPerDefense() * (double)level));
        strength.add(4, a * (pet.getPerStrength() * (double)level));
        intelligence.add(4, a * (pet.getPerIntelligence() * (double)level));
        speed.add(4, a * (pet.getPerSpeed() * (double)level));
        critChance.add(4, a * (pet.getPerCritChance() * (double)level));
        critDamage.add(4, a * (pet.getPerCritDamage() * (double)level));
        magicFind.add(4, a * (pet.getPerMagicFind() * (double)level));
        trueDefense.add(4, a * (pet.getPerTrueDefense() * (double)level));
        ferocity.add(4, a * (pet.getPerFerocity() * (double)level));
        atkSpeed.add(4, a * (pet.getPerAttackSpeed() * (double)level));
        PlayerUtils.updateHealth(Bukkit.getPlayer(statistics.getUuid()), statistics);
        return statistics;
    }

    public static PlayerStatistics updateArmorStatistics(SItem piece, PlayerStatistics statistics, int slot) {
        DoublePlayerStatistic health = statistics.getMaxHealth();
        DoublePlayerStatistic defense = statistics.getDefense();
        DoublePlayerStatistic strength = statistics.getStrength();
        DoublePlayerStatistic intelligence = statistics.getIntelligence();
        DoublePlayerStatistic ferocity = statistics.getFerocity();
        DoublePlayerStatistic speed = statistics.getSpeed();
        DoublePlayerStatistic critChance = statistics.getCritChance();
        DoublePlayerStatistic critDamage = statistics.getCritDamage();
        DoublePlayerStatistic magicFind = statistics.getMagicFind();
        DoublePlayerStatistic trueDefense = statistics.getTrueDefense();
        DoublePlayerStatistic atkSpeed = statistics.getAttackSpeed();
        statistics.zeroAll(slot);
        if (piece != null) {
            TickingMaterial tickingMaterial;
            if (piece.getType().getStatistics().getType() != GenericItemType.ARMOR) {
                return statistics;
            }
            Reforge reforge = piece.getReforge() == null ? Reforge.blank() : piece.getReforge();
            strength.set(slot, reforge.getStrength().getForRarity(piece.getRarity()));
            critDamage.set(slot, reforge.getCritDamage().getForRarity(piece.getRarity()));
            critChance.set(slot, reforge.getCritChance().getForRarity(piece.getRarity()));
            intelligence.set(slot, reforge.getIntelligence().getForRarity(piece.getRarity()));
            ferocity.set(slot, reforge.getFerocity().getForRarity(piece.getRarity()));
            atkSpeed.set(slot, reforge.getAttackSpeed().getForRarity(piece.getRarity()));
            PlayerBoostStatistics pieceStatistics = piece.getType().getBoostStatistics();
            if (pieceStatistics != null) {
                PlayerUtils.addBoostStatistics(statistics, slot, pieceStatistics);
            }
            Player player = Bukkit.getPlayer(statistics.getUuid());
            PlayerInventory inv = player.getInventory();
            SItem helm = SItem.find(inv.getHelmet());
            SItem chest = SItem.find(inv.getChestplate());
            SItem legs = SItem.find(inv.getLeggings());
            SItem boot = SItem.find(inv.getBoots());
            if (piece.getDataInt("hpb") > 0) {
                double hphpb = piece.getDataInt("hpb") * 4;
                double defhpb = piece.getDataInt("hpb") * 2;
                health.add(slot, hphpb);
                defense.add(slot, defhpb);
            }
            if (piece.isEnchantable()) {
                for (Enchantment enchantment : piece.getEnchantments()) {
                    if (enchantment.getType() == EnchantmentType.GROWTH) {
                        health.add(slot, 15.0 * (double)enchantment.getLevel());
                    }
                    if (enchantment.getType() == EnchantmentType.PROTECTION) {
                        defense.add(slot, 3.0 * (double)enchantment.getLevel());
                    }
                    if (enchantment.getType() == EnchantmentType.LUCKINESS) {
                        magicFind.add(slot, 0.02 * (double)enchantment.getLevel());
                    }
                    if (enchantment.getType() != EnchantmentType.LEGION) continue;
                    int level = enchantment.getLevel();
                    List<Entity> nearbyplayer = player.getNearbyEntities(30.0, 30.0, 30.0);
                    nearbyplayer.removeIf(entity -> entity.getType() != EntityType.PLAYER || entity.hasMetadata("NPC"));
                    double nbps = Math.min(20, nearbyplayer.size());
                    double multiper = (double)level * 0.07 * nbps;
                    multiper = Math.max(0.0, multiper);
                    defense.add(slot, (multiper /= 100.0) * statistics.getDefense().addAll());
                    strength.add(slot, multiper * statistics.getStrength().addAll());
                    intelligence.add(slot, multiper * statistics.getIntelligence().addAll());
                    speed.add(slot, multiper * statistics.getSpeed().addAll());
                    critChance.add(slot, multiper * statistics.getCritChance().addAll());
                    critDamage.add(slot, multiper * statistics.getCritDamage().addAll());
                    magicFind.add(slot, multiper * statistics.getMagicFind().addAll());
                    trueDefense.add(slot, multiper * statistics.getTrueDefense().addAll());
                    ferocity.add(slot, multiper * statistics.getFerocity().addAll());
                    atkSpeed.add(slot, multiper * statistics.getAttackSpeed().addAll());
                }
            }
            if (piece != null && (player.getWorld().getName().contains("f6") || player.getWorld().getName().contains("dungeon"))) {
                ItemSerial is = ItemSerial.getItemBoostStatistics(piece);
                health.add(slot, is.getHealth());
                defense.add(slot, is.getDefense());
                strength.add(slot, is.getStrength());
                intelligence.add(slot, is.getIntelligence());
                critChance.add(slot, is.getCritchance());
                critDamage.add(slot, is.getCritdamage());
                speed.add(slot, is.getSpeed());
                magicFind.add(slot, is.getMagicFind());
                atkSpeed.add(slot, is.getAtkSpeed());
                ferocity.add(slot, is.getFerocity());
            }
            if (piece.getType() == SMaterial.WARDEN_HELMET || piece.getType() == SMaterial.HIDDEN_VOIDLINGS_WARDEN_HELMET) {
                speed.sub(slot, statistics.getSpeed().addAll() / 2.0);
            }
            if ((tickingMaterial = piece.getType().getTickingInstance()) != null) {
                statistics.tickItem(slot, tickingMaterial.getInterval(), () -> tickingMaterial.tick(piece, Bukkit.getPlayer(statistics.getUuid())));
            }
        }
        PlayerUtils.updateHealth(Bukkit.getPlayer(statistics.getUuid()), statistics);
        return statistics;
    }

    public static PlayerStatistics updatePetStatistics(PlayerStatistics statistics) {
        Player player = Bukkit.getPlayer(statistics.getUuid());
        User user = User.getUser(player.getUniqueId());
        Pet.PetItem active = user.getActivePet();
        DoublePlayerStatistic health = statistics.getMaxHealth();
        DoublePlayerStatistic defense = statistics.getDefense();
        DoublePlayerStatistic strength = statistics.getStrength();
        DoublePlayerStatistic intelligence = statistics.getIntelligence();
        DoublePlayerStatistic ferocity = statistics.getFerocity();
        DoublePlayerStatistic speed = statistics.getSpeed();
        DoublePlayerStatistic critChance = statistics.getCritChance();
        DoublePlayerStatistic critDamage = statistics.getCritDamage();
        DoublePlayerStatistic magicFind = statistics.getMagicFind();
        DoublePlayerStatistic trueDefense = statistics.getTrueDefense();
        DoublePlayerStatistic atkSpeed = statistics.getAttackSpeed();
        DoublePlayerStatistic abilityDamage = statistics.getAbilityDamage();
        if (active != null) {
            int level = Pet.getLevel(active.getXp(), active.getRarity());
            Pet pet = (Pet)active.getType().getGenericInstance();
            health.set(7, pet.getPerHealth() * (double)level);
            defense.set(7, pet.getPerDefense() * (double)level);
            strength.set(7, pet.getPerStrength() * (double)level);
            intelligence.set(7, pet.getPerIntelligence() * (double)level);
            speed.set(7, pet.getPerSpeed() * (double)level);
            critChance.set(7, pet.getPerCritChance() * (double)level);
            critDamage.set(7, pet.getPerCritDamage() * (double)level);
            magicFind.set(7, pet.getPerMagicFind() * (double)level);
            trueDefense.set(7, pet.getPerTrueDefense() * (double)level);
            ferocity.set(7, pet.getPerFerocity() * (double)level);
            atkSpeed.set(7, pet.getPerAttackSpeed() * (double)level);
            abilityDamage.set(7, 0.0);
        }
        double defense1 = statistics.getDefense().addAll();
        double strength1 = statistics.getStrength().addAll();
        double intelligence1 = statistics.getIntelligence().addAll();
        double speed1 = statistics.getSpeed().addAll();
        double critChance1 = statistics.getCritChance().addAll();
        double critDamage1 = statistics.getCritDamage().addAll();
        double magicFind1 = statistics.getMagicFind().addAll();
        double trueDefense1 = statistics.getTrueDefense().addAll();
        double ferocity1 = statistics.getFerocity().addAll() - statistics.getFerocity().getFor(153);
        double atkSpeed1 = statistics.getAttackSpeed().addAll();
        if (active != null) {
            double LevelMul;
            int level = Pet.getLevel(active.getXp(), active.getRarity());
            Pet pet = (Pet)active.getType().getGenericInstance();
            if ((pet.getDisplayName().equals("Ender Dragon") || pet.getDisplayName().equals("Golden Tiger")) && active.getRarity().isAtLeast(Rarity.LEGENDARY)) {
                LevelMul = (double)level * 0.1;
                defense.add(7, defense1 * LevelMul / 100.0);
                strength.add(7, strength1 * LevelMul / 100.0);
                intelligence.add(7, intelligence1 * LevelMul / 100.0);
                speed.add(7, speed1 * LevelMul / 100.0);
                critChance.add(7, critChance1 * LevelMul / 100.0);
                critDamage.add(7, critDamage1 * LevelMul / 100.0);
                magicFind.add(7, magicFind1 * LevelMul / 100.0);
                trueDefense.add(7, trueDefense1 * LevelMul / 100.0);
                ferocity.add(7, ferocity1 * LevelMul / 100.0);
                atkSpeed.add(7, atkSpeed1 * LevelMul / 100.0);
            }
            if (pet.getDisplayName().equals("Mini T-34")) {
                LevelMul = (double)level * 0.2;
                defense.add(7, defense1 * LevelMul / 100.0);
                strength.add(7, strength1 * LevelMul / 100.0);
                intelligence.add(7, intelligence1 * LevelMul / 100.0);
                speed.add(7, speed1 * LevelMul / 100.0);
                critChance.add(7, critChance1 * LevelMul / 100.0);
                critDamage.add(7, critDamage1 * LevelMul / 100.0);
                magicFind.add(7, magicFind1 * LevelMul / 100.0);
                trueDefense.add(7, trueDefense1 * LevelMul / 100.0);
                ferocity.add(7, ferocity1 * LevelMul / 100.0);
                atkSpeed.add(7, atkSpeed1 * LevelMul / 100.0);
            } else if (pet.getDisplayName().equals("Black Cat")) {
                magicFind.add(7, 0.0015 * (double)level);
                speed.add(7, 0.01 * (double)level);
            } else if (pet.getDisplayName().equals("Baby Yeti")) {
                defense.add(7, strength1 * (double)level / 100.0);
            } else if (pet.getDisplayName().equals("Golden Tiger")) {
                magicFind.add(7, strength1 / 100.0 / 100.0);
                if (active.getRarity().isAtLeast(Rarity.MYTHIC)) {
                    int count = 0;
                    long num = user.getBits();
                    while (num != 0L) {
                        num /= 10L;
                        ++count;
                    }
                    ferocity.add(7, (double)count * ((double)level * 0.1));
                    magicFind.add(7, (double)count * ((double)level * 0.05) / 100.0);
                }
            } else if (pet.getDisplayName().equals("Magicivy")) {
                abilityDamage.add(7, Double.valueOf(level));
            }
        } else {
            statistics.zeroAll(7);
        }
        PlayerUtils.updateHealth(player, statistics);
        return statistics;
    }

    public static PlayerStatistics updateSetStatistics(Player player, SItem helmet, SItem chestplate, SItem leggings, SItem boots, PlayerStatistics statistics) {
        DoublePlayerStatistic health = statistics.getMaxHealth();
        DoublePlayerStatistic defense = statistics.getDefense();
        DoublePlayerStatistic strength = statistics.getStrength();
        DoublePlayerStatistic intelligence = statistics.getIntelligence();
        DoublePlayerStatistic ferocity = statistics.getFerocity();
        DoublePlayerStatistic speed = statistics.getSpeed();
        DoublePlayerStatistic critChance = statistics.getCritChance();
        DoublePlayerStatistic critDamage = statistics.getCritDamage();
        DoublePlayerStatistic magicFind = statistics.getMagicFind();
        DoublePlayerStatistic atkSpeed = statistics.getAttackSpeed();
        DoublePlayerStatistic trueDefense = statistics.getTrueDefense();
        if (helmet != null && chestplate != null && leggings != null && boots != null) {
            ArmorSet set = SMaterial.findArmorSet(helmet.getType(), chestplate.getType(), leggings.getType(), boots.getType());
            statistics.setArmorSet(set);
            statistics.zeroAll(5);
            if (set != null) {
                PlayerBoostStatistics boost = set.whileHasFullSet(player);
                if (boost != null) {
                    health.set(5, boost.getBaseHealth());
                    defense.set(5, boost.getBaseDefense());
                    strength.set(5, boost.getBaseStrength());
                    intelligence.set(5, boost.getBaseIntelligence());
                    speed.set(5, boost.getBaseSpeed());
                    critChance.set(5, boost.getBaseCritChance());
                    critDamage.set(5, boost.getBaseCritDamage());
                    magicFind.set(5, boost.getBaseMagicFind());
                    ferocity.set(5, boost.getBaseFerocity());
                    atkSpeed.set(5, boost.getBaseAttackSpeed());
                }
                if (set instanceof GigachadSet) {
                    double defense1 = statistics.getDefense().addAll();
                    double strength1 = statistics.getStrength().addAll();
                    double intelligence1 = statistics.getIntelligence().addAll();
                    double speed1 = statistics.getSpeed().addAll();
                    double critChance1 = statistics.getCritChance().addAll();
                    double critDamage1 = statistics.getCritDamage().addAll();
                    double magicFind1 = statistics.getMagicFind().addAll();
                    double trueDefense1 = statistics.getTrueDefense().addAll();
                    double ferocity1 = statistics.getFerocity().addAll() - statistics.getFerocity().getFor(153);
                    double atkSpeed1 = statistics.getAttackSpeed().addAll();
                    double LevelMul = 20.0;
                    defense.add(5, defense1 * LevelMul / 100.0);
                    strength.add(5, strength1 * LevelMul / 100.0);
                    intelligence.add(5, intelligence1 * LevelMul / 100.0);
                    speed.add(5, speed1 * LevelMul / 100.0);
                    critChance.add(5, critChance1 * LevelMul / 100.0);
                    critDamage.add(5, critDamage1 * LevelMul / 100.0);
                    magicFind.add(5, magicFind1 * LevelMul / 100.0);
                    trueDefense.add(5, trueDefense1 * LevelMul / 100.0);
                    ferocity.add(5, ferocity1 * LevelMul / 100.0);
                    atkSpeed.add(5, atkSpeed1 * LevelMul / 100.0);
                } else if (set instanceof MinichadSet) {
                    double defense1 = statistics.getDefense().addAll();
                    double strength1 = statistics.getStrength().addAll();
                    double intelligence1 = statistics.getIntelligence().addAll();
                    double speed1 = statistics.getSpeed().addAll();
                    double critChance1 = statistics.getCritChance().addAll();
                    double critDamage1 = statistics.getCritDamage().addAll();
                    double magicFind1 = statistics.getMagicFind().addAll();
                    double trueDefense1 = statistics.getTrueDefense().addAll();
                    double ferocity1 = statistics.getFerocity().addAll() - statistics.getFerocity().getFor(153);
                    double atkSpeed1 = statistics.getAttackSpeed().addAll();
                    double LevelMul = 10.0;
                    defense.add(5, defense1 * LevelMul / 100.0);
                    strength.add(5, strength1 * LevelMul / 100.0);
                    intelligence.add(5, intelligence1 * LevelMul / 100.0);
                    speed.add(5, speed1 * LevelMul / 100.0);
                    critChance.add(5, critChance1 * LevelMul / 100.0);
                    critDamage.add(5, critDamage1 * LevelMul / 100.0);
                    magicFind.add(5, magicFind1 * LevelMul / 100.0);
                    trueDefense.add(5, trueDefense1 * LevelMul / 100.0);
                    ferocity.add(5, ferocity1 * LevelMul / 100.0);
                    atkSpeed.add(5, atkSpeed1 * LevelMul / 100.0);
                }
            }
        } else {
            statistics.setArmorSet(null);
            health.zero(5);
            defense.zero(5);
            strength.zero(5);
            intelligence.zero(5);
            speed.zero(5);
            critChance.zero(5);
            critDamage.zero(5);
            magicFind.zero(5);
            ferocity.zero(5);
            atkSpeed.zero(5);
        }
        PlayerUtils.updateHealth(player, statistics);
        return statistics;
    }

    public static PlayerStatistics updateInventoryStatistics(Player player, PlayerStatistics statistics) {
        if (player == null) {
            return null;
        }
        if (statistics == null) {
            return null;
        }
        DoublePlayerStatistic health = statistics.getMaxHealth();
        DoublePlayerStatistic defense = statistics.getDefense();
        DoublePlayerStatistic strength = statistics.getStrength();
        DoublePlayerStatistic intelligence = statistics.getIntelligence();
        DoublePlayerStatistic ferocity = statistics.getFerocity();
        DoublePlayerStatistic speed = statistics.getSpeed();
        DoublePlayerStatistic critChance = statistics.getCritChance();
        DoublePlayerStatistic critDamage = statistics.getCritDamage();
        DoublePlayerStatistic magicFind = statistics.getMagicFind();
        DoublePlayerStatistic atkSpeed = statistics.getAttackSpeed();
        PlayerInventory inventory = player.getInventory();
        ArrayList<SMaterial> materials = new ArrayList<SMaterial>();
        for (int i = 0; i <= inventory.getSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            SItem sItem = SItem.find(stack);
            int slot = 15 + i;
            if (sItem != null) {
                if (materials.contains(sItem.getType()) || sItem.getType().getStatistics().getType() != GenericItemType.ACCESSORY) continue;
                materials.add(sItem.getType());
                if (sItem.getType().getFunction() instanceof AccessoryFunction) {
                    ((AccessoryFunction)sItem.getType().getFunction()).update(sItem, player, slot);
                }
            }
            statistics.zeroAll(slot);
            if (sItem == null) continue;
            Reforge reforge = sItem.getReforge() == null ? Reforge.blank() : sItem.getReforge();
            strength.set(slot, reforge.getStrength().getForRarity(sItem.getRarity()));
            critDamage.set(slot, reforge.getCritDamage().getForRarity(sItem.getRarity()));
            critChance.set(slot, reforge.getCritChance().getForRarity(sItem.getRarity()));
            intelligence.set(slot, reforge.getIntelligence().getForRarity(sItem.getRarity()));
            ferocity.set(slot, reforge.getFerocity().getForRarity(sItem.getRarity()));
            atkSpeed.set(slot, reforge.getAttackSpeed().getForRarity(sItem.getRarity()));
            PlayerBoostStatistics sItemStatistics = sItem.getType().getBoostStatistics();
            if (sItemStatistics != null && sItem.getType().getStatistics().getType() == GenericItemType.ACCESSORY) {
                PlayerUtils.addBoostStatistics(statistics, slot, sItemStatistics);
                continue;
            }
            statistics.zeroAll(slot);
        }
        PlayerUtils.updateHealth(player, statistics);
        return statistics;
    }

    public static void updateP(Player p) {
    }

    public static PlayerStatistics updatePotionEffects(User user, PlayerStatistics statistics) {
        DoublePlayerStatistic health = statistics.getMaxHealth();
        DoublePlayerStatistic defense = statistics.getDefense();
        DoublePlayerStatistic strength = statistics.getStrength();
        DoublePlayerStatistic intelligence = statistics.getIntelligence();
        DoublePlayerStatistic ferocity = statistics.getFerocity();
        DoublePlayerStatistic speed = statistics.getSpeed();
        DoublePlayerStatistic critChance = statistics.getCritChance();
        DoublePlayerStatistic critDamage = statistics.getCritDamage();
        DoublePlayerStatistic magicFind = statistics.getMagicFind();
        DoublePlayerStatistic trueDefense = statistics.getTrueDefense();
        DoublePlayerStatistic atkSpeed = statistics.getAttackSpeed();
        for (int i = 0; i < user.getEffects().size(); ++i) {
            ActivePotionEffect effect2 = user.getEffects().get(i);
            int slot = 52 + i;
            health.zero(slot);
            defense.zero(slot);
            strength.zero(slot);
            intelligence.zero(slot);
            speed.zero(slot);
            critChance.zero(slot);
            critDamage.zero(slot);
            magicFind.zero(slot);
            ferocity.zero(slot);
            atkSpeed.zero(slot);
            trueDefense.zero(slot);
            if (!effect2.getEffect().getType().isInstant()) {
                if (effect2.getRemaining() <= 0L || effect2.getEffect().getType().getStatsUpdate() == null) continue;
                effect2.getEffect().getType().getStatsUpdate().accept(statistics, slot, effect2.getEffect().getLevel());
                continue;
            }
            effect2.setRemaining(0L);
        }
        user.getEffects().removeIf(effect -> effect.getRemaining() <= 0L);
        return statistics;
    }

    public static PlayerStatistics boostPlayer(final PlayerStatistics statistics, final PlayerBoostStatistics boostStatistics, long ticks) {
        if (statistics == null) {
            return null;
        }
        final DoublePlayerStatistic health = statistics.getMaxHealth();
        final DoublePlayerStatistic defense = statistics.getDefense();
        final DoublePlayerStatistic strength = statistics.getStrength();
        final DoublePlayerStatistic intelligence = statistics.getIntelligence();
        final DoublePlayerStatistic ferocity = statistics.getFerocity();
        final DoublePlayerStatistic speed = statistics.getSpeed();
        final DoublePlayerStatistic critChance = statistics.getCritChance();
        final DoublePlayerStatistic critDamage = statistics.getCritDamage();
        final DoublePlayerStatistic magicFind = statistics.getMagicFind();
        final DoublePlayerStatistic atkSpeed = statistics.getAttackSpeed();
        health.add(6, boostStatistics.getBaseHealth());
        defense.add(6, boostStatistics.getBaseDefense());
        strength.add(6, boostStatistics.getBaseStrength());
        intelligence.add(6, boostStatistics.getBaseIntelligence());
        speed.add(6, boostStatistics.getBaseSpeed());
        critChance.add(6, boostStatistics.getBaseCritChance());
        critDamage.add(6, boostStatistics.getBaseCritDamage());
        magicFind.add(6, boostStatistics.getBaseMagicFind());
        ferocity.add(6, boostStatistics.getBaseFerocity());
        atkSpeed.add(6, boostStatistics.getBaseAttackSpeed());
        PlayerUtils.updateHealth(Bukkit.getPlayer(statistics.getUuid()), statistics);
        new BukkitRunnable(){

            public void run() {
                health.sub(6, boostStatistics.getBaseHealth());
                defense.sub(6, boostStatistics.getBaseDefense());
                strength.sub(6, boostStatistics.getBaseStrength());
                intelligence.sub(6, boostStatistics.getBaseIntelligence());
                speed.sub(6, boostStatistics.getBaseSpeed());
                critChance.sub(6, boostStatistics.getBaseCritChance());
                critDamage.sub(6, boostStatistics.getBaseCritDamage());
                magicFind.sub(6, boostStatistics.getBaseMagicFind());
                ferocity.sub(6, boostStatistics.getBaseFerocity());
                atkSpeed.sub(6, boostStatistics.getBaseAttackSpeed());
                PlayerUtils.updateHealth(Bukkit.getPlayer(statistics.getUuid()), statistics);
            }
        }.runTaskLater(SkySimEngine.getPlugin(), ticks);
        return statistics;
    }

    public static DamageResult getDamageDealt(ItemStack weapon, Player player, Entity damaged, boolean arrowHit) {
        PlayerInventory inv;
        SItem helmet;
        SItem sItem = SItem.find(weapon);
        double rot = (player.getLocation().getYaw() - 90.0f) % 360.0f;
        if (rot < 0.0) {
            rot += 360.0;
        }
        String facingDirection = "null";
        facingDirection = 0.0 <= rot && rot < 22.5 ? "North" : (22.5 <= rot && rot < 67.5 ? "Northeast" : (67.5 <= rot && rot < 112.5 ? "East" : (112.5 <= rot && rot < 157.5 ? "Southeast" : (157.5 <= rot && rot < 202.5 ? "South" : (202.5 <= rot && rot < 247.5 ? "Southwest" : (247.5 <= rot && rot < 292.5 ? "West" : (292.5 <= rot && rot < 337.5 ? "Northwest" : (337.5 <= rot && rot < 360.0 ? "North" : "null"))))))));
        double rot1 = (damaged.getLocation().getYaw() - 90.0f) % 360.0f;
        if (rot1 < 0.0) {
            rot1 += 360.0;
        }
        String facingDirection1 = "null";
        facingDirection1 = 0.0 <= rot1 && rot1 < 22.5 ? "North" : (22.5 <= rot1 && rot1 < 67.5 ? "Northeast" : (67.5 <= rot1 && rot1 < 112.5 ? "East" : (112.5 <= rot1 && rot1 < 157.5 ? "Southeast" : (157.5 <= rot1 && rot1 < 202.5 ? "South" : (202.5 <= rot1 && rot1 < 247.5 ? "Southwest" : (247.5 <= rot1 && rot1 < 292.5 ? "West" : (292.5 <= rot1 && rot1 < 337.5 ? "Northwest" : (337.5 <= rot1 && rot1 < 360.0 ? "North" : facingDirection))))))));
        int damage = 0;
        double enchantBonus = 0.0;
        double potionBonus = 0.0;
        PlayerStatistics statistics = STATISTICS_CACHE.get(player.getUniqueId());
        int critChanceMul = (int)(statistics.getCritChance().addAll() * 100.0);
        double critDamage = statistics.getCritDamage().addAll();
        double hpbwea = 0.0;
        long cap = 35000000000L;
        double d1 = Math.pow(Math.min(cap, User.getUser(player.getUniqueId()).getCoins()), 0.25);
        double finald = 2.5 * d1;
        int fd2 = (int)finald;
        double bonusDamage = 0.0;
        double bonusEn = 0.0;
        double strength = statistics.getStrength().addAll();
        double strength1 = strength % 10.0;
        double real = strength - strength1;
        double critgive = real / 10.0;
        double critreal = critgive / 100.0;
        double speed = Math.min(500.0, statistics.getSpeed().addAll() * 100.0);
        double realSpeed = Math.min(500.0, speed * 100.0) % 25.0;
        double realSpeedDIV = speed - realSpeed;
        double realSpeedDIVC = realSpeedDIV / 25.0;
        long leftpercent = 0L;
        double addDmg = 0.0;
        if (damaged instanceof LivingEntity) {
            leftpercent = Math.round(100.0 - ((LivingEntity)damaged).getHealth() / ((LivingEntity)damaged).getMaxHealth() * 100.0);
        }
        if ((helmet = SItem.find((inv = player.getInventory()).getHelmet())) != null && helmet.getType() == SMaterial.TARANTULA_HELMET) {
            critDamage += critreal;
        }
        if (sItem != null && sItem.getDataInt("hpb") > 0) {
            hpbwea = sItem.getDataInt("hpb") * 2;
        }
        SItem helm = SItem.find(inv.getHelmet());
        SItem chest = SItem.find(inv.getChestplate());
        SItem legs = SItem.find(inv.getLeggings());
        SItem boot = SItem.find(inv.getBoots());
        User user = User.getUser(player.getUniqueId());
        Pet pet = user.getActivePetClass();
        Pet.PetItem active1 = user.getActivePet();
        if (active1 != null) {
            int level;
            Pet.PetItem active;
            if (pet.getDisplayName().equals("Ender Dragon")) {
                active = user.getActivePet();
                level = Pet.getLevel(active.getXp(), active.getRarity());
                if (Groups.ENDERMAN.contains(damaged.getType())) {
                    enchantBonus += 0.25 * (double)level / 100.0;
                }
                if (sItem != null && sItem.getType() == SMaterial.ASPECT_OF_THE_DRAGONS) {
                    damage = (int)((double)damage + (double)level * 0.5);
                    strength += 0.3 * (double)level;
                }
            } else if (pet.getDisplayName().equals("Baby Yeti")) {
                active = user.getActivePet();
                level = Pet.getLevel(active.getXp(), active.getRarity());
                if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SNOW || player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK) {
                    strength += 0.5 * (double)level;
                    critDamage += 0.005 * (double)level;
                }
            } else if (pet.getDisplayName().equals("Golden Tiger")) {
                active = user.getActivePet();
                level = Pet.getLevel(active.getXp(), active.getRarity());
                long ferocity = Math.round(statistics.getFerocity().addAll());
                addDmg += (double)((int)ferocity / 30) * ((double)level * 0.1);
            } else if (pet.getDisplayName().equals("Archivy") && user.isHeadShot()) {
                critChanceMul = 100;
            }
        }
        if (sItem != null && (sItem.getType().getStatistics().getType() != GenericItemType.RANGED_WEAPON || arrowHit)) {
            SItem sitem;
            int level;
            Object type;
            PlayerBoostStatistics playerBoostStatistics = sItem.getType().getBoostStatistics();
            if (playerBoostStatistics != null) {
                damage = playerBoostStatistics.getBaseDamage();
            }
            if (sItem.getType() == SMaterial.EMERALD_BLADE) {
                damage = (int)((double)damage + finald);
            }
            if (sItem.getType().getStatistics().getType() == GenericItemType.WEAPON && sItem.getEnchantment(EnchantmentType.ONE_FOR_ALL) != null) {
                Enchantment e = sItem.getEnchantment(EnchantmentType.ONE_FOR_ALL);
                damage += damage * (e.getLevel() * 210) / 100;
            }
            if (helmet != null) {
                if (helmet.getType() == SMaterial.WARDEN_HELMET) {
                    damage = (int)((double)damage + 20.0 * realSpeedDIVC / 100.0 * (double)damage);
                } else if (helmet.getType() == SMaterial.HIDDEN_VOIDLINGS_WARDEN_HELMET) {
                    damage = (int)((double)damage + 35.0 * realSpeedDIVC / 100.0 * (double)damage);
                }
            }
            damage = (int)((double)damage + bonusEn);
            damage = (int)((double)damage + hpbwea);
            strength += hpbwea;
            damage = (int)((double)damage + addDmg);
            if (helmet != null && helmet.getType() == SMaterial.CROWN_OF_GREED) {
                damage = (int)((double)damage + PlayerListener.COGCalculation(damage, player));
            }
            if (sItem.getType() == SMaterial.POOCH_SWORD && EntityType.WOLF.equals(damaged.getType())) {
                strength += 150.0;
            }
            if (user.toBukkitPlayer().getWorld().getName().contains("f6") || user.toBukkitPlayer().getWorld().getName().contains("dungeon")) {
                damage += (int)ItemSerial.getItemBoostStatistics(sItem).getDamage();
            }
            if (sItem.getType() == SMaterial.PRISMARINE_BLADE && player.getLocation().getBlock().getType() == Material.WATER || player.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
                enchantBonus += 3.0;
            }
            if (sItem.getType() == SMaterial.AXE_OF_THE_SHREDDED && Groups.UNDEAD_MOBS.contains(damaged.getType())) {
                enchantBonus += 3.5;
            }
            if (sItem.getType() == SMaterial.DEATH_BOW && Groups.UNDEAD_MOBS.contains(damaged.getType()) && arrowHit) {
                enchantBonus += 2.0;
            }
            if (sItem.getType() == SMaterial.AXE_OF_THE_SHREDDED) {
                player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + 50.0));
            }
            if (sItem.getType() == SMaterial.REAPER_FALCHION && Groups.UNDEAD_MOBS.contains(damaged.getType())) {
                enchantBonus += 3.0;
            }
            if (sItem.getType() == SMaterial.REAPER_FALCHION) {
                player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + 10.0));
            }
            if (sItem.getType() == SMaterial.VOIDWALKER_KATANA && Groups.ENDERMAN.contains(damaged.getType())) {
                enchantBonus += 2.0;
            }
            if (sItem.getType() == SMaterial.VOIDEDGE_KATANA && Groups.ENDERMAN.contains(damaged.getType())) {
                enchantBonus += 2.5;
            }
            if (sItem.getType() == SMaterial.VORPAL_KATANA && Groups.ENDERMAN.contains(damaged.getType())) {
                enchantBonus += 3.0;
            }
            if (sItem.getType() == SMaterial.ATOMSPLIT_KATANA && Groups.ENDERMAN.contains(damaged.getType())) {
                enchantBonus += 3.5;
            }
            if (sItem.getType() == SMaterial.LIVID_DAGGER && facingDirection == facingDirection1) {
                enchantBonus += 2.0;
            }
            if (sItem.getType() != SMaterial.ENCHANTED_BOOK && sItem.isEnchantable()) {
                for (Enchantment enchantment : sItem.getEnchantments()) {
                    type = enchantment.getType();
                    level = enchantment.getLevel();
                    if (type == EnchantmentType.SHARPNESS && !arrowHit) {
                        enchantBonus += (double)(level * 5) / 100.0;
                    }
                    if (type == EnchantmentType.EXECUTE && !arrowHit) {
                        enchantBonus += (double)level * 0.2 * (double)leftpercent / 100.0;
                    }
                    if (type == EnchantmentType.FIRE_ASPECT && sItem.getType().getStatistics().getType() == GenericItemType.WEAPON) {
                        damaged.setFireTicks(0);
                    }
                    if (type == EnchantmentType.POWER && arrowHit) {
                        enchantBonus += (double)(level * 8) / 100.0;
                    }
                    if (type == EnchantmentType.FLAME && arrowHit) {
                        damaged.setFireTicks(0);
                    }
                    if (type == EnchantmentType.ENDER_SLAYER && !arrowHit && Groups.ENDERMAN.contains(damaged.getType())) {
                        enchantBonus += (double)(level * 12) / 100.0;
                    }
                    if (type == EnchantmentType.DRAGON_HUNTER && Groups.ENDERDRAGON.contains(damaged.getType())) {
                        enchantBonus += (double)(level * 8) / 100.0;
                    }
                    if (type == EnchantmentType.SMITE && !arrowHit && Groups.UNDEAD_MOBS.contains(damaged.getType())) {
                        enchantBonus += (double)(level * 8) / 100.0;
                    }
                    if (type == EnchantmentType.BANE_OF_ARTHROPODS && !arrowHit && Groups.ARTHROPODS.contains(damaged.getType())) {
                        enchantBonus += (double)(level * 8) / 100.0;
                    }
                    if (type == EnchantmentType.CRITICAL) {
                        critDamage += (double)(level * 10) / 100.0;
                    }
                    if (type == EnchantmentType.SOUL_EATER && SOUL_EATER_MAP.containsKey(player.getUniqueId()) && SOUL_EATER_MAP.get(player.getUniqueId()) != null) {
                        bonusDamage += SOUL_EATER_MAP.get(player.getUniqueId()).getStatistics().getDamageDealt() * (double)(level * 2);
                        SOUL_EATER_MAP.put(player.getUniqueId(), null);
                    }
                    if (type != EnchantmentType.LIFE_STEAL || level <= 0) continue;
                    player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + (double)level * 0.5 / 100.0 * player.getMaxHealth()));
                }
            }
            for (ActivePotionEffect effect : User.getUser(player.getUniqueId()).getEffects()) {
                type = effect.getEffect().getType();
                level = effect.getEffect().getLevel();
                if (type != vn.giakhanhvn.skysim.potion.PotionEffectType.ARCHERY || !arrowHit) continue;
                potionBonus += SUtil.getOrDefault(Arrays.asList(12.5, 25.0, 50.0, 75.0), level - 1, Double.valueOf((double)level * 25.0 - 25.0)) / 100.0;
            }
            if (sItem.getEnchantment(EnchantmentType.FATAL_TEMPO) != null) {
                int lvl = sItem.getEnchantment(EnchantmentType.FATAL_TEMPO).getLevel();
                double ferocity = statistics.getFerocity().addAll() - statistics.getFerocity().getFor(153);
                user.setBonusFerocity((int)Math.min(ferocity * 200.0 / 100.0, (double)user.getBonusFerocity() + ferocity * ((double)lvl * 5.0 / 100.0)));
                if (!user.isFatalActive()) {
                    SUtil.delay(() -> {
                        user.setBonusFerocity(0);
                        user.setFatalActive(false);
                    }, 60L);
                }
                user.setFatalActive(true);
            }
            if (player.getItemInHand() != null && (sitem = SItem.of(player.getItemInHand())) != null && sitem.getEnchantment(EnchantmentType.VAMPIRISM) != null) {
                double lvl = sitem.getEnchantment(EnchantmentType.VAMPIRISM).getLevel();
                if (lvl > 100.0) {
                    lvl = 100.0;
                }
                double aB = player.getHealth() + lvl / 100.0 * (player.getMaxHealth() - player.getHealth());
                double aC = Math.min(player.getMaxHealth(), aB);
                player.setHealth(aC);
            }
        }
        int combatLevel = Skill.getLevel(User.getUser(player.getUniqueId()).getCombatXP(), false);
        double weaponBonus = 0.0;
        double armorBonus = 1.0;
        int chance = SUtil.random(0, 100);
        if (chance > critChanceMul) {
            critDamage = 0.0;
        }
        double baseDamage = (double)(5 + damage) * (1.0 + strength / 100.0);
        double damageMultiplier = 1.0 + (double)combatLevel * 0.04 + enchantBonus + weaponBonus;
        final double finalCritDamage = critDamage;
        double finalDamage = baseDamage * damageMultiplier * armorBonus * (1.0 + finalCritDamage) + bonusDamage;
        double finalPotionBonus = potionBonus;
        if (EdibleMace.edibleMace.containsKey(player.getUniqueId()) && EdibleMace.edibleMace.get(player.getUniqueId()).booleanValue()) {
            finalDamage *= 2.0;
            finalPotionBonus *= 2.0;
            EdibleMace.edibleMace.put(player.getUniqueId(), false);
        }
        if (sItem != null) {
            if (sItem.getType() == SMaterial.POOCH_SWORD) {
                double health1 = statistics.getMaxHealth().addAll();
                health1 = Math.min(300000.0, health1);
                double dmggive = health1 % 50.0;
                double health2 = health1 - dmggive;
                finalDamage += health2 / 50.0;
            }
            if (sItem.getType().getStatistics().getType() == GenericItemType.WEAPON && sItem.getEnchantment(EnchantmentType.FIRST_STRIKE) != null && EntityFunction.FIRST_STRIKE_MAP.containsKey(damaged) && !EntityFunction.FIRST_STRIKE_MAP.get(damaged).contains(player.getUniqueId())) {
                finalDamage += finalDamage * ((double)sItem.getEnchantment(EnchantmentType.FIRST_STRIKE).getLevel() * 25.0 / 100.0);
                EntityFunction.FIRST_STRIKE_MAP.get(damaged).add(player.getUniqueId());
            }
        }
        double FinalDMG = finalDamage;
        double finalPot = finalPotionBonus;
        double fds = (FinalDMG + FinalDMG * finalPot) * (VoidlingsWardenHelmet.VOIDLING_WARDEN_BUFF.containsKey(user.getUuid()) ? 1.5 : 1.0);
        if (active1 != null && pet.getDisplayName().equals("Archivy") && arrowHit) {
            Pet.PetItem active = user.getActivePet();
            int level = Pet.getLevel(active.getXp(), active.getRarity());
            fds += fds * (double)((float)level / 2.0f) / 100.0;
            if (user.isHeadShot()) {
                fds += fds * (double)((float)level * 0.75f) / 100.0;
                user.toBukkitPlayer().playSound(user.toBukkitPlayer().getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                for (int i = 0; i < 50; ++i) {
                    damaged.getWorld().spigot().playEffect(damaged.getLocation().clone().add(0.0, 1.5, 0.0), Effect.MAGIC_CRIT, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(0.0, 0.5), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 100);
                }
            }
        }
        user.setHeadShot(false);
        final double fdsfinal = fds;
        return new DamageResult(){

            @Override
            public double getFinalDamage() {
                return fdsfinal;
            }

            @Override
            public boolean didCritDamage() {
                return finalCritDamage != 0.0;
            }
        };
    }

    public static void useAbility(Player player, final SItem sItem) {
        AbilityActivation activation;
        final Ability ability = sItem.getType().getAbility();
        if (ability != null && (activation = ability.getAbilityActivation()) != AbilityActivation.NO_ACTIVATION) {
            final UUID uuid = player.getUniqueId();
            if (ability.requirementsUse(player, sItem)) {
                player.sendMessage(Sputnik.trans(ability.getAbilityReq()));
                return;
            }
            if (COOLDOWN_MAP.containsKey(uuid) && COOLDOWN_MAP.get(uuid).contains(sItem.getType())) {
                if (ability.displayCooldown()) {
                    player.sendMessage(ChatColor.RED + "You currently have a cooldown for this ability!");
                }
            } else {
                int cost;
                int mana = Repeater.MANA_MAP.get(uuid);
                int resMana = mana - (cost = PlayerUtils.getFinalManaCost(player, sItem, ability.getManaCost()));
                if (resMana >= 0) {
                    Repeater.MANA_MAP.remove(uuid);
                    Repeater.MANA_MAP.put(uuid, resMana);
                    try {
                        ability.onAbilityUse(player, sItem);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (ability.displayUsage() && sItem.getType() != SMaterial.AXE_OF_THE_SHREDDED && sItem.getType() != SMaterial.BONEMERANG && sItem.getType() != SMaterial.SHADOW_FURY && sItem.getType() != SMaterial.ASPECT_OF_THE_JERRY && sItem.getType() != SMaterial.FLOWER_OF_TRUTH && sItem.getType() != SMaterial.EDIBLE_MACE) {
                        final long c = System.currentTimeMillis();
                        Repeater.DEFENSE_REPLACEMENT_MAP.put(player.getUniqueId(), new DefenseReplacement(){

                            @Override
                            public String getReplacement() {
                                return ChatColor.AQUA + "-" + cost + " Mana (" + ChatColor.GOLD + ability.getAbilityName() + ChatColor.AQUA + ")";
                            }

                            @Override
                            public long getEnd() {
                                return c + 2000L;
                            }
                        });
                    }
                    if (ability.getAbilityCooldownTicks() != 0) {
                        if (COOLDOWN_MAP.containsKey(uuid)) {
                            COOLDOWN_MAP.get(uuid).add(sItem.getType());
                        } else {
                            COOLDOWN_MAP.put(uuid, new ArrayList<SMaterial>(Collections.singletonList(sItem.getType())));
                        }
                        new BukkitRunnable(){

                            public void run() {
                                COOLDOWN_MAP.get(uuid).remove(sItem.getType());
                                if (COOLDOWN_MAP.get(uuid).size() == 0) {
                                    COOLDOWN_MAP.remove(uuid);
                                }
                            }
                        }.runTaskLater(SkySimEngine.getPlugin(), ability.getAbilityCooldownTicks());
                    }
                } else {
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
                }
            }
        }
    }

    public static void updateHealth(Player player, PlayerStatistics statistics) {
        boolean fill;
        if (player == null) {
            return;
        }
        boolean bl = fill = player.getHealth() == player.getMaxHealth();
        if (player.getMaxHealth() != statistics.getMaxHealth().addAll().doubleValue()) {
            player.setMaxHealth(statistics.getMaxHealth().addAll().doubleValue());
        }
        if (fill) {
            player.setHealth(player.getMaxHealth());
        }
    }

    public static List<SItem> getAccessories(Player player) {
        ArrayList<SItem> accessories = new ArrayList<SItem>();
        ArrayList<SMaterial> types = new ArrayList<SMaterial>();
        for (ItemStack stack : player.getInventory()) {
            SItem sItem = SItem.find(stack);
            if (sItem == null || sItem.getType().getStatistics().getType() != GenericItemType.ACCESSORY || types.contains(sItem.getType())) continue;
            accessories.add(sItem);
            types.add(sItem.getType());
        }
        return accessories;
    }

    public static boolean hasItem(Player player, SMaterial material) {
        for (ItemStack stack : player.getInventory()) {
            SItem sItem = SItem.find(stack);
            if (sItem == null || sItem.getType() != material) continue;
            return true;
        }
        return false;
    }

    public static void sendToIsland(Player player) {
        User user;
        World world = Bukkit.getWorld("islands");
        if (world == null) {
            world = new BlankWorldCreator("islands").createWorld();
        }
        if ((user = User.getUser(player.getUniqueId())).getIslandX() == null) {
            Config config = SkySimEngine.getPlugin().config;
            double xOffset = config.getDouble("islands.x");
            double zOffset = config.getDouble("islands.z");
            if (xOffset < -2.5E7 || xOffset > 2.5E7) {
                zOffset += 250.0;
            }
            File file = new File(config.getString("islands.schematic"));
            SUtil.pasteSchematic(file, new Location(world, 7.0 + xOffset, 100.0, 7.0 + zOffset), true);
            SUtil.setBlocks(new Location(world, 7.0 + xOffset, 104.0, 44.0 + zOffset), new Location(world, 5.0 + xOffset, 100.0, 44.0 + zOffset), Material.PORTAL, false);
            user.setIslandLocation(7.5 + xOffset, 7.5 + zOffset);
            user.save();
            if (xOffset > 0.0) {
                xOffset *= -1.0;
            } else if (xOffset <= 0.0) {
                if (xOffset != 0.0) {
                    xOffset *= -1.0;
                }
                xOffset += 250.0;
            }
            config.set("islands.x", xOffset);
            config.set("islands.z", zOffset);
            config.save();
        }
        World finalWorld = world;
        SUtil.delay(() -> player.teleport(finalWorld.getHighestBlockAt(SUtil.blackMagic(user.getIslandX()), SUtil.blackMagic(user.getIslandZ())).getLocation().add(0.5, 1.0, 0.5)), 10L);
    }

    public static PotionEffect getPotionEffect(Player player, PotionEffectType type) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (!effect.getType().getName().equals(type.getName())) continue;
            return effect;
        }
        return null;
    }

    public static void replacePotionEffect(Player player, PotionEffect add) {
        PotionEffect effect = PlayerUtils.getPotionEffect(player, add.getType());
        if (effect != null && effect.getAmplifier() > add.getAmplifier()) {
            return;
        }
        player.removePotionEffect(add.getType());
        player.addPotionEffect(add);
    }

    public static void handleSpecEntity(Entity entity, Player damager, AtomicDouble finalDamage) {
        if (entity.hasMetadata("isDead")) {
            return;
        }
        SEntity sEntity = SEntity.findSEntity(entity);
        if (sEntity != null) {
            EntityFunction function = sEntity.getFunction();
            if (damager != null) {
                sEntity.addDamageFor(damager, finalDamage.get());
            }
            if (((LivingEntity)entity).getHealth() - finalDamage.get() <= 0.0) {
                SItem sitem1;
                Watcher watcher;
                function.onDeath(sEntity, entity, damager);
                if (entity.hasMetadata("LD")) {
                    Sputnik.zero(entity);
                }
                if (entity.hasMetadata("WATCHER_E") && (watcher = Watcher.getWatcher(entity.getWorld())) != null) {
                    --watcher.currentMobsCount;
                    if (SUtil.random(0, 1) == 0) {
                        watcher.sd(watcher.mobDeathConvs[SUtil.random(0, watcher.mobDeathConvs.length - 1)], 0, 50, true);
                    }
                }
                if (damager != null) {
                    damager.playSound(damager.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
                }
                if ((sitem1 = SItem.find(damager.getItemInHand())) != null && sitem1.getEnchantment(EnchantmentType.SOUL_EATER) != null && sitem1.getType() != SMaterial.ENCHANTED_BOOK) {
                    SOUL_EATER_MAP.put(damager.getUniqueId(), sEntity);
                }
                if (sitem1 != null && sitem1.getEnchantment(EnchantmentType.TURBO_GEM) != null && sitem1.getType() != SMaterial.ENCHANTED_BOOK) {
                    User.getUser(damager.getUniqueId()).addBits(sitem1.getEnchantment(EnchantmentType.TURBO_GEM).getLevel());
                }
                User user = User.getUser(damager.getUniqueId());
                double xpDropped = sEntity.getStatistics().getXPDropped();
                if (PlayerUtils.getCookieDurationTicks(damager) > 0L) {
                    xpDropped += sEntity.getStatistics().getXPDropped() * 35.0 / 100.0;
                }
                Skill.reward(CombatSkill.INSTANCE, xpDropped, damager);
                SlayerQuest quest = user.getSlayerQuest();
                if (sEntity.getGenericInstance() instanceof SlayerBoss && !((SlayerBoss)sEntity.getGenericInstance()).getSpawnerUUID().equals(damager.getUniqueId())) {
                    PlayerUtils.finishSlayerQuest(((SlayerBoss)sEntity.getGenericInstance()).getSpawnerUUID());
                }
                if (quest != null && sEntity.getSpecType().getCraftType() == quest.getType().getType().getEntityType() && !damager.getWorld().getName().contains("f6")) {
                    if (sEntity.getGenericInstance() instanceof SlayerBoss && ((SlayerBoss)sEntity.getGenericInstance()).getSpawnerUUID().equals(damager.getUniqueId())) {
                        PlayerUtils.finishSlayerQuest(damager.getUniqueId());
                    } else {
                        double xpDropped2 = sEntity.getStatistics().getXPDropped();
                        if (PlayerUtils.getCookieDurationTicks(damager) > 0L) {
                            xpDropped2 += sEntity.getStatistics().getXPDropped() * 35.0 / 100.0;
                        }
                        quest.setXp(quest.getXp() + xpDropped2);
                        if (!(sEntity.getGenericInstance() instanceof SlayerBoss)) {
                            quest.setLastKilled(sEntity.getSpecType());
                        }
                        if (quest.getXp() >= (double)quest.getType().getSpawnXP() && quest.getSpawned() == 0L) {
                            Location location = entity.getLocation().clone().add(0.0, 1.0, 0.0);
                            quest.setSpawned(System.currentTimeMillis());
                            SlayerQuest.playBossSpawn(location, damager);
                            SUtil.delay(() -> quest.setEntity(new SEntity(location, quest.getType().getSpecType(), quest.getType().getTier(), damager.getUniqueId())), 28L);
                        }
                    }
                }
                entity.setMetadata("isDead", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
                boolean rare = false;
                for (EntityDrop drop : SUtil.shuffle(function.drops())) {
                    SItem sitem;
                    int r;
                    EntityDropType type = drop.getType();
                    double magicFind = STATISTICS_CACHE.get(damager.getUniqueId()).getMagicFind().addAll() / 100.0;
                    double sp = 100.0 * (drop.getDropChance() * (1.0 + magicFind * 10000.0 / 100.0));
                    if (!SkySimEngine.getPlugin().config.getBoolean("disableDebug")) {
                        SLog.info("-------------------------------");
                        SLog.info("Final SP " + sp);
                        SLog.info("Drop chance " + drop.getDropChance());
                        SLog.info("RM " + Math.round(100.0 / sp));
                        SLog.info("Mob Killed " + sEntity.getStatistics().getEntityName());
                        if (drop.getDropChance() >= 0.05) {
                            SLog.info("Canc! Item is " + type.getDisplay());
                        }
                        SLog.info("-------------------------------");
                    }
                    if (drop.getDropChance() >= 0.25) {
                        sp = 100.0 * (drop.getDropChance());
                    }
                    if ((r = SUtil.random(1, (int)Math.round(100.0 / sp))) != 1 || rare && type != EntityDropType.GUARANTEED) continue;
                    ItemStack stack = drop.getStack();
                    SItem sItem = SItem.find(stack);
                    if (sItem == null) {
                        sItem = SItem.of(stack);
                    }
                    MaterialStatistics s = sItem.getType().getStatistics();
                    String name = sItem.getRarity().getColor() + sItem.getType().getDisplayName(sItem.getVariant());
                    MaterialFunction f = sItem.getType().getFunction();
                    if (f != null && s.getType() != GenericItemType.ACCESSORY) {
                        f.onKill(entity, damager, sItem);
                    }
                    if (damager != null) {
                        for (SItem accessory : PlayerUtils.getAccessories(damager)) {
                            if (accessory.getType().getStatistics().getType() != GenericItemType.ACCESSORY) continue;
                            accessory.getType().getFunction().onKill(entity, damager, sItem);
                        }
                    }
                    if (type != EntityDropType.GUARANTEED && type != EntityDropType.COMMON && damager != null) {
                        damager.sendMessage(type.getColor() + "" + ChatColor.BOLD + (type == EntityDropType.CRAZY_RARE ? "CRAZY " : (type == EntityDropType.INSANE_RARE ? "INSANE " : "")) + "RARE DROP! " + ChatColor.GRAY + "(" + name + ChatColor.GRAY + ")" + (magicFind != 0.0 ? ChatColor.AQUA + " (+" + (int)(magicFind * 10000.0) + "% Magic Find!)" : ""));
                    }
                    if (sEntity.getStatistics().mobLevel() >= 25 && SUtil.random(1, 25000) == 1 && Skill.getLevel(User.getUser(damager.getUniqueId()).getCombatXP(), false) >= 20) {
                        int chance = (int)((double)Skill.getLevel(User.getUser(damager.getUniqueId()).getCombatXP(), false) * 1.5);
                        Random rnd = new Random();
                        int n = rnd.nextInt(100);
                    }
                    if (sEntity.getEntity().getType() == EntityType.ENDERMAN && SUtil.random(1, 40) == 1 && sEntity.getStatistics().mobLevel() >= 90 && SlayerBossType.SlayerMobType.ENDERMAN.getLevelForXP(User.getUser(damager.getUniqueId()).getEndermanSlayerXP()) >= 6 && User.getUser(damager.getUniqueId()).getActivePet() != null && User.getUser(damager.getUniqueId()).getActivePet().getType() == SMaterial.HIDDEN_VOIDLINGS_PET) {
                        PacketInvoker.dropVoidSpawner(damager, sEntity.getEntity().getLocation());
                    }
                    if ((sitem = SItem.find(damager.getItemInHand())) != null) {
                        if (drop.getOwner() == null) {
                            if (sitem.getEnchantment(EnchantmentType.TELEKINESIS) != null && !Sputnik.isFullInv(damager) && sitem.getType() != SMaterial.ENCHANTED_BOOK) {
                                Sputnik.GiveItem(sItem.getStack(), damager);
                            } else {
                                entity.getWorld().dropItem(entity.getLocation(), stack);
                            }
                        } else if (sitem.getEnchantment(EnchantmentType.TELEKINESIS) != null && !Sputnik.isFullInv(damager) && sitem.getType() != SMaterial.ENCHANTED_BOOK) {
                            Sputnik.GiveItem(sItem.getStack(), damager);
                        } else {
                            SUtil.spawnPersonalItem(stack, entity.getLocation(), drop.getOwner());
                        }
                    } else {
                        entity.getWorld().dropItem(entity.getLocation(), stack);
                    }
                    if (type == EntityDropType.GUARANTEED) continue;
                    rare = true;
                }
            }
        }
    }

    public static void finishSlayerQuest(UUID uuid) {
        Player damager = Bukkit.getPlayer(uuid);
        User user = User.getUser(uuid);
        SlayerQuest quest = user.getSlayerQuest();
        if (quest != null && quest.getDied() == 0L && quest.getKilled() == 0L) {
            quest.setKilled(System.currentTimeMillis());
            damager.playSound(damager.getLocation(), Sound.LEVEL_UP, 1.0f, 2.0f);
            damager.sendMessage("  " + ChatColor.GOLD + ChatColor.BOLD + "NICE! SLAYER BOSS SLAIN!");
            damager.sendMessage("   " + ChatColor.DARK_PURPLE + ChatColor.BOLD + "\u00bb " + ChatColor.GRAY + "Talk to Maddox to claim your " + quest.getType().getType().getName() + " Slayer XP!");
            if (PlayerUtils.isAutoSlayer(damager)) {
                SlayerGUI.claimReward(damager);
                StringBuilder sb = new StringBuilder();
                String bossType = quest.getType().getType().getName();
                if (bossType.toLowerCase().contains("zombie")) {
                    sb.append("REVENANT_HORROR_");
                }
                if (bossType.toLowerCase().contains("wolf")) {
                    sb.append("SVEN_PACKMASTER_");
                }
                if (bossType.toLowerCase().contains("spider")) {
                    sb.append("TARANTULA_BROODFATHER_");
                }
                if (bossType.toLowerCase().contains("enderman")) {
                    sb.append("VOIDGLOOM_SERAPH_");
                }
                sb.append(SUtil.toRomanNumeral(quest.getType().getTier()));
                User.getUser(damager.getUniqueId()).startSlayerQuest(SlayerBossType.getByNamespace(sb.toString()));
            }
        }
    }

    public static boolean takeMana(Player player, int amount) {
        int n = Repeater.MANA_MAP.get(player.getUniqueId()) - amount;
        if (n < 0) {
            return false;
        }
        Repeater.MANA_MAP.put(player.getUniqueId(), n);
        return true;
    }

    public static int getFinalManaCost(Player player, SItem sItem, int cost) {
        Enchantment ultimateWise;
        PlayerStatistics statistics = STATISTICS_CACHE.get(player.getUniqueId());
        int manaPool = 100 + SUtil.blackMagic(statistics.getIntelligence().addAll());
        int updated = cost;
        ArmorSet set = STATISTICS_CACHE.get(player.getUniqueId()).getArmorSet();
        if (set != null && set.equals(SMaterial.WISE_DRAGON_SET)) {
            updated = 0;
        }
        if ((ultimateWise = sItem.getEnchantment(EnchantmentType.ULTIMATE_WISE)) != null) {
            updated = Math.max(0, Long.valueOf(Math.round((double)updated - (double)updated * ((double)ultimateWise.getLevel() / 10.0))).intValue());
        }
        if (cost == -1) {
            updated = manaPool;
        }
        if (cost == -2) {
            updated = manaPool / 2;
        }
        return updated;
    }

    public static int getSpecItemIndex(Player player, SMaterial type) {
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getSize(); ++i) {
            SItem item = SItem.find(inventory.getItem(i));
            if (item == null || item.getType() != type) continue;
            return i;
        }
        return -1;
    }

    public static void addBoostStatistics(PlayerStatistics statistics, int slot, PlayerBoostStatistics boostStatistics) {
        if (boostStatistics == null) {
            return;
        }
        DoublePlayerStatistic health = statistics.getMaxHealth();
        DoublePlayerStatistic defense = statistics.getDefense();
        DoublePlayerStatistic strength = statistics.getStrength();
        DoublePlayerStatistic intelligence = statistics.getIntelligence();
        DoublePlayerStatistic ferocity = statistics.getFerocity();
        DoublePlayerStatistic speed = statistics.getSpeed();
        DoublePlayerStatistic critChance = statistics.getCritChance();
        DoublePlayerStatistic critDamage = statistics.getCritDamage();
        DoublePlayerStatistic atkSpeed = statistics.getAttackSpeed();
        DoublePlayerStatistic magicFind = statistics.getMagicFind();
        health.add(slot, boostStatistics.getBaseHealth());
        defense.add(slot, boostStatistics.getBaseDefense());
        strength.add(slot, boostStatistics.getBaseStrength());
        speed.add(slot, boostStatistics.getBaseSpeed());
        intelligence.add(slot, boostStatistics.getBaseIntelligence());
        critDamage.add(slot, boostStatistics.getBaseCritDamage());
        critChance.add(slot, boostStatistics.getBaseCritChance());
        magicFind.add(slot, boostStatistics.getBaseMagicFind());
        ferocity.add(slot, boostStatistics.getBaseFerocity());
        atkSpeed.add(slot, boostStatistics.getBaseAttackSpeed());
    }

    public static boolean isAutoSlayer(Player p) {
        boolean returnval = AUTO_SLAYER.containsKey(p.getUniqueId()) && AUTO_SLAYER.get(p.getUniqueId()).booleanValue();
        return returnval;
    }

    public static boolean isSBAToggle(Player pl) {
        UUID p = pl.getUniqueId();
        boolean returnval = Repeater.SBA_MAP.containsKey(p) && Repeater.SBA_MAP.get(p).booleanValue();
        return returnval;
    }

    public static long getCookieDurationTicks(Player p) {
        if (COOKIE_DURATION_CACHE.containsKey(p.getUniqueId())) {
            return COOKIE_DURATION_CACHE.get(p.getUniqueId());
        }
        COOKIE_DURATION_CACHE.put(p.getUniqueId(), 0L);
        return 0L;
    }

    public static void setCookieDurationTicks(Player p, long ticks) {
        COOKIE_DURATION_CACHE.put(p.getUniqueId(), ticks);
    }

    public static String getCookieDurationDisplay(Player p) {
        if (PlayerUtils.getCookieDurationTicks(p) > 0L) {
            return SUtil.getFormattedTimeToDay(PlayerUtils.getCookieDurationTicks(p));
        }
        return Sputnik.trans("&7Not active! Obtain booster cookies from the") + "\n" + Sputnik.trans("&7community shop in the hub.");
    }

    public static String getCookieDurationDisplayGUI(Player p) {
        if (PlayerUtils.getCookieDurationTicks(p) > 0L) {
            return ChatColor.GREEN + SUtil.getFormattedTimeToDay(PlayerUtils.getCookieDurationTicks(p));
        }
        return Sputnik.trans("&cNot active!");
    }

    public static void subtractDurationCookie(Player p, long sub) {
        if (PlayerUtils.getCookieDurationTicks(p) > 0L) {
            PlayerUtils.setCookieDurationTicks(p, PlayerUtils.getCookieDurationTicks(p) - sub);
        }
        if (PlayerUtils.getCookieDurationTicks(p) <= 0L) {
            PlayerUtils.wipeCookieStatsBuff(p);
        } else {
            PlayerUtils.loadCookieStatsBuff(p);
        }
    }

    public static boolean cookieBuffActive(Player p) {
        return PlayerUtils.getCookieDurationTicks(p) > 0L;
    }

    public static void loadCookieStatsBuff(Player player) {
        User user = User.getUser(player.getUniqueId());
        PlayerStatistics statistics = STATISTICS_CACHE.get(user.getUuid());
        statistics.zeroAll(151);
        statistics.getFerocity().set(151, 35.0);
        statistics.getDefense().set(151, 200.0);
        statistics.getCritDamage().set(151, 0.25);
        statistics.getIntelligence().set(151, 2000.0);
        statistics.getMagicFind().set(151, 0.3);
        statistics.getStrength().set(151, 100.0);
    }

    public static void wipeCookieStatsBuff(Player player) {
        User user = User.getUser(player.getUniqueId());
        PlayerStatistics statistics = STATISTICS_CACHE.get(user.getUuid());
        statistics.zeroAll(151);
    }

    public static void aBs(final Player p) {
        new BukkitRunnable(){
            final float cout;
            {
                this.cout = p.getLocation().getYaw();
            }

            public void run() {
                if (!p.isOnline()) {
                    this.cancel();
                    return;
                }
                Location loc = p.getLocation();
                loc.setYaw(this.cout);
                loc.setPitch(0.0f);
                loc.add(loc.getDirection().normalize().multiply(0.6));
                p.getWorld().spigot().playEffect(loc.clone().add(0.0, 2.2, 0.0), Effect.FLAME, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
    }

    public static class Debugmsg {
        public static boolean debugmsg;
    }

    public interface DamageResult {
        double getFinalDamage();

        boolean didCritDamage();
    }
}

