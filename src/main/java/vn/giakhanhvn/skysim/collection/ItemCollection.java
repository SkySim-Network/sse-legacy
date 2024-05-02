/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.collection.ItemCollectionCategory;
import vn.giakhanhvn.skysim.collection.ItemCollectionReward;
import vn.giakhanhvn.skysim.collection.ItemCollectionRewards;
import vn.giakhanhvn.skysim.collection.ItemCollectionUpgradeReward;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

public class ItemCollection {
    private static final Map<String, ItemCollection> COLLECTION_MAP = new HashMap<String, ItemCollection>();
    public static final ItemCollection WHEAT = new ItemCollection("Wheat", ItemCollectionCategory.FARMING, SMaterial.WHEAT, (short) 0, new ItemCollectionRewards(50, new ItemCollectionReward[0]), new ItemCollectionRewards(100, new ItemCollectionReward[0]), new ItemCollectionRewards(250, new ItemCollectionReward[0]), new ItemCollectionRewards(500, new ItemCollectionReward[0]), new ItemCollectionRewards(1000, new ItemCollectionReward[0]), new ItemCollectionRewards(2500, new ItemCollectionReward[0]), new ItemCollectionRewards(10000, new ItemCollectionReward[0]), new ItemCollectionRewards(15000, new ItemCollectionReward[0]), new ItemCollectionRewards(25000, new ItemCollectionReward[0]), new ItemCollectionRewards(50000, new ItemCollectionReward[0]), new ItemCollectionRewards(100000, new ItemCollectionReward[0]));
    public static final ItemCollection OAK_WOOD = new ItemCollection("Oak Wood", ItemCollectionCategory.FORAGING, SMaterial.OAK_WOOD, (short) 0, 9);
    public static final ItemCollection STRING = new ItemCollection("String", ItemCollectionCategory.COMBAT, SMaterial.STRING, (short) 0, new ItemCollectionRewards(50, new ItemCollectionReward[0]), new ItemCollectionRewards(100, new ItemCollectionReward[0]), new ItemCollectionRewards(250, new ItemCollectionUpgradeReward("Quiver", ChatColor.GREEN)), new ItemCollectionRewards(1000, new ItemCollectionReward[0]), new ItemCollectionRewards(2500, new ItemCollectionReward[0]), new ItemCollectionRewards(5000, new ItemCollectionReward[0]), new ItemCollectionRewards(10000, new ItemCollectionReward[0]), new ItemCollectionRewards(25000, new ItemCollectionReward[0]), new ItemCollectionRewards(50000, new ItemCollectionReward[0]));
    private final String name;
    private final String identifier;
    private final ItemCollectionCategory category;
    private final SMaterial material;
    private final short data;
    private final List<ItemCollectionRewards> rewards;
    private final boolean temporary;

    private ItemCollection(String name, ItemCollectionCategory category, SMaterial material, short data, ItemCollectionRewards ... rewards) {
        this.name = name;
        this.identifier = name.toLowerCase().replaceAll(" ", "_");
        this.category = category;
        this.material = material;
        this.data = data;
        this.rewards = new ArrayList<ItemCollectionRewards>(Arrays.asList(rewards));
        this.temporary = false;
        COLLECTION_MAP.put(this.identifier, this);
    }

    private ItemCollection(String name, ItemCollectionCategory category, SMaterial material, short data, int size) {
        this.name = name;
        this.identifier = name.toLowerCase().replaceAll(" ", "_");
        this.category = category;
        this.material = material;
        this.data = data;
        this.rewards = new ArrayList<ItemCollectionRewards>();
        for (int i = 0; i < size; ++i) {
            this.rewards.add(null);
        }
        this.temporary = true;
        COLLECTION_MAP.put(this.identifier, this);
    }

    public static String[] getProgress(Player player, ItemCollectionCategory category) {
        String progress;
        String title;
        User user = User.getUser(player.getUniqueId());
        AtomicInteger found = new AtomicInteger();
        AtomicInteger completed = new AtomicInteger();
        Collection<ItemCollection> collections = category != null ? ItemCollection.getCategoryCollections(category) : ItemCollection.getCollections();
        for (ItemCollection collection : collections) {
            if (user.getCollection(collection) > 0) {
                found.incrementAndGet();
            }
            if (user.getCollection(collection) < collection.getMaxAmount()) continue;
            completed.incrementAndGet();
        }
        if (collections.size() == found.get()) {
            title = SUtil.createProgressText("Collection Maxed Out", completed.get(), collections.size());
            progress = SUtil.createLineProgressBar(20, ChatColor.DARK_GREEN, completed.get(), collections.size());
        } else {
            title = SUtil.createProgressText("Collection Unlocked", found.get(), collections.size());
            progress = SUtil.createLineProgressBar(20, ChatColor.DARK_GREEN, found.get(), collections.size());
        }
        return new String[]{title, progress};
    }

    public int getMaxAmount() {
        if (this.rewards.isEmpty() || this.rewards.get(this.rewards.size() - 1) == null) {
            return 0;
        }
        return this.rewards.get(this.rewards.size() - 1).getRequirement();
    }

    public int getTierAmount() {
        return this.rewards.size();
    }

    public int getTier(int amount) {
        int tier = 0;
        for (ItemCollectionRewards reward : this.rewards) {
            if (reward == null) continue;
            if (reward.getRequirement() > amount) break;
            ++tier;
        }
        return tier;
    }

    public int getRequirementForTier(int tier) {
        if (--tier < 0 || tier > this.rewards.size() - 1) {
            return -1;
        }
        ItemCollectionRewards reward = this.rewards.get(tier);
        if (reward == null) {
            return -1;
        }
        return reward.getRequirement();
    }

    public ItemCollectionRewards getRewardsFor(int tier) {
        if (--tier < 0 || tier > this.rewards.size()) {
            return null;
        }
        return this.rewards.get(tier);
    }

    public static ItemCollection getByIdentifier(String identifier) {
        return COLLECTION_MAP.get(identifier.toLowerCase());
    }

    public static ItemCollection getByMaterial(SMaterial material, short data) {
        for (ItemCollection collection : COLLECTION_MAP.values()) {
            if (collection.material != material || collection.data != data) continue;
            return collection;
        }
        return null;
    }

    public static Map<ItemCollection, Integer> getDefaultCollections() {
        HashMap<ItemCollection, Integer> collections = new HashMap<ItemCollection, Integer>();
        for (ItemCollection collection : COLLECTION_MAP.values()) {
            collections.put(collection, 0);
        }
        return collections;
    }

    public static Collection<ItemCollection> getCollections() {
        return COLLECTION_MAP.values();
    }

    public static List<ItemCollection> getCategoryCollections(ItemCollectionCategory category) {
        return ItemCollection.getCollections().stream().filter(collection -> collection.category == category).collect(Collectors.toList());
    }

    public String getName() {
        return this.name;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public ItemCollectionCategory getCategory() {
        return this.category;
    }

    public SMaterial getMaterial() {
        return this.material;
    }

    public short getData() {
        return this.data;
    }

    public List<ItemCollectionRewards> getRewards() {
        return this.rewards;
    }

    public boolean isTemporary() {
        return this.temporary;
    }
}

