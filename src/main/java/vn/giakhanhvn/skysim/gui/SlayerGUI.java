/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.gui.SlayerCancellationConfirmGUI;
import vn.giakhanhvn.skysim.slayer.SlayerBossType;
import vn.giakhanhvn.skysim.slayer.SlayerQuest;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SlayerGUI
extends GUI {
    public SlayerGUI() {
        super("Slayer", 36);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        final Player player = e.getPlayer();
        final User user = User.getUser(e.getPlayer().getUniqueId());
        this.fill(BLACK_STAINED_GLASS_PANE);
        this.set(GUIClickableItem.getCloseItem(31));
        final SlayerQuest quest = user.getSlayerQuest();
        if (quest != null) {
            if (quest.getKilled() != 0L) {
                this.set(new GUIClickableItem(){

                    @Override
                    public void run(InventoryClickEvent e) {
                        user.setSlayerXP(quest.getType().getType(), user.getSlayerXP(quest.getType().getType()) + quest.getType().getRewardXP());
                        int level = quest.getType().getType().getLevelForXP(user.getSlayerXP(quest.getType().getType()));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 2.0f);
                        player.sendMessage("  " + ChatColor.GREEN + ChatColor.BOLD + "SLAYER QUEST COMPLETED!");
                        player.sendMessage("   " + ChatColor.YELLOW + quest.getType().getType().getName() + " Slayer LVL " + level + ChatColor.DARK_RED + " - " + ChatColor.GRAY + "Next LVL in " + ChatColor.LIGHT_PURPLE + SUtil.commaify(quest.getType().getXPReqForLevel(level) - user.getSlayerXP(quest.getType().getType())) + " XP" + ChatColor.GRAY + "!");
                        user.setSlayerQuest(null);
                        GUIType.SLAYER.getGUI().open(player);
                    }

                    @Override
                    public ItemStack getItem() {
                        return SUtil.getStack(ChatColor.GREEN + "Slayer Quest Complete!", quest.getType().getType().getIcon(), (short)0, 1, ChatColor.GRAY + "You've slain the boss!", ChatColor.GRAY + "SkySim is now a little", ChatColor.GRAY + "safer thanks to you!", "", ChatColor.GRAY + "Boss: " + quest.getType().getDisplayName(), "", ChatColor.DARK_GRAY + "Time to spawn: " + SUtil.getSlayerFormattedTime(quest.getSpawned() - quest.getStarted()), ChatColor.DARK_GRAY + "Time to kill: " + SUtil.getSlayerFormattedTime(quest.getKilled() - quest.getSpawned()), "", ChatColor.GRAY + "Reward: " + ChatColor.DARK_PURPLE + quest.getType().getRewardXP() + " " + quest.getType().getType().getName() + " Slayer XP", "", ChatColor.YELLOW + "Click to collect reward!");
                    }

                    @Override
                    public int getSlot() {
                        return 13;
                    }
                });
            } else if (quest.getDied() != 0L) {
                this.set(new GUIClickableItem(){

                    @Override
                    public void run(InventoryClickEvent e) {
                        user.setSlayerQuest(null);
                        player.sendMessage(ChatColor.YELLOW + "Your unsuccessful quest has been cleared out!");
                        GUIType.SLAYER.getGUI().open(player);
                    }

                    @Override
                    public ItemStack getItem() {
                        return SUtil.getStack(ChatColor.GREEN + "Slayer Quest Failed", Material.STAINED_CLAY, (short)14, 1, ChatColor.GRAY + "You've didn't succeed in", ChatColor.GRAY + "killing the boss on your", ChatColor.GRAY + "last Slayer quest.", "", ChatColor.GRAY + "Quest from: " + ChatColor.AQUA + SUtil.getSlayerFormattedTime(System.currentTimeMillis() - quest.getStarted()) + " ago", "", ChatColor.DARK_GRAY + "It's no big deal! You can", ChatColor.DARK_GRAY + "always try again!", "", ChatColor.YELLOW + "Ok, thanks for reminding me!");
                    }

                    @Override
                    public int getSlot() {
                        return 13;
                    }
                });
            } else {
                this.set(new GUIClickableItem(){

                    @Override
                    public void run(InventoryClickEvent e) {
                        new SlayerCancellationConfirmGUI(user).open(player);
                    }

                    @Override
                    public ItemStack getItem() {
                        return SUtil.getStack(ChatColor.GREEN + "Ongoing Slayer Quest", quest.getType().getType().getIcon(), (short)0, 1, ChatColor.GRAY + "You have an active Slayer", ChatColor.GRAY + "quest.", "", ChatColor.GRAY + "Boss: " + quest.getType().getDisplayName(), ChatColor.YELLOW + "Kill " + quest.getType().getType().getPluralName() + " to spawn the boss!", "", ChatColor.YELLOW + "Click to cancel the quest!");
                    }

                    @Override
                    public int getSlot() {
                        return 13;
                    }
                });
            }
            return;
        }
        this.set(30, SUtil.getStack(ChatColor.GREEN + "Random Slayer Quest", Material.WATCH, (short)0, 1, ChatColor.DARK_GRAY + "Extra Rewards", "", ChatColor.GRAY + "Start a slayer quest for a", ChatColor.GRAY + "random boss.", "", ChatColor.GRAY + "Quests started this way reward", ChatColor.GRAY + "more items and " + ChatColor.LIGHT_PURPLE + "XP" + ChatColor.GRAY + ".", "", ChatColor.RED + "Coming soon!"));
        this.set(32, SUtil.getStack(ChatColor.GREEN + "Global Combat XP Buff", Material.WHEAT, (short)0, 1, ChatColor.DARK_GRAY + "Slayer Bonus", "", ChatColor.GRAY + "Total buff: " + ChatColor.AQUA + "+" + user.getSlayerCombatXPBuff() + "% Combat XP", "", ChatColor.GRAY + "Earn extra Combat XP based on", ChatColor.GRAY + "your unique slayer boss kills.", "", ChatColor.DARK_GRAY + "Highest slain tiers", ChatColor.GRAY + "Revenant Horror: " + SlayerGUI.getTierText(user.getHighestRevenantHorror()), ChatColor.GRAY + "Tarantula Broodfather: " + SlayerGUI.getTierText(user.getHighestTarantulaBroodfather()), ChatColor.GRAY + "Sven Packmaster: " + SlayerGUI.getTierText(user.getHighestSvenPackmaster()), "", ChatColor.GRAY + "Tier I, II, III grant " + ChatColor.AQUA + "+1% XP" + ChatColor.GRAY + ".", ChatColor.GRAY + "Tier IV grants " + ChatColor.AQUA + "+2% XP" + ChatColor.GRAY + "."));
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                GUIType.REVENANT_HORROR.getGUI().open((Player)e.getWhoClicked());
            }

            @Override
            public int getSlot() {
                return 10;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.RED + "\u2620 " + ChatColor.YELLOW + "Revenant Horror", Material.ROTTEN_FLESH, (short)0, 1, ChatColor.GRAY + "Abhorrant Zombie stuck", ChatColor.GRAY + "between life and death for", ChatColor.GRAY + "an eternity.", "", ChatColor.GRAY + "Zombie Slayer: " + ChatColor.YELLOW + "LVL " + SlayerBossType.SlayerMobType.ZOMBIE.getLevelForXP(user.getZombieSlayerXP()), "", ChatColor.YELLOW + "Click to view boss!");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                GUIType.TARANTULA_BROODFATHER.getGUI().open((Player)e.getWhoClicked());
            }

            @Override
            public int getSlot() {
                return 11;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.RED + "\u2620 " + ChatColor.YELLOW + "Tarantula Broodfather", Material.WEB, (short)0, 1, ChatColor.GRAY + "Monstrous Spider who poisons", ChatColor.GRAY + "and devours its victims.", "", ChatColor.GRAY + "Spider Slayer: " + ChatColor.YELLOW + "LVL " + SlayerBossType.SlayerMobType.SPIDER.getLevelForXP(user.getSpiderSlayerXP()), "", ChatColor.YELLOW + "Click to view boss!");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                Player p = (Player)e.getWhoClicked();
                if (p == null) {
                    return;
                }
                if (PlayerUtils.isAutoSlayer(player)) {
                    PlayerUtils.AUTO_SLAYER.put(player.getUniqueId(), false);
                } else {
                    PlayerUtils.AUTO_SLAYER.put(player.getUniqueId(), true);
                }
                GUIType.SLAYER.getGUI().open((Player)e.getWhoClicked());
            }

            @Override
            public int getSlot() {
                return 28;
            }

            @Override
            public ItemStack getItem() {
                ItemStack isBuilder = new ItemStack(Material.BEDROCK, 1);
                isBuilder = PlayerUtils.isAutoSlayer(player) ? SUtil.getStack(Sputnik.trans("&bAuto-Slayer"), Material.INK_SACK, (short)10, 1, ChatColor.GRAY + "Upon defeating a boss,", Sputnik.trans("&aautomatically &7completes"), ChatColor.GRAY + "the quest and starts", ChatColor.GRAY + "another one of the same type.", "", Sputnik.trans("&7Currently: &aEnabled"), "", ChatColor.YELLOW + "Click to disable!") : SUtil.getStack(Sputnik.trans("&bAuto-Slayer"), Material.INK_SACK, (short)8, 1, ChatColor.GRAY + "Upon defeating a boss,", Sputnik.trans("&aautomatically &7completes"), ChatColor.GRAY + "the quest and starts", ChatColor.GRAY + "another one of the same type.", "", Sputnik.trans("&7Currently: &cDisabled"), "", ChatColor.YELLOW + "Click to enable!");
                return isBuilder;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                GUIType.SVEN_PACKMASTER.getGUI().open((Player)e.getWhoClicked());
            }

            @Override
            public int getSlot() {
                return 12;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.RED + "\u2620 " + ChatColor.YELLOW + "Sven Packmaster", Material.MUTTON, (short)0, 1, ChatColor.GRAY + "Rabid Wolf genetically", ChatColor.GRAY + "modified by a famous mad", ChatColor.GRAY + "scientist. Eats bones and", ChatColor.GRAY + "flesh.", "", ChatColor.GRAY + "Wolf Slayer: " + ChatColor.YELLOW + "LVL " + SlayerBossType.SlayerMobType.WOLF.getLevelForXP(user.getWolfSlayerXP()), "", ChatColor.YELLOW + "Click to view boss!");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                GUIType.VOIDGLOOM_SERAPH.getGUI().open((Player)e.getWhoClicked());
            }

            @Override
            public int getSlot() {
                return 13;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.RED + "\u2620 " + ChatColor.YELLOW + "Voidgloom Seraph", Material.ENDER_PEARL, (short)0, 1, ChatColor.GRAY + "If Necron is the right-hand", ChatColor.GRAY + "of the Wither King, this dark", ChatColor.GRAY + "demigod is the left-hand.", "", ChatColor.GRAY + "Enderman Slayer: " + ChatColor.YELLOW + "LVL " + SlayerBossType.SlayerMobType.WOLF.getLevelForXP(user.getEndermanSlayerXP()), "", ChatColor.YELLOW + "Click to view boss!");
            }
        });
        this.fill(SUtil.getStack(ChatColor.RED + "Not released yet!", Material.COAL_BLOCK, (short)0, 1, ChatColor.GRAY + "This boss is still in", ChatColor.GRAY + "development!"), 14, 16);
    }

    public static String getTierText(int highest) {
        if (highest == 0) {
            return ChatColor.GREEN + "Not played!";
        }
        ChatColor color = ChatColor.GREEN;
        if (highest == 2) {
            color = ChatColor.YELLOW;
        }
        if (highest == 3) {
            color = ChatColor.RED;
        }
        if (highest == 4) {
            color = ChatColor.DARK_RED;
        }
        if (highest == 5) {
            color = ChatColor.DARK_PURPLE;
        }
        return color + "Tier " + SUtil.toRomanNumeral(highest);
    }

    public static void claimReward(Player player) {
        User user = User.getUser(player.getUniqueId());
        SlayerQuest quest = user.getSlayerQuest();
        user.setSlayerXP(quest.getType().getType(), user.getSlayerXP(quest.getType().getType()) + quest.getType().getRewardXP());
        int level = quest.getType().getType().getLevelForXP(user.getSlayerXP(quest.getType().getType()));
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 2.0f);
        player.sendMessage("  " + ChatColor.GREEN + ChatColor.BOLD + "SLAYER QUEST COMPLETED!");
        player.sendMessage("   " + ChatColor.YELLOW + quest.getType().getType().getName() + " Slayer LVL " + level + ChatColor.DARK_RED + " - " + ChatColor.GRAY + "Next LVL in " + ChatColor.LIGHT_PURPLE + SUtil.commaify(quest.getType().getXPReqForLevel(level) - user.getSlayerXP(quest.getType().getType())) + " XP" + ChatColor.GRAY + "!");
        user.setSlayerQuest(null);
    }
}

