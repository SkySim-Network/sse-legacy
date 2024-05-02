/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.skill.ArcherSkill;
import vn.giakhanhvn.skysim.skill.BerserkSkill;
import vn.giakhanhvn.skysim.skill.CatacombsSkill;
import vn.giakhanhvn.skysim.skill.CombatSkill;
import vn.giakhanhvn.skysim.skill.EnchantingSkill;
import vn.giakhanhvn.skysim.skill.FarmingSkill;
import vn.giakhanhvn.skysim.skill.ForagingSkill;
import vn.giakhanhvn.skysim.skill.HealerSkill;
import vn.giakhanhvn.skysim.skill.MageSkill;
import vn.giakhanhvn.skysim.skill.MiningSkill;
import vn.giakhanhvn.skysim.skill.TankSkill;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.DefenseReplacement;
import vn.giakhanhvn.skysim.util.SUtil;

public abstract class Skill {
    public static final List<Integer> XP_GOALS = Arrays.asList(0, 50, 175, 375, 675, 1175, 1925, 2925, 4425, 6425, 9925, 14925, 22425, 32425, 47425, 67425, 97425, 147425, 222425, 322425, 522425, 822425, 1222425, 1722425, 2322425, 3022425, 3822425, 4722425, 5722425, 6822425, 8022425, 9322425, 10722425, 12222425, 13822425, 15522425, 17322425, 19222425, 21222425, 23322425, 25522425, 27822425, 30222425, 32722425, 35322425, 38072425, 40972425, 44072425, 47472425, 51172425, 55172425, 59472425, 64072425, 68972425, 74172425, 79672425, 85472425, 91572425, 97972425, 104672425, 111672425);
    public static final List<Integer> COIN_REWARDS = Arrays.asList(0, 25, 50, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2200, 2400, 2600, 2800, 3000, 3500, 4000, 5000, 6000, 7500, 10000, 12500, 15000, 17500, 20000, 25000, 30000, 35000, 40000, 45000, 50000, 60000, 70000, 80000, 90000, 100000, 125000, 150000, 175000, 200000, 250000, 300000, 350000, 400000, 450000, 500000, 600000, 700000, 800000, 1000000);

    public static int getLevel(double xp, boolean sixty) {
        if (xp == 0.0) {
            return 0;
        }
        if (xp >= 5.5172425E7 && !sixty) {
            return 50;
        }
        for (int i = XP_GOALS.size() - 1; i >= 0; --i) {
            if (!((double)XP_GOALS.get(i).intValue() < xp)) continue;
            return i;
        }
        return 60;
    }

    public static int getCoinReward(int level) {
        return COIN_REWARDS.get(level);
    }

    public static List<Skill> getSkills() {
        return Arrays.asList(FarmingSkill.INSTANCE, MiningSkill.INSTANCE, CombatSkill.INSTANCE, ForagingSkill.INSTANCE, EnchantingSkill.INSTANCE, CatacombsSkill.INSTANCE, TankSkill.INSTANCE, MageSkill.INSTANCE, BerserkSkill.INSTANCE, ArcherSkill.INSTANCE, HealerSkill.INSTANCE);
    }

    public static double getNextXPGoal(double xp, boolean sixty) {
        if (xp >= 5.5172425E7 && !sixty) {
            return 0.0;
        }
        for (int i = 0; i < XP_GOALS.size(); ++i) {
            int goal = XP_GOALS.get(i);
            if (!((double)goal > xp)) continue;
            return goal - SUtil.getOrDefault(XP_GOALS, i - 1, Integer.valueOf(0));
        }
        return 0.0;
    }

    public static double getNextOverallXPGoal(double xp, boolean sixty) {
        if (xp >= 5.5172425E7 && !sixty) {
            return 0.0;
        }
        for (int goal : XP_GOALS) {
            if (!((double)goal > xp)) continue;
            return goal;
        }
        return 0.0;
    }

    public static double getXPUntilLevelUp(double xp, boolean sixty) {
        double goal = Skill.getNextXPGoal(xp, sixty);
        if (goal == 0.0) {
            return Double.POSITIVE_INFINITY;
        }
        return goal - xp;
    }

    public static DefenseReplacement getProgressReplacement(final Skill skill, final double xp, final double additive, final long end) {
        return new DefenseReplacement(){

            @Override
            public String getReplacement() {
                int next = (int)Skill.getNextXPGoal(xp, skill.hasSixtyLevels());
                return ChatColor.DARK_AQUA + "+" + SUtil.commaify(additive) + " " + skill.getName() + " (" + SUtil.commaify((double)next - (Skill.getNextOverallXPGoal(xp, skill.hasSixtyLevels()) - xp)) + "/" + SUtil.commaify(next) + ")";
            }

            @Override
            public long getEnd() {
                return end;
            }
        };
    }

    public static void reward(Skill rewarded, double rewardXP, Player player) {
        User user = User.getUser(player.getUniqueId());
        Pet pet = user.getActivePetClass();
        if (pet != null && pet.getSkill() == rewarded) {
            Pet.PetItem item = user.getActivePet();
            int prevLevel = Pet.getLevel(item.getXp(), item.getRarity());
            item.setXp(item.getXp() + rewardXP);
            int level = Pet.getLevel(item.getXp(), item.getRarity());
            if (prevLevel != level) {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 2.0f);
                player.sendMessage(ChatColor.GREEN + "Your " + item.getRarity().getColor() + item.getType().getDisplayName(item.getType().getData()) + ChatColor.GREEN + " levelled up to level " + ChatColor.BLUE + level + ChatColor.GREEN + "!");
            }
        } else if (pet != null) {
            Pet.PetItem item = user.getActivePet();
            int prevLevel = Pet.getLevel(item.getXp(), item.getRarity());
            item.setXp(item.getXp() + rewardXP * 25.0 / 100.0);
            int level = Pet.getLevel(item.getXp(), item.getRarity());
            if (prevLevel != level) {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 2.0f);
                player.sendMessage(ChatColor.GREEN + "Your " + item.getRarity().getColor() + item.getType().getDisplayName(item.getType().getData()) + ChatColor.GREEN + " levelled up to level " + ChatColor.BLUE + level + ChatColor.GREEN + "!");
            }
        }
        user.addSkillXP(rewarded, rewardXP);
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0f, 2.0f);
        if (rewardXP > 0.0) {
            Repeater.DEFENSE_REPLACEMENT_MAP.put(player.getUniqueId(), Skill.getProgressReplacement(rewarded, user.getSkillXP(rewarded), rewardXP, System.currentTimeMillis() + 2000L));
        }
    }

    public abstract String getName();

    public abstract String getAlternativeName();

    public abstract List<String> getDescription();

    public abstract List<String> getLevelUpInformation(int var1, int var2, boolean var3);

    public abstract boolean hasSixtyLevels();

    public void onSkillUpdate(User user, double previousXP) {
        int level;
        int prevLevel = Skill.getLevel(previousXP, this.hasSixtyLevels());
        if (prevLevel != (level = Skill.getLevel(user.getSkillXP(this), this.hasSixtyLevels()))) {
            Player player = Bukkit.getPlayer((UUID)user.getUuid());
            if (player != null) {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
            }
            StringBuilder builder = new StringBuilder();
            builder.append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\n");
            builder.append(ChatColor.AQUA).append(ChatColor.BOLD).append("  SKILL LEVEL UP ").append(ChatColor.RESET).append(ChatColor.DARK_AQUA).append(this.getName()).append(" ");
            if (prevLevel != 0) {
                builder.append(ChatColor.DARK_GRAY).append(SUtil.toRomanNumeral(prevLevel)).append("\u279c");
            }
            builder.append(ChatColor.DARK_AQUA).append(SUtil.toRomanNumeral(level)).append("\n");
            builder.append(" \n");
            builder.append(ChatColor.GREEN).append(ChatColor.BOLD).append("  REWARDS");
            for (String line : this.getRewardLore(level, prevLevel, true)) {
                builder.append("\n   ").append(line);
            }
            builder.append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
            user.send(builder.toString());
            if (this != CatacombsSkill.INSTANCE) {
                user.addCoins(Skill.getCoinReward(level));
            }
        }
    }

    public List<String> getRewardLore(int level, int prevLevel, boolean showOld) {
        ArrayList<String> lore = new ArrayList<String>();
        String s = this.getAlternativeName();
        if (!s.contains("{skip}")) {
            lore.add(ChatColor.YELLOW + s + " " + SUtil.toRomanNumeral(level) + ChatColor.WHITE);
        }
        lore.addAll(this.getLevelUpInformation(level, prevLevel, showOld));
        if (this != CatacombsSkill.INSTANCE) {
            lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + SUtil.commaify(Skill.getCoinReward(level)) + ChatColor.GRAY + " Coins");
        }
        return lore;
    }
}

