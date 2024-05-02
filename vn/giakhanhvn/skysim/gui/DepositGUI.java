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
import vn.giakhanhvn.skysim.gui.BankerGUI;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIQueryItem;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class DepositGUI
extends GUI {
    public DepositGUI() {
        super("Bank Deposit", 36);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        this.fill(BLACK_STAINED_GLASS_PANE);
        final Player player = e.getPlayer();
        this.set(GUIClickableItem.createGUIOpenerItem(GUIType.BANKER, player, ChatColor.GREEN + "Go Back", 31, Material.ARROW, ChatColor.GRAY + "To Personal Bank Account"));
        final User user = User.getUser(player.getUniqueId());
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                long coins = user.getCoins();
                user.subCoins(coins);
                user.addBankCoins(coins);
                user.save();
                player.sendMessage(ChatColor.GREEN + "You have deposited " + ChatColor.GOLD + SUtil.commaify(coins) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()) + " coins " + ChatColor.GREEN + "in your account!");
                GUIType.BANKER.getGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 11;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Your whole purse", Material.CHEST, (short)0, 64, ChatColor.DARK_GRAY + "Bank deposit", " ", ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + SUtil.commaify(user.getCoins()), " ", ChatColor.YELLOW + "Click to deposit coins!");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                long coins = user.getCoins() / 2L;
                user.subCoins(coins);
                user.addBankCoins(coins);
                user.save();
                player.sendMessage(ChatColor.GREEN + "You have deposited " + ChatColor.GOLD + SUtil.commaify(coins) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()) + " coins " + ChatColor.GREEN + "in your account!");
                GUIType.BANKER.getGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 13;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Half your purse", Material.CHEST, (short)0, 32, ChatColor.DARK_GRAY + "Bank deposit", " ", ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + SUtil.commaify(user.getCoins() / 2L), " ", ChatColor.YELLOW + "Click to deposit coins!");
            }
        });
        this.set(new GUIQueryItem(){

            @Override
            public GUI onQueryFinish(String query) {
                try {
                    long coins = Long.parseLong(query);
                    if (coins < 0L) {
                        player.sendMessage(ChatColor.RED + "Enter a positive number!");
                        return null;
                    }
                    if (coins > user.getCoins()) {
                        player.sendMessage(ChatColor.RED + "You do not have that many coins!");
                        return null;
                    }
                    user.subCoins(coins);
                    user.addBankCoins(coins);
                    user.save();
                    player.sendMessage(ChatColor.GREEN + "You have deposited " + ChatColor.GOLD + SUtil.commaify(coins) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()) + " coins " + ChatColor.GREEN + "in your account!");
                }
                catch (NumberFormatException ex) {
                    player.sendMessage(ChatColor.RED + "That is not a valid number!");
                    return null;
                }
                return new BankerGUI();
            }

            @Override
            public void run(InventoryClickEvent e) {
            }

            @Override
            public int getSlot() {
                return 15;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Specific amount", Material.SIGN, (short)0, 1, ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), " ", ChatColor.YELLOW + "Click to deposit coins!");
            }
        });
    }
}

