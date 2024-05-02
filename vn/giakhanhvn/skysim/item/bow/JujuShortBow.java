/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.entity.EntityShootBowEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.projectiles.ProjectileSource
 */
package vn.giakhanhvn.skysim.item.bow;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.projectiles.ProjectileSource;
import vn.giakhanhvn.skysim.enchantment.Enchantment;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.item.bow.BowFunction;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.util.InventoryUpdate;
import vn.giakhanhvn.skysim.util.SLog;
import vn.giakhanhvn.skysim.util.SUtil;

public class JujuShortBow
implements ToolStatistics,
BowFunction {
    public static final Map<UUID, Boolean> USABLE_JUJU = new HashMap<UUID, Boolean>();

    @Override
    public String getDisplayName() {
        return "Juju Shortbow";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
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
        return 120;
    }

    @Override
    public double getBaseStrength() {
        return 50.0;
    }

    @Override
    public boolean displayKills() {
        return false;
    }

    @Override
    public void onInteraction(PlayerInteractEvent e) {
        SItem sItem = SItem.find(e.getPlayer().getItemInHand());
        Enchantment aiming = sItem.getEnchantment(EnchantmentType.AIMING);
        Player shooter = e.getPlayer();
        if (shooter.getPlayer().getInventory().contains(Material.ARROW, 1) || shooter.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                shooter.updateInventory();
                if (USABLE_JUJU.containsKey(shooter.getUniqueId()) && !USABLE_JUJU.get(shooter.getUniqueId()).booleanValue()) {
                    return;
                }
                if (shooter.getGameMode() != GameMode.CREATIVE) {
                    InventoryUpdate.removeInventoryItems(shooter.getInventory(), Material.ARROW, 1);
                }
                shooter.playSound(shooter.getLocation(), Sound.SHOOT_ARROW, 1.0f, 1.0f);
                Location location = shooter.getEyeLocation().add(shooter.getEyeLocation().getDirection().toLocation(shooter.getWorld()));
                Location l = location.clone();
                l.setYaw(location.getYaw());
                Arrow a = shooter.getWorld().spawnArrow(l, l.getDirection(), 2.1f, 1.5f);
                a.setShooter((ProjectileSource)shooter);
                USABLE_JUJU.put(shooter.getUniqueId(), false);
                PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(shooter.getUniqueId());
                double atkSpeed = Math.min(100L, Math.round(statistics.getAttackSpeed().addAll()));
                SUtil.delay(() -> USABLE_JUJU.put(shooter.getUniqueId(), true), (long)(16.0 / (1.0 + atkSpeed / 100.0)));
            } else if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                shooter.updateInventory();
                if (USABLE_JUJU.containsKey(shooter.getUniqueId()) && !USABLE_JUJU.get(shooter.getUniqueId()).booleanValue()) {
                    return;
                }
                if (shooter.getGameMode() != GameMode.CREATIVE) {
                    InventoryUpdate.removeInventoryItems(shooter.getInventory(), Material.ARROW, 1);
                }
                shooter.playSound(shooter.getLocation(), Sound.SHOOT_ARROW, 1.0f, 1.0f);
                Location location = shooter.getEyeLocation().add(shooter.getEyeLocation().getDirection().toLocation(shooter.getWorld()));
                Location l = location.clone();
                l.setYaw(location.getYaw());
                Arrow a2 = shooter.getWorld().spawnArrow(l, l.getDirection(), 2.2f, 1.6f);
                a2.setShooter((ProjectileSource)shooter);
                USABLE_JUJU.put(shooter.getUniqueId(), false);
                PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(shooter.getUniqueId());
                double atkSpeed = Math.min(100L, Math.round(statistics.getAttackSpeed().addAll()));
                SUtil.delay(() -> USABLE_JUJU.put(shooter.getUniqueId(), true), (long)(8.0 / (1.0 + atkSpeed / 100.0)));
            } else {
                SLog.severe("[JUJU-SHORTBOW] " + shooter.getUniqueId() + " <- Error Occurred on this user. Something messed up bruh");
            }
        }
    }

    @Override
    public void onBowShoot(SItem bow, EntityShootBowEvent e) {
        Player player = (Player)e.getEntity();
        e.setCancelled(true);
        player.updateInventory();
    }
}

