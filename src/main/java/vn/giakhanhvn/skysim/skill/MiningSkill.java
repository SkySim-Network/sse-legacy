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

public class MiningSkill
extends Skill {
    public static final MiningSkill INSTANCE = new MiningSkill();

    @Override
    public String getName() {
        return "Mining";
    }

    @Override
    public String getAlternativeName() {
        return "Spelunker";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Spelunk islands for ores and", "valuable materials to earn", "Mining XP!");
    }

    public double getDoubleDropChance(int level) {
        return (double)level * 4.0 / 100.0;
    }

    public double getTripleDropChance(int level) {
        return ((double)level - 25.0) * 4.0 / 100.0;
    }

    public double getDefense(int level) {
        return (double)level < 15.0 ? (double)level : (double)level + ((double)level - 14.0);
    }

    @Override
    public List<String> getLevelUpInformation(int level, int lastLevel, boolean showOld) {
        String dropChance = (showOld ? ChatColor.DARK_GRAY + "" + lastLevel * 4 + "\u279c" : "") + ChatColor.GREEN + level * 4;
        if (level > 25) {
            dropChance = (showOld ? ChatColor.DARK_GRAY + "" + (lastLevel - 25) * 4 + "\u279c" : "") + ChatColor.GREEN + (level - 25) * 4;
        }
        return Arrays.asList(ChatColor.WHITE + " Grants " + dropChance + "%" + ChatColor.WHITE + " chance", ChatColor.WHITE + " to drop " + (level > 25 ? "3" : "2") + "x ores.", ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + (level >= 15 ? "2" : "1") + " " + ChatColor.GREEN + "\u2748 Defense");
    }

    @Override
    public boolean hasSixtyLevels() {
        return true;
    }

    @Override
    public void onSkillUpdate(User user, double previousXP) {
        super.onSkillUpdate(user, previousXP);
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(user.getUuid());
        statistics.zeroAll(11);
        statistics.getDefense().set(11, this.getDefense(MiningSkill.getLevel(user.getSkillXP(this), this.hasSixtyLevels())));
    }
}

