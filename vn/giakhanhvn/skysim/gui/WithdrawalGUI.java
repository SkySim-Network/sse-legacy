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

public class WithdrawalGUI
extends GUI {
    public WithdrawalGUI() {
        super("Bank Withdrawal", 36);
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
                long coins = user.getBankCoins();
                user.addCoins(coins);
                user.subBankCoins(coins);
                user.save();
                player.sendMessage(ChatColor.GREEN + "You have withdrawn " + ChatColor.GOLD + SUtil.commaify(coins) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()) + " coins " + ChatColor.GREEN + "in your account!");
                GUIType.BANKER.getGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 10;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Everything in the account", Material.DROPPER, (short)0, 64, ChatColor.DARK_GRAY + "Bank withdrawal", " ", ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), " ", ChatColor.YELLOW + "Click to withdraw coins!");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                long coins = user.getBankCoins() / 2L;
                user.addCoins(coins);
                user.subBankCoins(coins);
                user.save();
                player.sendMessage(ChatColor.GREEN + "You have withdrawn " + ChatColor.GOLD + SUtil.commaify(coins) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()) + " coins " + ChatColor.GREEN + "in your account!");
                GUIType.BANKER.getGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 12;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Half the account", Material.DROPPER, (short)0, 32, ChatColor.DARK_GRAY + "Bank withdrawal", " ", ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins() / 2L), " ", ChatColor.YELLOW + "Click to withdraw coins!");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                long coins = Math.round(Long.valueOf(user.getBankCoins()).doubleValue() * 0.2);
                user.addCoins(coins);
                user.subBankCoins(coins);
                user.save();
                player.sendMessage(ChatColor.GREEN + "You have withdrawn " + ChatColor.GOLD + SUtil.commaify(coins) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()) + " coins " + ChatColor.GREEN + "in your account!");
                GUIType.BANKER.getGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 14;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Withdraw 20%", Material.DROPPER, (short)0, 1, ChatColor.DARK_GRAY + "Bank withdrawal", " ", ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + SUtil.commaify(Math.round(Long.valueOf(user.getBankCoins()).doubleValue() * 0.2)), " ", ChatColor.YELLOW + "Click to withdraw coins!");
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
                    if (coins > user.getBankCoins()) {
                        player.sendMessage(ChatColor.RED + "You do not have that many coins!");
                        return null;
                    }
                    user.addCoins(coins);
                    user.subBankCoins(coins);
                    user.save();
                    player.sendMessage(ChatColor.GREEN + "You have withdrawn " + ChatColor.GOLD + SUtil.commaify(coins) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()) + " coins " + ChatColor.GREEN + "in your account!");
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
                return 16;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Specific amount", Material.SIGN, (short)0, 1, ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + SUtil.commaify(user.getBankCoins()), " ", ChatColor.YELLOW + "Click to withdraw coins!");
            }
        });
    }
}

