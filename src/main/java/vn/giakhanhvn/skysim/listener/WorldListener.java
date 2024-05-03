/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.comphenix.protocol.PacketType
 *  com.comphenix.protocol.PacketType$Play$Client
 *  com.comphenix.protocol.events.ListenerPriority
 *  com.comphenix.protocol.events.PacketAdapter
 *  com.comphenix.protocol.events.PacketContainer
 *  com.comphenix.protocol.events.PacketEvent
 *  com.comphenix.protocol.events.PacketListener
 *  com.comphenix.protocol.wrappers.EnumWrappers$PlayerDigType
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.GameMode
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockState
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.Creeper
 *  org.bukkit.entity.EnderDragon
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.FallingBlock
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockFadeEvent
 *  org.bukkit.event.block.BlockIgniteEvent
 *  org.bukkit.event.block.BlockPhysicsEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.event.block.LeavesDecayEvent
 *  org.bukkit.event.entity.CreatureSpawnEvent
 *  org.bukkit.event.entity.CreatureSpawnEvent$SpawnReason
 *  org.bukkit.event.entity.EntityChangeBlockEvent
 *  org.bukkit.event.entity.EntityCreatePortalEvent
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.event.entity.EntityExplodeEvent
 *  org.bukkit.event.entity.EntityPortalEnterEvent
 *  org.bukkit.event.entity.EntityTargetLivingEntityEvent
 *  org.bukkit.event.entity.SlimeSplitEvent
 *  org.bukkit.event.player.PlayerPortalEvent
 *  org.bukkit.event.weather.WeatherChangeEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package vn.giakhanhvn.skysim.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import com.comphenix.protocol.wrappers.EnumWrappers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SlimeStatistics;
import vn.giakhanhvn.skysim.entity.caverns.CreeperFunction;
import vn.giakhanhvn.skysim.entity.nms.Dragon;
import vn.giakhanhvn.skysim.event.CreeperIgniteEvent;
import vn.giakhanhvn.skysim.item.ExperienceRewardStatistics;
import vn.giakhanhvn.skysim.item.ItemOrigin;
import vn.giakhanhvn.skysim.item.SBlock;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.listener.PListener;
import vn.giakhanhvn.skysim.region.Region;
import vn.giakhanhvn.skysim.region.RegionType;
import vn.giakhanhvn.skysim.skill.FarmingSkill;
import vn.giakhanhvn.skysim.skill.ForagingSkill;
import vn.giakhanhvn.skysim.skill.MiningSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.Groups;
import vn.giakhanhvn.skysim.util.SUtil;

public class WorldListener
extends PListener {
    private static final Map<UUID, List<BlockState>> RESTORER = new HashMap<UUID, List<BlockState>>();
    private static final List<UUID> ALREADY_TELEPORTING = new ArrayList<UUID>();
    public static final Map<UUID, EnumWrappers.PlayerDigType> isCM = new HashMap<UUID, EnumWrappers.PlayerDigType>();
    public static final Map<UUID, Boolean> isSWI = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Integer> miningSpeed = new HashMap<UUID, Integer>();
    public static final Map<UUID, Integer> breakingPower = new HashMap<UUID, Integer>();
    public static final Map<Block, Integer> CACHED_BLOCK_ID = new HashMap<Block, Integer>();
    public static final Map<Block, Byte> CACHED_BLOCK_BYTE = new HashMap<Block, Byte>();
    public static ArrayList<Material> blb = new ArrayList();
    public static ArrayList<Block> changed_blocks = new ArrayList();

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL) {
            return;
        }
        if (e.getEntity() instanceof FallingBlock) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        if (e.getEntity().getType() == EntityType.ENDERMAN) {
            e.setCancelled(true);
        }
        if (e.getBlock().getType() == Material.SOIL && e.getTo() == Material.DIRT) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        org.bukkit.entity.Entity entity = e.getEntity();
        if (entity instanceof EnderDragonPart || entity instanceof EnderDragon || entity instanceof Creeper) {
            e.blockList().clear();
        }
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        if (e.getIgnitingEntity() instanceof Fireball) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent e) {
        if (e.getNewState().getType() == Material.DIRT || e.getNewState().getType() == Material.GRASS) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (!entity.hasMetadata("specEntityObject")) {
            return;
        }
        e.getDrops().clear();
    }

    @EventHandler
    public void onCreeperIgnite(CreeperIgniteEvent e) {
        Creeper creeper = e.getEntity();
        SEntity sEntity = SEntity.findSEntity((org.bukkit.entity.Entity)creeper);
        if (sEntity == null) {
            return;
        }
        if (sEntity.getFunction() instanceof CreeperFunction) {
            ((CreeperFunction)sEntity.getFunction()).onCreeperIgnite(e, sEntity);
        }
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        SBlock sBlock;
        Block block = e.getBlock();
        Player player = e.getPlayer();
        User user = User.getUser(player.getUniqueId());
        SMaterial equiv = SMaterial.getSpecEquivalent(block.getType(), block.getData());
        Region region = Region.getRegionOfBlock(block);
        Collection<ItemStack> drops = block.getDrops(e.getPlayer().getItemInHand());
        if (player.getGameMode() != GameMode.CREATIVE) {
            boolean allowBreak = false;
            if (region != null) {
                double d;
                int level;
                if (Groups.FORAGING_REGIONS.contains((Object)region.getType()) && (block.getType() == Material.LOG || block.getType() == Material.LOG_2 || block.getType() == Material.LEAVES || block.getType() == Material.LEAVES_2)) {
                    allowBreak = true;
                    level = Skill.getLevel(user.getSkillXP(ForagingSkill.INSTANCE), ForagingSkill.INSTANCE.hasSixtyLevels());
                    d = ForagingSkill.INSTANCE.getDoubleDropChance(level);
                    double t = ForagingSkill.INSTANCE.getTripleDropChance(level);
                    WorldListener.extraDrops(drops, d, t, block);
                    WorldListener.addToRestorer(block, player);
                }
                if (Groups.FARMING_REGIONS.contains((Object)region.getType()) && Groups.FARMING_MATERIALS.contains(block.getType())) {
                    allowBreak = true;
                    level = Skill.getLevel(user.getSkillXP(FarmingSkill.INSTANCE), FarmingSkill.INSTANCE.hasSixtyLevels());
                    d = FarmingSkill.INSTANCE.getDoubleDropChance(level);
                    WorldListener.extraDrops(drops, d, 0.0, block);
                }
                if (Groups.MINING_REGIONS.contains((Object)region.getType())) {
                    Material type = block.getType();
                    switch (type) {
                        case COAL_ORE: 
                        case DIAMOND_BLOCK: 
                        case DIAMOND_ORE: 
                        case EMERALD_ORE: 
                        case GOLD_ORE: 
                        case IRON_ORE: 
                        case LAPIS_ORE: 
                        case REDSTONE_ORE: {
                            block.setType(Material.STONE);
                            break;
                        }
                        case STONE: {
                            if (block.getData() != 0) break;
                            block.setType(Material.COBBLESTONE);
                            break;
                        }
                        case OBSIDIAN: 
                        case ENDER_STONE: 
                        case NETHERRACK: 
                        case COBBLESTONE: {
                            block.setType(Material.BEDROCK);
                            WorldListener.regenerateLater(block, 60L, region.getType());
                        }
                    }
                    if (type != block.getType()) {
                        e.setCancelled(true);
                        if (equiv.getStatistics() instanceof ExperienceRewardStatistics) {
                            Skill.reward(((ExperienceRewardStatistics)equiv.getStatistics()).getRewardedSkill(), ((ExperienceRewardStatistics)equiv.getStatistics()).getRewardXP(), player);
                        }
                        int level2 = Skill.getLevel(user.getSkillXP(MiningSkill.INSTANCE), MiningSkill.INSTANCE.hasSixtyLevels());
                        double d2 = MiningSkill.INSTANCE.getDoubleDropChance(level2);
                        double t = MiningSkill.INSTANCE.getTripleDropChance(level2);
                        for (ItemStack drop : drops) {
                            SItem conv = SItem.convert(drop);
                            conv.setOrigin(ItemOrigin.NATURAL_BLOCK);
                            block.getWorld().dropItemNaturally(block.getLocation().clone().add(0.5, 0.5, 0.5), conv.getStack());
                        }
                        WorldListener.extraDrops(drops, d2, t, block);
                    }
                    if (block.getType() == Material.GLOWSTONE) {
                        allowBreak = true;
                        WorldListener.addToRestorer(block, player);
                    }
                }
            }
            if (user.isOnIsland(block)) {
                allowBreak = true;
            }
            if (!allowBreak) {
                e.setCancelled(true);
            }
        }
        if (equiv.getStatistics() instanceof ExperienceRewardStatistics && !e.isCancelled()) {
            Skill.reward(((ExperienceRewardStatistics)equiv.getStatistics()).getRewardedSkill(), ((ExperienceRewardStatistics)equiv.getStatistics()).getRewardXP(), player);
        }
        if ((sBlock = SBlock.getBlock(e.getBlock().getLocation())) != null && !e.isCancelled()) {
            sBlock.delete();
        }
        if (e.isCancelled() || player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        e.setCancelled(true);
        for (ItemStack drop : drops) {
            SItem conv = SItem.convert(drop);
            conv.setOrigin(ItemOrigin.NATURAL_BLOCK);
            block.getWorld().dropItemNaturally(block.getLocation().clone().add(0.5, 0.5, 0.5), conv.getStack());
        }
        block.setType(Material.AIR);
    }

    @EventHandler
    public void onFarmlandDecay(BlockPhysicsEvent e) {
        if (e.getChangedType() == Material.SOIL) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityTarget(EntityTargetLivingEntityEvent e) {
        org.bukkit.entity.Entity entity = e.getEntity();
        SEntity sEntity = SEntity.findSEntity(entity);
        if (sEntity == null) {
            return;
        }
        sEntity.getFunction().onTarget(sEntity, e);
        if (!(sEntity.getGenericInstance() instanceof Dragon)) {
            return;
        }
        e.setCancelled(true);
    }

    public void onPortalEnter(EntityPortalEnterEvent e) {
        Material portalType = e.getLocation().getBlock().getType();
        org.bukkit.entity.Entity entity = e.getEntity();
        if (ALREADY_TELEPORTING.contains(entity.getUniqueId())) {
            return;
        }
        if (portalType == Material.PORTAL) {
            World hub = Bukkit.getWorld((String)(!this.plugin.config.getString("hub_world").isEmpty() ? this.plugin.config.getString("hub_world") : "hub"));
            if (hub == null) {
                entity.sendMessage(ChatColor.RED + "Could not find a hub world to teleport you to!");
                return;
            }
            ALREADY_TELEPORTING.add(entity.getUniqueId());
            SUtil.delay(() -> ALREADY_TELEPORTING.remove(entity.getUniqueId()), 15L);
            entity.sendMessage(ChatColor.GRAY + "Sending you to the hub...");
            entity.teleport(hub.getSpawnLocation());
            return;
        }
        if (!(entity instanceof Player)) {
            return;
        }
        ALREADY_TELEPORTING.add(entity.getUniqueId());
        SUtil.delay(() -> ALREADY_TELEPORTING.remove(entity.getUniqueId()), 15L);
        entity.sendMessage(ChatColor.GRAY + "Sending you to your island...");
        PlayerUtils.sendToIsland((Player)entity);
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPortalCreate(EntityCreatePortalEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent e) {
        Slime slime = e.getEntity();
        SEntity sEntity = SEntity.findSEntity((org.bukkit.entity.Entity)slime);
        if (sEntity != null && sEntity.getStatistics() instanceof SlimeStatistics && !((SlimeStatistics)sEntity.getStatistics()).split()) {
            e.setCancelled(true);
        }
    }

    private static void addToRestorer(Block block, final Player player) {
        if (RESTORER.containsKey(player.getUniqueId())) {
            RESTORER.get(player.getUniqueId()).add(block.getState());
        } else {
            RESTORER.put(player.getUniqueId(), new ArrayList());
            RESTORER.get(player.getUniqueId()).add(block.getState());
            new BukkitRunnable(){

                public void run() {
                    for (BlockState state : RESTORER.get(player.getUniqueId())) {
                        state.getBlock().setType(state.getType());
                        state.setRawData(state.getRawData());
                        state.update();
                    }
                    RESTORER.remove(player.getUniqueId());
                }
            }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 1200L);
        }
    }

    private static void extraDrops(Collection<ItemStack> drops, double d, double t, Block block) {
        for (ItemStack drop : drops) {
            int amount = 0;
            if (SUtil.random(0.0, 1.0) < t) {
                amount = 2;
            } else if (SUtil.random(0.0, 1.0) < d) {
                amount = 1;
            }
            if (amount == 0) continue;
            block.getWorld().dropItemNaturally(block.getLocation().clone().add(0.5, 0.5, 0.5), SUtil.setStackAmount(drop, amount));
        }
    }

    private static BukkitTask regenerateLater(final Block block, long ticks, final RegionType type) {
        return new BukkitRunnable(){

            public void run() {
                if (block.getType() != Material.BEDROCK) {
                    return;
                }
                int r5 = SUtil.random(1, 5);
                switch (type) {
                    case COAL_MINE: {
                        if (SUtil.random(1, 15) == 1) {
                            block.setType(Material.COAL_ORE);
                            break;
                        }
                        block.setType(Material.STONE);
                        break;
                    }
                    case GOLD_MINE: 
                    case GUNPOWDER_MINES: {
                        if (SUtil.random(1, 20) == 1) {
                            block.setType(Material.GOLD_ORE);
                            break;
                        }
                        if (r5 == 1) {
                            block.setType(Material.IRON_ORE);
                            break;
                        }
                        block.setType(Material.STONE);
                        break;
                    }
                    case LAPIS_QUARRY: {
                        if (r5 == 1) {
                            block.setType(Material.LAPIS_ORE);
                            break;
                        }
                        block.setType(Material.STONE);
                        break;
                    }
                    case PIGMENS_DEN: {
                        if (r5 == 1) {
                            block.setType(Material.REDSTONE_ORE);
                            break;
                        }
                        block.setType(Material.STONE);
                        break;
                    }
                    case SLIMEHILL: {
                        if (r5 == 1) {
                            block.setType(Material.EMERALD_ORE);
                            break;
                        }
                        block.setType(Material.STONE);
                        break;
                    }
                    case DIAMOND_RESERVE: {
                        if (r5 == 1) {
                            block.setType(Material.DIAMOND_ORE);
                            break;
                        }
                        block.setType(Material.STONE);
                        break;
                    }
                    case OBSIDIAN_SANCTUARY: {
                        if (SUtil.random(1, 40) == 1) {
                            block.setType(Material.DIAMOND_BLOCK);
                            break;
                        }
                        if (SUtil.random(1, 30) == 1) {
                            block.setType(Material.OBSIDIAN);
                            break;
                        }
                        if (r5 == 1) {
                            block.setType(Material.DIAMOND_ORE);
                            break;
                        }
                        block.setType(Material.STONE);
                        break;
                    }
                    case THE_END: 
                    case DRAGONS_NEST: {
                        block.setType(Material.ENDER_STONE);
                        break;
                    }
                    case BLAZING_FORTRESS: {
                        block.setType(Material.NETHERRACK);
                        break;
                    }
                    default: {
                        block.setType(Material.STONE);
                    }
                }
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), ticks);
    }

    @EventHandler
    public void bpe(BlockPlaceEvent e) {
        if (e.getBlock().getType() != Material.PRISMARINE) {
            return;
        }
        e.getBlock().setMetadata("block_hardness", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)1200));
        e.getBlock().setMetadata("block_power", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)4));
    }

    public static void c() {
        SkySimEngine.getPTC().addPacketListener((PacketListener)new PacketAdapter((Plugin)SkySimEngine.getPlugin(), ListenerPriority.HIGHEST, new PacketType[]{PacketType.Play.Client.BLOCK_DIG}){

            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                EnumWrappers.PlayerDigType digType = (EnumWrappers.PlayerDigType)packet.getPlayerDigTypes().getValues().get(0);
                if (event.getPlayer() != null) {
                    isCM.put(event.getPlayer().getUniqueId(), digType);
                }
            }
        });
    }

    public double findDivFor(double a) {
        double cumB = a / 10.0 / a;
        double cumTongHopA = a * cumB;
        return 10.0 / cumTongHopA;
    }

    public int getPlayerMiningSpeed(Player p) {
        if (miningSpeed.containsKey(p.getUniqueId())) {
            return miningSpeed.get(p.getUniqueId());
        }
        miningSpeed.put(p.getUniqueId(), 100);
        return 600;
    }

    public int getPlayerBreakingPower(Player p) {
        if (breakingPower.containsKey(p.getUniqueId())) {
            return breakingPower.get(p.getUniqueId());
        }
        breakingPower.put(p.getUniqueId(), 8);
        return 8;
    }

    public double findMiningSpeedFor(Player p, Block b) {
        List aA;
        Iterator iterator;
        double finalResult = 0.0;
        double blockHardness = 15.0;
        double blockPower = 0.0;
        List a = b.getMetadata("block_hardness");
        Iterator iterator2 = a.iterator();
        if (iterator2.hasNext()) {
            MetadataValue mv = (MetadataValue)iterator2.next();
            blockHardness = mv.asInt();
            p.sendMessage("" + blockHardness);
        }
        if ((iterator = (aA = b.getMetadata("block_power")).iterator()).hasNext()) {
            MetadataValue mv = (MetadataValue)iterator.next();
            blockPower = mv.asInt();
            p.sendMessage("" + blockPower);
        }
        if (blockHardness == 0.0) {
            blockHardness = 15.0;
        }
        if (blb.contains(b.getType())) {
            blockHardness = 2.0E10;
        }
        finalResult = blockHardness * 30.0 / (double)this.getPlayerMiningSpeed(p);
        if (blockPower > (double)this.getPlayerBreakingPower(p)) {
            finalResult = 1.0E8;
        }
        return finalResult;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public double findMSIS(Player p, Block b) {
        double finalResult = 0.0;
        double blockHardness = 100.0;
        double blockPower = 4.0;
        if (b.getType() == Material.WOOL) {
            if (b.getData() == 3) {
                blockPower = 4.0;
                blockHardness = 1500.0;
            } else {
                if (b.getData() != 7) return -1.0;
                blockPower = 4.0;
                blockHardness = 500.0;
            }
        } else if (b.getType() == Material.PRISMARINE) {
            blockPower = 4.0;
            blockHardness = 800.0;
        } else if (b.getType() == Material.STAINED_CLAY) {
            if (b.getData() != 9) return -1.0;
            blockPower = 4.0;
            blockHardness = 500.0;
        } else if (b.getType() == Material.STONE) {
            if (b.getData() == 4) {
                blockPower = 5.0;
                blockHardness = 2000.0;
            } else if (b.getData() == 0) {
                blockHardness = this.miningValueForMaterial(b.getType());
                blockPower = 4.0;
            }
        } else {
            blockHardness = this.miningValueForMaterial(b.getType());
        }
        if (blb.contains(b.getType())) {
            blockHardness = 2.0E10;
        }
        finalResult = blockHardness * 30.0 / (double)this.getPlayerMiningSpeed(p);
        if (!(blockPower > (double)this.getPlayerBreakingPower(p))) return finalResult;
        return 1.0E8;
    }

    public int miningValueForMaterial(Material m) {
        switch (m) {
            case STONE: {
                return 15;
            }
            case COBBLESTONE: {
                return 20;
            }
            case COAL_ORE: 
            case DIAMOND_ORE: 
            case EMERALD_ORE: 
            case GOLD_ORE: 
            case IRON_ORE: 
            case LAPIS_ORE: 
            case REDSTONE_ORE: 
            case ENDER_STONE: 
            case QUARTZ_ORE: {
                return 60;
            }
            case DIAMOND_BLOCK: 
            case EMERALD_BLOCK: {
                return 100;
            }
            case OBSIDIAN: {
                return 1000;
            }
        }
        return -1;
    }

    public int breakingPowerForMaterial(Material m) {
        switch (m) {
            case STONE: {
                return 15;
            }
            case COBBLESTONE: {
                return 20;
            }
            case COAL_ORE: 
            case DIAMOND_ORE: 
            case EMERALD_ORE: 
            case GOLD_ORE: 
            case IRON_ORE: 
            case LAPIS_ORE: 
            case REDSTONE_ORE: 
            case ENDER_STONE: 
            case QUARTZ_ORE: {
                return 60;
            }
            case DIAMOND_BLOCK: 
            case EMERALD_BLOCK: {
                return 100;
            }
            case OBSIDIAN: {
                return 1000;
            }
        }
        return 0;
    }

    @EventHandler
    public void placeBlockEvent(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld() != player.getWorld() || !(p.getLocation().distance(player.getLocation()) <= 50.0) || p == player) continue;
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((Entity)((CraftLivingEntity)player).getHandle(), 0));
        }
    }
}

