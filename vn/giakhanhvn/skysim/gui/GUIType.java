/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.gui;

import vn.giakhanhvn.skysim.gui.ActiveEffectsGUI;
import vn.giakhanhvn.skysim.gui.AnvilGUI;
import vn.giakhanhvn.skysim.gui.AuctionConfirmGUI;
import vn.giakhanhvn.skysim.gui.AuctionHouseGUI;
import vn.giakhanhvn.skysim.gui.AuctionsBrowserGUI;
import vn.giakhanhvn.skysim.gui.BankerGUI;
import vn.giakhanhvn.skysim.gui.BossMenu;
import vn.giakhanhvn.skysim.gui.CollectionBoss;
import vn.giakhanhvn.skysim.gui.CollectionMenuGUI;
import vn.giakhanhvn.skysim.gui.CookieConfirmGUI;
import vn.giakhanhvn.skysim.gui.CookieGUI;
import vn.giakhanhvn.skysim.gui.CraftingTableGUI;
import vn.giakhanhvn.skysim.gui.CreateAuctionGUI;
import vn.giakhanhvn.skysim.gui.DepositGUI;
import vn.giakhanhvn.skysim.gui.DungeonsItemConverting;
import vn.giakhanhvn.skysim.gui.DungeonsLevelGUI;
import vn.giakhanhvn.skysim.gui.FarmMerchantGUI;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.ItemBrowserGUI;
import vn.giakhanhvn.skysim.gui.LiftGUI;
import vn.giakhanhvn.skysim.gui.ManageAuctionsGUI;
import vn.giakhanhvn.skysim.gui.PetsGUI;
import vn.giakhanhvn.skysim.gui.QuiverGUI;
import vn.giakhanhvn.skysim.gui.RecipeBookListGUI;
import vn.giakhanhvn.skysim.gui.ReforgeAnvilGUI;
import vn.giakhanhvn.skysim.gui.RevenantHorrorGUI;
import vn.giakhanhvn.skysim.gui.SkillMenuGUI;
import vn.giakhanhvn.skysim.gui.SkyBlockMenuGUI;
import vn.giakhanhvn.skysim.gui.SkyBlockProfileGUI;
import vn.giakhanhvn.skysim.gui.SlayerGUI;
import vn.giakhanhvn.skysim.gui.SvenPackmasterGUI;
import vn.giakhanhvn.skysim.gui.TarantulaBroodfatherGUI;
import vn.giakhanhvn.skysim.gui.TrashGUI;
import vn.giakhanhvn.skysim.gui.VoidgloomSeraph;
import vn.giakhanhvn.skysim.gui.WithdrawalGUI;
import vn.giakhanhvn.skysim.gui.YourBidsGUI;
import vn.giakhanhvn.skysim.util.SUtil;

public enum GUIType {
    CRAFTING_TABLE(CraftingTableGUI.class),
    ITEM_BROWSE(ItemBrowserGUI.class),
    ANVIL(AnvilGUI.class),
    TRASH(TrashGUI.class),
    COOKIE_GUI(CookieGUI.class),
    COOKIE_CONSUME_CONFIRM(CookieConfirmGUI.class),
    RECIPE_BOOK(RecipeBookListGUI.class),
    REFORGE_ANVIL(ReforgeAnvilGUI.class),
    DUNGEON_CRAFTING(DungeonsItemConverting.class),
    DUNGEON_SKILL(DungeonsLevelGUI.class),
    BANKER(BankerGUI.class),
    BANKER_DEPOSIT(DepositGUI.class),
    BANKER_WITHDRAWAL(WithdrawalGUI.class),
    SKYBLOCK_MENU(SkyBlockMenuGUI.class),
    CATACOMBS_BOSS(BossMenu.class),
    BOSS_COLLECTION(CollectionBoss.class),
    SKYBLOCK_PROFILE(SkyBlockProfileGUI.class),
    QUIVER(QuiverGUI.class),
    LIFT(LiftGUI.class),
    SLAYER(SlayerGUI.class),
    REVENANT_HORROR(RevenantHorrorGUI.class),
    TARANTULA_BROODFATHER(TarantulaBroodfatherGUI.class),
    SVEN_PACKMASTER(SvenPackmasterGUI.class),
    COLLECTION_MENU(CollectionMenuGUI.class),
    SKILL_MENU(SkillMenuGUI.class),
    PETS(PetsGUI.class),
    FARM_MERCHANT(FarmMerchantGUI.class),
    ACTIVE_EFFECTS(ActiveEffectsGUI.class),
    AUCTION_HOUSE(AuctionHouseGUI.class),
    AUCTIONS_BROWSER(AuctionsBrowserGUI.class),
    CREATE_AUCTION(CreateAuctionGUI.class),
    AUCTION_CONFIRM(AuctionConfirmGUI.class),
    MANAGE_AUCTIONS(ManageAuctionsGUI.class),
    YOUR_BIDS(YourBidsGUI.class),
    VOIDGLOOM_SERAPH(VoidgloomSeraph.class);

    private final Class<? extends GUI> gui;

    private GUIType(Class<? extends GUI> gui) {
        this.gui = gui;
    }

    public GUI getGUI() {
        try {
            return this.gui.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public GUI getGUI(Object ... params) {
        return SUtil.instance(GUI.class, params);
    }

    public static GUI getGUI(String title) {
        for (GUIType type : GUIType.values()) {
            if (!type.getGUI().getTitle().contains(title)) continue;
            return type.getGUI();
        }
        return null;
    }
}

