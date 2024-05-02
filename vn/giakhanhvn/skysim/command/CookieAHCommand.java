/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="Gets the NBT of your current item.", aliases="auh", permission="sse.cc")
public class CookieAHCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (PlayerUtils.getCookieDurationTicks(player) <= 0L) {
            player.playSound(player.getLocation(), Sound.VILLAGER_YES, 0.0f, 2.0f);
            this.send(Sputnik.trans("&eYou need the &dCookie Buff &eto use this command!"));
            this.send(Sputnik.trans("&eObtain a &6Booster Cookie &efrom the community shop in the hub!"));
            player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
            return;
        }
        GUIType.AUCTION_HOUSE.getGUI().open(player);
    }
}

