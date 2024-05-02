/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.skill;

import java.util.Arrays;
import java.util.List;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class CatacombsSkill
extends Skill {
    public static final CatacombsSkill INSTANCE = new CatacombsSkill();

    @Override
    public String getName() {
        return "The Catacombs";
    }

    @Override
    public String getAlternativeName() {
        return "{skip}";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Complete this dungeon to earn", "experience and unlock new", "rewards!");
    }

    @Override
    public List<String> getLevelUpInformation(int level, int lastLevel, boolean showOld) {
        return Arrays.asList(Sputnik.trans("&7Level " + SUtil.toRomanNumeral(level) + ": &7Increasing the stats"), Sputnik.trans("&7of your Dungeon items by"), Sputnik.trans("&c" + level * 5 + "% &7while in &cThe"), Sputnik.trans("&cCatacombs&7."));
    }

    @Override
    public boolean hasSixtyLevels() {
        return false;
    }

    @Override
    public void onSkillUpdate(User user, double previousXP) {
        super.onSkillUpdate(user, previousXP);
    }
}

