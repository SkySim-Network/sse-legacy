/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.entity.den;

import java.util.Collections;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.den.BaseSpider;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.SUtil;

public class MutantTarantula
extends BaseSpider {
    @Override
    public String getEntityName() {
        return ChatColor.RED + "Mutant Tarantula";
    }

    @Override
    public double getEntityMaxHealth() {
        return 576000.0;
    }

    @Override
    public double getDamageDealt() {
        return 5000.0;
    }

    @Override
    public double getXPDropped() {
        return 500.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (Entity e : entity.getNearbyEntities(1.0, 1.0, 1.0)) {
                    if (!(e instanceof Player)) {
                        return;
                    }
                    ((Player)e).damage(MutantTarantula.this.getDamageDealt() * 0.5, (Entity)entity);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 20L, 20L);
    }

    @Override
    public List<EntityDrop> drops() {
        return Collections.singletonList(new EntityDrop(SUtil.setStackAmount(SItem.of(SMaterial.TARANTULA_WEB).getStack(), 5), EntityDropType.GUARANTEED, 1.0));
    }
}

