/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.EntityZombie
 *  net.minecraft.server.v1_8_R3.GenericAttributes
 *  net.minecraft.server.v1_8_R3.World
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Color
 *  org.bukkit.Effect
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Zombie
 *  org.bukkit.event.entity.CreatureSpawnEvent$SpawnReason
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.nms;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.ZombieStatistics;
import vn.giakhanhvn.skysim.entity.nms.SNMSEntity;
import vn.giakhanhvn.skysim.entity.nms.SlayerBoss;
import vn.giakhanhvn.skysim.entity.nms.TieredValue;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class AtonedHorror
extends EntityZombie
implements SNMSEntity,
EntityFunction,
ZombieStatistics,
SlayerBoss {
    private static final TieredValue<Double> MAX_HEALTH_VALUES = new TieredValue<Double>(1.0E8, 1.0E8, 1.0E8, 1.0E8);
    private static final TieredValue<Double> DAMAGE_VALUES = new TieredValue<Double>(1500000.0, 1500000.0, 1500000.0, 1500000.0);
    private static final TieredValue<Double> SPEED_VALUES = new TieredValue<Double>(0.3, 0.3, 0.3, 0.3);
    private final int tier;
    private boolean enraged;
    private int circlus;
    private boolean boomboom;
    private boolean getloc;
    private boolean Cooldown;
    private Location flog;
    private final long end;
    private SEntity hologram;
    private SEntity hologram_name;
    private SEntity armorStand1;
    private final UUID spawnerUUID;

    public AtonedHorror(Integer tier, UUID spawnerUUID) {
        super((World)((CraftWorld)Bukkit.getPlayer((UUID)spawnerUUID).getWorld()).getHandle());
        this.tier = tier;
        this.boomboom = false;
        this.Cooldown = true;
        this.circlus = 1;
        this.enraged = false;
        this.end = System.currentTimeMillis() + 180000L;
        this.spawnerUUID = spawnerUUID;
    }

    public AtonedHorror(World world) {
        super(world);
        this.tier = 1;
        this.boomboom = false;
        this.enraged = false;
        this.circlus = 1;
        this.Cooldown = true;
        this.end = System.currentTimeMillis() + 180000L;
        this.spawnerUUID = UUID.randomUUID();
    }

    public void t_() {
        super.t_();
        Player player = Bukkit.getPlayer((UUID)this.spawnerUUID);
        if (player == null) {
            return;
        }
        if (((Zombie)this.bukkitEntity).getWorld() == player.getWorld() && this.getBukkitEntity().getLocation().distance(player.getLocation()) >= 35.0 && SUtil.random(0, 10) == 0) {
            if (this.boomboom) {
                return;
            }
            this.getBukkitEntity().teleport(player.getLocation());
        }
        if (System.currentTimeMillis() > this.end) {
            User.getUser(player.getUniqueId()).failSlayerQuest();
            ((Zombie)this.bukkitEntity).remove();
            this.hologram.remove();
            return;
        }
        Entity entity = this.getBukkitEntity().getHandle();
        double height = entity.getBoundingBox().e - entity.getBoundingBox().b;
        this.hologram_name.getEntity().teleport(this.getBukkitEntity().getLocation().clone().add(0.0, height, 0.0));
        this.hologram_name.getEntity().setCustomName(Sputnik.trans(Sputnik.entityNameTag((LivingEntity)this.getBukkitEntity(), Sputnik.buildcustomString(this.getEntityName(), 0, true))));
        this.hologram.getEntity().teleport(this.getBukkitEntity().getLocation().clone().add(0.0, 2.3, 0.0));
        if (!this.boomboom) {
            this.hologram.getEntity().setCustomName(ChatColor.RED + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000));
        }
        ((Zombie)this.bukkitEntity).setTarget((LivingEntity)player);
        this.getBukkitEntity().getWorld().spigot().playEffect(this.getBukkitEntity().getLocation(), Effect.FIREWORKS_SPARK, 0, 1, (float)SUtil.random(-1.0, 1.0), 1.0f, (float)SUtil.random(-1.0, 1.0), 0.0f, 1, 100);
        this.getBukkitEntity().getWorld().spigot().playEffect(this.getBukkitEntity().getLocation(), Effect.SPELL, 0, 1, (float)SUtil.random(-1.0, 1.0), 1.0f, (float)SUtil.random(-1.0, 1.0), 0.0f, 1, 100);
        if (this.boomboom) {
            this.getBukkitEntity().getWorld().spigot().playEffect(this.getBukkitEntity().getLocation(), Effect.VILLAGER_THUNDERCLOUD, 0, 1, (float)SUtil.random(0.0, 1.0), 1.0f, (float)SUtil.random(0.0, 1.0), 0.0f, 1, 100);
        }
        if (this.boomboom) {
            this.getBukkitEntity().teleport(this.flog);
            for (int i = 0; i < 50; ++i) {
                if (this.circlus < 3) {
                    return;
                }
                double angle = Math.PI * 2 * (double)i / 50.0;
                Location point = this.getBukkitEntity().getLocation().add(1.0, 0.0, 0.0).clone().add((double)this.circlus * Math.sin(angle), 0.0, (double)this.circlus * Math.cos(angle));
                point.getWorld().spigot().playEffect(point.clone().add(0.0, 1.0, 0.0), Effect.LARGE_SMOKE, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
            }
        }
        if (this.tier >= 1 && !this.boomboom && SUtil.random(0, 100) == 2 && !this.Cooldown) {
            this.boomboom = true;
            this.circlus = 1;
            this.tptoground((org.bukkit.entity.Entity)this.getBukkitEntity());
            this.flog = this.getBukkitEntity().getLocation().clone().add(0.0, 0.0, 0.0);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.0);
            this.hologram.getEntity().setCustomName(ChatColor.WHITE + "Boom: " + ChatColor.RED + "05");
            this.cylinder(this.getBukkitEntity().getLocation(), 1);
            SUtil.delay(() -> this.cylinder(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 1), 10L);
            SUtil.delay(() -> {
                this.circlus = 1;
            }, 20L);
            SUtil.delay(() -> {
                this.circlus = 2;
            }, 30L);
            SUtil.delay(() -> {
                this.circlus = 3;
            }, 40L);
            SUtil.delay(() -> {
                this.circlus = 4;
            }, 50L);
            SUtil.delay(() -> {
                this.circlus = 5;
            }, 60L);
            SUtil.delay(() -> {
                this.circlus = 6;
            }, 70L);
            SUtil.delay(() -> this.cylinder(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 2), 20L);
            SUtil.delay(() -> this.cylinder(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 3), 30L);
            SUtil.delay(() -> this.cylinder(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 4), 40L);
            SUtil.delay(() -> this.cylinder(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 5), 50L);
            SUtil.delay(() -> this.cylinder(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 6), 60L);
            SUtil.delay(() -> this.cylinder(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 7), 70L);
            SUtil.delay(() -> this.cylinder(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 7), 80L);
            SUtil.delay(() -> this.cylinder(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 7), 90L);
            SUtil.delay(() -> this.cylinderReset(this.getBukkitEntity().getLocation().clone().add(0.0, -1.0, 0.0), 7), 120L);
            SUtil.delay(() -> this.hologram.getEntity().setCustomName(ChatColor.WHITE + "Boom! " + ChatColor.RED + "04"), 20L);
            SUtil.delay(() -> this.hologram.getEntity().setCustomName(ChatColor.WHITE + "Boom! " + ChatColor.RED + "03"), 40L);
            SUtil.delay(() -> this.hologram.getEntity().setCustomName(ChatColor.WHITE + "Boom! " + ChatColor.RED + "02"), 60L);
            SUtil.delay(() -> this.hologram.getEntity().setCustomName(ChatColor.WHITE + "Boom! " + ChatColor.RED + "01"), 80L);
            SUtil.delay(() -> this.hologram.getEntity().setCustomName(ChatColor.WHITE + "Boom! " + ChatColor.RED + "00"), 100L);
            SUtil.delay(() -> this.boom((org.bukkit.entity.Entity)((Zombie)this.bukkitEntity)), 105L);
            SUtil.delay(() -> {
                this.boomboom = false;
            }, 120L);
            SUtil.delay(() -> this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(this.getMovementSpeed()), 121L);
            SUtil.delay(() -> this.hologram.getEntity().setCustomName(ChatColor.RED + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000)), 122L);
            SUtil.delay(() -> {
                this.Cooldown = true;
            }, 123L);
            SUtil.delay(() -> new BukkitRunnable(){

                public void run() {
                    if (AtonedHorror.this.Cooldown) {
                        AtonedHorror.this.Cooldown = false;
                    }
                }
            }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 400L), 124L);
        }
    }

    @Override
    public void onDamage(SEntity sEntity, org.bukkit.entity.Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        if (e.getDamager() instanceof Arrow) {
            e.setCancelled(true);
            return;
        }
        LivingEntity en = sEntity.getEntity();
        Vector v = new Vector(0, 0, 0);
        SUtil.delay(() -> AtonedHorror.lambda$onDamage$27((org.bukkit.entity.Entity)en, v), 1L);
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        EntityManager.DEFENSE_PERCENTAGE.put((org.bukkit.entity.Entity)entity, 70);
        entity.setMetadata("BOSS_OWNER_" + Bukkit.getPlayer((UUID)this.getSpawnerUUID()).getUniqueId().toString(), (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        SUtil.delay(() -> {
            this.Cooldown = false;
        }, 350L);
        this.hologram = new SEntity(entity.getLocation().add(0.0, 2.3, 0.0), SEntityType.UNCOLLIDABLE_ARMOR_STAND, new Object[0]);
        entity.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ((ArmorStand)this.hologram.getEntity()).setVisible(false);
        ((ArmorStand)this.hologram.getEntity()).setGravity(false);
        this.hologram.getEntity().setCustomNameVisible(true);
        entity.setMetadata("notDisplay", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        this.hologram_name = new SEntity(entity.getLocation().add(0.0, 2.0, 0.0), SEntityType.UNCOLLIDABLE_ARMOR_STAND, new Object[0]);
        ((ArmorStand)this.hologram_name.getEntity()).setVisible(false);
        Entity e = this.getBukkitEntity().getHandle();
        double height = e.getBoundingBox().e - e.getBoundingBox().b;
        ((ArmorStand)this.hologram_name.getEntity()).setGravity(false);
        this.hologram_name.getEntity().setCustomNameVisible(true);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    SUtil.delay(() -> AtonedHorror.this.hologram_name.remove(), 20L);
                    AtonedHorror.this.hologram.remove();
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        if (this.tier >= 1) {
            new BukkitRunnable(){

                public void run() {
                    Player player = Bukkit.getPlayer((UUID)AtonedHorror.this.spawnerUUID);
                    if (player == null) {
                        return;
                    }
                    int random = SUtil.random(1, 400);
                    if (random < 50) {
                        return;
                    }
                    if (AtonedHorror.this.boomboom) {
                        return;
                    }
                    if (entity.isDead()) {
                        this.cancel();
                        return;
                    }
                    Vector playersVector = AtonedHorror.this.createS(player).toVector();
                    ArmorStand armorStand1 = (ArmorStand)entity.getWorld().spawnEntity(entity.getLocation().add(0.0, 1.5, 0.0), EntityType.ARMOR_STAND);
                    armorStand1.getEquipment().setHelmet(SItem.of(SMaterial.TNT).getStack());
                    armorStand1.setGravity(true);
                    armorStand1.setVisible(false);
                    armorStand1.setCustomName(ChatColor.translateAlternateColorCodes((char)'&', (String)"&c\u2620 &fAtoned Horror"));
                    Vector mobsVector = entity.getLocation().toVector();
                    Vector vectorBetween = playersVector.subtract(mobsVector);
                    vectorBetween.divide(new Vector(10, 10, 10));
                    vectorBetween.add(new Vector(0.0, 0.3, 0.0));
                    int id = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)SkySimEngine.getPlugin(), () -> armorStand1.setVelocity(vectorBetween), 2L, 2L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SkySimEngine.getPlugin(), () -> Bukkit.getScheduler().cancelTask(id), 8L);
                    armorStand1.setMetadata("BoomBoomAtoned", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
                    EntityLiving nms = ((CraftLivingEntity)armorStand1).getHandle();
                    AtonedHorror.this.aA((org.bukkit.entity.Entity)armorStand1);
                }
            }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 20L, 40L);
        }
        if (this.tier >= 1) {
            new BukkitRunnable(){

                public void run() {
                    if (entity.isDead()) {
                        this.cancel();
                        return;
                    }
                    if (entity.getNoDamageTicks() == 0 && !AtonedHorror.this.boomboom && entity.getHealth() != entity.getMaxHealth()) {
                        if (entity.getHealth() + 55000.0 >= entity.getMaxHealth()) {
                            entity.setHealth(entity.getMaxHealth());
                        } else {
                            entity.setHealth(entity.getHealth() + 55000.0);
                        }
                    }
                }
            }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 20L, 10L);
        }
    }

    @Override
    public void onDeath(SEntity sEntity, org.bukkit.entity.Entity killed, org.bukkit.entity.Entity damager) {
        this.hologram.remove();
        SUtil.delay(() -> this.hologram_name.remove(), 20L);
    }

    @Override
    public String getEntityName() {
        return ChatColor.RED + "\u2620 " + ChatColor.WHITE + "Atoned Horror";
    }

    @Override
    public double getEntityMaxHealth() {
        return MAX_HEALTH_VALUES.getByNumber(this.tier);
    }

    @Override
    public double getDamageDealt() {
        return DAMAGE_VALUES.getByNumber(this.tier) * 1.1;
    }

    @Override
    public double getMovementSpeed() {
        return SPEED_VALUES.getByNumber(this.tier);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(new ItemStack(Material.IRON_SWORD)), SItem.of(SMaterial.ATONED_HEAD).getStack(), SUtil.enchant(new ItemStack(Material.DIAMOND_CHESTPLATE)), SUtil.enchant(SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB((int)0xFFFFFF))), SUtil.enchant(SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB((int)0xFFFFFF))));
    }

    @Override
    public LivingEntity spawn(Location location) {
        this.world = ((CraftWorld)location.getWorld()).getHandle();
        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.world.addEntity((Entity)this, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return (LivingEntity)this.getBukkitEntity();
    }

    @Override
    public List<EntityDrop> drops() {
        ArrayList<EntityDrop> drops = new ArrayList<EntityDrop>();
        int revFlesh = SUtil.random(1, 3);
        if (this.tier == 2) {
            revFlesh = SUtil.random(9, 18);
        }
        if (this.tier == 3) {
            revFlesh = SUtil.random(30, 50);
        }
        if (this.tier == 4) {
            revFlesh = SUtil.random(50, 64);
        }
        drops.add(new EntityDrop(SUtil.setStackAmount(SItem.of(SMaterial.REVENANT_FLESH).getStack(), revFlesh), EntityDropType.GUARANTEED, 1.0));
        if (this.tier >= 1) {
            int foulFlesh = 1;
            foulFlesh = SUtil.random(3, 5);
            drops.add(new EntityDrop(SUtil.setStackAmount(SItem.of(SMaterial.FOUL_FLESH).getStack(), foulFlesh), EntityDropType.OCCASIONAL, 0.2));
            drops.add(new EntityDrop(SMaterial.PESTILENCE_RUNE, EntityDropType.RARE, 0.005));
            drops.add(new EntityDrop(SMaterial.UNDEAD_CATALYST, EntityDropType.EXTRAORDINARILY_RARE, 0.01, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.REVENANT_CATALYST, EntityDropType.EXTRAORDINARILY_RARE, 0.01, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
            SItem smiteBook = SItem.of(SMaterial.ENCHANTED_BOOK);
            smiteBook.addEnchantment(EnchantmentType.SMITE, 15);
            drops.add(new EntityDrop(smiteBook.getStack(), EntityDropType.CRAZY_RARE, 0.0025, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.BEHEADED_HORROR, EntityDropType.CRAZY_RARE, 0.005, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.SNAKE_RUNE, EntityDropType.CRAZY_RARE, 0.005, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.SCYTHE_BLADE, EntityDropType.CRAZY_RARE, 0.0033333333333333335, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.WARDEN_HEART, EntityDropType.INSANE_RARE, 0.0025, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.SHARD_OF_THE_SHREDDED, EntityDropType.CRAZY_RARE, 0.0022222222222222222, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.HIDDEN_SHARD_DIAMOND, EntityDropType.CRAZY_RARE, 0.0025, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
            SItem vcBook = SItem.of(SMaterial.ENCHANTED_BOOK);
            vcBook.addEnchantment(EnchantmentType.VICIOUS, 10);
            drops.add(new EntityDrop(vcBook.getStack(), EntityDropType.INSANE_RARE, 0.002, Bukkit.getPlayer((UUID)this.getSpawnerUUID())));
        }
        return drops;
    }

    @Override
    public double getXPDropped() {
        return 2000.0;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public boolean isVillager() {
        return false;
    }

    @Override
    public UUID getSpawnerUUID() {
        return this.spawnerUUID;
    }

    public void boom(org.bukkit.entity.Entity entity) {
        if (entity.isDead()) {
            return;
        }
        entity.getWorld().playEffect(entity.getLocation(), Effect.EXPLOSION_HUGE, 3);
        entity.getWorld().playEffect(entity.getLocation(), Effect.EXPLOSION_HUGE, 3);
        entity.getWorld().playEffect(entity.getLocation(), Effect.EXPLOSION_HUGE, 3);
        SUtil.lightningLater(entity.getLocation(), true, 1L);
        SUtil.lightningLater(entity.getLocation().add((double)SUtil.random(-7, 7), 0.0, (double)SUtil.random(-7, 7)), true, 1L);
        SUtil.lightningLater(entity.getLocation().add((double)SUtil.random(-7, 7), 0.0, (double)SUtil.random(-7, 7)), true, 2L);
        SUtil.lightningLater(entity.getLocation().add((double)SUtil.random(-7, 7), 0.0, (double)SUtil.random(-7, 7)), true, 3L);
        SUtil.lightningLater(entity.getLocation().add((double)SUtil.random(-7, 7), 0.0, (double)SUtil.random(-7, 7)), true, 4L);
        for (org.bukkit.entity.Entity e : entity.getNearbyEntities(7.0, 7.0, 7.0)) {
            if (!(e instanceof LivingEntity) || e.hasMetadata("NoAffect")) continue;
            e.getWorld().playSound(e.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION_LARGE, 3);
            SUtil.lightningLater(e.getLocation(), true, 1L);
            if (e instanceof Player && ((Player)e).getGameMode() != GameMode.CREATIVE && ((Player)e).getGameMode() != GameMode.SPECTATOR) {
                User.getUser(e.getUniqueId()).kill(EntityDamageEvent.DamageCause.ENTITY_ATTACK, entity);
                continue;
            }
            ((LivingEntity)e).damage(((LivingEntity)e).getHealth(), e);
        }
    }

    public Location createS(Player player) {
        Location loc = player.getLocation();
        double xl = loc.getX();
        double yl = loc.getY();
        double zl = loc.getZ();
        Location location = new Location(player.getWorld(), xl, yl, zl);
        for (int y = location.getBlockY(); y > 0; --y) {
            if (location.subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR) continue;
            this.test(location);
            break;
        }
        return this.getBlockLoc(this.getBlockLoc(loc.getBlock().getLocation().clone())).clone().add(0.0, 0.0, 0.0);
    }

    public void getBlockAt(Player player) {
        Object returnloc = null;
        Location loc = player.getLocation();
        double xl = loc.getX();
        double yl = loc.getY();
        double zl = loc.getZ();
        Location location = new Location(player.getWorld(), xl, yl, zl);
        for (int y = location.getBlockY(); y > 0 && location.subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR; --y) {
        }
    }

    public void test(Location loc) {
        Location l = this.getBlockLoc(loc.getBlock().getLocation().clone());
        this.createClay(l.clone(), (byte)14);
        this.createClay(l.clone().add(0.0, 0.0, 1.0), (byte)14);
        this.createClay(l.clone().add(1.0, 0.0, 0.0), (byte)14);
        this.createClay(l.clone().add(0.0, 0.0, -1.0), (byte)14);
        this.createClay(l.clone().add(-1.0, 0.0, 0.0), (byte)14);
        this.createClay(l.clone().add(0.0, 0.0, -2.0), (byte)0);
        this.createClay(l.clone().add(1.0, 0.0, -1.0), (byte)0);
        this.createClay(l.clone().add(2.0, 0.0, 0.0), (byte)0);
        this.createClay(l.clone().add(1.0, 0.0, 1.0), (byte)0);
        this.createClay(l.clone().add(0.0, 0.0, 2.0), (byte)0);
        this.createClay(l.clone().add(-1.0, 0.0, 1.0), (byte)0);
        this.createClay(l.clone().add(-2.0, 0.0, 0.0), (byte)0);
        this.createClay(l.clone().add(-1.0, 0.0, -1.0), (byte)0);
        this.createClay(l.clone().add(1.0, 0.0, -2.0), (byte)8);
        this.createClay(l.clone().add(2.0, 0.0, -1.0), (byte)8);
        this.createClay(l.clone().add(2.0, 0.0, 1.0), (byte)8);
        this.createClay(l.clone().add(1.0, 0.0, 2.0), (byte)8);
        this.createClay(l.clone().add(-1.0, 0.0, 2.0), (byte)8);
        this.createClay(l.clone().add(-2.0, 0.0, 1.0), (byte)8);
        this.createClay(l.clone().add(-2.0, 0.0, -1.0), (byte)8);
        this.createClay(l.clone().add(-1.0, 0.0, -2.0), (byte)8);
    }

    public void createClay(Location loc, byte color) {
        Block cacheBlock = loc.getBlock();
        block0: for (Player p : loc.getWorld().getPlayers()) {
            int y;
            Location cacheLocation;
            if (cacheBlock.getType().equals((Object)Material.AIR)) {
                cacheLocation = cacheBlock.getLocation().clone();
                for (y = cacheLocation.getBlockY(); y > 0; --y) {
                    if (cacheLocation.subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR) continue;
                    this.sendPacketBlock(cacheLocation, color, p);
                    SUtil.delay(() -> cacheLocation.getBlock().getState().update(), 30L);
                    continue block0;
                }
                continue;
            }
            if (cacheBlock.getType() != Material.AIR && cacheBlock.getLocation().add(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR) {
                cacheLocation = cacheBlock.getLocation().clone();
                for (y = cacheLocation.getBlockY(); y > 0; ++y) {
                    if (cacheLocation.add(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR) continue;
                    this.sendPacketBlock(cacheLocation, color, p);
                    SUtil.delay(() -> cacheLocation.getBlock().getState().update(), 30L);
                    continue block0;
                }
                continue;
            }
            this.sendPacketBlock(loc, color, p);
            SUtil.delay(() -> loc.getBlock().getState().update(), 30L);
        }
    }

    public void sendPacketBlock(Location loc, byte color, Player p) {
        if (loc.getBlock().getType().toString().toUpperCase().contains("STAIR")) {
            if (color == 14) {
                p.sendBlockChange(loc, Material.ACACIA_STAIRS, loc.getBlock().getState().getData().getData());
            } else {
                p.sendBlockChange(loc, Material.QUARTZ_STAIRS, loc.getBlock().getState().getData().getData());
            }
        } else if (loc.getBlock().getType().toString().toUpperCase().contains("SLAB") || loc.getBlock().getType().toString().toUpperCase().contains("STEP")) {
            if (color == 14) {
                p.sendBlockChange(loc, Material.WOOD_STEP, (byte)4);
            } else {
                p.sendBlockChange(loc, Material.STEP, (byte)7);
            }
        } else {
            p.sendBlockChange(loc, Material.STAINED_CLAY, color);
        }
    }

    public Location getBlockLoc(Location l) {
        double x = l.getX() + 0.5;
        double z = l.getZ() + 0.5;
        if (l.getX() < 0.0) {
            x = l.getX() - 0.5;
        }
        if (l.getZ() < 0.0) {
            z = l.getZ() - 0.5;
        }
        Location loc = new Location(l.getWorld(), x, l.getY(), z);
        return loc;
    }

    public void tptoground(org.bukkit.entity.Entity e) {
        Location loc = e.getLocation();
        double xl = loc.getX();
        double yl = loc.getY();
        double zl = loc.getZ();
        Location location = new Location(loc.getWorld(), xl, yl, zl);
        for (int y = location.getBlockY(); y > 0; --y) {
            if (location.subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR) continue;
            e.teleport(location.clone().add(0.0, 1.0, 0.0));
            break;
        }
    }

    public void cylinder(Location loc, int r) {
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        org.bukkit.World w = loc.getWorld();
        int rSquared = r * r;
        for (int x = cx - r; x <= cx + r; ++x) {
            for (int z = cz - r; z <= cz + r; ++z) {
                if ((cx - x) * (cx - x) + (cz - z) * (cz - z) > rSquared) continue;
                Location l = new Location(w, (double)x, (double)cy, (double)z);
                this.sendPacketBedrock(l);
            }
        }
    }

    public void cylinderReset(Location loc, int r) {
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        org.bukkit.World w = loc.getWorld();
        int rSquared = r * r;
        for (int x = cx - r; x <= cx + r; ++x) {
            for (int z = cz - r; z <= cz + r; ++z) {
                if ((cx - x) * (cx - x) + (cz - z) * (cz - z) > rSquared) continue;
                Location l = new Location(w, (double)x, (double)cy, (double)z);
                l.getBlock().getState().update();
            }
        }
    }

    public void createBedrock(Location loc) {
        Block cacheBlock = loc.getBlock();
        block0: for (Player p : loc.getWorld().getPlayers()) {
            int y;
            Location cacheLocation;
            if (cacheBlock.getType().equals((Object)Material.AIR)) {
                cacheLocation = cacheBlock.getLocation().clone();
                for (y = cacheLocation.getBlockY(); y > 0; --y) {
                    if (cacheLocation.subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR) continue;
                    this.sendPacketBedrock(cacheLocation);
                    continue block0;
                }
                continue;
            }
            if (cacheBlock.getType() != Material.AIR && cacheBlock.getLocation().add(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR) {
                cacheLocation = cacheBlock.getLocation().clone();
                for (y = cacheLocation.getBlockY(); y > 0; ++y) {
                    if (cacheLocation.add(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR) continue;
                    this.sendPacketBedrock(cacheLocation);
                    continue block0;
                }
                continue;
            }
            this.sendPacketBedrock(loc);
        }
    }

    public void sendPacket(org.bukkit.World w, Location l) {
        for (Player p : w.getPlayers()) {
            p.sendBlockChange(l, Material.BEDROCK, (byte)0);
        }
    }

    public void sendPacketBedrock(Location loc) {
        if (loc.getBlock().getType() == Material.AIR) {
            return;
        }
        if (loc.getBlock().getType().toString().toUpperCase().contains("STAIR")) {
            this.send(loc, Material.NETHER_BRICK_STAIRS, loc.getBlock().getState().getData().getData(), loc.getWorld());
        } else if (loc.getBlock().getType().toString().toUpperCase().contains("SLAB") || loc.getBlock().getType().toString().toUpperCase().contains("STEP")) {
            this.send(loc, Material.STEP, (byte)6, loc.getWorld());
        } else {
            this.sendPacket(loc.getWorld(), loc);
        }
    }

    public void send(Location loc, Material mat, byte data, org.bukkit.World w) {
        for (Player p : w.getPlayers()) {
            p.sendBlockChange(loc, mat, data);
        }
    }

    public void aA(final org.bukkit.entity.Entity entity) {
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (entity.hasMetadata("BoomBoomAtoned") && entity.getTicksLived() > 2 && entity.isOnGround()) {
                    entity.getWorld().playSound(entity.getLocation(), Sound.WITHER_SPAWN, 1.0f, 2.0f);
                    for (org.bukkit.entity.Entity e : entity.getNearbyEntities(4.0, 3.0, 4.0)) {
                        if (!(e instanceof LivingEntity) || e.hasMetadata("NoAffect")) continue;
                        if (e instanceof Player) {
                            User.getUser(e.getUniqueId()).damage(((Player)e).getMaxHealth() * 10.0 / 100.0, EntityDamageEvent.DamageCause.ENTITY_ATTACK, entity);
                            ((LivingEntity)e).damage(0.1, entity);
                            continue;
                        }
                        ((LivingEntity)e).damage((double)SUtil.random(0, 10), entity);
                    }
                    entity.remove();
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    private static /* synthetic */ void lambda$onDamage$27(org.bukkit.entity.Entity en, Vector v) {
        en.setVelocity(v);
    }
}

