/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.region;

import org.bukkit.ChatColor;

public enum RegionType {
    VILLAGE("Village"),
    MOUNTAIN("Mountain"),
    FOREST("Forest"),
    FARM("Farm"),
    RUINS("Ruins"),
    DWARVEN_THE_LIFT("Dwarven Mines"),
    DWARVEN_MINES_VILLAGE("Dwarven Village"),
    DWARVEN_MINES("Dwarven Mines", ChatColor.DARK_GREEN),
    DWARVEN_MINES_GATE("Gates to the Mines"),
    DWARVEN_RAMPARTS_QUARRY("Rampart's Quarry"),
    DWARVEN_CLIFFSIDE_VEINS("Cliffside Veins"),
    DWARVEN_GREAT_ICE_WALL("Great Ice Wall"),
    DWARVEN_THE_MIST("The Mist", ChatColor.DARK_GRAY),
    UNFINISHED_AREA("Unfinished Area", ChatColor.RED),
    COLOSSEUM("Colosseum"),
    GRAVEYARD("Graveyard", ChatColor.RED),
    COAL_MINE("Coal Mine"),
    COAL_MINE_CAVES("Coal Mine"),
    WILDERNESS("Wilderness", ChatColor.DARK_GREEN),
    HIGH_LEVEL("High Level", ChatColor.RED),
    AUCTION_HOUSE("Auction House", ChatColor.GOLD),
    BAZAAR_ALLEY("Bazaar Alley", ChatColor.YELLOW),
    BANK("Bank", ChatColor.GOLD),
    BLACKSMITH("Blacksmith"),
    LIBRARY("Library"),
    THE_BARN("The Barn"),
    MUSHROOM_DESERT("Mushroom Desert"),
    GOLD_MINE("Gold Mine", ChatColor.GOLD),
    DEEP_CAVERN("Deep Caverns"),
    GUNPOWDER_MINES("Gunpowder Mines"),
    LAPIS_QUARRY("Lapis Quarry"),
    PIGMENS_DEN("Pigmen's Den"),
    SLIMEHILL("Slimehill"),
    BIRCH_PARK("Birch Park", ChatColor.GREEN),
    SPRUCE_WOODS("Spruce Woods", ChatColor.GREEN),
    DARK_THICKET("Dark Thicket", ChatColor.GREEN),
    SAVANNA_WOODLAND("Savanna Woodland", ChatColor.GREEN),
    JUNGLE_ISLAND("Jungle Island", ChatColor.GREEN),
    HOWLING_CAVE("Howling Cave"),
    DIAMOND_RESERVE("Diamond Reserve"),
    OBSIDIAN_SANCTUARY("Obsidian Sanctuary"),
    SPIDERS_DEN("Spider's Den", ChatColor.RED),
    SPIDERS_DEN_HIVE("Spider's Den", ChatColor.RED),
    BLAZING_FORTRESS("Blazing Fortress", ChatColor.RED),
    THE_END("The End", ChatColor.LIGHT_PURPLE),
    THE_END_NEST("The End", ChatColor.LIGHT_PURPLE),
    DRAGONS_NEST("Dragon's Nest", ChatColor.DARK_PURPLE);

    private final String name;
    private final ChatColor color;

    private RegionType(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    private RegionType(String name) {
        this(name, ChatColor.AQUA);
    }

    private RegionType() {
        this(null, ChatColor.GRAY);
    }

    public static RegionType getByID(int id) {
        return RegionType.values()[id];
    }

    public static RegionType getType(String string) {
        try {
            return RegionType.valueOf(string);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    public String getName() {
        return this.name;
    }

    public ChatColor getColor() {
        return this.color;
    }
}

