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

public class EnchantingSkill
extends Skill {
    public static final EnchantingSkill INSTANCE = new EnchantingSkill();

    @Override
    public String getName() {
        return "Enchanting";
    }

    @Override
    public String getAlternativeName() {
        return "Conjurer";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Enchant items to earn Enchanting", "XP!");
    }

    @Override
    public List<String> getLevelUpInformation(int level, int lastLevel, boolean showOld) {
        return Arrays.asList(ChatColor.WHITE + " Gain " + (showOld ? ChatColor.DARK_GRAY + "" + lastLevel * 4 + "\u279c" : "") + ChatColor.GREEN + level * 4 + "% " + ChatColor.WHITE + "more experience orbs", ChatColor.WHITE + " from any source.", ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + "0.5% " + ChatColor.RED + "\u0e51 Ability Damage", ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + "2 " + ChatColor.AQUA + "\u270e Intelligence");
    }

    @Override
    public boolean hasSixtyLevels() {
        return false;
    }

    @Override
    public void onSkillUpdate(User user, double previousXP) {
        super.onSkillUpdate(user, previousXP);
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(user.getUuid());
        statistics.zeroAll(14);
        statistics.getAbilityDamage().set(14, 0.5 * (double)EnchantingSkill.getLevel(user.getEnchantXP(), false));
        statistics.getIntelligence().set(14, Double.valueOf(2 * EnchantingSkill.getLevel(user.getEnchantXP(), false)));
    }
}

