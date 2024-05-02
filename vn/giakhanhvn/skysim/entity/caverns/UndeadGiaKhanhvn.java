/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.caverns;

import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.texture.ItemTexture;

public class UndeadGiaKhanhvn
extends BaseZombie {
    private boolean isEating = false;
    private boolean isBowing = false;
    private boolean EatingCooldown = false;

    @Override
    public String getEntityName() {
        return Sputnik.trans("&c&lUndead GiaKhanhVN");
    }

    @Override
    public double getEntityMaxHealth() {
        return 5.0E8;
    }

    @Override
    public double getDamageDealt() {
        return 60000.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        entity.getEquipment().setItemInHand(SItem.of(SMaterial.BONE).getStack());
        Sputnik.applyPacketNPC((Entity)entity, "ewogICJ0aW1lc3RhbXAiIDogMTYzMTk3MDM3OTg1MiwKICAicHJvZmlsZUlkIiA6ICIyMWUzNjdkNzI1Y2Y0ZTNiYjI2OTJjNGEzMDBhNGRlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJHZXlzZXJNQyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mZWI0ZTFlNTk3NDNiMDBmNWYwZmM3YTM1NjY3MGIzOTYxMGI4M2IwN2VjNTg2OWJjYmYzOGYwYTU3NGE2YTk5IgogICAgfQogIH0KfQ==", "KuyyIyFjOe4dOCqyEJZGZ/6zOD6tW69/q/CGJ+Y5rIKLfDIcRfPECoDt9mXc/R8aun50jpJdSYbgFoupv7sQaUWPAuPdcVx9YNrPWxbTn8F+1aWntg/AKswhYLb1C6jbwhzKg/PDckE405SuGn/QAcS0OFEfBb/sl/4cJ3xzTfrgnOmQuNIeoCMmBurJFTdPcFbg4CWQuKIenJDj5BM4rBM5aH1v0KJlSz6srU9tKHAr6nwTnKzComylP2nAxZsZD5PXvaYhBiWC+qUme2TVX77hOxReDQWFpG4Vfj2y/+Lh54BRRZWN7kb2B9msJxPgGw+V82A9+agoXpqClkfy1GHG1ZcAyyEblEygkeqR2ElFtyaLp1H29ebtCO4G6Tgh8shCzPFAhoGag03jBWqGQ+gVzU2R2z/w9aJXsRVAkI4fV5tg753v6a4XGUM/NbvIbwt3OJF9DwRknsbp/2dIryorKmVyMyuWG0/Qt/VWyrGj6j7Dj2/kL/xxwRe2E6B3VM260Obkx+NPJ2MmtmJ7k1G92xubnlVvA+fcLKrH+E6OeyrGCG2Eme0otZtookHshgtxI1q17m2NtOwlirw4IZWK+WsNkhzJlOpnJSCF0/koXwunKyniRSbHNWGBizoFG1LF1zSJcOkU443chhmgP0niSNwPPweQsa4qWMS1ca4=", true);
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 70);
        entity.setMetadata("notDisplay", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        final double height = 1.85;
        final ArmorStand hologram = (ArmorStand)entity.getWorld().spawn(entity.getLocation().add(0.0, height, 0.0), ArmorStand.class);
        hologram.setVisible(false);
        hologram.setGravity(false);
        hologram.setSmall(false);
        hologram.setMarker(true);
        hologram.setBasePlate(false);
        hologram.setCustomNameVisible(true);
        for (Entity e : entity.getNearbyEntities(10.0, 10.0, 10.0)) {
            Sputnik.moveTo(entity, e.getLocation(), 0.2f);
        }
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (UndeadGiaKhanhvn.this.isEating) {
                    entity.getWorld().playSound(entity.getLocation(), Sound.EAT, 1.0f, 1.0f);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 4L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (UndeadGiaKhanhvn.this.isEating) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 65, 4));
                    Location loc = entity.getLocation();
                    loc.add(0.0, 1.4, 0.0);
                    loc.add(entity.getLocation().getDirection().multiply(0.5));
                    new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.CAULDRON_ITEM))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
                    new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.CAULDRON_ITEM))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
                    new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.CAULDRON_ITEM))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 3L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (entity.getHealth() < entity.getMaxHealth() * 1.0 / 2.0 && !UndeadGiaKhanhvn.this.EatingCooldown && !UndeadGiaKhanhvn.this.isEating) {
                    UndeadGiaKhanhvn.this.EatingCooldown = true;
                    SUtil.delay(() -> UndeadGiaKhanhvn.this.isEating = true, 5L);
                    entity.getEquipment().setItemInHand(SItem.of(SMaterial.ENCHANTED_GOLDEN_APPLE).getStack());
                    new BukkitRunnable(){

                        public void run() {
                            entity.getEquipment().setItemInHand(new ItemStack(Material.AIR));
                            entity.getWorld().playSound(entity.getLocation(), Sound.BURP, 1.0f, 1.0f);
                            double healamount = SUtil.random(100000000, 150000000);
                            if (!entity.isDead()) {
                                entity.setHealth(Math.min(entity.getMaxHealth(), entity.getHealth() + healamount));
                            }
                            SUtil.delay(() -> entity.getEquipment().setItemInHand(SItem.of(SMaterial.BONE).getStack()), 5L);
                            UndeadGiaKhanhvn.this.isEating = false;
                            SUtil.delay(() -> UndeadGiaKhanhvn.this.EatingCooldown = false, SUtil.random(400, 500));
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 60L);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 10L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    hologram.setCustomName(Sputnik.trans(Sputnik.entityNameTag(entity, Sputnik.buildcustomString(UndeadGiaKhanhvn.this.getEntityName(), 0, true))));
                    new BukkitRunnable(){

                        public void run() {
                            hologram.remove();
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 20L);
                }
                if (hologram.isDead()) {
                    this.cancel();
                    return;
                }
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.setCustomName(Sputnik.trans(Sputnik.entityNameTag(entity, Sputnik.buildcustomString(UndeadGiaKhanhvn.this.getEntityName(), 0, true))));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 0L);
        new BukkitRunnable(){

            public void run() {
                EntityLiving nms = ((CraftLivingEntity)entity).getHandle();
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (Entity entities : entity.getWorld().getNearbyEntities(entity.getLocation().add(entity.getLocation().getDirection().multiply(1.0)), 1.5, 1.5, 1.5)) {
                    Player target;
                    if (UndeadGiaKhanhvn.this.isEating || !(entities instanceof Player) || (target = (Player)entities).getGameMode() == GameMode.CREATIVE || target.getGameMode() == GameMode.SPECTATOR || target.hasMetadata("NPC") || target.getNoDamageTicks() == 7 || SUtil.random(0, 10) > 8) continue;
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
    public double getXPDropped() {
        return 15570.0;
    }

    @Override
    public double getMovementSpeed() {
        return 0.3;
    }

    @Override
    public int mobLevel() {
        return 540;
    }
}

