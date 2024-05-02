/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EntityInsentient
 *  net.minecraft.server.v1_8_R3.EntityTypes
 *  org.bukkit.entity.EntityType
 */
package vn.giakhanhvn.skysim.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityTypes;
import org.bukkit.entity.EntityType;
import vn.giakhanhvn.skysim.dimoon.Dimoonizae;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.EntityStatistics;
import vn.giakhanhvn.skysim.entity.Stronker;
import vn.giakhanhvn.skysim.entity.caverns.DiamondSkeleton;
import vn.giakhanhvn.skysim.entity.caverns.DiamondZombie;
import vn.giakhanhvn.skysim.entity.caverns.EnchantedDiamondSkeleton;
import vn.giakhanhvn.skysim.entity.caverns.EnchantedDiamondZombie;
import vn.giakhanhvn.skysim.entity.caverns.LapisZombie;
import vn.giakhanhvn.skysim.entity.caverns.LargeSlime;
import vn.giakhanhvn.skysim.entity.caverns.MediumSlime;
import vn.giakhanhvn.skysim.entity.caverns.Pigman;
import vn.giakhanhvn.skysim.entity.caverns.SmallSlime;
import vn.giakhanhvn.skysim.entity.caverns.UndeadGiaKhanhvn;
import vn.giakhanhvn.skysim.entity.den.BroodMother;
import vn.giakhanhvn.skysim.entity.den.CaveSpider;
import vn.giakhanhvn.skysim.entity.den.DasherSpider;
import vn.giakhanhvn.skysim.entity.den.MutantTarantula;
import vn.giakhanhvn.skysim.entity.den.Silverfish;
import vn.giakhanhvn.skysim.entity.den.SpiderJockey;
import vn.giakhanhvn.skysim.entity.den.SpidersDenSkeleton;
import vn.giakhanhvn.skysim.entity.den.SpidersDenSlime;
import vn.giakhanhvn.skysim.entity.den.SplitterSpider;
import vn.giakhanhvn.skysim.entity.den.TarantulaBeast;
import vn.giakhanhvn.skysim.entity.den.TarantulaVermin;
import vn.giakhanhvn.skysim.entity.den.VoraciousSpider;
import vn.giakhanhvn.skysim.entity.den.WeaverSpider;
import vn.giakhanhvn.skysim.entity.dungeons.TestingMob;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.BigfootGiant;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.DiamondGiant;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.GiantsDummy;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.JollyPinkGiant;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.LASRGiant;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanDummy;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanDummy_Idle;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanGiant;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanHuman;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SleepingGolem_S;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.TerracottaDummy;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.TerracottaSadan;
import vn.giakhanhvn.skysim.entity.dungeons.minibosses.AngryArchaeologist;
import vn.giakhanhvn.skysim.entity.dungeons.minibosses.FrozenAdv;
import vn.giakhanhvn.skysim.entity.dungeons.minibosses.HolyLostAdv;
import vn.giakhanhvn.skysim.entity.dungeons.minibosses.ShadowAssassins;
import vn.giakhanhvn.skysim.entity.dungeons.minibosses.SuperiorLostAdv;
import vn.giakhanhvn.skysim.entity.dungeons.minibosses.Unstable;
import vn.giakhanhvn.skysim.entity.dungeons.minibosses.YoungLostAdv;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.CryptDreadlord;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.CryptLurker;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.CryptSoulstealer;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.CryptUndead;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.Fels;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.ScaredSkeleton;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.SkeletonMaster;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.SkeletonSoldier;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.Skeletor;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.SkeletorPrime;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.Sniper;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.SuperArcher;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.SuperTankZombie;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.TankZombie;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.UndeadSkeleton;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.Withermancer;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.ZombieKnight;
import vn.giakhanhvn.skysim.entity.dungeons.regularentity.ZombieSoldier;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherBonzo;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherCannibal;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherFlamer;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherFreak;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherFrost;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherLeech;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherLivid;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherMrDead;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherMute;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherOoze;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherParasite;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherParasiteFish;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherPsycho;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherPutrid;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherReaper;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherRevoker;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherSkull;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherTear;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherVader;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.WatcherWalker;
import vn.giakhanhvn.skysim.entity.end.Enderman;
import vn.giakhanhvn.skysim.entity.end.ObsidianDefender;
import vn.giakhanhvn.skysim.entity.end.StrongEnderman;
import vn.giakhanhvn.skysim.entity.end.VoidcrazedManiac;
import vn.giakhanhvn.skysim.entity.end.VoidlingDevotee;
import vn.giakhanhvn.skysim.entity.end.VoidlingExtremist;
import vn.giakhanhvn.skysim.entity.end.VoidlingFanatic;
import vn.giakhanhvn.skysim.entity.end.VoidlingRadical;
import vn.giakhanhvn.skysim.entity.end.VoidlingsWardenMob;
import vn.giakhanhvn.skysim.entity.end.Watcher;
import vn.giakhanhvn.skysim.entity.end.WeakEnderman;
import vn.giakhanhvn.skysim.entity.end.Zealot;
import vn.giakhanhvn.skysim.entity.insentient.WheatCrystal;
import vn.giakhanhvn.skysim.entity.nether.LargeMagmaCube;
import vn.giakhanhvn.skysim.entity.nether.MediumMagmaCube;
import vn.giakhanhvn.skysim.entity.nether.SmallMagmaCube;
import vn.giakhanhvn.skysim.entity.nether.WitherSkeleton;
import vn.giakhanhvn.skysim.entity.nms.AtonedHorror;
import vn.giakhanhvn.skysim.entity.nms.BorisYeltsin;
import vn.giakhanhvn.skysim.entity.nms.CrimsonSathanas;
import vn.giakhanhvn.skysim.entity.nms.Giant;
import vn.giakhanhvn.skysim.entity.nms.OldDragon;
import vn.giakhanhvn.skysim.entity.nms.ProtectorDragon;
import vn.giakhanhvn.skysim.entity.nms.RevenantHorror;
import vn.giakhanhvn.skysim.entity.nms.SneakyCreeper;
import vn.giakhanhvn.skysim.entity.nms.StrongDragon;
import vn.giakhanhvn.skysim.entity.nms.SuperiorDragon;
import vn.giakhanhvn.skysim.entity.nms.SvenPackmaster;
import vn.giakhanhvn.skysim.entity.nms.TarantulaBroodfather;
import vn.giakhanhvn.skysim.entity.nms.UncollidableArmorStand;
import vn.giakhanhvn.skysim.entity.nms.UnstableDragon;
import vn.giakhanhvn.skysim.entity.nms.VelocityArmorStand;
import vn.giakhanhvn.skysim.entity.nms.VoidgloomSeraph;
import vn.giakhanhvn.skysim.entity.nms.WiseDragon;
import vn.giakhanhvn.skysim.entity.nms.YoungDragon;
import vn.giakhanhvn.skysim.entity.skeleton.HighLevelSkeleton;
import vn.giakhanhvn.skysim.entity.wolf.HowlingSpirit;
import vn.giakhanhvn.skysim.entity.wolf.OldWolf;
import vn.giakhanhvn.skysim.entity.wolf.PackEnforcer;
import vn.giakhanhvn.skysim.entity.wolf.PackSpirit;
import vn.giakhanhvn.skysim.entity.wolf.SoulOfTheAlpha;
import vn.giakhanhvn.skysim.entity.wolf.SvenAlpha;
import vn.giakhanhvn.skysim.entity.wolf.SvenFollower;
import vn.giakhanhvn.skysim.entity.wolf.SvenPup;
import vn.giakhanhvn.skysim.entity.wolf.Wolf;
import vn.giakhanhvn.skysim.entity.zombie.AtonedChampion;
import vn.giakhanhvn.skysim.entity.zombie.AtonedRevenant;
import vn.giakhanhvn.skysim.entity.zombie.CryptGhoul;
import vn.giakhanhvn.skysim.entity.zombie.DeformedRevenant;
import vn.giakhanhvn.skysim.entity.zombie.DiamondGoblinzine;
import vn.giakhanhvn.skysim.entity.zombie.Goblinzine;
import vn.giakhanhvn.skysim.entity.zombie.GoldenGhoul;
import vn.giakhanhvn.skysim.entity.zombie.RevenantChampion;
import vn.giakhanhvn.skysim.entity.zombie.RevenantSycophant;
import vn.giakhanhvn.skysim.entity.zombie.Zombie;
import vn.giakhanhvn.skysim.entity.zombie.ZombieVillager;

public enum SEntityType {
    WATCHER_CANNIBAL(EntityType.ZOMBIE, WatcherCannibal.class),
    WATCHER_FLAMER(EntityType.ZOMBIE, WatcherFlamer.class),
    WATCHER_FREAK(EntityType.ZOMBIE, WatcherFreak.class),
    WATCHER_FROST(EntityType.ZOMBIE, WatcherFrost.class),
    WATCHER_LEECH(EntityType.ZOMBIE, WatcherLeech.class),
    WATCHER_MR_DEAD(EntityType.ZOMBIE, WatcherMrDead.class),
    WATCHER_MUTE(EntityType.ZOMBIE, WatcherMute.class),
    WATCHER_OOZE(EntityType.ZOMBIE, WatcherOoze.class),
    WATCHER_PARASITE(EntityType.ZOMBIE, WatcherParasite.class),
    WATCHER_PARASITE_SILVERFISH(EntityType.SILVERFISH, WatcherParasiteFish.class),
    WATCHER_PSYCHO(EntityType.ZOMBIE, WatcherPsycho.class),
    WATCHER_PUTRID(EntityType.ZOMBIE, WatcherPutrid.class),
    WATCHER_REAPER(EntityType.ZOMBIE, WatcherReaper.class),
    WATCHER_REVOKER(EntityType.ZOMBIE, WatcherRevoker.class),
    WATCHER_SKULL(EntityType.ZOMBIE, WatcherSkull.class),
    WATCHER_TEAR(EntityType.ZOMBIE, WatcherTear.class),
    WATCHER_VADER(EntityType.ZOMBIE, WatcherVader.class),
    WATCHER_WALKER(EntityType.ZOMBIE, WatcherWalker.class),
    WATCHER_LIVID(EntityType.ZOMBIE, WatcherLivid.class),
    WATCHER_BONZO(EntityType.ZOMBIE, WatcherBonzo.class),
    GOBLIN_DIMOON(EntityType.ZOMBIE, Goblinzine.class),
    DGOBLIN_DIMOON(EntityType.ZOMBIE, DiamondGoblinzine.class),
    DIMOON_MINIBOSS(EntityType.ZOMBIE, Dimoonizae.class),
    LOST_ADV_SUP(EntityType.ZOMBIE, SuperiorLostAdv.class),
    LOST_ADV_YOUNG(EntityType.ZOMBIE, YoungLostAdv.class),
    LOST_ADV_UNSTABLE(EntityType.ZOMBIE, Unstable.class),
    LOST_ADV_HOLY(EntityType.ZOMBIE, HolyLostAdv.class),
    ANGRY_ARCH(EntityType.ZOMBIE, AngryArchaeologist.class),
    FROZEN_ADV(EntityType.ZOMBIE, FrozenAdv.class),
    BOSS_BORIS_YELTSIN(EntityType.ZOMBIE, BorisYeltsin.class),
    SCARY_CAVE_UNDEADBOSS_1(EntityType.ZOMBIE, UndeadGiaKhanhvn.class),
    SHADOW_ASSASSINS(EntityType.ZOMBIE, ShadowAssassins.class),
    TERRORANT(EntityType.ZOMBIE, Giant.class),
    TEST_OBJECT(EntityType.ZOMBIE, Zombie.class),
    VOIDLINGS_WARDEN(EntityType.ZOMBIE, VoidlingsWardenMob.class),
    TEST_CHIMMY_OBJECT_T34(EntityType.ZOMBIE, TestingMob.class),
    TERRACOTTA_SADAN(EntityType.ZOMBIE, TerracottaSadan.class),
    BIGFOOT_SADAN(EntityType.ZOMBIE, BigfootGiant.class),
    LASR_SADAN(EntityType.ZOMBIE, LASRGiant.class),
    JOLLY_PINK_SADAN(EntityType.ZOMBIE, JollyPinkGiant.class),
    DIAMOND_SADAN(EntityType.ZOMBIE, DiamondGiant.class),
    SLEEPING_GOLEM(EntityType.ZOMBIE, SleepingGolem_S.class),
    WOKE_GOLEM(EntityType.ZOMBIE, Stronker.class),
    GIANT_SADAN(EntityType.ZOMBIE, SadanGiant.class),
    SADAN(EntityType.ZOMBIE, SadanHuman.class),
    DUMMY_SADAN_1(EntityType.ZOMBIE, SadanDummy_Idle.class),
    DUMMY_FUNCTION_2(EntityType.ZOMBIE, SadanDummy.class),
    GIANT_DUMMY(EntityType.ZOMBIE, GiantsDummy.class),
    TERRACOTTA_DUMMY(EntityType.ZOMBIE, TerracottaDummy.class),
    FELS(EntityType.ENDERMAN, Fels.class),
    WITHERMANCER(EntityType.SKELETON, Withermancer.class),
    TANK_ZOMBIE(EntityType.ZOMBIE, TankZombie.class),
    CRYPT_DREADLORD(EntityType.ZOMBIE, CryptDreadlord.class),
    CRYPT_UNDEAD(EntityType.ZOMBIE, CryptUndead.class),
    CRYPT_SOULSTEALER(EntityType.ZOMBIE, CryptSoulstealer.class),
    CRYPT_LURKER(EntityType.ZOMBIE, CryptLurker.class),
    SCARED_SKELETON(EntityType.SKELETON, ScaredSkeleton.class),
    SKELETON_MASTER(EntityType.SKELETON, SkeletonMaster.class),
    SNIPER(EntityType.SKELETON, Sniper.class),
    SUPER_ARCHER(EntityType.SKELETON, SuperArcher.class),
    SUPER_TANK_ZOMBIE(EntityType.ZOMBIE, SuperTankZombie.class),
    UNDEAD_SKELETON(EntityType.SKELETON, UndeadSkeleton.class),
    ZOMBIE_KNIGHT(EntityType.ZOMBIE, ZombieKnight.class),
    ZOMBIE_SOLDIER(EntityType.ZOMBIE, ZombieSoldier.class),
    SKELETON_SOLDIER(EntityType.SKELETON, SkeletonSoldier.class),
    SKELETOR(EntityType.ZOMBIE, Skeletor.class),
    SKELETOR_PRIME(EntityType.ZOMBIE, SkeletorPrime.class),
    ZEALOT(EntityType.ENDERMAN, Zealot.class),
    ENDER_CHEST_ZEALOT(EntityType.ENDERMAN, Zealot.EnderChestZealot.class),
    VOIDGLOOM_SERAPH(EntityType.ENDERMAN, VoidgloomSeraph.class, true),
    CRIMSON_SATHANAS(EntityType.SKELETON, CrimsonSathanas.class, true),
    VOIDLING_DEVOTEE(EntityType.ENDERMAN, VoidlingDevotee.class),
    VOIDLING_RADICAL(EntityType.ENDERMAN, VoidlingRadical.class),
    VOIDCRAZED_MANIAC(EntityType.ENDERMAN, VoidcrazedManiac.class),
    VOIDLING_EXTREMIST(EntityType.ENDERMAN, VoidlingExtremist.class),
    VOIDLING_FANATIC(EntityType.ENDERMAN, VoidlingFanatic.class),
    SPECIAL_ZEALOT(EntityType.ENDERMAN, Zealot.SpecialZealot.class),
    PROTECTOR_DRAGON(EntityType.ENDER_DRAGON, ProtectorDragon.class),
    OLD_DRAGON(EntityType.ENDER_DRAGON, OldDragon.class),
    WISE_DRAGON(EntityType.ENDER_DRAGON, WiseDragon.class),
    UNSTABLE_DRAGON(EntityType.ENDER_DRAGON, UnstableDragon.class),
    YOUNG_DRAGON(EntityType.ENDER_DRAGON, YoungDragon.class),
    STRONG_DRAGON(EntityType.ENDER_DRAGON, StrongDragon.class),
    SUPERIOR_DRAGON(EntityType.ENDER_DRAGON, SuperiorDragon.class),
    REVENANT_HORROR(EntityType.ZOMBIE, RevenantHorror.class, true),
    REVENANT_SYCOPHANT(EntityType.ZOMBIE, RevenantSycophant.class),
    REVENANT_CHAMPION(EntityType.ZOMBIE, RevenantChampion.class),
    DEFORMED_REVENANT(EntityType.ZOMBIE, DeformedRevenant.class),
    ATONED_HORROR(EntityType.ZOMBIE, AtonedHorror.class),
    ATONED_REVENANT(EntityType.ZOMBIE, AtonedRevenant.class),
    ATONED_CHAMPION(EntityType.ZOMBIE, AtonedChampion.class),
    SVEN_PACKMASTER(EntityType.WOLF, SvenPackmaster.class, true),
    SVEN_PUP(EntityType.WOLF, SvenPup.class, true),
    SVEN_FOLLOWER(EntityType.WOLF, SvenFollower.class),
    PACK_ENFORCER(EntityType.WOLF, PackEnforcer.class),
    SVEN_ALPHA(EntityType.WOLF, SvenAlpha.class),
    TARANTULA_BROODFATHER(EntityType.SPIDER, TarantulaBroodfather.class),
    TOP_CAVE_SPIDER(EntityType.CAVE_SPIDER, TarantulaBroodfather.TopCaveSpider.class),
    TARANTULA_VERMIN(EntityType.SPIDER, TarantulaVermin.class),
    TARANTULA_BEAST(EntityType.SPIDER, TarantulaBeast.class),
    MUTANT_TARANTULA(EntityType.SPIDER, MutantTarantula.class),
    WATCHER(EntityType.SKELETON, Watcher.class),
    OBSIDIAN_DEFENDER(EntityType.SKELETON, ObsidianDefender.class),
    VELOCITY_ARMOR_STAND(EntityType.ARMOR_STAND, VelocityArmorStand.class),
    UNCOLLIDABLE_ARMOR_STAND(EntityType.ARMOR_STAND, UncollidableArmorStand.class),
    WHEAT_CRYSTAL(EntityType.ARMOR_STAND, WheatCrystal.class),
    LAPIS_ZOMBIE(EntityType.ZOMBIE, LapisZombie.class),
    PIGMAN(EntityType.PIG_ZOMBIE, Pigman.class),
    SMALL_SLIME(EntityType.SLIME, SmallSlime.class),
    MEDIUM_SLIME(EntityType.SLIME, MediumSlime.class),
    LARGE_SLIME(EntityType.SLIME, LargeSlime.class),
    DIAMOND_ZOMBIE(EntityType.ZOMBIE, DiamondZombie.class),
    DIAMOND_SKELETON(EntityType.SKELETON, DiamondSkeleton.class),
    ENCHANTED_DIAMOND_ZOMBIE(EntityType.ZOMBIE, EnchantedDiamondZombie.class),
    ENCHANTED_DIAMOND_SKELETON(EntityType.SKELETON, EnchantedDiamondSkeleton.class),
    WEAK_ENDERMAN(EntityType.ENDERMAN, WeakEnderman.class),
    ENDERMAN(EntityType.ENDERMAN, Enderman.class),
    STRONG_ENDERMAN(EntityType.ENDERMAN, StrongEnderman.class),
    SPLITTER_SPIDER(EntityType.SPIDER, SplitterSpider.class),
    SILVERFISH(EntityType.SILVERFISH, Silverfish.class),
    SPIDER_JOCKEY(EntityType.SPIDER, SpiderJockey.class),
    JOCKEY_SKELETON(EntityType.SKELETON, SpiderJockey.JockeySkeleton.class),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, CaveSpider.class),
    BROOD_MOTHER(EntityType.SPIDER, BroodMother.class),
    DASHER_SPIDER(EntityType.SPIDER, DasherSpider.class),
    WEAVER_SPIDER(EntityType.SPIDER, WeaverSpider.class),
    VORACIOUS_SPIDER(EntityType.SPIDER, VoraciousSpider.class),
    ZOMBIE(EntityType.ZOMBIE, Zombie.class),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE, ZombieVillager.class),
    CRYPT_GHOUL(EntityType.ZOMBIE, CryptGhoul.class),
    GOLDEN_GHOUL(EntityType.ZOMBIE, GoldenGhoul.class),
    WOLF(EntityType.WOLF, Wolf.class),
    OLD_WOLF(EntityType.WOLF, OldWolf.class),
    PACK_SPIRIT(EntityType.WOLF, PackSpirit.class),
    HOWLING_SPIRIT(EntityType.WOLF, HowlingSpirit.class),
    SOUL_OF_THE_ALPHA(EntityType.WOLF, SoulOfTheAlpha.class),
    SPIDERS_DEN_SKELETON(EntityType.SKELETON, SpidersDenSkeleton.class),
    HIGH_LEVEL_SKELETON(EntityType.SKELETON, HighLevelSkeleton.class),
    SPIDERS_DEN_SLIME(EntityType.SLIME, SpidersDenSlime.class),
    SNEAKY_CREEPER(EntityType.CREEPER, SneakyCreeper.class),
    WITHER_SKELETON(EntityType.SKELETON, WitherSkeleton.class),
    SMALL_MAGMA_CUBE(EntityType.MAGMA_CUBE, SmallMagmaCube.class),
    MEDIUM_MAGMA_CUBE(EntityType.MAGMA_CUBE, MediumMagmaCube.class),
    LARGE_MAGMA_CUBE(EntityType.MAGMA_CUBE, LargeMagmaCube.class);

    private final EntityType craftType;
    private final Class<?> clazz;
    private final boolean specific;

    private SEntityType(EntityType craftType, Class<?> clazz, boolean specific) {
        this.craftType = craftType;
        this.clazz = clazz;
        this.specific = specific;
        if (EntityInsentient.class.isAssignableFrom(clazz)) {
            SEntityType.registerEntity(this.name(), craftType.getTypeId(), (Class<? extends EntityInsentient>) clazz);
        }
    }

    private SEntityType(EntityType craftType, Class<?> clazz) {
        this(craftType, clazz, false);
    }

    public EntityStatistics getStatistics() {
        Object generic = this.getGenericInstance();
        if (generic instanceof EntityStatistics) {
            return (EntityStatistics)generic;
        }
        return null;
    }

    public EntityFunction getFunction() {
        Object generic = this.getGenericInstance();
        if (generic instanceof EntityFunction) {
            return (EntityFunction)generic;
        }
        return null;
    }

    public Object instance(Object ... params) {
        try {
            Class[] paramTypes = new Class[params.length];
            for (int i = 0; i < paramTypes.length; ++i) {
                paramTypes[i] = params[i].getClass();
            }
            return this.clazz.getConstructor(paramTypes).newInstance(params);
        }
        catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Object getGenericInstance() {
        try {
            return this.clazz.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void registerEntity(String name, int id, Class<? extends EntityInsentient> clazz) {
        try {
            ArrayList<Map> dataMap = new ArrayList<Map>();
            for (Field f : EntityTypes.class.getDeclaredFields()) {
                if (!f.getType().getSimpleName().equals(Map.class.getSimpleName())) continue;
                f.setAccessible(true);
                dataMap.add((Map)f.get(null));
            }
            if (((Map)dataMap.get(2)).containsKey(id)) {
                ((Map)dataMap.get(0)).remove(name);
                ((Map)dataMap.get(2)).remove(id);
            }
            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, Integer.TYPE);
            method.setAccessible(true);
            method.invoke(null, clazz, name, id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SEntityType getEntityType(String name) {
        return SEntityType.valueOf(name.toUpperCase());
    }

    public EntityType getCraftType() {
        return this.craftType;
    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public boolean isSpecific() {
        return this.specific;
    }
}

