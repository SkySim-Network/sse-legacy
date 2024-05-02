/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.NBTBase
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  org.bukkit.Bukkit
 *  org.bukkit.Effect
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockState
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.EnderDragonPart
 *  org.bukkit.entity.Enderman
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.ItemFrame
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.entity.Villager
 *  org.bukkit.event.Event
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.event.entity.EntityChangeBlockEvent
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.event.entity.PotionSplashEvent
 *  org.bukkit.event.entity.ProjectileHitEvent
 *  org.bukkit.event.inventory.InventoryAction
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.event.inventory.InventoryType
 *  org.bukkit.event.inventory.InventoryType$SlotType
 *  org.bukkit.event.player.PlayerDropItemEvent
 *  org.bukkit.event.player.PlayerFishEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerPickupItemEvent
 *  org.bukkit.event.player.PlayerToggleFlightEvent
 *  org.bukkit.event.player.PlayerToggleSneakEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.collection.ItemCollection;
import vn.giakhanhvn.skysim.enchantment.Enchantment;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.StaticDragonManager;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.JollyPinkGiant;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanGiant;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.AbilityActivation;
import vn.giakhanhvn.skysim.item.FishingRodFunction;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.ItemOrigin;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.PlayerBoostStatistics;
import vn.giakhanhvn.skysim.item.SBlock;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.TickingMaterial;
import vn.giakhanhvn.skysim.item.armor.Witherborn;
import vn.giakhanhvn.skysim.item.storage.Storage;
import vn.giakhanhvn.skysim.item.weapon.EdibleMace;
import vn.giakhanhvn.skysim.listener.PListener;
import vn.giakhanhvn.skysim.potion.PotionEffect;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.DefenseReplacement;
import vn.giakhanhvn.skysim.util.Groups;
import vn.giakhanhvn.skysim.util.ManaReplacement;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class ItemListener
extends PListener {
    public static final Map<Player, String> Classes = new HashMap<Player, String>();
    public static final Map<Player, Boolean> IsDead = new HashMap<Player, Boolean>();

    @EventHandler
    public void useEtherWarp(PlayerInteractEvent e) {
        if (!SItem.isSpecItem(e.getItem())) {
            return;
        }
        SItem sItem = SItem.find(e.getItem());
        if (sItem == null) {
            return;
        }
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        ItemListener.updateStatistics(e.getPlayer());
        Action action = e.getAction();
        if (sItem.getDataString("etherwarp_trans").equals("true") && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && e.getPlayer().isSneaking()) {
            int cost;
            if (!SItem.isAbleToDoEtherWarpTeleportation(player, sItem)) {
                AbilityActivation activation;
                Ability ability = sItem.getType().getAbility();
                if (ability != null && ((activation = ability.getAbilityActivation()) == AbilityActivation.LEFT_CLICK || activation == AbilityActivation.RIGHT_CLICK) && (activation == AbilityActivation.LEFT_CLICK ? action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK : action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
                    PlayerUtils.useAbility(player, sItem);
                }
                return;
            }
            int mana = Repeater.MANA_MAP.get(uuid);
            int resMana = mana - (cost = PlayerUtils.getFinalManaCost(player, sItem, 250));
            if (resMana >= 0) {
                Repeater.MANA_MAP.remove(uuid);
                Repeater.MANA_MAP.put(uuid, resMana);
                final long c = System.currentTimeMillis();
                Repeater.DEFENSE_REPLACEMENT_MAP.put(player.getUniqueId(), new DefenseReplacement(){

                    @Override
                    public String getReplacement() {
                        return ChatColor.AQUA + "-" + cost + " Mana (" + ChatColor.GOLD + "Ether Transmission" + ChatColor.AQUA + ")";
                    }

                    @Override
                    public long getEnd() {
                        return c + 2000L;
                    }
                });
                SItem.etherWarpTeleportation(e.getPlayer(), sItem);
            } else {
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                final long c = System.currentTimeMillis();
                Repeater.MANA_REPLACEMENT_MAP.put(player.getUniqueId(), new ManaReplacement(){

                    @Override
                    public String getReplacement() {
                        return "" + ChatColor.RED + ChatColor.BOLD + "NOT ENOUGH MANA";
                    }

                    @Override
                    public long getEnd() {
                        return c + 1500L;
                    }
                });
            }
        }
    }

    @EventHandler
    public void onPlayerInteracting(PlayerInteractEvent e) {
    }

    @EventHandler
    public void PotionsSplash(PotionSplashEvent e) {
        for (Entity ef : e.getAffectedEntities()) {
            if (!ef.hasMetadata("LD")) continue;
            for (org.bukkit.potion.PotionEffect pe : e.getEntity().getEffects()) {
                if (pe.getType() == PotionEffectType.HEAL) {
                    e.setCancelled(true);
                    continue;
                }
                if (pe.getType() != PotionEffectType.HARM) continue;
                e.setCancelled(true);
                ((LivingEntity)ef).damage(1.0E-4);
            }
        }
        if (e.getEntity().getShooter() instanceof LivingEntity && ((LivingEntity)e.getEntity().getShooter()).hasMetadata("LD")) {
            for (Entity ef : e.getAffectedEntities()) {
                if (ef.hasMetadata("LD")) {
                    e.setCancelled(true);
                    if (ef.isDead()) {
                        return;
                    }
                    ((LivingEntity)ef).setHealth(Math.min(((LivingEntity)ef).getMaxHealth(), ((LivingEntity)ef).getHealth() + ((LivingEntity)ef).getMaxHealth() * 10.0 / 100.0));
                    continue;
                }
                if (!(ef instanceof Player)) continue;
                e.setCancelled(true);
                ((LivingEntity)ef).setHealth(Math.min(((LivingEntity)ef).getMaxHealth(), ((LivingEntity)ef).getHealth() + 500.0));
                ((Player)ef).sendMessage(Sputnik.trans("&a&lBUFF! &fYou were splashed with &cHealing V&f!"));
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        MaterialFunction function;
        AbilityActivation activation;
        if (e.getAction() != Action.RIGHT_CLICK_AIR) {
            for (Player p : e.getPlayer().getWorld().getPlayers()) {
                if (p == e.getPlayer()) continue;
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((net.minecraft.server.v1_8_R3.Entity)((CraftLivingEntity)e.getPlayer()).getHandle(), 0));
            }
        }
        if (!SItem.isSpecItem(e.getItem())) {
            return;
        }
        SItem sItem = SItem.find(e.getItem());
        if (sItem == null) {
            return;
        }
        if (sItem.getType() == SMaterial.HIDDEN_SOUL_WHIP) {
            e.setCancelled(true);
        }
        if (!(sItem.getStack().getType() != Material.MONSTER_EGG && sItem.getStack().getType() != Material.MONSTER_EGGS || e.getPlayer().isOp())) {
            e.setCancelled(true);
        }
        if (sItem.getType() == SMaterial.HIDDEN_GYRO_EYE || sItem.getType() == SMaterial.HIDDEN_VOID_FRAGMENT) {
            e.setCancelled(true);
        }
        ItemListener.updateStatistics(e.getPlayer());
        Action action = e.getAction();
        if (sItem.getType().getStatistics().getSpecificType() == SpecificItemType.HELMET && action == Action.RIGHT_CLICK_AIR && ItemListener.isAir(e.getPlayer().getInventory().getHelmet())) {
            e.getPlayer().getInventory().setHelmet(sItem.getStack());
            e.getPlayer().setItemInHand(null);
        }
        Player player = e.getPlayer();
        Ability ability = sItem.getType().getAbility();
        if (ability != null && ((activation = ability.getAbilityActivation()) == AbilityActivation.LEFT_CLICK || activation == AbilityActivation.RIGHT_CLICK) && (activation == AbilityActivation.LEFT_CLICK ? action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK : action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            if (sItem.getDataString("etherwarp_trans").equals("true")) {
                if (!player.isSneaking()) {
                    PlayerUtils.useAbility(player, sItem);
                }
            } else {
                PlayerUtils.useAbility(player, sItem);
            }
        }
        if ((function = sItem.getType().getFunction()) != null) {
            function.onInteraction(e);
        }
    }

    @EventHandler
    public void onPlayerMage(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        if (!player.getWorld().getName().equals("dungeon")) {
            return;
        }
        if (!Classes.containsKey(player)) {
            Classes.put(player, "M");
        }
        if (Classes.get(player) == "M" && action == Action.LEFT_CLICK_AIR) {
            String ACT = "true";
            ItemStack item = player.getInventory().getItemInHand();
            SItem sitem = SItem.find(item);
            if (sitem == null) {
                return;
            }
            SMaterial material = sitem.getType();
            MaterialStatistics statistics = material.getStatistics();
            SpecificItemType type = statistics.getSpecificType();
            if (type.getName().contains("SWORD")) {
                Location blockLocation = player.getTargetBlock((Set)null, 30).getLocation();
                Location crystalLocation = player.getEyeLocation();
                Vector vector = blockLocation.clone().add(0.1, 0.0, 0.1).toVector().subtract(crystalLocation.clone().toVector());
                double count = 25.0;
                for (int i = 1; i <= (int)count; ++i) {
                    for (Entity entity : player.getWorld().getNearbyEntities(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), 0.5, 0.0, 0.5)) {
                        if (ACT == "false") {
                            return;
                        }
                        if (entity.isDead() || !(entity instanceof LivingEntity) || entity.hasMetadata("GiantSword") || entity instanceof Player || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand) continue;
                        User user = User.getUser(player.getUniqueId());
                        boolean damage = false;
                        double enchantBonus = 0.0;
                        double potionBonus = 0.0;
                        PlayerStatistics statistics1 = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
                        double critDamage = statistics1.getCritDamage().addAll();
                        double speed = statistics1.getSpeed().addAll();
                        double realSpeed = speed * 100.0 % 25.0;
                        double realSpeedDIV = speed - realSpeed;
                        double realSpeedDIVC = realSpeedDIV / 25.0;
                        PlayerInventory inv = player.getInventory();
                        SItem helmet = SItem.find(inv.getHelmet());
                        if (helmet != null && helmet.getType() == SMaterial.WARDEN_HELMET) {
                            enchantBonus += (100.0 + 20.0 * realSpeedDIVC) / 100.0;
                        }
                        for (Enchantment enchantment : sitem.getEnchantments()) {
                            EnchantmentType type1 = enchantment.getType();
                            int level = enchantment.getLevel();
                            if (type1 == EnchantmentType.SHARPNESS) {
                                enchantBonus += (double)(level * 6) / 100.0;
                            }
                            if (type1 == EnchantmentType.SMITE && Groups.UNDEAD_MOBS.contains(entity.getType())) {
                                enchantBonus += (double)(level * 8) / 100.0;
                            }
                            if (type1 == EnchantmentType.ENDER_SLAYER && Groups.ENDERMAN.contains(entity.getType())) {
                                enchantBonus += (double)(level * 12) / 100.0;
                            }
                            if (type1 == EnchantmentType.BANE_OF_ARTHROPODS && Groups.ARTHROPODS.contains(entity.getType())) {
                                enchantBonus += (double)(level * 8) / 100.0;
                            }
                            if (type1 == EnchantmentType.DRAGON_HUNTER && Groups.ENDERDRAGON.contains(entity.getType())) {
                                enchantBonus += (double)(level * 8) / 100.0;
                            }
                            if (type1 != EnchantmentType.CRITICAL) continue;
                            critDamage += (double)(level * 10) / 100.0;
                        }
                        PlayerBoostStatistics playerBoostStatistics = (PlayerBoostStatistics)material.getStatistics();
                        double baseDamage = ((double)(5 + playerBoostStatistics.getBaseDamage()) + statistics1.getStrength().addAll() / 5.0) * (1.0 + statistics1.getStrength().addAll() / 100.0);
                        int combatLevel = Skill.getLevel(User.getUser(player.getUniqueId()).getCombatXP(), false);
                        double weaponBonus = 0.0;
                        double armorBonus = 1.0;
                        int critChanceMul = (int)(statistics1.getCritChance().addAll() * 100.0);
                        int chance = SUtil.random(0, 100);
                        if (chance > critChanceMul) {
                            critDamage = 0.0;
                        }
                        double damageMultiplier = 1.0 + (double)combatLevel * 0.04 + enchantBonus + weaponBonus;
                        double finalCritDamage = critDamage;
                        double finalDamage = baseDamage * damageMultiplier * armorBonus * (1.0 + finalCritDamage);
                        double finalPotionBonus = potionBonus;
                        if (entity.isDead() || !(entity instanceof LivingEntity) || entity instanceof Player || entity instanceof EnderDragonPart || entity instanceof Villager || entity instanceof ArmorStand || entity instanceof Item || entity instanceof ItemFrame) continue;
                        if (EdibleMace.edibleMace.containsKey(player.getUniqueId()) && EdibleMace.edibleMace.get(player.getUniqueId()).booleanValue()) {
                            finalDamage *= 2.0;
                            EdibleMace.edibleMace.put(player.getUniqueId(), false);
                        }
                        final ArmorStand stand3 = (ArmorStand)new SEntity(entity.getLocation().clone().add(SUtil.random(-1.5, 1.5), 1.0, SUtil.random(-1.5, 1.5)), SEntityType.UNCOLLIDABLE_ARMOR_STAND, new Object[0]).getEntity();
                        if (finalCritDamage == 0.0) {
                            stand3.setCustomName("" + ChatColor.GRAY + (int)finalDamage);
                        } else {
                            stand3.setCustomName(SUtil.rainbowize("\u2727" + (int)finalDamage + "\u2727"));
                        }
                        stand3.setCustomNameVisible(true);
                        stand3.setGravity(false);
                        stand3.setVisible(false);
                        user.damageEntity((Damageable)entity, finalDamage);
                        new BukkitRunnable(){

                            public void run() {
                                stand3.remove();
                                this.cancel();
                            }
                        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 30L);
                        ACT = "false";
                    }
                    player.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), Effect.FIREWORKS_SPARK, 24, 1, 0.0f, 0.0f, 0.0f, 1.0f, 0, 64);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player)e.getPlayer();
        Inventory storage = Storage.getCurrentStorageOpened(player);
        if (storage == null) {
            return;
        }
        Inventory inventory = e.getInventory();
        SItem hand = SItem.find(player.getItemInHand());
        if (hand == null) {
            return;
        }
        NBTTagCompound storageData = new NBTTagCompound();
        for (int i = 0; i < inventory.getSize(); ++i) {
            SItem sItem = SItem.find(inventory.getItem(i));
            if (sItem == null) {
                SItem equiv = SItem.of(inventory.getItem(i));
                if (equiv != null) {
                    storageData.setByteArray(String.valueOf(i), SUtil.gzipCompress(equiv.toCompound().toString().getBytes()));
                    continue;
                }
                storageData.remove(String.valueOf(i));
                continue;
            }
            storageData.setByteArray(String.valueOf(i), SUtil.gzipCompress(sItem.toCompound().toString().getBytes()));
        }
        hand.getData().set("storage_data", (NBTBase)storageData);
        hand.update();
        Storage.closeCurrentStorage(player);
    }

    @EventHandler
    public void onPlayerFlight(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        GameMode gameMode = player.getGameMode();
        if (gameMode == GameMode.CREATIVE || gameMode == GameMode.SPECTATOR) {
            return;
        }
        for (ItemStack stack : player.getInventory().getArmorContents()) {
            Ability ability;
            SItem sItem = SItem.find(stack);
            if (sItem == null || (ability = sItem.getType().getAbility()) == null || !e.isFlying() || ability.getAbilityActivation() != AbilityActivation.FLIGHT) continue;
            e.setCancelled(true);
            PlayerUtils.useAbility(player, sItem);
        }
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        GameMode gameMode = player.getGameMode();
        for (ItemStack stack : player.getInventory().getArmorContents()) {
            Ability ability;
            SItem sItem = SItem.find(stack);
            if (sItem == null || (ability = sItem.getType().getAbility()) == null || !player.isSneaking() || ability.getAbilityActivation() != AbilityActivation.SNEAK) continue;
            PlayerUtils.useAbility(player, sItem);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTopInventory().getType() != InventoryType.CRAFTING) {
            return;
        }
        if (e.getSlotType() != InventoryType.SlotType.CONTAINER && e.getSlotType() != InventoryType.SlotType.QUICKBAR) {
            return;
        }
        if (e.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            return;
        }
        Inventory inventory = e.getClickedInventory();
        if (inventory == null) {
            return;
        }
        if (inventory.getType() != InventoryType.PLAYER) {
            return;
        }
        ItemStack current = e.getCurrentItem();
        if (current == null) {
            return;
        }
        SItem sItem = SItem.find(current);
        if (sItem == null) {
            sItem = SItem.of(current);
        }
        ItemListener.updateStatistics((Player)e.getWhoClicked());
        if (sItem.getType().getStatistics().getSpecificType() == null || sItem.getType().getStatistics().getSpecificType() != SpecificItemType.HELMET) {
            return;
        }
        PlayerInventory playerInventory = (PlayerInventory)inventory;
        if (!ItemListener.isAir(playerInventory.getHelmet())) {
            return;
        }
        e.setCancelled(true);
        e.setCurrentItem(new ItemStack(Material.AIR));
        playerInventory.setHelmet(current);
    }

    @EventHandler
    public void onArmorChange(InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getType() != InventoryType.PLAYER && e.getClickedInventory().getType() != InventoryType.CRAFTING) {
            return;
        }
        ItemListener.updateStatistics((Player)e.getWhoClicked());
        player.getInventory().setHelmet(player.getInventory().getHelmet());
        player.getInventory().setChestplate(player.getInventory().getChestplate());
        player.getInventory().setLeggings(player.getInventory().getLeggings());
        player.getInventory().setBoots(player.getInventory().getBoots());
    }

    @EventHandler
    public void onArmorChange1(InventoryCloseEvent e) {
        Player player = (Player)e.getPlayer();
        player.getInventory().setHelmet(player.getInventory().getHelmet());
        player.getInventory().setChestplate(player.getInventory().getChestplate());
        player.getInventory().setLeggings(player.getInventory().getLeggings());
        player.getInventory().setBoots(player.getInventory().getBoots());
        ItemListener.updateStatistics(player);
    }

    @EventHandler
    public void onArmorChange2(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
        ItemStack helmet = player.getInventory().getHelmet();
        SUtil.delay(() -> player.getInventory().setHelmet(helmet), 10L);
        SItem helmet1 = SItem.find(player.getInventory().getHelmet());
        if (helmet1 == null) {
            return;
        }
        TickingMaterial tickingMaterial = helmet1.getType().getTickingInstance();
        if (tickingMaterial != null) {
            statistics.tickItem(39, tickingMaterial.getInterval(), () -> tickingMaterial.tick(helmet1, Bukkit.getPlayer((UUID)statistics.getUuid())));
        }
        ItemListener.updateStatistics(player);
    }

    @EventHandler(priority=EventPriority.HIGH)
    public void onItemClick(InventoryClickEvent e) {
        ItemStack stack = e.getCurrentItem();
        if (stack == null) {
            return;
        }
        SItem sItem = SItem.find(stack);
        if (sItem == null) {
            return;
        }
        if (sItem.getType().getFunction() == null) {
            return;
        }
        sItem.getType().getFunction().onInventoryClick(sItem, e);
    }

    @EventHandler
    public void onItemMove(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getType() != InventoryType.PLAYER) {
            return;
        }
        if (e.getSlot() != 8) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        SItem sItem = SItem.find(e.getItemInHand());
        if (sItem == null) {
            return;
        }
        if (sItem.getType().getStatistics().getSpecificType() == SpecificItemType.HELMET && ItemListener.isAir(e.getPlayer().getInventory().getHelmet())) {
            e.setCancelled(true);
            e.getPlayer().getInventory().setHelmet(sItem.getStack());
            e.getPlayer().setItemInHand(null);
            return;
        }
        if (!sItem.getType().isCraft()) {
            if (sItem.getType().getStatistics().getType() != GenericItemType.BLOCK) {
                e.setCancelled(true);
            } else {
                new SBlock(e.getBlockPlaced().getLocation(), sItem.getType(), sItem.getData()).save();
            }
        }
    }

    @EventHandler
    public void onFrameInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Player player = e.getPlayer();
        final Block block = e.getClickedBlock();
        ItemStack hand = e.getItem();
        if (hand == null) {
            return;
        }
        SItem item = SItem.find(hand);
        if (item == null) {
            return;
        }
        if (block.getType() != Material.ENDER_PORTAL_FRAME) {
            return;
        }
        SBlock sBlock = SBlock.getBlock(block.getLocation());
        if (sBlock == null) {
            e.setCancelled(true);
            return;
        }
        if (sBlock.getType() != SMaterial.SUMMONING_FRAME) {
            e.setCancelled(true);
            return;
        }
        if (!block.hasMetadata("placer")) {
            if (item.getType() != SMaterial.SUMMONING_EYE) {
                return;
            }
            block.setMetadata("placer", (MetadataValue)new FixedMetadataValue((Plugin)this.plugin, (Object)player.getUniqueId()));
            BlockState state = block.getState();
            state.setRawData((byte)4);
            state.update();
            player.getInventory().setItemInHand(SItem.of(SMaterial.SLEEPING_EYE).getStack());
            List<Location> locations = StaticDragonManager.EYES.containsKey(player.getUniqueId()) ? StaticDragonManager.EYES.get(player.getUniqueId()) : new ArrayList<Location>();
            locations.add(block.getLocation());
            StaticDragonManager.EYES.remove(player.getUniqueId());
            StaticDragonManager.EYES.put(player.getUniqueId(), locations);
            int quantity = 0;
            for (List<Location> ls : StaticDragonManager.EYES.values()) {
                quantity += ls.size();
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.getWorld().getName().equals("dragon")) continue;
                p.sendMessage(ChatColor.DARK_PURPLE + "\u262c " + ChatColor.GREEN + player.getName() + ChatColor.LIGHT_PURPLE + " placed a Summoning Eye! " + (quantity == 8 ? "Brace yourselves! " : "") + ChatColor.GRAY + "(" + (quantity == 8 ? ChatColor.GREEN : ChatColor.YELLOW) + quantity + ChatColor.GRAY + "/" + ChatColor.GREEN + "8" + ChatColor.GRAY + ")");
            }
            if (quantity != 8) {
                return;
            }
            ArrayList<UUID> cleared = new ArrayList<UUID>();
            for (List<Location> ls : StaticDragonManager.EYES.values()) {
                for (Location location : ls) {
                    Block b = location.getBlock();
                    List values = b.getMetadata("placer");
                    Player p = Bukkit.getPlayer((UUID)((UUID)((MetadataValue)values.get(0)).value()));
                    if (p == null || cleared.contains(p.getUniqueId())) continue;
                    PlayerInventory inventory = p.getInventory();
                    for (int i = 0; i < inventory.getSize(); ++i) {
                        SItem si = SItem.find(inventory.getItem(i));
                        if (si == null || si.getType() != SMaterial.SLEEPING_EYE) continue;
                        inventory.setItem(i, SItem.of(SMaterial.REMNANT_OF_THE_EYE).getStack());
                    }
                    p.sendMessage(ChatColor.DARK_PURPLE + "Your Sleeping Eyes have been awoken by the magic of the Dragon. They are now Remnants of the Eye!");
                    cleared.add(p.getUniqueId());
                }
            }
            StaticDragonManager.ACTIVE = true;
            block.getWorld().playSound(block.getLocation(), Sound.ENDERMAN_STARE, 50.0f, -2.0f);
            new BukkitRunnable(){

                public void run() {
                    block.getWorld().playSound(block.getLocation(), Sound.ENDERDRAGON_DEATH, 50.0f, -2.0f);
                }
            }.runTaskLater((Plugin)this.plugin, 90L);
            new BukkitRunnable(){

                public void run() {
                    for (int i = 0; i < 3; ++i) {
                        block.getWorld().playSound(block.getLocation(), Sound.EXPLODE, 50.0f, -2.0f);
                    }
                    SEntityType dragonType = SEntityType.PROTECTOR_DRAGON;
                    int chance = SUtil.random(0, 100);
                    if (chance >= 16) {
                        dragonType = SEntityType.OLD_DRAGON;
                    }
                    if (chance >= 32) {
                        dragonType = SEntityType.WISE_DRAGON;
                    }
                    if (chance >= 48) {
                        dragonType = SEntityType.UNSTABLE_DRAGON;
                    }
                    if (chance >= 64) {
                        dragonType = SEntityType.YOUNG_DRAGON;
                    }
                    if (chance >= 80) {
                        dragonType = SEntityType.STRONG_DRAGON;
                    }
                    if (chance >= 96) {
                        dragonType = SEntityType.SUPERIOR_DRAGON;
                    }
                    SEntity entity = new SEntity(block.getLocation().clone().add(0.0, 53.0, 0.0), dragonType, new Object[0]);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-642, 36, -246).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-635, 51, -233).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-717, 39, -255).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-715, 44, -299).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-682, 58, -323).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-638, 51, -318).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-623, 58, -293).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-678, 31, -287).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-697, 35, -249).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    block.getWorld().spawnEntity(block.getWorld().getBlockAt(-638, 40, -309).getLocation().add(-0.5, 0.7, -0.5), EntityType.ENDER_CRYSTAL);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!p.getWorld().getName().equals("dragon")) continue;
                        Vector vector = p.getLocation().clone().subtract(new Vector(-670.5, 58.0, -275.5)).toVector();
                        p.setVelocity(vector.normalize().multiply(40.0).setY(100.0));
                    }
                    StaticDragonManager.DRAGON = entity;
                    block.getWorld().playSound(block.getLocation(), Sound.ENDERDRAGON_GROWL, 50.0f, 1.0f);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!p.getWorld().getName().equals("dragon")) continue;
                        p.sendMessage(ChatColor.DARK_PURPLE + "\u262c " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "The " + ChatColor.RED + ChatColor.BOLD + entity.getStatistics().getEntityName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + " has spawned!");
                    }
                }
            }.runTaskLater((Plugin)this.plugin, 180L);
            return;
        }
        List values = block.getMetadata("placer");
        Player p = Bukkit.getPlayer((UUID)((UUID)((MetadataValue)values.get(0)).value()));
        if (p == null) {
            return;
        }
        if (item.getType() == SMaterial.SLEEPING_EYE) {
            if (!p.getUniqueId().equals(player.getUniqueId())) {
                player.sendMessage(ChatColor.RED + "You can only recover Summoning Eyes that you placed!");
                return;
            }
            if (StaticDragonManager.ACTIVE) {
                player.sendMessage(ChatColor.RED + "You cannot recover Summoning Eyes after the dragon has been summoned!");
                return;
            }
            block.removeMetadata("placer", (Plugin)this.plugin);
            BlockState state = block.getState();
            state.setRawData((byte)0);
            state.update();
            player.getInventory().setItemInHand(SItem.of(SMaterial.SUMMONING_EYE).getStack());
            StaticDragonManager.EYES.get(p.getUniqueId()).remove(block.getLocation());
            player.sendMessage(ChatColor.DARK_PURPLE + "You recovered a Summoning Eye!");
            return;
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        List o;
        Item item = e.getItem();
        Player player = e.getPlayer();
        ItemListener.updateStatistics(player);
        NBTTagCompound compound = CraftItemStack.asNMSCopy((ItemStack)item.getItemStack()).getTag();
        if (compound == null) {
            compound = new NBTTagCompound();
        }
        if (!compound.hasKey("type")) {
            item.getItemStack().setItemMeta(SItem.of(item.getItemStack()).getStack().getItemMeta());
        }
        if (item.hasMetadata("owner") && (o = item.getMetadata("owner")).size() != 0 && !((MetadataValue)o.get(0)).asString().equals(e.getPlayer().getUniqueId().toString())) {
            e.setCancelled(true);
            return;
        }
        User user = User.getUser(player.getUniqueId());
        ItemStack stack = item.getItemStack();
        SItem sItem = SItem.find(stack);
        if (sItem == null) {
            throw new NullPointerException("Something messed up! Check again");
        }
        if (item.hasMetadata("obtained")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.getWorld().getName().equals("dragon")) continue;
                if (!sItem.getFullName().equals("\u00a76Ender Dragon") && !sItem.getFullName().equals("\u00a75Ender Dragon")) {
                    p.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.YELLOW + " has obtained " + sItem.getFullName() + ChatColor.YELLOW + "!");
                    continue;
                }
                p.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.YELLOW + " has obtained " + ChatColor.GRAY + "[Lvl 1] " + sItem.getFullName() + ChatColor.YELLOW + "!");
            }
        }
        if (sItem.getOrigin() == ItemOrigin.NATURAL_BLOCK || sItem.getOrigin() == ItemOrigin.MOB) {
            sItem.setOrigin(ItemOrigin.UNKNOWN);
            ItemCollection collection = ItemCollection.getByMaterial(sItem.getType(), stack.getDurability());
            if (collection != null) {
                int prev = user.getCollection(collection);
                user.addToCollection(collection, stack.getAmount());
                user.save();
                if (prev == 0) {
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  COLLECTION UNLOCKED " + ChatColor.RESET + ChatColor.YELLOW + collection.getName());
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 2.0f);
                }
            }
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        SItem sItem = SItem.find(e.getItemDrop().getItemStack());
        if (sItem != null && (sItem.getType() == SMaterial.SKYBLOCK_MENU || sItem.getType() == SMaterial.QUIVER_ARROW)) {
            e.setCancelled(true);
        }
        ItemListener.updateStatistics(e.getPlayer());
    }

    @EventHandler
    public void onItemMove(PlayerDropItemEvent e) {
        SItem sItem = SItem.find(e.getItemDrop().getItemStack());
        if (sItem != null && (sItem.getType() == SMaterial.SKYBLOCK_MENU || sItem.getType() == SMaterial.QUIVER_ARROW)) {
            e.setCancelled(true);
        }
        ItemListener.updateStatistics(e.getPlayer());
    }

    @EventHandler
    public void onItemDrop1(PlayerDropItemEvent e) {
        SItem sItem = SItem.find(e.getItemDrop().getItemStack());
        if (sItem != null && sItem.getType() == SMaterial.BONEMERANG && e.getItemDrop().getItemStack().toString().contains("GHAST_TEAR")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemMove1(PlayerDropItemEvent e) {
        SItem sItem = SItem.find(e.getItemDrop().getItemStack());
        if (sItem != null && (sItem.getType() == SMaterial.SKYBLOCK_MENU || sItem.getType() == SMaterial.QUIVER_ARROW)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFishingRodReel(PlayerFishEvent e) {
        SItem rod = SItem.find(e.getPlayer().getItemInHand());
        if (rod == null) {
            return;
        }
        e.getHook().setMetadata("owner", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)e.getPlayer()));
        MaterialFunction function = rod.getType().getFunction();
        if (function == null) {
            return;
        }
        if (function instanceof FishingRodFunction) {
            ((FishingRodFunction)function).onFish(rod, e);
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent e) {
        SItem item = SItem.find(e.getPotion().getItem());
        if (item == null) {
            return;
        }
        if (!item.isPotion()) {
            return;
        }
        e.setCancelled(true);
        for (LivingEntity entity : e.getAffectedEntities()) {
            User user;
            if (!(entity instanceof Player) || (user = User.getUser(entity.getUniqueId())) == null) continue;
            for (PotionEffect effect : item.getPotionEffects()) {
                PlayerUtils.updatePotionEffects(user, PlayerUtils.STATISTICS_CACHE.get(user.getUuid()));
                if (effect.getType().getOnDrink() != null) {
                    effect.getType().getOnDrink().accept(effect, (Player)entity);
                }
                long ticks = (long)((double)effect.getDuration() * e.getIntensity(entity));
                if (!user.hasPotionEffect(effect.getType()) || user.hasPotionEffect(effect.getType()) && ticks > user.getPotionEffect(effect.getType()).getRemaining()) {
                    user.removePotionEffect(effect.getType());
                    user.addPotionEffect(new PotionEffect(effect.getType(), effect.getLevel(), ticks));
                }
                entity.sendMessage((effect.getType().isBuff() ? ChatColor.GREEN + "" + ChatColor.BOLD + "BUFF!" : ChatColor.RED + "" + ChatColor.BOLD + "DEBUFF!") + ChatColor.RESET + ChatColor.WHITE + " You " + (e.getPotion().getShooter().equals(entity) ? "splashed yourself" : "were splashed") + " with " + effect.getDisplayName() + ChatColor.WHITE + "!");
            }
        }
    }

    public static void updateStatistics(final Player player) {
        final PlayerInventory inv = player.getInventory();
        final ItemStack beforeHelmet = inv.getHelmet();
        final ItemStack beforeChestplate = inv.getChestplate();
        final ItemStack beforeLeggings = inv.getLeggings();
        final ItemStack beforeBoots = inv.getBoots();
        new BukkitRunnable(){

            public void run() {
                PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
                ItemStack afterHelmet = inv.getHelmet();
                ItemStack afterChestplate = inv.getChestplate();
                ItemStack afterLeggings = inv.getLeggings();
                ItemStack afterBoots = inv.getBoots();
                boolean helmetSimilar = ItemListener.similar(beforeHelmet, afterHelmet);
                boolean chestplateSimilar = ItemListener.similar(beforeChestplate, afterChestplate);
                boolean leggingsSimilar = ItemListener.similar(beforeLeggings, afterLeggings);
                boolean bootsSimilar = ItemListener.similar(beforeBoots, afterBoots);
                SItem helmet = null;
                SItem chestplate = null;
                SItem leggings = null;
                SItem boots = null;
                if (!helmetSimilar) {
                    helmet = SItem.find(afterHelmet);
                    PlayerUtils.updateArmorStatistics(helmet, statistics, 0);
                }
                if (!chestplateSimilar) {
                    chestplate = SItem.find(afterChestplate);
                    PlayerUtils.updateArmorStatistics(chestplate, statistics, 1);
                }
                if (!leggingsSimilar) {
                    leggings = SItem.find(afterLeggings);
                    PlayerUtils.updateArmorStatistics(leggings, statistics, 2);
                }
                if (!bootsSimilar) {
                    boots = SItem.find(afterBoots);
                    PlayerUtils.updateArmorStatistics(boots, statistics, 3);
                }
                PlayerUtils.updateInventoryStatistics(player, statistics);
                User.getUser(player.getUniqueId()).updateArmorInventory();
                ItemListener.checkCondition(player);
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 1L);
    }

    public static void checkCondition(Player p) {
        SItem helm = SItem.find(p.getInventory().getHelmet());
        SItem chest = SItem.find(p.getInventory().getChestplate());
        SItem leg = SItem.find(p.getInventory().getLeggings());
        SItem boots = SItem.find(p.getInventory().getBoots());
        if (helm != null && chest != null && leg != null && boots != null) {
            if (Groups.WITHER_HELMETS.contains((Object)helm.getType()) && Groups.WITHER_CHESTPLATES.contains((Object)chest.getType()) && Groups.WITHER_LEGGINGS.contains((Object)leg.getType()) && Groups.WITHER_BOOTS.contains((Object)boots.getType())) {
                if (Witherborn.WITHER_COOLDOWN.containsKey(p.getUniqueId())) {
                    if (!Witherborn.WITHER_COOLDOWN.get(p.getUniqueId()).booleanValue() && !Witherborn.WITHER_MAP.containsKey(p.getUniqueId())) {
                        Witherborn w = new Witherborn(p);
                        w.spawnWither();
                    }
                } else if (!Witherborn.WITHER_MAP.containsKey(p.getUniqueId())) {
                    Witherborn w = new Witherborn(p);
                    w.spawnWither();
                }
            } else if (Witherborn.WITHER_MAP.containsKey(p.getUniqueId())) {
                Witherborn.WITHER_MAP.remove(p.getUniqueId());
            }
        } else if (Witherborn.WITHER_MAP.containsKey(p.getUniqueId())) {
            Witherborn.WITHER_MAP.remove(p.getUniqueId());
        }
    }

    public static void updateStatistics1(Player player) {
        PlayerInventory inv = player.getInventory();
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
        ItemStack afterHelmet = inv.getHelmet();
        ItemStack afterChestplate = inv.getChestplate();
        ItemStack afterLeggings = inv.getLeggings();
        ItemStack afterBoots = inv.getBoots();
        SItem helmet = null;
        SItem chestplate = null;
        SItem leggings = null;
        SItem boots = null;
        helmet = SItem.find(afterHelmet);
        PlayerUtils.updateArmorStatistics(helmet, statistics, 0);
        chestplate = SItem.find(afterChestplate);
        PlayerUtils.updateArmorStatistics(chestplate, statistics, 1);
        leggings = SItem.find(afterLeggings);
        PlayerUtils.updateArmorStatistics(leggings, statistics, 2);
        boots = SItem.find(afterBoots);
        PlayerUtils.updateArmorStatistics(boots, statistics, 3);
        SUtil.delay(() -> PlayerUtils.updateInventoryStatistics(player, statistics), 1L);
    }

    private static boolean similar(ItemStack is, ItemStack is1) {
        if (is == null && is1 == null) {
            return true;
        }
        if (is != null && is1 == null) {
            return false;
        }
        if (is == null) {
            return false;
        }
        return is.isSimilar(is1);
    }

    private static boolean isAir(ItemStack is) {
        if (is == null) {
            return true;
        }
        return is.getType() == Material.AIR;
    }

    @EventHandler
    public void aAaB(ProjectileHitEvent enn) {
        Projectile e = enn.getEntity();
        if (!(e instanceof Arrow)) {
            return;
        }
        if (!(((Arrow)e).getShooter() instanceof Player)) {
            return;
        }
        Player player = (Player)((Arrow)e).getShooter();
        boolean ACT = true;
        if (e.isOnGround()) {
            return;
        }
        for (Entity enderman_1 : e.getWorld().getNearbyEntities(e.getLocation(), 1.5, 1.5, 1.5)) {
            if (!(enderman_1 instanceof Enderman) || !ACT || e.isOnGround() || enderman_1.isDead()) continue;
            if (SItem.find(player.getItemInHand()) == null) {
                e.remove();
                break;
            }
            if (SItem.find(player.getItemInHand()).getType() != SMaterial.TERMINATOR && SItem.find(player.getItemInHand()).getType() != SMaterial.JUJU_SHORTBOW) {
                e.remove();
                return;
            }
            ACT = false;
            EntityDamageByEntityEvent bl = new EntityDamageByEntityEvent((Entity)((Arrow)e), (Entity)((LivingEntity)enderman_1), EntityDamageEvent.DamageCause.CUSTOM, 1);
            Bukkit.getPluginManager().callEvent((Event)bl);
            ((LivingEntity)enderman_1).setHealth(((LivingEntity)enderman_1).getHealth() - Math.min(((LivingEntity)enderman_1).getHealth(), bl.getDamage()));
            e.remove();
        }
    }

    @EventHandler
    public void changeBlock(EntityChangeBlockEvent event) {
        Entity fallingBlock = event.getEntity();
        if (event.getEntityType() == EntityType.FALLING_BLOCK && fallingBlock.hasMetadata("t")) {
            Block block = event.getBlock();
            block.setType(Material.AIR);
            event.setCancelled(true);
            List<Entity> entityList = fallingBlock.getNearbyEntities(3.0, 3.0, 3.0);
            fallingBlock.getWorld().playSound(fallingBlock.getLocation(), Sound.EXPLODE, 2.0f, 0.0f);
            fallingBlock.getWorld().playEffect(fallingBlock.getLocation(), Effect.EXPLOSION_HUGE, 0);
            for (Entity e : fallingBlock.getNearbyEntities(7.0, 7.0, 7.0)) {
                if (!(e instanceof Item)) continue;
                e.remove();
            }
            fallingBlock.setVelocity(new Vector(0, 0, 0));
            List<Entity> fallingBlockList = fallingBlock.getNearbyEntities(7.0, 7.0, 7.0);
            fallingBlockList.forEach(entity -> {
                if (entity.getType() == EntityType.FALLING_BLOCK && entity.hasMetadata("t")) {
                    entity.remove();
                }
            });
            entityList.forEach(entity -> {
                if (entity instanceof Player) {
                    Player p = (Player)entity;
                    JollyPinkGiant.damagePlayer(p);
                } else if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                    ((LivingEntity)entity).damage(0.0);
                }
            });
            fallingBlock.remove();
        }
    }

    @EventHandler
    public void changeBlockF1(EntityChangeBlockEvent event) {
        Entity fallingBlock = event.getEntity();
        if (event.getEntityType() == EntityType.FALLING_BLOCK && fallingBlock.hasMetadata("f")) {
            Block block = event.getBlock();
            block.setType(Material.AIR);
            event.setCancelled(true);
            List<Entity> entityList = fallingBlock.getNearbyEntities(3.0, 3.0, 3.0);
            fallingBlock.getWorld().playSound(fallingBlock.getLocation(), Sound.EXPLODE, 2.0f, 0.0f);
            fallingBlock.getWorld().playEffect(fallingBlock.getLocation(), Effect.EXPLOSION_HUGE, 0);
            for (Entity e : fallingBlock.getNearbyEntities(7.0, 7.0, 7.0)) {
                if (!(e instanceof Item)) continue;
                e.remove();
            }
            fallingBlock.setVelocity(new Vector(0, 0, 0));
            List<Entity> fallingBlockList = fallingBlock.getNearbyEntities(7.0, 7.0, 7.0);
            fallingBlockList.forEach(entity -> {
                if (entity.getType() == EntityType.FALLING_BLOCK && entity.hasMetadata("f")) {
                    entity.remove();
                }
            });
            entityList.forEach(entity -> {
                if (entity instanceof Player) {
                    Player p = (Player)entity;
                    SadanGiant.damagePlayer(p);
                } else if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                    ((LivingEntity)entity).damage(0.0);
                }
            });
            fallingBlock.remove();
        }
    }

    @EventHandler
    public void onentityded(EntityDeathEvent e) {
        if (e.getEntity().getWorld().getName().contains("f6")) {
            e.setDroppedExp(0);
        }
    }
}

