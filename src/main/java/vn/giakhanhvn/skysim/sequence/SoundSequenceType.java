/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.sequence;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.sequence.SoundSequence;

public class SoundSequenceType {
    private static final List<SoundSequenceType> TYPES = new ArrayList<SoundSequenceType>();
    public static final SoundSequenceType MADDOX_BATPHONE = new SoundSequenceType("maddox_batphone", new SoundSequence(new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 1L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 3L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 5L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 7L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 9L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 18L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 20L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 22L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 24L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 26L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 35L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 37L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 39L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 41L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 1.0f, 26.0f, 43L), new SoundSequence.DelayedSound(Sound.WOOD_CLICK, 1.0f, 1.0f, 52L)));
    public static final SoundSequenceType TRADE_COMPLETE = new SoundSequenceType("trade_completed", new SoundSequence(new SoundSequence.DelayedSound(Sound.NOTE_PLING, 0.3f, 0.5f, 1L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 0.3f, 0.69f, 1L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 0.3f, 0.84f, 1L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 0.3f, 1.12f, 3L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 0.3f, 0.55f, 3L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 0.3f, 0.69f, 3L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 0.3f, 0.84f, 5L), new SoundSequence.DelayedSound(Sound.NOTE_PLING, 0.3f, 1.12f, 5L)));
    public static final SoundSequenceType SLAYER_BOSS_SPAWN = new SoundSequenceType("slayer_boss_spawn", new SoundSequence(new SoundSequence.DelayedSound(Sound.WITHER_SHOOT, 1.0f, 9.0f, 1L), new SoundSequence.DelayedSound(Sound.WITHER_SHOOT, 1.0f, 9.0f, 4L), new SoundSequence.DelayedSound(Sound.WITHER_SHOOT, 1.0f, 5.0f, 7L), new SoundSequence.DelayedSound(Sound.WITHER_SHOOT, 1.0f, 5.0f, 10L), new SoundSequence.DelayedSound(Sound.WITHER_SHOOT, 1.0f, 5.0f, 13L), new SoundSequence.DelayedSound(Sound.WITHER_SHOOT, 1.0f, 5.0f, 16L), new SoundSequence.DelayedSound(Sound.WITHER_SHOOT, 1.0f, 1.0f, 19L), new SoundSequence.DelayedSound(Sound.WITHER_SHOOT, 1.0f, 1.0f, 22L), new SoundSequence.DelayedSound(Sound.WITHER_SHOOT, 1.0f, 1.0f, 25L), new SoundSequence.DelayedSound(Sound.WITHER_SPAWN, 1.0f, -25.0f, 28L), new SoundSequence.DelayedSound(Sound.EXPLODE, 1.0f, 1.0f, 28L)));
    public static final SoundSequenceType SLAYER_MINIBOSS_SPAWN = new SoundSequenceType("slayer_miniboss_spawn", new SoundSequence(new SoundSequence.DelayedSound(Sound.EXPLODE, 1.0f, 2.0f, 0L), new SoundSequence.DelayedSound(Sound.EXPLODE, 1.0f, 2.0f, 3L), new SoundSequence.DelayedSound(Sound.EXPLODE, 1.0f, 2.0f, 6L), new SoundSequence.DelayedSound(Sound.EXPLODE, 1.0f, 2.0f, 9L), new SoundSequence.DelayedSound(Sound.EXPLODE, 1.0f, 2.0f, 12L)));
    private final String namespace;
    private final SoundSequence sequence;

    public SoundSequenceType(String namespace, SoundSequence sequence) {
        this.namespace = namespace;
        this.sequence = sequence;
        TYPES.add(this);
    }

    public String getNamespace() {
        return this.namespace;
    }

    public SoundSequence getSequence() {
        return this.sequence;
    }

    public void play(Location location) {
        this.sequence.play(location);
    }

    public void play(Entity entity) {
        this.sequence.play(entity);
    }

    public void play(Player p) {
        this.sequence.play(p);
    }

    public static SoundSequenceType getByNamespace(String namespace) {
        for (SoundSequenceType type : TYPES) {
            if (!namespace.toLowerCase().equals(type.namespace.toLowerCase())) continue;
            return type;
        }
        return null;
    }
}

