/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.entity.nether;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.SkeletonStatistics;
import vn.giakhanhvn.skysim.item.SMaterial;

public class WitherSkeleton
implements SkeletonStatistics,
EntityFunction {
    @Override
    public String getEntityName() {
        return "Wither Skeleton";
    }

    @Override
    public double getEntityMaxHealth() {
        return 250.0;
    }

    @Override
    public double getDamageDealt() {
        return 152.0;
    }

    @Override
    public boolean isWither() {
        return true;
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(new ItemStack(Material.BONE, 3), EntityDropType.GUARANTEED, 1.0), new EntityDrop(SMaterial.COAL, EntityDropType.COMMON, 0.5), new EntityDrop(SMaterial.ENCHANTED_COAL, EntityDropType.OCCASIONAL, 0.025), new EntityDrop(SMaterial.ENCHANTED_CHARCOAL, EntityDropType.RARE, 0.01));
    }

    @Override
    public double getXPDropped() {
        return 13.0;
    }
}

