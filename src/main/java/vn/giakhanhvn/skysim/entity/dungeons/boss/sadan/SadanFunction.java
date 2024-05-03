/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.inventory.EntityEquipment
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package vn.giakhanhvn.skysim.entity.dungeons.boss.sadan;

import java.util.Random;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.AnimationSequence;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanBossManager;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SadanFunction {
    public static String generateRandom() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = SUtil.random(5, 6);
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generatedString;
    }

    public static void jlp(World w) {
        SEntity se_jollypink = new SEntity(new Location(w, 183.5, 84.0, 253.5, -45.0f, 0.0f), SEntityType.GIANT_DUMMY, new Object[0]);
        LivingEntity e = se_jollypink.getEntity();
        EntityEquipment eq = e.getEquipment();
        eq.setItemInHand(null);
        eq.setHelmet(SadanFunction.b(14751108, Material.LEATHER_HELMET));
        eq.setChestplate(SadanFunction.b(14751108, Material.LEATHER_CHESTPLATE));
        eq.setLeggings(SadanFunction.b(14751108, Material.LEATHER_LEGGINGS));
        eq.setBoots(SadanFunction.b(14751108, Material.LEATHER_BOOTS));
        e.setMetadata("JollyPink", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
    }

    public static void lasrg(World w) {
        SEntity se_jollypink = new SEntity(new Location(w, 199.5, 84.0, 253.5, 45.0f, 0.0f), SEntityType.GIANT_DUMMY, new Object[0]);
        LivingEntity e = se_jollypink.getEntity();
        EntityEquipment eq = e.getEquipment();
        eq.setItemInHand(null);
        eq.setHelmet(SadanFunction.b(12228503, Material.LEATHER_HELMET));
        eq.setChestplate(SadanFunction.b(12228503, Material.LEATHER_CHESTPLATE));
        eq.setLeggings(SadanFunction.b(12228503, Material.LEATHER_LEGGINGS));
        eq.setBoots(SadanFunction.b(12228503, Material.LEATHER_BOOTS));
        e.setMetadata("LASR", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
    }

    public static void diag(World w) {
        SEntity se_jollypink = new SEntity(new Location(w, 183.5, 84.0, 279.5, -135.0f, 0.0f), SEntityType.GIANT_DUMMY, new Object[0]);
        LivingEntity e = se_jollypink.getEntity();
        EntityEquipment eq = e.getEquipment();
        eq.setItemInHand(SUtil.enchant(SadanFunction.c(Material.DIAMOND_SWORD)));
        eq.setHelmet(SadanFunction.c(Material.DIAMOND_HELMET));
        eq.setChestplate(SadanFunction.c(Material.DIAMOND_CHESTPLATE));
        eq.setLeggings(SadanFunction.c(Material.DIAMOND_LEGGINGS));
        eq.setBoots(SadanFunction.c(Material.DIAMOND_BOOTS));
        e.setMetadata("Diamond", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
    }

    public static void bfg(World w) {
        SEntity se_jollypink = new SEntity(new Location(w, 199.5, 84.0, 279.5, 135.0f, 0.0f), SEntityType.GIANT_DUMMY, new Object[0]);
        LivingEntity e = se_jollypink.getEntity();
        EntityEquipment eq = e.getEquipment();
        eq.setItemInHand(null);
        eq.setHelmet(null);
        eq.setChestplate(null);
        eq.setLeggings(null);
        eq.setBoots(SadanFunction.b(8991025, Material.LEATHER_BOOTS));
        e.setMetadata("Bigfoot", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
    }

    public static ItemStack buildColorStack(int hexcolor) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_HELMET), Color.fromRGB((int)hexcolor));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static ItemStack b(int hexcolor, Material m) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(m), Color.fromRGB((int)hexcolor));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static ItemStack c(Material m) {
        ItemStack stack = new ItemStack(m);
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static void endPhase1(World w) {
        for (Entity e : w.getEntities()) {
            if (e.hasMetadata("ftd")) {
                e.remove();
            }
            if (!e.hasMetadata("t_sadan")) continue;
            e.remove();
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
            e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION, 10);
        }
    }

    public static void release(World w) {
        SUtil.delay(() -> SadanFunction.rjp(w), 10L);
        SUtil.delay(() -> SadanFunction.rdia(w), 30L);
        SUtil.delay(() -> SadanFunction.rbf(w), 50L);
        SUtil.delay(() -> SadanFunction.rlasr(w), 70L);
    }

    public static void rjp(World w) {
        for (Entity e : w.getEntities()) {
            if (!e.hasMetadata("JollyPink")) continue;
            e.remove();
            new SEntity(new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), e.getLocation().getYaw(), 0.0f), SEntityType.JOLLY_PINK_SADAN, new Object[0]);
            w.strikeLightningEffect(e.getLocation());
            break;
        }
        AnimationSequence.edit(new Location(w, 187.0, 81.0, 257.0), new Location(w, 178.0, 101.0, 248.0), w);
    }

    public static void rdia(World w) {
        for (Entity e : w.getEntities()) {
            if (!e.hasMetadata("Diamond")) continue;
            e.remove();
            new SEntity(new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), e.getLocation().getYaw(), 0.0f), SEntityType.DIAMOND_SADAN, new Object[0]);
            w.strikeLightningEffect(e.getLocation());
            break;
        }
        AnimationSequence.edit(new Location(w, 187.0, 82.0, 275.0), new Location(w, 178.0, 101.0, 284.0), w);
    }

    public static void rbf(World w) {
        for (Entity e : w.getEntities()) {
            if (!e.hasMetadata("Bigfoot")) continue;
            e.remove();
            new SEntity(new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), e.getLocation().getYaw(), 0.0f), SEntityType.BIGFOOT_SADAN, new Object[0]);
            w.strikeLightningEffect(e.getLocation());
            break;
        }
        AnimationSequence.edit(new Location(w, 194.0, 82.0, 283.0), new Location(w, 203.0, 101.0, 275.0), w);
    }

    public static void rlasr(World w) {
        for (Entity e : w.getEntities()) {
            if (!e.hasMetadata("LASR")) continue;
            e.remove();
            new SEntity(new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), e.getLocation().getYaw(), 0.0f), SEntityType.LASR_SADAN, new Object[0]);
            w.strikeLightningEffect(e.getLocation());
            break;
        }
        AnimationSequence.edit(new Location(w, 203.0, 101.0, 248.0), new Location(w, 195.0, 81.0, 257.0), w);
    }

    public static void a(World w, double x, double z, float yaw) {
        new SEntity(new Location(w, x += 0.5, 69.0, z += 0.5, yaw, 0.0f), SEntityType.TERRACOTTA_DUMMY, new Object[0]);
    }

    public static void aA(World w, double x, double z, float yaw) {
        new SEntity(new Location(w, x += 0.5, 69.0, z += 0.5, yaw, 0.0f), SEntityType.SLEEPING_GOLEM, new Object[0]);
    }

    public static void b(World w) {
        new SEntity(new Location(w, 191.5, 54.0, 266.5, 180.0f, 0.0f), SEntityType.DUMMY_SADAN_1, new Object[0]);
    }

    public static void s_(Entity e) {
        World w = e.getWorld();
        SadanFunction.lasrg(w);
        SadanFunction.diag(w);
        SadanFunction.bfg(w);
        SadanFunction.jlp(w);
        SadanFunction.aA(w, 184.0, 252.0, -45.0f);
        SadanFunction.aA(w, 198.0, 252.0, 45.0f);
        SadanFunction.aA(w, 204.0, 266.0, 90.0f);
        SadanFunction.aA(w, 184.0, 280.0, -135.0f);
        SadanFunction.aA(w, 178.0, 266.0, -90.0f);
        SadanFunction.aA(w, 198.0, 280.0, 135.0f);
        SadanFunction.a(w, 194.0, 295.0, 90.0f);
        SadanFunction.a(w, 188.0, 295.0, -90.0f);
        SadanFunction.a(w, 194.0, 290.0, 90.0f);
        SadanFunction.a(w, 188.0, 290.0, -90.0f);
        SadanFunction.a(w, 194.0, 285.0, 90.0f);
        SadanFunction.a(w, 188.0, 285.0, -90.0f);
        SadanFunction.a(w, 194.0, 280.0, 90.0f);
        SadanFunction.a(w, 188.0, 280.0, -90.0f);
        SadanFunction.a(w, 194.0, 275.0, 90.0f);
        SadanFunction.a(w, 188.0, 275.0, -90.0f);
        SadanFunction.b(w);
        SadanFunction.a(w, 194.0, 257.0, 90.0f);
        SadanFunction.a(w, 188.0, 257.0, -90.0f);
        SadanFunction.a(w, 194.0, 252.0, 90.0f);
        SadanFunction.a(w, 188.0, 252.0, -90.0f);
        SadanFunction.a(w, 194.0, 248.0, 90.0f);
        SadanFunction.a(w, 188.0, 248.0, -90.0f);
        SadanFunction.a(w, 194.0, 243.0, 90.0f);
        SadanFunction.a(w, 188.0, 243.0, -90.0f);
        SadanFunction.a(w, 194.0, 238.0, 90.0f);
        SadanFunction.a(w, 188.0, 238.0, -90.0f);
    }

    public static Integer dmgc(int damage, Player p, Entity e) {
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(p.getUniqueId());
        if (statistics == null) {
            return 0;
        }
        double defense = statistics.getDefense().addAll();
        int dmglater = (int)Math.round((double)damage - (double)damage * (defense / (defense + 100.0)));
        User.getUser(p.getUniqueId()).damage(dmglater, EntityDamageEvent.DamageCause.ENTITY_ATTACK, e);
        p.damage(1.0E-6, null);
        return dmglater;
    }

    public static void roomLoop(World w) {
    }

    public static void endRoom1(World w) {
        if (w.getName().contains("f6")) {
            SUtil.broadcastWorld(Sputnik.trans("&c&lSKYSIM MC >> &e&lThis demo floor currently in development so you can't respawn or get reward, sorry! We will update later on, thanks for playing, leave rating on #server-rating"), w);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c30s"), w), 600L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c20s"), w), 1000L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c10s"), w), 1200L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c5s"), w), 1300L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c4s"), w), 1320L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c3s"), w), 1340L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c2s"), w), 1360L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c1s"), w), 1380L);
            SUtil.delay(() -> SadanBossManager.endFloor(w), 1400L);
        }
    }

    public static void sendReMsg(boolean finishornot, World w) {
        if (w.getName().contains("f6") && Repeater.FloorLivingSec.containsKey(w.getUID())) {
            if (finishornot) {
                int bitsReward = Math.round((600 - Math.min(600, Repeater.FloorLivingSec.get(w.getUID()))) * 150 / 255);
                String rew = "&b+" + SUtil.commaify(bitsReward) + " Bits &7(Completion Reward)";
                if (bitsReward <= 0) {
                    rew = "&cYou have no rewards!";
                } else {
                    w.getPlayers().forEach(p -> User.of(p).addBits(bitsReward));
                }
                SUtil.broadcastWorld(Sputnik.trans("&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"), w);
                SUtil.broadcastWorld(Sputnik.trans("        &cThe Catacombs Demo &8- &eFloor VI"), w);
                SUtil.broadcastWorld(Sputnik.trans("&c"), w);
                SUtil.broadcastWorld(Sputnik.trans("        &c☠&e Defeated &cSadan &ein &a" + Sputnik.formatTime(Repeater.FloorLivingSec.get(w.getUID()))), w);
                SUtil.broadcastWorld(Sputnik.trans("&c"), w);
                SUtil.broadcastWorld(Sputnik.trans("            " + rew), w);
                SUtil.broadcastWorld(Sputnik.trans("&c"), w);
                SUtil.broadcastWorld(Sputnik.trans("&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"), w);
            } else {
                SUtil.broadcastWorld(Sputnik.trans("&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"), w);
                SUtil.broadcastWorld(Sputnik.trans("        &cThe Catacombs Demo &8- &eFloor VI"), w);
                SUtil.broadcastWorld(Sputnik.trans("&c"), w);
                SUtil.broadcastWorld(Sputnik.trans("        &c☠&e You died, but you can try again!"), w);
                SUtil.broadcastWorld(Sputnik.trans("&c"), w);
                SUtil.broadcastWorld(Sputnik.trans("           &cYou have no rewards cause you died."), w);
                SUtil.broadcastWorld(Sputnik.trans("&c"), w);
                SUtil.broadcastWorld(Sputnik.trans("&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"), w);
            }
        }
    }

    public static void endRoom2(World w) {
        if (w.getName().contains("f6")) {
            SUtil.broadcastWorld(Sputnik.trans("&e"), w);
            SUtil.broadcastWorld(Sputnik.trans("&aThis demo floor currently in development so you can't respawn or get reward yet, sorry! We will update later on, leave rating of this boss on #server-rating, thank you."), w);
            SUtil.broadcastWorld(Sputnik.trans("&e"), w);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c5s"), w), 200L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c4s"), w), 220L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c3s"), w), 240L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c2s"), w), 260L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eThis dungeon will close in &c1s"), w), 280L);
            SUtil.delay(() -> SUtil.broadcastWorld(Sputnik.trans("&c[Warning] &eWarping you back to the Hub"), w), 300L);
            SUtil.delay(() -> SadanBossManager.endFloor(w), 300L);
        }
    }
}

