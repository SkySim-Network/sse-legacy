/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.gui.BlockBasedGUI;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.item.MaterialQuantifiable;
import vn.giakhanhvn.skysim.item.Recipe;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.SUtil;

public class CraftingTableGUI
extends GUI
implements BlockBasedGUI {
    private static final ItemStack RECIPE_REQUIRED = SUtil.createNamedItemStack(Material.BARRIER, ChatColor.RED + "Recipe Required");
    private static final int[] CRAFT_SLOTS = new int[]{10, 11, 12, 19, 20, 21, 28, 29, 30};
    private static final int RESULT_SLOT = 24;

    public CraftingTableGUI() {
        super("Craft Item", 54);
        this.fill(BLACK_STAINED_GLASS_PANE, 13, 34);
        this.border(RED_STAINED_GLASS_PANE);
        this.border(BLACK_STAINED_GLASS_PANE, 0, 44);
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                MaterialQuantifiable material;
                int ind;
                ItemStack stack;
                Recipe<?> recipe;
                boolean shift = e.isShiftClick();
                Inventory inventory = e.getClickedInventory();
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.BARRIER || e.getCurrentItem().getType() == Material.AIR) {
                    return;
                }
                ItemStack result = inventory.getItem(24);
                if (result == null || result.getType() == Material.AIR) {
                    return;
                }
                SItem item = SItem.find(result);
                if (!shift) {
                    if (e.getCursor() != null && e.getCursor().getType() != Material.AIR) {
                        SItem cursor = SItem.find(e.getCursor());
                        if (cursor == null) {
                            cursor = SItem.convert(e.getCursor());
                        }
                        if (!item.equals(cursor)) {
                            return;
                        }
                        if (e.getCursor().getAmount() + result.getAmount() > 64) {
                            return;
                        }
                        e.getCursor().setAmount(e.getCursor().getAmount() + result.getAmount());
                    } else {
                        e.getWhoClicked().setItemOnCursor(result);
                    }
                }
                if ((recipe = Recipe.parseRecipe(CraftingTableGUI.this.getCurrentRecipe(inventory))) == null) {
                    return;
                }
                List<MaterialQuantifiable> ingredients = recipe.getIngredients();
                ArrayList<MaterialQuantifiable> materials = new ArrayList<MaterialQuantifiable>(ingredients.size());
                for (MaterialQuantifiable materialQuantifiable : ingredients) {
                    materials.add(materialQuantifiable.clone());
                }
                int max = Integer.MAX_VALUE;
                if (shift) {
                    for (int slot : CRAFT_SLOTS) {
                        stack = inventory.getItem(slot);
                        ind = CraftingTableGUI.indexOf(recipe, materials, MaterialQuantifiable.of(stack));
                        if (ind == -1) continue;
                        material = materials.get(ind);
                        int m = stack.getAmount() / material.getAmount();
                        if (m >= max) continue;
                        max = m;
                    }
                }
                for (int slot : CRAFT_SLOTS) {
                    stack = inventory.getItem(slot);
                    ind = CraftingTableGUI.indexOf(recipe, materials, MaterialQuantifiable.of(stack));
                    if (ind == -1) continue;
                    material = materials.get(ind);
                    int remaining = stack.getAmount() - material.getAmount();
                    if (shift) {
                        remaining = stack.getAmount() - material.getAmount() * max;
                    }
                    materials.remove(ind);
                    if (remaining <= 0) {
                        inventory.setItem(slot, new ItemStack(Material.AIR));
                        continue;
                    }
                    material.setAmount(remaining);
                    stack.setAmount(remaining);
                }
                if (shift) {
                    HashMap<Integer , ItemStack> hashMap = e.getWhoClicked().getInventory().addItem(SUtil.setStackAmount(result, result.getAmount() * max));
                    for (ItemStack stack2 : hashMap.values()) {
                        e.getWhoClicked().getWorld().dropItem(e.getWhoClicked().getLocation(), stack2).setVelocity(e.getWhoClicked().getLocation().getDirection());
                    }
                }
                CraftingTableGUI.this.update(inventory);
            }

            @Override
            public int getSlot() {
                return 24;
            }

            @Override
            public ItemStack getItem() {
                return RECIPE_REQUIRED;
            }

            @Override
            public boolean canPickup() {
                return false;
            }
        });
        this.set(GUIClickableItem.getCloseItem(49));
    }

    @Override
    public void onOpen(final GUIOpenEvent e) {
        new BukkitRunnable(){

            public void run() {
                final GUI gui = e.getOpened();
                GUI current = GUI.GUI_MAP.get(e.getPlayer().getUniqueId());
                InventoryView view = e.getPlayer().getOpenInventory();
                if (!(current instanceof CraftingTableGUI) || view == null) {
                    this.cancel();
                    return;
                }
                final Inventory inventory = view.getTopInventory();
                new BukkitRunnable(){

                    public void run() {
                        Recipe<?> recipe = Recipe.parseRecipe(CraftingTableGUI.this.getCurrentRecipe(inventory));
                        if (recipe == null) {
                            inventory.setItem(24, RECIPE_REQUIRED);
                            SUtil.border(inventory, gui, SUtil.createColoredStainedGlassPane((short)14, ChatColor.RESET + " "), 45, 48, true, false);
                            SUtil.border(inventory, gui, SUtil.createColoredStainedGlassPane((short)14, ChatColor.RESET + " "), 50, 53, true, false);
                            return;
                        }
                        SItem sItem = recipe.getResult();
                        inventory.setItem(24, sItem.getStack());
                        SUtil.border(inventory, gui, SUtil.createColoredStainedGlassPane((short)5, ChatColor.RESET + " "), 45, 48, true, false);
                        SUtil.border(inventory, gui, SUtil.createColoredStainedGlassPane((short)5, ChatColor.RESET + " "), 50, 53, true, false);
                    }
                }.runTaskLater(SkySimEngine.getPlugin(), 1L);
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
    }

    @Override
    public Material getBlock() {
        return Material.WORKBENCH;
    }

    private ItemStack[] getCurrentRecipe(Inventory inventory) {
        ItemStack[] stacks = new ItemStack[9];
        for (int i = 0; i < CRAFT_SLOTS.length; ++i) {
            stacks[i] = inventory.getItem(CRAFT_SLOTS[i]);
        }
        return stacks;
    }

    private static int indexOf(Recipe<?> recipe, List<MaterialQuantifiable> ingredients, MaterialQuantifiable search) {
        List<SMaterial> exchangeables = Recipe.getExchangeablesOf(search.getMaterial());
        for (int i = 0; i < ingredients.size(); ++i) {
            MaterialQuantifiable ingredient = ingredients.get(i);
            if (recipe.isUseExchangeables() && exchangeables != null && exchangeables.contains(ingredient.getMaterial()) && search.getAmount() >= ingredient.getAmount()) {
                return i;
            }
            if (ingredient.getMaterial() != search.getMaterial() || search.getAmount() < ingredient.getAmount()) continue;
            return i;
        }
        return -1;
    }
}

