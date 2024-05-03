/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  org.bukkit.Location
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.event.entity.EntityShootBowEvent
 *  org.bukkit.projectiles.ProjectileSource
 */
package vn.giakhanhvn.skysim.item.bow;

import com.google.common.util.concurrent.AtomicDouble;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.projectiles.ProjectileSource;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.AbilityActivation;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.item.bow.BowFunction;

public class HurricaneBow
implements ToolStatistics,
BowFunction,
Ability {
    @Override
    public String getAbilityName() {
        return "Tempest";
    }

    @Override
    public String getAbilityDescription() {
        return "The more kills you get using this bow the more powerful it becomes! Reach 250 kills to unlock its full potential.";
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
    public AbilityActivation getAbilityActivation() {
        return AbilityActivation.NO_ACTIVATION;
    }

    @Override
    public String getDisplayName() {
        return "Hurricane Bow";
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
        return true;
    }

    @Override
    public void onBowShoot(SItem bow, EntityShootBowEvent e) {
        Location l;
        Player shooter = (Player)e.getEntity();
        int kills = bow.getDataInt("kills");
        Location location = shooter.getEyeLocation().add(shooter.getEyeLocation().getDirection().toLocation(shooter.getWorld()));
        float speed = e.getForce() * 3.0f;
        if (kills >= 20) {
            l = location.clone();
            l.setYaw(location.getYaw() - 15.0f);
            shooter.getWorld().spawnArrow(l, l.getDirection(), speed, 1.0f).setShooter((ProjectileSource)shooter);
        }
        if (kills >= 50) {
            l = location.clone();
            l.setYaw(location.getYaw() + 15.0f);
            shooter.getWorld().spawnArrow(l, l.getDirection(), speed, 1.0f).setShooter((ProjectileSource)shooter);
        }
        if (kills >= 100) {
            l = location.clone();
            l.setYaw(location.getYaw() - 30.0f);
            shooter.getWorld().spawnArrow(l, l.getDirection(), speed, 1.0f).setShooter((ProjectileSource)shooter);
        }
        if (kills >= 250) {
            l = location.clone();
            l.setYaw(location.getYaw() + 30.0f);
            shooter.getWorld().spawnArrow(l, l.getDirection(), speed, 1.0f).setShooter((ProjectileSource)shooter);
        }
    }

    @Override
    public void onBowHit(Entity hit, Player shooter, Arrow arrow, SItem weapon, AtomicDouble finalDamage) {
        if (!(hit instanceof LivingEntity)) {
            return;
        }
        if (hit instanceof Villager) {
            return;
        }
        if (((LivingEntity)hit).getHealth() - finalDamage.get() <= 0.0) {
            weapon.setKills(weapon.getDataInt("kills") + 1);
        }
    }
}

