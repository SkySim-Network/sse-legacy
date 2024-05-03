/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  org.bukkit.ChatColor
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.ItemFrame
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Villager
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.EulerAngle
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.item.weapon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.FerocityCalculation;
import vn.giakhanhvn.skysim.util.Groups;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class LividDagger
implements ToolStatistics,
MaterialFunction,
Ability {
    String ACT3 = "true";
    private boolean active;

    @Override
    public int getBaseDamage() {
        return 210;
    }

    @Override
    public double getBaseStrength() {
        return 60.0;
    }

    @Override
    public double getBaseCritChance() {
        return 1.0;
    }

    @Override
    public double getBaseAttackSpeed() {
        return 100.0;
    }

    @Override
    public double getBaseCritDamage() {
        return 0.5;
    }

    @Override
    public String getDisplayName() {
        return "Livid Dagger";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.WEAPON;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.SWORD;
    }

    @Override
    public String getLore() {
        return null;
    }

    @Override
    public String getAbilityName() {
        return "Throw";
    }

    @Override
    public String getAbilityDescription() {
        return "Throw your dagger at your enemies! Your Critical Hits deal " + ChatColor.BLUE + "100% " + ChatColor.GRAY + "more damage if you are behind your target.";
    }

    @Override
    public void onAbilityUse(final Player player1, final SItem sItem) {
        Location throwLoc = player1.getLocation().add(0.0, 1.2, 0.0);
        final Vector throwVec = player1.getLocation().add(player1.getLocation().getDirection().multiply(10)).toVector().subtract(player1.getLocation().toVector()).normalize().multiply(1.2);
        for (Player p : player1.getWorld().getPlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((net.minecraft.server.v1_8_R3.Entity)((CraftLivingEntity)player1).getHandle(), 0));
        }
        final ArmorStand armorStand1 = (ArmorStand)player1.getWorld().spawnEntity(throwLoc, EntityType.ARMOR_STAND);
        armorStand1.getEquipment().setItemInHand(SItem.of(SMaterial.IRON_SWORD).getStack());
        armorStand1.setGravity(false);
        armorStand1.setVisible(false);
        Player bukkitPlayer = player1.getPlayer();
        final Vector teleportTo = bukkitPlayer.getLocation().getDirection().normalize().multiply(1);
        final Vector[] previousVector = new Vector[]{throwVec};
        new BukkitRunnable(){
            private int run = -1;

            public void run() {
                boolean back;
                int angle;
                Vector newVector;
                int ran;
                int i = ran = 0;
                int num = 90;
                Object loc = null;
                ++this.run;
                if (this.run > 100) {
                    this.cancel();
                    return;
                }
                for (int j = 0; j < 7; ++j) {
                    armorStand1.getWorld().spigot().playEffect(armorStand1.getLocation().clone().add(0.0, 1.75, 0.0), Effect.CRIT, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(0.0, 0.5), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 100);
                }
                if (!armorStand1.getLocation().getBlock().getType().isTransparent() || armorStand1.isOnGround()) {
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
                double xPos = armorStand1.getRightArmPose().getX();
                armorStand1.setRightArmPose(new EulerAngle(xPos + 0.35, 0.0, 0.0));
                previousVector[0] = newVector = new Vector(throwVec.getX(), previousVector[0].getY() - 0.03, throwVec.getZ());
                armorStand1.setVelocity(newVector);
                if (i < 13) {
                    angle = i * 20 + num;
                    back = false;
                } else {
                    angle = i * 20 - num;
                    back = true;
                }
                if (!armorStand1.getLocation().getBlock().getType().isTransparent()) {
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
                
                for (Entity e : armorStand1.getNearbyEntities(0.7, 0.7, 0.7)) {
                    Damageable entity;
                    if (!(e instanceof LivingEntity) || e == player1.getPlayer() || (entity = (Damageable)e).isDead() || !(entity instanceof LivingEntity) || entity instanceof Player || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand || entity instanceof Item || entity instanceof ItemFrame || entity.hasMetadata("GiantSword") || Groups.ENDERMAN.contains(entity.getType())) continue;
                    User user = User.getUser(player1.getUniqueId());
                    Object[] atp = Sputnik.calculateDamage(player1, player1, sItem.getStack(), (LivingEntity)entity, false);
                    double finalDamage1 = ((Float)atp[0]).floatValue();
                    PlayerListener.spawnDamageInd((Entity)entity, ((Float)atp[2]).floatValue(), (Boolean)atp[1]);
                    FerocityCalculation.activeFerocityTimes(player1, (LivingEntity)entity, (int)finalDamage1, (Boolean)atp[1]);
                    user.damageEntity(entity, finalDamage1);
                    player1.playSound(player1.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                    player1.sendMessage(Sputnik.trans("&7Your Livid Dagger hit &c1 &7enemy for &c" + SUtil.commaify(new BigDecimal(finalDamage1).setScale(1, RoundingMode.HALF_EVEN).doubleValue()) + " &7damage."));
                    this.cancel();
                    armorStand1.remove();
                    break;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 1L, 1L);
        new BukkitRunnable(){

            public void run() {
                armorStand1.remove();
                this.cancel();
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 100L);
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 100;
    }

    @Override
    public int getManaCost() {
        return 150;
    }
}

