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

@CommandParameters(description="Gets the NBT of your current item.", aliases="fm", permission="sse.cc")
public class CookieMerchantCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (PlayerUtils.getCookieDurationTicks(player) <= 0L) {
            this.send(Sputnik.trans("&cYou need the Cookie Buff active to use this feature!"));
            player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
            return;
        }
        GUIType.FARM_MERCHANT.getGUI().open(player);
    }
}

