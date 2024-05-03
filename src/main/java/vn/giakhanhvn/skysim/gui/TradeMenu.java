/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.milkbowl.vault.economy.Economy
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.sequence.SoundSequenceType;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;
import vn.giakhanhvn.skysim.util.TradeUtil;

public class TradeMenu {
    public static final Map<UUID, Boolean> tradeClose = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Player> tradeClosePlayerName = new HashMap<UUID, Player>();
    public static final Map<UUID, Integer> tradeP1Countdown = new HashMap<UUID, Integer>();
    public static final Map<UUID, Integer> tradeP2Countdown = new HashMap<UUID, Integer>();
    public static final Map<UUID, Boolean> tradeP1Ready = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Boolean> tradeP2Ready = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Boolean> successTrade = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Boolean> player1TradeUUID = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Boolean> player2TradeUUID = new HashMap<UUID, Boolean>();
    @Getter
    private final Player p1;
    @Getter
    private final Player p2;
    private final UUID tradeUUID;

    public TradeMenu(Player player1, Player player2, UUID uuid) {
        this.p1 = player1;
        this.p2 = player2;
        this.tradeUUID = uuid;
    }

    public static void triggerCloseEvent(UUID tradeUUID, boolean isSuccess, Player player) {
        if (tradeClose.containsKey(tradeUUID)) {
            return;
        }
        if (!isSuccess) {
            tradeClose.put(tradeUUID, isSuccess);
            tradeClosePlayerName.put(tradeUUID, player);
        } else {
            tradeClose.put(tradeUUID, isSuccess);
            tradeClosePlayerName.put(tradeUUID, player);
        }
    }

    public void open() {
        if (this.p1.getUniqueId() == this.p2.getUniqueId()) {
            return;
        }
        TradeUtil.trading.put(this.p1.getUniqueId(), true);
        TradeUtil.trading.put(this.p2.getUniqueId(), true);
        TradeGUI.player1.put(this.tradeUUID, this.p1);
        TradeGUI.player2.put(this.tradeUUID, this.p2);
        new TradeGUI(this.tradeUUID).open(this.p1);
        new TradeGUIInvert(this.tradeUUID).open(this.p2);
        new BukkitRunnable(){

            public void run() {
                if (!TradeMenu.this.p1.isOnline() || !TradeMenu.this.p2.isOnline()) {
                    if (!TradeMenu.this.p1.isOnline()) {
                        TradeMenu.triggerCloseEvent(TradeMenu.this.tradeUUID, false, TradeMenu.this.p1);
                    } else if (!TradeMenu.this.p2.isOnline()) {
                        TradeMenu.triggerCloseEvent(TradeMenu.this.tradeUUID, false, TradeMenu.this.p2);
                    }
                }
                if (tradeClose.containsKey(TradeMenu.this.tradeUUID)) {
                    this.cancel();
                    User.getUser(TradeMenu.this.p1.getUniqueId()).updateInventory();
                    User.getUser(TradeMenu.this.p2.getUniqueId()).updateInventory();
                    if (!tradeClose.get(TradeMenu.this.tradeUUID).booleanValue()) {
                        if (tradeClosePlayerName.get(TradeMenu.this.tradeUUID) == TradeMenu.this.p1) {
                            TradeMenu.this.p1.sendMessage(Sputnik.trans("&cYou cancelled the trade!"));
                            TradeMenu.this.p2.sendMessage(Sputnik.trans("&b" + TradeMenu.this.p1.getName() + " &ccancelled the trade!"));
                            TradeMenu.this.p2.closeInventory();
                        } else {
                            TradeMenu.this.p2.sendMessage(Sputnik.trans("&cYou cancelled the trade!"));
                            TradeMenu.this.p1.sendMessage(Sputnik.trans("&b" + TradeMenu.this.p2.getName() + " &ccancelled the trade!"));
                            TradeMenu.this.p1.closeInventory();
                        }
                        TradeMenu.this.clean();
                    } else if (successTrade.containsKey(TradeMenu.this.tradeUUID)) {
                        List<ItemStack> itemlist2;
                        List<ItemStack> itemlist1;
                        if (successTrade.get(TradeMenu.this.tradeUUID).booleanValue()) {
                            itemlist1 = TradeGUI.itemOfferP1.get(TradeMenu.this.tradeUUID);
                            itemlist2 = TradeGUI.itemOfferP2.get(TradeMenu.this.tradeUUID);
                            TradeGUI.itemOfferP1.put(TradeMenu.this.tradeUUID, itemlist2);
                            TradeGUI.itemOfferP2.put(TradeMenu.this.tradeUUID, itemlist1);
                        }
                        itemlist1 = TradeGUI.itemOfferP1.get(TradeMenu.this.tradeUUID);
                        itemlist2 = TradeGUI.itemOfferP2.get(TradeMenu.this.tradeUUID);
                        StringBuilder sb1 = new StringBuilder();
                        sb1.append("&6Trade completed with &r").append(TradeMenu.this.p2.getDisplayName()).append("&6!");
                        for (ItemStack itemRece : itemlist1) {
                            if (!CraftItemStack.asNMSCopy(itemRece).getTag().hasKey("data_bits")) {
                                sb1.append("\n &a&l+ &8").append(itemRece.getAmount()).append("x &r").append(itemRece.getItemMeta().getDisplayName());
                                continue;
                            }
                            sb1.append("\n &a&l+ &8").append(itemRece.getItemMeta().getDisplayName());
                        }
                        for (ItemStack itemTaken : itemlist2) {
                            if (!CraftItemStack.asNMSCopy(itemTaken).getTag().hasKey("data_bits")) {
                                sb1.append("\n &c&l- &8").append(itemTaken.getAmount()).append("x &r").append(itemTaken.getItemMeta().getDisplayName());
                                continue;
                            }
                            sb1.append("\n &c&l- &8").append(itemTaken.getItemMeta().getDisplayName());
                        }
                        TradeMenu.this.p1.sendMessage(Sputnik.trans(sb1.toString()));
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("&6Trade completed with ").append(TradeMenu.this.p1.getDisplayName()).append("&6!");
                        for (ItemStack itemRece : itemlist2) {
                            if (!CraftItemStack.asNMSCopy(itemRece).getTag().hasKey("data_bits")) {
                                sb2.append("\n &a&l+ &8").append(itemRece.getAmount()).append("x &r").append(itemRece.getItemMeta().getDisplayName());
                                continue;
                            }
                            sb2.append("\n &a&l+ &8").append(itemRece.getItemMeta().getDisplayName());
                        }
                        for (ItemStack itemTaken : itemlist1) {
                            if (!CraftItemStack.asNMSCopy(itemTaken).getTag().hasKey("data_bits")) {
                                sb2.append("\n &c&l- &8").append(itemTaken.getAmount()).append("x &r").append(itemTaken.getItemMeta().getDisplayName());
                                continue;
                            }
                            sb2.append("\n &c&l- &8").append(itemTaken.getItemMeta().getDisplayName());
                        }
                        TradeMenu.this.p2.sendMessage(Sputnik.trans(sb2.toString()));
                        SoundSequenceType.TRADE_COMPLETE.play(TradeMenu.this.p1);
                        SoundSequenceType.TRADE_COMPLETE.play(TradeMenu.this.p2);
                        TradeMenu.this.p1.closeInventory();
                        TradeMenu.this.p2.closeInventory();
                        User.getUser(TradeMenu.this.p1.getUniqueId()).updateInventory();
                        User.getUser(TradeMenu.this.p2.getUniqueId()).updateInventory();
                        TradeMenu.this.clean();
                        TradeGUI.itemOfferP1.remove(TradeMenu.this.p1.getUniqueId());
                        TradeGUI.itemOfferP2.remove(TradeMenu.this.p2.getUniqueId());
                    }
                    TradeMenu.this.returnToAllPlayers(TradeMenu.this.p1, TradeMenu.this.p2);
                }
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void clean() {
        SUtil.delay(() -> {
            player1TradeUUID.remove(this.p1.getUniqueId());
            player2TradeUUID.remove(this.p2.getUniqueId());
            tradeClose.remove(this.tradeUUID);
            tradeClosePlayerName.remove(this.tradeUUID);
            tradeP1Countdown.remove(this.tradeUUID);
            tradeP2Countdown.remove(this.tradeUUID);
            tradeP1Ready.remove(this.tradeUUID);
            tradeP2Ready.remove(this.tradeUUID);
            TradeGUI.itemOfferP1.remove(this.p1.getUniqueId());
            TradeGUI.itemOfferP2.remove(this.p2.getUniqueId());
            TradeUtil.resetTrade(this.p1);
            TradeUtil.resetTrade(this.p2);
            TradeUtil.trading.put(this.p1.getUniqueId(), false);
            TradeUtil.trading.put(this.p2.getUniqueId(), false);
        }, 2L);
    }

    public void returnToAllPlayers(Player player1, Player player2) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack;
        for (ItemStack i : TradeGUI.itemOfferP1.get(this.tradeUUID)) {
            nmsStack = CraftItemStack.asNMSCopy(i);
            if (!nmsStack.getTag().hasKey("data_bits")) {
                Sputnik.smartGiveItem(i, player1);
                continue;
            }
            User.getUser(player1.getUniqueId()).addBits(nmsStack.getTag().getLong("data_bits"));
        }
        for (ItemStack i : TradeGUI.itemOfferP2.get(this.tradeUUID)) {
            nmsStack = CraftItemStack.asNMSCopy(i);
            if (!nmsStack.getTag().hasKey("data_bits")) {
                Sputnik.smartGiveItem(i, player2);
                continue;
            }
            User.getUser(player1.getUniqueId()).addBits(nmsStack.getTag().getLong("data_bits"));
        }
    }

}

