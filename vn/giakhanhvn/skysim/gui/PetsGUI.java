/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.EulerAngle
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;
import vn.giakhanhvn.skysim.gui.GUIOpenEvent;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.PaginationList;
import vn.giakhanhvn.skysim.util.SLog;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class PetsGUI
extends GUI {
    private static final int[] INTERIOR = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43};
    public static final Map<UUID, Boolean> PET_SHOWN = new HashMap<UUID, Boolean>();
    private int page;
    private boolean pickup;

    public static void setShowPets(Player p, boolean bo) {
        if (p == null) {
            SLog.severe("An unexpected error occured on Pets saving!");
        }
        PET_SHOWN.put(p.getUniqueId(), bo);
    }

    public static boolean getShowPet(Player p) {
        if (p == null) {
            return true;
        }
        if (PET_SHOWN.containsKey(p.getUniqueId())) {
            return PET_SHOWN.get(p.getUniqueId());
        }
        PET_SHOWN.put(p.getUniqueId(), true);
        return true;
    }

    public PetsGUI(int page, boolean pickup) {
        super("Pets", 54);
        this.page = page;
        this.pickup = pickup;
    }

    public PetsGUI(boolean pickup) {
        this(1, pickup);
    }

    public PetsGUI() {
        this(false);
    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        Pet.PetItem active;
        final Player player = e.getPlayer();
        final User user = User.getUser(player.getUniqueId());
        this.border(BLACK_STAINED_GLASS_PANE);
        PaginationList paged = new PaginationList(28);
        paged.addAll(user.getPets());
        if (paged.size() == 0) {
            this.page = 0;
        }
        final int finalPage = this.page;
        if (this.page > 1) {
            this.title = "(" + finalPage + "/" + this.page + ") Pets";
            this.set(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    new PetsGUI(finalPage - 1, false).open((Player)e.getWhoClicked());
                }

                @Override
                public int getSlot() {
                    return 45;
                }

                @Override
                public ItemStack getItem() {
                    return SUtil.createNamedItemStack(Material.ARROW, ChatColor.GREEN + "Pervious Page");
                }
            });
        }
        if (this.page != paged.getPageCount()) {
            this.set(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    new PetsGUI(finalPage + 1, false).open((Player)e.getWhoClicked());
                }

                @Override
                public int getSlot() {
                    return 53;
                }

                @Override
                public ItemStack getItem() {
                    return SUtil.createNamedItemStack(Material.ARROW, ChatColor.GREEN + "Next Page");
                }
            });
        }
        final String name = (active = user.getActivePet()) == null ? ChatColor.RED + "None" : active.getRarity().getColor() + active.getType().getDisplayName(active.getType().getData());
        this.set(4, SUtil.getStack(ChatColor.GREEN + "Pets", Material.BONE, (short)0, 1, ChatColor.GRAY + "View and manage all of your", ChatColor.GRAY + "Pets.", " ", ChatColor.GRAY + "Level up your pets faster by", ChatColor.GRAY + "gaining XP in their favorite", ChatColor.GRAY + "skill!", " ", ChatColor.GRAY + "Selected pet: " + name));
        this.set(47, SUtil.getStack(ChatColor.GREEN + "Pet Score Rewards", Material.DIAMOND, (short)0, 1, ChatColor.GRAY + "Pet score is calculated based", ChatColor.GRAY + "on how many " + ChatColor.GREEN + "unique" + ChatColor.GRAY + " pets you", ChatColor.GRAY + "have and the " + ChatColor.GREEN + "rarity" + ChatColor.GRAY + " of these", ChatColor.GRAY + "pets.", " ", ChatColor.GOLD + "10 Score: " + ChatColor.GRAY + "+" + ChatColor.AQUA + "1 Magic Find", ChatColor.GOLD + "25 Score: " + ChatColor.GRAY + "+" + ChatColor.AQUA + "2 Magic Find", ChatColor.GOLD + "50 Score: " + ChatColor.GRAY + "+" + ChatColor.AQUA + "3 Magic Find", ChatColor.GOLD + "75 Score: " + ChatColor.GRAY + "+" + ChatColor.AQUA + "4 Magic Find", ChatColor.GOLD + "100 Score: " + ChatColor.GRAY + "+" + ChatColor.AQUA + "5 Magic Find", ChatColor.GOLD + "130 Score: " + ChatColor.GRAY + "+" + ChatColor.AQUA + "6 Magic Find", ChatColor.GOLD + "175 Score: " + ChatColor.GRAY + "+" + ChatColor.AQUA + "7 Magic Find", " ", ChatColor.BLUE + "Your Pet Score: " + ChatColor.RED + "Coming soon!"));
        this.set(GUIClickableItem.createGUIOpenerItem(GUIType.SKYBLOCK_MENU, player, ChatColor.GREEN + "Go Back", 48, Material.ARROW, ChatColor.GRAY + "To SkySim Menu"));
        this.set(GUIClickableItem.getCloseItem(49));
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                new PetsGUI(PetsGUI.this.page, !PetsGUI.this.pickup).open(player);
            }

            @Override
            public int getSlot() {
                return 50;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Convert Pet to an Item", Material.INK_SACK, PetsGUI.this.pickup ? (short)10 : 8, 1, ChatColor.GRAY + "Enable this setting and", ChatColor.GRAY + "click any pet to convert it", ChatColor.GRAY + "to an item.", " ", PetsGUI.this.pickup ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled");
            }
        });
        this.set(new GUIClickableItem(){

            @Override
            public void run(InventoryClickEvent e) {
                Player p = (Player)e.getWhoClicked();
                if (p == null) {
                    return;
                }
                if (PetsGUI.getShowPet(player)) {
                    PetsGUI.setShowPets(player, false);
                    player.sendMessage(ChatColor.GREEN + "Hide Pets is now enabled!");
                    player.closeInventory();
                } else {
                    PetsGUI.this.showPetInRange(player);
                    PetsGUI.setShowPets(player, true);
                    player.sendMessage(ChatColor.RED + "Hide Pets is now disabled!");
                    player.closeInventory();
                }
            }

            @Override
            public int getSlot() {
                return 51;
            }

            @Override
            public ItemStack getItem() {
                ItemStack isBuilder = new ItemStack(Material.BEDROCK, 1);
                isBuilder = PetsGUI.getShowPet(player) ? SUtil.getStack(ChatColor.GREEN + "Hide Pets", Material.STONE_BUTTON, (short)0, 1, ChatColor.GRAY + "Hide all pets which are", ChatColor.GRAY + "little heads from being", ChatColor.GRAY + "visible in the world.", " ", ChatColor.GRAY + "Pet effects remain active.", " ", ChatColor.GRAY + "Currently: " + ChatColor.GREEN + "Pets shown!", ChatColor.GRAY + "Selected pet: " + name, " ", ChatColor.YELLOW + "Click to hide!") : SUtil.getStack(ChatColor.RED + "Hide Pets", Material.STONE_BUTTON, (short)0, 1, ChatColor.GRAY + "Hide all pets which are", ChatColor.GRAY + "little heads from being", ChatColor.GRAY + "visible in the world.", " ", ChatColor.GRAY + "Pet effects remain active.", " ", ChatColor.GRAY + "Currently: " + ChatColor.RED + "Pets hidden!", ChatColor.GRAY + "Selected pet: " + name, " ", ChatColor.YELLOW + "Click to show!");
                return isBuilder;
            }
        });
        List p = paged.getPage(this.page);
        if (p == null) {
            return;
        }
        for (int i = 0; i < p.size(); ++i) {
            final int slot = INTERIOR[i];
            final Pet.PetItem pet = (Pet.PetItem)p.get(i);
            final String n = pet.getRarity().getColor() + pet.getType().getDisplayName(pet.getType().getData());
            final SItem item = SItem.of(pet.getType());
            item.setRarity(pet.getRarity());
            item.setDataDouble("xp", pet.getXp());
            item.getData().setBoolean("equipped", true);
            item.update();
            if (!this.pickup) {
                ItemMeta meta = item.getStack().getItemMeta();
                List lore = meta.getLore();
                lore.add(" ");
                if (pet.isActive()) {
                    lore.add(ChatColor.RED + "Click to despawn");
                } else {
                    lore.add(ChatColor.YELLOW + "Click to summon");
                }
                meta.setLore(lore);
                item.getStack().setItemMeta(meta);
            }
            this.set(new GUIClickableItem(){

                @Override
                public void run(InventoryClickEvent e) {
                    if (PetsGUI.this.pickup) {
                        if (Sputnik.isFullInv(player)) {
                            player.sendMessage(ChatColor.RED + "Your inventory is full! Clean it up!");
                            player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                            return;
                        }
                        SItem n2 = SItem.of(pet.getType());
                        n2.setRarity(pet.getRarity());
                        n2.setDataDouble("xp", pet.getXp());
                        player.getInventory().addItem(new ItemStack[]{n2.getStack()});
                        pet.setActive(false);
                        if (user.getActivePet() == pet) {
                            PetsGUI.destroyArmorStandWithUUID(player.getUniqueId(), player.getWorld());
                        }
                        user.removePet(pet);
                        new PetsGUI(PetsGUI.this.page, false).open(player);
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
                        return;
                    }
                    if (pet.isActive()) {
                        pet.setActive(false);
                        player.closeInventory();
                        PetsGUI.destroyArmorStandWithUUID(player.getUniqueId(), player.getWorld());
                        player.sendMessage(ChatColor.GREEN + "You despawned your " + n + ChatColor.GREEN + "!");
                        return;
                    }
                    user.equipPet(pet);
                    PetsGUI.destroyArmorStandWithUUID(player.getUniqueId(), player.getWorld());
                    player.closeInventory();
                    player.sendMessage(ChatColor.GREEN + "You spawned your " + n + ChatColor.GREEN + "!");
                    PetsGUI.spawnFlyingHeads(player, user.getActivePetClass(), pet.toItem().getStack());
                }

                @Override
                public int getSlot() {
                    return slot;
                }

                @Override
                public ItemStack getItem() {
                    return item.getStack();
                }
            });
        }
    }

    public static void applyThingy(ArmorStand as, boolean a) {
        as.setCustomNameVisible(a);
        as.setMarker(true);
        as.setVisible(false);
        as.setGravity(false);
        as.setArms(true);
        as.setRightArmPose(new EulerAngle(0.0, 45.0, 0.0));
        as.setMetadata("pets", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        as.setRemoveWhenFarAway(false);
    }

    public static BukkitTask spawnFlyingHeads(final Player player, final Pet petclass, final ItemStack stacc) {
        PetsGUI.destroyArmorStandWithUUID(player.getUniqueId(), player.getWorld());
        Pet.PetItem active = User.getUser(player.getUniqueId()).getActivePet();
        int level = Pet.getLevel(active.getXp(), active.getRarity());
        final Location location = player.getLocation();
        final ArmorStand name = (ArmorStand)player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        PetsGUI.applyThingy(name, true);
        name.setSmall(true);
        name.setMetadata(player.getUniqueId().toString() + "_pets", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        final ArmorStand stand = (ArmorStand)player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        PetsGUI.applyThingy(stand, false);
        stand.setMetadata(player.getUniqueId().toString() + "_pets", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        String displayname = Sputnik.trans("&8[&7Lv" + level + "&8] " + active.toItem().getRarity().getColor() + player.getName() + "'s " + petclass.getDisplayName());
        stand.getEquipment().setItemInHand(stacc);
        name.setCustomName(displayname);
        stand.setMetadata(player.getUniqueId().toString(), (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        return new BukkitRunnable(){
            int count = 0;
            int stat = 0;

            public void run() {
                Pet.PetItem active1 = User.getUser(player.getUniqueId()).getActivePet();
                if (active1 == null || !player.isOnline() || !player.getWorld().equals(stand.getWorld())) {
                    name.remove();
                    stand.remove();
                    this.cancel();
                    return;
                }
                if (name.isDead()) {
                    stand.remove();
                    this.cancel();
                    return;
                }
                if (!player.getWorld().getEntities().contains(name)) {
                    name.remove();
                    stand.remove();
                    if (active1 != null) {
                        Sputnik.createPet(player);
                    }
                    this.cancel();
                    return;
                }
                if (player.getWorld().getName().contains("f6")) {
                    name.setCustomNameVisible(false);
                }
                Pet.PetItem active = User.getUser(player.getUniqueId()).getActivePet();
                int level1 = Pet.getLevel(active.getXp(), active.getRarity());
                name.setCustomName(Sputnik.trans("&8[&7Lv" + level1 + "&8] " + active.toItem().getRarity().getColor() + player.getName() + "'s " + petclass.getDisplayName()));
                stand.getEquipment().setItemInHand(stacc);
                Location target = player.getLocation();
                target.setPitch(0.0f);
                target.add(target.getDirection().multiply(-1));
                double distance = target.distance(location);
                double yoffset = Math.sin((float)this.count / 4.0f) / 1.7 + 1.0;
                if (distance < 5.0 && this.stat >= 1 || distance < 0.6) {
                    if (this.stat < 5) {
                        ++this.stat;
                        return;
                    }
                    ++this.count;
                    if (this.count > 24) {
                        this.count = 0;
                    }
                    location.setY((location.getY() + target.getY()) / 2.0);
                } else {
                    this.stat = 0;
                    Vector v = target.toVector().subtract(location.toVector()).normalize().multiply(Math.min(9.0, Math.min(15.0, distance / 2.0) / 4.0 + 0.2));
                    location.setDirection(v);
                    location.add(v);
                    if (this.count > 13) {
                        this.count = (int)((double)this.count / 1.1);
                    } else if (this.count < 11) {
                        this.count = (int)((double)this.count * 1.1);
                    }
                }
                Location nameLoc = location.clone();
                if (nameLoc.distanceSquared(target) > 25.0) {
                    name.setCustomNameVisible(false);
                } else if (!target.getWorld().getName().contains("f6")) {
                    name.setCustomNameVisible(true);
                }
                nameLoc.setPitch(0.0f);
                nameLoc.add(nameLoc.getDirection().multiply(0.15));
                nameLoc.setYaw(nameLoc.getYaw() + 90.0f);
                nameLoc.add(nameLoc.getDirection().multiply(0.527));
                name.teleport(nameLoc.add(0.0, yoffset + 0.85, 0.0));
                stand.teleport(location.clone().add(0.0, yoffset, 0.0));
                Pet pet_ = User.getUser(player.getUniqueId()).getActivePetClass();
                if (distance < 5.0 && this.stat >= 1 || distance < 0.6) {
                    PetsGUI.spawnParticle(pet_, nameLoc.clone().add(0.0, -0.2, 0.0));
                } else {
                    PetsGUI.spawnParticle(pet_, nameLoc.clone().add(0.0, -0.2, 0.0).add(location.clone().add(0.0, yoffset, 0.0).getDirection().clone().multiply(-0.6)));
                }
                PetsGUI.sendDestroyPacket((org.bukkit.entity.Entity)stand, (org.bukkit.entity.Entity)name, player.getWorld(), player);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 3L, 3L);
    }

    public static void destroyArmorStandWithUUID(UUID uuid, World w) {
        String uuidString = uuid.toString() + "_pets";
        for (org.bukkit.entity.Entity e : w.getEntities()) {
            if (!e.hasMetadata(uuidString)) continue;
            e.remove();
        }
    }

    public static void spawnParticle(Pet pet, Location l) {
        World w = l.getWorld();
        for (org.bukkit.entity.Entity e : w.getNearbyEntities(l, 30.0, 35.0, 30.0)) {
            Player p;
            if (!(e instanceof Player) || !PetsGUI.getShowPet(p = (Player)e)) continue;
            pet.particleBelowA(p, l);
        }
    }

    public static void sendDestroyPacket(org.bukkit.entity.Entity as1, org.bukkit.entity.Entity as2, World w, Player owner) {
        Entity el = ((CraftEntity)as1).getHandle();
        Entity el_ = ((CraftEntity)as2).getHandle();
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[]{el.getId()});
        PacketPlayOutEntityDestroy packet_ = new PacketPlayOutEntityDestroy(new int[]{el_.getId()});
        for (org.bukkit.entity.Entity e : w.getNearbyEntities(as1.getLocation(), 30.0, 35.0, 30.0)) {
            Player p;
            if (!(e instanceof Player) || PetsGUI.getShowPet(p = (Player)e)) continue;
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet_);
        }
    }

    public void showPetInRange(Player p) {
        for (org.bukkit.entity.Entity e : p.getNearbyEntities(30.0, 35.0, 30.0)) {
            if (!e.hasMetadata("pets")) continue;
            Entity el = ((CraftEntity)e).getHandle();
            net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy((ItemStack)((LivingEntity)e).getEquipment().getItemInHand());
            el.setEquipment(0, nmsItem);
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving)el);
            PacketPlayOutEntityEquipment packet2 = new PacketPlayOutEntityEquipment(el.getId(), 0, nmsItem);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet2);
        }
    }
}

