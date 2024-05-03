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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.auction.AuctionItem;
import vn.giakhanhvn.skysim.gui.AuctionViewGUI;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class ConfirmBidGUI
extends GUI {
    private final AuctionItem item;
    private final long amount;

    public ConfirmBidGUI(AuctionItem item, long amount) {
        super("Confirm " + (item.isBin() ? "Purchase" : "Bid"), 27);
        this.item = item;
        this.amount = amount;
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        final Player player = e.getPlayer();
        final User user = User.getUser(e.getPlayer().getUniqueId());
        final int count = this.item.getItem().getStack().getAmount();
        final boolean personal = this.item.getOwner().getUuid().equals(user.getUuid());
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                if (ConfirmBidGUI.this.item.isExpired()) {
                    player.sendMessage(ChatColor.RED + "The item you are trying to bid on has already expired!");
                    player.closeInventory();
                    return;
                }
                User top = ConfirmBidGUI.this.item.getTopBidder();
                if (top != null && top.getUuid().equals(user.getUuid())) {
                    player.sendMessage(ChatColor.GREEN + "You are already top bid!");
                    return;
                }
                if (user.getCoins() < ConfirmBidGUI.this.amount) {
                    player.sendMessage(ChatColor.RED + "You cannot afford this bid!");
                    return;
                }
                if (personal) {
                    player.sendMessage(ChatColor.RED + "This is your own auction!");
                    return;
                }
                ConfirmBidGUI.this.item.bid(user, ConfirmBidGUI.this.amount);
                new AuctionViewGUI(ConfirmBidGUI.this.item).open(player);
                if (ConfirmBidGUI.this.item.isBin()) {
                    player.sendMessage(ChatColor.GREEN + "Purchased " + ConfirmBidGUI.this.item.getItem().getFullName() + ChatColor.GREEN + " successfully!");
                } else {
                    player.sendMessage(ChatColor.GREEN + "Bid placed on " + ConfirmBidGUI.this.item.getItem().getFullName() + ChatColor.GREEN + " successfully!");
                }
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Confirm", Material.STAINED_CLAY, (short)13, 1, ChatColor.GRAY + (ConfirmBidGUI.this.item.isBin() ? "Purchasing" : "Bidding on") + ": " + (count != 1 ? count + "x " : "") + ConfirmBidGUI.this.item.getItem().getFullName(), ChatColor.GRAY + "Cost: " + ChatColor.GOLD + SUtil.commaify(ConfirmBidGUI.this.amount) + " coin" + (ConfirmBidGUI.this.amount != 1L ? "s" : ""));
            }

            @Override
            public int getSlot() {
                return 11;
            }
        });
        ArrayList<String> bil = new ArrayList<String>();
        bil.add(" ");
        bil.add((count != 1 ? count + "x " : "") + this.item.getItem().getFullName());
        bil.addAll(this.item.getItem().getLore().asBukkitLore());
        this.set(13, SUtil.getStack(ChatColor.YELLOW + "" + ChatColor.BOLD + ChatColor.UNDERLINE + (this.item.isBin() ? "BUYING ITEM" : "BIDDING ON ITEM") + ":", this.item.getItem().getStack().getType(), this.item.getItem().getStack().getDurability(), this.item.getItem().getStack().getAmount(), bil));
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

