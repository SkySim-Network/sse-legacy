/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package vn.giakhanhvn.skysim.auction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.auction.AuctionBid;
import vn.giakhanhvn.skysim.config.Config;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.user.AuctionSettings;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class AuctionItem {
    private static final Map<UUID, AuctionItem> AUCTION_ITEM_CACHE = new HashMap<UUID, AuctionItem>();
    private static final SkySimEngine plugin = SkySimEngine.getPlugin();
    private static final File AUCTION_ITEM_FOLDER = new File(plugin.getDataFolder(), "./auctions");
    private Config config;
    private UUID uuid;
    private SItem item;
    private long starter;
    private List<AuctionBid> bids;
    private long end;
    private UUID owner;
    private List<UUID> participants;
    private boolean bin;

    private AuctionItem(UUID uuid, SItem item, long starter, long end, UUID owner, List<UUID> participants, boolean bin) {
        this.uuid = uuid;
        this.item = item;
        this.starter = starter;
        this.bids = new ArrayList<AuctionBid>();
        this.end = end;
        this.owner = owner;
        this.participants = participants;
        this.bin = bin;
        if (!AUCTION_ITEM_FOLDER.exists()) {
            AUCTION_ITEM_FOLDER.mkdirs();
        }
        String path = uuid.toString() + ".yml";
        File configFile = new File(AUCTION_ITEM_FOLDER, path);
        boolean save = false;
        try {
            if (!configFile.exists()) {
                save = true;
                configFile.createNewFile();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        this.config = new Config(AUCTION_ITEM_FOLDER, path);
        AUCTION_ITEM_CACHE.put(uuid, this);
        if (save) {
            this.save();
        }
        this.load();
    }

    public void load() {
        this.uuid = UUID.fromString(this.config.getString("uuid"));
        this.item = (SItem)this.config.get("item");
        this.starter = this.config.getLong("starter");
        this.bids = (List)this.config.get("bids");
        this.owner = UUID.fromString(this.config.getString("owner"));
        List<String> strings = this.config.getStringList("participants");
        for (String string : strings) {
            this.participants.add(UUID.fromString(string));
        }
        this.end = this.config.getLong("end");
        this.bin = this.config.getBoolean("bin");
    }

    public void save() {
        this.config.set("uuid", this.uuid.toString());
        this.config.set("item", this.item);
        this.config.set("starter", this.starter);
        this.config.set("bids", this.bids);
        this.config.set("owner", this.owner.toString());
        ArrayList<String> strings = new ArrayList<String>();
        for (UUID uuid : this.participants) {
            strings.add(uuid.toString());
        }
        this.config.set("participants", strings);
        this.config.set("end", this.end);
        this.config.set("bin", this.bin);
        this.config.save();
    }

    public boolean delete() {
        this.config = null;
        AUCTION_ITEM_CACHE.remove(this.uuid);
        return new File(AUCTION_ITEM_FOLDER, this.uuid.toString() + ".yml").delete();
    }

    public User getOwner() {
        return User.getUser(this.owner);
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= this.end;
    }

    public User getTopBidder() {
        UUID uuid = null;
        long b = 0L;
        for (AuctionBid bid : this.bids) {
            if (bid.getAmount() <= b) continue;
            uuid = bid.getBidder();
            b = bid.getAmount();
        }
        return User.getUser(uuid);
    }

    public AuctionBid getTopBid() {
        AuctionBid b = null;
        long l = 0L;
        for (AuctionBid bid : this.bids) {
            if (bid.getAmount() <= l) continue;
            l = bid.getAmount();
            b = bid;
        }
        return b;
    }

    public long getTopBidAmount() {
        long b = 0L;
        for (AuctionBid bid : this.bids) {
            if (bid.getAmount() <= b) continue;
            b = bid.getAmount();
        }
        return b;
    }

    public AuctionBid getRecentBid() {
        return this.bids.size() == 0 ? null : this.bids.get(this.bids.size() - 1);
    }

    public long nextBid() {
        long top = this.getTopBidAmount();
        if (top == 0L) {
            return this.starter;
        }
        return Math.round((double)top * 1.15);
    }

    public AuctionBid getBid(UUID uuid) {
        for (int i = this.bids.size() - 1; i >= 0; --i) {
            AuctionBid bid = this.bids.get(i);
            if (!bid.getBidder().equals(uuid)) continue;
            return bid;
        }
        return null;
    }

    public AuctionBid getBid(User user) {
        return this.getBid(user.getUuid());
    }

    public void claim(Player player) {
        if (!this.participants.contains(player.getUniqueId())) {
            return;
        }
        User user = User.getUser(player.getUniqueId());
        if (player.getUniqueId().equals(this.owner)) {
            if (this.bids.size() == 0) {
                player.getInventory().addItem(new ItemStack[]{this.item.getStack()});
            } else {
                user.addCoins(this.getTopBidAmount());
            }
            this.removeParticipant(user.getUuid());
            return;
        }
        AuctionBid top = this.getTopBid();
        if (top == null) {
            return;
        }
        AuctionBid bid = this.getBid(user);
        if (bid == null) {
            return;
        }
        if (top.getBidder().equals(player.getUniqueId())) {
            player.getInventory().addItem(new ItemStack[]{this.item.getStack()});
        } else {
            user.addCoins(bid.getAmount());
        }
        this.removeParticipant(player.getUniqueId());
    }

    public void removeParticipant(UUID uuid) {
        this.participants.removeIf(uuid::equals);
        if (this.participants.size() == 0) {
            this.delete();
        }
    }

    public void bid(User user, long amount) {
        AuctionBid prev;
        if (!this.participants.contains(user.getUuid())) {
            this.participants.add(user.getUuid());
        }
        user.subCoins(amount - ((prev = this.getBid(user)) != null ? prev.getAmount() : 0L));
        this.bids.add(new AuctionBid(user.getUuid(), amount, System.currentTimeMillis()));
        String bidder = Bukkit.getOfflinePlayer((UUID)user.getUuid()).getName();
        if (this.bin) {
            this.messageOwner(ChatColor.GOLD + "[Auction] " + ChatColor.GREEN + bidder + ChatColor.YELLOW + " bought " + this.item.getFullName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + SUtil.commaify(this.starter) + " coin" + (this.starter != 1L ? "s" : ""));
            this.end();
            Player player = Bukkit.getPlayer((UUID)user.getUuid());
            if (player != null) {
                this.claim(player);
            }
            return;
        }
        this.messageOwner(ChatColor.GOLD + "[Auction] " + ChatColor.GREEN + bidder + ChatColor.YELLOW + " bid " + ChatColor.GOLD + SUtil.commaify(this.starter) + " coin" + (this.starter != 1L ? "s" : "") + ChatColor.YELLOW + " on " + this.item.getFullName());
        for (UUID participant : this.participants) {
            AuctionBid bid;
            Player player;
            if (participant.equals(user.getUuid()) || (player = Bukkit.getPlayer((UUID)participant)) == null || (bid = this.getBid(participant)) == null) continue;
            long diff = amount - bid.getAmount();
            player.sendMessage(ChatColor.GOLD + "[Auction] " + ChatColor.GREEN + bidder + ChatColor.YELLOW + " outbid you by " + ChatColor.GOLD + diff + " coin" + (diff != 1L ? "s" : "") + ChatColor.YELLOW + " for " + this.item.getFullName());
        }
    }

    public void messageOwner(String message) {
        User ou = this.getOwner();
        Player owner = Bukkit.getPlayer((UUID)ou.getUuid());
        if (owner != null) {
            owner.sendMessage(message);
        }
    }

    public void end() {
        this.end = System.currentTimeMillis() - 1L;
    }

    public long getRemaining() {
        return Math.max(0L, this.end - System.currentTimeMillis());
    }

    public ItemStack getDisplayItem(boolean inspect, boolean yourAuction) {
        ItemStack stack = this.item.getStack().clone();
        ItemMeta meta = stack.getItemMeta();
        List lore = meta.getLore();
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------");
        lore.add(ChatColor.GRAY + "Seller: " + ChatColor.GREEN + Bukkit.getOfflinePlayer((UUID)this.owner).getName());
        User top = this.getTopBidder();
        if (this.isBin()) {
            lore.add(ChatColor.GRAY + "Buy it now: " + ChatColor.GOLD + SUtil.commaify(this.starter) + " coins");
        } else if (top == null) {
            lore.add(ChatColor.GRAY + "Starting bid: " + ChatColor.GOLD + SUtil.commaify(this.starter) + " coins");
        } else {
            lore.add(ChatColor.GRAY + "Bids: " + ChatColor.GREEN + this.bids.size() + " bids");
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Top bid: " + ChatColor.GOLD + SUtil.commaify(this.getTopBidAmount()) + " coins");
            lore.add(ChatColor.GRAY + "Bidder: " + ChatColor.GREEN + Bukkit.getOfflinePlayer((UUID)top.getUuid()).getName());
        }
        if (yourAuction) {
            lore.add(" ");
            lore.add(ChatColor.GREEN + "This is your own auction!");
        }
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Ends in: " + ChatColor.YELLOW + SUtil.getAuctionFormattedTime(this.getRemaining()));
        if (inspect) {
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "Click to inspect!");
        }
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    public String toString() {
        return "AuctionItem{uuid=" + this.uuid.toString() + ", item=" + this.item.toString() + ", bids=" + this.bids.toString() + ", end=" + this.end + "}";
    }

    public static AuctionItem createAuction(SItem item, long starter, long end, UUID owner, boolean bin) {
        return new AuctionItem(UUID.randomUUID(), item, starter, end, owner, new ArrayList<UUID>(Collections.singletonList(owner)), bin);
    }

    public static AuctionItem getAuction(UUID uuid) {
        if (AUCTION_ITEM_CACHE.containsKey(uuid)) {
            return AUCTION_ITEM_CACHE.get(uuid);
        }
        if (!new File(AUCTION_ITEM_FOLDER, uuid.toString() + ".yml").exists()) {
            return null;
        }
        return new AuctionItem(uuid, null, 0L, 0L, null, new ArrayList<UUID>(), false);
    }

    public static Collection<AuctionItem> getAuctions() {
        if (AUCTION_ITEM_FOLDER == null || !AUCTION_ITEM_FOLDER.exists()) {
            return new ArrayList<AuctionItem>();
        }
        return AUCTION_ITEM_CACHE.values();
    }

    public static List<AuctionItem> getOwnedAuctions(String name) {
        OfflinePlayer player = Bukkit.getOfflinePlayer((String)name);
        if (player == null) {
            return null;
        }
        User user = User.getUser(player.getUniqueId());
        if (user == null) {
            return null;
        }
        return user.getAuctions();
    }

    public static CompletableFuture<List<AuctionItem>> search(AuctionSettings settings) {
        return CompletableFuture.supplyAsync(() -> {
            Stream<AuctionItem> items = AuctionItem.getAuctions().stream();
            items = items.filter(item -> !item.isExpired());
            items = items.filter(item -> item.getItem().getType().getStatistics().getCategory() == settings.getCategory());
            if (settings.getQuery() != null) {
                items = items.filter(item -> {
                    String query = settings.getQuery().toLowerCase();
                    String name = item.getItem().getType().getDisplayName(item.getItem().getType().getData()).toLowerCase();
                    String lore = item.getItem().getLore().asBukkitLore().toString().toLowerCase();
                    return query.contains(name) || query.contains(lore) || query.contains(item.getItem().getType().name().toLowerCase());
                });
            }
            items = items.sorted((i1, i2) -> {
                switch (settings.getSort()) {
                    case HIGHEST_BID: {
                        return Long.compare(i1.getTopBidAmount(), i2.getTopBidAmount());
                    }
                    case LOWEST_BID: {
                        if (i1.getTopBidAmount() < i2.getTopBidAmount()) {
                            return 1;
                        }
                        if (i2.getTopBidAmount() > i1.getTopBidAmount()) {
                            return -1;
                        }
                        return 0;
                    }
                    case MOST_BIDS: {
                        return Long.compare(i1.getBids().size(), i2.getBids().size());
                    }
                    case ENDING_SOON: {
                        return Long.compare(i2.end - System.currentTimeMillis(), i2.end - System.currentTimeMillis());
                    }
                }
                return 0;
            });
            if (settings.getTier() != null) {
                items = items.filter(item -> item.getItem().getRarity() == settings.getTier());
            }
            switch (settings.getType()) {
                case AUCTIONS_ONLY: {
                    items = items.filter(item -> !item.isBin());
                    break;
                }
                case BIN_ONLY: {
                    items = items.filter(AuctionItem::isBin);
                }
            }
            return items.collect(Collectors.toList());
        });
    }

    public static void loadAuctionsFromDisk() {
        if (!AUCTION_ITEM_FOLDER.exists()) {
            return;
        }
        for (File f : Objects.requireNonNull(AUCTION_ITEM_FOLDER.listFiles())) {
            UUID uuid;
            String name = f.getName();
            if (!name.endsWith(".yml")) continue;
            name = name.substring(0, name.length() - 4);
            try {
                uuid = UUID.fromString(name);
            }
            catch (IllegalArgumentException ex) {
                continue;
            }
            AuctionItem.getAuction(uuid);
        }
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public SItem getItem() {
        return this.item;
    }

    public long getStarter() {
        return this.starter;
    }

    public List<AuctionBid> getBids() {
        return this.bids;
    }

    public long getEnd() {
        return this.end;
    }

    public List<UUID> getParticipants() {
        return this.participants;
    }

    public boolean isBin() {
        return this.bin;
    }
}

