/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  me.libraryaddict.disguise.disguisetypes.PlayerDisguise
 *  me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher
 *  net.minecraft.server.v1_8_R3.AttributeInstance
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.EntityZombie
 *  net.minecraft.server.v1_8_R3.GenericAttributes
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.GameMode
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.WitherSkull
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.entity.dungeons.regularentity;

import com.google.common.util.concurrent.AtomicDouble;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.entity.zombie.NPCMobs;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class CryptSoulstealer
extends BaseZombie
implements NPCMobs {
    private boolean isBowing = false;

    @Override
    public String getEntityName() {
        return "Crypt Soulstealer";
    }

    @Override
    public double getEntityMaxHealth() {
        return 1.0E8;
    }

    @Override
    public double getDamageDealt() {
        return 1000000.0;
    }

    public static ItemStack b(int hexcolor, Material m) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(m), Color.fromRGB((int)hexcolor));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        ((CraftZombie)entity).setBaby(false);
        AttributeInstance followRange = ((CraftLivingEntity)entity).getHandle().getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        followRange.setValue(40.0);
        final PlayerDisguise pl = Sputnik.applyPacketNPC((Entity)entity, "ewogICJ0aW1lc3RhbXAiIDogMTYyNjMyMjczMDkzMSwKICAicHJvZmlsZUlkIiA6ICIzZmM3ZmRmOTM5NjM0YzQxOTExOTliYTNmN2NjM2ZlZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJZZWxlaGEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDA5M2Q2NTJjMWI5ZjIyZmUwZTgxZWIxYTAyOGZhNGIwYjY5MDRjZWQ1YzdlZTlkNjI5YTgxOTU2MDc2NDk5YyIKICAgIH0KICB9Cn0=", "NybQzjteIreKG8mUVlpy4+gVYEloMFxsdAQyfRk5+WS2braPgTWfwxVvv8sukxJLpgxQjrOzSOVhwW5k4cO9j2n8ugWUOzUWnrxGzKmvegZ5UTmDVanLhg2ESFce0oFadJ7RrrQeYYgfqFFjKsA/9Q+Aky0KfdV38pt8U2UsGq68IVSjyickXD3QiwHR9u4FINT98th6m4/9iwhm80Oz1wd9C3O4kdpqGwNWrxLJx8MlcTfzmqSnuuw8bpSNXjXeD1yuScqAXkr8CYg78vg106YFQMNMuwNyIJX65HtTnjJD01xjoKVDw+jKZkFy9v/9ejtQyUjv1cumzrD+lQDejbKyFDNq5cuS0FGza3cfZrqXDXLRr4ujxARNQGxDsbRaXHVbGhuVnHfKy2Z5SjjPOgAzk+ZLzt3nINsp0lRj9xxYilOnKLi+6ExC38+1xUwcU2jtqvkqqCHYDe35WtVIj6nir/sBSbOu93z2anM7/eFH2cboGP/JVwrAJ4o5gH2u644DTxfB9zd6uUqs2mKGwSDd6N/S8IYJmjjQbk87mj9NpnMvWbPVpAs7pmROzuLJ12w+wJtUz6LqU1Nr5YgZyT2NgGiG9xZl560RAAXtNDexM29Zy+gNfIL6aYuLoy6Jz0OhPcKmDfsVWsSsUO7AQDRSLcc5cgGO17m/P0E0l6o=", true);
        pl.getWatcher().setRightClicking(false);
        final PlayerWatcher skywatch = pl.getWatcher();
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 70);
        entity.setMetadata("LD", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("DungeonMobs", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    Sputnik.zero((Entity)entity);
                    this.cancel();
                    return;
                }
                CraftLivingEntity target1 = ((CraftZombie)entity).getTarget();
                if (target1 != null) {
                    if (target1.getLocation().distance(entity.getLocation()) >= 2.0 && target1.getLocation().distance(entity.getLocation()) < 16.0) {
                        entity.teleport(entity.getLocation().setDirection(target1.getLocation().toVector().subtract(entity.getLocation().toVector())));
                        CryptSoulstealer.sendHeadRotation((Entity)entity, entity.getLocation().getYaw(), entity.getLocation().getPitch());
                    }
                    if (target1.getLocation().distance(entity.getLocation()) < 2.0 || target1.getLocation().distance(entity.getLocation()) > 16.0) {
                        SUtil.delay(() -> entity.getEquipment().setItemInHand(SItem.of(SMaterial.BOW).getStack()), 0L);
                        CryptSoulstealer.this.isBowing = false;
                    }
                    if (target1.getLocation().distance(entity.getLocation()) >= 2.0 && target1.getLocation().distance(entity.getLocation()) < 16.0 && !CryptSoulstealer.this.isBowing) {
                        CryptSoulstealer.this.isBowing = true;
                        skywatch.setRightClicking(false);
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 4));
                        entity.getEquipment().setItemInHand(SItem.of(SMaterial.BOW).getStack());
                        new BukkitRunnable(){
                            int t = 0;
                            int atkCharge = 20;

                            public void run() {
                                ++this.t;
                                if (!CryptSoulstealer.this.isBowing) {
                                    skywatch.setRightClicking(false);
                                    entity.removePotionEffect(PotionEffectType.SLOW);
                                    this.cancel();
                                    return;
                                }
                                if (this.t == 5) {
                                    if (!CryptSoulstealer.this.isBowing) {
                                        return;
                                    }
                                    skywatch.setRightClicking(true);
                                }
                                if (this.t == this.atkCharge) {
                                    if (!CryptSoulstealer.this.isBowing) {
                                        return;
                                    }
                                    skywatch.setRightClicking(false);
                                }
                                if (this.t >= this.atkCharge + 1) {
                                    if (!CryptSoulstealer.this.isBowing) {
                                        return;
                                    }
                                    CryptSoulstealer.this.throwSkull(entity, pl);
                                    skywatch.setRightClicking(false);
                                    entity.removePotionEffect(PotionEffectType.SLOW);
                                    CryptSoulstealer.this.isBowing = false;
                                }
                            }
                        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
        new BukkitRunnable(){

            public void run() {
                EntityLiving nms = ((CraftLivingEntity)entity).getHandle();
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                CraftLivingEntity target1 = ((CraftZombie)entity).getTarget();
                for (Entity entities : entity.getWorld().getNearbyEntities(entity.getLocation().add(entity.getLocation().getDirection().multiply(1.0)), 1.5, 1.5, 1.5)) {
                    Player target;
                    if (CryptSoulstealer.this.isBowing || !(entities instanceof Player) || (target = (Player)entities).getGameMode() == GameMode.CREATIVE || target.getGameMode() == GameMode.SPECTATOR || target.hasMetadata("NPC") || target.getNoDamageTicks() == 7 || SUtil.random(0, 10) > 8) continue;
                    entity.teleport(entity.getLocation().setDirection(target.getLocation().subtract(entities.getLocation()).toVector()));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer)players).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((net.minecraft.server.v1_8_R3.Entity)((CraftLivingEntity)entity).getHandle(), 0));
                    }
                    nms.r((net.minecraft.server.v1_8_R3.Entity)((CraftPlayer)target).getHandle());
                    break;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(new ItemStack(Material.BOW), null, null, null, null);
    }

    @Override
    public void onDeath(SEntity sEntity, Entity killed, Entity damager) {
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public boolean hasNameTag() {
        return false;
    }

    @Override
    public boolean isVillager() {
        return false;
    }

    @Override
    public void onDamage(SEntity sEntity, Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
    }

    @Override
    public double getXPDropped() {
        return 56.0;
    }

    @Override
    public double getMovementSpeed() {
        return 0.2;
    }

    public void throwSkull(LivingEntity e, PlayerDisguise pl) {
        WitherSkull skull = (WitherSkull)e.launchProjectile(WitherSkull.class);
        skull.setShooter((ProjectileSource)e);
        e.getWorld().playSound(e.getLocation(), Sound.WITHER_SHOOT, 1.0f, 1.0f);
    }

    public static void sendHeadRotation(Entity e, float yaw, float pitch) {
        EntityZombie pl = ((CraftZombie)e).getHandle();
        pl.setLocation(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), yaw, pitch);
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport((net.minecraft.server.v1_8_R3.Entity)pl);
        Sputnik.sendPacket(e.getWorld(), (Packet)packet);
    }
}

