/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.Reforgable;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.reforge.ReforgeType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class ReforgeAnvilGUI
extends GUI {
    private static final ItemStack DEFAULT_REFORGE_ITEM = SUtil.getStack(ChatColor.YELLOW + "Reforge Item", Material.ANVIL, (short)0, 1, ChatColor.GRAY + "Place an item above to reforge", ChatColor.GRAY + "it! Reforging items adds a", ChatColor.GRAY + "random modifier to the item that", ChatColor.GRAY + "grants stat boosts.");
    private static final Map<Rarity, Integer> COST_MAP = new HashMap<Rarity, Integer>();
    private static final List<UUID> COOLDOWN = new ArrayList<UUID>();

    public void fillFrom(Inventory i, int startFromSlot, int height, ItemStack stacc) {
        i.setItem(startFromSlot, stacc);
        i.setItem(startFromSlot + 9, stacc);
        i.setItem(startFromSlot + 9 + 9, stacc);
        i.setItem(startFromSlot + 9 + 9 + 9, stacc);
        i.setItem(startFromSlot + 9 + 9 + 9 + 9, stacc);
    }

    public ReforgeAnvilGUI() {
        super("Reforge Item", 45);
        this.fill(BLACK_STAINED_GLASS_PANE);
        this.set(GUIClickableItem.getCloseItem(40));
        this.set(new GUIClickableItem(){

            @Override
            public int getSlot() {
                return 22;
            }

            @Override
            public ItemStack getItem() {
                return DEFAULT_REFORGE_ITEM;
            }

            @Override
            public boolean canPickup() {
                return false;
            }

            @Override
            public void run(InventoryClickEvent e) {
                SItem sItem = SItem.find(e.getClickedInventory().getItem(13));
                if (sItem == null) {
                    return;
                }
                if (!(sItem.getType().getGenericInstance() instanceof Reforgable)) {
                    return;
                }
                List possible = Arrays.stream(ReforgeType.values()).filter(type -> type.getReforge().getCompatibleTypes().contains((Object)sItem.getType().getStatistics().getType()) && type.isAccessible()).collect(Collectors.toList());
                final Player player = (Player)e.getWhoClicked();
                if (possible.size() == 0) {
                    player.sendMessage(ChatColor.RED + "That item cannot be reforged!");
                    return;
                }
                if (COOLDOWN.contains(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "Please wait a little bit before doing this!");
                    return;
                }
                User user = User.getUser(player.getUniqueId());
                int cost = (Integer)COST_MAP.get((Object)sItem.getRarity());
                if (user.getCoins() - (long)cost < 0L) {
                    player.sendMessage(ChatColor.RED + "You cannot afford to reforge this!");
                    return;
                }
                String prev = sItem.getFullName();
                user.subCoins(cost);
                sItem.setReforge(((ReforgeType)((Object)possible.get(SUtil.random(0, possible.size() - 1)))).getReforge());
                player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0f, 1.0f);
                player.sendMessage(ChatColor.GREEN + "You reforged your " + prev + ChatColor.GREEN + " into a " + sItem.getFullName() + ChatColor.GREEN + "!");
                COOLDOWN.add(player.getUniqueId());
                new BukkitRunnable(){

                    public void run() {
                        COOLDOWN.remove(player.getUniqueId());
                    }
                }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 20L);
            }
        });
        this.set(13, null);
    }

    @Override
    public void update(final Inventory inventory) {
        new BukkitRunnable(){

            public void run() {
                SItem sItem = SItem.find(inventory.getItem(13));
                if (sItem == null) {
                    inventory.setItem(22, DEFAULT_REFORGE_ITEM);
                    return;
                }
                ItemStack stack = SUtil.getStack(ChatColor.GREEN + "Reforge Item", Material.ANVIL, (short)0, 1, ChatColor.GRAY + "Reforges the above item, giving", ChatColor.GRAY + "it a random item modifier that", ChatColor.GRAY + "boosts its stats.", " ", ChatColor.GRAY + "Cost", ChatColor.GOLD + SUtil.commaify((Integer)COST_MAP.get((Object)sItem.getRarity())) + " Coins", " ", ChatColor.YELLOW + "Click to reforge!");
                if (!sItem.isReforgable()) {
                    stack = SUtil.getStack(ChatColor.RED + "Error!", Material.BARRIER, (short)0, 1, ChatColor.GRAY + "You cannot reforge this item!");
                }
                inventory.setItem(22, stack);
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 1L);
    }

    @Override
    public void onOpen(final GUIOpenEvent e) {
        new BukkitRunnable(){

            public void run() {
                Player player = e.getPlayer();
                if (ReforgeAnvilGUI.this != GUI.GUI_MAP.get(player.getUniqueId())) {
                    this.cancel();
                    return;
                }
                Inventory inventory = e.getInventory();
                SItem sItem = SItem.find(inventory.getItem(13));
                if (sItem == null) {
                    ReforgeAnvilGUI.this.fillFrom(inventory, 0, 5, SUtil.createColoredStainedGlassPane((short)14, ChatColor.RESET + " "));
                    ReforgeAnvilGUI.this.fillFrom(inventory, 8, 5, SUtil.createColoredStainedGlassPane((short)14, ChatColor.RESET + " "));
                    return;
                }
                if (sItem.isReforgable()) {
                    ReforgeAnvilGUI.this.fillFrom(inventory, 0, 5, SUtil.createColoredStainedGlassPane((short)5, ChatColor.RESET + " "));
                    ReforgeAnvilGUI.this.fillFrom(inventory, 8, 5, SUtil.createColoredStainedGlassPane((short)5, ChatColor.RESET + " "));
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 5L);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player)e.getPlayer();
        GUI gui = GUI.GUI_MAP.get(player.getUniqueId());
        if (gui == null) {
            return;
        }
        gui.onClose(e);
        GUI.GUI_MAP.remove(player.getUniqueId());
    }

    static {
        COST_MAP.put(Rarity.COMMON, 250);
        COST_MAP.put(Rarity.UNCOMMON, 500);
        COST_MAP.put(Rarity.RARE, 1000);
        COST_MAP.put(Rarity.EPIC, 2500);
        COST_MAP.put(Rarity.LEGENDARY, 5000);
        COST_MAP.put(Rarity.MYTHIC, 10000);
        COST_MAP.put(Rarity.SUPREME, 15000);
        COST_MAP.put(Rarity.SPECIAL, 25000);
        COST_MAP.put(Rarity.VERY_SPECIAL, 50000);
        COST_MAP.put(Rarity.EXCLUSIVE, 1000000);
    }
}

