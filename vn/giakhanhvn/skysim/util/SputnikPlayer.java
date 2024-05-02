/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.NBTBase
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  net.minecraft.server.v1_8_R3.NBTTagInt
 *  org.bukkit.Material
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.util;

import de.tr7zw.nbtapi.NBTItem;
import java.util.HashMap;
import java.util.Map;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SputnikPlayer {
    public static final Map<Player, Integer> AbsHP = new HashMap<Player, Integer>();

    public static void sendTranslated(Player p, String content) {
        p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)content));
    }

    public static void BonemerangFix(Player player) {
        for (int i = 0; i < player.getInventory().getSize(); ++i) {
            NBTItem nbti;
            ItemStack stack = player.getInventory().getItem(i);
            if (stack == null || !(nbti = new NBTItem(stack)).hasKey("ejectedBonemerang").booleanValue() || nbti.getInteger("ejectedBonemerang") != 1) continue;
            net.minecraft.server.v1_8_R3.ItemStack tagStack = CraftItemStack.asNMSCopy((ItemStack)stack);
            NBTTagCompound tagCompound = tagStack.hasTag() ? tagStack.getTag() : new NBTTagCompound();
            tagCompound.set("ejectedBonemerang", (NBTBase)new NBTTagInt(0));
            tagStack.setTag(tagCompound);
            ItemStack itemStack = CraftItemStack.asBukkitCopy((net.minecraft.server.v1_8_R3.ItemStack)tagStack);
            if (tagStack.getTag().getInt("ejectedBonemerang") != 0) continue;
            itemStack.setType(Material.BONE);
            player.getInventory().setItem(i, itemStack);
        }
    }

    public static void KatanasFix(Player player) {
        for (int i = 0; i < player.getInventory().getSize(); ++i) {
            NBTItem nbti;
            ItemStack stack = player.getInventory().getItem(i);
            if (stack == null || !(nbti = new NBTItem(stack)).hasKey("isGoldSword").booleanValue() || nbti.getInteger("isGoldSword") != 1) continue;
            net.minecraft.server.v1_8_R3.ItemStack tagStack = CraftItemStack.asNMSCopy((ItemStack)stack);
            NBTTagCompound tagCompound = tagStack.hasTag() ? tagStack.getTag() : new NBTTagCompound();
            tagCompound.set("isGoldSword", (NBTBase)new NBTTagInt(0));
            tagStack.setTag(tagCompound);
            ItemStack itemStack = CraftItemStack.asBukkitCopy((net.minecraft.server.v1_8_R3.ItemStack)tagStack);
            if (tagStack.getTag().getInt("isGoldSword") != 0) continue;
            itemStack.setType(Material.DIAMOND_SWORD);
            player.getInventory().setItem(i, itemStack);
        }
    }

    public static void setCustomAbsorptionHP(Player p, float amount) {
        EntityHuman human = ((CraftHumanEntity)p).getHandle();
        if (!AbsHP.containsKey(p)) {
            AbsHP.put(p, 0);
        }
        Integer absHP = AbsHP.get(p);
        AbsHP.put(p, Math.round(amount));
    }

    public static void minusCustomAbsorptionHP(Player p, float amount) {
        EntityHuman human = ((CraftHumanEntity)p).getHandle();
        if (!AbsHP.containsKey(p)) {
            AbsHP.put(p, 0);
        }
        Integer absHP = AbsHP.get(p);
        if (AbsHP.get(p) == 0) {
            return;
        }
        AbsHP.put(p, AbsHP.get(p) - Math.round(amount));
    }

    public static Integer getCustomAbsorptionHP(Player p) {
        EntityHuman human = ((CraftHumanEntity)p).getHandle();
        if (!AbsHP.containsKey(p)) {
            AbsHP.put(p, 0);
        }
        return AbsHP.get(p);
    }

    public static void updateScaledAHP(Player p) {
        EntityHuman human = ((CraftHumanEntity)p).getHandle();
        if (!AbsHP.containsKey(p)) {
            AbsHP.put(p, 0);
        }
        if (AbsHP.get(p) == 0) {
            human.setAbsorptionHearts(0.0f);
        }
        Integer absHP = AbsHP.get(p);
        human.setAbsorptionHearts((float)Math.min(20.0, (double)((int)Math.round(0.05 * (double)absHP.intValue()))));
        if (AbsHP.get(p) == 0) {
            human.setAbsorptionHearts(0.0f);
        }
    }
}

