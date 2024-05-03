/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item;

import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.skill.Skill;

public interface ExperienceRewardStatistics
extends MaterialStatistics {
    public double getRewardXP();

    public Skill getRewardedSkill();
}

