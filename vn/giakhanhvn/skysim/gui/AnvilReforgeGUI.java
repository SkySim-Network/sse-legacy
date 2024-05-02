/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.gui;

import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.enchantment.Enchantment;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.gui.BlockBasedGUI;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.skill.EnchantingSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class AnvilReforgeGUI
extends GUI
implements BlockBasedGUI {
    private static final ItemStack ANVIL_BARRIER = SUtil.getSingleLoreStack(ChatColor.RED + "Reforge Anvil", Material.BARRIER, (short)0, 1, "Place a target item in the left slot and a sacrifice item in the right slot to combine Reforge Stones!");
    private static final ItemStack DEFAULT_COMBINE_ITEMS = SUtil.getStack(ChatColor.GREEN + "Combine Items", Material.ANVIL, (short)0, 1, ChatColor.GRAY + "Combine the items in the slots", ChatColor.GRAY + "to the left and right below.");
    private static final String CANNOT_COMBINE = ChatColor.RED + "These items cannot be combined!";
    private boolean isApplied = false;

    public AnvilReforgeGUI() {
        super("Reforge Anvil", 54);
        this.fill(BLACK_STAINED_GLASS_PANE);
        this.fill(RED_STAINED_GLASS_PANE, 45, 53);
        this.set(GUIClickableItem.getCloseItem(49));
        for (int i : Arrays.asList(11, 12, 20)) {
            this.set(i, SUtil.getSingleLoreStack(ChatColor.GOLD + "Item to Upgrade", Material.STAINED_GLASS_PANE, (short)14, 1, "The item you want to upgrade should be placed in the slot on this side."));
        }
        for (int i : Arrays.asList(14, 15, 24)) {
            this.set(i, SUtil.getSingleLoreStack(ChatColor.GOLD + "Item to Sacrifice", Material.STAINED_GLASS_PANE, (short)14, 1, "The item you are sacrificing in order to upgrade the item on the left should be placed in the slot on this side."));
        }
        this.set(29, null);
        this.set(33, null);
        this.set(new GUIClickableItem(){

            @Override
            public void run(final InventoryClickEvent e) {
                ItemStack current = e.getCurrentItem();
                if (current == null) {
                    return;
                }
                if (e.getCurrentItem().getType() == Material.BARRIER) {
                    e.setCancelled(true);
                    return;
                }
                final Inventory inventory = e.getClickedInventory();
                if (!SUtil.isAir(inventory.getItem(29)) || !SUtil.isAir(inventory.getItem(33))) {
                    e.setCancelled(true);
                    return;
                }
                new BukkitRunnable(){

                    public void run() {
                        inventory.setItem(e.getSlot(), ANVIL_BARRIER);
                    }
                }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 1L);
            }

            @Override
            public int getSlot() {
                return 13;
            }

            @Override
            public boolean canPickup() {
                return true;
            }

            @Override
            public ItemStack getItem() {
                return ANVIL_BARRIER;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                HumanEntity entity = e.getWhoClicked();
                Inventory inventory = e.getClickedInventory();
                ItemStack upgrade = inventory.getItem(29);
                ItemStack sacrifice = inventory.getItem(33);
                if (upgrade == null || sacrifice == null) {
                    entity.sendMessage(CANNOT_COMBINE);
                    return;
                }
                SItem specUpgrade = SItem.find(upgrade);
                SItem specSacrifice = SItem.find(sacrifice);
                if (inventory.getItem(11).getData().getData() == 14 || inventory.getItem(14).getData().getData() == 14) {
                    entity.sendMessage(CANNOT_COMBINE);
                    return;
                }
                if (specSacrifice.getType() == SMaterial.HOT_POTATO_BOOK && specUpgrade.getDataInt("hpb") == 9) {
                    entity.sendMessage(Sputnik.trans("&7You have already applied the maximum number of Hot Potato books to this item! &eFuming Hot Potato Book coming soon!"));
                }
                if (specSacrifice.getType() == SMaterial.ENCHANTED_BOOK) {
                    for (Enchantment enchantment : specSacrifice.getEnchantments()) {
                        Skill.reward(EnchantingSkill.INSTANCE, enchantment.getLevel() * 2, (Player)e.getWhoClicked());
                    }
                }
                inventory.setItem(22, DEFAULT_COMBINE_ITEMS);
                AnvilReforgeGUI.this.isApplied = true;
                AnvilReforgeGUI.setItemTo(true, false, inventory);
                AnvilReforgeGUI.setItemTo(false, false, inventory);
                inventory.setItem(29, null);
                inventory.setItem(33, null);
                entity.getWorld().playSound(entity.getLocation(), Sound.ANVIL_USE, 1.0f, 1.0f);
            }

            @Override
            public int getSlot() {
                return 22;
            }

            @Override
            public ItemStack getItem() {
                return DEFAULT_COMBINE_ITEMS;
            }
        });
    }

    @Override
    public void onOpen(final GUIOpenEvent e) {
        new BukkitRunnable(){

            public void run() {
                Player player = e.getPlayer();
                if (AnvilReforgeGUI.this != GUI.GUI_MAP.get(player.getUniqueId())) {
                    this.cancel();
                    return;
                }
                Inventory inventory = e.getInventory();
                AnvilReforgeGUI.this.update(inventory);
                if (inventory.getItem(11).getData().getData() == 14 || inventory.getItem(14).getData().getData() == 14) {
                    SUtil.border(inventory, AnvilReforgeGUI.this, SUtil.createColoredStainedGlassPane((short)14, ChatColor.RESET + " "), 45, 48, true, false);
                    SUtil.border(inventory, AnvilReforgeGUI.this, SUtil.createColoredStainedGlassPane((short)14, ChatColor.RESET + " "), 50, 53, true, false);
                } else {
                    SUtil.border(inventory, AnvilReforgeGUI.this, SUtil.createColoredStainedGlassPane((short)5, ChatColor.RESET + " "), 45, 48, true, false);
                    SUtil.border(inventory, AnvilReforgeGUI.this, SUtil.createColoredStainedGlassPane((short)5, ChatColor.RESET + " "), 50, 53, true, false);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 5L);
    }

    @Override
    public void update(final Inventory inventory) {
        new BukkitRunnable(){

            public void run() {
                ItemStack select;
                if (inventory.getItem(13) == null) {
                    inventory.setItem(13, SUtil.getStack(ChatColor.RED + "Loading...", Material.BARRIER, (short)0, 1, ChatColor.GRAY + "If this appear for too long", ChatColor.GRAY + "contact admins!"));
                }
                if (inventory.getItem(29) == null && inventory.getItem(33) == null && inventory.getItem(13).getType() == Material.BARRIER && AnvilReforgeGUI.this.isApplied) {
                    AnvilReforgeGUI.this.isApplied = false;
                }
                if ((inventory.getItem(29) != null || inventory.getItem(33) != null) && AnvilReforgeGUI.this.isApplied) {
                    Sputnik.smartGiveItem(inventory.getItem(13), (Player)inventory.getViewers().get(0));
                    inventory.setItem(13, SUtil.getStack(ChatColor.RED + "Loading...", Material.BARRIER, (short)0, 1, ChatColor.GRAY + "If this appear for too long", ChatColor.GRAY + "contact admins!"));
                    AnvilReforgeGUI.this.isApplied = false;
                }
                if ((select = inventory.getItem(13)) != null && select.getType() != Material.BARRIER && SUtil.isAir(inventory.getItem(29)) && SUtil.isAir(inventory.getItem(33))) {
                    return;
                }
                SItem specUpgrade = SItem.find(inventory.getItem(29));
                SItem specSacrifice = SItem.find(inventory.getItem(33));
                boolean upgradeGreen = false;
                boolean sacrificeGreen = false;
                if (specSacrifice == null && specUpgrade != null) {
                    upgradeGreen = true;
                }
                if (specUpgrade != null) {
                    if (SUtil.isEnchantable(specUpgrade) || SUtil.isHotPotatoAble(specUpgrade)) {
                        upgradeGreen = true;
                    }
                    if (specUpgrade.getType() == SMaterial.HIDDEN_ETHERWARP_CONDUIT) {
                        upgradeGreen = true;
                    }
                    if (specUpgrade.getType() == SMaterial.HOT_POTATO_BOOK) {
                        upgradeGreen = false;
                    }
                }
                if (specSacrifice != null) {
                    if (SUtil.isEnchantable(specSacrifice)) {
                        sacrificeGreen = true;
                    } else if (specSacrifice.getType() == SMaterial.HOT_POTATO_BOOK) {
                        sacrificeGreen = true;
                    } else if (specSacrifice.getType().toString().contains("HIDDEN_ETHERWARP")) {
                        sacrificeGreen = true;
                    }
                }
                if (specUpgrade != null && specSacrifice != null) {
                    if (!SUtil.isHotPotatoAble(specUpgrade) && specSacrifice.getType() == SMaterial.HOT_POTATO_BOOK) {
                        upgradeGreen = false;
                        sacrificeGreen = false;
                    }
                    if (!SUtil.isEnchantable(specUpgrade) && specSacrifice.getType() == SMaterial.ENCHANTED_BOOK) {
                        upgradeGreen = false;
                        sacrificeGreen = false;
                    }
                    if (specUpgrade.getType() == SMaterial.HIDDEN_ETHERWARP_CONDUIT && specSacrifice.getType() == SMaterial.HIDDEN_ETHERWARP_MERGER) {
                        upgradeGreen = true;
                        sacrificeGreen = true;
                    }
                    if ((specUpgrade.getType() == SMaterial.ASPECT_OF_THE_VOID || specUpgrade.getType() == SMaterial.ASPECT_OF_THE_END) && specSacrifice.getType() == SMaterial.HIDDEN_ETHERWARP_TRANSCODER) {
                        upgradeGreen = true;
                        sacrificeGreen = true;
                    }
                }
                AnvilReforgeGUI.setItemTo(true, upgradeGreen, inventory);
                AnvilReforgeGUI.setItemTo(false, sacrificeGreen, inventory);
                if (upgradeGreen && sacrificeGreen) {
                    if (specUpgrade.getType() == SMaterial.HIDDEN_ETHERWARP_CONDUIT && specSacrifice.getType() != SMaterial.HIDDEN_ETHERWARP_MERGER) {
                        AnvilReforgeGUI.setItemTo(false, false, inventory);
                        inventory.setItem(13, ANVIL_BARRIER);
                        inventory.setItem(22, DEFAULT_COMBINE_ITEMS);
                        return;
                    }
                    if ((specUpgrade.getType() == SMaterial.ASPECT_OF_THE_END || specUpgrade.getType() == SMaterial.ASPECT_OF_THE_VOID) && specSacrifice.getType() != SMaterial.HIDDEN_ETHERWARP_TRANSCODER && specSacrifice.getType() != SMaterial.ENCHANTED_BOOK && specSacrifice.getType() != SMaterial.HOT_POTATO_BOOK) {
                        AnvilReforgeGUI.setItemTo(false, false, inventory);
                        inventory.setItem(13, ANVIL_BARRIER);
                        inventory.setItem(22, DEFAULT_COMBINE_ITEMS);
                        return;
                    }
                    if (specUpgrade.getType() != SMaterial.ASPECT_OF_THE_END && specUpgrade.getType() != SMaterial.ASPECT_OF_THE_VOID && specSacrifice.getType() == SMaterial.HIDDEN_ETHERWARP_TRANSCODER) {
                        AnvilReforgeGUI.setItemTo(false, false, inventory);
                        inventory.setItem(13, ANVIL_BARRIER);
                        inventory.setItem(22, DEFAULT_COMBINE_ITEMS);
                        return;
                    }
                    if (specUpgrade.getType() != specSacrifice.getType() && specSacrifice.getType() != SMaterial.ENCHANTED_BOOK && specSacrifice.getType() != SMaterial.HOT_POTATO_BOOK && specSacrifice.getType() != SMaterial.HIDDEN_ETHERWARP_MERGER && specSacrifice.getType() != SMaterial.HIDDEN_ETHERWARP_TRANSCODER) {
                        AnvilReforgeGUI.setItemTo(false, false, inventory);
                        inventory.setItem(13, ANVIL_BARRIER);
                        inventory.setItem(22, DEFAULT_COMBINE_ITEMS);
                        return;
                    }
                    if ((specUpgrade.getType() == SMaterial.ASPECT_OF_THE_END || specUpgrade.getType() == SMaterial.ASPECT_OF_THE_VOID) && specSacrifice.getType() == SMaterial.HIDDEN_ETHERWARP_TRANSCODER && specUpgrade.getDataString("etherwarp_trans") == "true") {
                        AnvilReforgeGUI.setItemTo(false, false, inventory);
                        inventory.setItem(13, ANVIL_BARRIER);
                        inventory.setItem(22, DEFAULT_COMBINE_ITEMS);
                        return;
                    }
                    if (specUpgrade.getType() != SMaterial.ENCHANTED_BOOK && specSacrifice.getType() != SMaterial.HOT_POTATO_BOOK && !specSacrifice.getType().toString().contains("ETHER")) {
                        for (Enchantment enchantment : specSacrifice.getEnchantments()) {
                            if (enchantment.getType().getCompatibleTypes().contains((Object)specUpgrade.getType().getStatistics().getSpecificType())) continue;
                            AnvilReforgeGUI.setItemTo(true, false, inventory);
                            inventory.setItem(13, ANVIL_BARRIER);
                            inventory.setItem(22, DEFAULT_COMBINE_ITEMS);
                            return;
                        }
                    }
                    SItem display = specUpgrade.clone();
                    if (specSacrifice.getType() != SMaterial.HOT_POTATO_BOOK && !specSacrifice.getType().toString().contains("ETHER")) {
                        List<Enchantment> list;
                        for (Enchantment enchantment : specSacrifice.getEnchantments()) {
                            Enchantment equiv = display.getEnchantment(enchantment.getType());
                            if (equiv != null && equiv.getLevel() == enchantment.getLevel()) {
                                display.removeEnchantment(enchantment.getType());
                                if (enchantment.getType() == EnchantmentType.ONE_FOR_ALL || enchantment.getType() == EnchantmentType.TELEKINESIS) {
                                    display.addEnchantment(enchantment.getType(), enchantment.getLevel());
                                    continue;
                                }
                                if (enchantment.getLevel() < 5) {
                                    display.addEnchantment(enchantment.getType(), enchantment.getLevel() + 1);
                                    continue;
                                }
                                display.addEnchantment(enchantment.getType(), enchantment.getLevel());
                                continue;
                            }
                            display.addEnchantment(enchantment.getType(), enchantment.getLevel());
                        }
                        if (specSacrifice.getEnchantment(EnchantmentType.ONE_FOR_ALL) != null && specUpgrade.getType() != SMaterial.ENCHANTED_BOOK) {
                            for (Enchantment enchantment : specUpgrade.getEnchantments()) {
                                if (enchantment.getType() == EnchantmentType.TELEKINESIS) continue;
                                display.removeEnchantment(enchantment.getType());
                                display.addEnchantment(EnchantmentType.ONE_FOR_ALL, specSacrifice.getEnchantment(EnchantmentType.ONE_FOR_ALL).getLevel());
                            }
                        }
                        if (display.getEnchantment(EnchantmentType.ONE_FOR_ALL) != null && display.getType() != SMaterial.ENCHANTED_BOOK && display.getType().getStatistics().getType() == GenericItemType.WEAPON) {
                            for (Enchantment enchantment : display.getEnchantments()) {
                                if (enchantment.getType() == EnchantmentType.TELEKINESIS || enchantment.getType() == EnchantmentType.ONE_FOR_ALL) continue;
                                display.removeEnchantment(enchantment.getType());
                            }
                        }
                        if (display.getType() != SMaterial.ENCHANTED_BOOK && display.getEnchantments() != null && (list = Enchantment.ultimateEnchantsListFromList(display.getEnchantments())).size() > 1) {
                            for (int i = 1; i < list.size(); ++i) {
                                display.removeEnchantment(list.get(i).getType());
                            }
                        }
                    } else if (specSacrifice.getType() == SMaterial.HOT_POTATO_BOOK) {
                        display.setDataInt("hpb", display.getDataInt("hpb") + 1);
                    } else if (specUpgrade.getType() == SMaterial.HIDDEN_ETHERWARP_CONDUIT && specSacrifice.getType() == SMaterial.HIDDEN_ETHERWARP_MERGER) {
                        display = SItem.of(SMaterial.HIDDEN_ETHERWARP_TRANSCODER);
                    } else if ((specUpgrade.getType() == SMaterial.ASPECT_OF_THE_VOID || specUpgrade.getType() == SMaterial.ASPECT_OF_THE_END) && specSacrifice.getType() == SMaterial.HIDDEN_ETHERWARP_TRANSCODER) {
                        display.setDataString("etherwarp_trans", "true");
                    }
                    inventory.setItem(13, display.getStack());
                    inventory.setItem(22, AnvilReforgeGUI.getCombineItemsForXP(0));
                    return;
                }
                inventory.setItem(13, ANVIL_BARRIER);
                inventory.setItem(22, DEFAULT_COMBINE_ITEMS);
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 0L);
    }

    @Override
    public Material getBlock() {
        return Material.ANVIL;
    }

    private static void setItemTo(boolean upgrade, boolean green, Inventory inventory) {
        for (int i : upgrade ? Arrays.asList(11, 12, 20) : Arrays.asList(14, 15, 24)) {
            inventory.setItem(i, SUtil.getSingleLoreStack(ChatColor.GOLD + "Item to " + (upgrade ? "Upgrade" : "Sacrifice"), Material.STAINED_GLASS_PANE, green ? (short)5 : 14, 1, upgrade ? "The item you want to upgrade should be placed in the slot on this side." : "The item you are sacrificing in order to upgrade the item on the left should be placed in the slot on this side."));
        }
    }

    private static ItemStack getCombineItemsForXP(int levels) {
        return SUtil.getStack(ChatColor.GREEN + "Combine Items", Material.ANVIL, (short)0, 1, ChatColor.GRAY + "Combine the items in the slots", ChatColor.GRAY + "to the left and right below.", "", ChatColor.GRAY + "Cost", ChatColor.DARK_AQUA + "" + levels + " Exp Level" + (levels != 1 ? "s" : ""), "", ChatColor.YELLOW + "Click to combine!");
    }
}

