package vn.giakhanhvn.skysim.entity.nms;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEnderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.entity.*;
import vn.giakhanhvn.skysim.entity.end.EndermanStatistics;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.BlockFallAPI;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

import java.util.*;

public class VoidgloomSeraph extends EntityEnderman implements SNMSEntity, EntityFunction, SlayerBoss, EndermanStatistics {
    private static final TieredValue<Double> MAX_HEALTH_VALUES = new TieredValue(3.0E7D, 1.5E8D, 6.66E8D, 1.0E9D);
    private static final TieredValue<Double> DAMAGE_VALUES = new TieredValue(120000.0D, 1000000.0D, 1200000.0D, 1500000.0D);
    private static final TieredValue<Double> SPEED_VALUES = new TieredValue(0.2D, 0.2D, 0.2D, 0.2D);
    private static final TieredValue<Double> XP_DROPS = new TieredValue(750.0D, 1600.0D, 2900.4D, 10900.0D);
    public static final Map<Entity, Player> BEACON_THROW = new HashMap();
    public static final Map<Entity, Integer> HIT_SHIELD = new HashMap();
    public static final Map<Entity, Integer> HIT_SHIELD_MAX = new HashMap();
    public static final Map<UUID, Entity> OWNER_BOSS = new HashMap();
    public static final Map<Entity, Player> NUKEKUBI_TARGET = new HashMap();
    public static final Map<Entity, Integer> NUKEKUBI_DAMAGE = new HashMap();
    @Getter
    private final int tier;
    private final long end;
    private boolean CooldownSkill;
    private boolean CooldownSkill2;
    private boolean CooldownSkill3;
    private boolean CooldownSkill4;
    private boolean activeskull;
    private boolean activebea;
    private boolean activehs;
    private boolean CooldownSkill5;
    private boolean HeartRadi;
    private boolean isBroken;
    private SEntity hologram;
    private SEntity hologram_name;
    private final ArrayList<Entity> Ar1 = new ArrayList();
    public static final Map<UUID, List<Entity>> LivingSkulls = new HashMap();
    private final ArrayList<Entity> Ar2 = new ArrayList();
    private final UUID spawnerUUID;
    static UUID spawnerUUID2;
    public static final Map<Entity, Block> CACHED_BLOCK = new HashMap();
    public static final Map<Entity, Integer> CACHED_BLOCK_ID = new HashMap();
    public static final Map<Entity, Byte> CACHED_BLOCK_DATA = new HashMap();

    public VoidgloomSeraph(Integer tier, UUID spawnerUUID) {
        super(((CraftWorld) Bukkit.getPlayer(spawnerUUID).getWorld()).getHandle());
        this.setSize(0.6F, 2.9F);
        this.S = 1.0F;
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.tier = tier;
        this.end = System.currentTimeMillis() + 240000L;
        this.spawnerUUID = spawnerUUID;
        this.activehs = false;
        this.activebea = false;
        this.HeartRadi = false;
        this.isBroken = false;
        this.CooldownSkill = true;
        this.CooldownSkill2 = true;
        this.CooldownSkill3 = true;
        this.CooldownSkill4 = true;
        this.CooldownSkill5 = true;
        this.activeskull = false;
        spawnerUUID2 = spawnerUUID;
    }

    public VoidgloomSeraph(World world) {
        super(world);
        this.setSize(0.6F, 2.9F);
        this.S = 1.0F;
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.tier = 1;
        this.activebea = false;
        this.activehs = false;
        this.HeartRadi = false;
        this.isBroken = false;
        this.end = System.currentTimeMillis() + 240000L;
        this.spawnerUUID = UUID.randomUUID();
        this.CooldownSkill = true;
        this.CooldownSkill2 = true;
        this.CooldownSkill3 = true;
        this.CooldownSkill4 = true;
        this.CooldownSkill5 = true;
        this.activeskull = false;
        spawnerUUID2 = this.spawnerUUID;
    }

    public static Player getPlayer() {
        return Bukkit.getPlayer(spawnerUUID2);
    }

    public void t_() {
        super.t_();
        ((CraftEnderman) this.bukkitEntity).getHandle();
        final Player player = Bukkit.getPlayer(this.spawnerUUID);
        if (player != null) {
            OWNER_BOSS.put(player.getUniqueId(), this.bukkitEntity);
            if (this.bukkitEntity.getWorld() == player.getWorld() && this.getBukkitEntity().getLocation().distance(player.getLocation()) >= 50.0D && SUtil.random(0, 10) == 0 && !this.HeartRadi) {
                this.getBukkitEntity().teleport(player.getLocation());
                this.getBukkitEntity().getWorld().spigot().playEffect(this.getBukkitEntity().getLocation(), Effect.SMOKE, 0, 1, (float) SUtil.random(-1.0D, 1.0D), 2.0F, (float) SUtil.random(-1.0D, 1.0D), 0.0F, 1, 12);
            }

            if (!this.CooldownSkill5 && !this.HeartRadi && !this.activehs) {
                this.teleportSkill(this.bukkitEntity, player);
                this.CooldownSkill5 = true;
                SUtil.delay(() -> {
                    this.CooldownSkill5 = false;
                }, SUtil.random(170, 250));
            }

            Location locPitch = this.bukkitEntity.getLocation();
            locPitch.setPitch(0.0F);
            if (!this.HeartRadi) {
                this.bukkitEntity.teleport(locPitch);
            }

            if (this.bukkitEntity.getLocation().getBlock().getType() != Material.AIR && this.bukkitEntity.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR) {
                this.bukkitEntity.teleport(player);
            }

            if (System.currentTimeMillis() > this.end) {
                Entity stand = Repeater.BEACON_THROW2.get(player.getUniqueId());
                BlockFallAPI.removeBlock(Repeater.BEACON.get(stand), player.getWorld());
                Sputnik.RemoveEntityArray(this.Ar1);
                Sputnik.RemoveEntityArray(this.Ar2);
                if (LivingSkulls.containsKey(this.spawnerUUID)) {
                    List<Entity> a = LivingSkulls.get(this.spawnerUUID);
                    Sputnik.RemoveEntityArray(a);
                }

                if (stand != null) {
                    stand.remove();
                }

                User.getUser(player.getUniqueId()).failSlayerQuest();
                ((Enderman) this.bukkitEntity).remove();
                this.hologram.remove();
            } else {
                final Entity entity = this.bukkitEntity;
                if (this.tier > 1 && this.activebea && !this.isBroken) {
                    Entity stand = Repeater.BEACON_THROW2.get(player.getUniqueId());
                    if (stand != null) {
                        if (stand.isDead()) {
                            BlockFallAPI.removeBlock(Repeater.BEACON.get(stand), entity.getWorld());
                            if (CACHED_BLOCK.containsKey(stand) && CACHED_BLOCK_ID.containsKey(stand) && CACHED_BLOCK_DATA.containsKey(stand)) {
                                CACHED_BLOCK.get(stand).getLocation().getBlock().setTypeIdAndData(CACHED_BLOCK_ID.get(stand), CACHED_BLOCK_DATA.get(stand), true);
                            }
                        }

                        if (stand.getNearbyEntities(0.5D, 0.5D, 0.5D).contains(player)) {
                            this.isBroken = true;
                            SUtil.delay(() -> {
                                BlockFallAPI.removeBlock(Repeater.BEACON.get(stand), entity.getWorld());
                                stand.remove();
                                entity.getWorld().playSound(stand.getLocation(), Sound.ITEM_BREAK, 1.0F, 0.5F);
                                entity.getWorld().playEffect(stand.getLocation(), Effect.EXPLOSION_LARGE, 1);
                                if (CACHED_BLOCK.containsKey(stand) && CACHED_BLOCK_ID.containsKey(stand) && CACHED_BLOCK_DATA.containsKey(stand)) {
                                    CACHED_BLOCK.get(stand).getLocation().getBlock().setTypeIdAndData(CACHED_BLOCK_ID.get(stand), CACHED_BLOCK_DATA.get(stand), true);
                                }

                            }, 4L);
                            return;
                        }
                    }
                }

                if (this.tier > 1 && !this.CooldownSkill && SUtil.random(0, 100) == 4 && !this.activebea && !this.activehs) {
                    Sputnik.endermanCarryBlock((Enderman) this.bukkitEntity, Material.BEACON);
                    this.activebea = true;
                    BEACON_THROW.remove(entity);

                    Repeater.BEACON_THROW2.remove(player.getUniqueId());

                    (new BukkitRunnable() {
                        public void run() {
                            if (entity.isDead()) {
                                Entity stand = Repeater.BEACON_THROW2.get(player.getUniqueId());
                                BlockFallAPI.removeBlock(Repeater.BEACON.get(stand), entity.getWorld());
                                if (stand != null) {
                                    stand.remove();
                                }

                                this.cancel();
                            } else {
                                Location l1 = entity.getLocation();
                                l1.add(entity.getLocation().getDirection().normalize().multiply(1));
                                l1.setYaw((float) SUtil.random(0, 360));
                                ArmorStand armorStand1 = (ArmorStand) entity.getWorld().spawnEntity(l1.add(0.0D, 0.3D, 0.0D), EntityType.ARMOR_STAND);
                                armorStand1.getEquipment().setHelmet(SItem.of(SMaterial.BEACON).getStack());
                                armorStand1.setGravity(true);
                                armorStand1.setVisible(false);
                                armorStand1.setCustomName(ChatColor.translateAlternateColorCodes('&', ""));
                                Vector vec = armorStand1.getLocation().getDirection().normalize().multiply(1.25D);
                                armorStand1.setMetadata("BeaconSkill", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
                                vec.setY(0.55D);
                                armorStand1.setVelocity(vec);
                                Sputnik.endermanCarryBlock((Enderman) entity, Material.AIR);
                                SUtil.delay(() -> {
                                    player.playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 0.4F, 0.81F);
                                }, 10L);
                                SUtil.delay(() -> {
                                    player.playSound(player.getLocation(), "mob.guardian.elder.idle", 0.2F, 0.85F);
                                }, 15L);
                                SUtil.delay(() -> {
                                    player.playSound(player.getLocation(), "mob.guardian.elder.idle", 0.2F, 0.85F);
                                }, 40L);
                                SUtil.delay(() -> {
                                    player.playSound(player.getLocation(), "mob.guardian.elder.idle", 0.2F, 0.85F);
                                }, 40L);
                                VoidgloomSeraph.this.Ar1.add(armorStand1);
                                Repeater.BEACON_OWNER.remove(armorStand1);
                                Repeater.BEACON_OWNER.put(armorStand1, player);
                                VoidgloomSeraph.a(armorStand1);
                            }
                        }
                    }).runTaskLater(SkySimEngine.getPlugin(), 80L);
                    (new BukkitRunnable() {
                        public void run() {
                            ArmorStand stand;
                            if (entity.isDead()) {
                                stand = (ArmorStand) Repeater.BEACON_THROW2.get(player.getUniqueId());
                                BlockFallAPI.removeBlock(Repeater.BEACON.get(stand), entity.getWorld());
                                if (VoidgloomSeraph.CACHED_BLOCK.containsKey(stand) && VoidgloomSeraph.CACHED_BLOCK_ID.containsKey(stand) && VoidgloomSeraph.CACHED_BLOCK_DATA.containsKey(stand)) {
                                    VoidgloomSeraph.CACHED_BLOCK.get(stand).getLocation().getBlock().setTypeIdAndData(VoidgloomSeraph.CACHED_BLOCK_ID.get(stand), VoidgloomSeraph.CACHED_BLOCK_DATA.get(stand), true);
                                }

                                if (stand != null) {
                                    stand.remove();
                                }

                                this.cancel();
                            } else {
                                stand = (ArmorStand) Repeater.BEACON_THROW2.get(player.getUniqueId());
                                VoidgloomSeraph.this.Ar1.add(stand);
                                if (!VoidgloomSeraph.this.isBroken) {
                                    User.getUser(player.getUniqueId()).damage(player.getMaxHealth() * 3.0D, DamageCause.ENTITY_ATTACK, entity);
                                    player.damage(0.1D, entity);
                                    BlockFallAPI.removeBlock(Repeater.BEACON.get(stand), entity.getWorld());
                                    if (stand != null) {
                                        stand.remove();
                                    }

                                    VoidgloomSeraph.this.activebea = false;
                                    entity.getWorld().playSound(stand.getLocation(), Sound.ITEM_BREAK, 1.0F, 0.5F);
                                    entity.getWorld().playEffect(stand.getLocation(), Effect.EXPLOSION_LARGE, 1);
                                    if (VoidgloomSeraph.CACHED_BLOCK.containsKey(stand) && VoidgloomSeraph.CACHED_BLOCK_ID.containsKey(stand) && VoidgloomSeraph.CACHED_BLOCK_DATA.containsKey(stand)) {
                                        VoidgloomSeraph.CACHED_BLOCK.get(stand).getLocation().getBlock().setTypeIdAndData(VoidgloomSeraph.CACHED_BLOCK_ID.get(stand), VoidgloomSeraph.CACHED_BLOCK_DATA.get(stand), true);
                                    }
                                } else {
                                    VoidgloomSeraph.this.isBroken = false;
                                    BlockFallAPI.removeBlock(Repeater.BEACON.get(stand), entity.getWorld());
                                    if (VoidgloomSeraph.CACHED_BLOCK.containsKey(stand) && VoidgloomSeraph.CACHED_BLOCK_ID.containsKey(stand) && VoidgloomSeraph.CACHED_BLOCK_DATA.containsKey(stand)) {
                                        VoidgloomSeraph.CACHED_BLOCK.get(stand).getLocation().getBlock().setTypeIdAndData(VoidgloomSeraph.CACHED_BLOCK_ID.get(stand), VoidgloomSeraph.CACHED_BLOCK_DATA.get(stand), true);
                                    }

                                    stand.remove();
                                    VoidgloomSeraph.this.activebea = false;
                                }

                                VoidgloomSeraph.this.activebea = false;
                            }
                        }
                    }).runTaskLater(SkySimEngine.getPlugin(), 185L);
                    (new BukkitRunnable() {
                        public void run() {
                            if (VoidgloomSeraph.this.CooldownSkill) {
                                VoidgloomSeraph.this.CooldownSkill = false;
                            }

                        }
                    }).runTaskLater(SkySimEngine.getPlugin(), 400L);
                }

                int amounths = 30;
                if (this.tier == 2) {
                    amounths = 60;
                }

                if (this.tier == 3) {
                    amounths = 120;
                }

                if (this.tier >= 4) {
                    amounths = 200;
                }

                if (this.tier >= 1 && !this.CooldownSkill2 && SUtil.random(0, 100) == 2 && !this.activehs && !this.HeartRadi && !this.activebea) {
                    this.CooldownSkill2 = true;
                    this.activehs = true;
                    HIT_SHIELD.put(entity, amounths);
                    HIT_SHIELD_MAX.put(entity, amounths);
                    EntityManager.DEFENSE_PERCENTAGE.put(entity, 100);
                }

                if (!HIT_SHIELD.containsKey(entity) && this.activehs) {
                    HIT_SHIELD.put(entity, amounths);
                    HIT_SHIELD_MAX.put(entity, amounths);
                }

                if (this.activehs && HIT_SHIELD.get(entity) <= 0 && !this.HeartRadi && !this.activebea) {
                    this.CooldownSkill2 = true;
                    HIT_SHIELD.remove(entity);
                    HIT_SHIELD_MAX.remove(entity);
                    player.playSound(player.getLocation(), Sound.ZOMBIE_REMEDY, 1.0F, 1.0F);
                    EntityManager.DEFENSE_PERCENTAGE.put(entity, 75);
                    this.activehs = false;
                    (new BukkitRunnable() {
                        public void run() {
                            if (VoidgloomSeraph.this.CooldownSkill2) {
                                VoidgloomSeraph.this.CooldownSkill2 = false;
                            }

                        }
                    }).runTaskLater(SkySimEngine.getPlugin(), 850L);
                }

                if (this.bukkitEntity.getWorld() == player.getWorld() && this.getBukkitEntity().getLocation().distance(player.getLocation()) > 20.0D && this.HeartRadi) {
                    User.getUser(player.getUniqueId()).damage(player.getMaxHealth() * 90.0D / 100.0D, DamageCause.ENTITY_ATTACK, entity);
                    player.damage(1.0E-4D, entity);
                }

                net.minecraft.server.v1_8_R3.Entity entity_ = this.getBukkitEntity().getHandle();
                double height = entity_.getBoundingBox().e - entity_.getBoundingBox().b;
                this.hologram_name.getEntity().teleport(this.getBukkitEntity().getLocation().clone().add(0.0D, height, 0.0D));
                this.hologram_name.getEntity().setCustomName(Sputnik.trans(Sputnik.entityNameTag((LivingEntity) this.getBukkitEntity(), Sputnik.buildcustomString(this.getEntityName(), 0, true))));
                this.hologram.getEntity().teleport(this.getBukkitEntity().getLocation().clone().add(0.0D, 3.2D, 0.0D));
                this.hologram.getEntity().setCustomName(ChatColor.RED + SUtil.getFormattedTime(this.end - System.currentTimeMillis(), 1000));
                ((Enderman) this.getBukkitEntity()).setTarget(player);
                if (this.tier >= 4 && !this.CooldownSkill3 && SUtil.random(0, 100) <= 10 && !this.HeartRadi && !this.activehs) {
                    EntityManager.DEFENSE_PERCENTAGE.put(entity, 100);
                    this.HeartRadi = true;
                    this.CooldownSkill3 = true;
                    double x = entity.getLocation().getX();
                    double y = entity.getLocation().getY();
                    double z = entity.getLocation().getZ();
                    Location l = new Location(entity.getWorld(), x, y, z);
                    entity.getWorld().playEffect(entity.getLocation(), Effect.EXPLOSION_HUGE, 3);
                    entity.getWorld().playEffect(entity.getLocation(), Effect.EXPLOSION_HUGE, 3);
                    final ArmorStand stand_sit = entity.getWorld().spawn(l, ArmorStand.class);
                    stand_sit.setVisible(false);
                    stand_sit.setGravity(true);
                    stand_sit.setMarker(true);
                    stand_sit.setPassenger(entity);
                    this.Ar1.add(stand_sit);
                    this.Ar2.add(stand_sit);
                    (new BukkitRunnable() {
                        public void run() {
                            if (stand_sit.isDead()) {
                                this.cancel();
                            } else {
                                stand_sit.setPassenger(entity);
                            }
                        }
                    }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
                    final ArmorStand stand = stand_sit.getWorld().spawn(stand_sit.getLocation().add(0.0D, 0.0D, 0.0D), ArmorStand.class);
                    stand.setVisible(false);
                    stand.setGravity(false);
                    stand.setMarker(true);
                    stand.setMetadata("HeartRadiAS", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
                    this.Ar1.add(stand);
                    this.Ar2.add(stand);
                    final ArmorStand stand1 = entity.getWorld().spawn(stand.getLocation().add(0.0D, 1.5D, 0.0D), ArmorStand.class);
                    stand1.setVisible(false);
                    stand1.setGravity(false);
                    stand1.setMarker(true);
                    this.Ar1.add(stand1);
                    this.Ar2.add(stand1);
                    stand1.setMetadata("HeartRadiAS", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
                    final ArmorStand stand2 = entity.getWorld().spawn(stand1.getLocation().add(0.0D, 1.5D, 0.0D), ArmorStand.class);
                    stand2.setVisible(false);
                    stand2.setGravity(false);
                    stand2.setMarker(true);
                    this.Ar1.add(stand2);
                    this.Ar2.add(stand2);
                    stand2.setMetadata("HeartRadiAS", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
                    l.setYaw(l.getYaw() + 90.0F);
                    final ArmorStand stand1_ = stand_sit.getWorld().spawn(l, ArmorStand.class);
                    stand1_.setVisible(false);
                    stand1_.setGravity(false);
                    stand1_.setMarker(true);
                    this.Ar1.add(stand1_);
                    this.Ar2.add(stand1_);
                    stand1.setMetadata("HeartRadiAS", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
                    final ArmorStand stand11 = entity.getWorld().spawn(stand1_.getLocation().add(0.0D, 1.5D, 0.0D), ArmorStand.class);
                    stand11.setVisible(false);
                    stand11.setGravity(false);
                    stand11.setMarker(true);
                    this.Ar1.add(stand11);
                    this.Ar2.add(stand11);
                    stand11.setMetadata("HeartRadiAS", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
                    final ArmorStand stand22 = entity.getWorld().spawn(stand11.getLocation().add(0.0D, 1.5D, 0.0D), ArmorStand.class);
                    stand22.setVisible(false);
                    stand22.setGravity(false);
                    stand22.setMarker(true);
                    this.Ar1.add(stand22);
                    this.Ar2.add(stand22);
                    (new BukkitRunnable() {
                        public void run() {
                            player.playSound(player.getLocation(), Sound.FIREWORK_LARGE_BLAST, 1.0F, 0.5F);
                            Sputnik.playFuckingSoundOfVoidgloomThatTookForeverToMake(player, stand_sit);
                        }
                    }).runTaskLater(SkySimEngine.getPlugin(), 15L);
                    (new BukkitRunnable() {
                        public void run() {
                            if (entity.isDead()) {
                                Sputnik.RemoveEntityArray(VoidgloomSeraph.this.Ar2);
                                this.cancel();
                            } else {
                                Sputnik.entityBeam(stand, stand.getLocation(), player, entity);
                                Sputnik.entityBeam(stand22, stand.getLocation(), player, entity);
                                Sputnik.entityBeam(stand11, stand.getLocation(), player, entity);
                                Sputnik.entityBeam(stand1_, stand.getLocation(), player, entity);
                                Sputnik.entityBeam(stand2, stand.getLocation(), player, entity);
                                Sputnik.entityBeam(stand1, stand.getLocation(), player, entity);
                                (new BukkitRunnable() {
                                    public void run() {
                                        if (stand22.isDead()) {
                                            this.cancel();
                                        } else if (entity.isDead()) {
                                            Sputnik.IsInsideTheBeam.remove(player.getUniqueId());
                                            this.cancel();
                                        } else {
                                            if (Sputnik.IsInsideTheBeam.containsKey(player.getUniqueId()) && Sputnik.IsInsideTheBeam.get(player.getUniqueId())) {
                                                Entity entityx = VoidgloomSeraph.OWNER_BOSS.get(player.getUniqueId());
                                                if (entityx == null) {
                                                    this.cancel();
                                                    return;
                                                }

                                                User.getUser(player.getUniqueId()).damage(player.getMaxHealth() * 25.0D / 100.0D, DamageCause.ENTITY_ATTACK, entityx);
                                                Sputnik.IsInsideTheBeam.remove(player.getUniqueId());
                                            }

                                        }
                                    }
                                }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 3L);
                            }
                        }
                    }).runTaskLater(SkySimEngine.getPlugin(), 25L);
                    (new BukkitRunnable() {
                        public void run() {
                            VoidgloomSeraph.this.HeartRadi = false;
                            Sputnik.RemoveEntityArray(VoidgloomSeraph.this.Ar2);
                            EntityManager.DEFENSE_PERCENTAGE.put(entity, 75);
                            (new BukkitRunnable() {
                                public void run() {
                                    if (VoidgloomSeraph.this.CooldownSkill3) {
                                        VoidgloomSeraph.this.CooldownSkill3 = false;
                                    }

                                }
                            }).runTaskLater(SkySimEngine.getPlugin(), 700L);
                        }
                    }).runTaskLater(SkySimEngine.getPlugin(), 200L);
                }

                if (this.activeskull) {
                    int damagefin = 800;
                    if (this.tier >= 4) {
                        damagefin = 2000;
                    }

                    if (LivingSkulls.containsKey(player.getUniqueId()) && this.activeskull) {
                        damageUpdate(damagefin * 2 * (LivingSkulls.get(player.getUniqueId()).size() + 1));
                    }
                }

                if (LivingSkulls.containsKey(player.getUniqueId()) && this.activeskull) {
                    this.activeskull = LivingSkulls.get(player.getUniqueId()).size() > 0;
                }

                if (this.tier >= 3 && !this.CooldownSkill4 && SUtil.random(0, 100) == 2 && !this.activehs && !this.activeskull) {
                    LivingSkulls.remove(player.getUniqueId());

                    this.CooldownSkill4 = true;
                    this.activeskull = true;
                    spawnNukekubi(entity, player, 1, 3);
                    (new BukkitRunnable() {
                        public void run() {
                            if (entity.isDead()) {
                                if (VoidgloomSeraph.LivingSkulls.containsKey(player.getUniqueId())) {
                                    Sputnik.RemoveEntityArray(VoidgloomSeraph.LivingSkulls.get(player.getUniqueId()));
                                }

                                this.cancel();
                            } else {
                                if (VoidgloomSeraph.this.activeskull) {
                                    VoidgloomSeraph.spawnNukekubi(entity, player, 1, 2);
                                } else {
                                    VoidgloomSeraph.updateSkill(VoidgloomSeraph.LivingSkulls.get(player.getUniqueId()));
                                    (new BukkitRunnable() {
                                        public void run() {
                                            if (VoidgloomSeraph.this.CooldownSkill4) {
                                                VoidgloomSeraph.this.CooldownSkill4 = false;
                                            }

                                        }
                                    }).runTaskLater(SkySimEngine.getPlugin(), 400L);
                                }

                            }
                        }
                    }).runTaskLater(SkySimEngine.getPlugin(), 300L);
                }

            }
        }
    }

    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        entity.setMetadata("NoAffect", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        entity.setMetadata("Voidgloom", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        entity.setMetadata("BOSS_OWNER_" + Bukkit.getPlayer(this.getSpawnerUUID()).getUniqueId().toString(), new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        entity.setMetadata("GiantSword", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        entity.setMetadata("SlayerBoss", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        LivingSkulls.put(this.spawnerUUID, new ArrayList());
        SUtil.delay(() -> {
            entity.removeMetadata("GiantSword", SkySimEngine.getPlugin());
        }, 20L);
        SUtil.delay(() -> {
            int amounths = 30;
            if (this.tier == 2) {
                amounths = 60;
            }

            if (this.tier == 3) {
                amounths = 120;
            }

            if (this.tier >= 4) {
                amounths = 200;
            }

            this.CooldownSkill2 = true;
            this.activehs = true;
            HIT_SHIELD.put(entity, amounths);
            HIT_SHIELD_MAX.put(entity, amounths);
            EntityManager.DEFENSE_PERCENTAGE.put(entity, 100);
        }, 0L);
        SUtil.delay(() -> {
            this.CooldownSkill5 = false;
        }, 70L);
        this.playBossParticle_1(entity);
        this.playBossParticle_2(entity);
        this.playShieldParticle(entity);
        SUtil.delay(() -> {
            this.CooldownSkill = false;
        }, 150L);
        this.CooldownSkill2 = false;
        SUtil.delay(() -> {
            this.CooldownSkill3 = false;
        }, 100L);
        SUtil.delay(() -> {
            this.CooldownSkill4 = false;
        }, 300L);
        net.minecraft.server.v1_8_R3.Entity entity_ = this.getBukkitEntity().getHandle();
        this.hologram = new SEntity(entity.getLocation().add(0.0D, 3.2D, 0.0D), SEntityType.UNCOLLIDABLE_ARMOR_STAND);
        BEACON_THROW.put(entity, Bukkit.getPlayer(this.spawnerUUID));
        ((ArmorStand) this.hologram.getEntity()).setVisible(false);
        ((ArmorStand) this.hologram.getEntity()).setGravity(false);
        this.hologram.getEntity().setCustomNameVisible(true);
        entity.setMetadata("notDisplay", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        net.minecraft.server.v1_8_R3.Entity e = this.getBukkitEntity().getHandle();
        double height_ = e.getBoundingBox().e - e.getBoundingBox().b;
        this.hologram_name = new SEntity(entity.getLocation().add(0.0D, height_, 0.0D), SEntityType.UNCOLLIDABLE_ARMOR_STAND);
        ((ArmorStand) this.hologram_name.getEntity()).setVisible(false);
        ((ArmorStand) this.hologram_name.getEntity()).setGravity(false);
        this.hologram_name.getEntity().setCustomNameVisible(true);
        (new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    VoidgloomSeraph.this.hologram.remove();
                    this.cancel();
                    SUtil.delay(() -> {
                        VoidgloomSeraph.this.hologram_name.remove();
                    }, 20L);
                    Sputnik.RemoveEntityArray(VoidgloomSeraph.LivingSkulls.get(VoidgloomSeraph.this.getSpawnerUUID()));
                    Repeater.BEACON_THROW2.remove(VoidgloomSeraph.this.getSpawnerUUID());
                    VoidgloomSeraph.OWNER_BOSS.remove(VoidgloomSeraph.this.getSpawnerUUID());
                    if (VoidgloomSeraph.getPlayer() != null) {
                        VoidgloomSeraph.destroyArmorStandWithUUID(VoidgloomSeraph.this.getSpawnerUUID(), VoidgloomSeraph.getPlayer().getWorld());
                    }

                    VoidgloomSeraph.BEACON_THROW.remove(entity);
                    VoidgloomSeraph.HIT_SHIELD.remove(entity);
                    VoidgloomSeraph.HIT_SHIELD_MAX.remove(entity);
                    Repeater.BEACON_OWNER.remove(entity);
                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
        (new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    Sputnik.RemoveEntityArray(VoidgloomSeraph.this.Ar2);
                    if (VoidgloomSeraph.LivingSkulls.containsKey(VoidgloomSeraph.this.getSpawnerUUID())) {
                        List<Entity> a = VoidgloomSeraph.LivingSkulls.get(VoidgloomSeraph.this.getSpawnerUUID());
                        Sputnik.RemoveEntityArray(a);
                    }

                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
        (new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                } else if (VoidgloomSeraph.getPlayer() == null) {
                    this.cancel();
                    entity.remove();
                } else {
                    if (!VoidgloomSeraph.this.HeartRadi) {
                        VoidgloomSeraph.getPlayer().damage(VoidgloomSeraph.DAMAGE_VALUES.getByNumber(VoidgloomSeraph.this.tier), entity);
                    }

                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 20L);
    }

    public void onDamage(SEntity sEntity, Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        Entity en = sEntity.getEntity();
        Vector v = new Vector(0, 0, 0);
        SUtil.delay(() -> {
            en.setVelocity(v);
        }, 1L);
    }

    public void onDeath(SEntity sEntity, Entity killed, Entity damager) {
        this.hologram.remove();
        SUtil.delay(() -> {
            this.hologram_name.remove();
        }, 20L);
        Entity stand = Repeater.BEACON_THROW2.get(damager.getUniqueId());
        BlockFallAPI.removeBlock(Repeater.BEACON.get(stand), killed.getWorld());
        Sputnik.RemoveEntityArray(this.Ar1);
        Sputnik.RemoveEntityArray(this.Ar2);
        if (LivingSkulls.containsKey(this.spawnerUUID)) {
            List<Entity> a = LivingSkulls.get(this.spawnerUUID);
            Sputnik.RemoveEntityArray(a);
        }

        if (stand != null) {
            stand.remove();
        }

    }

    public String getEntityName() {
        return ChatColor.RED + "☠ " + ChatColor.AQUA + "Voidgloom Seraph";
    }

    public double getEntityMaxHealth() {
        return MAX_HEALTH_VALUES.getByNumber(this.tier);
    }

    public double getDamageDealt() {
        return DAMAGE_VALUES.getByNumber(this.tier);
    }

    public double getMovementSpeed() {
        return SPEED_VALUES.getByNumber(this.tier);
    }

    public LivingEntity spawn(Location location) {
        this.world = ((CraftWorld) location.getWorld()).getHandle();
        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.world.addEntity(this, SpawnReason.CUSTOM);
        return (LivingEntity) this.getBukkitEntity();
    }

    public List<EntityDrop> drops() {
        List<EntityDrop> drops = new ArrayList();
        int revFlesh = SUtil.random(1, 3);
        if (this.tier == 2) {
            revFlesh = SUtil.random(9, 18);
        }

        if (this.tier == 3) {
            revFlesh = SUtil.random(30, 50);
        }

        if (this.tier == 4) {
            revFlesh = SUtil.random(50, 64);
        }

        drops.add(new EntityDrop(SUtil.setStackAmount(SItem.of(SMaterial.NULL_SPHERE).getStack(), revFlesh), EntityDropType.GUARANTEED, 1.0D));
        if (this.tier >= 2) {
            drops.add(new EntityDrop(SMaterial.SUMMONING_EYE, EntityDropType.EXTRAORDINARILY_RARE, 0.0033333333333333335D, Bukkit.getPlayer(this.getSpawnerUUID())));
        }

        if (this.tier >= 3) {
            drops.add(new EntityDrop(SMaterial.HIDDEN_ETHERWARP_MERGER, EntityDropType.EXTRAORDINARILY_RARE, 5.0E-4D, Bukkit.getPlayer(this.getSpawnerUUID())));
        }

        if (this.tier >= 4) {
            SItem endBook = SItem.of(SMaterial.ENCHANTED_BOOK);
            endBook.addEnchantment(EnchantmentType.ENDER_SLAYER, 15);
            drops.add(new EntityDrop(endBook.getStack(), EntityDropType.CRAZY_RARE, 0.002D, Bukkit.getPlayer(this.getSpawnerUUID())));
            SItem legiBook = SItem.of(SMaterial.ENCHANTED_BOOK);
            legiBook.addEnchantment(EnchantmentType.LEGION, SUtil.random(1, 2));
            drops.add(new EntityDrop(legiBook.getStack(), EntityDropType.INSANE_RARE, 0.00125D, Bukkit.getPlayer(this.getSpawnerUUID())));
            SItem fatalBook = SItem.of(SMaterial.ENCHANTED_BOOK);
            fatalBook.addEnchantment(EnchantmentType.FATAL_TEMPO, 1);
            drops.add(new EntityDrop(fatalBook.getStack(), EntityDropType.CRAZY_RARE, 4.0E-4D, Bukkit.getPlayer(this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.HIDDEN_DEMONS_PEARL, EntityDropType.INSANE_RARE, 2.2222222222222223E-4D, Bukkit.getPlayer(this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.HIDDEN_ETHERWARP_CONDUIT, EntityDropType.CRAZY_RARE, 0.002D, Bukkit.getPlayer(this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.JUDGEMENT_CORE, EntityDropType.CRAZY_RARE, 0.001D, Bukkit.getPlayer(this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.HIDDEN_GYRO_EYE, EntityDropType.CRAZY_RARE, 0.001D, Bukkit.getPlayer(this.getSpawnerUUID())));
            drops.add(new EntityDrop(SMaterial.HIDDEN_REFINED_POWDER, EntityDropType.EXTRAORDINARILY_RARE, 0.001D, Bukkit.getPlayer(this.getSpawnerUUID())));
        }

        return drops;
    }

    public double getXPDropped() {
        return XP_DROPS.getByNumber(this.tier);
    }

    public boolean isBaby() {
        return false;
    }

    public boolean isAngry() {
        return true;
    }

    public UUID getSpawnerUUID() {
        return this.spawnerUUID;
    }

    public static void spawnNukekubi(Entity e, Player p, Integer damage, Integer spawnCouples) {
        Location loc1_;
        Location loc2_;
        Location loc1;
        if (spawnCouples <= 2) {
            loc1_ = p.getLocation();
            loc1_.setYaw(loc1_.getYaw() + (float) SUtil.random(0, 360));
            loc1_ = p.getLocation();
            loc1_.setYaw(loc1_.getYaw() + (float) SUtil.random(0, 360));
            loc2_ = loc1_.add(loc1_.getDirection().multiply(5));
            loc1 = loc1_.add(loc1_.getDirection().multiply(-5));
            moveHeadAround(spawnHeads(e, loc2_, p), p, damage);
            moveHeadAround(spawnHeads(e, loc1, p), p, damage);
        } else {
            for (int i = 0; i < spawnCouples; ++i) {
                if (SUtil.random(1, 2) == 1) {
                    loc1_ = p.getLocation();
                    loc1_.setYaw(loc1_.getYaw() + (float) SUtil.random(0, 360));
                    loc2_ = p.getLocation();
                    loc2_.setYaw(loc1_.getYaw() + (float) SUtil.random(0, 360));
                    loc1 = loc1_.add(loc1_.getDirection().multiply(5));
                    loc2_.add(loc2_.getDirection().multiply(-5));
                    moveHeadAround(spawnHeads(e, loc1, p), p, damage);
                } else {
                    loc1_ = p.getLocation();
                    loc1_.setYaw(loc1_.getYaw() + (float) SUtil.random(0, 360));
                    loc2_ = p.getLocation();
                    loc2_.setYaw(loc1_.getYaw() + (float) SUtil.random(0, 360));
                    loc1_.add(loc1_.getDirection().multiply(5));
                    Location loc2 = loc2_.add(loc2_.getDirection().multiply(-5));
                    moveHeadAround(spawnHeads(e, loc2, p), p, damage);
                }
            }
        }

    }

    public static void destroyArmorStandWithUUID(UUID uuid, org.bukkit.World w) {
        String uuidString = uuid.toString() + "_NUKEKUBI";
        Iterator var3 = w.getEntities().iterator();

        while (var3.hasNext()) {
            Entity e = (Entity) var3.next();
            if (e.hasMetadata(uuidString)) {
                e.remove();
            }
        }

    }

    public static LivingEntity spawnHeads(Entity e, Location loc, final Player p) {
        final ArmorStand entity = (ArmorStand) loc.getWorld().spawnEntity(e.getLocation().add(e.getLocation().getDirection().normalize().multiply(-1)), EntityType.ARMOR_STAND);
        loc.setY(loc.getY() + SUtil.random(0.0D, 0.6D));
        entity.setCustomName(Sputnik.trans("&c☠ &bVoidgloom Seraph"));
        entity.setVisible(false);
        entity.setGravity(false);
        entity.getEquipment().setHelmet(SItem.of(SMaterial.NUKEKUBI).getStack());
        entity.getEquipment().setItemInHand(new ItemStack(Material.AIR));
        entity.setMetadata("Nukekubi", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        entity.setMetadata(p.getUniqueId().toString() + "_NUKEKUBI", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        NUKEKUBI_TARGET.put(entity, p);
        if (!LivingSkulls.containsKey(p.getUniqueId())) {
            LivingSkulls.put(p.getUniqueId(), new ArrayList());
        }

        LivingSkulls.get(p.getUniqueId()).add(entity);
        moveToLoc(entity, loc, 3, 0, 1.0D);
        (new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                } else {
                    if (entity.hasMetadata("Nukekubi")) {
                        Location l = entity.getLocation().setDirection(p.getLocation().toVector().subtract(entity.getLocation().toVector()));
                        entity.teleport(l);
                        double x = 0.0D;
                        double y = 0.0D;
                        double z = 0.0D;
                        x = Math.toRadians(l.getPitch());
                        entity.setHeadPose(new EulerAngle(x, y, z));
                        entity.getWorld().playEffect(entity.getLocation().add(0.0D, 1.1D, 0.0D), Effect.WITCH_MAGIC, 1);
                        entity.getWorld().playEffect(entity.getLocation().add(0.0D, 1.2D, 0.0D), Effect.WITCH_MAGIC, 1);
                        entity.getWorld().playEffect(entity.getLocation().add(0.0D, 1.2D, 0.0D), Effect.WITCH_MAGIC, 1);
                        entity.getWorld().playEffect(entity.getLocation().add(0.0D, 1.4D, 0.0D), Effect.WITCH_MAGIC, 1);
                        entity.getWorld().playEffect(entity.getLocation().add(0.0D, 1.4D, 0.0D), Effect.WITCH_MAGIC, 1);
                    }

                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
        return entity;
    }

    public static void moveToLoc(final Entity e, final Location target, int tickingRad, int firstTickRad, final double jump) {
        final Location l = e.getLocation().setDirection(target.toVector().subtract(e.getLocation().toVector()));
        (new BukkitRunnable() {
            public void run() {
                Vector teleportTo = l.getDirection().normalize().multiply(jump);
                if (e.isDead()) {
                    this.cancel();
                } else if (e.getWorld().getNearbyEntities(target, 1.5D, 1.5D, 1.5D).contains(e)) {
                    this.cancel();
                } else {
                    e.teleport(e.getLocation().add(teleportTo).multiply(jump));
                    e.getWorld().spigot().playEffect(e.getLocation().add(0.0D, 1.1D, 0.0D), Effect.FLAME, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                    e.getWorld().spigot().playEffect(e.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLAME, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                    e.getWorld().spigot().playEffect(e.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLAME, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), firstTickRad, tickingRad);
    }

    public static void moveHeadAround(final Entity head, final Player p, final Integer damage) {
        (new BukkitRunnable() {
            public void run() {
                if (head.isDead()) {
                    this.cancel();
                } else {
                    int i1 = 0;
                    if (VoidgloomSeraph.NUKEKUBI_DAMAGE.containsKey(p)) {
                        i1 = VoidgloomSeraph.NUKEKUBI_DAMAGE.get(p);
                    }

                    if (head.getWorld().equals(p.getWorld())) {
                        Sputnik.drawLineforMovingPoints(head.getLocation().add(head.getLocation().getDirection().multiply(0.1D)).add(0.0D, 2.0D, 0.0D), p.getLocation().add(0.0D, 1.8D, 0.0D), 20.0D, p, damage, head);
                        Sputnik.dmgc(i1, p, head);
                    } else {
                        head.remove();
                    }

                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 20L);
    }

    public static void damageUpdate(double formula) {
        NUKEKUBI_DAMAGE.put(getPlayer(), (int) formula);
    }

    public static void targetSelect(Entity entity, LivingEntity target) {
        if (entity instanceof Creature) {
            ((Creature) entity).setTarget(target);
        }
    }

    public static void updateSkill(List<Entity> list) {
        Iterator var1 = list.iterator();

        while (var1.hasNext()) {
            Entity e = (Entity) var1.next();
            NUKEKUBI_TARGET.remove(e);
        }

    }

    public static BukkitTask a(final ArmorStand entity) {
        return (new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                } else {
                    org.bukkit.World world = entity.getWorld();
                    if (entity.hasMetadata("BeaconSkill") && entity.isOnGround()) {
                        entity.remove();
                        if (VoidgloomSeraph.getPlayer() != null) {
                            VoidgloomSeraph.getPlayer().getWorld().playSound(VoidgloomSeraph.getPlayer().getLocation(), Sound.ITEM_BREAK, 0.5F, 0.67F);
                        }

                        Location loc = entity.getLocation().getBlock().getLocation().add(0.5D, 0.0D, 0.5D);
                        ArmorStand armorStand2 = (ArmorStand) entity.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                        VoidgloomSeraph.CACHED_BLOCK.put(armorStand2, loc.getBlock());
                        VoidgloomSeraph.CACHED_BLOCK_ID.put(armorStand2, loc.getBlock().getTypeId());
                        VoidgloomSeraph.CACHED_BLOCK_DATA.put(armorStand2, loc.getBlock().getData());
                        armorStand2.setGravity(true);
                        armorStand2.setVisible(false);
                        armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&c☠ &fTEST"));
                        armorStand2.setMetadata("BeaconSkill2", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
                        loc.getBlock().setType(Material.BARRIER);
                        Repeater.BEACON_THROW2.put(Repeater.BEACON_OWNER.get(entity).getUniqueId(), armorStand2);
                        VoidgloomSeraph.b(armorStand2);
                        Repeater.BEACON.put(armorStand2, BlockFallAPI.sendBlockDestroyWithSignal(loc, Material.BEACON, (byte) 0, world));
                    }

                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
    }

    public static void b(final Entity armorStand2) {
        (new BukkitRunnable() {
            public void run() {
                if (armorStand2.isDead()) {
                    this.cancel();
                } else {
                    org.bukkit.World world = armorStand2.getWorld();
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                    world.playEffect(armorStand2.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FLYING_GLYPH, 3);
                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void teleportSkill(final Entity e, final Player p) {
        final int LOR = SUtil.random(0, 1);
        (new BukkitRunnable() {
            int cout = 0;
            float addedYaw = p.getLocation().getYaw();

            public void run() {
                if (this.cout >= 7) {
                    p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 1.0F, 1.0F);
                    this.cancel();
                } else {
                    Location a = p.getLocation();
                    if (LOR == 0) {
                        this.addedYaw += 19.0F;
                    } else {
                        this.addedYaw -= 19.0F;
                    }

                    a.setPitch(0.0F);
                    a.setYaw(this.addedYaw);
                    a.add(a.getDirection().normalize().multiply(1.7D));
                    a.setY(e.getLocation().getY());
                    Location tpl = a.clone();
                    tpl.setYaw(e.getLocation().getYaw());
                    e.teleport(tpl);
                    VoidgloomSeraph.this.dP(a);
                    a.getWorld().playSound(a, Sound.ENDERMAN_TELEPORT, 0.2F, 1.0F);
                    ++this.cout;
                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void drawPointerAt(Location loc) {
        loc.getWorld().spigot().playEffect(loc, Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
        loc.getWorld().spigot().playEffect(loc, Effect.LARGE_SMOKE, 21, 0, 0.1F, 0.0F, 0.1F, 0.01F, 1, 30);
        loc.getWorld().spigot().playEffect(loc, Effect.LARGE_SMOKE, 21, 0, 0.1F, 0.0F, 0.1F, 0.01F, 1, 30);
        loc.getWorld().spigot().playEffect(loc, Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
    }

    public void dP(Location loc) {
        this.drawPointerAt(loc.clone().add(0.0D, 0.9D, 0.0D));
        this.drawPointerAt(loc.clone().add(0.0D, 1.5D, 0.0D));
    }

    public void playShieldParticle(final Entity e) {
        (new BukkitRunnable() {
            float cout = e.getLocation().getYaw();

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                } else {
                    Location loc = e.getLocation();
                    loc.setYaw(this.cout);
                    loc.setPitch(0.0F);
                    loc.add(loc.getDirection().normalize().multiply(0.8D));
                    if (VoidgloomSeraph.HIT_SHIELD.containsKey(e)) {
                        int hitshield = VoidgloomSeraph.HIT_SHIELD.get(e);
                        int hitshieldmax = VoidgloomSeraph.HIT_SHIELD_MAX.get(e);
                        int stage = 3;
                        if (hitshield <= hitshieldmax / 2 && hitshield > hitshieldmax * 25 / 100) {
                            stage = 2;
                        } else if (hitshield <= hitshieldmax * 25 / 100 && hitshield != 1) {
                            stage = 1;
                        } else if (hitshield == 1) {
                            stage = 1;
                        }

                        if (VoidgloomSeraph.HIT_SHIELD.get(e) > 0) {
                            e.getWorld().spigot().playEffect(loc, Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                            e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 0.6D, 0.0D), Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                            e.getWorld().spigot().playEffect(loc, Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                            e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 0.6D, 0.0D), Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                            if (stage >= 2) {
                                e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 1.2D, 0.0D), Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                                e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 1.2D, 0.0D), Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                            }

                            if (stage == 3) {
                                e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 2.4D, 0.0D), Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                                e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 2.4D, 0.0D), Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                                e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 1.8D, 0.0D), Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                                e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 1.8D, 0.0D), Effect.WITCH_MAGIC, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                            }
                        }
                    }

                    this.cout += 18.0F;
                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void playBossParticle_1(final Entity e) {
        (new BukkitRunnable() {
            float cout = e.getLocation().getYaw();

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                } else {
                    Location loc = e.getLocation().clone().add(0.0D, 0.3D, 0.0D);
                    loc.setYaw(this.cout);
                    loc.setPitch(0.0F);
                    loc.add(loc.getDirection().normalize().multiply(0.4D));
                    e.getWorld().spigot().playEffect(loc, Effect.CRIT, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                    e.getWorld().spigot().playEffect(loc, Effect.CRIT, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                    this.cout += 9.0F;
                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void playBossParticle_2(final Entity e) {
        (new BukkitRunnable() {
            float cout = e.getLocation().getYaw();

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                } else {
                    Location loc = e.getLocation().clone().add(0.0D, 0.3D, 0.0D);
                    loc.setYaw(this.cout);
                    loc.setPitch(0.0F);
                    loc.add(loc.getDirection().normalize().multiply(0.4D));
                    e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 0.6D, 0.0D), Effect.CRIT, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                    e.getWorld().spigot().playEffect(loc.clone().add(0.0D, 0.6D, 0.0D), Effect.CRIT, 0, 1, 1.0F, 1.0F, 1.0F, 0.0F, 0, 64);
                    this.cout -= 9.0F;
                }
            }
        }).runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
    }

}
