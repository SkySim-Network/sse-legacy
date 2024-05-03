/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Sound
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.EnderDragon
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.item.weapon;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class AspectOfTheDragons
implements ToolStatistics,
MaterialFunction,
Ability {
    @Override
    public int getBaseDamage() {
        return 225;
    }

    @Override
    public double getBaseStrength() {
        return 100.0;
    }

    @Override
    public String getDisplayName() {
        return "Aspect of the Dragons";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
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
    public String getLore() {
        return null;
    }

    @Override
    public String getAbilityName() {
        return "Dragon Rage";
    }

    @Override
    public String getAbilityDescription() {
        return "All Monsters in front of you take " + ChatColor.RED + "5,000 " + ChatColor.GRAY + "damage. Hit monsters take large knockback.";
    }

    @Override
    public void onAbilityUse(Player player, SItem sItem) {
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
        int manaPool = SUtil.blackMagic(100.0 + statistics.getIntelligence().addAll());
        player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 5.0f, 5.0f);
        for (Entity entity : player.getWorld().getNearbyEntities(player.getLocation().add(player.getLocation().getDirection().multiply(3.0)), 3.0, 3.0, 3.0)) {
            if (!(entity instanceof LivingEntity) || entity instanceof Player || entity instanceof EnderDragon || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand || entity.hasMetadata("GiantSword") || entity.hasMetadata("NoAffect")) continue;
            User user = User.getUser(player.getUniqueId());
            entity.setVelocity(player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize().multiply(-1.0).multiply(8.0));
            int baseMagicDmg = 10000;
            baseMagicDmg = (int)((double)baseMagicDmg + (double)baseMagicDmg * (statistics.getAbilityDamage().addAll() / 100.0));
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
            double baseDamage = (double)baseMagicDmg * ((double)(manaPool / 100) * 0.1 + 1.0);
            final ArmorStand stands = (ArmorStand)new SEntity(entity.getLocation().clone().add(SUtil.random(-1.5, 1.5), 1.0, SUtil.random(-1.5, 1.5)), SEntityType.UNCOLLIDABLE_ARMOR_STAND, new Object[0]).getEntity();
            stands.setCustomName("" + ChatColor.GRAY + (int)baseDamage);
            stands.setCustomNameVisible(true);
            stands.setGravity(false);
            stands.setVisible(false);
            new BukkitRunnable(){

                public void run() {
                    stands.remove();
                    this.cancel();
                }
            }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 30L);
            user.damageEntity((Damageable)((LivingEntity)entity), baseDamage);
        }
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 100;
    }

    @Override
    public int getManaCost() {
        return 100;
    }
}

