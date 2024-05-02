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
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class BankerGUI
extends GUI {
    public BankerGUI() {
        super("Bank", 36);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        final Player player = e.getPlayer();
        final User user = User.getUser(e.getPlayer().getUniqueId());
        this.fill(BLACK_STAINED_GLASS_PANE);
        this.set(GUIClickableItem.getCloseItem(31));
        this.set(new GUIClickableItem(){

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Deposit Coins", Material.CHEST, (short)0, 1, ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), " ", ChatColor.GRAY + "Store coins in the bank to", ChatColor.GRAY + "keep them safe while you go", ChatColor.GRAY + "on adventures!", " ", ChatColor.GRAY + "You will earn " + ChatColor.AQUA + "2%" + ChatColor.GRAY + " interest every", ChatColor.GRAY + "season for your first " + ChatColor.GOLD + "10 million", ChatColor.GRAY + "banked coins.", " ", ChatColor.GRAY + "Until interest: " + ChatColor.RED + "Unavailable", " ", ChatColor.YELLOW + "Click to make a deposit!");
            }

            @Override
            public void run(InventoryClickEvent e) {
                GUIType.BANKER_DEPOSIT.getGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 11;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Withdraw Coins", Material.DROPPER, (short)0, 1, ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), " ", ChatColor.GRAY + "Take your coins out of the", ChatColor.GRAY + "bank in order to spend", ChatColor.GRAY + "them.", " ", ChatColor.YELLOW + "Click to withdraw coins!");
            }

            @Override
            public void run(InventoryClickEvent e) {
                GUIType.BANKER_WITHDRAWAL.getGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 13;
            }
        });
        this.set(15, SUtil.getStack(ChatColor.GREEN + "Recent transactions", Material.MAP, (short)0, 1, " ", ChatColor.RED + "" + ChatColor.BOLD + "COMING SOON"));
        this.set(32, SUtil.getStack(ChatColor.GREEN + "Information", Material.REDSTONE_TORCH_ON, (short)0, 1, ChatColor.GRAY + "Keep your coins safe in the bank!", ChatColor.GRAY + "You lose half the coins in your", ChatColor.GRAY + "purse when dying in combat.", " ", ChatColor.GRAY + "Balance limit: " + ChatColor.GOLD + "50 Million", " ", ChatColor.GRAY + "The banker rewards you every 31", ChatColor.GRAY + "hours with " + ChatColor.AQUA + "interest" + ChatColor.GRAY + " for the", ChatColor.GRAY + "coins in your bank balance.", " ", ChatColor.GRAY + "Interest " + ChatColor.RED + ChatColor.BOLD + "COMING SOON"));
    }
}

