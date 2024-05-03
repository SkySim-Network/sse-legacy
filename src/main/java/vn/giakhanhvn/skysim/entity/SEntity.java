/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.GenericAttributes
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.Ageable
 *  org.bukkit.entity.Creature
 *  org.bukkit.entity.Enderman
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Skeleton
 *  org.bukkit.entity.Skeleton$SkeletonType
 *  org.bukkit.entity.Slime
 *  org.bukkit.entity.Wolf
 *  org.bukkit.entity.Zombie
 *  org.bukkit.inventory.EntityEquipment
 *  org.bukkit.material.MaterialData
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package vn.giakhanhvn.skysim.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.Ageable;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.EntityStatistics;
import vn.giakhanhvn.skysim.entity.JockeyStatistics;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.SkeletonStatistics;
import vn.giakhanhvn.skysim.entity.SlimeStatistics;
import vn.giakhanhvn.skysim.entity.ZombieStatistics;
import vn.giakhanhvn.skysim.entity.end.EndermanStatistics;
import vn.giakhanhvn.skysim.entity.nms.SNMSEntity;
import vn.giakhanhvn.skysim.entity.wolf.WolfStatistics;
import vn.giakhanhvn.skysim.util.SUtil;

public class SEntity {
    private static final SkySimEngine plugin = SkySimEngine.getPlugin();
    public static final Map<Entity, Boolean> isStarred = new HashMap<Entity, Boolean>();
    private final SEntityType specType;
    private final LivingEntity entity;
    private final Map<UUID, Double> damageDealt;
    private BukkitTask task;
    private BukkitTask ticker;
    private Object genericInstance;
    private EntityStatistics statistics;
    private EntityFunction function;

    public SEntity(Location location, SEntityType specType, Object ... params) {
        Object instance;
        this.specType = specType;
        this.genericInstance = instance = specType.instance(params);
        final EntityFunction function = (EntityFunction)instance;
        final EntityStatistics statistics = (EntityStatistics)instance;
        this.function = function;
        this.statistics = statistics;
        this.entity = instance instanceof SNMSEntity ? ((SNMSEntity)instance).spawn(location) : (LivingEntity)location.getWorld().spawnEntity(location, specType.getCraftType());
        this.damageDealt = new HashMap<UUID, Double>();
        if (statistics.getMovementSpeed() != -1.0) {
            ((CraftLivingEntity)this.entity).getHandle().getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(statistics.getMovementSpeed());
        }
        Location move = this.entity.getLocation().clone();
        move.setYaw(location.getYaw());
        this.entity.teleport(move);
        SEntityEquipment equipment = statistics.getEntityEquipment();
        EntityEquipment ee = this.entity.getEquipment();
        if (equipment != null) {
            ee.setHelmet(equipment.getHelmet());
            ee.setChestplate(equipment.getChestplate());
            ee.setLeggings(equipment.getLeggings());
            ee.setBoots(equipment.getBoots());
            ee.setItemInHand(equipment.getItemInHand());
        }
        this.entity.setNoDamageTicks(15);
        this.entity.setMaximumNoDamageTicks(15);
        this.entity.setRemoveWhenFarAway(statistics.removeWhenFarAway());
        function.onSpawn(this.entity, this);
        if (function.tick(this.entity)) {
            this.ticker = new BukkitRunnable(){

                public void run() {
                    if (SEntity.this.entity.isDead()) {
                        this.cancel();
                    }
                    function.tick(SEntity.this.entity);
                }
            }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        }
        if (statistics instanceof SlimeStatistics && this.entity instanceof Slime) {
            ((Slime)this.entity).setSize(((SlimeStatistics)statistics).getSize());
        }
        if (statistics instanceof EndermanStatistics && this.entity instanceof Enderman) {
            ((Enderman)this.entity).setCarriedMaterial(((EndermanStatistics)statistics).getCarriedMaterial() != null ? ((EndermanStatistics)statistics).getCarriedMaterial() : new MaterialData(Material.AIR));
        }
        if (this.entity instanceof org.bukkit.entity.Ageable) {
            if (this.genericInstance instanceof Ageable && ((Ageable)this.genericInstance).isBaby()) {
                ((org.bukkit.entity.Ageable)this.entity).setBaby();
            } else {
                ((org.bukkit.entity.Ageable)this.entity).setAdult();
            }
        }
        if (statistics instanceof ZombieStatistics && this.entity instanceof Zombie) {
            ((Zombie)this.entity).setVillager(((ZombieStatistics)statistics).isVillager());
        }
        if (statistics instanceof JockeyStatistics) {
            this.entity.setPassenger((Entity)new SEntity(location, ((JockeyStatistics)statistics).getPassenger(), new Object[0]).getEntity());
        }
        if (statistics instanceof WolfStatistics && this.entity instanceof Wolf) {
            ((Wolf)this.entity).setAngry(((WolfStatistics)statistics).isAngry());
        }
        if (statistics instanceof SkeletonStatistics && this.entity instanceof Skeleton) {
            ((Skeleton)this.entity).setSkeletonType(((SkeletonStatistics)statistics).isWither() ? Skeleton.SkeletonType.WITHER : Skeleton.SkeletonType.NORMAL);
        }
        if (this.entity instanceof org.bukkit.entity.Ageable) {
            ((org.bukkit.entity.Ageable)this.entity).setAdult();
        }
        new BukkitRunnable(){

            public void run() {
                if (!statistics.isVisible()) {
                    ((CraftLivingEntity)SEntity.this.entity).getHandle().setInvisible(true);
                }
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 2L);
        int rand = 0;
        if (this.entity.hasMetadata("WATCHER_E")) {
            rand = SUtil.random(7, 12);
            rand *= 1000000;
            ((CraftZombie)this.entity).setBaby(false);
        }
        function.onSpawnNameTag(this.entity, this, specType, params);
        this.entity.setMaxHealth(statistics.getEntityMaxHealth() + (double)rand);
        this.entity.setHealth(this.entity.getMaxHealth());
        this.entity.setMetadata("specEntityObject", (MetadataValue)new FixedMetadataValue((Plugin)plugin, (Object)this));
        new BukkitRunnable(){

            public void run() {
                if (SEntity.this.entity.hasMetadata("upsidedown")) {
                    SEntity.this.entity.setCustomName("Dinnerbone");
                    SEntity.this.entity.setCustomNameVisible(false);
                }
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 2L);
    }

    public SEntity(Entity e, SEntityType type, Object ... params) {
        this(e.getLocation(), type, params);
    }

    public void addDamageFor(Player player, double damage) {
        UUID uuid = player.getUniqueId();
        if (this.damageDealt.containsKey(uuid)) {
            damage += this.damageDealt.get(uuid).doubleValue();
        }
        this.damageDealt.remove(uuid);
        this.damageDealt.put(uuid, damage);
    }

    public void setVisible(final boolean visible) {
        new BukkitRunnable(){

            public void run() {
                ((CraftLivingEntity)SEntity.this.entity).getHandle().setInvisible(!visible);
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 2L);
    }

    public void setTarget(LivingEntity target) {
        if (!(this.entity instanceof Creature)) {
            return;
        }
        ((Creature)this.entity).setTarget(target);
    }

    public void remove() {
        if (this.ticker != null) {
            this.ticker.cancel();
        }
        if (this.task != null) {
            this.task.cancel();
        }
        this.entity.remove();
    }

    public void setHealth(int health) {
    }

    public void setDefense(double percent) {
    }

    public void setStarred(boolean starred) {
        isStarred.put((Entity)this.entity, starred);
    }

    public void setMetadata(Object metadata) {
        this.entity.setMetadata("specEntityObject", (MetadataValue)new FixedMetadataValue((Plugin)plugin, (Object)this));
    }

    public void setDamage(int damage) {
    }

    public static SEntity findSEntity(Entity entity) {
        if (!entity.hasMetadata("specEntityObject") || entity.getMetadata("specEntityObject").size() == 0 || !(((MetadataValue)entity.getMetadata("specEntityObject").get(0)).value() instanceof SEntity)) {
            return null;
        }
        return (SEntity)((MetadataValue)entity.getMetadata("specEntityObject").get(0)).value();
    }

    public SEntityType getSpecType() {
        return this.specType;
    }

    public LivingEntity getEntity() {
        return this.entity;
    }

    public Map<UUID, Double> getDamageDealt() {
        return this.damageDealt;
    }

    public BukkitTask getTask() {
        return this.task;
    }

    public BukkitTask getTicker() {
        return this.ticker;
    }

    public Object getGenericInstance() {
        return this.genericInstance;
    }

    public EntityStatistics getStatistics() {
        return this.statistics;
    }

    public EntityFunction getFunction() {
        return this.function;
    }
}

