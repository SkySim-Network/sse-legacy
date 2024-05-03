/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.xxmicloxx.NoteBlockAPI.model.Song
 *  com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer
 *  com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.util;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import java.io.File;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanHuman;

public class PlayJingle {
    public static void play(SkySimSong eff, byte volume, final Location loc, int radius) {
        Song song = NBSDecoder.parse((File)new File(SkySimEngine.getPlugin().getDataFolder() + File.separator + "/songs/dungeon_drama.nbs"));
        final PositionSongPlayer esp = new PositionSongPlayer(song);
        esp.setDistance(radius);
        esp.setVolume((byte)100);
        esp.setLoop(true);
        esp.setTargetLocation(loc);
        for (Player p : loc.getWorld().getPlayers()) {
            esp.addPlayer(p);
        }
        esp.setPlaying(true);
        new BukkitRunnable(){

            public void run() {
                if (loc.getWorld() == null) {
                    esp.destroy();
                    this.cancel();
                    return;
                }
                if (SadanHuman.IsMusicPlaying.containsKey(loc.getWorld().getUID()) && !SadanHuman.IsMusicPlaying.get(loc.getWorld().getUID()).booleanValue()) {
                    esp.destroy();
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    public static enum SkySimSoundEffect {
        DUNGEONS_WITHER_DOOR_OPEN,
        DUNGEONS_BLOOD_DOOR_OPEN,
        DUNGEONS_WATCHER_AMBIENT,
        DUNGEONS_WATCHER_SUMMON,
        DUNGEONS_WATCHER_HURT,
        LASER_SHOOT,
        LASER_AMBIENT;

    }

    public static enum SkySimSong {
        WIDE_IS_MY_MOTHERLAND,
        DUNGEONS_DRAMA,
        FLOOR7_NECRONTHEME,
        THE_WATCHER,
        SYSTEM_JINGLE,
        FESTIVAL_NEW_YEAR,
        FESTIVAL_LUNAR_NEW_YEAR,
        FESTIVAL_CHRISTMAS,
        FESTIVAL_VICTORY_DAY,
        FESTIVAL_SKYSIM_ANNIVERSARY_DAY,
        FESTIVAL_MAY_DAY;

    }
}

