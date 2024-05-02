/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.sk89q.worldedit.BlockVector
 *  com.sk89q.worldedit.EditSession
 *  com.sk89q.worldedit.LocalWorld
 *  com.sk89q.worldedit.MaxChangedBlocksException
 *  com.sk89q.worldedit.Vector
 *  com.sk89q.worldedit.WorldEdit
 *  com.sk89q.worldedit.blocks.BaseBlock
 *  com.sk89q.worldedit.bukkit.BukkitUtil
 *  com.sk89q.worldedit.regions.CuboidRegion
 *  com.sk89q.worldedit.regions.Region
 *  com.sk89q.worldedit.world.World
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 */
package vn.giakhanhvn.skysim.entity.dungeons.boss.sadan;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class AnimationSequence {
    public static void chainAnimation(org.bukkit.World w) {
        boolean chaining = false;
        if (w.getName().contains("f6")) {
            AnimationSequence.beginRenderDown(w);
            SUtil.delay(() -> AnimationSequence.beginRenderUp(w), 330L);
        }
    }

    public static void pasteBase(org.bukkit.World w, int delay) {
        SUtil.delay(() -> Sputnik.pasteSchematic("chain_base", true, 186.0f, 105.0f, 271.0f, w), delay - 1);
        SUtil.delay(() -> Sputnik.pasteSchematic("chain_base", true, 186.0f, 105.0f, 271.0f, w), delay);
        SUtil.delay(() -> Sputnik.pasteSchematic("chain_base", true, 186.0f, 105.0f, 271.0f, w), delay + 1);
    }

    public static void pasteGlass(org.bukkit.World w, int delay, int phase) {
        if (phase == 0) {
            SUtil.delay(() -> Sputnik.pasteSchematic("f6_h1", true, 189.0f, 71.0f, 266.0f, w), delay);
        } else if (phase == 1) {
            SUtil.delay(() -> Sputnik.pasteSchematic("f6_h2", true, 190.0f, 71.0f, 269.0f, w), delay);
        } else if (phase == 2) {
            SUtil.delay(() -> Sputnik.pasteSchematic("f6_h3", true, 191.0f, 73.0f, 266.0f, w), delay);
        }
    }

    public static void pasteChain(org.bukkit.World w, float y, int delay, boolean up) {
        SUtil.delay(() -> Sputnik.pasteSchematic("chain_main", true, 195.0f, y, 261.0f, w), delay);
        SUtil.delay(() -> w.playSound(new Location(w, 195.0, (double)y, 261.0), Sound.HORSE_ARMOR, 100.0f, 0.0f), delay);
    }

    public static void pasteAir(org.bukkit.World w, float y, int delay) {
        SUtil.delay(() -> AnimationSequence.edit(new Location(w, 191.0, (double)(y - 2.0f), 266.0), new Location(w, 191.0, 69.0, 266.0), w), delay + 1);
    }

    public static void beginRenderUp(org.bukkit.World w) {
        AnimationSequence.pasteChain(w, 69.0f, 1, false);
        AnimationSequence.pasteBase(w, 1);
        AnimationSequence.pasteChain(w, 71.0f, 20, false);
        AnimationSequence.pasteBase(w, 20);
        AnimationSequence.pasteAir(w, 71.0f, 20);
        AnimationSequence.pasteChain(w, 73.0f, 40, false);
        AnimationSequence.pasteBase(w, 40);
        AnimationSequence.pasteAir(w, 73.0f, 40);
        AnimationSequence.pasteChain(w, 75.0f, 60, false);
        AnimationSequence.pasteBase(w, 60);
        AnimationSequence.pasteAir(w, 75.0f, 60);
        AnimationSequence.pasteChain(w, 77.0f, 80, false);
        AnimationSequence.pasteBase(w, 80);
        AnimationSequence.pasteAir(w, 77.0f, 80);
        AnimationSequence.pasteChain(w, 79.0f, 100, false);
        AnimationSequence.pasteBase(w, 100);
        AnimationSequence.pasteAir(w, 79.0f, 100);
        AnimationSequence.pasteChain(w, 81.0f, 120, false);
        AnimationSequence.pasteBase(w, 120);
        AnimationSequence.pasteAir(w, 81.0f, 120);
        AnimationSequence.pasteChain(w, 83.0f, 140, false);
        AnimationSequence.pasteBase(w, 140);
        AnimationSequence.pasteGlass(w, 140, 0);
        AnimationSequence.pasteAir(w, 83.0f, 140);
        AnimationSequence.pasteChain(w, 85.0f, 160, false);
        AnimationSequence.pasteBase(w, 160);
        AnimationSequence.pasteAir(w, 85.0f, 160);
        AnimationSequence.pasteChain(w, 87.0f, 180, false);
        AnimationSequence.pasteBase(w, 180);
        AnimationSequence.pasteAir(w, 87.0f, 180);
        AnimationSequence.pasteChain(w, 89.0f, 200, false);
        AnimationSequence.pasteBase(w, 200);
        AnimationSequence.pasteAir(w, 89.0f, 200);
        AnimationSequence.pasteChain(w, 91.0f, 220, false);
        AnimationSequence.pasteBase(w, 220);
        AnimationSequence.pasteAir(w, 91.0f, 220);
        AnimationSequence.pasteChain(w, 93.0f, 240, false);
        AnimationSequence.pasteBase(w, 240);
        AnimationSequence.pasteAir(w, 93.0f, 240);
        AnimationSequence.pasteChain(w, 95.0f, 260, false);
        AnimationSequence.pasteBase(w, 260);
        AnimationSequence.pasteAir(w, 95.0f, 260);
        AnimationSequence.pasteChain(w, 97.0f, 280, false);
        AnimationSequence.pasteBase(w, 280);
        AnimationSequence.pasteAir(w, 97.0f, 280);
        AnimationSequence.pasteChain(w, 99.0f, 300, false);
        AnimationSequence.pasteBase(w, 300);
        AnimationSequence.pasteAir(w, 99.0f, 300);
        AnimationSequence.pasteBase(w, 301);
    }

    public static void beginRenderDown(org.bukkit.World w) {
        AnimationSequence.pasteChain(w, 99.0f, 1, false);
        AnimationSequence.pasteBase(w, 1);
        AnimationSequence.pasteChain(w, 97.0f, 20, false);
        AnimationSequence.pasteBase(w, 20);
        AnimationSequence.pasteChain(w, 95.0f, 40, false);
        AnimationSequence.pasteBase(w, 40);
        AnimationSequence.pasteChain(w, 93.0f, 60, false);
        AnimationSequence.pasteBase(w, 60);
        AnimationSequence.pasteChain(w, 91.0f, 80, false);
        AnimationSequence.pasteBase(w, 80);
        AnimationSequence.pasteChain(w, 89.0f, 110, false);
        AnimationSequence.pasteBase(w, 110);
        AnimationSequence.pasteChain(w, 87.0f, 130, false);
        AnimationSequence.pasteBase(w, 130);
        AnimationSequence.pasteChain(w, 85.0f, 150, false);
        AnimationSequence.pasteBase(w, 150);
        AnimationSequence.pasteChain(w, 83.0f, 170, false);
        AnimationSequence.pasteBase(w, 170);
        AnimationSequence.pasteChain(w, 81.0f, 180, false);
        AnimationSequence.pasteBase(w, 180);
        AnimationSequence.pasteChain(w, 79.0f, 200, false);
        AnimationSequence.pasteBase(w, 200);
        AnimationSequence.pasteChain(w, 77.0f, 220, false);
        AnimationSequence.pasteBase(w, 220);
        AnimationSequence.pasteChain(w, 75.0f, 240, false);
        AnimationSequence.pasteBase(w, 240);
        AnimationSequence.pasteChain(w, 73.0f, 260, false);
        AnimationSequence.pasteBase(w, 260);
        AnimationSequence.pasteChain(w, 71.0f, 280, false);
        AnimationSequence.pasteGlass(w, 280, 1);
        AnimationSequence.pasteBase(w, 280);
        AnimationSequence.pasteChain(w, 69.0f, 300, false);
        AnimationSequence.pasteBase(w, 300);
        AnimationSequence.pasteGlass(w, 300, 2);
        AnimationSequence.pasteBase(w, 301);
        SUtil.delay(() -> AnimationSequence.r(w), 301L);
        SUtil.delay(() -> new SEntity(new Location(w, 191.5, 54.0, 266.5, 180.0f, 0.0f), SEntityType.DUMMY_FUNCTION_2, new Object[0]), 300L);
    }

    public static void r(org.bukkit.World w) {
        for (Entity e1 : w.getEntities()) {
            if (!e1.hasMetadata("dummy_r")) continue;
            e1.remove();
        }
    }

    public static void edit(Location pos1, Location pos2, org.bukkit.World w) {
        LocalWorld world = BukkitUtil.getLocalWorld((org.bukkit.World)w);
        CuboidRegion selection = new CuboidRegion((World)world, (Vector)BlockVector.toBlockPoint((double)pos1.getX(), (double)pos1.getY(), (double)pos1.getZ()), (Vector)BlockVector.toBlockPoint((double)pos2.getX(), (double)pos2.getY(), (double)pos2.getZ()));
        EditSession e = WorldEdit.getInstance().getEditSessionFactory().getEditSession((World)world, -1);
        try {
            e.setBlocks((Region)selection, new BaseBlock(0));
        }
        catch (MaxChangedBlocksException e1) {
            e1.printStackTrace();
        }
    }
}

