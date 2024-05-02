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

public class MageSkill
extends Skill
implements DungeonsSkill {
    public static final MageSkill INSTANCE = new MageSkill();

    @Override
    public String getName() {
        return "Mage";
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
        t.add("Mage Staff");
        t.add("Efficent Spells");
        return t;
    }

    @Override
    public List<String> getOrb() {
        ArrayList<String> t = new ArrayList<String>();
        t.add("Guided Sheep");
        t.add("Thunderstorm");
        return t;
    }

    @Override
    public List<String> getGhost() {
        ArrayList<String> t = new ArrayList<String>();
        t.add("Pop-up Wall");
        t.add("Fireball");
        return t;
    }
}

