/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityZombie
 *  net.minecraft.server.v1_8_R3.GenericAttributes
 *  net.minecraft.server.v1_8_R3.World
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Zombie
 *  org.bukkit.event.entity.CreatureSpawnEvent$SpawnReason
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.nms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
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
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class RevenantHorror
extends EntityZombie
implements SNMSEntity,
EntityFunction,
ZombieStatistics,
SlayerBoss {
    private static final TieredValue<Double> MAX_HEALTH_VALUES = new TieredValue<Double>(500.0, 20000.0, 400000.0, 1500000.0);
    private static final TieredValue<Double> DAMAGE_VALUES = new TieredValue<Double>(15.0, 50.0, 300.0, 1000.0);
    private static final TieredValue<Double> SPEED_VALUES = new TieredValue<Double>(0.35, 0.4, 0.45, 0.55);
    private final int tier;
    private boolean enraged;
    private boolean raged;
    private boolean Cooldown;
    private final long end;
    private SEntity hologram;
    private SEntity hologram_name;
    private final UUID spawnerUUID;

    public RevenantHorror(Integer tier, UUID spawnerUUID) {
        super((World)((CraftWorld)Bukkit.getPlayer((UUID)spawnerUUID).getWorld()).getHandle());
        this.tier = tier;
        this.enraged = false;
        this.end = System.currentTimeMillis() + 180000L;
        this.spawnerUUID = spawnerUUID;
        this.Cooldown = true;
    }

    public RevenantHorror(World world) {
        super(world);
        this.tier = 1;
        this.enraged = false;
        this.end = System.currentTimeMillis() + 180000L;
        this.spawnerUUID = UUID.randomUUID();
        this.Cooldown = true;
    }

    public void t_() {
        super.t_();
        Player player = Bukkit.getPlayer((UUID)this.spawnerUUID);
        if (player == null) {
            return;
        }
        if (((Zombie)this.bukkitEntity).getWorld() == player.getWorld() && this.getBukkitEntity().getLocation().distance(player.getLocation()) >= 20.0 && SUtil.random(0, 10) == 0) {
            this.getBukkitEntity().teleport(player.getLocation());
        }
        Zombie e = (Zombie)this.getBukkitEntity();
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
        if (!this.raged) {
            this.hologram.getEntity().setCustomName(ChatColor.RED + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000));
        } else {
            this.hologram.getEntity().setCustomName(ChatColor.DARK_RED + "ENRAGED " + ChatColor.RED + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000));
        }
        ((Zombie)this.bukkitEntity).setTarget((LivingEntity)player);
        if (this.tier >= 3 && !this.enraged && SUtil.random(0, 20) == 0 && !this.Cooldown) {
            this.enraged = true;
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(this.getMovementSpeed());
            this.hologram.getEntity().setCustomName(ChatColor.DARK_RED + "" + ChatColor.RED + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000));
            player.playSound(player.getLocation(), Sound.ZOMBIE_WOODBREAK, 1.0f, 1.0f);
            player.setVelocity(new Vector(SUtil.random(-1.0, 1.0), SUtil.random(0.0, 0.5), SUtil.random(-1.0, 1.0)));
            new BukkitRunnable(){

                public void run() {
                    RevenantHorror.this.enraged = false;
                    RevenantHorror.this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(RevenantHorror.this.getMovementSpeed());
                    RevenantHorror.this.hologram.getEntity().setCustomName(ChatColor.RED + SUtil.getFormattedTime(RevenantHorror.this.end - System.currentTimeMillis(), 1000));
                }
            }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 200L);
        }
        if (this.tier >= 3 && !this.raged && SUtil.random(0, 200) == 0 && !this.Cooldown) {
            this.raged = true;
            this.Cooldown = true;
            e.getEquipment().setChestplate(SUtil.enchant(SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB((int)0xBE0000))));
            e.getEquipment().setHelmet(SItem.of(SMaterial.REV_HORROR_2).getStack());
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(this.getMovementSpeed() + 0.02);
            this.hologram.getEntity().setCustomName(ChatColor.DARK_RED + "ENRAGED " + ChatColor.RED + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000));
            new BukkitRunnable(){
                public void run() {
                    e.getEquipment().setChestplate(SUtil.enchant(new ItemStack(Material.DIAMOND_CHESTPLATE)));
                    e.getEquipment().setHelmet(SItem.of(SMaterial.REVENANT_HORROR_HEAD).getStack());
                    RevenantHorror.this.raged = false;
                    RevenantHorror.this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(RevenantHorror.this.getMovementSpeed());
                    RevenantHorror.this.hologram.getEntity().setCustomName(ChatColor.RED + SUtil.getFormattedTime(RevenantHorror.this.end - System.currentTimeMillis(), 1000));
                    SUtil.delay(() -> RevenantHorror.this.Cooldown = false, 550L);
                }
            }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 250L);
        }
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        SUtil.delay(() -> {
            this.Cooldown = false;
        }, 400L);
        entity.setMetadata("BOSS_OWNER_" + Bukkit.getPlayer((UUID)this.getSpawnerUUID()).getUniqueId().toString(), (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        this.hologram = new SEntity(entity.getLocation().add(0.0, 2.3, 0.0), SEntityType.UNCOLLIDABLE_ARMOR_STAND, new Object[0]);
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
                    this.cancel();
                    return;
                }
                Player player = Bukkit.getPlayer((UUID)RevenantHorror.this.spawnerUUID);
                if (player == null) {
                    return;
                }
                player.damage(RevenantHorror.this.getDamageDealt() * 0.5, (org.bukkit.entity.Entity)entity);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 60L, 60L);
        if (this.tier >= 2) {
            new BukkitRunnable(){

                public void run() {
                    if (entity.isDead()) {
                        this.cancel();
                        return;
                    }
                    Player player = Bukkit.getPlayer((UUID)RevenantHorror.this.spawnerUUID);
                    if (player == null) {
                        return;
                    }
                    player.damage(RevenantHorror.this.getDamageDealt(), (org.bukkit.entity.Entity)entity);
                }
            }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 20L, 20L);
        }
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    SUtil.delay(() -> RevenantHorror.this.hologram_name.remove(), 20L);
                    RevenantHorror.this.hologram.remove();
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    @Override
    public void onDeath(SEntity sEntity, org.bukkit.entity.Entity killed, org.bukkit.entity.Entity damager) {
        this.hologram.remove();
        SUtil.delay(() -> this.hologram_name.remove(), 20L);
    }

    @Override
    public String getEntityName() {
        return ChatColor.RED + "\u2620 " + ChatColor.AQUA + "Revenant Horror";
    }

    @Override
    public double getEntityMaxHealth() {
        return MAX_HEALTH_VALUES.getByNumber(this.tier);
    }

    @Override
    public double getDamageDealt() {
        return DAMAGE_VALUES.getByNumber(this.tier) * (this.enraged ? 1.8 : 1.0) * (double)(this.raged ? 2 : 2);
    }

    @Override
    public double getMovementSpeed() {
        return SPEED_VALUES.getByNumber(this.tier) * (this.enraged ? 1.05 : 1.0);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(new ItemStack(Material.DIAMOND_HOE), SItem.of(SMaterial.REVENANT_HORROR_HEAD).getStack(), SUtil.enchant(new ItemStack(Material.DIAMOND_CHESTPLATE)), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.DIAMOND_BOOTS));
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
        if (this.tier >= 2) {
            int foulFlesh = 1;
            if (this.tier == 3) {
                foulFlesh = SUtil.random(1, 2);
            }
            if (this.tier == 4) {
                foulFlesh = SUtil.random(2, 3);
            }
            drops.add(new EntityDrop(SUtil.setStackAmount(SItem.of(SMaterial.FOUL_FLESH).getStack(), foulFlesh), EntityDropType.OCCASIONAL, 0.2));
            drops.add(new EntityDrop(SMaterial.PESTILENCE_RUNE, EntityDropType.RARE, 0.05));
            drops.add(new EntityDrop(SMaterial.UNDEAD_CATALYST, EntityDropType.EXTRAORDINARILY_RARE, 0.01));
            drops.add(new EntityDrop(SMaterial.REVENANT_CATALYST, EntityDropType.EXTRAORDINARILY_RARE, 0.01));
        }
        if (this.tier >= 3) {
            SItem smiteBook = SItem.of(SMaterial.ENCHANTED_BOOK);
            smiteBook.addEnchantment(EnchantmentType.SMITE, 6);
            drops.add(new EntityDrop(smiteBook.getStack(), EntityDropType.EXTRAORDINARILY_RARE, 0.01));
            drops.add(new EntityDrop(SMaterial.BEHEADED_HORROR, EntityDropType.CRAZY_RARE, 0.005));
        }
        if (this.tier >= 4) {
            drops.add(new EntityDrop(SMaterial.SNAKE_RUNE, EntityDropType.CRAZY_RARE, 0.005));
            drops.add(new EntityDrop(SMaterial.SCYTHE_BLADE, EntityDropType.CRAZY_RARE, 5.384615384615384E-4));
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
    public boolean isVillager() {
        return false;
    }

    @Override
    public UUID getSpawnerUUID() {
        return this.spawnerUUID;
    }

    @Override
    public int getTier() {
        return this.tier;
    }
}

