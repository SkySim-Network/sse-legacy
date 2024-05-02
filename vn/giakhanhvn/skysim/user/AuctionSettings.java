/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.serialization.ConfigurationSerializable
 */
package vn.giakhanhvn.skysim.user;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import vn.giakhanhvn.skysim.item.ItemCategory;
import vn.giakhanhvn.skysim.item.Rarity;

public class AuctionSettings
implements Cloneable,
ConfigurationSerializable {
    private ItemCategory category;
    private String query;
    private Sort sort;
    private Rarity tier;
    private Type type;

    public AuctionSettings(ItemCategory category, String query, Sort sort, Rarity tier, Type type) {
        this.category = category;
        this.query = query;
        this.sort = sort;
        this.tier = tier;
        this.type = type;
    }

    public AuctionSettings() {
        this(ItemCategory.WEAPONS, null, Sort.HIGHEST_BID, null, Type.SHOW_ALL);
    }

    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("category", this.category.name());
        map.put("query", this.query);
        map.put("sort", this.sort.name());
        if (this.tier != null) {
            map.put("tier", this.tier.name());
        }
        map.put("type", this.type.name());
        return map;
    }

    public String toString() {
        return "AuctionSettings{category=" + this.category.name() + ", query=" + this.query + ", sort=" + this.sort.name() + ", tier=" + this.tier.name() + ", type=" + this.type.name() + "}";
    }

    public boolean equals(Object o) {
        if (!(o instanceof AuctionSettings)) {
            return false;
        }
        AuctionSettings settings = (AuctionSettings)o;
        return this.category == settings.category && this.query.equals(settings.query) && this.sort == settings.sort && this.tier == settings.tier && this.type == settings.type;
    }

    public AuctionSettings clone() {
        return new AuctionSettings(this.category, this.query, this.sort, this.tier, this.type);
    }

    public ItemCategory getCategory() {
        return this.category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Sort getSort() {
        return this.sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Rarity getTier() {
        return this.tier;
    }

    public void setTier(Rarity tier) {
        this.tier = tier;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static AuctionSettings deserialize(Map<String, Object> map) {
        return new AuctionSettings(ItemCategory.valueOf((String)map.get("category")), map.containsKey("query") ? (String)map.get("query") : null, Sort.valueOf((String)map.get("sort")), map.containsKey("tier") ? Rarity.getRarity((String)map.get("tier")) : null, Type.valueOf((String)map.get("type")));
    }

    public static enum Sort {
        HIGHEST_BID("Highest Bid"),
        LOWEST_BID("Lowest Bid"),
        ENDING_SOON("Ending soon"),
        MOST_BIDS("Most Bids");

        private final String display;

        private Sort(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return this.display;
        }

        public Sort previous() {
            int prev = this.ordinal() - 1;
            if (prev < 0) {
                return Sort.values()[Sort.values().length - 1];
            }
            return Sort.values()[prev];
        }

        public Sort next() {
            int nex = this.ordinal() + 1;
            if (nex > Sort.values().length - 1) {
                return Sort.values()[0];
            }
            return Sort.values()[nex];
        }
    }

    public static enum Type {
        SHOW_ALL("Show All"),
        BIN_ONLY("BIN Only"),
        AUCTIONS_ONLY("Auctions Only");

        private final String display;

        private Type(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return this.display;
        }

        public Type previous() {
            int prev = this.ordinal() - 1;
            if (prev < 0) {
                return Type.values()[Type.values().length - 1];
            }
            return Type.values()[prev];
        }

        public Type next() {
            int nex = this.ordinal() + 1;
            if (nex > Type.values().length - 1) {
                return Type.values()[0];
            }
            return Type.values()[nex];
        }
    }
}

