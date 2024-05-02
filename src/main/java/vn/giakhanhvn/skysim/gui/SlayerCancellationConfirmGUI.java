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

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class SlayerCancellationConfirmGUI
extends GUI {
    public SlayerCancellationConfirmGUI(final User user) {
        super("Confirm", 27);
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                user.setSlayerQuest(null);
                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Your Slayer Quest has been cancelled!");
                GUIType.SLAYER.getGUI().open((Player)e.getWhoClicked());
                user.removeAllSlayerBosses();
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Confirm", Material.STAINED_CLAY, (short)13, 1, ChatColor.RED + "Clears " + ChatColor.GRAY + "progress towards", ChatColor.GRAY + "the current Slayer Quest to", ChatColor.GRAY + "let you pick a different", ChatColor.GRAY + "one.", "", ChatColor.RED + "" + ChatColor.BOLD + "CANCELLING THE QUEST!", ChatColor.YELLOW + "Click to cancel the quest!");
            }

            @Override
            public int getSlot() {
                return 11;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                e.getWhoClicked().closeInventory();
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.RED + "Cancel", Material.STAINED_CLAY, (short)14, 1, new String[0]);
            }

            @Override
            public int getSlot() {
                return 15;
            }
        });
    }
}

