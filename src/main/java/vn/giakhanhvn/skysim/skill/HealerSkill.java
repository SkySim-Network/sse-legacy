/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import vn.giakhanhvn.skysim.skill.DungeonsSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.User;

public class HealerSkill
extends Skill
implements DungeonsSkill {
    public static final HealerSkill INSTANCE = new HealerSkill();

    @Override
    public String getName() {
        return "Healer";
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
        t.add("Renew");
        t.add("Healing Aura");
        t.add("Revive");
        t.add("Orbies" + ChatColor.RED + " Soon!");
        t.add("Soul Tether" + ChatColor.RED + " Soon!");
        return t;
    }

    @Override
    public List<String> getOrb() {
        ArrayList<String> t = new ArrayList<String>();
        t.add("Healing Circle");
        t.add("Wish");
        return t;
    }

    @Override
    public List<String> getGhost() {
        ArrayList<String> t = new ArrayList<String>();
        t.add("Healing Potion");
        t.add("Revive Self");
        return t;
    }
}

