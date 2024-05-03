/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.skill;

import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;

public class CombatSkill
extends Skill {
    public static final CombatSkill INSTANCE = new CombatSkill();

    @Override
    public String getName() {
        return "Combat";
    }

    @Override
    public String getAlternativeName() {
        return "Warrior";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Fight mobs and players to earn", "Combat XP!");
    }

    @Override
    public List<String> getLevelUpInformation(int level, int lastLevel, boolean showOld) {
        return Arrays.asList(ChatColor.WHITE + " Deal " + (showOld ? ChatColor.DARK_GRAY + "" + lastLevel * 4 + "\u279c" : "") + ChatColor.GREEN + level * 4 + "% " + ChatColor.WHITE + "more damage to mobs.", ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + "0.5% " + ChatColor.BLUE + "\u2623 Crit Chance");
    }

    @Override
    public boolean hasSixtyLevels() {
        return false;
    }

    @Override
    public void onSkillUpdate(User user, double previousXP) {
        super.onSkillUpdate(user, previousXP);
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(user.getUuid());
        statistics.zeroAll(12);
        statistics.getCritChance().set(12, 0.005 * (double)CombatSkill.getLevel(user.getCombatXP(), false));
    }
}

