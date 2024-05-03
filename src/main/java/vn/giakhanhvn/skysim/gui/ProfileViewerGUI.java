/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.gui;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.placeholding;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class ProfileViewerGUI
extends GUI {
    public Player p;

    public ProfileViewerGUI(Player player) {
        super(player.getName() + "'s Profile", 54);
        this.p = player;
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        if (this.p == null) {
            return;
        }
        this.fill(BLACK_STAINED_GLASS_PANE);
        final Player player = e.getPlayer();
        final User user = User.getUser(this.p.getUniqueId());
        this.set(GUIClickableItem.getCloseItem(49));
        this.set(new GUIItem(){

            @Override
            public int getSlot() {
                return 22;
            }

            @Override
            public ItemStack getItem() {
                placeholding pl = new placeholding();
                ItemStack itemstack = SUtil.getSkullStack(ProfileViewerGUI.this.p.getDisplayName(), ProfileViewerGUI.this.p.getName(), 1, Sputnik.trans("  &c\u2764 Health &f" + SUtil.commaify(Math.round(ProfileViewerGUI.this.p.getHealth())) + " HP"), Sputnik.trans("  &a\u2748 Defense&f " + pl.onRequest((OfflinePlayer)ProfileViewerGUI.this.p, "defense")), Sputnik.trans("  &c\u2741 Strength&f " + pl.onRequest((OfflinePlayer)ProfileViewerGUI.this.p, "strength")), Sputnik.trans("  &f\u2726 Speed " + pl.onRequest((OfflinePlayer)ProfileViewerGUI.this.p, "speed")), Sputnik.trans("  &9\u2623 Crit Chance&f " + pl.onRequest((OfflinePlayer)ProfileViewerGUI.this.p, "critchance") + "%"), Sputnik.trans("  &9\u2620 Crit Damage&f " + pl.onRequest((OfflinePlayer)ProfileViewerGUI.this.p, "critdamage") + "%"), Sputnik.trans("  &b\u270e Intelligence&f " + pl.onRequest((OfflinePlayer)ProfileViewerGUI.this.p, "int")), Sputnik.trans("  &e\u2694 Bonus Attack Speed&f " + pl.onRequest((OfflinePlayer)ProfileViewerGUI.this.p, "atkSpeed") + "%"), Sputnik.trans("  &c\u2afd Ferocity&f " + pl.onRequest((OfflinePlayer)ProfileViewerGUI.this.p, "ferocity")), Sputnik.trans("  &c\u0e51 Ability Damage&f " + pl.onRequest((OfflinePlayer)ProfileViewerGUI.this.p, "abilityDamage") + "%"), " ", Sputnik.trans("&8Skill Average: &6N/A &7(non-cosmetic)"), " ");
                return itemstack;
            }
        });
        this.set(new GUIItem(){

            @Override
            public int getSlot() {
                return 1;
            }

            @Override
            public ItemStack getItem() {
                if (ProfileViewerGUI.this.p.getItemInHand() != null && ProfileViewerGUI.this.p.getItemInHand().getType() != Material.AIR) {
                    return ProfileViewerGUI.this.p.getItemInHand();
                }
                ArrayList<String> lore = new ArrayList<String>();
                ItemStack gst = SUtil.createColoredStainedGlassPane((short)0, Sputnik.trans("&eHeld Item"));
                lore.add(ChatColor.RED + "Empty");
                ItemMeta met = gst.getItemMeta();
                met.setLore(lore);
                gst.setItemMeta(met);
                return gst;
            }
        });
        this.set(new GUIItem(){

            @Override
            public int getSlot() {
                return 10;
            }

            @Override
            public ItemStack getItem() {
                if (ProfileViewerGUI.this.p.getInventory().getHelmet() != null) {
                    return ProfileViewerGUI.this.p.getInventory().getHelmet();
                }
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.RED + "Empty");
                ItemStack gst = SUtil.createColoredStainedGlassPane((short)0, Sputnik.trans("&eHelmet"));
                ItemMeta met = gst.getItemMeta();
                met.setLore(lore);
                gst.setItemMeta(met);
                return gst;
            }
        });
        this.set(new GUIItem(){

            @Override
            public int getSlot() {
                return 19;
            }

            @Override
            public ItemStack getItem() {
                if (ProfileViewerGUI.this.p.getInventory().getChestplate() != null) {
                    return ProfileViewerGUI.this.p.getInventory().getChestplate();
                }
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.RED + "Empty");
                ItemStack gst = SUtil.createColoredStainedGlassPane((short)0, Sputnik.trans("&eChestplate"));
                ItemMeta met = gst.getItemMeta();
                met.setLore(lore);
                gst.setItemMeta(met);
                return gst;
            }
        });
        this.set(new GUIItem(){

            @Override
            public int getSlot() {
                return 28;
            }

            @Override
            public ItemStack getItem() {
                if (ProfileViewerGUI.this.p.getInventory().getLeggings() != null) {
                    return ProfileViewerGUI.this.p.getInventory().getLeggings();
                }
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.RED + "Empty");
                ItemStack gst = SUtil.createColoredStainedGlassPane((short)0, Sputnik.trans("&eLeggings"));
                ItemMeta met = gst.getItemMeta();
                met.setLore(lore);
                gst.setItemMeta(met);
                return gst;
            }
        });
        this.set(new GUIItem(){

            @Override
            public int getSlot() {
                return 37;
            }

            @Override
            public ItemStack getItem() {
                if (ProfileViewerGUI.this.p.getInventory().getBoots() != null) {
                    return ProfileViewerGUI.this.p.getInventory().getBoots();
                }
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.RED + "Empty");
                ItemStack gst = SUtil.createColoredStainedGlassPane((short)0, Sputnik.trans("&eBoots"));
                ItemMeta met = gst.getItemMeta();
                met.setLore(lore);
                gst.setItemMeta(met);
                return gst;
            }
        });
        this.set(new GUIItem(){

            @Override
            public int getSlot() {
                return 46;
            }

            @Override
            public ItemStack getItem() {
                if (user.getActivePet() != null) {
                    Pet.PetItem pet = user.getActivePet();
                    SItem item = SItem.of(pet.getType());
                    item.setRarity(pet.getRarity());
                    item.setDataDouble("xp", pet.getXp());
                    item.getData().setBoolean("equipped", true);
                    item.update();
                    ItemStack petstack = item.getStack();
                    ItemMeta meta = petstack.getItemMeta();
                    List newlore = item.getStack().getItemMeta().getLore();
                    newlore.add(" ");
                    newlore.add(item.getRarity().getBoldedColor() + item.getRarity().getDisplay());
                    meta.setLore(newlore);
                    petstack.setItemMeta(meta);
                    return petstack;
                }
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.RED + "Empty");
                ItemStack gst = SUtil.createColoredStainedGlassPane((short)0, Sputnik.trans("&ePets"));
                ItemMeta met = gst.getItemMeta();
                met.setLore(lore);
                gst.setItemMeta(met);
                return gst;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                ((Player)e.getWhoClicked()).closeInventory();
                ((Player)e.getWhoClicked()).chat("/trade " + ProfileViewerGUI.this.p.getName());
            }

            @Override
            public int getSlot() {
                return 16;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Trade Request", Material.EMERALD, (short)0, 1, ChatColor.YELLOW + "Send a trade request");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                ((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Not available!");
            }

            @Override
            public int getSlot() {
                return 15;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Visit Island", Material.FEATHER, (short)0, 1, ChatColor.RED + "Not available!");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                ((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Coming at a later date.");
            }

            @Override
            public int getSlot() {
                return 25;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.YELLOW + "Unfinished!", Material.DIAMOND, (short)0, 1, ChatColor.RED + "Not available!");
            }
        });
        if (player.hasPermission("system.viewinv")) {
            this.set(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    ((Player)e.getWhoClicked()).chat("/openinv " + ProfileViewerGUI.this.p.getName());
                }

                @Override
                public int getSlot() {
                    return 50;
                }

                @Override
                public ItemStack getItem() {
                    return SUtil.getStack(ChatColor.GREEN + "Open Player Inventory", Material.CHEST, (short)0, 1, ChatColor.YELLOW + "Click to view " + ProfileViewerGUI.this.p.getName() + "'s", ChatColor.YELLOW + "inventory.");
                }
            });
        }
        new BukkitRunnable(){

            public void run() {
                if (ProfileViewerGUI.this != GUI.GUI_MAP.get(player.getUniqueId())) {
                    return;
                }
                if (!ProfileViewerGUI.this.p.isOnline()) {
                    return;
                }
                new ProfileViewerGUI(ProfileViewerGUI.this.p).open(player);
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 40L);
    }
}

