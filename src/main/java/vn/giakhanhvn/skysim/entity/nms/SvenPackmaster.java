/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityWolf
 *  net.minecraft.server.v1_8_R3.GenericAttributes
 *  net.minecraft.server.v1_8_R3.World
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Wolf
 *  org.bukkit.event.entity.CreatureSpawnEvent$SpawnReason
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.entity.nms;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityWolf;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.nms.SNMSEntity;
import vn.giakhanhvn.skysim.entity.nms.SlayerBoss;
import vn.giakhanhvn.skysim.entity.nms.TieredValue;
import vn.giakhanhvn.skysim.entity.wolf.WolfStatistics;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SvenPackmaster
extends EntityWolf
implements SNMSEntity,
EntityFunction,
WolfStatistics,
SlayerBoss {
    private static final TieredValue<Double> MAX_HEALTH_VALUES = new TieredValue<Double>(2000.0, 40000.0, 750000.0, 2000000.0);
    private static final TieredValue<Double> DAMAGE_VALUES = new TieredValue<Double>(60.0, 200.0, 450.0, 1100.0);
    private static final TieredValue<Double> TRUE_DAMAGE_VALUES = new TieredValue<Double>(0.0, 10.0, 50.0, 200.0);
    private static final TieredValue<Double> SPEED_VALUES = new TieredValue<Double>(0.35, 0.4, 0.45, 0.55);
    private final int tier;
    private final long end;
    private SEntity hologram;
    private SEntity hologram_name;
    private final UUID spawnerUUID;
    private long lastDamage;
    private boolean pupsSpawned;
    private boolean isActive;
    private final List<SEntity> pups;

    public SvenPackmaster(Integer tier, UUID spawnerUUID) {
        super((World)((CraftWorld)Bukkit.getPlayer((UUID)spawnerUUID).getWorld()).getHandle());
        this.tier = tier;
        this.end = System.currentTimeMillis() + 240000L;
        this.spawnerUUID = spawnerUUID;
        this.lastDamage = System.currentTimeMillis() - 1L;
        this.pupsSpawned = false;
        this.isActive = false;
        this.pups = new ArrayList<SEntity>();
    }

    public SvenPackmaster(World world) {
        super(world);
        this.tier = 1;
        this.end = System.currentTimeMillis() + 240000L;
        this.spawnerUUID = UUID.randomUUID();
        this.lastDamage = System.currentTimeMillis() - 1L;
        this.pupsSpawned = false;
        this.isActive = false;
        this.pups = new ArrayList<SEntity>();
    }

    public void t_() {
        super.t_();
        Player player = Bukkit.getPlayer((UUID)this.spawnerUUID);
        if (player == null) {
            return;
        }
        if (System.currentTimeMillis() > this.end) {
            User.getUser(player.getUniqueId()).failSlayerQuest();
            ((Wolf)this.bukkitEntity).remove();
            this.hologram.remove();
            return;
        }
        if (((Wolf)this.bukkitEntity).getWorld() == player.getWorld() && this.getBukkitEntity().getLocation().distance(player.getLocation()) >= 20.0 && SUtil.random(0, 10) == 0) {
            this.getBukkitEntity().teleport(player.getLocation());
        }
        if (System.currentTimeMillis() > this.lastDamage && (this.pups.isEmpty() || !this.pupsSpawned)) {
            this.setHealth(Math.min(this.getMaxHealth(), this.getHealth() + this.getHealth() * 5.0E-4f));
        }
        if (this.pups.isEmpty()) {
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(this.getMovementSpeed());
        } else {
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3);
        }
        this.getBukkitEntity().getWorld().spigot().playEffect(this.getBukkitEntity().getLocation(), Effect.FLAME, 0, 1, (float)SUtil.random(-1.0, 1.0), 0.0f, (float)SUtil.random(-1.0, 1.0), 0.0f, 1, 100);
        this.getBukkitEntity().getWorld().spigot().playEffect(this.getBukkitEntity().getLocation(), Effect.FIREWORKS_SPARK, 0, 1, (float)SUtil.random(-1.0, 1.0), 0.0f, (float)SUtil.random(-1.0, 1.0), 0.0f, 1, 100);
        ((Wolf)this.getBukkitEntity()).setTarget((LivingEntity)player);
        Entity entity = this.getBukkitEntity().getHandle();
        double height = entity.getBoundingBox().e - entity.getBoundingBox().b;
        this.hologram_name.getEntity().teleport(this.getBukkitEntity().getLocation().clone().add(0.0, height, 0.0));
        this.hologram_name.getEntity().setCustomName(Sputnik.trans(Sputnik.entityNameTag((LivingEntity)this.getBukkitEntity(), Sputnik.buildcustomString(this.getEntityName(), 0, true))));
        this.hologram.getEntity().teleport(this.getBukkitEntity().getLocation().clone().add(0.0, 1.1, 0.0));
        if (!this.isActive) {
            this.hologram.getEntity().setCustomName(ChatColor.RED + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000));
        } else {
            this.hologram.getEntity().setCustomName(Sputnik.trans("&bCalling the pups! &c" + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000)));
        }
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        entity.setMetadata("BOSS_OWNER_" + Bukkit.getPlayer((UUID)this.getSpawnerUUID()).getUniqueId().toString(), (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        Player player = Bukkit.getPlayer((UUID)this.spawnerUUID);
        if (player != null) {
            player.playSound(player.getLocation(), Sound.WOLF_HOWL, 1.0f, 5.0f);
        }
        this.hologram = new SEntity(entity.getLocation().add(0.0, 1.1, 0.0), SEntityType.UNCOLLIDABLE_ARMOR_STAND, new Object[0]);
        ((ArmorStand)this.hologram.getEntity()).setVisible(false);
        ((ArmorStand)this.hologram.getEntity()).setGravity(false);
        this.hologram.getEntity().setCustomNameVisible(true);
        entity.setMetadata("notDisplay", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        Entity e = this.getBukkitEntity().getHandle();
        double height = e.getBoundingBox().e - e.getBoundingBox().b;
        this.hologram_name = new SEntity(entity.getLocation().add(0.0, height, 0.0), SEntityType.UNCOLLIDABLE_ARMOR_STAND, new Object[0]);
        ((ArmorStand)this.hologram_name.getEntity()).setVisible(false);
        ((ArmorStand)this.hologram_name.getEntity()).setGravity(false);
        this.hologram_name.getEntity().setCustomNameVisible(true);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    SUtil.delay(() -> SvenPackmaster.this.hologram_name.remove(), 20L);
                    SvenPackmaster.this.hologram.remove();
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    @Override
    public String getEntityName() {
        return ChatColor.RED + "\u2620 " + ChatColor.WHITE + "Sven Packmaster";
    }

    @Override
    public double getEntityMaxHealth() {
        return MAX_HEALTH_VALUES.getByNumber(this.tier);
    }

    @Override
    public double getDamageDealt() {
        return DAMAGE_VALUES.getByNumber(this.tier);
    }

    @Override
    public double getMovementSpeed() {
        return SPEED_VALUES.getByNumber(this.tier);
    }

    @Override
    public void onDeath(SEntity sEntity, org.bukkit.entity.Entity killed, org.bukkit.entity.Entity damager) {
        SUtil.delay(() -> this.hologram_name.remove(), 20L);
        this.hologram.remove();
        for (SEntity pup : this.pups) {
            pup.getEntity().setHealth(0.0);
        }
    }

    @Override
    public void onAttack(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player)e.getEntity();
        User.getUser(player.getUniqueId()).damage(TRUE_DAMAGE_VALUES.getByNumber(this.tier), EntityDamageEvent.DamageCause.ENTITY_ATTACK, (org.bukkit.entity.Entity)this.getBukkitEntity());
        if (player.getUniqueId().equals(this.spawnerUUID)) {
            this.lastDamage = System.currentTimeMillis() + 15000L;
        }
    }

    @Override
    public LivingEntity spawn(Location location) {
        this.world = ((CraftWorld)location.getWorld()).getHandle();
        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.world.addEntity((Entity)this, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return (LivingEntity)this.getBukkitEntity();
    }

    @Override
    public void onDamage(SEntity sEntity, org.bukkit.entity.Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        if (e.getDamager() instanceof Arrow) {
            e.setCancelled(true);
            return;
        }
        Player player = Bukkit.getPlayer((UUID)this.spawnerUUID);
        if (player == null) {
            return;
        }
        if (this.tier >= 3 && sEntity.getEntity().getHealth() - damage.get() < this.getEntityMaxHealth() / 2.0 && !this.pupsSpawned) {
            this.isActive = true;
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.0);
            player.playSound(player.getLocation(), Sound.WOLF_HOWL, 1.0f, 1.0f);
            SUtil.delay(() -> this.hologram.getEntity().setCustomName(Sputnik.trans("&c" + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000))), 120L);
            SUtil.delay(() -> this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(this.getMovementSpeed()), 120L);
            SUtil.delay(() -> {
                this.isActive = false;
            }, 120L);
            this.pupsSpawned = true;
            for (int i = 0; i < 10; ++i) {
                SUtil.delay(() -> {
                    if (this.bukkitEntity.isDead()) {
                        return;
                    }
                    this.pups.add(new SEntity((org.bukkit.entity.Entity)sEntity.getEntity(), SEntityType.SVEN_PUP, this.getEntityMaxHealth() * 0.1, this.getDamageDealt() * 0.5, player, this));
                }, i * 20);
            }
        }
    }

    @Override
    public List<EntityDrop> drops() {
        ArrayList<EntityDrop> drops = new ArrayList<EntityDrop>();
        int teeth = SUtil.random(1, 3);
        if (this.tier == 2) {
            teeth = SUtil.random(9, 18);
        }
        if (this.tier == 3) {
            teeth = SUtil.random(30, 50);
        }
        if (this.tier == 4) {
            teeth = SUtil.random(50, 64);
        }
        drops.add(new EntityDrop(SUtil.setStackAmount(SItem.of(SMaterial.WOLF_TOOTH).getStack(), teeth), EntityDropType.GUARANTEED, 1.0));
        if (this.tier >= 2) {
            int hamsterWheel = 1;
            if (this.tier == 3) {
                hamsterWheel = SUtil.random(2, 4);
            }
            if (this.tier == 4) {
                hamsterWheel = SUtil.random(4, 5);
            }
            drops.add(new EntityDrop(SUtil.setStackAmount(SItem.of(SMaterial.HAMSTER_WHEEL).getStack(), hamsterWheel), EntityDropType.OCCASIONAL, 0.2));
            drops.add(new EntityDrop(SMaterial.SPIRIT_RUNE, EntityDropType.RARE, 0.05));
        }
        if (this.tier >= 3) {
            SItem critBook = SItem.of(SMaterial.ENCHANTED_BOOK);
            critBook.addEnchantment(EnchantmentType.CRITICAL, 6);
            drops.add(new EntityDrop(critBook.getStack(), EntityDropType.EXTRAORDINARILY_RARE, 0.005));
            drops.add(new EntityDrop(SMaterial.RED_CLAW_EGG, EntityDropType.CRAZY_RARE, this.tier == 3 ? 0.0025 : 0.002));
        }
        if (this.tier >= 4) {
            SItem chimBook = SItem.of(SMaterial.ENCHANTED_BOOK);
            chimBook.addEnchantment(EnchantmentType.CHIMERA, 1);
            drops.add(new EntityDrop(chimBook.getStack(), EntityDropType.CRAZY_RARE, 0.002));
            SItem chimBook2 = SItem.of(SMaterial.ENCHANTED_BOOK);
            chimBook2.addEnchantment(EnchantmentType.CHIMERA, 2);
            drops.add(new EntityDrop(chimBook2.getStack(), EntityDropType.INSANE_RARE, 0.0016666666666666668));
            drops.add(new EntityDrop(SMaterial.COUTURE_RUNE, EntityDropType.CRAZY_RARE, 0.00625));
            drops.add(new EntityDrop(SMaterial.GRIZZLY_BAIT, EntityDropType.CRAZY_RARE, 0.006666666666666667));
            drops.add(new EntityDrop(SMaterial.OVERFLUX_CAPACITOR, EntityDropType.CRAZY_RARE, 0.006666666666666667));
        }
        return drops;
    }

    @Override
    public double getXPDropped() {
        return 0.0;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public boolean isAngry() {
        return true;
    }

    @Override
    public UUID getSpawnerUUID() {
        return this.spawnerUUID;
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    public List<SEntity> getPups() {
        return this.pups;
    }
}

