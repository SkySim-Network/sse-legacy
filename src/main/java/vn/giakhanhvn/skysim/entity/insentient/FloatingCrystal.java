/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockState
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.insentient;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.nms.VelocityArmorStand;
import vn.giakhanhvn.skysim.util.SUtil;

public abstract class FloatingCrystal
extends VelocityArmorStand {
    @Override
    public void onSpawn(LivingEntity entity, SEntity sEntity) {
        final ArmorStand stand = (ArmorStand)entity;
        stand.setVisible(false);
        stand.setHelmet(SUtil.getSkull(this.getURL(), null));
        stand.setVelocity(new Vector(0.0, 0.1, 0.0));
        stand.setMetadata("specUnbreakableArmorStand", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        new BukkitRunnable(){

            public void run() {
                if (stand.isDead()) {
                    this.cancel();
                    return;
                }
                Vector velClone = stand.getVelocity().clone();
                stand.setVelocity(new Vector(0.0, velClone.getY() < 0.0 ? 0.1 : -0.1, 0.0));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 15L, 15L);
        new BukkitRunnable(){

            public void run() {
                if (stand.isDead()) {
                    this.cancel();
                    return;
                }
                Location location = stand.getLocation();
                location.setYaw(stand.getLocation().getYaw() + 15.0f);
                stand.teleport(location);
                stand.getWorld().spigot().playEffect(stand.getEyeLocation().clone().add(SUtil.random(-0.5, 0.5), 0.0, SUtil.random(-0.5, 0.5)), Effect.FIREWORKS_SPARK, 24, 1, 0.0f, 0.0f, 0.0f, 1.0f, 0, 64);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        new BukkitRunnable(){

            public void run() {
                if (stand.isDead()) {
                    this.cancel();
                    return;
                }
                List<Block> farmland = SUtil.getNearbyBlocks(stand.getEyeLocation(), 11, Material.SOIL);
                if (farmland.size() == 0) {
                    return;
                }
                ArrayList<Block> possible = new ArrayList<Block>();
                for (Block block : farmland) {
                    Block a = block.getLocation().clone().add(0.0, 1.0, 0.0).getBlock();
                    if (a.getType() != Material.AIR) continue;
                    possible.add(a);
                }
                if (possible.size() == 0) {
                    return;
                }
                Block above = (Block)possible.get(SUtil.random(0, possible.size() - 1));
                if (above == null) {
                    return;
                }
                above.setType(Material.CROPS);
                BlockState state = above.getState();
                state.setRawData((byte)7);
                state.update();
                Location blockLocation = above.getLocation();
                Location crystalLocation = stand.getEyeLocation();
                Vector vector = blockLocation.clone().add(0.5, 0.0, 0.5).toVector().subtract(crystalLocation.clone().toVector());
                double count = 25.0;
                for (int i = 1; i <= (int)count; ++i) {
                    stand.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), Effect.FIREWORKS_SPARK, 24, 1, 0.0f, 0.0f, 0.0f, 1.0f, 0, 64);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 20L, 20L);
    }

    @Override
    public double getXPDropped() {
        return 0.0;
    }

    protected abstract String getURL();
}

