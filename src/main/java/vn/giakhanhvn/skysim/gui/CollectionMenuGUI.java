/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.gui;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.collection.ItemCollection;
import vn.giakhanhvn.skysim.collection.ItemCollectionCategory;
import vn.giakhanhvn.skysim.gui.CategoryCollectionGUI;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class CollectionMenuGUI
extends GUI {
    public CollectionMenuGUI() {
        super("Collection", 54);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        this.fill(BLACK_STAINED_GLASS_PANE);
        Player player = e.getPlayer();
        User user = User.getUser(player.getUniqueId());
        this.set(GUIClickableItem.getCloseItem(49));
        AtomicInteger found = new AtomicInteger();
        Collection<ItemCollection> collections = ItemCollection.getCollections();
        for (ItemCollection collection : collections) {
            if (user.getCollection(collection) <= 0) continue;
            found.incrementAndGet();
        }
        this.set(GUIClickableItem.createGUIOpenerItem(GUIType.SKYBLOCK_MENU, player, ChatColor.GREEN + "Go Back", 48, Material.ARROW, ChatColor.GRAY + "To SkyBlock Menu"));
        String[] progress = ItemCollection.getProgress(player, null);
        this.set(4, SUtil.getStack(ChatColor.GREEN + "Collection", Material.PAINTING, (short)0, 1, ChatColor.GRAY + "View all of the items available", ChatColor.GRAY + "in SkyBlock. Collect more of an", ChatColor.GRAY + "item to unlock rewards on your", ChatColor.GRAY + "way to becoming a master of", ChatColor.GRAY + "SkyBlock!", " ", progress[0], progress[1]));
        this.set(CollectionMenuGUI.createCollectionClickable(new CategoryCollectionGUI(ItemCollectionCategory.FARMING), ItemCollectionCategory.FARMING, Material.GOLD_HOE, 20, player));
        this.set(CollectionMenuGUI.createCollectionClickable(new CategoryCollectionGUI(ItemCollectionCategory.MINING), ItemCollectionCategory.MINING, Material.STONE_PICKAXE, 21, player));
        this.set(CollectionMenuGUI.createCollectionClickable(new CategoryCollectionGUI(ItemCollectionCategory.COMBAT), ItemCollectionCategory.COMBAT, Material.STONE_SWORD, 22, player));
        this.set(CollectionMenuGUI.createCollectionClickable(new CategoryCollectionGUI(ItemCollectionCategory.FORAGING), ItemCollectionCategory.FORAGING, Material.SAPLING, (short)3, 23, player));
        this.set(CollectionMenuGUI.createCollectionClickable(new CategoryCollectionGUI(ItemCollectionCategory.FISHING), ItemCollectionCategory.FISHING, Material.FISHING_ROD, 24, player));
    }

    private static GUIClickableItem createCollectionClickable(GUI gui, ItemCollectionCategory category, Material icon, short data, int slot, Player player) {
        String[] progress = ItemCollection.getProgress(player, category);
        return GUIClickableItem.createGUIOpenerItem(gui, player, ChatColor.GREEN + category.getName() + " Collection", slot, icon, data, ChatColor.GRAY + "View your " + category.getName() + " Collection!", " ", progress[0], progress[1], " ", ChatColor.YELLOW + "Click to view!");
    }

    private static GUIClickableItem createCollectionClickable(GUI gui, ItemCollectionCategory category, Material icon, int slot, Player player) {
        return CollectionMenuGUI.createCollectionClickable(gui, category, icon, (short)0, slot, player);
    }
}

