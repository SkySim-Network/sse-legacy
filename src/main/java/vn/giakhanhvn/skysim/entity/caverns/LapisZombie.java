/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.entity.caverns;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.EntityStatistics;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;

public class LapisZombie
implements EntityFunction,
EntityStatistics {
    @Override
    public String getEntityName() {
        return "Lapis Zombie";
    }

    @Override
    public double getEntityMaxHealth() {
        return 200.0;
    }

    @Override
    public double getDamageDealt() {
        return 50.0;
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(null, new ItemStack(Material.STAINED_GLASS, 1, (short) 11), SItem.of(SMaterial.LAPIS_ARMOR_CHESTPLATE).getStack(), SItem.of(SMaterial.LAPIS_ARMOR_LEGGINGS).getStack(), SItem.of(SMaterial.LAPIS_ARMOR_BOOTS).getStack());
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(SMaterial.ROTTEN_FLESH, EntityDropType.GUARANTEED, 1.0), new EntityDrop(SMaterial.LAPIS_ARMOR_HELMET, EntityDropType.RARE, 0.05), new EntityDrop(SMaterial.LAPIS_ARMOR_CHESTPLATE, EntityDropType.RARE, 0.05), new EntityDrop(SMaterial.LAPIS_ARMOR_LEGGINGS, EntityDropType.RARE, 0.05), new EntityDrop(SMaterial.LAPIS_ARMOR_BOOTS, EntityDropType.RARE, 0.05));
    }

    @Override
    public double getXPDropped() {
        return 12.0;
    }
}

