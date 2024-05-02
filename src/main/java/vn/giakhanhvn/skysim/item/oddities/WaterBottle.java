/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.item.oddities;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.ItemData;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.potion.PotionEffect;
import vn.giakhanhvn.skysim.potion.PotionEffectType;
import vn.giakhanhvn.skysim.util.SUtil;

public class WaterBottle
implements MaterialStatistics,
MaterialFunction,
ItemData {
    @Override
    public NBTTagCompound getData() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setBoolean("splash", false);
        return compound;
    }

    @Override
    public String getDisplayName() {
        return "Water Bottle";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public List<String> getDataLore(String key, Object value) {
        if (!key.equals("effects")) {
            return null;
        }
        NBTTagCompound compound = (NBTTagCompound)value;
        ArrayList<String> lore = new ArrayList<String>();
        for (String k : compound.c()) {
            lore.add(" ");
            NBTTagCompound effectData = compound.getCompound(k);
            PotionEffectType type = PotionEffectType.getByNamespace(k);
            int level = effectData.getInt("level");
            long duration = effectData.getLong("duration");
            PotionEffect effect = new PotionEffect(type, level, duration);
            lore.add(type.getName() + " " + SUtil.toRomanNumeral(effect.getLevel()) + (!effect.getType().isInstant() ? ChatColor.WHITE + " (" + effect.getDurationDisplay() + ")" : ""));
            for (String line : SUtil.splitByWordAndLength(effect.getDescription(), 30, "\\s")) {
                lore.add(ChatColor.GRAY + line);
            }
        }
        return lore;
    }

    @Override
    public void onInstanceUpdate(SItem instance) {
        int max = 0;
        for (PotionEffect effect : instance.getPotionEffects()) {
            if (effect.getLevel() <= max) continue;
            max = effect.getLevel();
        }
        instance.setRarity(SUtil.findPotionRarity(max), false);
        if (instance.getPotionEffects().size() == 1) {
            instance.setDisplayName(ChatColor.stripColor((String)(instance.getPotionEffects().get(0).getType().getName() + " " + SUtil.toRomanNumeral(instance.getPotionEffects().get(0).getLevel()))) + (instance.isSplashPotion() ? " Splash" : "") + " Potion");
        }
        if (instance.getPotionEffects().size() > 1) {
            instance.setDisplayName((instance.isSplashPotion() ? "Splash " : "") + "Potion");
        }
        if (instance.getPotionEffects().size() == 0) {
            instance.setDisplayName("Water Bottle");
        }
    }
}

