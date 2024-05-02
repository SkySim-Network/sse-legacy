/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.dimoon;

import java.util.List;
import java.util.UUID;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.dimoon.Dimoon;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.BlockFallAPI;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SummoningSequence {
    public UUID[] __qch__ = new UUID[8];
    double[] di = new double[]{234735.5, 191.0, 236483.5};
    double[] em = new double[]{234666.5, 191.0, 236549.5};
    double[] gl = new double[]{234600.5, 191.0, 236480.5};
    double[] rb = new double[]{234672.5, 191.0, 236414.5};
    public static double[] wl = new double[]{234668.5, 154.0, 236481.5};
    private SkySimEngine plugin = SkySimEngine.getPlugin();
    private boolean acE = false;
    private boolean acD = false;
    private boolean acR = false;
    private boolean acG = false;
    private boolean bossSpawning = false;
    private boolean bossSpawned = false;
    private World w;

    public SummoningSequence(final World w) {
        this.w = w;
        new BukkitRunnable(){

            public void run() {
                if (SummoningSequence.this.bossSpawned) {
                    this.cancel();
                    return;
                }
                if (SummoningSequence.this.acE && SummoningSequence.this.acR && SummoningSequence.this.acD && SummoningSequence.this.acG && !SummoningSequence.this.bossSpawning) {
                    SummoningSequence.this.bossSpawning = true;
                    SummoningSequence.this.play();
                }
                if (SummoningSequence.this.acE) {
                    SummoningSequence.this.gemParticle(new Location(w, SummoningSequence.this.em[0], SummoningSequence.this.em[1] + 1.0, SummoningSequence.this.em[2]), 74.0f, 209.0f, 29.0f);
                }
                if (SummoningSequence.this.acR) {
                    SummoningSequence.this.gemParticle(new Location(w, SummoningSequence.this.rb[0], SummoningSequence.this.rb[1] + 1.0, SummoningSequence.this.rb[2]), 212.0f, 46.0f, 38.0f);
                }
                if (SummoningSequence.this.acG) {
                    SummoningSequence.this.gemParticle(new Location(w, SummoningSequence.this.gl[0], SummoningSequence.this.gl[1] + 1.0, SummoningSequence.this.gl[2]), 222.0f, 205.0f, 58.0f);
                }
                if (SummoningSequence.this.acD) {
                    SummoningSequence.this.gemParticle(new Location(w, SummoningSequence.this.di[0], SummoningSequence.this.di[1] + 1.0, SummoningSequence.this.di[2]), 58.0f, 222.0f, 216.0f);
                }
            }
        }.runTaskTimer((Plugin)this.plugin, 0L, 1L);
        new BukkitRunnable(){

            public void run() {
                if (SummoningSequence.this.bossSpawned) {
                    this.cancel();
                    return;
                }
                if (SummoningSequence.this.acE) {
                    SummoningSequence.this.activeBeam(new Location(w, SummoningSequence.this.em[0], SummoningSequence.this.em[1] + 1.0, SummoningSequence.this.em[2]), new Location(w, wl[0], wl[1] + 1.0, wl[2]), 74.0f, 209.0f, 29.0f);
                }
                if (SummoningSequence.this.acR) {
                    SummoningSequence.this.activeBeam(new Location(w, SummoningSequence.this.rb[0], SummoningSequence.this.rb[1] + 1.0, SummoningSequence.this.rb[2]), new Location(w, wl[0], wl[1] + 1.0, wl[2]), 212.0f, 46.0f, 38.0f);
                }
                if (SummoningSequence.this.acG) {
                    SummoningSequence.this.activeBeam(new Location(w, SummoningSequence.this.gl[0], SummoningSequence.this.gl[1] + 1.0, SummoningSequence.this.gl[2]), new Location(w, wl[0], wl[1] + 1.0, wl[2]), 222.0f, 205.0f, 58.0f);
                }
                if (SummoningSequence.this.acD) {
                    SummoningSequence.this.activeBeam(new Location(w, SummoningSequence.this.di[0], SummoningSequence.this.di[1] + 1.0, SummoningSequence.this.di[2]), new Location(w, wl[0], wl[1] + 1.0, wl[2]), 58.0f, 222.0f, 216.0f);
                }
            }
        }.runTaskTimerAsynchronously((Plugin)this.plugin, 0L, 6L);
    }

    void play() {
        SUtil.broadcastWorld(Sputnik.trans("&b\u272c&c\u272c&e\u272c&a\u272c &cAll the crystals have been activated, Dimoon's &4cursed seal &cis slowly tearing down, Brace yourselves!"), this.w);
        new Location(this.w, wl[0], wl[1] + 1.0, wl[2]).getWorld().playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_SPAWN, 50.0f, 0.7f);
        SUtil.delay(() -> {
            new Location(this.w, wl[0], wl[1] + 1.0, wl[2]).getWorld().playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ENDERDRAGON_DEATH, 50.0f, -2.0f);
            SUtil.delay(() -> {
                this.playBossSpawnAnimation();
                this.w.strikeLightningEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]));
            }, 90L);
        }, 40L);
        new BukkitRunnable(){
            int i = 0;

            public void run() {
                ++this.i;
                if (this.i > 0) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 40; ++i) {
                    SummoningSequence.this.w.spigot().playEffect(new Location(SummoningSequence.this.w, wl[0], wl[1] + 1.0, wl[2]).clone().add(SUtil.random(-0.5, 0.5), SUtil.random(0.0, 1.0), SUtil.random(-0.5, 0.5)), Effect.LARGE_SMOKE, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
                    SummoningSequence.this.w.spigot().playEffect(new Location(SummoningSequence.this.w, wl[0], wl[1] + 1.0, wl[2]).clone().add(SUtil.random(-0.5, 0.5), SUtil.random(0.0, 1.0), SUtil.random(-0.5, 0.5)), Effect.POTION_SWIRL, 0, 1, 0.68235296f, 0.1882353f, 0.6901961f, 1.0f, 0, 640);
                }
            }
        }.runTaskTimerAsynchronously((Plugin)this.plugin, 0L, 1L);
    }

    void playBossSpawnAnimation() {
        this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
        this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
        this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
        this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
        new BukkitRunnable(){

            public void run() {
                if (!SummoningSequence.this.bossSpawning) {
                    this.cancel();
                    return;
                }
                Location startPoint = new Location(SummoningSequence.this.w, wl[0] - 2.5, wl[1] + 1.0, wl[2] - 2.52);
                for (int i = 0; i < 30; ++i) {
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add((double)SUtil.random(-5, 5), (double)SUtil.random(0, 5), (double)SUtil.random(-5, 5)), Effect.COLOURED_DUST, 0, 1, 0.2901961f, 0.81960785f, 0.11372549f, 1.0f, 0, 640);
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add((double)SUtil.random(-5, 5), (double)SUtil.random(0, 5), (double)SUtil.random(-5, 5)), Effect.COLOURED_DUST, 0, 1, 0.83137256f, 0.18039216f, 0.14901961f, 1.0f, 0, 640);
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add((double)SUtil.random(-5, 5), (double)SUtil.random(0, 5), (double)SUtil.random(-5, 5)), Effect.COLOURED_DUST, 0, 1, 0.87058824f, 0.8039216f, 0.22745098f, 1.0f, 0, 640);
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add((double)SUtil.random(-5, 5), (double)SUtil.random(0, 5), (double)SUtil.random(-5, 5)), Effect.COLOURED_DUST, 0, 1, 0.22745098f, 0.87058824f, 0.84705883f, 1.0f, 0, 640);
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add((double)SUtil.random(-5, 5), (double)SUtil.random(0, 5), (double)SUtil.random(-5, 5)), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                }
            }
        }.runTaskTimer((Plugin)this.plugin, 0L, 15L);
        SUtil.delay(() -> {
            Sputnik.pasteSchematicRep("egg1", true, 234666.0f, 155.0f, 236479.0f, this.w);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_DEATH, 10.0f, 2.0f);
        }, 10L);
        SUtil.delay(() -> {
            Sputnik.pasteSchematicRep("egg2", true, 234666.0f, 155.0f, 236479.0f, this.w);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_DEATH, 10.0f, 2.0f);
        }, 110L);
        SUtil.delay(() -> {
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_DEATH, 10.0f, 2.0f);
            List<Block> b = Sputnik.pasteSchematicRep("egg3", true, 234666.0f, 155.0f, 236479.0f, this.w);
            SUtil.delay(() -> {
                this.plugin.sq.bossSpawned = true;
            }, 50L);
            SUtil.delay(() -> {
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_DEATH, 10.0f, 2.0f);
                this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
                for (int i = 0; i < b.size(); ++i) {
                    int random = SUtil.random(0, 3);
                    double range = 0.0;
                    Location loc = new Location(this.w, wl[0], wl[1] + 1.0, wl[2]);
                    loc.setYaw((float)SUtil.random(0, 360));
                    if (random == 1) {
                        range = 0.8;
                    }
                    if (random == 2) {
                        range = 0.9;
                    }
                    if (random == 3) {
                        range = 1.1;
                    }
                    Vector vec = loc.getDirection().normalize().multiply(range);
                    vec.setY(0.9);
                    int j = i;
                    Location bloc = ((Block)b.get(j)).getLocation();
                    Material type = ((Block)b.get(j)).getType();
                    byte data = ((Block)b.get(j)).getData();
                    SUtil.delay(() -> BlockFallAPI.sendVelocityBlock(bloc, type, data, this.w, 60, vec), 1L);
                    ((Block)b.get(i)).setType(Material.AIR);
                }
                SUtil.delay(() -> {
                    this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_WOODBREAK, 10.0f, 0.5f);
                    this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_SPAWN, 10.0f, 1.2f);
                    Dimoon.spawnDimoon();
                    this.plugin.sq.bossSpawned = true;
                    this.plugin.sq.bossSpawning = false;
                }, 1L);
            }, 100L);
        }, 210L);
    }

    void activeBeam(Location startPoint, Location endPoint, float r, float g, float b) {
        Location blockLocation = endPoint;
        Location crystalLocation = startPoint;
        Vector vector = blockLocation.clone().toVector().subtract(crystalLocation.clone().toVector());
        int count = 120;
        for (int i = 1; i <= count; ++i) {
            startPoint.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / (double)count)), Effect.COLOURED_DUST, 0, 1, r / 255.0f, g / 255.0f, b / 255.0f, 1.0f, 0, 640);
            startPoint.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / (double)count)), Effect.COLOURED_DUST, 0, 1, r / 255.0f, g / 255.0f, b / 255.0f, 1.0f, 0, 640);
        }
    }

    void gemParticle(Location startPoint, float r, float g, float b) {
        startPoint.add(0.0, -3.0, 0.0);
        for (int i = 0; i < 30; ++i) {
            startPoint.getWorld().spigot().playEffect(startPoint.clone().add(SUtil.random(-2.5, 2.5), (double)SUtil.random(-6, 3), SUtil.random(-2.5, 2.5)), Effect.COLOURED_DUST, 0, 1, r / 255.0f, g / 255.0f, b / 255.0f, 1.0f, 0, 640);
        }
    }

    void checkSlots() {
        if (this.acD != (this.__qch__[0] != null && this.__qch__[1] != null)) {
            if (!this.acD) {
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_UNFECT, 10.0f, 1.2f);
                SUtil.broadcastWorld(Sputnik.trans("&b\u272c Sapphire Crystal &ehave been empowered and activated!"), this.w);
            } else {
                SUtil.broadcastWorld(Sputnik.trans("&b\u272c Sapphire Crystal &ede-activated!"), this.w);
            }
        }
        if (this.acR != (this.__qch__[6] != null && this.__qch__[7] != null)) {
            if (!this.acR) {
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_UNFECT, 10.0f, 1.2f);
                SUtil.broadcastWorld(Sputnik.trans("&c\u272c Ruby Crystal &ehave been empowered and activated!"), this.w);
            } else {
                SUtil.broadcastWorld(Sputnik.trans("&c\u272c Ruby Crystal &ede-activated!"), this.w);
            }
        }
        if (this.acE != (this.__qch__[2] != null && this.__qch__[3] != null)) {
            if (!this.acE) {
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_UNFECT, 10.0f, 1.2f);
                SUtil.broadcastWorld(Sputnik.trans("&a\u272c Jade Crystal &ehave been empowered and activated!"), this.w);
            } else {
                SUtil.broadcastWorld(Sputnik.trans("&a\u272c Jade Crystal &ede-activated!"), this.w);
            }
        }
        if (this.acG != (this.__qch__[4] != null && this.__qch__[5] != null)) {
            if (!this.acG) {
                SUtil.broadcastWorld(Sputnik.trans("&6\u272c Topaz Crystal &ehave been empowered and activated!"), this.w);
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_UNFECT, 10.0f, 1.2f);
            } else {
                SUtil.broadcastWorld(Sputnik.trans("&6\u272c Topaz Crystal &ede-activated!"), this.w);
            }
        }
        this.acD = this.__qch__[0] != null && this.__qch__[1] != null;
        this.acE = this.__qch__[2] != null && this.__qch__[3] != null;
        this.acG = this.__qch__[4] != null && this.__qch__[5] != null;
        this.acR = this.__qch__[6] != null && this.__qch__[7] != null;
    }

    public void interactCatalyst(Player p, int slot, boolean rightClick) {
        if (this.haveCatal(p) && SkySimEngine.getPlugin().altarCooldown) {
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 2.0f);
            p.sendMessage(Sputnik.trans("&cYou cannot use the altar right now! Let it cool down for a while!"));
            return;
        }
        if (this.bossSpawning || this.bossSpawned) {
            if (this.__qch__[slot] == p.getUniqueId()) {
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 0.0f);
                p.sendMessage(Sputnik.trans("&cYou cannot recover your &aCatalyst&c once the boss spawned!"));
            }
            return;
        }
        if (!this.bossSpawning && !this.bossSpawned && slot != -1) {
            this.checkSlots();
            if (this.haveCatal(p) && this.__qch__[slot] == null) {
                this.__qch__[slot] = p.getUniqueId();
                p.setItemInHand(null);
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 2.0f);
                p.sendMessage(Sputnik.trans("&4\u262c &cYou placed a &aCatalyst&c!"));
                SUtil.broadcastWorld(Sputnik.trans("&4\u262c &b" + p.getName() + " &cplaced a Catalyst! &7(&e" + this.catalystInTheAltar() + "&7/&a8&7)"), p.getWorld());
                this.checkSlots();
            } else if (this.haveCatal(p) && this.__qch__[slot] != null) {
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 0.0f);
                p.sendMessage(Sputnik.trans("&cThis slot is already occupied!"));
            } else if (this.__qch__[slot] != null) {
                this.checkSlots();
                if (this.__qch__[slot] == p.getUniqueId()) {
                    this.__qch__[slot] = null;
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 2.0f);
                    p.sendMessage(Sputnik.trans("&4\u262c &cYou recovered back your &aCatalyst&7!"));
                    Sputnik.smartGiveItem(SItem.of(SMaterial.HIDDEN_DIMOON_GEM).getStack(), p);
                    this.countDown(p);
                    this.checkSlots();
                } else {
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 0.0f);
                    p.sendMessage(Sputnik.trans("&cThis is not your &aCatalyst&c, duh."));
                }
                this.checkSlots();
            }
        }
    }

    public void pickupAllCatalysts(Player p) {
        for (int i = 0; i < this.__qch__.length; ++i) {
            if (this.__qch__[i] != p.getUniqueId()) continue;
            this.__qch__[i] = null;
            Sputnik.smartGiveItem(SItem.of(SMaterial.HIDDEN_DIMOON_GEM).getStack(), p);
        }
        this.checkSlots();
    }

    public int catalystInTheAltar() {
        int j = 0;
        for (int i = 0; i < this.__qch__.length; ++i) {
            j += this.__qch__[i] != null ? 1 : 0;
        }
        return j;
    }

    boolean haveCatal(Player p) {
        if (this.getItemInHand(p) == null) {
            return false;
        }
        return this.getItemInHand(p).getType() == SMaterial.HIDDEN_DIMOON_GEM;
    }

    SItem getItemInHand(Player p) {
        return SItem.find(p.getItemInHand());
    }

    void countDown(final Player p) {
        User.getUser(p.getUniqueId()).setCooldownAltar(15);
        new BukkitRunnable(){

            public void run() {
                if (User.getUser(p.getUniqueId()).getCooldownAltar() <= 0) {
                    User.getUser(p.getUniqueId()).setCooldownAltar(0);
                    this.cancel();
                    return;
                }
                User.getUser(p.getUniqueId()).setCooldownAltar(User.getUser(p.getUniqueId()).getCooldownAltar() - 1);
            }
        }.runTaskTimerAsynchronously((Plugin)this.plugin, 0L, 20L);
    }

    public void setAcE(boolean acE) {
        this.acE = acE;
    }

    public boolean isAcE() {
        return this.acE;
    }

    public void setAcD(boolean acD) {
        this.acD = acD;
    }

    public boolean isAcD() {
        return this.acD;
    }

    public void setAcR(boolean acR) {
        this.acR = acR;
    }

    public boolean isAcR() {
        return this.acR;
    }

    public void setAcG(boolean acG) {
        this.acG = acG;
    }

    public boolean isAcG() {
        return this.acG;
    }

    public void setBossSpawning(boolean bossSpawning) {
        this.bossSpawning = bossSpawning;
    }

    public boolean isBossSpawning() {
        return this.bossSpawning;
    }

    public void setBossSpawned(boolean bossSpawned) {
        this.bossSpawned = bossSpawned;
    }

    public boolean isBossSpawned() {
        return this.bossSpawned;
    }
}

