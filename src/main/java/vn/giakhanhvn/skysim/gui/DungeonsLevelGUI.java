/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.potion.Potion
 *  org.bukkit.potion.PotionType
 */
package vn.giakhanhvn.skysim.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import vn.giakhanhvn.skysim.collection.ItemCollection;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.gui.SkillDetails;
import vn.giakhanhvn.skysim.skill.ArcherSkill;
import vn.giakhanhvn.skysim.skill.BerserkSkill;
import vn.giakhanhvn.skysim.skill.CatacombsSkill;
import vn.giakhanhvn.skysim.skill.DungeonsSkill;
import vn.giakhanhvn.skysim.skill.HealerSkill;
import vn.giakhanhvn.skysim.skill.MageSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.skill.TankSkill;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class DungeonsLevelGUI
extends GUI {
    public static final ItemStack CATA_HEAD = SUtil.getSkullURL("964e1c3e315c8d8fffc37985b6681c5bd16a6f97ffd07199e8a05efbef103793");

    public DungeonsLevelGUI() {
        super("Dungeoneering", 54);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        this.fill(BLACK_STAINED_GLASS_PANE);
        Player player = e.getPlayer();
        User user = User.getUser(player.getUniqueId());
        this.set(GUIClickableItem.getCloseItem(49));
        AtomicInteger found = new AtomicInteger();
        Collection<ItemCollection> collections = ItemCollection.getCollections();
        for (ItemCollection collection : collections) {
            if (user.getCollection(collection) <= 0) continue;
            found.incrementAndGet();
        }
        this.set(GUIClickableItem.createGUIOpenerItem(GUIType.SKILL_MENU, player, ChatColor.GREEN + "Go Back", 48, Material.ARROW, ChatColor.GRAY + "To Skills"));
        this.set(DungeonsLevelGUI.createCollectionClickable(new SkillDetails(CatacombsSkill.INSTANCE, player, 1), CatacombsSkill.INSTANCE, 12, player));
        this.set(13, RED_STAINED_GLASS_PANE);
        this.set(14, RED_STAINED_GLASS_PANE);
        this.set(DungeonsLevelGUI.createCollectionClickableForClasses(null, HealerSkill.INSTANCE, 29, player));
        this.set(DungeonsLevelGUI.createCollectionClickableForClasses(null, MageSkill.INSTANCE, 30, player));
        this.set(DungeonsLevelGUI.createCollectionClickableForClasses(null, BerserkSkill.INSTANCE, 31, player));
        this.set(DungeonsLevelGUI.createCollectionClickableForClasses(null, ArcherSkill.INSTANCE, 32, player));
        this.set(DungeonsLevelGUI.createCollectionClickableForClasses(null, TankSkill.INSTANCE, 33, player));
    }

    private static GUIClickableItem createCollectionClickableForClasses(GUI gui, Skill skill, int slot, Player player) {
        User user = User.getUser(player.getUniqueId());
        ItemStack stack = null;
        Material mat = null;
        String lore1 = "{}";
        String lore2 = "{}";
        String lore3 = "{}";
        String lore4 = "{}";
        if (skill instanceof ArcherSkill) {
            mat = Material.BOW;
            lore1 = "&7Arrow Damage: &c" + (120.0 + 0.8 * (double)Skill.getLevel(user.getSkillXP(skill), false)) + "%";
            lore2 = "&7Melee Damage: &c-25%";
            lore3 = " ";
        } else if (skill instanceof BerserkSkill) {
            mat = Material.IRON_SWORD;
            lore1 = "&7Melee Damage: &c" + (40.0 + 0.4 * (double)Skill.getLevel(user.getSkillXP(skill), false)) + "%";
            lore2 = " ";
            lore3 = "&7Walk Speed: &a" + (30.0 + 0.3 * (double)Skill.getLevel(user.getSkillXP(skill), false)) + "%";
            lore4 = " ";
        } else if (skill instanceof TankSkill) {
            mat = Material.LEATHER_CHESTPLATE;
            lore1 = "&7Health: &a+100";
            lore2 = "&7Defense: &a" + (50 + 1 * Skill.getLevel(user.getSkillXP(skill), false));
            lore3 = " ";
        } else if (skill instanceof MageSkill) {
            mat = Material.BLAZE_ROD;
            lore1 = "&7Intelligence: &a" + (250 + 5 * Skill.getLevel(user.getSkillXP(skill), false));
            lore2 = " ";
        } else if (skill instanceof HealerSkill) {
            mat = Material.POTION;
        }
        ArrayList<String> l = new ArrayList<String>();
        double xp = skill != null ? user.getSkillXP(skill) : 0.0;
        int level = skill != null ? Skill.getLevel(xp, skill.hasSixtyLevels()) : 0;
        String name = ChatColor.RED + "Coming Soon!";
        if (skill != null && (level < 50 && !skill.hasSixtyLevels() || level < 60 && skill.hasSixtyLevels())) {
            name = skill.getName();
            int nextLevel = level + 1;
            String numeral = SUtil.toRomanNumeral(nextLevel);
            double nextXP = Skill.getNextOverallXPGoal(xp, skill.hasSixtyLevels());
            if (!lore1.contains("{}")) {
                l.add(Sputnik.trans(lore1));
            }
            if (!lore2.contains("{}")) {
                l.add(Sputnik.trans(lore2));
            }
            if (!lore3.contains("{}")) {
                l.add(Sputnik.trans(lore3));
            }
            if (!lore4.contains("{}")) {
                l.add(Sputnik.trans(lore4));
            }
            l.add(Sputnik.trans("&f&lClass Passives"));
            for (String str : ((DungeonsSkill)((Object)skill)).getPassive()) {
                l.add(Sputnik.trans(" &8\u2022 &a" + str));
            }
            l.add(" ");
            l.add(Sputnik.trans("&f&lDungeon Orb Abilties"));
            for (String str : ((DungeonsSkill)((Object)skill)).getOrb()) {
                l.add(Sputnik.trans(" &8\u2022 &6" + str));
            }
            l.add(" ");
            l.add(Sputnik.trans("&f&lGhost Abilties"));
            for (String str : ((DungeonsSkill)((Object)skill)).getGhost()) {
                l.add(Sputnik.trans(" &8\u2022 &f" + str));
            }
            l.add(" ");
            l.add(SUtil.createProgressText("Progress to Level " + numeral, xp, nextXP));
            l.add(SUtil.createSLineProgressBar(20, ChatColor.DARK_GREEN, xp, nextXP));
            l.add(" ");
            l.add(Sputnik.trans("&8&oUse this class in dungeons"));
            l.add(Sputnik.trans("&8&oto level it up"));
            l.add(" ");
        } else if (skill != null) {
            name = skill.getName();
        }
        if (skill != null) {
            l.add(ChatColor.RED + "Skill Tree Coming Soon!");
        }
        stack = SUtil.getStack(ChatColor.GREEN + name + (level != 0 ? " " + SUtil.toRomanNumeral(level) : ""), mat, (short)0, 1, l);
        if (skill instanceof HealerSkill) {
            Potion pot = new Potion(1);
            pot.setType(PotionType.INSTANT_HEAL);
            pot.setSplash(true);
            pot.apply(stack);
        }
        return GUIClickableItem.createGUIOpenerItemStack(gui, player, slot, stack);
    }

    private static GUIClickableItem createCollectionClickable(GUI gui, Skill skill, int slot, Player player) {
        User user = User.getUser(player.getUniqueId());
        ArrayList<String> l = new ArrayList<String>();
        if (skill != null) {
            for (String line : skill.getDescription()) {
                l.add(ChatColor.GRAY + line);
            }
        }
        if (l.size() == 0) {
            l.add(ChatColor.GRAY + "Coming at a later date!");
        } else {
            l.add(" ");
        }
        double xp = skill != null ? user.getSkillXP(skill) : 0.0;
        int level = skill != null ? Skill.getLevel(xp, skill.hasSixtyLevels()) : 0;
        String name = ChatColor.RED + "Coming Soon!";
        if (skill != null && (level < 50 && !skill.hasSixtyLevels() || level < 60 && skill.hasSixtyLevels())) {
            name = skill.getName();
            int nextLevel = level + 1;
            String numeral = SUtil.toRomanNumeral(nextLevel);
            double nextXP = Skill.getNextOverallXPGoal(xp, skill.hasSixtyLevels());
            l.add(SUtil.createProgressText("Progress to Level " + numeral, xp, nextXP));
            l.add(SUtil.createSLineProgressBar(20, ChatColor.DARK_GREEN, xp, nextXP));
            l.add(" ");
            for (String line : skill.getRewardLore(nextLevel, nextLevel, false)) {
                l.add("" + line);
            }
            l.add(" ");
        } else if (skill != null) {
            name = skill.getName();
        }
        if (skill != null) {
            l.add(ChatColor.YELLOW + "Click to view!");
        }
        return GUIClickableItem.createGUIOpenerItemHead(gui, player, ChatColor.RED + name, slot, "964e1c3e315c8d8fffc37985b6681c5bd16a6f97ffd07199e8a05efbef103793", 1, SUtil.toArray(l, String.class));
    }
}

