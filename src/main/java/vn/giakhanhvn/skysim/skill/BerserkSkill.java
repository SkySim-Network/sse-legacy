/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import vn.giakhanhvn.skysim.skill.DungeonsSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.User;

public class BerserkSkill
extends Skill
implements DungeonsSkill {
    public static final BerserkSkill INSTANCE = new BerserkSkill();

    @Override
    public String getName() {
        return "Berserk";
    }

    @Override
    public String getAlternativeName() {
        return "{skip}";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("");
    }

    @Override
    public List<String> getLevelUpInformation(int level, int lastLevel, boolean showOld) {
        return Arrays.asList("");
    }

    @Override
    public boolean hasSixtyLevels() {
        return false;
    }

    @Override
    public void onSkillUpdate(User user, double previousXP) {
        super.onSkillUpdate(user, previousXP);
    }

    @Override
    public List<String> getPassive() {
        ArrayList<String> t = new ArrayList<String>();
        t.add("Bloodlust");
        t.add("Lust for Blood");
        return t;
    }

    @Override
    public List<String> getOrb() {
        ArrayList<String> t = new ArrayList<String>();
        t.add("Throwing Axe");
        t.add("Ragnagrok");
        return t;
    }

    @Override
    public List<String> getGhost() {
        ArrayList<String> t = new ArrayList<String>();
        t.add("Throwing Axe");
        t.add("Strength Potion");
        return t;
    }
}

