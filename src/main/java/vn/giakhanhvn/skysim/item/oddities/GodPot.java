/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.item.oddities;

import java.util.ArrayList;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SkullStatistics;
import vn.giakhanhvn.skysim.potion.PotionEffect;
import vn.giakhanhvn.skysim.potion.PotionEffectType;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class GodPot
implements SkullStatistics,
MaterialFunction,
Ability {
    private boolean bool = true;

    @Override
    public String getURL() {
        return "60226d4c1d30fbebecae939da900603e4cd0fed8592a1bb3e11f9ac92391a45a";
    }

    @Override
    public String getDisplayName() {
        return "God Potion";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.SPECIAL;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public String getAbilityName() {
        return "Drink!";
    }

    @Override
    public String getAbilityDescription() {
        return Sputnik.trans("&7Consume this potion give the player &a24 hours &7of positive effects!");
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 0;
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
    public boolean isStackable() {
        return false;
    }

    @Override
    public void onAbilityUse(final Player player, SItem sItem) {
        SUtil.delay(() -> {
            this.bool = false;
        }, 35L);
        ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
        effects.add(new PotionEffect(PotionEffectType.FEROCITY, 3, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.ARCHERY, 4, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.CRITICAL, 6, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.STRENGTH, 8, 1728000L));
        if (!player.getWorld().getName().contains("arena")) {
            effects.add(new PotionEffect(PotionEffectType.JUMP_BOOST, 4, 1728000L));
            effects.add(new PotionEffect(PotionEffectType.RABBIT, 6, 1728000L));
        }
        effects.add(new PotionEffect(PotionEffectType.RESISTANCE, 8, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.SPEED, 8, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.HASTE, 4, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.SPIRIT, 4, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.TRUE_RESISTANCE, 4, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.NIGHT_VISION, 1, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.WATER_BREATH, 1, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.STAMINA, 4, 1728000L));
        effects.add(new PotionEffect(PotionEffectType.MAGIC_FIND, 5, 1728000L));
        User user = User.getUser(player.getUniqueId());
        new BukkitRunnable(){

            public void run() {
                if (!GodPot.this.bool) {
                    player.getWorld().playEffect(player.getLocation().add(0.0, 0.5, 0.0), Effect.EXPLOSION_HUGE, 0);
                    player.getWorld().playEffect(player.getLocation().add(0.0, 0.5, 0.0), Effect.EXPLOSION_HUGE, 0);
                    player.sendMessage(Sputnik.trans("&a&lSIP! &eThe &cGod Potion &egrants you powers for &924 hours&e!"));
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 100; ++i) {
                    double r = SUtil.random(0.0, 1.0);
                    player.getWorld().playEffect(player.getLocation().add(0.0, r, 0.0), Effect.POTION_SWIRL, 0);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        for (PotionEffect effect : effects) {
            user.removePotionEffect(effect.getType());
            PlayerUtils.updatePotionEffects(user, PlayerUtils.STATISTICS_CACHE.get(user.getUuid()));
            if (effect.getType().getOnDrink() != null) {
                effect.getType().getOnDrink().accept(effect, player);
            }
            user.addPotionEffect(effect);
        }
        player.setItemInHand(null);
    }
}

