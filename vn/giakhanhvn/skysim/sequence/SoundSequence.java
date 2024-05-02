/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.sequence;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.sequence.Sequence;

public class SoundSequence
implements Sequence {
    private final List<DelayedSound> sounds;

    public SoundSequence(DelayedSound ... sounds) {
        this.sounds = Arrays.asList(sounds);
    }

    public void append(DelayedSound sound) {
        this.sounds.add(sound);
    }

    @Override
    public void play(Location location) {
        for (DelayedSound sound : this.sounds) {
            sound.play(location);
        }
    }

    public void play(Player p) {
        for (DelayedSound sound : this.sounds) {
            sound.play(p);
        }
    }

    @Override
    public void play(Entity entity) {
        this.play(entity.getLocation());
    }

    public static class DelayedSound {
        private final Sound sound;
        private final float volume;
        private final float pitch;
        private final long delay;

        public DelayedSound(Sound sound, float volume, float pitch, long delay) {
            this.sound = sound;
            this.volume = volume;
            this.pitch = pitch;
            this.delay = delay;
        }

        public DelayedSound(Sound sound, float volume, float pitch) {
            this(sound, volume, pitch, 0L);
        }

        public void play(final Location location) {
            new BukkitRunnable(){

                public void run() {
                    location.getWorld().playSound(location, sound, volume, pitch);
                }
            }.runTaskLater((Plugin)SkySimEngine.getPlugin(), this.delay);
        }

        public void play(final Player entity) {
            new BukkitRunnable(){

                public void run() {
                    entity.playSound(entity.getLocation(), sound, volume, pitch);
                }
            }.runTaskLater((Plugin)SkySimEngine.getPlugin(), this.delay);
        }
    }
}

