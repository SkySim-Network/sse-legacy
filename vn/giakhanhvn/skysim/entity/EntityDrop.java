/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.entity;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;

public class EntityDrop {
    private final ItemStack stack;
    private final EntityDropType type;
    private final double dropChance;
    private final Player owner;

    public EntityDrop(ItemStack stack, EntityDropType type, double dropChance, Player owner) {
        this.stack = stack;
        this.type = type;
        this.dropChance = dropChance;
        this.owner = owner;
    }

    public EntityDrop(SMaterial material, byte variant, EntityDropType type, double dropChance, Player owner) {
        this.stack = SItem.of(material, variant).getStack();
        this.type = type;
        this.dropChance = dropChance;
        this.owner = owner;
    }

    public EntityDrop(SMaterial material, EntityDropType type, double dropChance, Player owner) {
        this(material, (byte)material.getData(), type, dropChance, owner);
    }

    public EntityDrop(SMaterial material, byte variant, EntityDropType type, double dropChance) {
        this(material, variant, type, dropChance, null);
    }

    public EntityDrop(ItemStack stack, EntityDropType type, double dropChance) {
        this(stack, type, dropChance, null);
    }

    public EntityDrop(SMaterial material, EntityDropType type, double dropChance) {
        this(material, type, dropChance, null);
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public EntityDropType getType() {
        return this.type;
    }

    public double getDropChance() {
        return this.dropChance;
    }

    public Player getOwner() {
        return this.owner;
    }
}

