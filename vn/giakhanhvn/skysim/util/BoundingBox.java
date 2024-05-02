/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.AxisAlignedBB
 *  net.minecraft.server.v1_8_R3.Block
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.IBlockAccess
 *  net.minecraft.server.v1_8_R3.IBlockData
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.util;

import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockAccess;
import net.minecraft.server.v1_8_R3.IBlockData;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.util.Vector;

public class BoundingBox {
    Vector max;
    Vector min;

    BoundingBox(Vector min, Vector max) {
        this.max = max;
        this.min = min;
    }

    BoundingBox(Block block) {
        IBlockData blockData = ((CraftWorld)block.getWorld()).getHandle().getType(new BlockPosition(block.getX(), block.getY(), block.getZ()));
        net.minecraft.server.v1_8_R3.Block blockNative = blockData.getBlock();
        blockNative.updateShape((IBlockAccess)((CraftWorld)block.getWorld()).getHandle(), new BlockPosition(block.getX(), block.getY(), block.getZ()));
        this.min = new Vector((double)block.getX() + blockNative.B(), (double)block.getY() + blockNative.D(), (double)block.getZ() + blockNative.F());
        this.max = new Vector((double)block.getX() + blockNative.C(), (double)block.getY() + blockNative.E(), (double)block.getZ() + blockNative.G());
    }

    BoundingBox(AxisAlignedBB bb) {
        this.min = new Vector(bb.a, bb.b, bb.c);
        this.max = new Vector(bb.d, bb.e, bb.f);
    }

    public Vector midPoint() {
        return this.max.clone().add(this.min).multiply(0.5);
    }
}

