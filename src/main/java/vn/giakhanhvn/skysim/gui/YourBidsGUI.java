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
import java.util.List;
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
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class YourBidsGUI
extends GUI {
    private static final int[] INTERIOR = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43};
    private List<AuctionItem> items;

    public YourBidsGUI() {
        super("Your Bids", 27);
        this.border(BLACK_STAINED_GLASS_PANE);
    }

    @Override
    public void early(Player player) {
        User user = User.getUser(player.getUniqueId());
        this.items = user.getBids();
        this.size = Math.max(54, Double.valueOf(Math.ceil((double)this.items.size() / 7.0)).intValue() * 9 + 18);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        final Player player = e.getPlayer();
        if (this.items == null) {
            return;
        }
        int ended = 0;
        for (final AuctionItem item : this.items) {
            if (!item.isExpired()) continue;
            ++ended;
        }
        if (ended != 0) {
            final int finalEnded = ended;
            this.set(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    for (AuctionItem item : YourBidsGUI.this.items) {
                        if (!item.isExpired()) continue;
                        item.claim(player);
                    }
                    player.closeInventory();
                }

                @Override
                public int getSlot() {
                    return 21;
                }

                @Override
                public ItemStack getItem() {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.DARK_GRAY + "Ended Auctions");
                    lore.add(" ");
                    lore.add(ChatColor.GRAY + "You got " + ChatColor.GREEN + finalEnded + " item" + (finalEnded != 1 ? "s" : "") + ChatColor.GRAY + " to");
                    lore.add(ChatColor.GRAY + "claim items/reclaim bids.");
                    lore.add(" ");
                    lore.add(ChatColor.YELLOW + "Click to claim!");
                    return SUtil.getStack(ChatColor.GREEN + "Claim All", Material.CAULDRON_ITEM, (short)0, 1, lore);
                }
            });
        }
        this.set(GUIClickableItem.createGUIOpenerItem(GUIType.AUCTION_HOUSE, player, ChatColor.GREEN + "Go Back", 22, Material.ARROW, ChatColor.GRAY + "To Auction House"));
        for (int i = 0; i < this.items.size(); ++i) {
            AuctionItem item;
            item = this.items.get(i);
            final int slot = INTERIOR[i];
            this.set(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    new AuctionViewGUI(item, YourBidsGUI.this).open(player);
                }

                @Override
                public int getSlot() {
                    return slot;
                }

                @Override
                public ItemStack getItem() {
                    return item.getDisplayItem(true, true);
                }
            });
        }
    }

    private static enum Sort {
        RECENTLY_UPDATED("Recently Updated"),
        HIGHEST_BID("Highest Bid"),
        MOST_BIDS("Most Bids");

        private final String display;

        private Sort(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return this.display;
        }

        public Sort previous() {
            int prev = this.ordinal() - 1;
            if (prev < 0) {
                return Sort.values()[Sort.values().length - 1];
            }
            return Sort.values()[prev];
        }

        public Sort next() {
            int nex = this.ordinal() + 1;
            if (nex > Sort.values().length - 1) {
                return Sort.values()[0];
            }
            return Sort.values()[nex];
        }
    }
}

