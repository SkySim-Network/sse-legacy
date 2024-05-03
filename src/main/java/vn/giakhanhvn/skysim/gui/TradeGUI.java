/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.milkbowl.vault.economy.Economy
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.NBTBase
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  net.minecraft.server.v1_8_R3.NBTTagLong
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.gui;

import java.util.*;

import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagLong;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.Untradeable;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class TradeGUI
extends GUI {
    private final UUID tradeUUID;
    public static final Map<UUID, List<ItemStack>> itemOfferP1 = new HashMap<UUID, List<ItemStack>>();
    public static final Map<UUID, List<ItemStack>> itemOfferP2 = new HashMap<UUID, List<ItemStack>>();
    public static final Map<UUID, Player> player1 = new HashMap<UUID, Player>();
    public static final Map<UUID, Player> player2 = new HashMap<UUID, Player>();
    public static final Map<UUID, Integer> tradeCountdown = new HashMap<UUID, Integer>();
    private final int[] ls = new int[]{0, 1, 2, 3, 9, 10, 11, 12, 18, 19, 20, 21, 27, 28, 29, 30};
    private final int[] rs = new int[]{5, 6, 7, 8, 14, 15, 16, 17, 23, 24, 25, 26, 32, 33, 34, 35};

    public void fillFrom(Inventory i, int startFromSlot, int height, ItemStack stacc) {
        i.setItem(startFromSlot, stacc);
        i.setItem(startFromSlot + 9, stacc);
        i.setItem(startFromSlot + 9 + 9, stacc);
        i.setItem(startFromSlot + 9 + 9 + 9, stacc);
        i.setItem(startFromSlot + 9 + 9 + 9 + 9, stacc);
    }

    public TradeGUI() {
        this(UUID.randomUUID());
    }

    public TradeGUI(UUID uuid) {
        super("You                  " + player2.get(uuid).getName(), 45);
        this.tradeUUID = uuid;
        if (!itemOfferP1.containsKey(uuid) && itemOfferP1.get(uuid) == null) {
            itemOfferP1.put(uuid, new ArrayList());
        }
        if (!itemOfferP2.containsKey(uuid) && itemOfferP2.get(uuid) == null) {
            itemOfferP2.put(uuid, new ArrayList());
        }
    }

    @Override
    public void onOpen(final GUIOpenEvent e) {
        final Player player = e.getPlayer();
        final Inventory i = e.getInventory();
        ItemStack stk = SUtil.getSingleLoreStack(ChatColor.GRAY + "\u21e6 Your stuff", Material.STAINED_GLASS_PANE, (short)0, 1, ChatColor.GRAY + "Their stuff \u21e8");
        stk.setDurability((short)7);
        this.fillFrom(i, 4, 5, stk);
        TradeMenu.tradeP1Ready.put(this.tradeUUID, false);
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                if (TradeMenu.tradeP1Countdown.containsKey(TradeGUI.this.tradeUUID) && TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) <= 0 && (itemOfferP1.get(TradeGUI.this.tradeUUID).size() > 0 || itemOfferP2.get(TradeGUI.this.tradeUUID).size() > 0) && !TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID).booleanValue()) {
                    TradeMenu.tradeP1Ready.put(TradeGUI.this.tradeUUID, true);
                    player2.get(TradeGUI.this.tradeUUID).playSound(player2.get(TradeGUI.this.tradeUUID).getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                    player1.get(TradeGUI.this.tradeUUID).playSound(player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                }
            }

            @Override
            public int getSlot() {
                return 39;
            }

            @Override
            public ItemStack getItem() {
                ItemStack stack = SUtil.getStack(Sputnik.trans("&aTrading!"), Material.STAINED_CLAY, (short)13, 1, ChatColor.GRAY + "Click an item in your", ChatColor.GRAY + "inventory to offer it for", ChatColor.GRAY + "trade.");
                return stack;
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
            }

            @Override
            public int getSlot() {
                return 41;
            }

            @Override
            public ItemStack getItem() {
                ItemStack stack = SUtil.getStack(Sputnik.trans("&eNew deal"), Material.INK_SACK, (short)8, 1, ChatColor.GRAY + "Trading with " + player2.get(TradeGUI.this.tradeUUID).getName() + ".");
                return stack;
            }
        });
        new BukkitRunnable(){

            public void run() {
                if (TradeGUI.this != GUI.GUI_MAP.get(player.getUniqueId())) {
                    this.cancel();
                    return;
                }
                if (TradeMenu.tradeP1Countdown.containsKey(TradeGUI.this.tradeUUID) && TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) > 0) {
                    TradeMenu.tradeP1Countdown.put(TradeGUI.this.tradeUUID, TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) - 1);
                }
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 20L);
        new BukkitRunnable(){

            public void run() {
                if (TradeGUI.this != GUI.GUI_MAP.get(player.getUniqueId())) {
                    this.cancel();
                    return;
                }
                if (TradeMenu.tradeP2Ready.containsKey(TradeGUI.this.tradeUUID) && TradeMenu.tradeP1Countdown.containsKey(TradeGUI.this.tradeUUID)) {
                    if (TradeMenu.tradeP2Ready.get(TradeGUI.this.tradeUUID).booleanValue()) {
                        i.setItem(41, SUtil.getStack(Sputnik.trans("&aOther player confirmed!"), Material.INK_SACK, (short)10, 1, ChatColor.GRAY + "Trading with " + player2.get(TradeGUI.this.tradeUUID).getName() + ".", ChatColor.GRAY + "Waiting for you to confirm..."));
                    } else if (TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) > 0 && !TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID).booleanValue()) {
                        i.setItem(41, SUtil.getStack(Sputnik.trans("&eDeal timer..."), Material.INK_SACK, (short)8, 1, ChatColor.GRAY + "Trading with " + player2.get(TradeGUI.this.tradeUUID).getName() + ".", ChatColor.GRAY + "The trade changed recently."));
                    } else if (TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID).booleanValue()) {
                        i.setItem(41, SUtil.getStack(Sputnik.trans("&ePending their confirm."), Material.INK_SACK, (short)8, 1, ChatColor.GRAY + "Trading with " + player2.get(TradeGUI.this.tradeUUID).getName() + ".", ChatColor.GRAY + "Waiting for them to confirm."));
                    } else {
                        i.setItem(41, SUtil.getStack(Sputnik.trans("&eNew deal"), Material.INK_SACK, (short)8, 1, ChatColor.GRAY + "Trading with " + player2.get(TradeGUI.this.tradeUUID).getName() + "."));
                    }
                }
                if (TradeMenu.tradeP1Countdown.containsKey(TradeGUI.this.tradeUUID)) {
                    if (TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) > 0) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&eDeal timer! &7(&e" + TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID) + "&7)"), Material.STAINED_CLAY, (short)4, TradeMenu.tradeP1Countdown.get(TradeGUI.this.tradeUUID), ChatColor.GRAY + "The trade recently changed.", ChatColor.GRAY + "Please review it before", ChatColor.GRAY + "accepting."));
                        TradeMenu.tradeP1Ready.put(TradeGUI.this.tradeUUID, false);
                    } else if (TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID).booleanValue()) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&aDeal accepted!"), Material.STAINED_CLAY, (short)13, 1, ChatColor.GRAY + "You accepted the trade.", ChatColor.GRAY + "wait for the other party to", ChatColor.GRAY + "accept."));
                    } else if (itemOfferP1.get(TradeGUI.this.tradeUUID).size() <= 0 && itemOfferP2.get(TradeGUI.this.tradeUUID).size() <= 0) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&aTrading!"), Material.STAINED_CLAY, (short)13, 1, ChatColor.GRAY + "Click an item in your", ChatColor.GRAY + "inventory to offer it for", ChatColor.GRAY + "trade."));
                    } else if (itemOfferP2.get(TradeGUI.this.tradeUUID).size() <= 0 && !TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID).booleanValue()) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&eWarning!"), Material.STAINED_CLAY, (short)1, 1, ChatColor.GRAY + "You are offering items", ChatColor.GRAY + "without getting anything in", ChatColor.GRAY + "return.", " ", ChatColor.YELLOW + "Click to accept anyway!"));
                    } else if (itemOfferP1.get(TradeGUI.this.tradeUUID).size() <= 0) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&bGift!"), Material.STAINED_CLAY, (short)11, 1, ChatColor.GRAY + "You are receiving items", ChatColor.GRAY + "without offering anything in", ChatColor.GRAY + "return.", " ", ChatColor.YELLOW + "Click to accept!"));
                    } else if (itemOfferP1.get(TradeGUI.this.tradeUUID).size() > 0 && itemOfferP2.get(TradeGUI.this.tradeUUID).size() > 0) {
                        i.setItem(39, SUtil.getStack(Sputnik.trans("&eDeal!"), Material.STAINED_CLAY, (short)5, 1, ChatColor.GRAY + "All trades are final and", ChatColor.GRAY + "cannot be reverted.", " ", ChatColor.GREEN + "Make sure to review the", ChatColor.GREEN + "trade before accepting", " ", ChatColor.YELLOW + "Click to accept the trade!"));
                    }
                }
                if (TradeMenu.tradeP1Ready.get(TradeGUI.this.tradeUUID).booleanValue() && TradeMenu.tradeP2Ready.get(TradeGUI.this.tradeUUID).booleanValue()) {
                    this.cancel();
                    TradeMenu.successTrade.put(TradeGUI.this.tradeUUID, true);
                    TradeMenu.triggerCloseEvent(TradeGUI.this.tradeUUID, true, e.getPlayer());
                }
                List<ItemStack> stl1 = itemOfferP1.get(TradeGUI.this.tradeUUID);
                List<ItemStack> stl2 = itemOfferP2.get(TradeGUI.this.tradeUUID);
                ItemStack stk = SUtil.getSingleLoreStack(ChatColor.GRAY + "\u21e6 Your stuff", Material.STAINED_GLASS_PANE, (short)0, 1, ChatColor.GRAY + "Their stuff \u21e8");
                stk.setDurability((short)7);
                TradeGUI.this.fillFrom(i, 4, 5, stk);
                int a = -1;
                for (int slot : TradeGUI.this.ls) {
                    if (a < stl1.size() - 1) {
                        if (SItem.find(stl1.get(++a)) != null) {
                            i.setItem(slot, User.getUser(player1.get(TradeGUI.this.tradeUUID).getUniqueId()).updateItemBoost(SItem.find(stl1.get(a))));
                            continue;
                        }
                        i.setItem(slot, stl1.get(a));
                        continue;
                    }
                    i.setItem(slot, null);
                }
                int b = -1;
                for (int slot : TradeGUI.this.rs) {
                    if (b < stl2.size() - 1) {
                        if (SItem.find(stl2.get(++b)) != null) {
                            i.setItem(slot, User.getUser(player1.get(TradeGUI.this.tradeUUID).getUniqueId()).updateItemBoost(SItem.find(stl2.get(b))));
                            continue;
                        }
                        i.setItem(slot, stl2.get(b));
                        continue;
                    }
                    i.setItem(slot, null);
                }
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
        new BukkitRunnable(){

            public void run() {
                if (!player1.get(TradeGUI.this.tradeUUID).isOnline() || !player1.get(TradeGUI.this.tradeUUID).getWorld().equals(player2.get(TradeGUI.this.tradeUUID).getWorld())) {
                    this.cancel();
                    TradeMenu.triggerCloseEvent(TradeGUI.this.tradeUUID, false, player1.get(TradeGUI.this.tradeUUID));
                } else if (!player2.get(TradeGUI.this.tradeUUID).isOnline() || !player2.get(TradeGUI.this.tradeUUID).getWorld().equals(player1.get(TradeGUI.this.tradeUUID).getWorld())) {
                    this.cancel();
                    TradeMenu.triggerCloseEvent(TradeGUI.this.tradeUUID, false, player2.get(TradeGUI.this.tradeUUID));
                }
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
        this.set(new GUISignItem(){

            @Override
            public GUI onSignClose(String query, Player target) {
                if (target != player1.get(TradeGUI.this.tradeUUID)) {
                    return null;
                }
                if (Objects.equals(query, "$canc")) {
                    return new TradeGUI(TradeGUI.this.tradeUUID);
                }
                try {
                    long add = Long.parseLong(query);
                    double cur = User.getUser(player1.get(tradeUUID).getUniqueId()).getBits();
                    if (add <= 0L) {
                        player1.get(TradeGUI.this.tradeUUID).playSound(player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                        player.sendMessage(ChatColor.RED + "Couldn't validate this Bits amount!");
                        return new TradeGUI(TradeGUI.this.tradeUUID);
                    }
                    if ((double)add > cur) {
                        player1.get(TradeGUI.this.tradeUUID).playSound(player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                        player.sendMessage(ChatColor.RED + "You don't have that much Bits for this.");
                        return new TradeGUI(TradeGUI.this.tradeUUID);
                    }
                    if (add > 640000L) {
                        player1.get(TradeGUI.this.tradeUUID).playSound(player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                        player.sendMessage(ChatColor.RED + "You cannot transfer more than 640,000 Bits at once!");
                        return new TradeGUI(TradeGUI.this.tradeUUID);
                    }
                    if (itemOfferP1.get(TradeGUI.this.tradeUUID).size() < 16) {
                        if (User.getUser(player1.get(tradeUUID).getUniqueId()).subBits(add)){
                            player1.get(TradeGUI.this.tradeUUID).playSound(player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
                            player2.get(TradeGUI.this.tradeUUID).playSound(player2.get(TradeGUI.this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
                            long stackamount = Math.min(64L, Math.max(10000L, add) / 10000L);
                            ItemStack coinsStack = SUtil.getSkullURLStack(ChatColor.AQUA + Sputnik.formatFull(add) + " Bits", "7b951fed6a7b2cbc2036916dec7a46c4a56481564d14f945b6ebc03382766d3b", (int)stackamount, ChatColor.GRAY + "Lump-sum amount");
                            net.minecraft.server.v1_8_R3.ItemStack tagStack = CraftItemStack.asNMSCopy(coinsStack);
                            NBTTagCompound tagCompound = tagStack.hasTag() ? tagStack.getTag() : new NBTTagCompound();
                            tagCompound.set("data_bits", new NBTTagLong(add));
                            tagStack.setTag(tagCompound);
                            coinsStack = CraftItemStack.asBukkitCopy(tagStack);
                            itemOfferP1.get(TradeGUI.this.tradeUUID).add(coinsStack);
                            TradeMenu.tradeP1Countdown.put(TradeGUI.this.tradeUUID, 3);
                            TradeMenu.tradeP2Countdown.put(TradeGUI.this.tradeUUID, 3);
                        } else {
                            player1.get(TradeGUI.this.tradeUUID).playSound(player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                            player.sendMessage(ChatColor.RED + "An unexpected error occured while attempting to access the economy!");
                        }
                    }
                    return new TradeGUI(TradeGUI.this.tradeUUID);
                }
                catch (NumberFormatException ex) {
                    player.sendMessage(ChatColor.RED + "Couldn't parse this Bits amount!");
                    player1.get(TradeGUI.this.tradeUUID).playSound(player1.get(TradeGUI.this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                    return new TradeGUI(TradeGUI.this.tradeUUID);
                }
            }

            @Override
            public void run(InventoryClickEvent e) {
                player.playSound(player.getLocation(), Sound.CLICK, 1.0f, 1.0f);
            }

            @Override
            public int getSlot() {
                return 36;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getSkullURLStack(ChatColor.AQUA + "Bits transaction", "7b951fed6a7b2cbc2036916dec7a46c4a56481564d14f945b6ebc03382766d3b", 1, ChatColor.GRAY + " ", ChatColor.YELLOW + "Click to add bits!");
            }

            @Override
            public UUID inti() {
                return TradeGUI.this.tradeUUID;
            }
        });
    }

    @Override
    public void onBottomClick(InventoryClickEvent e) {
        if (e.getSlot() < 0) {
            e.setCancelled(true);
            return;
        }
        ItemStack cs = null;
        if (player1.get(this.tradeUUID).getInventory().getItem(e.getSlot()) != null) {
            cs = player1.get(this.tradeUUID).getInventory().getItem(e.getSlot());
        }
        if (cs != null) {
            SItem sItem = SItem.find(cs);
            if (sItem != null && SItem.isSpecItem(cs)) {
                if (!(sItem.getType().getGenericInstance() instanceof Untradeable)) {
                    if (itemOfferP1.get(this.tradeUUID).size() < 16) {
                        itemOfferP1.get(this.tradeUUID).add(cs);
                        player1.get(this.tradeUUID).playSound(player1.get(this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
                        player2.get(this.tradeUUID).playSound(player2.get(this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
                        player1.get(this.tradeUUID).getInventory().setItem(e.getSlot(), null);
                        TradeMenu.tradeP1Countdown.put(this.tradeUUID, 3);
                        TradeMenu.tradeP2Countdown.put(this.tradeUUID, 3);
                    } else {
                        player1.get(this.tradeUUID).playSound(player1.get(this.tradeUUID).getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        player1.get(this.tradeUUID).sendMessage(Sputnik.trans("&c&lIT'S FULL! &7Your trade window is full!"));
                    }
                } else {
                    player1.get(this.tradeUUID).sendMessage(Sputnik.trans("&cYou cannot trade this item!"));
                    player1.get(this.tradeUUID).playSound(player1.get(this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                }
            } else {
                player1.get(this.tradeUUID).sendMessage(Sputnik.trans("&cYou cannot trade this item!"));
                player1.get(this.tradeUUID).playSound(player1.get(this.tradeUUID).getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
            }
        }
    }

    @Override
    public void onTopClick(InventoryClickEvent e) {
        if (itemOfferP1.get(this.tradeUUID).contains(e.getInventory().getItem(e.getSlot())) && TradeGUI.isContain(this.ls, e.getSlot())) {
            itemOfferP1.get(this.tradeUUID).remove(e.getInventory().getItem(e.getSlot()));
            ItemStack stack = e.getInventory().getItem(e.getSlot());
            player1.get(this.tradeUUID).playSound(player1.get(this.tradeUUID).getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
            net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
            if (!nmsStack.getTag().hasKey("data_bits")) {
                Sputnik.smartGiveItem(stack, player1.get(this.tradeUUID));
            } else {
                User.getUser(player1.get(tradeUUID).getUniqueId()).addBits(nmsStack.getTag().getLong("data_bits"));
            }
            TradeMenu.tradeP1Countdown.put(this.tradeUUID, 3);
            TradeMenu.tradeP2Countdown.put(this.tradeUUID, 3);
        }
    }

    @Override
    public void onClose(InventoryCloseEvent e) {
        TradeMenu.triggerCloseEvent(this.tradeUUID, false, (Player)e.getPlayer());
        ((Player)e.getPlayer()).playSound(e.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        GUIListener.QUERY_MAP.remove(e.getPlayer().getUniqueId());
        GUIListener.QUERY_MAPPING.remove(e.getPlayer().getUniqueId());
    }


    public static boolean isContain(int[] array, int key) {
        for (int i : array) {
            if (i != key) continue;
            return true;
        }
        return false;
    }
}

