/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.oddities;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;
import vn.giakhanhvn.skysim.util.Sputnik;

public class DimoonCatalyst
implements SkullStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Dimoon's Catalyst";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public String getLore() {
        return Sputnik.trans("An &5Amethyst Gem &7that used to break &cDimoon&7's seal, use it in one of four &eGreat &eHero &eAltar &7in the &cWithering Ruins");
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public String getURL() {
        return "74e8ff30e3937098637c0af03a1f2a6b17f0e828ab2a57a267a01da484ba0c57";
    }
}

