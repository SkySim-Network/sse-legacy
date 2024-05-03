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
import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.collection.ItemCollection;
import vn.giakhanhvn.skysim.collection.ItemCollectionReward;
import vn.giakhanhvn.skysim.collection.ItemCollectionRewards;
import vn.giakhanhvn.skysim.gui.CategoryCollectionGUI;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class ItemCollectionGUI
extends GUI {
    private final ItemCollection collection;

    public ItemCollectionGUI(ItemCollection collection) {
        super(collection.getName() + " Collection", 54);
        this.collection = collection;
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        int t;
        this.fill(BLACK_STAINED_GLASS_PANE);
        Player player = e.getPlayer();
        User user = User.getUser(player.getUniqueId());
        int amount = user.getCollection(this.collection);
        int tier = this.collection.getTier(amount);
        this.set(4, SUtil.getStack(ChatColor.YELLOW + this.collection.getName() + " " + SUtil.toRomanNumeral(tier), this.collection.getMaterial().getCraftMaterial(), this.collection.getData(), 1, ChatColor.GRAY + "View all your " + this.collection.getName() + " Collection", ChatColor.GRAY + "progress and rewards!", " ", ChatColor.GRAY + "Total Collected: " + ChatColor.YELLOW + SUtil.commaify(amount)));
        this.set(GUIClickableItem.getCloseItem(49));
        this.set(GUIClickableItem.createGUIOpenerItem(new CategoryCollectionGUI(this.collection.getCategory()), player, ChatColor.GREEN + "Go Back", 48, Material.ARROW, (short)0, ChatColor.GRAY + "To " + this.collection.getCategory().getName() + " Collection"));
        int i = 0;
        int slot = 18;
        while (i < this.collection.getRewards().size() && (t = i + 1) != 28) {
            ItemCollectionRewards rewards = this.collection.getRewards().get(i);
            if (rewards != null) {
                final int finalSlot = slot;
                ChatColor color = ChatColor.RED;
                short data = 14;
                if (amount >= rewards.getRequirement()) {
                    color = ChatColor.GREEN;
                    data = 5;
                }
                if (tier + 1 == t) {
                    color = ChatColor.YELLOW;
                    data = 4;
                }
                final ChatColor finalColor = color;
                final short finalData = data;
                final ArrayList<String> lore = new ArrayList<String>(Arrays.asList(" ", SUtil.createProgressText("Progress", amount, rewards.getRequirement()), SUtil.createLineProgressBar(20, ChatColor.DARK_GREEN, amount, rewards.getRequirement()), " "));
                if (rewards.size() != 0) {
                    lore.add(ChatColor.GRAY + "Reward" + (rewards.size() != 1 ? "s" : "") + ":");
                    for (ItemCollectionReward reward : rewards) {
                        lore.add(ChatColor.GRAY + " " + reward.toRewardString());
                    }
                    lore.add(" ");
                }
                lore.add(ChatColor.YELLOW + "Click to view rewards!");
                int finalT = t;
                this.set(new GUIClickableItem(){

                    @Override
                    public void run(InventoryClickEvent e) {
                    }

                    @Override
                    public int getSlot() {
                        return finalSlot;
                    }

                    @Override
                    public ItemStack getItem() {
                        return SUtil.getStack(finalColor + ItemCollectionGUI.this.collection.getName() + " " + SUtil.toRomanNumeral(finalT), Material.STAINED_GLASS_PANE, finalData, finalT, lore);
                    }
                });
            }
            ++i;
            ++slot;
        }
    }
}

