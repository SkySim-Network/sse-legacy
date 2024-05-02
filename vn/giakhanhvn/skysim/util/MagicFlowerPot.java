/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.TileEntityFlowerPot
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.util;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.TileEntityFlowerPot;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public class MagicFlowerPot {
    public static final boolean changePot(Block flowerPot, boolean refreshChunk, ArmorStand s) {
        if (s.isDead()) {
            return false;
        }
        if (!s.isDead() && flowerPot.getType() == Material.FLOWER_POT) {
            try {
                ItemStack is = new ItemStack(Material.RED_ROSE);
                TileEntityFlowerPot tefp = (TileEntityFlowerPot)((CraftWorld)flowerPot.getWorld()).getHandle().getTileEntity(new BlockPosition(flowerPot.getX(), flowerPot.getY(), flowerPot.getZ()));
                tefp.a(CraftItemStack.asNMSCopy((ItemStack)is).getItem(), CraftItemStack.asNMSCopy((ItemStack)is).getData());
                tefp.update();
                ((CraftWorld)flowerPot.getWorld()).getHandle().notify(new BlockPosition(flowerPot.getX(), flowerPot.getY(), flowerPot.getZ()));
                if (refreshChunk) {
                    flowerPot.getWorld().refreshChunk(flowerPot.getChunk().getX(), flowerPot.getChunk().getZ());
                }
            }
            catch (Exception ex) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static final boolean changePotAir(Block flowerPot, boolean refreshChunk) {
        if (flowerPot.getType() == Material.FLOWER_POT) {
            try {
                ItemStack is = new ItemStack(Material.AIR);
                TileEntityFlowerPot tefp = (TileEntityFlowerPot)((CraftWorld)flowerPot.getWorld()).getHandle().getTileEntity(new BlockPosition(flowerPot.getX(), flowerPot.getY(), flowerPot.getZ()));
                tefp.a(CraftItemStack.asNMSCopy((ItemStack)is).getItem(), CraftItemStack.asNMSCopy((ItemStack)is).getData());
                tefp.update();
                ((CraftWorld)flowerPot.getWorld()).getHandle().notify(new BlockPosition(flowerPot.getX(), flowerPot.getY(), flowerPot.getZ()));
                if (refreshChunk) {
                    flowerPot.getWorld().refreshChunk(flowerPot.getChunk().getX(), flowerPot.getChunk().getZ());
                }
            }
            catch (Exception ex) {
                return false;
            }
            return true;
        }
        return false;
    }
}

