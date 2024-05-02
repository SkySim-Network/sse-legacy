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

import java.util.concurrent.atomic.AtomicBoolean;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.gui.CreateAuctionGUI;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIQueryItem;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class AuctionDurationGUI
extends GUI {
    public AuctionDurationGUI() {
        super("Auction Duration", 36);
        this.fill(BLACK_STAINED_GLASS_PANE);
    }

    @Override
    public void onOpen(final GUIOpenEvent e) {
        final User user = User.getUser(e.getPlayer().getUniqueId());
        this.set(AuctionDurationGUI.createTime((short)14, 1, 10, user));
        this.set(AuctionDurationGUI.createTime((short)6, 6, 11, user));
        this.set(AuctionDurationGUI.createTime((short)1, 12, 12, user));
        this.set(AuctionDurationGUI.createTime((short)4, 24, 13, user));
        this.set(AuctionDurationGUI.createTime((short)0, 48, 14, user));
        final AtomicBoolean right = new AtomicBoolean();
        this.set(new GUIQueryItem(){

            @Override
            public GUI onQueryFinish(String query) {
                long l;
                try {
                    l = Long.parseLong(query);
                    if (l <= 0L) {
                        e.getPlayer().sendMessage(ChatColor.RED + "Could not read this number!");
                        return null;
                    }
                    if (right.get() && l >= 0L && l > 1728L) {
                        e.getPlayer().sendMessage(ChatColor.RED + "You cannot put up an auction for more than 3 days!");
                        return null;
                    }
                    if (!right.get() && l >= 0L && l > 72L) {
                        e.getPlayer().sendMessage(ChatColor.RED + "You cannot put up an auction for more than 3 days!");
                        return null;
                    }
                }
                catch (NumberFormatException ex) {
                    e.getPlayer().sendMessage(ChatColor.RED + "Could not read this number!");
                    return null;
                }
                user.getAuctionEscrow().setDuration(l * (long)(right.get() ? 60000 : 3600000));
                return new CreateAuctionGUI();
            }

            @Override
            public void run(InventoryClickEvent e2) {
                if (e2.isRightClick()) {
                    right.set(true);
                }
            }

            @Override
            public int getSlot() {
                return 16;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Custom Duration", Material.WATCH, (short)0, 1, ChatColor.GRAY + "Specify how long you want", ChatColor.GRAY + "the auction to last.", " ", ChatColor.AQUA + "Right-click for minutes!", ChatColor.YELLOW + "Click to set hours!");
            }
        });
        this.set(GUIClickableItem.createGUIOpenerItem(GUIType.CREATE_AUCTION, e.getPlayer(), ChatColor.GREEN + "Go Back", 31, Material.ARROW, (short)0, ChatColor.GRAY + "To Create " + (user.isAuctionCreationBIN() ? "BIN " : "") + "Auction"));
    }

    private static GUIClickableItem createTime(final short color, int hours, final int slot, final User user) {
        final long millis = (long)hours * 3600000L;
        return new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                user.getAuctionEscrow().setDuration(millis);
                new AuctionDurationGUI().open((Player)e.getWhoClicked());
            }

            @Override
            public int getSlot() {
                return slot;
            }

            @Override
            public ItemStack getItem() {
                ItemStack stack = SUtil.getStack(ChatColor.GREEN + SUtil.getAuctionSetupFormattedTime(millis), Material.STAINED_CLAY, color, 1, ChatColor.YELLOW + "Click to pick!");
                if (user.getAuctionEscrow().getDuration() == millis) {
                    SUtil.enchant(stack);
                }
                return stack;
            }
        };
    }
}

