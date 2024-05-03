/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.gui.SlayerConfirmGUI;
import vn.giakhanhvn.skysim.slayer.SlayerBossType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SvenPackmasterGUI
extends GUI {
    public SvenPackmasterGUI() {
        super("Sven Packmaster", 54);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        this.fill(BLACK_STAINED_GLASS_PANE);
        final Player player = e.getPlayer();
        this.set(GUIClickableItem.createGUIOpenerItem(GUIType.SLAYER, player, ChatColor.GREEN + "Go Back", 49, Material.ARROW, ChatColor.GRAY + "To Slayer"));
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                new SlayerConfirmGUI(SlayerBossType.SVEN_PACKMASTER_I, () -> User.getUser(player.getUniqueId()).startSlayerQuest(SlayerBossType.SVEN_PACKMASTER_I)).open(player);
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(SlayerBossType.SVEN_PACKMASTER_I.getDisplayName(), SlayerBossType.SVEN_PACKMASTER_I.getType().getIcon(), (short)0, 1, SlayerBossType.SVEN_PACKMASTER_I.asLore(true));
            }

            @Override
            public int getSlot() {
                return 11;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                new SlayerConfirmGUI(SlayerBossType.SVEN_PACKMASTER_II, () -> User.getUser(player.getUniqueId()).startSlayerQuest(SlayerBossType.SVEN_PACKMASTER_II)).open(player);
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(SlayerBossType.SVEN_PACKMASTER_II.getDisplayName(), SlayerBossType.SVEN_PACKMASTER_II.getType().getIcon(), (short)0, 2, SlayerBossType.SVEN_PACKMASTER_II.asLore(true));
            }

            @Override
            public int getSlot() {
                return 12;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                new SlayerConfirmGUI(SlayerBossType.SVEN_PACKMASTER_III, () -> User.getUser(player.getUniqueId()).startSlayerQuest(SlayerBossType.SVEN_PACKMASTER_III)).open(player);
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(SlayerBossType.SVEN_PACKMASTER_III.getDisplayName(), SlayerBossType.SVEN_PACKMASTER_III.getType().getIcon(), (short)0, 3, SlayerBossType.SVEN_PACKMASTER_III.asLore(true));
            }

            @Override
            public int getSlot() {
                return 13;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                new SlayerConfirmGUI(SlayerBossType.SVEN_PACKMASTER_IV, () -> User.getUser(player.getUniqueId()).startSlayerQuest(SlayerBossType.SVEN_PACKMASTER_IV)).open(player);
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(SlayerBossType.SVEN_PACKMASTER_IV.getDisplayName(), SlayerBossType.SVEN_PACKMASTER_IV.getType().getIcon(), (short)0, 4, SlayerBossType.SVEN_PACKMASTER_IV.asLore(true));
            }

            @Override
            public int getSlot() {
                return 14;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.DARK_PURPLE + "Sven Packmaster V", Material.COAL_BLOCK, (short)0, 1, ChatColor.GRAY + "This excruciatingly difficult", ChatColor.GRAY + "boss tier will release at a", ChatColor.GRAY + "later date.");
            }

            @Override
            public int getSlot() {
                return 15;
            }

            @Override
            public void run(InventoryClickEvent e) {
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.DARK_PURPLE + "Boss Leveling Rewards", Material.GOLD_BLOCK, (short)0, 1, ChatColor.DARK_GRAY + "Wolf Slayer LVL", ChatColor.GRAY + " ", Sputnik.trans("&51. &7Kill boss to get XP"), Sputnik.trans("&52. &7Gain LVL from XP"), Sputnik.trans("&53. &7Unlock rewards per LVL"), Sputnik.trans(" "), Sputnik.trans("&7Current LVL: &e" + SlayerBossType.SlayerMobType.WOLF.getLevelForXP(User.getUser(player.getUniqueId()).getWolfSlayerXP())), Sputnik.trans(" "), Sputnik.trans("&7Wolf Slayer XP to LVL " + (SlayerBossType.SlayerMobType.WOLF.getLevelForXP(User.getUser(player.getUniqueId()).getWolfSlayerXP()) + 1) + ":"), Sputnik.trans(SUtil.createLineProgressBar(18, ChatColor.DARK_PURPLE, User.getUser(player.getUniqueId()).getWolfSlayerXP(), SlayerBossType.staticGetXPReqForLevel(SlayerBossType.SlayerMobType.WOLF.getLevelForXP(User.getUser(player.getUniqueId()).getWolfSlayerXP()), EntityType.WOLF))), " ", Sputnik.trans("&cNot available on Semi-Sandbox mode!"));
            }

            @Override
            public int getSlot() {
                return 29;
            }

            @Override
            public void run(InventoryClickEvent e) {
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GOLD + "Boss Drops", Material.GOLD_NUGGET, (short)0, 1, ChatColor.DARK_GRAY + "Sven Packmaster", " ", Sputnik.trans("&7Usually, the boss will drop"), Sputnik.trans("&aNull Sphere&7."), " ", Sputnik.trans("&7If you're lucky, you may get"), Sputnik.trans("&7one of &d6 &7possible"), Sputnik.trans("&7drops from this boss."), " ", Sputnik.trans("&cMenu is not available!"));
            }

            @Override
            public int getSlot() {
                return 31;
            }

            @Override
            public void run(InventoryClickEvent e) {
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Slayers Recipe", Material.BOOK, (short)0, 1, ChatColor.DARK_GRAY + "Sven Packmaster", " ", Sputnik.trans("&cFeature is not available!"));
            }

            @Override
            public int getSlot() {
                return 33;
            }

            @Override
            public void run(InventoryClickEvent e) {
            }
        });
    }
}

