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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
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
import vn.giakhanhvn.skysim.gui.GUIQueryItem;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.item.ItemCategory;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.user.AuctionSettings;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.PaginationList;
import vn.giakhanhvn.skysim.util.SUtil;

public class AuctionsBrowserGUI
extends GUI {
    private static final int[] INTERIOR = new int[]{11, 12, 13, 14, 15, 16, 20, 21, 22, 23, 24, 25, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42, 43};
    private int page;

    public AuctionsBrowserGUI(int page) {
        super("Auctions Browser", 54);
        this.page = page;
    }

    public AuctionsBrowserGUI() {
        this(1);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        PaginationList result;
        final Player player = e.getPlayer();
        final User user = User.getUser(player.getUniqueId());
        final AuctionSettings settings = user.getAuctionSettings();
        final ItemCategory category = settings.getCategory();
        try {
            result = new PaginationList((Collection)AuctionItem.search(settings).get(), 24);
        }
        catch (InterruptedException | ExecutionException ex) {
            player.closeInventory();
            player.sendMessage(ChatColor.RED + "Something went wrong while talking to the Auction House service!");
            return;
        }
        final String browsing = ChatColor.GREEN + "Currently browsing!";
        final String view = ChatColor.YELLOW + "Click to view items!";
        List items = result.getPage(this.page);
        if (items == null) {
            items = new ArrayList();
        }
        if (items.size() == 0) {
            this.page = 0;
        }
        final int finalPage = this.page;
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                settings.setCategory(ItemCategory.WEAPONS);
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 0;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GOLD + "Weapons", Material.GOLD_SWORD, (short)0, 1, ChatColor.DARK_GRAY + "Category", " ", ChatColor.GRAY + "Examples:", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Swords", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Bows", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Axes", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Magic Weapons", " ", category == ItemCategory.WEAPONS ? browsing : view);
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                settings.setCategory(ItemCategory.ARMOR);
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 9;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.AQUA + "Armor", Material.DIAMOND_CHESTPLATE, (short)0, 1, ChatColor.DARK_GRAY + "Category", " ", ChatColor.GRAY + "Examples:", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Hats", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Chestplates", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Leggings", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Boots", " ", category == ItemCategory.ARMOR ? browsing : view);
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                settings.setCategory(ItemCategory.ACCESSORIES);
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 18;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getSkullURLStack(ChatColor.DARK_GREEN + "Accessories", "f36b821c1afdd5a5d14e3b3bd0a32263c8df5df5db6e1e88bf65e97b27a8530", 1, ChatColor.DARK_GRAY + "Category", " ", ChatColor.GRAY + "Examples:", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Talismans", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Rings", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Orbs", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Artifacts", " ", category == ItemCategory.ACCESSORIES ? browsing : view);
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                settings.setCategory(ItemCategory.CONSUMABLES);
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 27;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.RED + "Consumables", Material.APPLE, (short)0, 1, ChatColor.DARK_GRAY + "Category", " ", ChatColor.GRAY + "Examples:", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Potions", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Food", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Books", " ", category == ItemCategory.CONSUMABLES ? browsing : view);
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                settings.setCategory(ItemCategory.BLOCKS);
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 36;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.YELLOW + "Blocks", Material.COBBLESTONE, (short)0, 1, ChatColor.DARK_GRAY + "Category", " ", ChatColor.GRAY + "Examples:", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Dirt", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Stone", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Any blocks really", " ", category == ItemCategory.BLOCKS ? browsing : view);
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                settings.setCategory(ItemCategory.TOOLS_MISC);
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 45;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.LIGHT_PURPLE + "Tools & Misc", Material.STICK, (short)0, 1, ChatColor.DARK_GRAY + "Category", " ", ChatColor.GRAY + "Examples:", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Tools", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Specials", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Magic", ChatColor.DARK_GRAY + "\u25fc " + ChatColor.GRAY + "Staff items", " ", category == ItemCategory.TOOLS_MISC ? browsing : view);
            }
        });
        this.border(category.getStainedGlassPane(), 1, 53);
        if (this.page > 1) {
            this.set(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    if (e.isRightClick()) {
                        new AuctionsBrowserGUI(0).open(player);
                    } else {
                        new AuctionsBrowserGUI(finalPage - 1).open(player);
                    }
                }

                @Override
                public int getSlot() {
                    return 46;
                }

                @Override
                public ItemStack getItem() {
                    return SUtil.getStack(ChatColor.GREEN + "Previous Page", Material.ARROW, (short)0, 1, ChatColor.GRAY + "(" + finalPage + "/" + result.getPageCount() + ")", " ", ChatColor.AQUA + "Right-Click to skip!", ChatColor.YELLOW + "Click to turn page!");
                }
            });
        }
        if (this.page != result.getPageCount()) {
            this.set(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    if (e.isRightClick()) {
                        new AuctionsBrowserGUI(result.getPageCount()).open(player);
                    } else {
                        new AuctionsBrowserGUI(finalPage + 1).open(player);
                    }
                }

                @Override
                public int getSlot() {
                    return 53;
                }

                @Override
                public ItemStack getItem() {
                    return SUtil.getStack(ChatColor.GREEN + "Next Page", Material.ARROW, (short)0, 1, ChatColor.GRAY + "(" + finalPage + "/" + result.getPageCount() + ")", " ", ChatColor.AQUA + "Right-Click to skip!", ChatColor.YELLOW + "Click to turn page!");
                }
            });
        }
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                settings.setQuery(null);
                settings.setSort(AuctionSettings.Sort.HIGHEST_BID);
                settings.setTier(null);
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 47;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Reset Settings", Material.ANVIL, (short)0, 1, ChatColor.GRAY + "Clears your auction", ChatColor.GRAY + "browsing settings except", ChatColor.GRAY + "BIN Filter.", " ", ChatColor.YELLOW + "Click to clear!");
            }
        });
        this.set(new GUIQueryItem(){

            @Override
            public void run(InventoryClickEvent e) {
            }

            @Override
            public GUI onQueryFinish(String query) {
                settings.setQuery(query);
                return new AuctionsBrowserGUI();
            }

            @Override
            public int getSlot() {
                return 48;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Search", Material.SIGN, (short)0, 1, ChatColor.GRAY + "Find items by name, type,", ChatColor.GRAY + "lore or enchants.", " ", ChatColor.YELLOW + "Click to search!");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                if (e.isRightClick()) {
                    settings.setSort(settings.getSort().previous());
                } else {
                    settings.setSort(settings.getSort().next());
                }
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 50;
            }

            @Override
            public ItemStack getItem() {
                ArrayList<String> lore = new ArrayList<String>(Collections.singletonList(" "));
                for (AuctionSettings.Sort s : AuctionSettings.Sort.values()) {
                    if (settings.getSort() == s) {
                        lore.add(ChatColor.AQUA + "\u25b6 " + s.getDisplay());
                        continue;
                    }
                    lore.add(ChatColor.GRAY + s.getDisplay());
                }
                lore.add(" ");
                lore.add(ChatColor.AQUA + "Right-Click to go backwards!");
                lore.add(ChatColor.YELLOW + "Click to switch sort!");
                return SUtil.getStack(ChatColor.GREEN + "Sort", Material.HOPPER, (short)0, 1, lore);
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                Rarity tier = settings.getTier();
                if (e.isRightClick()) {
                    if (tier == null) {
                        settings.setTier(Rarity.values()[Rarity.values().length - 1]);
                    } else if (tier.ordinal() == 0) {
                        settings.setTier(null);
                    } else {
                        settings.setTier(tier.downgrade());
                    }
                } else if (tier == null) {
                    settings.setTier(Rarity.values()[0]);
                } else if (tier.ordinal() == Rarity.values().length - 1) {
                    settings.setTier(null);
                } else {
                    settings.setTier(tier.upgrade());
                }
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 51;
            }

            @Override
            public ItemStack getItem() {
                ArrayList<String> lore = new ArrayList<String>(Collections.singletonList(" "));
                if (settings.getTier() == null) {
                    lore.add(ChatColor.DARK_GRAY + "\u25b6 No filter");
                } else {
                    lore.add(ChatColor.GRAY + "No filter");
                }
                for (Rarity rarity : Rarity.values()) {
                    String normal = SUtil.toNormalCase(rarity.name());
                    if (settings.getTier() == rarity) {
                        lore.add(rarity.getColor() + "\u25b6 " + normal);
                        continue;
                    }
                    lore.add(ChatColor.GRAY + normal);
                }
                lore.add(" ");
                lore.add(ChatColor.AQUA + "Right-Click to go backwards!");
                lore.add(ChatColor.YELLOW + "Click to switch rarity!");
                return SUtil.getStack(ChatColor.GREEN + "Item Tier", Material.EYE_OF_ENDER, (short)0, 1, lore);
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                if (e.isRightClick()) {
                    settings.setType(settings.getType().previous());
                } else {
                    settings.setType(settings.getType().next());
                }
                new AuctionsBrowserGUI().open(player);
            }

            @Override
            public int getSlot() {
                return 52;
            }

            @Override
            public ItemStack getItem() {
                ArrayList<String> lore = new ArrayList<String>(Collections.singletonList(" "));
                for (AuctionSettings.Type t : AuctionSettings.Type.values()) {
                    if (settings.getType() == t) {
                        lore.add(ChatColor.DARK_AQUA + "\u25b6 " + t.getDisplay());
                        continue;
                    }
                    lore.add(ChatColor.GRAY + t.getDisplay());
                }
                lore.add(" ");
                lore.add(ChatColor.AQUA + "Right-Click to go backwards!");
                lore.add(ChatColor.YELLOW + "Click to switch filter!");
                return SUtil.getStack(ChatColor.GREEN + "BIN Filter", Material.GOLD_BLOCK, (short)0, 1, lore);
            }
        });
        this.set(GUIClickableItem.createGUIOpenerItem(GUIType.AUCTION_HOUSE, player, ChatColor.GREEN + "Go Back", 49, Material.ARROW, ChatColor.GRAY + "To Auction House"));
        for (int i = 0; i < items.size(); ++i) {
            final int slot = INTERIOR[i];
            final AuctionItem item = (AuctionItem)items.get(i);
            this.set(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    new AuctionViewGUI(item, AuctionsBrowserGUI.this).open(player);
                }

                @Override
                public int getSlot() {
                    return slot;
                }

                @Override
                public ItemStack getItem() {
                    return item.getDisplayItem(true, user.getUuid().equals(item.getOwner().getUuid()));
                }
            });
        }
    }
}

