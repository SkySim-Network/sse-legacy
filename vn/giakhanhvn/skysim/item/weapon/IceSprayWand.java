/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.slikey.effectlib.effect.ConeEffect
 *  de.slikey.effectlib.util.ParticleEffect
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.EnderDragon
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.EulerAngle
 */
package vn.giakhanhvn.skysim.item.weapon;

import de.slikey.effectlib.effect.ConeEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class IceSprayWand
implements ToolStatistics,
MaterialFunction,
Ability {
    @Override
    public int getBaseDamage() {
        return 140;
    }

    @Override
    public double getBaseIntelligence() {
        return 345.0;
    }

    @Override
    public String getDisplayName() {
        return "Ice Spray Wand";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
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
        return "Ice Spray";
    }

    @Override
    public String getAbilityDescription() {
        return Sputnik.trans("&7Produces a cone of ice in front of the caster that deals &c25,000 &7damage to mobs and freezes them in place for &e5 &7seconds! Frozen mobs take &c10% &7incresed damage!");
    }

    @Override
    public void onAbilityUse(Player player, SItem sItem) {
        Location loc = player.getEyeLocation();
        ConeEffect Effect2 = new ConeEffect(SkySimEngine.effectManager);
        Effect2.setLocation(loc.clone().add(loc.getDirection().normalize().multiply(-0.25)).add(0.0, -0.1, 0.0));
        Effect2.particle = ParticleEffect.SNOW_SHOVEL;
        Effect2.color = Color.WHITE;
        Effect2.angularVelocity = 0.39269908169872414;
        Effect2.lengthGrow = 0.025f;
        Effect2.particles = 30;
        Effect2.period = 3;
        Effect2.iterations = 5;
        Effect2.start();
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
        int manaPool = SUtil.blackMagic(100.0 + statistics.getIntelligence().addAll());
        player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 5.0f, 5.0f);
        for (final Entity entity : player.getWorld().getNearbyEntities(player.getLocation().add(player.getLocation().getDirection().multiply(3.0)), 3.0, 3.0, 3.0)) {
            if (!(entity instanceof LivingEntity) || entity instanceof Player || entity instanceof EnderDragon || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand || entity.hasMetadata("GiantSword") || entity.hasMetadata("NoAffect") || entity.isDead()) continue;
            User user = User.getUser(player.getUniqueId());
            double baseDamage = Sputnik.calculateMagicDamage(entity, player, 32000, 0.1);
            user.damageEntityIgnoreShield((Damageable)((LivingEntity)entity), baseDamage);
            entity.setMetadata("frozen", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
            PlayerListener.spawnDamageInd(entity, baseDamage, false);
            double b = 0.0;
            for (int i = 0; i < 2; ++i) {
                final int d = i;
                if (i == 0) {
                    b = 0.2;
                } else if (i == 1) {
                    b = 0.4;
                } else if (i == 2) {
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

    @Override
    public int getAbilityCooldownTicks() {
        return 100;
    }

    @Override
    public int getManaCost() {
        return 50;
    }
}

