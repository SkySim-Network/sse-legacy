/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.auction.AuctionBid;
import vn.giakhanhvn.skysim.auction.AuctionItem;
import vn.giakhanhvn.skysim.gui.ConfirmBidGUI;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIQueryItem;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class AuctionViewGUI
extends GUI {
    private final AuctionItem item;
    private final GUI ret;
    private final long bid;

    public AuctionViewGUI(AuctionItem item, GUI ret, long bid) {
        super((item.isBin() ? "BIN " : "") + "Auction View", 54);
        this.item = item;
        this.ret = ret;
        this.bid = bid;
        this.fill(BLACK_STAINED_GLASS_PANE);
    }

    public AuctionViewGUI(AuctionItem item, GUI ret) {
        this(item, ret, item.nextBid());
    }

    public AuctionViewGUI(AuctionItem item) {
        this(item, null);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        Player player = e.getPlayer();
        User user = User.getUser(player.getUniqueId());
        boolean personal = this.item.getOwner().getUuid().equals(user.getUuid());
        this.set(13, this.item.getDisplayItem(false, user.getUuid().equals(this.item.getOwner().getUuid())));
        if (this.item.isBin()) {
            for (GUIItem item : this.getBINItems(player)) {
                this.set(item);
            }
        } else {
            for (GUIItem item : this.getAuctionItems(player)) {
                this.set(item);
            }
        }
        if (this.ret != null) {
            this.set(GUIClickableItem.createGUIOpenerItem(this.ret, player, ChatColor.GREEN + "Go Back", 49, Material.ARROW, (short)0, ChatColor.GRAY + "To " + this.ret.getTitle()));
        } else {
            this.set(GUIClickableItem.getCloseItem(49));
        }
    }

    private List<GUIItem> getBINItems(final Player player) {
        final User user = User.getUser(player.getUniqueId());
        final boolean personal = this.item.getOwner().getUuid().equals(user.getUuid());
        ArrayList<GUIItem> items = new ArrayList<GUIItem>();
        if (this.item.isExpired()) {
            final long topBid = this.item.getTopBidAmount();
            items.add(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    AuctionViewGUI.this.item.claim(player);
                    player.closeInventory();
                }

                @Override
                public int getSlot() {
                    return 31;
                }

                @Override
                public ItemStack getItem() {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(" ");
                    if (AuctionViewGUI.this.item.getBids().size() == 0) {
                        if (AuctionViewGUI.this.item.isBin()) {
                            lore.add(ChatColor.GRAY + "No one has bought your item.");
                        } else {
                            lore.add(ChatColor.GRAY + "No one has bid on your item.");
                        }
                        lore.add(ChatColor.GREEN + "You may pick it back up.");
                        lore.add(" ");
                        lore.add(ChatColor.YELLOW + "Click to pick up item!");
                    } else {
                        lore.add(ChatColor.GRAY + "Item sold to " + ChatColor.GREEN + Bukkit.getOfflinePlayer((UUID)AuctionViewGUI.this.item.getTopBidder().getUuid()));
                        lore.add(ChatColor.GRAY + "for " + ChatColor.GOLD + topBid + " coin" + (topBid != 1L ? "s" : "") + ChatColor.GRAY + "!");
                        lore.add(" ");
                        lore.add(ChatColor.YELLOW + "Click to collect coins!");
                    }
                    return SUtil.getStack(ChatColor.GOLD + "Collect Auction", AuctionViewGUI.this.item.getBids().size() != 0 ? Material.GOLD_BLOCK : Material.GOLD_NUGGET, (short)0, 1, lore);
                }
            });
        } else {
            items.add(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    if (personal) {
                        player.sendMessage(ChatColor.RED + "This is your own auction!");
                        return;
                    }
                    if (AuctionViewGUI.this.item.isExpired()) {
                        player.sendMessage(ChatColor.RED + "The item you are trying to bid on has already expired!");
                        player.closeInventory();
                        return;
                    }
                    User top = AuctionViewGUI.this.item.getTopBidder();
                    if (top != null && top.getUuid().equals(user.getUuid())) {
                        player.sendMessage(ChatColor.GREEN + "You are already top bid!");
                        return;
                    }
                    if (user.getCoins() < AuctionViewGUI.this.bid) {
                        player.sendMessage(ChatColor.RED + "You cannot afford this bid!");
                        return;
                    }
                    new ConfirmBidGUI(AuctionViewGUI.this.item, AuctionViewGUI.this.bid).open(player);
                }

                @Override
                public int getSlot() {
                    return personal && AuctionViewGUI.this.item.getBids().size() == 0 ? 29 : 31;
                }

                @Override
                public ItemStack getItem() {
                    Material icon;
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(" ");
                    if (AuctionViewGUI.this.item.isBin()) {
                        lore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + SUtil.commaify(AuctionViewGUI.this.item.getStarter()) + " coin" + (AuctionViewGUI.this.item.getStarter() != 1L ? "s" : ""));
                    } else {
                        lore.add(ChatColor.GRAY + "New bid: " + ChatColor.GOLD + SUtil.commaify(AuctionViewGUI.this.bid) + " coin" + (AuctionViewGUI.this.bid != 1L ? "s" : ""));
                        AuctionBid bid = AuctionViewGUI.this.item.getBid(user);
                        if (bid != null) {
                            lore.add(ChatColor.GRAY + "Your previous bid: " + ChatColor.YELLOW + SUtil.commaify(bid.getAmount()) + " coin" + (bid.getAmount() != 1L ? "s" : ""));
                        }
                    }
                    lore.add(" ");
                    User top = AuctionViewGUI.this.item.getTopBidder();
                    if (personal) {
                        lore.add(ChatColor.GREEN + "This is your own auction!");
                    } else if (top != null && top.getUuid().equals(user.getUuid())) {
                        lore.add(ChatColor.GREEN + "Already top bid!");
                    } else if (user.getCoins() < AuctionViewGUI.this.bid) {
                        lore.add(ChatColor.RED + "Cannot afford bid!");
                    } else {
                        lore.add(ChatColor.YELLOW + "Click to " + (AuctionViewGUI.this.item.isBin() ? "buy" : "bid") + "!");
                    }
                    Material material = icon = user.getCoins() < AuctionViewGUI.this.bid || personal ? Material.POTATO_ITEM : Material.GOLD_NUGGET;
                    if (top != null && top.getUuid().equals(user.getUuid())) {
                        icon = Material.GOLD_BLOCK;
                    }
                    return SUtil.getStack(ChatColor.GOLD + (AuctionViewGUI.this.item.isBin() ? "Buy Item Right Now" : "Submit Bid"), icon, (short)0, 1, lore);
                }
            });
        }
        if (personal && this.item.getBids().size() == 0 && !this.item.isExpired()) {
            items.add(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    if (AuctionViewGUI.this.item.isExpired()) {
                        player.sendMessage(ChatColor.RED + "The auction you are trying to cancel has already expired!");
                        player.closeInventory();
                        return;
                    }
                    if (AuctionViewGUI.this.item.getBids().size() != 0) {
                        player.sendMessage(ChatColor.RED + "Someone has bid on this item and it cannot be removed!");
                        return;
                    }
                    player.closeInventory();
                    player.getInventory().addItem(new ItemStack[]{AuctionViewGUI.this.item.getItem().getStack()});
                    AuctionViewGUI.this.item.delete();
                    player.sendMessage(ChatColor.GREEN + "Your auction has been successfully cancelled!");
                }

                @Override
                public int getSlot() {
                    return 33;
                }

                @Override
                public ItemStack getItem() {
                    return SUtil.getStack(ChatColor.RED + "Cancel Auction", Material.STAINED_CLAY, (short)14, 1, ChatColor.GRAY + "You may cancel auctions as", ChatColor.GRAY + "long as they have " + ChatColor.RED + "0" + ChatColor.GRAY + " bids!", " ", ChatColor.YELLOW + "Click to cancel auction!");
                }
            });
        }
        return items;
    }

    private List<GUIItem> getAuctionItems(final Player player) {
        final User user = User.getUser(player.getUniqueId());
        final boolean personal = this.item.getOwner().getUuid().equals(user.getUuid());
        ArrayList<GUIItem> items = new ArrayList<GUIItem>();
        if (this.item.isExpired()) {
            final long topBid = this.item.getTopBidAmount();
            items.add(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    AuctionViewGUI.this.item.claim(player);
                    player.closeInventory();
                }

                @Override
                public int getSlot() {
                    return 29;
                }

                @Override
                public ItemStack getItem() {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(" ");
                    if (AuctionViewGUI.this.item.getBids().size() == 0) {
                        if (AuctionViewGUI.this.item.isBin()) {
                            lore.add(ChatColor.GRAY + "No one has bought your item.");
                        } else {
                            lore.add(ChatColor.GRAY + "No one has bid on your item.");
                        }
                        lore.add(ChatColor.GREEN + "You may pick it back up.");
                        lore.add(" ");
                        lore.add(ChatColor.YELLOW + "Click to pick up item!");
                    } else {
                        lore.add(ChatColor.GRAY + "Item sold to " + ChatColor.GREEN + Bukkit.getOfflinePlayer((UUID)AuctionViewGUI.this.item.getTopBidder().getUuid()));
                        lore.add(ChatColor.GRAY + "for " + ChatColor.GOLD + topBid + " coin" + (topBid != 1L ? "s" : "") + ChatColor.GRAY + "!");
                        lore.add(" ");
                        lore.add(ChatColor.YELLOW + "Click to collect coins!");
                    }
                    return SUtil.getStack(ChatColor.GOLD + "Collect Auction", AuctionViewGUI.this.item.getBids().size() != 0 ? Material.GOLD_BLOCK : Material.GOLD_NUGGET, (short)0, 1, lore);
                }
            });
        } else {
            items.add(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    if (personal) {
                        player.sendMessage(ChatColor.RED + "This is your own auction!");
                        return;
                    }
                    if (AuctionViewGUI.this.item.isExpired()) {
                        player.sendMessage(ChatColor.RED + "The item you are trying to bid on has already expired!");
                        player.closeInventory();
                        return;
                    }
                    User top = AuctionViewGUI.this.item.getTopBidder();
                    if (top != null && top.getUuid().equals(user.getUuid())) {
                        player.sendMessage(ChatColor.GREEN + "You are already top bid!");
                        return;
                    }
                    if (user.getCoins() < AuctionViewGUI.this.bid) {
                        player.sendMessage(ChatColor.RED + "You cannot afford this bid!");
                        return;
                    }
                    new ConfirmBidGUI(AuctionViewGUI.this.item, AuctionViewGUI.this.bid).open(player);
                }

                @Override
                public int getSlot() {
                    return 29;
                }

                @Override
                public ItemStack getItem() {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(" ");
                    if (AuctionViewGUI.this.item.isBin()) {
                        lore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + SUtil.commaify(AuctionViewGUI.this.item.getStarter()) + " coin" + (AuctionViewGUI.this.item.getStarter() != 1L ? "s" : ""));
                    } else {
                        lore.add(ChatColor.GRAY + "New bid: " + ChatColor.GOLD + SUtil.commaify(AuctionViewGUI.this.bid) + " coin" + (AuctionViewGUI.this.bid != 1L ? "s" : ""));
                    }
                    lore.add(" ");
                    if (personal) {
                        lore.add(ChatColor.GREEN + "This is your own auction!");
                    } else if (user.getCoins() < AuctionViewGUI.this.bid) {
                        lore.add(ChatColor.RED + "Cannot afford purchase!");
                    } else {
                        lore.add(ChatColor.YELLOW + "Click to " + (AuctionViewGUI.this.item.isBin() ? "buy" : "bid") + "!");
                    }
                    return SUtil.getStack(ChatColor.GOLD + (AuctionViewGUI.this.item.isBin() ? "Buy Item Right Now" : "Submit Bid"), user.getCoins() < AuctionViewGUI.this.bid || personal ? Material.POTATO_ITEM : Material.GOLD_NUGGET, (short)0, 1, lore);
                }
            });
        }
        if (personal && this.item.getBids().size() == 0 && !this.item.isExpired()) {
            items.add(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    if (AuctionViewGUI.this.item.isExpired()) {
                        player.sendMessage(ChatColor.RED + "The auction you are trying to cancel has already expired!");
                        player.closeInventory();
                        return;
                    }
                    if (AuctionViewGUI.this.item.getBids().size() != 0) {
                        player.sendMessage(ChatColor.RED + "Someone has bid on this item and it cannot be removed!");
                        return;
                    }
                    player.closeInventory();
                    player.getInventory().addItem(new ItemStack[]{AuctionViewGUI.this.item.getItem().getStack()});
                    AuctionViewGUI.this.item.delete();
                    player.sendMessage(ChatColor.GREEN + "Your auction has been successfully cancelled!");
                }

                @Override
                public int getSlot() {
                    return 31;
                }

                @Override
                public ItemStack getItem() {
                    return SUtil.getStack(ChatColor.RED + "Cancel Auction", Material.STAINED_CLAY, (short)14, 1, ChatColor.GRAY + "You may cancel auctions as", ChatColor.GRAY + "long as they have " + ChatColor.RED + "0" + ChatColor.GRAY + " bids!", " ", ChatColor.YELLOW + "Click to cancel auction!");
                }
            });
        }
        if (!personal && !this.item.isExpired() && this.item.nextBid() <= user.getCoins()) {
            items.add(new GUIQueryItem(){

                @Override
                public GUI onQueryFinish(String query) {
                    long l;
                    if (AuctionViewGUI.this.item.isExpired()) {
                        player.sendMessage(ChatColor.RED + "The auction you are trying to bid on has already expired!");
                        player.closeInventory();
                    }
                    try {
                        l = Long.parseLong(query);
                        if (l <= 0L) {
                            player.sendMessage(ChatColor.RED + "Could not read this number!");
                            return null;
                        }
                    }
                    catch (NumberFormatException ex) {
                        player.sendMessage(ChatColor.RED + "Could not read this number!");
                        return null;
                    }
                    if (l < AuctionViewGUI.this.item.nextBid()) {
                        player.sendMessage(ChatColor.RED + "That bid is less than the minimum!");
                        return new AuctionViewGUI(AuctionViewGUI.this.item, AuctionViewGUI.this.ret, AuctionViewGUI.this.bid);
                    }
                    if (AuctionViewGUI.this.bid > user.getCoins()) {
                        player.sendMessage(ChatColor.RED + "You cannot afford that bid!");
                        return new AuctionViewGUI(AuctionViewGUI.this.item, AuctionViewGUI.this.ret, AuctionViewGUI.this.bid);
                    }
                    return new AuctionViewGUI(AuctionViewGUI.this.item, AuctionViewGUI.this.ret, l);
                }

                @Override
                public void run(InventoryClickEvent e) {
                }

                @Override
                public int getSlot() {
                    return 31;
                }

                @Override
                public ItemStack getItem() {
                    String display = ChatColor.GOLD + SUtil.commaify(AuctionViewGUI.this.bid) + " coin" + (AuctionViewGUI.this.bid != 1L ? "s" : "");
                    return SUtil.getStack(ChatColor.WHITE + "Bid Amount: " + display, Material.GOLD_INGOT, (short)0, 1, ChatColor.GRAY + "You need to bid at least", display + ChatColor.GRAY + " to hold", ChatColor.GRAY + "the top bid on this", ChatColor.GRAY + "auction.", " ", ChatColor.GRAY + "The " + ChatColor.YELLOW + "top bid" + ChatColor.GRAY + " on auction", ChatColor.GRAY + "ends wins the item.", " ", ChatColor.GRAY + "If you do not win, you can", ChatColor.GRAY + "claim your bid coins back.", " ", ChatColor.YELLOW + "Click to edit amount!");
                }
            });
        }
        final ArrayList<String> lore = new ArrayList<String>();
        if (this.item.getBids().size() > 0) {
            lore.add(ChatColor.GRAY + "Total bids: " + ChatColor.GREEN + this.item.getBids().size() + " bid" + (this.item.getBids().size() != 1 ? "s" : ""));
            for (int i = this.item.getBids().size() - 1; i >= 0; --i) {
                AuctionBid bid = this.item.getBids().get(i);
                lore.add(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "------------");
                lore.add(ChatColor.GRAY + "Bid: " + ChatColor.GOLD + bid.getAmount() + " coin" + (bid.getAmount() != 1L ? "s" : ""));
                lore.add(ChatColor.GRAY + "By: " + ChatColor.GREEN + Bukkit.getOfflinePlayer((UUID)bid.getBidder()).getName());
                lore.add(ChatColor.AQUA + SUtil.getAuctionSetupFormattedTime(bid.timeSinceBid()).toLowerCase() + " ago");
            }
        } else {
            lore.add(ChatColor.GRAY + "No bids have been placed on");
            lore.add(ChatColor.GRAY + "this item yet.");
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Be the first to bid on it!");
        }
        items.add(new GUIItem(){

            @Override
            public int getSlot() {
                return 33;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.WHITE + "Bid History", Material.MAP, (short)0, 1, lore);
            }
        });
        return items;
    }
}

