/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.gui;

import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.skill.CombatSkill;
import vn.giakhanhvn.skysim.skill.EnchantingSkill;
import vn.giakhanhvn.skysim.skill.FarmingSkill;
import vn.giakhanhvn.skysim.skill.ForagingSkill;
import vn.giakhanhvn.skysim.skill.MiningSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SkillDetails
extends GUI {
    private Skill skill;
    private UUID uuid;
    private Material skillInstanceMat;
    private Material skillLvlMat;
    private short data = 0;
    private Player player;
    private int page;
    static final int[] slots = new int[]{9, 18, 27, 28, 29, 20, 11, 2, 3, 4, 13, 22, 31, 32, 33, 24, 15, 6, 7, 8, 17, 26, 35, 44, 53};

    public SkillDetails(Skill skill, Player player, int index) {
        super(skill.getName() + " Skill", 54);
        this.skill = skill;
        this.page = index;
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        block40: {
            int level;
            double xp;
            block39: {
                this.fill(BLACK_STAINED_GLASS_PANE);
                this.set(GUIClickableItem.getCloseItem(49));
                if (this.skill instanceof CombatSkill) {
                    this.skillInstanceMat = Material.STONE_SWORD;
                    this.skillLvlMat = Material.DIAMOND_HELMET;
                } else if (this.skill instanceof MiningSkill) {
                    this.skillInstanceMat = Material.STONE_PICKAXE;
                    this.skillLvlMat = Material.IRON_BLOCK;
                } else if (this.skill instanceof ForagingSkill) {
                    this.skillInstanceMat = Material.SAPLING;
                    this.data = (short)3;
                    this.skillLvlMat = Material.LOG;
                } else if (this.skill instanceof FarmingSkill) {
                    this.skillInstanceMat = Material.GOLD_HOE;
                    this.skillLvlMat = Material.HAY_BLOCK;
                } else if (this.skill instanceof EnchantingSkill) {
                    this.skillInstanceMat = Material.ENCHANTMENT_TABLE;
                    this.skillLvlMat = Material.ENCHANTED_BOOK;
                }
                xp = this.skill != null ? User.getUser(this.uuid).getSkillXP(this.skill) : 0.0;
                level = this.skill != null ? Skill.getLevel(xp, this.skill.hasSixtyLevels()) : 0;
                this.set(new GUIClickableItem(){

                    @Override
                    public void run(InventoryClickEvent e) {
                        GUIType.SKILL_MENU.getGUI().open(SkillDetails.this.player);
                    }

                    @Override
                    public int getSlot() {
                        return 48;
                    }

                    @Override
                    public ItemStack getItem() {
                        return SUtil.getStack(ChatColor.GREEN + "Go Back", Material.ARROW, (short)0, 1, ChatColor.GRAY + "To Skill Menu");
                    }
                });
                String name = "";
                ArrayList<String> l = new ArrayList<String>();
                if (this.skill != null) {
                    for (String line : this.skill.getDescription()) {
                        l.add(ChatColor.GRAY + line);
                    }
                }
                if (this.skill != null && (level < 50 && !this.skill.hasSixtyLevels() || level < 60 && this.skill.hasSixtyLevels())) {
                    name = this.skill.getName();
                    int nextLevel = level + 1;
                    String numeral = SUtil.toRomanNumeral(nextLevel);
                    double nextXP = Skill.getNextOverallXPGoal(xp, this.skill.hasSixtyLevels());
                    l.add(" ");
                    l.add(SUtil.createProgressText("Progress to Level " + numeral, xp, nextXP));
                    l.add(SUtil.createSLineProgressBar(20, ChatColor.DARK_GREEN, xp, nextXP));
                    l.add(" ");
                    for (String line : this.skill.getRewardLore(nextLevel, nextLevel, false)) {
                        l.add("  " + line);
                    }
                    l.add(" ");
                } else if (this.skill != null) {
                    name = this.skill.getName();
                    l.add(" ");
                }
                if (this.skill != null) {
                    l.add(Sputnik.trans("&8Increase your " + this.skill.getName() + " Level to"));
                    l.add(Sputnik.trans("&8unlock Perks, statistic bonuses,"));
                    l.add(Sputnik.trans("&8and more!"));
                }
                this.set(0, SUtil.getStack(ChatColor.GREEN + this.skill.getName() + " Skill", this.skillInstanceMat, this.data, 1, l));
                if (this.page == 1 && level >= 25) {
                    this.set(new GUIClickableItem(){

                        @Override
                        public void run(InventoryClickEvent e) {
                            new SkillDetails(SkillDetails.this.skill, (Player)e.getWhoClicked(), SkillDetails.this.page + 1).open(SkillDetails.this.player);
                        }

                        @Override
                        public int getSlot() {
                            return 50;
                        }

                        @Override
                        public ItemStack getItem() {
                            return SUtil.getStack(ChatColor.GREEN + "Levels 26-50", Material.ARROW, (short)0, 1, ChatColor.YELLOW + "Click to view!");
                        }
                    });
                } else if (this.page == 2 && level >= 25) {
                    this.set(new GUIClickableItem(){

                        @Override
                        public void run(InventoryClickEvent e) {
                            new SkillDetails(SkillDetails.this.skill, (Player)e.getWhoClicked(), SkillDetails.this.page - 1).open(SkillDetails.this.player);
                        }

                        @Override
                        public int getSlot() {
                            return 50;
                        }

                        @Override
                        public ItemStack getItem() {
                            return SUtil.getStack(ChatColor.GREEN + "Levels 1-25", Material.ARROW, (short)0, 1, ChatColor.YELLOW + "Click to view!");
                        }
                    });
                } else if (level < 25 && this.page > 1) {
                    this.player.closeInventory();
                }
                if (this.page != 1) break block39;
                int i = 1;
                short data = 0;
                ChatColor c = ChatColor.GRAY;
                double nextXP = Skill.getNextOverallXPGoal(xp, this.skill.hasSixtyLevels());
                for (int slot : slots) {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Sputnik.trans("&7Rewards:"));
                    for (String str : this.skill.getRewardLore(i, i, false)) {
                        lore.add("  " + str);
                    }
                    if (i > level && i != level + 1) {
                        data = 14;
                        c = ChatColor.RED;
                    } else if (i == level + 1) {
                        data = 4;
                        c = ChatColor.YELLOW;
                        lore.add(" ");
                        lore.add(SUtil.createProgressText(ChatColor.GRAY + "Progress" + ChatColor.YELLOW, xp, nextXP));
                        lore.add(SUtil.createSLineProgressBar(20, ChatColor.DARK_GREEN, xp, nextXP));
                    } else if (i <= level) {
                        data = 5;
                        c = ChatColor.GREEN;
                        lore.add(" ");
                        lore.add(c + "UNLOCKED");
                    }
                    if (i <= level && i % 5 == 0) {
                        this.set(slot, SUtil.getStack("" + c + this.skill.getName() + " Level " + SUtil.toRomanNumeral(i), this.skillLvlMat, this.data, i, lore));
                    } else {
                        this.set(slot, SUtil.getColorStack(data, "" + c + this.skill.getName() + " Level " + SUtil.toRomanNumeral(i), lore, (short)0, i));
                    }
                    ++i;
                }
                break block40;
            }
            if (this.page != 2) break block40;
            int i = 26;
            short data = 0;
            ChatColor c = ChatColor.GRAY;
            double nextXP = Skill.getNextOverallXPGoal(xp, this.skill.hasSixtyLevels());
            for (int slot : slots) {
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(Sputnik.trans("&7Rewards:"));
                for (String str : this.skill.getRewardLore(i, i, false)) {
                    lore.add("  " + str);
                }
                if (i > level && i != level + 1) {
                    data = 14;
                    c = ChatColor.RED;
                } else if (i == level + 1) {
                    data = 4;
                    c = ChatColor.YELLOW;
                    lore.add(" ");
                    lore.add(SUtil.createProgressText(ChatColor.GRAY + "Progress" + ChatColor.YELLOW, xp, nextXP));
                    lore.add(SUtil.createSLineProgressBar(20, ChatColor.DARK_GREEN, xp, nextXP));
                } else if (i <= level) {
                    data = 5;
                    c = ChatColor.GREEN;
                    lore.add(" ");
                    lore.add(c + "UNLOCKED");
                }
                if (i <= level && i % 5 == 0) {
                    this.set(slot, SUtil.getStack("" + c + this.skill.getName() + " Level " + SUtil.toRomanNumeral(i), this.skillInstanceMat, this.data, i, lore));
                } else {
                    this.set(slot, SUtil.getColorStack(data, "" + c + this.skill.getName() + " Level " + SUtil.toRomanNumeral(i), lore, (short)0, i));
                }
                ++i;
            }
        }
    }
}

