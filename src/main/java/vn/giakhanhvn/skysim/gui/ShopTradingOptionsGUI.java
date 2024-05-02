/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package vn.giakhanhvn.skysim.gui;

import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class ShopTradingOptionsGUI
extends GUI {
    private final SItem item;
    private final GUI ret;

    public ShopTradingOptionsGUI(SItem item, GUI ret) {
        super("Shop Trading Options", 54);
        this.item = item;
        this.ret = ret;
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        Player player = e.getPlayer();
        this.fill(BLACK_STAINED_GLASS_PANE);
        this.set(ShopTradingOptionsGUI.createTrade(this.item, 20, 1, player));
        this.set(ShopTradingOptionsGUI.createTrade(this.item, 21, 5, player));
        this.set(ShopTradingOptionsGUI.createTrade(this.item, 22, 10, player));
        this.set(ShopTradingOptionsGUI.createTrade(this.item, 23, 32, player));
        this.set(ShopTradingOptionsGUI.createTrade(this.item, 24, 64, player));
        this.set(GUIClickableItem.createGUIOpenerItem(this.ret, player, ChatColor.GREEN + "Go Back", 48, Material.ARROW, (short)0, ChatColor.GRAY + "To " + this.ret.getTitle()));
        this.set(GUIClickableItem.getCloseItem(49));
    }

    private static GUIClickableItem createTrade(final SItem item, final int slot, final int amount, final Player player) {
        final User user = User.getUser(player.getUniqueId());
        final SItem display = item.clone();
        display.getStack().setAmount(amount);
        ItemMeta meta = display.getStack().getItemMeta();
        if (amount != 1) {
            meta.setDisplayName(meta.getDisplayName() + ChatColor.DARK_GRAY + " x" + amount);
        }
        List lore = meta.getLore();
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cost");
        final long price = item.getType().getStatistics().getPrice() * (long)amount;
        lore.add(ChatColor.GOLD + SUtil.commaify(price) + " Coin" + (price != 1L ? "s" : ""));
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Click to purchase!");
        meta.setLore(lore);
        display.getStack().setItemMeta(meta);
        return new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                if (price > user.getCoins()) {
                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                    return;
                }
                HashMap m = player.getInventory().addItem(new ItemStack[]{SUtil.setSItemAmount(item.clone(), amount).getStack()});
                if (m.size() != 0) {
                    player.sendMessage(ChatColor.RED + "Free up inventory space to purchase this!");
                    return;
                }
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 2.0f);
                user.subCoins(price);
            }

            @Override
            public int getSlot() {
                return slot;
            }

            @Override
            public ItemStack getItem() {
                return display.getStack();
            }
        };
    }
}

