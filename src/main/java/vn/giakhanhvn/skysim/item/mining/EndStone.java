/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.mining;

import vn.giakhanhvn.skysim.item.ExperienceRewardStatistics;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.skill.MiningSkill;
import vn.giakhanhvn.skysim.skill.Skill;

public class EndStone
implements ExperienceRewardStatistics,
MaterialFunction {
    @Override
    public double getRewardXP() {
        return 5.0;
    }

    @Override
    public Skill getRewardedSkill() {
        return MiningSkill.INSTANCE;
    }

    @Override
    public String getDisplayName() {
        return "End Stone";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }
}

