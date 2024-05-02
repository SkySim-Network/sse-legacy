/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package vn.giakhanhvn.skysim.entity.dungeons;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.end.BaseEnderman;
import vn.giakhanhvn.skysim.util.EntityManager;

public class Fels
extends BaseEnderman {
    @Override
    public String getEntityName() {
        return "Fels";
    }

    @Override
    public double getEntityMaxHealth() {
        return 1.0E8;
    }

    @Override
    public double getDamageDealt() {
        return 1400000.0;
    }

    @Override
    public void onSpawn(LivingEntity entity, SEntity sEntity) {
        entity.setMetadata("upsidedown", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 60);
    }

    @Override
    public double getXPDropped() {
        return 320.0;
    }

    @Override
    public int mobLevel() {
        return 0;
    }
}

